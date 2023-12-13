package org.service.loanservice.controller;

/**
 * @Project loans-service
 * @PackageName org.service.loanservice.controller
 * @FileName LoansController.java
 *
 *
 * @Author Prakash Ponali (@pponali)
 * @Date 12/12/23
 * @Description
 */

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.service.loanservice.LoansConstants;
import org.service.loanservice.dto.ErrorResponseDto;
import org.service.loanservice.dto.LoansContactInfoDto;
import org.service.loanservice.dto.LoansDto;
import org.service.loanservice.dto.ResponseDto;
import org.service.loanservice.service.ILoansService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/**
 * @author Eazy Bytes
 */

@Tag(
        name = "CRUD REST APIs for Loans in EazyBank",
        description = "CRUD REST APIs in EazyBank to CREATE, UPDATE, FETCH AND DELETE loan details"
)
@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class LoansController {

    @Value("${build.version}")
    private String buildVersion;

    private final ILoansService iLoansService;

    private final LoansContactInfoDto loansContactInfoDto;

    private final Environment env;

    public LoansController(ILoansService iLoansService, LoansContactInfoDto loansContactInfoDto, Environment env){
        this.iLoansService = iLoansService;
        this.loansContactInfoDto = loansContactInfoDto;
        this.env = env;
    }



    @Operation(
            summary = "Create Loan REST API",
            description = "REST API to create new loan inside EazyBank"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createLoan(@RequestParam
                                                  @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
                                                  String mobileNumber) {
        iLoansService.createLoan(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(LoansConstants.STATUS_201, LoansConstants.MESSAGE_201));
    }

    @Operation(
            summary = "Fetch Loan Details REST API",
            description = "REST API to fetch loan details based on a mobile number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @GetMapping("/fetch")
    public ResponseEntity<LoansDto> fetchLoanDetails(@RequestParam
                                                     @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
                                                     String mobileNumber) {
        LoansDto loansDto = iLoansService.fetchLoan(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(loansDto);
    }

    @Operation(
            summary = "Update Loan Details REST API",
            description = "REST API to update loan details based on a loan number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateLoanDetails(@Valid @RequestBody LoansDto loansDto) {
        boolean isUpdated = iLoansService.updateLoan(loansDto);
        if(isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(LoansConstants.STATUS_200, LoansConstants.MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(LoansConstants.STATUS_417, LoansConstants.MESSAGE_417_UPDATE));
        }
    }

    @Operation(
            summary = "Delete Loan Details REST API",
            description = "REST API to delete Loan details based on a mobile number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteLoanDetails(@RequestParam
                                                         @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
                                                         String mobileNumber) {
        boolean isDeleted = iLoansService.deleteLoan(mobileNumber);
        if(isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(LoansConstants.STATUS_200, LoansConstants.MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(LoansConstants.STATUS_417, LoansConstants.MESSAGE_417_DELETE));
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
    public ResponseEntity<LoansContactInfoDto> getContactInfo() {
        return ResponseEntity.status(HttpStatus.OK).body(loansContactInfoDto);
    }

}
