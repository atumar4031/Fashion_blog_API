package com.productblog.dtos;

import lombok.*;

import java.time.LocalDateTime;
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class LikesDto {
    private Long id;
    private Long likes;
    private Long dislikes;
    private LocalDateTime created_at;
    private LocalDateTime modify_at;
}
