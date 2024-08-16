package ai.wbw.service.core.service;

import ai.wbw.service.common.model.ResultEntity;
import ai.wbw.service.core.dto.LoginReqDto;
import ai.wbw.service.core.dto.ResetPasswdDto;
import ai.wbw.service.core.entity.User;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description 用户相关接口 @Author Cocoa @Date 2024/8/15 @Version 0.1
 */
public interface IUserService extends IService<User> {

  ResultEntity createUser(User user);

  /**
   * 校验密码
   *
   * @param loginReqDto
   * @return
   */
  ResultEntity verify(LoginReqDto loginReqDto);

  /**
   * 根据用户名查询用户信息
   *
   * @param username
   * @return
   */
  ResultEntity getByUsername(String username);

  /**
   * 修改密码
   *
   * @param username
   * @param password
   * @param newPasswd
   * @return
   */
  ResultEntity updatePwd(String username, String password, String newPasswd);

  /**
   * 重置密码
   *
   * @return
   */
  ResultEntity resetPwd(ResetPasswdDto resetPasswdDto);

  /**
   * 登出
   *
   * @param userId
   * @return
   */
  ResultEntity logout(Long userId);

  JSONObject testVerify(LoginReqDto loginReqDto);
}
