package com.fool3.common.admin.service;

import com.fool3.common.admin.common.Order;
import com.fool3.common.admin.entity.Role;
import com.github.pagehelper.PageInfo;

import java.util.Set;

public interface IRoleService {
    Set<String> selectRoleSetByUserId(Long userId);

    PageInfo<Role> queryPage(String keyword, int pageNum, int pageSize, String sort, Order order);

    Role getById(Long id);

    Long save(Role role);

    int update(Role role);

    int deleteById(Long id);
}
