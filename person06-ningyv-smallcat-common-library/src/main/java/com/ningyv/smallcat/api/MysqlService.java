package com.ningyv.smallcat.api;

import com.ningyv.smallcat.entity.po.MenberPo;
import com.ningyv.smallcat.entity.vo.MemberLoginVo;
import com.ningyv.smallcat.entity.vo.MemberVo;
import com.ningyv.smallcat.resultEntity.ResultEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author LCX
 * @create 2019-12-22 16:55
 */
@FeignClient("database-provider")
public interface MysqlService {

    //查询登录数量
    @RequestMapping("/get/member/loginacct/count")
    public ResultEntity<Integer> getLoginacctCount(@RequestParam("loginacct") String loginacct);

    //保存会员
    @RequestMapping("/mysql/remote/save/member")
    public ResultEntity<String> saveMember(@RequestBody MemberVo memberVo);

    //查询会员数量
    @RequestMapping("/select/member/from/form")
    ResultEntity<MenberPo> getMemberPOByFormVO(@RequestBody MemberLoginVo memberLoginVo);

    //查询手机号是否被占用
    @RequestMapping("/select/phoneNum/count")
    ResultEntity<Integer> selectPhoneNum(@RequestParam("phoneNum") String phoneNum);
}
