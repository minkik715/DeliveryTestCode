package com.example.deliverytestagain.domain;

import com.example.deliverytestagain.dto.FoodRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Food {
    @Id @GeneratedValue
    @Column(name="food_id")
    private Long id;
    private String name;
    private int price;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    Restaurant restaurant;

    public Food(FoodRequestDto foodRequestDto){
        this.name = foodRequestDto.getName();
        this.price = foodRequestDto.getPrice();
    }

    public Food() {

    }
}
