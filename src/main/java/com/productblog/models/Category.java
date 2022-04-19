package com.productblog.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
    private Long id;
    private String name;
    @OneToMany(mappedBy = "category")
    private List<Product> products;
    private LocalDateTime created_at;
    private LocalDateTime modify_at;
}
