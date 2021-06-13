package com.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Map;

@Entity
@Table(name = "carts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cart implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty(notes = "The ordered user", required = true)
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ApiModelProperty(notes = "The ordered products")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Map<String, CartItem> cartItems;

    @ApiModelProperty(notes = "The total amount of product's price")
    @Column(name = "total_amount")
    private Double totalAmount;

    @ApiModelProperty(notes = "The total amount of VAT")
    @Column(name = "total_vat")
    private Double totalVAT;

    @ApiModelProperty(notes = "The shipment cost")
    @Column(name = "shipment_cost")
    private Double shipmentCost;

    @ApiModelProperty(notes = "The order status")
    @Column(name = "status")
    private Boolean status;
}
