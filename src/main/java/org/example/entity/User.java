package org.example.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.entity.base.BaseEntity;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users",
        uniqueConstraints = @UniqueConstraint(columnNames = "login"))
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@ToString(callSuper = true, exclude = "orders")
public class User extends BaseEntity {
    @Column(name = "login")
    @NonNull
    private String login;

    @Column(name = "password")
    @NonNull
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    @NonNull
    private Role role;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    @NonNull
    private Address address;

    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "client_id")
    private List<Order> orders = new ArrayList<>();
}