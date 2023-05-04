package org.example.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.entity.base.BaseEntity;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
@NoArgsConstructor
@RequiredArgsConstructor
@Data
@ToString(callSuper = true)
public class Product extends BaseEntity {
    @Column(name = "title")
    @NonNull
    private String title;

    @Column(name = "description")
    @NonNull
    private String description;

    @Column(name = "price")
    @NonNull
    private double price;

    @Enumerated(EnumType.STRING)
    @Column(name = "product_type")
    @NonNull
    private ProductType productType;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "products_orders",
            joinColumns = {@JoinColumn(name = "products_id")},
            inverseJoinColumns = {@JoinColumn(name = "orders_id")})
    private List<Order> orders = new ArrayList<>();
}