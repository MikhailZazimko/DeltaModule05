package com.javarush.spring13and14.model.mapper;

import com.javarush.spring13and14.model.dto.UserDto;
import com.javarush.spring13and14.model.dto.UserFormData;
import com.javarush.spring13and14.model.entity.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {
    User from(UserFormData userFormData);
    User from(UserDto userDto);
    UserDto dto(User user);
}
