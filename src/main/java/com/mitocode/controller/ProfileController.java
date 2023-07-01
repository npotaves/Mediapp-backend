package com.mitocode.controller;

import com.mitocode.dto.RolDTO;
import com.mitocode.model.Role;
import com.mitocode.service.IProfileService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {
    @Qualifier("defaultMapper")
    private final ModelMapper modelMapper;
    private final IProfileService service;

    @PostMapping("/role")
    public ResponseEntity<List<RolDTO>> getRoleByUser(@RequestBody String username) throws Exception {
        List<Role> role = service.getRoleByUsername(username);
        List<RolDTO> roleDTO = role.stream().map(m -> {
            return modelMapper.map(m, RolDTO.class);
        }).collect(Collectors.toList());
        return new ResponseEntity<>(roleDTO, HttpStatus.OK);
    }
}
