package com.example.demo.controller;

import com.example.demo.model.ShippingInfo;
import com.example.demo.repository.ShippingInfoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/shipping"})
public class ShippingInfoController {

    private ShippingInfoRepository repository;

    ShippingInfoController(ShippingInfoRepository shippingInfoRepository) {
        this.repository = shippingInfoRepository;
    }

    // CRUD methods here
    @GetMapping
    public List findAll() {
        return repository.findAll();
    }

    @GetMapping(path = {"/{shipping_id}"})
    public ResponseEntity<ShippingInfo> findById(@PathVariable long shipping_id) {
        return repository.findById(shipping_id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ShippingInfo create(@RequestBody ShippingInfo shippingInfo) {
        return repository.save(shippingInfo);
    }

    @PutMapping(value = "/{shipping_id}")
    public ResponseEntity<ShippingInfo> update(@PathVariable("shipping_id") long shipping_id,
                                               @RequestBody ShippingInfo shippingInfo) {
        return repository.findById(shipping_id)
                .map(record -> {
                    record.setShipping_info(shippingInfo.getShipping_info());
                    record.setShipping_cost(shippingInfo.getShipping_cost());
                    record.setShipping_region(shippingInfo.getShipping_region());
                    ShippingInfo updated = repository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = {"/{shipping_id}"})
    public ResponseEntity<?> delete(@PathVariable("shipping_id") long shipping_id) {
        return repository.findById(shipping_id)
                .map(record -> {
                    repository.deleteById(shipping_id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }

}