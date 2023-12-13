package org.service.loanservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @Author Prakash Ponali (@pponali)
 * @Date 12/12/23
 * @Description
 */



@Schema(description = "Loans Contact Info Dto", name = "LoansContactInfoDto")
@ConfigurationProperties(prefix = "loans")
public record LoansContactInfoDto(String message, Map<String, String> contactDetails, List<String> onCallSupport) {
}
