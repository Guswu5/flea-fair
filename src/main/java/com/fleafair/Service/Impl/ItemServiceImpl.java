package com.fleafair.Service.Impl;

import com.fleafair.Common.Result;
import com.fleafair.DTO.ItemReleaseDTO;
import com.fleafair.Entity.Item;
import com.fleafair.Mapper.ItemMapper;
import com.fleafair.Service.ItemService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.constraints.Size;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        item.setUserId(userId);
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

}
