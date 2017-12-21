package com.it.project.user.controller;

import com.it.project.common.constants.Constant;
import com.it.project.common.exception.BickException;
import com.it.project.common.response.APIResult;
import com.it.project.user.dao.UserMapper;
import com.it.project.user.entity.LoginInfo;
import com.it.project.user.entity.User;
import com.it.project.user.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author fengqigui
 * @Date 2017/12/19 19:46
 */

@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;


    @RequestMapping(value = "/login", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public APIResult<String> login(@RequestBody LoginInfo loginInfo){

        APIResult<String> apiResult = new APIResult<>();
        try {
            String data = loginInfo.getData();
            String key = loginInfo.getAESkey();
            if (StringUtils.isBlank(data) || StringUtils.isBlank(key)) {
                throw new BickException("数据格式错误");
            }
            
            String token = userService.login(data, key);
            apiResult.setData(token);
        }catch (BickException e) {
            apiResult.setCode(Constant.RESP_STATUS_INTERNAL_ERROR);
            apiResult.setMessage(e.getMessage());
        }catch (Exception e){
            LOGGER.error("Fail to login",e);
            apiResult.setCode(Constant.RESP_STATUS_INTERNAL_ERROR);
            apiResult.setMessage("内部错误");
        }
        return apiResult;

    }


}
