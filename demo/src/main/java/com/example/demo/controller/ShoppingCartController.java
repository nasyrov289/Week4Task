package com.example.demo.controller;

import com.example.demo.model.ShoppingCart;
import com.example.demo.repository.ShoppingCartRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/cart"})
public class ShoppingCartController {

    private ShoppingCartRepository repository;

    ShoppingCartController(ShoppingCartRepository shoppingCartRepository) {
        this.repository = shoppingCartRepository;
    }

    // CRUD methods here
    @GetMapping
    public List findAll() {
        return repository.findAll();
    }

    @GetMapping(path = {"/{cart_id}"})
    public ResponseEntity<ShoppingCart> findById(@PathVariable long cart_id) {
        return repository.findById(cart_id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ShoppingCart create(@RequestBody ShoppingCart shoppingCart) {
        return repository.save(shoppingCart);
    }

    @PutMapping(value = "/{cart_id}")
    public ResponseEntity<ShoppingCart> update(@PathVariable("cart_id") long cart_id,
                                               @RequestBody ShoppingCart shoppingCart) {
        return repository.findById(cart_id)
                .map(record -> {
                    record.setProduct_id(shoppingCart.getProduct_id());
                    record.setDate_added(shoppingCart.getDate_added());
                    record.setQuantity(shoppingCart.getQuantity());
                    ShoppingCart updated = repository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = {"/{cart_id}"})
    public ResponseEntity<?> delete(@PathVariable("cart_id") long cart_id) {
        return repository.findById(cart_id)
                .map(record -> {
                    repository.deleteById(cart_id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }

}