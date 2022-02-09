package com.example.deliverytestagain.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orders_id")
    private Long id;

    private String name;

    private int quantity;

    private int price;

    @ManyToOne
    @JoinColumn(name="order_info_id")
    private OrderInfo orderInfo;
}
