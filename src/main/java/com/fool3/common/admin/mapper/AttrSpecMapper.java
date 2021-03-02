package com.fool3.common.admin.mapper;

import com.fool3.common.admin.entity.AttrSpec;

public interface AttrSpecMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AttrSpec record);

    int insertSelective(AttrSpec record);

    AttrSpec selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AttrSpec record);

    int updateByPrimaryKey(AttrSpec record);
}