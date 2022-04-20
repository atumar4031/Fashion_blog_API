package com.productblog.dtos;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class PostDto {
    private Long id;
    private String title;
    private String description;
}
