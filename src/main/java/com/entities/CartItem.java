package com.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "cart_items")
@Getter
@Setter
@NoArgsConstructor
public class CartItem {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ApiModelProperty(notes = "The product details")
    @ManyToOne()
    @JoinColumn(name = "product_id")
    private Product product;

    @ApiModelProperty(notes = "The order details")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "cart_id")
    @JsonBackReference
    private Cart cart;

    @ApiModelProperty(notes = "The product quantity", value = "0")
    @Column(name = "quantity")
    private Integer quantity;

}
