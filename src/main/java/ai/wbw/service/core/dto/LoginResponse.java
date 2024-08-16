package ai.wbw.service.core.dto;

import lombok.Data;

@Data
public class LoginResponse {

  private Long userId;

  private String username;

  private String nickname;

  private String avatar;
}
