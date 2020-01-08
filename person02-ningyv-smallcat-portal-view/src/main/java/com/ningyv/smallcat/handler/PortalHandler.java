package com.ningyv.smallcat.handler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author LCX
 * @create 2019-12-23 20:26
 */
@Controller
public class PortalHandler {
    @RequestMapping("/")
    public String goPortal(){
        return "index";
    }

    @RequestMapping("/to/member/register.html")
    public String toRegister(){
        return "member-register";
    }

    @RequestMapping("/forget/password.html")
    public String forPassword(){
        return "forget-Password";
    }
    @RequestMapping("/other/error.html")
    public String otherError(){
        return "other-error";
    }
}
