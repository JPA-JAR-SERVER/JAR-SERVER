package com.app.projectjar.controller.OAuth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
@Slf4j
public class CustomError implements ErrorController {

    @GetMapping("/error")
    public String handleError(HttpServletRequest request){
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if(status != null){
            int code = Integer.valueOf(status.toString());
            log.info("{}========",code);
            if(code == HttpStatus.NOT_FOUND.value()){
                return "/error/404";
            }else if(code == HttpStatus.UNAUTHORIZED.value()){
                return "/error/401";
            }else if(code == HttpStatus.FORBIDDEN.value()){
                return "/error/403";
            }
        }
        return "/error/500";
    }


    @GetMapping("/service")
    public String comingSoon(){
        return "error/coming-soon";
    }
}
















