package com.mitocode.repo;

import com.mitocode.model.User;

public interface IUserRepo extends IGenericRepo<User, Integer>  {

    //FROM User WHERE username = ?
    //Derived Query
    User findOneByUsername(String username);
}
