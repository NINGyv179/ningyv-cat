package com.ningyv.smallcat.api;

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
}
