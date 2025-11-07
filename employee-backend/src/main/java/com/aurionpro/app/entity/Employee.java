package com.aurionpro.app.entity;

import jakarta.persistence.*; // Import everything from jakarta.persistence
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
@Table(name = "employee", uniqueConstraints = {
    @UniqueConstraint(name = "uk_employee_email", columnNames = "email"),
    @UniqueConstraint(name = "uk_employee_phone_number", columnNames = "phone_number")
})
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "first name is mandatory")
    @Size(min = 2, message = "first name should have at least 2 characters")
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotBlank(message = "Last name is mandatory")
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone_number") 
    private String phoneNumber;

    @Column(name = "is_deleted")
    private boolean isDeleted = false;
    
    @ManyToOne(fetch = FetchType.LAZY) 
    @JoinColumn(name = "role_id")
    private Role role;

}