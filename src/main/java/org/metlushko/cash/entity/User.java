package org.metlushko.cash.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    private String userId;
    private String firstName;
    private String lastName;
    private String surName;
    private String email;
    private String phoneNumber;
}


