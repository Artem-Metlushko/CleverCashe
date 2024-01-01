package org.metlushko.cash.util;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UuidWrapper {

    public String randomUUID() {
        return UUID.randomUUID().toString();
    }

}
