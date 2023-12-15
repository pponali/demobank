package org.service.accountservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.service.accountservice.constants.AccountsConstants;
import org.service.accountservice.dto.*;
import org.service.accountservice.service.IAccountsService;
import org.service.accountservice.service.ICustomerDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

/**
 * @Author Prakash Ponali (@pponali)
 * @Date 10/12/23
 * @Description
 */


@Tag(name = "Accounts", description = "Accounts API")
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600, allowCredentials = "false")
@Validated
@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE}, headers = "Accept=application/json")
public class AccountsController {

    @Value("${build.version}")
    private String buildVersion;


    @Autowired
    Environment env;
    private final ICustomerDetailsService customerDetailsService;

    @Autowired
    AccountsContactInfoDto accountsContactInfoDto;

    private final IAccountsService accountsService;

    public AccountsController(ICustomerDetailsService customerDetailsService, IAccountsService accountsService) {
        this.customerDetailsService = customerDetailsService;
        this.accountsService = accountsService;
    }

    @Operation(summary = "Create Account", description = "Create Account", tags = {"Accounts"})
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Response information after creating the account"),
            @ApiResponse(
                    responseCode = "500",
                    description = "If the create is failed",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            )
    })
    @ResponseStatus(HttpStatus.CREATED)
    @NotNull(message = "Customer details cannot be null")
    @Validated
    @PostMapping(value = "/create")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto) {
        accountsService.createAccount(customerDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountsConstants.STATUS_201, AccountsConstants.STATUS_MESSAGE));


    }

    @Operation(
            summary = "Fetch Account",
            description = "Fetch Account"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Response information after fetch the account"),
            @ApiResponse(
                    responseCode = "500",
                    description = "If the fetch is failed",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "If the user is not found for fetching the information",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            )
    })
    @GetMapping(value = "/fetch")
    public ResponseEntity<CustomerDto> fetchAccount(
            @Pattern(regexp = "(^$|[0-9]{10})",
                    message = "Mobile number should not contain the special chars.")
            String mobileNumber) {
        customerDetailsService.fetchCustomerDetails(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(accountsService.fetchAccount(mobileNumber));
    }

    @Operation(
            summary = "Update Account",
            description = "Update Account"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Response information after updating the account"),
            @ApiResponse(
                    responseCode = "500",
                    description = "If the update is failed",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "If the user is not found for the update",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            )
    })
    @PutMapping(value = "/update")
    public ResponseEntity<CustomerDto> update(@Valid @RequestBody CustomerDto customerDto) {
        boolean updated = accountsService.updateAccount(customerDto);
        if (updated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(customerDto);
        } else {
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .body(customerDto);
        }
    }


    @Operation(
            summary = "Delete Account",
            description = "Delete Account"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Response information after delete the account"),
            @ApiResponse(
                    responseCode = "500",
                    description = "If the delete is failed",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "If the user is not found for the deletion",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            )
    })
    @GetMapping(value = "/delete")
    public ResponseEntity<?> delete(
            @Pattern(regexp = "(^$|[0-9]{10})",
                    message = "Mobile number should not contain the special chars.")
            String mobileNumber) {
        boolean updated = accountsService.deleteAccount(mobileNumber);
        if (updated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Account has been deleted successfully");

        } else {
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .body(Optional.empty());
        }
    }

    @Operation(
            method = "GET",
            summary = "Get Build Info",
            description = "validation for property changes."
    )
    @GetMapping(value = "/build-info", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "success response"),
            @ApiResponse(responseCode = "500", description = "failure response")
    })
    public ResponseEntity<String> getBuildInfo() {
        return ResponseEntity.status(HttpStatus.OK).body(buildVersion);
    }

    @Operation(
            method = "GET",
            summary = "Get version Info",
            description = "validation for environment property changes."
    )
    @GetMapping(value = "/version-info", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "success response"),
            @ApiResponse(responseCode = "500", description = "failure response")
    })
    public ResponseEntity<ArrayList<String>> getJavaVersion() {
        ArrayList<String> list = new ArrayList<>();
        list.add(env.getProperty("JAVA_HOME"));
        list.add(env.getProperty("MAVEN_HOME"));
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @Operation(
            method = "GET",
            summary = "Get Contact Info",
            description = "validation for property changes."
    )
    @GetMapping(value = "/contact-info", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "success response"),
            @ApiResponse(responseCode = "500", description = "failure response")
    })
    public ResponseEntity<AccountsContactInfoDto> getContactInfo() {
        return ResponseEntity.status(HttpStatus.OK).body(accountsContactInfoDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "success response"),
            @ApiResponse(responseCode = "500", description = "failure response")
    })
    @GetMapping(value = "/fetchCustomerDetails", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerDetailsDto> fetchCustomerDetails(@RequestParam String mobileNumber){
        return ResponseEntity.status(HttpStatus.OK).body(customerDetailsService.fetchCustomerDetails(mobileNumber));

    }



}
