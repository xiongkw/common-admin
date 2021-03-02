package com.fool3.common.admin.mapper;

import com.fool3.common.admin.entity.TeamMember;

public interface TeamMemberMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TeamMember record);

    int insertSelective(TeamMember record);

    TeamMember selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TeamMember record);

    int updateByPrimaryKey(TeamMember record);
}