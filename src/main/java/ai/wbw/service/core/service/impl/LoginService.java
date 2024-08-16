package ai.wbw.service.core.service.impl;

import ai.wbw.service.common.model.ResultEntity;
import ai.wbw.service.core.dto.LoginInfoDto;
import ai.wbw.service.core.entity.Login;
import ai.wbw.service.core.mapper.LoginMapper;
import ai.wbw.service.core.service.ILoginService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * @Description @Author Cocoa @Date 2024/8/15 @Version 0.1
 */
@Service
public class LoginService extends ServiceImpl<LoginMapper, Login> implements ILoginService {
  @Override
  public ResultEntity saveOrUpdate(LoginInfoDto loginInfoDto) {
    Login login = new Login();
    BeanUtils.copyProperties(loginInfoDto, login);
    return saveOrUpdate(login) ? ResultEntity.ok() : ResultEntity.fail();
  }
}
