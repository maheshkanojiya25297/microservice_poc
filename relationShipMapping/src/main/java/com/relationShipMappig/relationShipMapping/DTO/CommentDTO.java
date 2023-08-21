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
public class CommentDTO {

    private String commentText;
    private AuthorDTO commentByAuther;
}
