package com.productblog.dtos;
import com.productblog.models.Category;
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
    private Category category;
}
