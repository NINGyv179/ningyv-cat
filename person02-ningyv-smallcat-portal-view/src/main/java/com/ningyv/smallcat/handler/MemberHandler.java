package com.ningyv.smallcat.handler;

import com.ningyv.smallcat.api.AuthoService;
import com.ningyv.smallcat.constant.Constant;
import com.ningyv.smallcat.entity.vo.MemberLoginVo;
import com.ningyv.smallcat.entity.vo.MemberSuccessVo;
import com.ningyv.smallcat.entity.vo.MemberVo;
import com.ningyv.smallcat.resultEntity.ResultEntity;
import com.ningyv.smallcat.utils.CrowdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.spring5.context.SpringContextUtils;

/**
 * @author LCX
 * @create 2019-12-23 21:02
 */
@Controller
public class MemberHandler {

    @Autowired
    public AuthoService authoService;

    @RequestMapping("/member/do/login.html")
    public String doLogin(MemberLoginVo memberVo, Model model){

        String userpswd = memberVo.getUserpswd();

        System.out.println(userpswd);
        userpswd = CrowdUtils.md5(userpswd);

        memberVo.setUserpswd(userpswd);

        ResultEntity<MemberSuccessVo> memberVoResultEntity = authoService.doLogin(memberVo);

        if (ResultEntity.FAILED.equals(memberVoResultEntity.getResult())){

            model.addAttribute(Constant.ATTR_NAME_ERROR_MESSAGE,memberVoResultEntity.getMessage());

            return "portal";
        }

        MemberSuccessVo data = memberVoResultEntity.getData();

        model.addAttribute(Constant.ATTR_NAME_LOGIN_MEMBER,data);

        return "member-conter";


    }
}
