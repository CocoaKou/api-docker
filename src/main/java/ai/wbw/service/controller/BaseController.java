package ai.wbw.service.controller;

import java.beans.PropertyEditorSupport;
import java.sql.Timestamp;
import java.util.Date;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

/**
 * @Description Base Controller @Author Cocoa @Date 2024/8/13 14:16 @Version 0.1
 */
public class BaseController {

  @InitBinder
  protected void initBinder(WebDataBinder binder) {
    binder.registerCustomEditor(
        Date.class,
        new PropertyEditorSupport() {
          @Override
          public void setAsText(String value) {
            setValue(new Timestamp(Long.valueOf(value)));
          }
        });
  }
}
