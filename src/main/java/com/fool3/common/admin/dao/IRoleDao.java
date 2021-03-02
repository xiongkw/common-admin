package com.fool3.common.admin.dao;

import com.fool3.common.admin.common.Order;
import com.fool3.common.admin.entity.Role;
import com.fool3.common.admin.mapper.RoleMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

public interface IRoleDao extends RoleMapper {
    Set<String> selectRoleSetByUserId(@Param("userId") Long userId);

    List<Role> selectByKeyword(@Param("keyword") String keyword, @Param("sort") String sort, @Param("order") Order order);

    Long save(Role role);

}
