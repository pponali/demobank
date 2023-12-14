package org.service.cardservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
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
@Getter
@Setter
public class CardsContactInfoDto {
    private String message;
    private Map<String, String> contactDetail;
    private List<String> onCallSupport;
}
