package cn.ssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeDemo {

    @RequestMapping(value = "/")
    public String demo1(){
        return "/Desk/Home";
    }
}
