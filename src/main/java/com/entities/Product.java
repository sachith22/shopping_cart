package com.entities;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
public class Product implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ApiModelProperty(notes = "The product code")
    @Column(name = "code", unique = true)
    private String code;

    @ApiModelProperty(notes = "The product title")
    @Column(name = "title")
    private String title;

    @ApiModelProperty(notes = "The product name")
    @Column(name = "name")
    private String name;

//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "category_id", nullable = false)
//    Category category;

    @ApiModelProperty(notes = "The product price")
    @Column(name = "price")
    private Double price;

    @ApiModelProperty(notes = "The tax for product")
    @Column(name = "tax")
    private Double tax;

    @ApiModelProperty(notes = "The product quantity", value = "0")
    @Column(name = "quantity")
    private Integer quantity;

    @ApiModelProperty(notes = "The product image")
    @Column(name = "image_url")
    private String imageUrl;

}
