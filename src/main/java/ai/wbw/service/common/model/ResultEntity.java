package ai.wbw.service.common.model;

import ai.wbw.service.common.enums.Code;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description 结果封装实体 @Author Cocoa @Date 2024/8/15 @Version 0.1
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultEntity {

  private int code;
  private String msg;
  private Object data;

  public static ResultEntity ok() {
    return new ResultEntity(Code.SUCCESS.getValue(), Code.SUCCESS.getMsg(), null);
  }

  public static ResultEntity ok(Object data) {
    return new ResultEntity(Code.SUCCESS.getValue(), Code.SUCCESS.getMsg(), data);
  }

  public static ResultEntity fail() {
    return new ResultEntity(Code.FAILED.getValue(), Code.FAILED.getMsg(), null);
  }

  public static ResultEntity fail(Object data) {
    return new ResultEntity(Code.FAILED.getValue(), Code.FAILED.getMsg(), data);
  }
}
