package com.geca.trackingboss.model.register;

import android.support.annotation.Nullable;

import com.squareup.moshi.Json;

public class RegisterRequest {

    public static class Role {
        @Json(name = "id")
        private int id = 2;

        public Role() {
        }

        public Role(int id) {
            this.id = id;
        }
    }

    @Nullable
    @Json(name = "id")
    private Integer id;
    @Json(name = "username")
    private String username;
    @Json(name = "password")
    private String password;
    @Json(name = "confirmPassword")
    private String confirmPassword;
    @Json(name = "firstname")
    private String firstname;
    @Json(name = "lastname")
    private String lastname;
    @Json(name = "dni")
    private String dni;
    @Json(name = "phone")
    private String phone;
    @Json(name = "address")
    private String address;
    @Json(name = "role")
    private Role role;
    @Json(name = "type")
    private boolean type;

    public RegisterRequest() {
    }

    public RegisterRequest(Integer id, String username, String password, String confirmPassword, String firstname, String lastname, String dni, String phone, String address, boolean type) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.firstname = firstname;
        this.lastname = lastname;
        this.dni = dni;
        this.phone = phone;
        this.address = address;
        this.type = type;
        role = new Role();
    }

    @Nullable
    public Integer getId() {
        return id;
    }

    public RegisterRequest setId(@Nullable Integer id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public RegisterRequest setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public RegisterRequest setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public RegisterRequest setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }

    public String getFirstname() {
        return firstname;
    }

    public RegisterRequest setFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public String getLastname() {
        return lastname;
    }

    public RegisterRequest setLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public String getDni() {
        return dni;
    }

    public RegisterRequest setDni(String dni) {
        this.dni = dni;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public RegisterRequest setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public RegisterRequest setAddress(String address) {
        this.address = address;
        return this;
    }

    public Role getRole() {
        return role;
    }

    public RegisterRequest setRole(Role role) {
        this.role = role;
        return this;
    }

    public boolean isType() {
        return type;
    }

    public RegisterRequest setType(boolean type) {
        this.type = type;
        return this;
    }
}
