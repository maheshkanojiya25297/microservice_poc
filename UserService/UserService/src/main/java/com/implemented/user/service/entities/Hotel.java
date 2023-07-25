package com.implemented.user.service.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Hotel {

    public String id;
    public String name;
    public String location;
    public String about;
}
