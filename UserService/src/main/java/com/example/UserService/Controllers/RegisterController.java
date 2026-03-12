package com.example.UserService.Controllers;

import com.example.UserService.DTO.AuthUserDTO;
import com.example.UserService.DTO.RegisterUserDTO;
import com.example.UserService.Entity.User;
import com.example.UserService.Service.ServiceUser;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/Users")
@RequiredArgsConstructor

public class RegisterController {
    private final ServiceUser serviceUser;

    @PostMapping("/resgistro")
    public AuthUserDTO resgistro(@RequestBody @Valid RegisterUserDTO regdto){
        return serviceUser.registerUser(regdto);
    }

}
