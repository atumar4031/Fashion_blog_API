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
public class Product {
    @Id
    @SequenceGenerator(
            name = "product_sequence",
            sequenceName = "product_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator = "product_sequence"
    )
    private Long id;
    private String title;
    private String description;
    private Double price;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "category_id",
            nullable = false,
            referencedColumnName = "id"
    )
    private Category category;
    @OneToMany(mappedBy = "product")
    private List<Comment> comments;
    @OneToMany(mappedBy = "product")
    private  List<Like> like;
    private LocalDateTime created_at;
    private LocalDateTime modify_at;
}
