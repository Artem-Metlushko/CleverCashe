package util;


import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import org.metlushko.cash.dto.UserDto;
import org.metlushko.cash.dto.UserDtoBuilder;
import org.metlushko.cash.entity.User;

@Data
@Accessors(chain = true)
@Builder(setterPrefix = "with", toBuilder = true)
public class TestObjectUtil {

    @Builder.Default
    private String userId = "45c39ef0-2268-0000-aa93-a425be52eada";

    @Builder.Default
    private String firstName = "Kurt";

    @Builder.Default
    private String lastName = "Cobain";

    @Builder.Default
    private String surName = "Cobainvich";

    @Builder.Default
    private String email = "1584@mail.ru";

    @Builder.Default
    private String phoneNumber = "5554-12345";

    public User buildUser() {
        return User.builder()
                .userId(userId)
                .firstName(firstName)
                .lastName(lastName)
                .surName(surName)
                .email(email)
                .phoneNumber(phoneNumber)
                .build();
    }

    public UserDto buildUserDto(){
        return UserDtoBuilder.builder()
                .firstName(firstName)
                .lastName(lastName)
                .surName(surName)
                .email(email)
                .phoneNumber(phoneNumber)
                .build();

    }




}
