package ai.wbw.service.core.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @Description User DTO @Author Cocoa @Date 2024/8/15 10:02 @Version 0.1
 */
public class UserDto implements UserDetails {

  @NotEmpty(message = "username cannot be Null")
  private String username;

  @NotEmpty(message = "password cannot be Null")
  private String password;

  @NotEmpty(message = "mobile cannot be Null")
  private String mobile;

  @NotEmpty(message = "email cannot be Null")
  @Email
  private String email;

  private String countryCode;

  private String nickname;

  private String gender;

  private String birthday;

  private String avatar;

  public UserDto() {}
  ;

  public UserDto(
      @NotEmpty(message = "username cannot be Null") String username,
      @NotEmpty(message = "password cannot be Null") String password) {
    this.username = username;
    this.password = password;
  }

  public String getUsername() {
    return username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return UserDetails.super.isAccountNonExpired();
  }

  @Override
  public boolean isAccountNonLocked() {
    return UserDetails.super.isAccountNonLocked();
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return UserDetails.super.isCredentialsNonExpired();
  }

  @Override
  public boolean isEnabled() {
    return UserDetails.super.isEnabled();
  }

  public void setUsername(String username) {
    this.username = username;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of();
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getCountryCode() {
    return countryCode;
  }

  public void setCountryCode(String countryCode) {
    this.countryCode = countryCode;
  }

  public String getNickname() {
    return nickname;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public String getBirthday() {
    return birthday;
  }

  public void setBirthday(String birthday) {
    this.birthday = birthday;
  }

  public String getAvatar() {
    return avatar;
  }

  public void setAvatar(String avatar) {
    this.avatar = avatar;
  }

  @Override
  public String toString() {
    return "UserDto{"
        + "username='"
        + username
        + '\''
        + ", password='"
        + password
        + '\''
        + ", mobile='"
        + mobile
        + '\''
        + ", email='"
        + email
        + '\''
        + ", countryCode='"
        + countryCode
        + '\''
        + ", nickname='"
        + nickname
        + '\''
        + ", gender='"
        + gender
        + '\''
        + ", birthday='"
        + birthday
        + '\''
        + ", avatar='"
        + avatar
        + '\''
        + '}';
  }
}
