package com.example.demo.controller;

import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/customer"})
public class CustomerController {

    private CustomerRepository repository;

    CustomerController(CustomerRepository customerRepository) {
        this.repository = customerRepository;
    }

    // CRUD methods here
    @GetMapping
    public List findAll() {
        return repository.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<Customer> findById(@PathVariable long id) {
        return repository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Customer create(@RequestBody Customer customer) {
        return repository.save(customer);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Customer> update(@PathVariable("id") long id,
                                           @RequestBody Customer customer) {
        return repository.findById(id)
                .map(record -> {
                    record.setCustomer_name(customer.getCustomer_name());
                    record.setEmail(customer.getEmail());
                    record.setPassword(customer.getPassword());
                    record.setAccount_balance(customer.getAccount_balance());
                    record.setAddress(customer.getAddress());
                    Customer updated = repository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity<?> delete(@PathVariable("id") long id) {
        return repository.findById(id)
                .map(record -> {
                    repository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }

}