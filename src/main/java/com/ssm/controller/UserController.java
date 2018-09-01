package com.ssm.controller;

import com.ssm.service.UserService;
import com.ssm.utils.AjaxResult;
import com.ssm.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by huangdonghua on 2017/11/5.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/userList")
    @ResponseBody
    public Map<String,Object> queryUserPage(UserVo userVo,
                         @RequestParam(required = false,name = "order[0][column]") String order_column,
                         @RequestParam(required = false,name = "order[0][dir]") String order_column_dir,
                         @RequestParam(required = false,name = "search[value]") String username){
        if(order_column != null && !order_column.equals("")){
            userVo.setOrder_column(Integer.valueOf(order_column));
        }
        if(order_column_dir != null && !order_column_dir.equals("")){
            userVo.setOrder_column_dir(order_column_dir);
        }
        if(username != null && !username.equals("")){
            userVo.setUsername(username);
        }

        Map<String,Object> map = null;
        try {
            map = userService.queryUserPage(userVo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    @RequestMapping("/deleteUser")
    @ResponseBody
    public AjaxResult deleteUser(@RequestBody String id){

        AjaxResult ajaxResult = AjaxResult.SUCCESS;

        System.out.println("删除ID"+id);

        return ajaxResult;

    }



}
