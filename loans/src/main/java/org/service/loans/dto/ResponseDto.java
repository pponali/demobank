package org.service.loans.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;

/**
 * @Author Prakash Ponali (@pponali)
 * @Date 10/12/23
 * @Description
 *
 * <a href="https://martinfowler.com/eaaCatalog/dataTransferObject.html">DTO Pattern</a>
 */


@Schema(name = "Common Response", description = "Common Response Parameters")
@Data
@AllArgsConstructor
@Builder(toBuilder = true)
@EqualsAndHashCode(callSuper = false, of = {"status", "message"})
@ToString(callSuper = false, of = {"status", "message"})
public class ResponseDto implements Serializable{
    @Schema(name = "status", description = "description", example = "OK")
    String status;
    @Schema(name = "message", description = "description", example = "status message")
    String message;

}
