package com.fool3.common.admin.dao;

import com.fool3.common.admin.common.Order;
import com.fool3.common.admin.dto.UserDTO;
import com.fool3.common.admin.entity.User;
import com.fool3.common.admin.mapper.UserMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IUserDao extends UserMapper {

    User getByUsername(@Param("username") String username);

    Long save(User user);

    List<UserDTO> selectByKeyword(@Param("keyword") String keyword, @Param("sort") String sort, @Param("order") Order order);
}
