package ai.wbw.service.core.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/** 重置密码的参数对象 */
@Data
public class ResetPasswdDto {

  @Email(message = "Invalid email address")
  private String username;

  @NotEmpty(message = "Password cannot be null")
  private String newPassword;

  @NotEmpty(message = "Verification code cannot be null")
  private String verificationCode;

  public ResetPasswdDto() {}
  ;

  public ResetPasswdDto(
      @NotEmpty(message = "username cannot be null") String username,
      @NotEmpty(message = "password cannot be null") String newPassword,
      @NotEmpty(message = "verification code cannot be null") String verificationCode) {
    this.username = username;
    this.newPassword = newPassword;
    this.verificationCode = verificationCode;
  }
}
