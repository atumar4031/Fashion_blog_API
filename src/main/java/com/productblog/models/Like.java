package com.productblog.models;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter
@Builder
@Entity(name="likes")
public class Like {
    @Id
    @SequenceGenerator(
            name = "like_sequence",
            sequenceName = "like_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "like_sequence"
    )
    private Long id;
    private Long likes;
    private Long dislikes;

    @ManyToOne
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "post_id",
            referencedColumnName = "id")
    private Post post;

    private LocalDateTime created_at;
    private LocalDateTime modify_at;
}
