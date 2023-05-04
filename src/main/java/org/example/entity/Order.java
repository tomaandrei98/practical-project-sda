package org.example.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.entity.base.BaseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@ToString(callSuper = true)
public class Order extends BaseEntity {
    @Column(name = "user_name")
    @NonNull
    private String userName;

    @Column(name = "delivery_address")
    @NonNull
    private String deliveryAddress;

    @Column(name = "order_date")
    @NonNull
    private LocalDate orderDate;

    @Column(name = "order_status")
    @Enumerated(EnumType.STRING)
    @NonNull
    private OrderStatus orderStatus;

    @ManyToMany(mappedBy = "orders")
    private List<Product> products = new ArrayList<>();
}