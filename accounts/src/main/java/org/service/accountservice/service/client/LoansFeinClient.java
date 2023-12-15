package org.service.accountservice.service.client;

import org.service.accountservice.dto.LoansDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author Prakash Ponali (@pponali)
 * @Date 14/12/23
 * @Description
 */

@FeignClient("loans")
public interface LoansFeinClient {

    @GetMapping("/api/fetch")
    ResponseEntity<LoansDto> fetchLoanDetails(@RequestParam String mobileNumber);

}
