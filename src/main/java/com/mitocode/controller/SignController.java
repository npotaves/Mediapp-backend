package com.mitocode.controller;

import com.mitocode.dto.SignDTO;
import com.mitocode.model.Sign;
import com.mitocode.service.impl.SignServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/signs")
@RequiredArgsConstructor
public class SignController {

    private final SignServiceImpl service;

    @Qualifier("defaultMapper")
    private final ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<SignDTO>> findAll(){
        List<SignDTO> list = service.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SignDTO> findById(@PathVariable("id") Integer id){
        SignDTO dto = this.convertToDto(service.findById(id));
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody SignDTO dto){
        Sign obj = service.save(this.convertToEntity(dto));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdSign()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<SignDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody SignDTO dto){
        dto.setIdSign(id);
        Sign obj = service.update(this.convertToEntity(dto), id);
        return new ResponseEntity<>(this.convertToDto(obj), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id){
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/pageable")
    public ResponseEntity<Page<SignDTO>> listPage(Pageable pageable) {
        Page<SignDTO> page = service.listPage(pageable).map(p -> mapper.map(p, SignDTO.class));
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    private SignDTO convertToDto(Sign obj){
        return mapper.map(obj, SignDTO.class);
    }

    private Sign convertToEntity(SignDTO dto){
        return mapper.map(dto, Sign.class);
    }
}
