package com.maids.librarymanagement.entity;

import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Patron extends BaseEntity{

    private String name;
    private String phoneNumber;
    private String email;

}
