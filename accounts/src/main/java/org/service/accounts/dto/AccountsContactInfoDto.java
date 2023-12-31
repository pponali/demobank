package org.service.accounts.dto;

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

@Schema(name = "Accounts Contact Info Dto" , description = "Accounts Contact Info Dto" )
@ConfigurationProperties(prefix = "accounts")
@Getter
@Setter
public class AccountsContactInfoDto {
    private String message;
    private Map<String, String> contactDetail;
    private List<String> onCallSupport;
}
