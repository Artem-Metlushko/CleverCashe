package org.metlushko.cash.dto;

import io.soabase.recordbuilder.core.RecordBuilder;

@RecordBuilder
public record UserDto(
        String firstName,
        String lastName,
        String surName,
        String email,
        String phoneNumber) {
}
