package com.productblog.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name= "likes")
public class Likes {
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
    private Customer customer;
    @ManyToOne
    @JoinColumn(
            name = "product_id",
            referencedColumnName = "id")
    private Product product;
}
