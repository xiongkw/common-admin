package com.fool3.common.admin.mapper;

import com.fool3.common.admin.dto.UserDTO;
import com.fool3.common.admin.entity.User;

public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    UserDTO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}