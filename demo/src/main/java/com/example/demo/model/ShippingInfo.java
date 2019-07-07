package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class ShippingInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shipping_id;

    private String shipping_info;
    private long shipping_cost;
    private String shipping_region;

    public Long getShipping_id() {
        return shipping_id;
    }

    public void setShipping_id(Long shipping_id) {
        this.shipping_id = shipping_id;
    }

    public String getShipping_info() {
        return shipping_info;
    }

    public void setShipping_info(String shipping_info) {
        this.shipping_info = shipping_info;
    }

    public long getShipping_cost() {
        return shipping_cost;
    }

    public void setShipping_cost(long shipping_cost) {
        this.shipping_cost = shipping_cost;
    }

    public String getShipping_region() {
        return shipping_region;
    }

    public void setShipping_region(String shipping_region) {
        this.shipping_region = shipping_region;
    }
}
