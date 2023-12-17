package org.service.loans.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @Author Prakash Ponali (@pponali)
 * @Date 12/12/23
 * @Description
 */



@Schema(description = "Loans Contact Info Dto", name = "Loans Contact Info Dto ")
@ConfigurationProperties(prefix = "loans")
@Getter
@Setter
public class LoansContactInfoDto {
    private String message;
    private Map<String, String> contactDetail;
    private List<String> onCallSupport;
}
