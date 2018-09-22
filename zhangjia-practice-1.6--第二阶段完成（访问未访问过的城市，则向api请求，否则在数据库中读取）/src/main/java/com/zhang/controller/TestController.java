package com.zhang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {

    //多个 url对应一个页面
    @RequestMapping(value={"/test","/test.html"})
    public String test()
    {
        return "test";
    }
}
