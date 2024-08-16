package ai.wbw.service;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ServiceApplicationTests {
  private static final Logger logger = LoggerFactory.getLogger(ServiceApplicationTests.class);

  @Test
  public void contextLoads() {}

  @Test
  public void test1() {
    logger.info("hello test1");
  }

  @Test
  public void test2() {
    logger.info("hello test2");
  }
}
