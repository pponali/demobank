package org.service.accounts.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.service.accounts.dto.CustomerDetailsDto;
import org.service.accounts.service.ICustomerDetailsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Author Prakash Ponali (@pponali)
 * @Date 14/12/23
 * @Description
 */

@Slf4j
@Tag(name = "Customer Details", description = "Customer details API")
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600, allowCredentials = "false")
@Validated
@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE}, headers = "Accept=application/json")
public class CustomerDetailsController {

    private final ICustomerDetailsService customerDetailsService;

    public CustomerDetailsController(ICustomerDetailsService customerDetailsService) {
        this.customerDetailsService = customerDetailsService;
    }

    @ResponseStatus(HttpStatus.OK)
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "success response"),
            @ApiResponse(responseCode = "500", description = "failure response")
    })
    @GetMapping(value = "/fetchCustomerDetails", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerDetailsDto> fetchCustomerDetails(@RequestHeader("X-Request-Trace-Id") String correlationId,  @RequestParam String mobileNumber){
        log.info("Correlation id in the accounts service {} ", correlationId);
        return ResponseEntity.status(HttpStatus.OK).body(customerDetailsService.fetchCustomerDetails(correlationId, mobileNumber));

    }



}
