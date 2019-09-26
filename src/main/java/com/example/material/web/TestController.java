package com.example.material.web;

import com.example.material.service.MaterialStoreHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * TestController.java
 *
 * @author xbliao   2019/9/24
 */

@RestController
@RequestMapping
public class TestController {

    @Resource
    private MaterialStoreHandler materialStoreHandler;


    @RequestMapping("/sayHello")

    public String hello() {

        return "Hello,SpringBoot!";

    }

//    @RequestMapping("/test")
//
//    public String test() {
//        List<InboundMaterialsEntity> inboundMaterialsEntityList = materialStoreService.queryInboundMaterial(new Date());
//        return new Gson().toJson(inboundMaterialsEntityList);
//    }
}