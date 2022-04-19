package com.productblog.dtos;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class ProductDto {
    private Long id;
    private String title;
    private String description;
    private Double price;
    private Integer likes;
    private LocalDateTime created_at;
    private LocalDateTime modify_at;
}
