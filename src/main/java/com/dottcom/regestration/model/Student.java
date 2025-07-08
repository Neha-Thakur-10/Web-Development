package com.dottcom.regestration.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "First Name is required")
    private String firstName;

    private String middleName;

    @NotBlank(message = "Last Name is required")
    private String lastName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dob;

    @NotBlank(message = "Gender is required")
    private String gender;

    @NotBlank(message = "Education is required")
    private String education;

    @Pattern(regexp = "^[0-9]{10}$", message = "Contact must be a 10-digit number")
    private String contact;

    @NotBlank(message = "Address is required")
    private String address;

    @Size(min = 4, message = "Username must be at least 4 characters")
    @Pattern(regexp = "^[a-zA-Z0-9._-]{4,}$", message = "Username can only contain letters, numbers, dots, dashes, or underscores")
    private String username;

    @Size(min = 6, message = "Password must be at least 6 characters")
    @Pattern(
        regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+=-]).{6,}$",
        message = "Password must contain uppercase, number, and special character"
    )
    private String password;

    // Optional: getter for full name used in reports
    public String getFullName() {
        return firstName + " " + (middleName != null ? middleName + " " : "") + lastName;
    }
}
