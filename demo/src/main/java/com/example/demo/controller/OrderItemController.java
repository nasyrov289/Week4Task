package com.example.demo.controller;

import com.example.demo.model.OrderItem;
import com.example.demo.repository.OrderItemRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/order/item"})
public class OrderItemController {

    private OrderItemRepository repository;

    OrderItemController(OrderItemRepository orderItemRepository) {
        this.repository = orderItemRepository;
    }

    // CRUD methods here
    @GetMapping
    public List findAll() {
        return repository.findAll();
    }

    @GetMapping(path = {"/{order_id}"})
    public ResponseEntity<OrderItem> findById(@PathVariable long order_id) {
        return repository.findById(order_id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public OrderItem create(@RequestBody OrderItem orderItem) {
        return repository.save(orderItem);
    }

    @PutMapping(value = "/{order_id}")
    public ResponseEntity<OrderItem> update(@PathVariable("order_id") long order_id,
                                            @RequestBody OrderItem orderItem) {
        return repository.findById(order_id)
                .map(record -> {
                    record.setUser_id(orderItem.getUser_id());
                    record.setShipping_id(orderItem.getShipping_id());
                    record.setCustomer_name(orderItem.getCustomer_name());
                    record.setDate_created(orderItem.getDate_created());
                    record.setDate_shipped(orderItem.getDate_shipped());
                    OrderItem updated = repository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = {"/{order_id}"})
    public ResponseEntity<?> delete(@PathVariable("order_id") long order_id) {
        return repository.findById(order_id)
                .map(record -> {
                    repository.deleteById(order_id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }

}