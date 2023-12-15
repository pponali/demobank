package org.service.accountservice.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.service.accountservice.dto.CustomerDetailsDto;
import org.service.accountservice.service.ICustomerDetailsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Author Prakash Ponali (@pponali)
 * @Date 14/12/23
 * @Description
 */
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



}
