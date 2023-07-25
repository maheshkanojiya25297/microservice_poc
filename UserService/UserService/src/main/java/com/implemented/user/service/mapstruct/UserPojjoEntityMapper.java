package com.implemented.user.service.mapstruct;
import com.implemented.user.service.entities.User;
import com.implemented.user.service.payload.UserRequestBean;
/*
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
*/

import java.util.function.Function;


//@Mapper(componentModel = "spring")
public interface UserPojjoEntityMapper {
/*

    UserPojjoEntityMapper INSTANCE = Mappers.getMapper(UserPojjoEntityMapper.class);

    @Mappings({
            @Mapping(target = "userId", source = "userRequestBean.userId"),
            @Mapping(target = "name", source = "userRequestBean.name"),
            @Mapping(target = "about", source = "userRequestBean.about"),
            @Mapping(target = "email", source = "userRequestBean.email"),
            @Mapping(target = "ratings", source = "userRequestBean.ratings")
    })
    User roleUserEntityPojo(UserRequestBean userRequestBean);


    @Mappings({
            @Mapping(target = "userId", source = "user.userId"),
            @Mapping(target = "name", source = "user.name"),
            @Mapping(target = "about", source = "user.about"),
            @Mapping(target = "email", source = "user.email"),
            @Mapping(target = "ratings", source = "user.ratings")
    })
    UserResponseBean ResponseRoleUserEntityPojo(User user);
*/

    public static Function<UserRequestBean, User> roleUserEntityPojo = userRequestBean -> User.builder()
            .userId(userRequestBean.getUserId())
            .name(userRequestBean.getName())
            .about(userRequestBean.getAbout())
            .email(userRequestBean.getEmail())
            .ratings(userRequestBean.getRatings())
            .build();
}
