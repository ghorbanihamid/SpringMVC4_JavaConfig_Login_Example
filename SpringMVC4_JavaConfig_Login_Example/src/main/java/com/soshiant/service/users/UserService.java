package com.soshiant.service.users;


import com.soshiant.model.user.UserInfo;

public interface UserService {

    public boolean login(String userName, String password);

    public boolean saveNewUser(UserInfo userInfo);

}
