package com.raysun.shirodemo.web.mvc;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ShiroDemoController {
	
	@RequestMapping("/hello1")
    public String hello1() {
		System.out.println("begin checking privilege by m1.");
        SecurityUtils.getSubject().checkRole("admin");
        return "success";
    }

    @RequiresRoles("admin")
    @RequestMapping("/hello2")
    public String hello2() {
    	System.out.println("begin checking privilege by m2.");
        return "success";
    }
    
}
