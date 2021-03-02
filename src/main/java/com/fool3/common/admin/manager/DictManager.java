package com.fool3.common.admin.manager;

import com.fool3.common.admin.common.IConstants;
import com.fool3.common.admin.entity.AttrValue;
import com.fool3.common.admin.service.IAttrSpecService;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Component
public class DictManager {
    @Autowired
    private IAttrSpecService attrSpecService;

    private final Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().expireAfterWrite(3, TimeUnit.MINUTES).build();

    public String format(IConstants.AttrSpecCode attrSpecCode, String valueCode) {
        if (attrSpecCode == null || valueCode == null) {
            return null;
        }
        String specCode = attrSpecCode.name();
        Map<String, String> valueMap = cache.getIfPresent(specCode);
        if (valueMap == null) {
            List<AttrValue> values = attrSpecService.selectBySpecCode(specCode);
            valueMap = Optional.ofNullable(values).map(v -> v.stream().collect(Collectors.toMap(AttrValue::getAttrValue, AttrValue::getAttrName))).orElse(Maps.newHashMap());
            cache.put(specCode, valueMap);
        }
        if (valueMap.containsKey(valueCode)) {
            return valueMap.get(valueCode);
        }
        return valueCode;
    }
}
