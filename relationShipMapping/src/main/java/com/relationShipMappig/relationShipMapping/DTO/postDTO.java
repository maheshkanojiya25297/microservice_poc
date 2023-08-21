package com.relationShipMappig.relationShipMapping.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class postDTO {

    private String postTitle;
    private String postDescription;
    private List<CommentDTO> comments;
}
