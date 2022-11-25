package com.nhom9.springjwt.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String address;

    private String phone;

    @JoinColumn(name = "ward_id")
    @ManyToOne
    private Ward ward;

    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "address", orphanRemoval = true)
    private List<Invoice> invoices = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Ward getWard() {
        return ward;
    }

    public void setWard(Ward ward) {
        this.ward = ward;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Address() {
    }

    public Address(String address, String phone, Ward ward, User user) {
        this.address = address;
        this.phone = phone;
        this.ward = ward;
        this.user = user;
    }

}
