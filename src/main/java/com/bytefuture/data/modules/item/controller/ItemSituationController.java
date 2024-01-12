package com.bytefuture.data.modules.item.controller;

import com.bytefuture.data.common.vo.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;
import com.bytefuture.data.modules.item.service.ItemSituationService;



/**
 * 事项情况
 *
 * @author kchen
 * @email galen1711@126.com
 * @date 2024-01-11 11:17:57
 */
@Slf4j
@RestController
@RequestMapping("/itemSituation")
public class ItemSituationController {
    @Autowired
    private ItemSituationService baseService;

    @GetMapping("/getItemSituationData")
    public ResponseResult getItemSituationData(){
        return ResponseResult.ok(baseService.getItemSituationData());
    }

    public ResponseResult dataDisplay(){
        baseService.dataDisplay();
        return ResponseResult.ok();
    }


}
