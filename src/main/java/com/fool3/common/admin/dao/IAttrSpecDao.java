package com.fool3.common.admin.dao;

import com.fool3.common.admin.entity.AttrValue;
import com.fool3.common.admin.mapper.AttrSpecMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IAttrSpecDao extends AttrSpecMapper {
    List<AttrValue> selectBySpecCode(@Param("specCode") String specCode);
}
