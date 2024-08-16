package ai.wbw.service.core.service.impl;

import static org.springframework.util.StringUtils.hasText;

import ai.wbw.service.common.constant.Constants;
import ai.wbw.service.common.constant.RedisConstants;
import ai.wbw.service.common.model.ResultEntity;
import ai.wbw.service.core.dto.*;
import ai.wbw.service.core.entity.*;
import ai.wbw.service.core.mapper.*;
import ai.wbw.service.core.service.*;
import ai.wbw.service.util.CryptoUtil;
import ai.wbw.service.util.JsonUtils;
import ai.wbw.service.util.StringUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description User service @Author Cocoa @Date 2024/8/15 @Version 0.1
 */
@Service
@Transactional
public class UserService extends ServiceImpl<UserMapper, User> implements IUserService {

  @Autowired private IRedisService redisService;

  @Autowired private IRedisTokenService redisTokenService;

  private static Logger logger = LoggerFactory.getLogger(UserService.class);

  @Override
  @Transactional
  public ResultEntity createUser(User user) {
    if (user == null) {
      return ResultEntity.fail("params null");
    }
    if (StringUtils.isBlank(user.getUsername()) || StringUtils.isBlank(user.getPassword())) {
      return ResultEntity.fail("username or password null");
    }
    // 未激活
    user.setState(Constants.USER_STATE_NOT_ACTIVATED);
    // 加盐
    String salt = RandomStringUtils.randomAlphabetic(6);
    user.setSalt(salt);
    user.setPassword(CryptoUtil.encodeByMD5Base64(user.getPassword() + salt));
    // 判断用户名是否已经存在
    QueryWrapper<User> wrapper = new QueryWrapper<>();
    wrapper.eq("username", user.getUsername());
    User existUser = this.baseMapper.selectOne(wrapper);
    if (existUser != null) {
      // 如果未激活，且激活邮件失效，则允许重新注册
      if (Constants.USER_STATE_NOT_ACTIVATED.equals(existUser.getState())
          && !redisService.existsKey(RedisConstants.REDIS_KEY_PREFIX_EMAIL + existUser.getId())) {
        user.setId(existUser.getId());
        int flag = this.baseMapper.updateById(user);
        if (flag > 0) {
          logger.debug("Sendding activtion email...");
          // 发送激活邮件
          // ...
          return ResultEntity.ok();
        } else {
          return ResultEntity.fail();
        }
      }
      return ResultEntity.fail("user already exist");
    } else {
      int flag = this.baseMapper.insert(user);
      if (flag > 0) {
        return ResultEntity.ok();
        // 发送激活邮件
        // ...
      } else {
        return ResultEntity.fail();
      }
    }
  }

  @Override
  public ResultEntity verify(LoginReqDto loginReqDto) {
    QueryWrapper<User> wrapper = new QueryWrapper<>();
    wrapper.eq("username", loginReqDto.getUsername());
    User existUser = this.baseMapper.selectOne(wrapper);
    if ((existUser == null)) {
      return ResultEntity.fail("user not exist");
    } else {
      // if (!loginReqDto.getPassword().equals(existUser.getPassword())) {
      // 此处为password + salt的MD5加密
      if (!CryptoUtil.encodeByMD5Base64(loginReqDto.getPassword() + existUser.getSalt())
          .equals(existUser.getPassword())) {
        return ResultEntity.fail("username or password error");
      } else if (Constants.USER_STATE_NOT_ACTIVATED.equals(existUser.getState())) {
        return ResultEntity.fail("account not actived");
      } else if (Constants.USER_STATE_DISABLED.equals(existUser.getState())) {
        return ResultEntity.fail("account disabled");
      } else {
        LoginResultDto<LoginResponse> loginResultDto = new LoginResultDto<LoginResponse>();
        TokenModelDto token = redisTokenService.createAppToken(existUser.getId() + "");
        loginResultDto.setAccessToken(token.getToken());
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setUserId(Long.valueOf(existUser.getId()));
        loginResponse.setUsername(existUser.getUsername());
        loginResponse.setNickname(existUser.getNickname());
        if (hasText(existUser.getAvatar())) {
          loginResponse.setAvatar("avatar url");
        }
        loginResultDto.setResult(loginResponse);
        return ResultEntity.ok(loginResultDto);
      }
    }
  }

  @Override
  public ResultEntity getByUsername(String username) {
    if (StringUtils.isBlank(username)) {
      return ResultEntity.fail("username is null");
    }
    QueryWrapper<User> wrapper = new QueryWrapper<>();
    wrapper.eq("username", username);
    User user = this.baseMapper.selectOne(wrapper);
    if (user != null) {
      return ResultEntity.ok(user);
    } else {
      return ResultEntity.fail("user not exist");
    }
  }

  @Override
  public ResultEntity updatePwd(String username, String password, String newPassword) {
    if (StringUtil.isBlank(username)
        || StringUtil.isBlank(password)
        || StringUtil.isBlank(newPassword)) {
      return ResultEntity.fail("param null");
    } else {
      QueryWrapper<User> wrapper = new QueryWrapper<>();
      wrapper.eq("username", username);
      User user = this.baseMapper.selectOne(wrapper);
      if (user == null) {
        return ResultEntity.fail("username not exist");
      }
      if (CryptoUtil.validateByMD5Base64(user.getPassword(), password + user.getSalt())) {
        user.setPassword(CryptoUtil.encodeByMD5Base64(newPassword + user.getSalt()));
        this.baseMapper.updateById(user);
        return ResultEntity.ok();
      } else {
        return ResultEntity.fail("username or password error");
      }
    }
  }

  @Override
  public ResultEntity resetPwd(ResetPasswdDto resetPasswdDto) {
    QueryWrapper<User> wrapper = new QueryWrapper<>();
    wrapper.eq("username", resetPasswdDto.getUsername());
    User user = this.baseMapper.selectOne(wrapper);
    if (user == null) {
      return ResultEntity.fail("user not exist");
    } else {
      String resetPasswdEmailCodeRedisKey =
          RedisConstants.REDIS_KEY_PREFIX_CAPTCHA + resetPasswdDto.getUsername();
      if (redisService.existsKey(resetPasswdEmailCodeRedisKey)
          && redisService
              .getValue(resetPasswdEmailCodeRedisKey)
              .equals(resetPasswdDto.getVerificationCode())) {
        String newEncodePassword =
            CryptoUtil.encodeByMD5Base64(resetPasswdDto.getNewPassword() + user.getSalt());
        if (newEncodePassword.equals(user.getPassword())) {
          return ResultEntity.fail("new password is same as old password");
        }
        // 修改为新密码
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("username", resetPasswdDto.getUsername());
        updateWrapper.set("password", newEncodePassword);
        if (this.update(null, updateWrapper)) {
          redisService.deleteKey(resetPasswdEmailCodeRedisKey);
          return ResultEntity.ok();
        }
      } else {
        return ResultEntity.fail("verification code invalid");
      }
      return ResultEntity.fail();
    }
  }

  @Override
  public ResultEntity logout(Long userId) {
    if (userId == null) {
      return ResultEntity.fail("userId is null");
    } else {
      if (redisService.existsKey(RedisConstants.REDIS_KEY_PREFIX_APP + userId)) {
        // 删除相关的token和regid
        redisService.deleteKey(RedisConstants.REDIS_KEY_PREFIX_APP + userId);
        return ResultEntity.ok();
      } else {
        return ResultEntity.fail("user not sign in");
      }
    }
  }

  @Override
  public JSONObject testVerify(LoginReqDto loginReqDto) {
    return JsonUtils.readJsonFile("templates/json/verify.json");
  }
}
