package com.teg.teggerli_back.domain.users;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name = "customers")
@AllArgsConstructor
@NoArgsConstructor
public class Customer extends User {

    @Column(length = 500)
    private String address;

}
