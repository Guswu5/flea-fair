package com.fleafair.Controller;

import com.fleafair.Common.Result;
import com.fleafair.DTO.ItemReleaseDTO;
import com.fleafair.Service.ItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/item")
@Tag(name = "商品接口")
@Slf4j
public class ItamController {

    @Autowired
    private ItemService itemService;

    /**
     * 发布商品
     * @param releaseDTO
     * @param userId
     * @return
     */
    @PostMapping("/release")
    @Operation(summary = "发布商品")
    public Result<?> Release(@RequestBody ItemReleaseDTO releaseDTO,
                            @AuthenticationPrincipal Long userId){
        log.info("发布商品：{}", releaseDTO);

        return itemService.Release(releaseDTO, userId);
    }

    /**
     * 搜索商品
     * @param keyword
     * @return
     */
    @GetMapping("/search")
    @Operation(summary = "搜索商品")
    public Result<?> Search(String keyword , int page, int size){
        log.info("搜索商品：{}", keyword);
        return itemService.SearchItems(keyword, page, size);
    }

    /**
     * 获取商品详情
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @Operation(summary = "获取商品详情")
    public Result<?> GetById(@PathVariable Long id){
        log.info("获取商品：{}", id);
        return itemService.getById(id);
    }

    /**
     * 获取默认商品列表
     * @return
     */
    @GetMapping
    @Operation(summary = "默认商品列表")
    public Result<?> DefaultItems(){
        log.info("获取默认商品列表");
        return itemService.GetDefaultItems();
    }
}
