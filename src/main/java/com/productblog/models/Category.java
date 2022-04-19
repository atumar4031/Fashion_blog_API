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
@Entity
public class Category {
    @Id
    @SequenceGenerator(
            name = "category_sequence",
            sequenceName = "category_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator = "category_sequence"
    )
    private Long id;
    private String name;
    @OneToMany(mappedBy = "category")
    private List<Product> products;
    private LocalDateTime created_at;
    private LocalDateTime modify_at;
}
