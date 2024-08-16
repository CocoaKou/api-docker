package ai.wbw.service.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @Description User Entity @Author Cocoa @Date 2024/8/15 @Version 0.1
 */
@TableName("t_user")
@Data
public class User implements Serializable {

  @TableId(value = "id", type = IdType.ASSIGN_ID)
  private Long id;

  private String username;

  private String password;

  private String salt;

  private String type;

  private String state;

  private String mobile;

  private String origin;

  private String email;

  private String countryCode;

  private String nickname;

  private String gender;

  private String birthday;

  private String avatar;

  private String remark;

  private String createdBy;

  private Date createdTime;

  private String updatedBy;

  private Date updatedTime;

  @TableField(exist = false)
  private String newPasswd;
}
