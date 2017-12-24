package com.soshiant.controller.users;


import com.soshiant.model.user.UserInfo;
import com.soshiant.service.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/springmvc")
public class UserController {

    @Autowired
    private UserService userService;

    //============================== Url : http://localhost:8080/springmvc/showRegisterUser=============================
    @RequestMapping(value = "/showRegisterUser",method = RequestMethod.GET)
    public ModelAndView showRegisterUserForm()  {

        ModelAndView model = new ModelAndView("/user/registerUser");
        return model;
    }
    //============================== Url : http://localhost:8080/springmvc/registerUser=================================
    @RequestMapping(value = "/registerUser",method = RequestMethod.POST)
    public ModelAndView registerUser(@RequestParam(value= "userFirstName",required = true) String userFirstName,
                                     @RequestParam(value= "userLastName",required = true) String userLastName,
                                     @RequestParam(value= "username",required = true) String username,
                                     @RequestParam(value= "password",required = true) String password) throws Exception {

        ModelAndView tempModel = new ModelAndView();
        UserInfo userInfo = new UserInfo();
        userInfo.setUserFirstName(userFirstName);
        userInfo.setUserLastName(userLastName);
        userInfo.setUsername(username);
        userInfo.setPassword(password);

        boolean registerResult = userService.saveNewUser(userInfo);
        if (! registerResult){
            tempModel.setViewName("error");
            tempModel.setStatus(HttpStatus.NOT_ACCEPTABLE);
        }
        else {
            tempModel.setViewName("redirect:/springmvc/showLogin");
            tempModel.setStatus(HttpStatus.OK);
        }
        return tempModel;
    }
    //==================================================================================================================

}
