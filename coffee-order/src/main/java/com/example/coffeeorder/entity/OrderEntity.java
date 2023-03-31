package com.example.coffeeorder.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "coffeeorder")
public class OrderEntity {
    @Id
    @GeneratedValue
    private Integer id;
    @Column
    private String orderNumber;
    @Column
    private String coffeeName;
    @Column
    private int coffeeCount;
    @Column
    private String customerName;
}
