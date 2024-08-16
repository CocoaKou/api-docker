package ai.wbw.service.core.dto;

import lombok.Data;

/**
 * @Description Login Result DTO @Author Cocoa @Date 2024/8/15 @Version 0.1
 */
@Data
public class LoginResultDto<T> {

  private String accessToken;

  private T result;
}
