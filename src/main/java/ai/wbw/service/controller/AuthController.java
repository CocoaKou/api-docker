package ai.wbw.service.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

/**
 * @Description @Author Cocoa @Date 2024/8/14 11:57 @Version 0.1
 */
@RestController
@RequestMapping("/oauth2")
public class AuthController extends BaseController {

  private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

  // 测试接口
  @GetMapping(value = "/confirm_access")
  public @ResponseBody String comfirmAccess() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
    String name1 = authentication.getName();
    logger.info("name1:", name1, "name2:", oAuth2User.getName());
    return "SUCCESS";
  }
}
