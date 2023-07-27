package com.example.demo.config.aspect;

import com.example.demo.config.auth.AuthService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class AuthAspect {
    @Autowired
    private AuthService authService;
    @Before("@annotation(TokenRequired)")
    public void checkToken(){
        ServletRequestAttributes request =
                (ServletRequestAttributes) RequestContextHolder
                        .currentRequestAttributes();
        String token = request.getRequest().getHeader("Authorization");
        if (token == null) throw new RuntimeException("token is null");
        token = token.replace("Bearer", "");
        System.out.println(authService.getClaims(token));

    }


    @Around("@annotation(TokenRequired)")
    public void checkTokenAround(){
        ServletRequestAttributes request =
                (ServletRequestAttributes) RequestContextHolder
                        .currentRequestAttributes();
        String token = request.getRequest().getHeader("Authorization");
        if (token == null) throw new RuntimeException("token is null");
        token = token.replace("Bearer", "");
        System.out.println(authService.getClaims(token));

    }
}
