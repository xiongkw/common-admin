package com.fool3.common.admin.dao;

import com.fool3.common.admin.common.Order;
import com.fool3.common.admin.entity.Team;
import com.fool3.common.admin.mapper.TeamMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ITeamDao extends TeamMapper {

    List<Team> selectByKeyword(@Param("keyword") String keyword, @Param("sort") String sort, @Param("order") Order order);

    Long save(Team team);

}
