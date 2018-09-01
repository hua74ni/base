package com.ssm.mapper;

import com.ssm.TkMapper.CustomMapper;
import com.ssm.pojo.SysPermission;
import com.ssm.pojo.User;
import com.ssm.vo.UserVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by huangdonghua on 2017/11/5.
 */

public interface UserMapper extends CustomMapper<User> {

    List<User> queryUserPage(@Param("userVo") UserVo userVo);

    int queryUserTotal(@Param("userVo") UserVo userVo);

    User queryUserByUserCode(@Param("usercode") String usercode);

    List<SysPermission> queryPermissionByUserId(@Param("userId") String userId);

    List<SysPermission> queryMenuByUserId(@Param("userId") String userId);

}
