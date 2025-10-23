package com.dinh.todo.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import lombok.experimental.PackagePrivate;

import java.util.ArrayList;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Entity
@Table(name = "carts")
@Data
@FieldDefaults(level = PRIVATE)
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Min(value = 0)
    int sum;

    @OneToOne
    @JoinColumn(name = "user_id")
    User user;

    @OneToMany(mappedBy = "cart")
    List<CartDetail> cartDetails = new ArrayList<>();

    public double getSubTotal() {
        return this.cartDetails.stream()
                .mapToDouble(detail -> detail.getPrice() * detail.getQuantity()).sum();
    }

    public double getShippingFee() {
        return 0;
    }

    public double getTotal() {
        return getSubTotal() + getShippingFee();
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", sum=" + sum +
                ", cartDetails=" + cartDetails +
                '}';
    }
}
