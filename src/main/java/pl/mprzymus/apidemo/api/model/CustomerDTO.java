package pl.mprzymus.apidemo.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CustomerDTO {
    @ApiModelProperty(value = "First name of customer", required = true)
    private String firstName;
    @ApiModelProperty(value = "Last name of customer", required = true)
    private String lastName;
    private String customerUrl;
}
