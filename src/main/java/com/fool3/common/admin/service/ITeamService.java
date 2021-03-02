package com.fool3.common.admin.service;

import com.fool3.common.admin.common.Order;
import com.fool3.common.admin.entity.Team;
import com.github.pagehelper.PageInfo;

public interface ITeamService {

    PageInfo<Team> queryPage(String keyword, int pageNum, int pageSize, String sort, Order order);

    Team getById(Long id);

    Long save(Team team);

    int update(Team team);

    int deleteById(Long id);
}
