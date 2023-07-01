package com.mitocode.service.impl;


import com.mitocode.model.Role;
import com.mitocode.repo.IGenericRepo;
import com.mitocode.repo.IProfileRepo;
import com.mitocode.service.IProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl extends CRUDImpl<Role, Integer> implements IProfileService  {

    private final IProfileRepo repo;

    @Override
    protected IGenericRepo<Role, Integer> getRepo() {
        return repo;
    }

    @Override
    public List<Role> getRoleByUsername(String username) {
        return repo.getRoleByUsername(username);
    }
}
