package com.fool3.common.admin.dto;

import com.fool3.common.admin.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO extends User {
    private String genderName;
}
