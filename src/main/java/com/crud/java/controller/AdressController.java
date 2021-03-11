package com.crud.java.controller;

import com.crud.java.model.Adress;
import com.crud.java.service.AdressService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/adresses")
@Api("Adress API")
public class AdressController {

    @Autowired
    private AdressService service;

    @ApiOperation("CREATE A NEW ADDRESS")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Address successfully creating"),
            @ApiResponse(code = 404, message = "The request resource was not found")
    })
    @PostMapping
    public Adress createNewAdress(@RequestBody @Valid Adress adress) throws JSONException {
        return service.save(adress);
    }

    @ApiOperation("GET A ADDRESS")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Find address request successfully executed"),
            @ApiResponse(code = 404, message = "The address was not found")
    })
    @GetMapping("{id}")
    public Adress getAdressById(@PathVariable Integer id){
        return service.findById(id);
    }

    @ApiOperation("DELETE A ADDRESS")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Deleted address request successfully executed"),
            @ApiResponse(code = 404, message = "The address was not found")
    })
    @DeleteMapping("{id}")
    public void deleteAdress(@PathVariable Integer id){
        service.delete(id);
    }

    @ApiOperation("UPDATE A ADDRESS")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Updated address request successfully executed"),
            @ApiResponse(code = 404, message = "The address was not found")
    })
    @PutMapping("{id}")
    public Adress updateAdress(@PathVariable Integer id,
                               @RequestBody @Valid Adress newAdress){
        return service.update(id,newAdress);
    }
}
