package com.fool3.common.admin.mapper;

import com.fool3.common.admin.entity.AttrValue;

public interface AttrValueMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AttrValue record);

    int insertSelective(AttrValue record);

    AttrValue selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AttrValue record);

    int updateByPrimaryKey(AttrValue record);
}