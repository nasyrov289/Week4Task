package com.example.demo.controller;

import com.example.demo.model.OrderDetails;
import com.example.demo.repository.OrderDetailsRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/order/details"})
public class OrderDetailsController {

    private OrderDetailsRepository repository;

    OrderDetailsController(OrderDetailsRepository orderDetailsRepository) {
        this.repository = orderDetailsRepository;
    }

    // CRUD methods here
    @GetMapping
    public List findAll() {
        return repository.findAll();
    }

    @GetMapping(path = {"/{order_id}"})
    public ResponseEntity<OrderDetails> findById(@PathVariable long order_id) {
        return repository.findById(order_id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public OrderDetails create(@RequestBody OrderDetails orderDetails) {
        return repository.save(orderDetails);
    }

    @PutMapping(value = "/{order_id}")
    public ResponseEntity<OrderDetails> update(@PathVariable("order_id") long order_id,
                                               @RequestBody OrderDetails orderDetails) {
        return repository.findById(order_id)
                .map(record -> {
                    record.setProduct_id(orderDetails.getProduct_id());
                    record.setProduct_name(orderDetails.getProduct_name());
                    record.setUnit_cost(orderDetails.getUnit_cost());
                    record.setQuantity(orderDetails.getQuantity());
                    record.getTotal();
                    OrderDetails updated = repository.save(record);
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