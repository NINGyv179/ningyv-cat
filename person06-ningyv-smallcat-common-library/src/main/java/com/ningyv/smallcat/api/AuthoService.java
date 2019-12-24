package com.ningyv.smallcat.api;

import com.ningyv.smallcat.entity.vo.MemberLoginVo;
import com.ningyv.smallcat.entity.vo.MemberSuccessVo;
import com.ningyv.smallcat.entity.vo.MemberVo;
import com.ningyv.smallcat.resultEntity.ResultEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author LCX
 * @create 2019-12-23 21:05
 */
@FeignClient("member-authenticati")
public interface AuthoService {

    @RequestMapping("/member/do/login")
    public ResultEntity<MemberSuccessVo> doLogin(@RequestBody MemberLoginVo memberLoginVo);
}
