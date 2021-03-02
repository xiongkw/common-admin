package com.fool3.common.admin.service;

import com.fool3.common.admin.entity.AttrValue;

import java.util.List;

public interface IAttrSpecService {
    List<AttrValue> selectBySpecCode(String specCode);
}
