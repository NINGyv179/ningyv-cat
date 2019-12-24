package com.ningyv.smallcat.service.impl;

import com.ningyv.smallcat.entity.po.MenberPo;
import com.ningyv.smallcat.entity.po.MenberPoExample;
import com.ningyv.smallcat.entity.vo.MemberLoginVo;
import com.ningyv.smallcat.entity.vo.MemberVo;
import com.ningyv.smallcat.mapper.MenberPoMapper;
import com.ningyv.smallcat.service.MemberService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author LCX
 * @create 2019-12-22 16:24
 */
@Service
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MenberPoMapper menberPoMapper;

    @Override
    public Integer getMemberLoginCount(String loginacct) {

        //创建example对象
        MenberPoExample example = new MenberPoExample();
        //创建criteria对象封装查询条件
        example.createCriteria().andLoginacctEqualTo(loginacct);
        //执行查询
        return menberPoMapper.countByExample(example);
    }

    @Override
    @Transactional(readOnly = false,propagation = Propagation.REQUIRES_NEW,rollbackFor = Exception.class)
    public void saveMember(MemberVo memberVo) {

        //将vo对象转换成PO对象
        MenberPo menberPo = new MenberPo();

        BeanUtils.copyProperties(memberVo,menberPo);
        //执行保存
        menberPoMapper.insertSelective(menberPo);
    }

    @Override
    public MenberPo selectMemberPoByformVo(MemberLoginVo memberLoginVo) {
        //创建查询化对象
        MenberPoExample menberPoExample = new MenberPoExample();

        MenberPoExample.Criteria criteria = menberPoExample.createCriteria();
        //拼接
        criteria.andLoginacctEqualTo(memberLoginVo.getUsername());

        criteria.andUserpswdEqualTo(memberLoginVo.getUserpswd());
        //执行查询返回结果
        List<MenberPo> menberPos = menberPoMapper.selectByExample(menberPoExample);

        if (menberPos == null || menberPos.size()==0){
            return  null;
        }

        return  menberPos.get(0);
    }

}
