package com.productblog.models;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@Entity(name="category")

public class Category {
    @Id
    @SequenceGenerator(
            name = "category_sequence",
            sequenceName = "category_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "category_sequence"
    )
    private Long id;
    private String name;
    @OneToMany(mappedBy = "category")
    private List<Post> post;
    private LocalDateTime created_at;
    private LocalDateTime modify_at;

    public Category(String name, LocalDateTime created_at, LocalDateTime modify_at) {
        this.name = name;
        this.created_at = created_at;
        this.modify_at = modify_at;
    }
}
