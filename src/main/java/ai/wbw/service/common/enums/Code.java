package ai.wbw.service.common.enums;

import lombok.Getter;
import lombok.Setter;

public enum Code {
  /** 状态码 失败 */
  FAILED(9999, "failed"),
  /** 状态码 成功 */
  SUCCESS(1000, "success");

  @Setter @Getter private int value;
  @Setter @Getter private String msg;

  Code(int value, String msg) {
    this.value = value;
    this.msg = msg;
  }
}
