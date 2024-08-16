package ai.wbw.service.core.service;

import ai.wbw.service.common.model.ResultEntity;
import ai.wbw.service.core.dto.LoginInfoDto;
import ai.wbw.service.core.entity.Login;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description Login information interface @Author Cocoa @Date 2024/8/15 @Version 0.1
 */
public interface ILoginService extends IService<Login> {

  ResultEntity saveOrUpdate(LoginInfoDto loginInfoDto);
}
