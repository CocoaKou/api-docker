package ai.wbw.service.core.dto;

/** Token的Model类 */
public class TokenModelDto {

  // 用户id
  private String userId;

  // 随机生成的uuid
  private String token;

  public TokenModelDto(String userId, String token) {
    this.userId = userId;
    this.token = token;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }
}
