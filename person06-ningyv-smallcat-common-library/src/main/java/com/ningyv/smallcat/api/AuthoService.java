package com.ningyv.smallcat.api;

import com.ningyv.smallcat.entity.vo.MemberLoginVo;
import com.ningyv.smallcat.entity.vo.MemberSuccessVo;
import com.ningyv.smallcat.entity.vo.MemberVo;
import com.ningyv.smallcat.resultEntity.ResultEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author LCX
 * @create 2019-12-23 21:05
 */
@FeignClient("member-authenticati")
public interface AuthoService {

    //登录
    @RequestMapping("/member/do/login")
    public ResultEntity<MemberSuccessVo> doLogin(@RequestBody MemberLoginVo memberLoginVo);

    //发短信
    @RequestMapping("/send/message/to/redis")
    public ResultEntity<String> sendMessage(@RequestParam("phoneNum") String phoneNum);

    //注册
    @RequestMapping("/do/member/to/register")  //传入一个VO对象   注册有账号 密码 手机号 验证码
    public ResultEntity<String> doRegister(@RequestBody MemberVo memberVo);
}
