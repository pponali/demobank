package org.service.accounts.service.client;

import jakarta.validation.constraints.Pattern;
import org.service.accounts.dto.CardsDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author Prakash Ponali (@pponali)
 * @Date 14/12/23
 * @Description
 */

@FeignClient("cards")
public interface CardsFeinClient {

    @GetMapping(value= "/api/fetch", consumes = "application/json")
    ResponseEntity<CardsDto> fetchCardDetails(
            @RequestHeader("X-Request-Trace-Id") String correlationId,
            @RequestParam String mobileNumber);

}
