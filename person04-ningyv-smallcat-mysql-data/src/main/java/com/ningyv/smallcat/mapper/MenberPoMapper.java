package com.ningyv.smallcat.mapper;

import com.ningyv.smallcat.entity.po.MenberPo;
import com.ningyv.smallcat.entity.po.MenberPoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MenberPoMapper {
    int countByExample(MenberPoExample example);

    int deleteByExample(MenberPoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MenberPo record);

    int insertSelective(MenberPo record);

    List<MenberPo> selectByExample(MenberPoExample example);

    MenberPo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MenberPo record, @Param("example") MenberPoExample example);

    int updateByExample(@Param("record") MenberPo record, @Param("example") MenberPoExample example);

    int updateByPrimaryKeySelective(MenberPo record);

    int updateByPrimaryKey(MenberPo record);
}