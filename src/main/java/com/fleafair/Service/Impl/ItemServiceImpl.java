package com.fleafair.Service.Impl;

import com.fleafair.Common.Result;
import com.fleafair.DTO.ItemReleaseDTO;
import com.fleafair.Entity.Item;
import com.fleafair.Mapper.ItemMapper;
import com.fleafair.Service.ItemService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fleafair.VO.SearchVO;
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

}
