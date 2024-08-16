package ai.wbw.service;

import ai.wbw.service.core.dto.TokenSubject;
import ai.wbw.service.core.service.impl.JwtTokenService;
import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class JwtTokenServiceTest {
  @Autowired public JwtTokenService jwtTokenService;

//  @Test
  public void token() {
    String token = jwtTokenService.createDefaultToken(new TokenSubject("userid", "passwd"));
    log.info(token);
    TokenSubject tokenSubject = jwtTokenService.parseToken(token);
    log.info(JSON.toJSONString(tokenSubject));
  }
}
