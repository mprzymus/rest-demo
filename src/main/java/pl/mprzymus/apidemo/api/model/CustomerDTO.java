package pl.mprzymus.apidemo.api.model;

import lombok.Data;

@Data
public class CustomerDTO {
    private String firstName;
    private String lastName;
    private String customerUrl;
}
