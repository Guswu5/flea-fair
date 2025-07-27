package com.fleafair.Service.Impl;

import com.fleafair.Common.Result;
import com.fleafair.DTO.ItemReleaseDTO;
import com.fleafair.Entity.Item;
import com.fleafair.Entity.User;
import com.fleafair.Mapper.ItemMapper;
import com.fleafair.Mapper.UserMapper;
import com.fleafair.Service.ItemService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fleafair.Service.UserService;
import com.fleafair.VO.ItemVO;
import com.fleafair.VO.SearchVO;
import com.fleafair.VO.UserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Map;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemMapper itemMapper;
    @Autowired
    private UserMapper userMapper;

    /**
     * 发布商品
     * @param releaseDTO
     * @return
     */
    @Override
    public Result<?> Release(ItemReleaseDTO releaseDTO , Long userId) {

        Item item = new Item();

        BeanUtils.copyProperties(releaseDTO, item);// 将releaseDTO中的属性复制到item中
        item.setUserId(userId + 10000);
        item.setCoverImage(releaseDTO.getImages().get(0));
        item.setStatus(1);

        //设置创建和修改时间
        item.setCreateTime(java.time.LocalDateTime.now());
        item.setUpdateTime(java.time.LocalDateTime.now());

        // images字段转为json字符串
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            item.setImagesJson(objectMapper.writeValueAsString(releaseDTO.getImages()));
        } catch (JsonProcessingException e) {
            return Result.error(400, "图片URL序列化失败");
        }
        // 向数据库插入商品
        itemMapper.insert(item);

        return Result.success(Map.of("itemId", item.getId()));
    }

    /**
     * 搜索商品
     *
     * @param keyword
     * @param page
     * @param size
     * @return
     */
    @Override
    public Result<?> SearchItems(String keyword, int page, int size) {
        // 计算偏移量
        int offset = (page - 1) * size;

        // 查询商品
        List<Item> items = itemMapper.searchItems(keyword, offset, size);

        //获取items里面的第一个categoryId
        int total = items.get(0).getCategoryId();


        // 构造返回结果
        Map<String, Object> result = Map.of(
            "list", items,
            "total", total
        );

        return Result.success(result);
    }

    /**
     * 根据id查询商品
     * @param id
     * @return
     */
    @Override
    public Result<?> getById(Long id) {
        // 从数据库获取商品详情
        Item item = itemMapper.getById(id);

        // 如果商品不存在，返回错误
        if (item == null) {
            return Result.error(404, "商品不存在");
        }

        // 解析图片JSON
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            if (item.getImagesJson() != null && !item.getImagesJson().isEmpty()) {
                List<String> images = objectMapper.readValue(item.getImagesJson(), List.class);
                item.setImages(images);
            }
        } catch (JsonProcessingException e) {
            return Result.error(500, "图片数据解析失败");
        }

        // 获取卖家信息
        User seller = userMapper.findById(item.getUserId() - 10000); // 转换为原始用户ID
        UserVO sellerVO = null;
        if (seller != null) {
            sellerVO = UserVO.builder()
                    .userId(seller.getId())
                    .username(seller.getUsername())
                    .avatar(seller.getAvatar() != null ? seller.getAvatar() : "") // 默认头像
                    .build();
        }

        // 构建ItemVO对象
        ItemVO itemVO = ItemVO.builder()
                .id(item.getId())
                .title(item.getTitle())
                .desc(item.getDesc())
                .price(item.getPrice())
                .images(item.getImages())
                .seller(sellerVO)
                .build();

        return Result.success(itemVO);
    }
}
