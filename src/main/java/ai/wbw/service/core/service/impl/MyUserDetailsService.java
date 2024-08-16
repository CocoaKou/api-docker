// package ai.wbw.service.core.service.impl;
//
// import ai.wbw.service.core.entity.User;
// import ai.wbw.service.core.mapper.UserMapper;
// import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
// import org.slf4j.LoggerFactory;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.stereotype.Component;
// import java.util.Collection;
// import java.util.HashSet;
//
/// ***
// ** @Description: 自定义用户登录，加载数据库中的用户数据
// ** @Modified By:
// ***/
// @Component
// public class MyUserDetailsService implements UserDetailsService {
//
//
//    private final org.slf4j.Logger logger= LoggerFactory.getLogger(MyUserDetailsService.class);
//
//    @Autowired
//    private UserMapper userMapper;
//
//    /*
//        重写loadUserByUsername()将自定义用户登录放入oauth2
//     */
//    @Override
//    public UserDetails loadUserByUsername(String userName){
//        logger.info("loadUserByUsername:" + userName);
//        //从自己的用户表中获取数据
//        QueryWrapper<User> queryWrapper = new QueryWrapper();
//        User user = userMapper.selectOne(queryWrapper.eq("username",userName));
//        if (user==null) {
//            throw new UsernameNotFoundException("该用户:"+userName+"不存在!");
//        }
//
//        //创建一个HashSet存放用户权限
//        Collection<SimpleGrantedAuthority> collection = new HashSet<SimpleGrantedAuthority>();
//        collection.add(new SimpleGrantedAuthority("ADMIN"));
//        collection.add(new SimpleGrantedAuthority("USER"));
//        //如果这个用户在数据库就返回给SpringSecurity框架
//        logger.info("UserName:"+userName+";Password:"+user.getPassword()+";Role:"+collection);
//        return new
// org.springframework.security.core.userdetails.User(userName,user.getPassword(),collection);
//
//    }
// }
