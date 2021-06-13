package com.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * For future use
 */
@Entity
@Table(name = "address")
@Getter
@Setter
@NoArgsConstructor
public class Address implements Serializable  {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "address_type")
    private AddressType addressType;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    public enum AddressType {
        SHIPPING_ADDRESS("user.shipping.address"),
        BILLING_ADDRESS("user.billing.address");

        private String addressType;

        AddressType(String addressType) {
            this.addressType = addressType;
        }

        public String getAddressType() {
            return addressType;
        }

        public void setAddressType(String addressType) {
            this.addressType = addressType;
        }
    }
}
