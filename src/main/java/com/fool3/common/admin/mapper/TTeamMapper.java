package com.fool3.common.admin.mapper;

import com.fool3.common.admin.entity.TTeam;

public interface TTeamMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TTeam record);

    int insertSelective(TTeam record);

    TTeam selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TTeam record);

    int updateByPrimaryKey(TTeam record);
}