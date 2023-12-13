package org.service.cardservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

/**
 * @Author Prakash Ponali (@pponali)
 * @Date 12/12/23
 * @Description
 */

@Schema(description = "Loans Contact Info Dto", name = "LoansContactInfoDto", required = false)
@ConfigurationProperties(prefix = "cards")
public record CardsContactInfoDto(String message, Map<String, String> contactDetail, List<String> onCallSupport) {
}
