package org.metlushko.cash.dto;

import org.metlushko.cash.validation.EmailConstraint;

public record UserDto(
        String firstName,
        String lastName,
        String surName,
        @EmailConstraint String email,
        String phoneNumber) {


}
