package com.ningyv.smallcat.service;


import com.ningyv.smallcat.entity.vo.MemberVo;

/**
 * @author LCX
 * @create 2019-12-22 16:24
 */
public interface MemberService {

    Integer getMemberLoginCount(String loginacct);

    void saveMember(MemberVo memberVo);
}
