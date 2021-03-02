package com.fool3.common.admin.mapper;

import com.fool3.common.admin.entity.TTeamMember;

public interface TTeamMemberMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TTeamMember record);

    int insertSelective(TTeamMember record);

    TTeamMember selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TTeamMember record);

    int updateByPrimaryKey(TTeamMember record);
}