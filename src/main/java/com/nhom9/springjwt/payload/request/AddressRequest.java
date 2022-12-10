package com.nhom9.springjwt.payload.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AddressRequest {
    @NotNull
    private Long ward_id;

    @NotNull
    private Long user_id;

    @NotBlank
    private String address;

    @NotBlank
    private String phone;

    public Long getWard_id() {
        return ward_id;
    }

    public void setWard_id(Long ward_id) {
        this.ward_id = ward_id;
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

    public AddressRequest(@NotNull Long ward_id, @NotBlank String address, @NotBlank String phone,
            @NotNull Long user_id) {
        this.ward_id = ward_id;
        this.address = address;
        this.phone = phone;
        this.user_id = user_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

}
