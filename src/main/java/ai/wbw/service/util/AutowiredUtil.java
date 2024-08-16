package ai.wbw.service.util;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/** 服务注入工具类 */
@Component
public class AutowiredUtil {
  public static AutowiredUtil autowiredUtil;

  @PostConstruct
  public void init() {
    autowiredUtil = this;
  }

  /** 注入的服务 */
  @Autowired public ApplicationContext applicationContext;
}
