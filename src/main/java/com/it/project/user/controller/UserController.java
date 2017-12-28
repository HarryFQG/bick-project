package com.it.project.user.controller;

import com.it.project.common.constants.Constant;
import com.it.project.common.exception.BickException;
import com.it.project.common.response.APIResult;
import com.it.project.common.rest.BaseController;

import com.it.project.user.entity.LoginInfo;
import com.it.project.user.entity.User;
import com.it.project.user.entity.UserElement;
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

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author fengqigui
 * @Date 2017/12/19 19:46
 */

@RestController
@RequestMapping("/user")
public class UserController extends BaseController{

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


    /**
     * 修改用户的昵称
     * 这里注意：用户的ID从后台获取，不在是前台获取，因为别人可以利用这个传别人的 ID 。从而修改别人的
     * 其次，这里没有session，但是有token
     * @param user
     * @return
     */
    @RequestMapping(value = "/modifyUser", method = RequestMethod.POST)
    public APIResult modifyNickName(@RequestBody User user) {

        APIResult resp = new APIResult() ;
        try{

            UserElement currentUser = getCurrentUser();
            user.setUserId(currentUser.getUserId());
            userService.modifyNickNmae(user);

        }catch (BickException e) {
            resp.setCode(Constant.RESP_STATUS_INTERNAL_ERROR);
            resp.setMessage(e.getMessage());
        }catch (Exception e){
            LOGGER.error("Fail to modify name",e);
            resp.setCode(Constant.RESP_STATUS_INTERNAL_ERROR);
            resp.setMessage("内部错误");
        }
        return resp;

    }


    /**
     * 发送短信的controller
     * @param user
     * @param request
     * @return
     */
    @RequestMapping("/sendVercode")
    public APIResult modifyNickName(@RequestBody User user, HttpServletRequest request) {

        APIResult resp = new APIResult() ;
        try{
            userService.sendVercode(user.getUserMobile(), getIpFromRequest(request));
        } catch (Exception e){
            LOGGER.error("Fail to send sms vercode",e);
            resp.setCode(Constant.RESP_STATUS_INTERNAL_ERROR);
            resp.setMessage("内部错误");
        }
        return resp;

    }


}
