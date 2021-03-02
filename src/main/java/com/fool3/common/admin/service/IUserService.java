package com.fool3.common.admin.service;

import com.fool3.common.admin.common.Order;
import com.fool3.common.admin.dto.UserDTO;
import com.fool3.common.admin.entity.User;
import com.github.pagehelper.PageInfo;

public interface IUserService {
    User getByUsername(String username);

    PageInfo<UserDTO> queryPage(String keyword, int pageNum, int pageSize, String sort, Order order);

    UserDTO getById(Long id);

    Long save(User user);

    int update(User user);

    int deleteById(Long id);
}
