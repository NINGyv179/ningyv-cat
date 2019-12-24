package com.ningyv.smallcat.handler;

import com.ningyv.smallcat.constant.Constant;
import com.ningyv.smallcat.entity.po.MenberPo;
import com.ningyv.smallcat.entity.vo.MemberLoginVo;
import com.ningyv.smallcat.entity.vo.MemberVo;
import com.ningyv.smallcat.resultEntity.ResultEntity;
import com.ningyv.smallcat.service.MemberService;
import com.ningyv.smallcat.utils.CrowdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LCX
 * @create 2019-12-22 16:25
 */
@RestController
public class MemberSqlHandler {
    @Autowired
    private MemberService memberService;

    @RequestMapping("/get/member/loginacct/count")
    public ResultEntity<Integer> getLoginacctCount(@RequestParam("loginacct") String loginacct) {
        if (!CrowdUtils.stringEffectCheck(loginacct)) {
            return ResultEntity.failed(Constant.MSG_STRING_ERROR);
        }
        Integer count = memberService.getMemberLoginCount(loginacct);

        return ResultEntity.successWithData(count);
    }

    @RequestMapping("/mysql/remote/save/member")
    public ResultEntity<String> saveMember(@RequestBody  MemberVo memberVo) {
        memberService.saveMember(memberVo);
        return ResultEntity.successWithoutData();
    }

    //查询会员数量
    @RequestMapping("/select/member/from/form")
    ResultEntity<MenberPo> getMemberPOByFormVO(@RequestBody MemberLoginVo memberLoginVo){

        try {
            MenberPo menberPo = memberService.selectMemberPoByformVo(memberLoginVo);
            return ResultEntity.successWithData(menberPo);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultEntity.failed(e.getMessage());
        }
    }

}
