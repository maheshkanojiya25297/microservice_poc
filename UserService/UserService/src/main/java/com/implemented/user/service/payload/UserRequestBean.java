package com.implemented.user.service.payload;

import com.implemented.user.service.entities.Rating;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRequestBean {

    private String userId;

    private String name;

    private String about;

    private String email;

    private List<Rating> ratings = new ArrayList<>();
}
