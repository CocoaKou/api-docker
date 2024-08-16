package ai.wbw.service.core.dto;

import jakarta.validation.constraints.NotEmpty;

/**
 * @Description update password DTO @Author Cocoa @Date 2024/8/15 @Version 0.1
 */
public class UpdatePasswordDto {

  @NotEmpty(message = "username cannot be Null")
  private String username;

  @NotEmpty(message = "password cannot be Null")
  private String password;

  @NotEmpty(message = "new password cannot be Null")
  private String newPassword;

  public UpdatePasswordDto() {}
  ;

  public String getNewPassword() {
    return newPassword;
  }

  public UpdatePasswordDto(
      @NotEmpty(message = "username cannot be Null") String username,
      @NotEmpty(message = "password cannot be Null") String password,
      @NotEmpty(message = "new password cannot be Null") String newPassword) {
    this.username = username;
    this.password = password;
    this.newPassword = newPassword;
  }

  public void setNewPassword(String newPassword) {
    this.newPassword = newPassword;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
