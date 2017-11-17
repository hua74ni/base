package com.ssm.service.impl;

import com.ssm.mapper.UserMapper;
import com.ssm.pojo.SysPermission;
import com.ssm.pojo.User;
import com.ssm.service.UserService;
import com.ssm.utils.MD5;
import com.ssm.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by huangdonghua on 2017/11/5.
 */
@Service("userService")
//@Transactional(rollbackFor=java.lang.Exception.class) service配置回滚 在配置文件中配置
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    public Map<String, Object> queryUserPage(UserVo userVo) throws Exception {

        Map<String, Object> map = new HashMap<String, Object>();
        List<User> users = null;
        int total = 0;

        users = userMapper.queryUserPage(userVo);
        total = userMapper.queryUserTotal(userVo);

        map.put("recordsFiltered", total);
        map.put("data", users);
        map.put("recordsTotal", total);

        return map;
    }

    public User login(User user) throws Exception {

        User loginUser = userMapper.queryUserByUserCode(user.getUsercode());

        if(loginUser == null){
            throw new RuntimeException("账号不存在");
        }

        if(loginUser != null){

            String password = loginUser.getPassword();
            password = new MD5().getMD5ofStr(password);

            if(loginUser.getPassword().equals(password)){
                List<SysPermission> permissions = userMapper.queryPermissionByUserId(loginUser.getId());
                List<SysPermission> menus = userMapper.queryMenuByUserId(loginUser.getId());
                loginUser.setPermissions(permissions);
                loginUser.setMenus(menus);
                return loginUser;
            }else{
                throw new RuntimeException("账号或密码错误");
            }

        }
        throw new RuntimeException("登录出现异常");

    }

    public User shiroLogin(User user) throws Exception {
        User loginUser = userMapper.queryUserByUserCode(user.getUsercode());

        if(loginUser == null){
            throw new RuntimeException("账号不存在");
        }
        return loginUser;
    }

    public List<SysPermission> queryPermissionByUserId(String userId) throws Exception {
        return userMapper.queryPermissionByUserId(userId);
    }

    public List<SysPermission> queryMenuByUserId(String userId) throws Exception {
        return userMapper.queryMenuByUserId(userId);
    }
}
