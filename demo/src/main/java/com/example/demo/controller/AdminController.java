package com.example.demo.controller;

import com.example.demo.model.Admin;
import com.example.demo.repository.AdminRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/admin"})
public class AdminController {

    private AdminRepository repository;

    AdminController(AdminRepository adminRepository) {
        this.repository = adminRepository;
    }

    // CRUD methods here
    @GetMapping
    public List findAll() {
        return repository.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<Admin> findById(@PathVariable long id) {
        return repository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Admin create(@RequestBody Admin admin) {
        return repository.save(admin);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Admin> update(@PathVariable("id") long id,
                                        @RequestBody Admin admin) {
        return repository.findById(id)
                .map(record -> {
                    record.setAdmin_name(admin.getAdmin_name());
                    record.setEmail(admin.getEmail());
                    record.setPassword(admin.getPassword());
                    Admin updated = repository.save(record);
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