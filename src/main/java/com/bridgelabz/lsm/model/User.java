package com.bridgelabz.lsm.model;

import com.bridgelabz.lsm.dto.UserDTO;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;


@Data
@Entity
@Table(name = "UserDetails")
public class User {

    @Id
    @GeneratedValue
    private Integer userId;
    private String firstName;
    private String lastName;
    private String mobileNumber;
    private String email;
    private String password;
    private String dateOfBirth;
    private LocalDate date = LocalDate.now();

    public User(UserDTO dto) {
        super();
        this.firstName = dto.getFirstName();
        this.lastName = dto.getLastName();
        this.mobileNumber = dto.getMobileNumber();
        this.email = dto.getEmail();
        this.password = dto.getPassword();
        this.dateOfBirth = dto.getDateOfBirth();
    }

    public User() {

    }
}
