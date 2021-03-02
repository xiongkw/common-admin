package com.fool3.common.admin.service.impl;

import com.fool3.common.admin.common.IConstants;
import com.fool3.common.admin.common.Order;
import com.fool3.common.admin.dao.IUserDao;
import com.fool3.common.admin.dto.UserDTO;
import com.fool3.common.admin.entity.User;
import com.fool3.common.admin.manager.DictManager;
import com.fool3.common.admin.service.IUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private DictManager dictManager;

    private final Consumer<UserDTO> formatConsumer = u -> u.setGenderName(dictManager.format(IConstants.AttrSpecCode.GENDER, u.getGender()));

    @Override
    public User getByUsername(String username) {
        return userDao.getByUsername(username);
    }

    @Override
    public PageInfo<UserDTO> queryPage(String keyword, int pageNum, int pageSize, String sort, Order order) {
        PageHelper.startPage(pageNum, pageSize);
        List<UserDTO> list = Optional.ofNullable(userDao.selectByKeyword(keyword, sort, order)).orElse(Lists.newArrayList());
        list.forEach(formatConsumer);
        return new PageInfo<>(list);
    }

    @Override
    public UserDTO getById(Long id) {
        UserDTO user = userDao.selectByPrimaryKey(id);
        Optional.ofNullable(user).ifPresent(formatConsumer);
        return user;
    }

    @Override
    public Long save(User user) {
        String password = user.getPassword();
        encodePasswordOrNull(user, password);
        return userDao.save(user);
    }

    @Override
    public int update(User user) {
        String password = user.getPassword();
        encodePasswordOrNull(user, password);
        return userDao.updateByPrimaryKeySelective(user);
    }

    private void encodePasswordOrNull(User user, String password) {
        if (StringUtils.isNotEmpty(password)) {
            user.setPassword(passwordEncoder.encode(password));
        } else {
            user.setPassword(null);
        }
    }

    @Override
    public int deleteById(Long id) {
        User user = new User();
        user.setId(id);
        user.setIsDeleted(true);
        return userDao.updateByPrimaryKeySelective(user);
    }
}
