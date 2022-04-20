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
public class Like {
    @Id
    @SequenceGenerator(
            name = "like_sequence",
            sequenceName = "like_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator = "like_sequence"
    )
    private Long id;
    private Long likes;
    private Long dislikes;
    @OneToMany
    @JoinColumn(
            name = "customer_id",
            referencedColumnName = "id")
    private List<Customer> customer;
    @ManyToOne
    @JoinColumn(
            name = "product_id",
            referencedColumnName = "id")
    private Product product;
}
