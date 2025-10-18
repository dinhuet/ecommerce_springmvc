package com.dinh.todo.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Entity
@Table(name = "products")
@Data
@FieldDefaults(level = PRIVATE)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Tên không dc để trống")
    private String name;

    @Positive(message = "Price phải lớn hơn 0")
    private double price;

    private String image;

    @NotEmpty(message = "detailDesc không dc để trống")
    @Column(columnDefinition = "MEDIUMTEXT")
    private String detailDesc;

    @NotEmpty(message = "shortDesc không dc để trống")
    private String shortDesc;

    @Min(value = 1, message = "Số lượng phải lớn hơn 1 hoặc bằng 1")
    private Long quantity;
    private Long sold;
    private String factory;


    @OneToMany(mappedBy = "product")
    private List<OrderDetail> orderDetails;

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", image='" + image + '\'' +
                ", detailDesc='" + detailDesc + '\'' +
                ", shortDesc='" + shortDesc + '\'' +
                ", quantity=" + quantity +
                ", sold=" + sold +
                ", factory='" + factory + '\'' +
                ", target='" + target + '\'' +
                '}';
    }

    private String target;


}
