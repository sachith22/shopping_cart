package com.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@ApiModel(description = "Class representing a Users in the application.")
@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    @ApiModelProperty(notes = "The database generated User ID")
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ApiModelProperty(notes = "The user first name")
    @Column(name = "first_name")
    private String firstName;

    @ApiModelProperty(notes = "The user last name")
    @Column(name = "last_name")
    private String lastName;

    @ApiModelProperty(notes = "The user-specific username", required = true)
    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @ApiModelProperty(notes = "The user password")
    @Column(name = "password")
    private String password;

    @ApiModelProperty(notes = "The user-specific email address")
    @Column(name = "email", unique = true, nullable = false)
    //@Email
    private String email;

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Address> address;


    public User(String firstName, String lastName, String username, String password, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
