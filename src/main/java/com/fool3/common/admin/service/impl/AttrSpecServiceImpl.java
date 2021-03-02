package com.fool3.common.admin.service.impl;

import com.fool3.common.admin.dao.IAttrSpecDao;
import com.fool3.common.admin.entity.AttrValue;
import com.fool3.common.admin.service.IAttrSpecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttrSpecServiceImpl implements IAttrSpecService {
    @Autowired
    private IAttrSpecDao attrSpecDao;

    @Override
    public List<AttrValue> selectBySpecCode(String specCode) {
        return attrSpecDao.selectBySpecCode(specCode);
    }
}
