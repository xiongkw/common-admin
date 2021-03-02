package com.fool3.common.admin.service.impl;

import com.fool3.common.admin.common.Order;
import com.fool3.common.admin.dao.ITeamDao;
import com.fool3.common.admin.entity.Team;
import com.fool3.common.admin.service.ITeamService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamService implements ITeamService {
    @Autowired
    private ITeamDao teamDao;

    @Override
    public PageInfo<Team> queryPage(String keyword, int pageNum, int pageSize, String sort, Order order) {
        PageHelper.startPage(pageNum, pageSize);
        List<Team> list = Optional.ofNullable(teamDao.selectByKeyword(keyword, sort, order)).orElse(Lists.newArrayList());
        return new PageInfo<>(list);
    }

    @Override
    public Team getById(Long id) {
        return teamDao.selectByPrimaryKey(id);
    }

    @Override
    public Long save(Team team) {
        return teamDao.save(team);
    }

    @Override
    public int update(Team team) {
        return teamDao.updateByPrimaryKey(team);
    }

    @Override
    public int deleteById(Long id) {
        return teamDao.deleteByPrimaryKey(id);
    }
}
