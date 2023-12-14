package org.service.accountservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Random;

/**
 * @Author Prakash Ponali (@pponali)
 * @Date 10/12/23
 * @Description
 */


@Data @AllArgsConstructor @NoArgsConstructor
@Schema(name = "Accounts Response", description = "Accounts Response parameters")
public class AccountsDto implements Serializable {


    @Schema(name = "Branch Address", example = "Branch Address")
    @NotEmpty(message = "Branch Address should not be empty")
    @Size(min = 5, max = 30 , message = "Branch Address should be min of 5 and max of 30")
    String branchAddress;

    @Schema(name = "Account Number", example = "Account Number")
    @Size(min = 5, max = 30 , message = "Account Number should be min of 5 and max of 30")
    Long accountNumber;

    @Schema(name = "Account Type")
    @NotEmpty(message = "Account Type should not be empty")
    @Size(min = 5, max = 30 , message = "Account Type should be min of 5 and max of 30")
    String accountType;

}
