package com.furkanguzel.client.contractdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {

    private String id;

    private String username;

    private String name;

    private String surname;

    private String email;

    private String birthDate;

    public String getNameAndSurname() {
        return this.name + " " + this.surname;
    }

}