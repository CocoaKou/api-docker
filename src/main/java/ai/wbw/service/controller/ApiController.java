package ai.wbw.service.controller;

import ai.wbw.service.common.enums.Code;
import ai.wbw.service.common.model.ResultEntity;
import ai.wbw.service.core.dto.*;
import ai.wbw.service.core.entity.User;
import ai.wbw.service.core.service.ILoginService;
import ai.wbw.service.core.service.IUserService;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Description Main API @Author Cocoa @Date 2024/8/13 14:14 @Version 0.1
 */
@RestController
@RequestMapping("/api/v1")
public class ApiController extends BaseController {

  @Autowired private IUserService userService;
  @Autowired private ILoginService loginService;

  private static final Logger logger = LoggerFactory.getLogger(ApiController.class);

  // 测试接口
  @GetMapping(value = "/app/test/hello")
  public @ResponseBody String hello() {
    return "Hello";
  }

  // 登录验证
  @PostMapping(value = "/app/auth/verify")
  public @ResponseBody ResultEntity verify(@RequestBody @Validated LoginReqDto loginReqDto) {
    return userService.verify(loginReqDto);
  }

  // 用户注册
  @PostMapping(value = "/app/auth/register")
  public @ResponseBody ResultEntity register(@RequestBody @Validated UserDto userDto) {
    User user = new User();
    BeanUtils.copyProperties(userDto, user);
    return userService.createUser(user);
  }

  // 用户登出
  @PostMapping(value = "/app/auth/logout")
  public @ResponseBody ResultEntity logout(@RequestBody Map<String, Long> map) {
    if (map == null || !map.containsKey("userId")) {
      return new ResultEntity(Code.FAILED.getValue(), "userId null", "");
    }
    ResultEntity result = userService.logout(map.get("userId"));
    return result;
  }

  // 根据用户名查询
  @PostMapping(value = "/app/user/userinfo")
  public @ResponseBody ResultEntity getByUsername(@RequestBody Map<String, String> map) {
    if (map == null || !map.containsKey("username")) {
      return ResultEntity.fail("username null");
    }
    return userService.getByUsername(map.get("username"));
  }

  // 修改密码
  @PostMapping(value = "/app/user/update-passwd")
  public @ResponseBody ResultEntity updatePwd(
      @RequestBody @Validated UpdatePasswordDto updatePasswordDto) {
    return userService.updatePwd(
        updatePasswordDto.getUsername(),
        updatePasswordDto.getPassword(),
        updatePasswordDto.getNewPassword());
  }

  // 重置密码
  @PostMapping(value = "/app/user/reset-passwd")
  public @ResponseBody ResultEntity resetPwd(
      @RequestBody @Validated ResetPasswdDto resetPasswdDto) {
    return userService.resetPwd(resetPasswdDto);
  }

  // 保存登录信息
  @PutMapping(value = "/app/user/login")
  public @ResponseBody ResultEntity loginInfo(@RequestBody @Validated LoginInfoDto loginInfoDto) {
    return loginService.saveOrUpdate(loginInfoDto);
  }
}
