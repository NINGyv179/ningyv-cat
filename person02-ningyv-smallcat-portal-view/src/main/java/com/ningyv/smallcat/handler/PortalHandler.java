package com.ningyv.smallcat.handler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author LCX
 * @create 2019-12-23 20:26
 */
@Controller
public class PortalHandler {
    @RequestMapping("/")
    public String goPortal(){
        return "portal";
    }
}
