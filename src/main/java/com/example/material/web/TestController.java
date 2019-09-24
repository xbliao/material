package com.example.material.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TestController.java
 *
 * @author xbliao   2019/9/24
 */

@RestController
@RequestMapping
public class TestController {



    @RequestMapping("/sayHello")

    public String hello(){

        return "Hello,SpringBoot!";

    }


}