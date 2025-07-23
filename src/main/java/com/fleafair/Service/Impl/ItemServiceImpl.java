package com.fleafair.Service.Impl;

import com.fleafair.Common.Result;
import com.fleafair.DTO.ItemReleaseDTO;
import com.fleafair.Entity.Item;
import com.fleafair.Mapper.ItemMapper;
import com.fleafair.Service.ItemService;
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
    public Result<?> Release(ItemReleaseDTO releaseDTO) {

        Item item = new Item();
        BeanUtils.copyProperties(releaseDTO, item);

        //向数据库插入商品
        itemMapper.insert(item);

        return Result.success(Map.of("itemId", item.getId()));
    }

}
