package com.lcwd.user.service.mapstruct;

import com.lcwd.user.service.entities.Rating;
import com.lcwd.user.service.entities.User;
import com.lcwd.user.service.payload.UserRequestBean;
import com.lcwd.user.service.payload.UserResponseBean;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-24T16:11:53+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 19 (Oracle Corporation)"
)
@Component
public class UserPojjoEntityMapperImpl implements UserPojjoEntityMapper {

    @Override
    public User roleUserEntityPojo(UserRequestBean userRequestBean) {
        if ( userRequestBean == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.userId( userRequestBean.getUserId() );
        user.name( userRequestBean.getName() );
        user.about( userRequestBean.getAbout() );
        user.email( userRequestBean.getEmail() );
        List<Rating> list = userRequestBean.getRatings();
        if ( list != null ) {
            user.ratings( new ArrayList<Rating>( list ) );
        }

        return user.build();
    }

    @Override
    public UserResponseBean ResponseRoleUserEntityPojo(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponseBean userResponseBean = new UserResponseBean();

        userResponseBean.setUserId( user.getUserId() );
        userResponseBean.setName( user.getName() );
        userResponseBean.setAbout( user.getAbout() );
        userResponseBean.setEmail( user.getEmail() );
        List<Rating> list = user.getRatings();
        if ( list != null ) {
            userResponseBean.setRatings( new ArrayList<Rating>( list ) );
        }

        return userResponseBean;
    }
}
