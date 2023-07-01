package com.mitocode.service;

import com.mitocode.model.Menu;
import com.mitocode.model.Role;
import com.mitocode.model.User;

import java.util.List;

public interface IProfileService extends ICRUD<Role, Integer>{

    List<Role> getRoleByUsername(String username);
}
