package com.dinh.todo.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Entity
@Table(name = "orders")
@Data
@FieldDefaults(level = PRIVATE)
@Builder
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    double totalPrice;

    @OneToMany(mappedBy = "order")
    List<OrderDetail>  orderDetails;

    // userid
    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    @NotEmpty
    @Column(name = "receiver_name")
    String receiverName;

    @NotEmpty
    @Column(name = "receiver_address")
    String receiverAddress;

    @NotEmpty
    @Column(name = "receiver_phone")
    String receiverPhone;

    private String status;

    public Order() {

    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", totalPrice=" + totalPrice +
                ", orderDetails=" + orderDetails +
                ", receiverName='" + receiverName + '\'' +
                ", receiverAddress='" + receiverAddress + '\'' +
                ", receiverPhone='" + receiverPhone + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
