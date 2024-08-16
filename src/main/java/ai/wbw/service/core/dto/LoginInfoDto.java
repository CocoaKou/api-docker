package ai.wbw.service.core.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @Description Login Information DTO @Author Cocoa @Date 2024/8/15 @Version 0.1
 */
@Data
public class LoginInfoDto {
  @NotNull private Long userId;

  private String state;

  private String ip;

  private Boolean isOnline;

  private String appType;

  private String appVersion;

  private String clientModel;

  private String clientOs;
}
