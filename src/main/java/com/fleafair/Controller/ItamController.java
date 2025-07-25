package com.fleafair.Controller;

import com.fleafair.Common.Result;
import com.fleafair.DTO.ItemReleaseDTO;
import com.fleafair.Entity.Item;
import com.fleafair.Service.ItemService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/item")
@Tag(name = "商品接口")
@Slf4j
public class ItamController {

    @Autowired
    private ItemService itemService;

    @PostMapping("/release")
    public Result<?> Release(@RequestBody ItemReleaseDTO releaseDTO,
                            @AuthenticationPrincipal Long userId){
        log.info("发布商品：{}", releaseDTO);

        return itemService.Release(releaseDTO, userId);
    }

}
