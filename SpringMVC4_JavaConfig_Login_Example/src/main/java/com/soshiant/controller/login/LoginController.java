package com.soshiant.controller.login;


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
public class LoginController {


    @Autowired
    private UserService userService;

    //===================================== Url : http://localhost:8080/springmvc/showLogin=============================
    @RequestMapping(value = {"/showLogin","/logout"},method = RequestMethod.GET)
    public ModelAndView showLoginForm()  {

        ModelAndView model = new ModelAndView("/login/login");
        return model;
    }
    //===================================== Url : http://localhost:8080/springmvc/login=================================
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ModelAndView login(@RequestParam(value= "username",required = true) String username,
                              @RequestParam(value= "password",required = true) String password) throws Exception {

        ModelAndView tempModel = new ModelAndView();
        boolean loginResult = userService.login(username,password);
        if (! loginResult){
            tempModel.setViewName("error");
            tempModel.setStatus(HttpStatus.NOT_ACCEPTABLE);
        }
        else {
            tempModel.setViewName("/dashboard/dashboard");
            tempModel.setStatus(HttpStatus.OK);
        }
        return tempModel;
    }
    //==================================================================================================================
}
