package com.bytefuture.data.modules.data.controller;

import com.bytefuture.data.common.vo.ResponseResult;
import com.bytefuture.data.modules.data.service.AggregateDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author KendrickChen
 * @date 2024/1/11  10:29
 */
@Slf4j
@RestController
@RequestMapping("/aggregateData")
public class AggregateDataController {

    @Autowired
    private AggregateDataService baseService;

    @GetMapping("/start")
    public ResponseResult getInfoByApplyId(){
        return ResponseResult.ok(baseService.aggregateData());
    }
}
