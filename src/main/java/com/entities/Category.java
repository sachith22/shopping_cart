package com.entities;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * For future use
 */
@Entity
@Table(name = "categories")
@Getter
@Setter
@NoArgsConstructor
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ApiModelProperty(notes = "The category name")
    @Column(name = "category_name")
    private String categoryName;

    @ApiModelProperty(notes = "The category description")
    @Column(name = "category_desc")
    private String description;

    @ApiModelProperty(notes = "The category image")
    @Column(name = "image_url")
    private String imageUrl;

//	@OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	Set<Product> products;
}
