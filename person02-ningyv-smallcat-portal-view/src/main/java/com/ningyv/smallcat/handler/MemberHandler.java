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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * @author LCX
 * @create 2019-12-23 21:02
 */
@Controller
public class MemberHandler {

    @Autowired
    public AuthoService authoService;


    //登录
    @ResponseBody
    @RequestMapping("/member/do/login.html")
    public ResultEntity<String> doLogin(MemberLoginVo memberVo, Model model, HttpServletRequest httpServletRequest){

        //获取图片的验证码
        String rightCode = (String) httpServletRequest.getSession().getAttribute("rightCode");
        //获取用户输入的验证码
        String tryCode = httpServletRequest.getParameter("vercode");
        if (!Objects.equals(rightCode,tryCode)){
            //设置错误信息
            model.addAttribute(Constant.ATTR_NAME_ERROR_MESSAGE,Constant.MSG_ERROE_YANZHENGMA_MEMBER);

            //返回错误信息
            return ResultEntity.failed(Constant.MSG_STRING_RANDOM_ERROR);
        }

        //获取用户输入的密码
        String userpswd = memberVo.getUserpswd();
        System.err.println(userpswd);
        //如果密码格式错误
        if (!CrowdUtils.stringEffectCheck(userpswd)){
            //设置错误信息
            model.addAttribute(Constant.ATTR_NAME_ERROR_MESSAGE,Constant.MSG_ERROE_MEMBER);

            //返回错误信息
            return ResultEntity.failed(Constant.MSG_ERROE_MEMBER_TO);
        }

        //加密输入的密码，和数据库的作比对
        userpswd = CrowdUtils.md5(userpswd);

        //把加密后的密码重新设置回去
        memberVo.setUserpswd(userpswd);

        //执行登录
        ResultEntity<MemberSuccessVo> memberVoResultEntity = authoService.doLogin(memberVo);

        //如果登陆失败
        if (ResultEntity.FAILED.equals(memberVoResultEntity.getResult())){

            model.addAttribute(Constant.ATTR_NAME_ERROR_MESSAGE,memberVoResultEntity.getMessage());


            //返回错误信息
            return ResultEntity.failed(memberVoResultEntity.getMessage());
        }

        //获取用户信息然后保存到数据域
        MemberSuccessVo data = memberVoResultEntity.getData();

        model.addAttribute(Constant.ATTR_NAME_LOGIN_MEMBER,data);

        //跳转到会员中心页面
        return ResultEntity.successWithoutData();
    }

    //发消息
    @ResponseBody
    @RequestMapping("/member/sent/short/message.html")
    public ResultEntity<String> sendMessage(@RequestParam("phoneNum") String phoneNum){
        //发消息
        return authoService.sendMessage(phoneNum);
    }

    //注册
    @ResponseBody
    @RequestMapping("/member/to/register.html")
    public ResultEntity<String> toRegister(MemberVo memberVo,Model model){

        if (memberVo==null){
            model.addAttribute(Constant.ATTR_NAME_ERROR_MESSAGE,Constant.MSG_ERROE_MEMBER);
            //return "member-register";
            return ResultEntity.failed(Constant.MSG_ERROE_MEMBER_TO);
        }

        System.out.println(memberVo);

        //获取输入的密码
        String userpswd = memberVo.getUserpswd();
        if (!CrowdUtils.stringEffectCheck(userpswd)){
            //设置错误信息
            model.addAttribute(Constant.ATTR_NAME_ERROR_MESSAGE,Constant.MSG_ERROE_MEMBER);

            //返回注册页面
            //return "member-register";
            return ResultEntity.failed(Constant.MSG_ERROE_MEMBER_TO);
        }

        //密码加密
        userpswd = CrowdUtils.md5(userpswd);

        //把加密后的密码重新设置回去
        memberVo.setUserpswd(userpswd);

        System.out.println(memberVo);

        //执行注册
        ResultEntity<String> stringResultEntity = authoService.doRegister(memberVo);

        if (ResultEntity.FAILED.equals(stringResultEntity.getResult())){

            //设置错误信息
            model.addAttribute(Constant.ATTR_NAME_ERROR_MESSAGE,stringResultEntity.getMessage());

            //返回注册页面
          //  return "member-register";
            return ResultEntity.failed(stringResultEntity.getMessage());
        }
        //跳转到登录页面
        return ResultEntity.successWithoutData();
    }























}
