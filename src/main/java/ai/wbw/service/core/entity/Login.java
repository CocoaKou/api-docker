package ai.wbw.service.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

/**
 * @Description Login Entity @Author Cocoa @Date 2024/8/15 @Version 0.1
 */
@TableName("t_login")
@Data
public class Login {

  @TableId(value = "id", type = IdType.AUTO)
  private Long id;

  private Long userId;

  private String state;

  private String ip;

  private Boolean isOnline;

  private String appType;

  private String appVersion;

  private String clientModel;

  private String clientOs;

  private String createdBy;

  private Date createdTime;

  private String updatedBy;

  private Date updatedTime;

  @TableField(exist = false)
  private String newPasswd;
}
