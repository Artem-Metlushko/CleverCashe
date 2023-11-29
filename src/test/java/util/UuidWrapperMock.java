package util;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.metlushko.cash.util.UuidWrapper;

@NoArgsConstructor
@AllArgsConstructor
public class UuidWrapperMock extends UuidWrapper {

    private String UUID_DEFAULT;

    public static UuidWrapperMock getInstance(String UUID_DEFAULT) {
        return new UuidWrapperMock(UUID_DEFAULT);
    }

    @Override
    public String randomUUID() {
        return UUID_DEFAULT;
    }



}
