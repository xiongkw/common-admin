package com.fool3.common.admin.service.impl;

import com.fool3.common.admin.common.Order;
import com.fool3.common.admin.dao.IRoleDao;
import com.fool3.common.admin.entity.Role;
import com.fool3.common.admin.service.IRoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private IRoleDao roleDao;

    @Override
    public Set<String> selectRoleSetByUserId(Long userId) {
        return roleDao.selectRoleSetByUserId(userId);
    }

    @Override
    public PageInfo<Role> queryPage(String keyword, int pageNum, int pageSize, String sort, Order order) {
        PageHelper.startPage(pageNum, pageSize);
        List<Role> list = Optional.ofNullable(roleDao.selectByKeyword(keyword, sort, order)).orElse(Lists.newArrayList());
        return new PageInfo<>(list);
    }

    @Override
    public Role getById(Long id) {
        return roleDao.selectByPrimaryKey(id);
    }

    @Override
    public Long save(Role role) {
        return roleDao.save(role);
    }

    @Override
    public int update(Role role) {
        return roleDao.updateByPrimaryKeySelective(role);
    }

    @Override
    public int deleteById(Long id) {
        return roleDao.deleteByPrimaryKey(id);
    }
}
