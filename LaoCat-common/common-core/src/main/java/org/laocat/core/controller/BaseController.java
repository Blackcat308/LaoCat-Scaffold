package org.laocat.core.controller;

import org.laocat.core.exception.PasswordErrException;
import org.laocat.core.exception.RoleCodeAlreadyExistException;
import org.laocat.core.exception.UserAlreadyExistException;
import org.laocat.core.response.structure.ResponseEntity;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @description: 基础controller
 * @author: LaoCat
 * @date: 2022/6/27
 * @see
 */
@RestController
@RestControllerAdvice(basePackages = "org.laocat.controller")
public class BaseController {
    /**
     * @description: 为方便前端传入字符串解析为date
     * @author: LaoCat
     * @date: 2022/6/27
     * @returnType: void
     */
    @InitBinder
    public void globalInitBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(new CustomDateFormat(), true));
    }


    /**
     * @description: 拦截异常
     * @author: LaoCat
     * @date: 2022/6/27
     * @returnType: org.laocat.core.response.structure.ResponseEntity<?>
     */
    @ExceptionHandler({RoleCodeAlreadyExistException.class, UserAlreadyExistException.class, PasswordErrException.class})
    @ResponseBody
    public ResponseEntity<?> laoCatCustomExceptionHandler(Exception e) {

        String errMsg = null;
        if (e instanceof RoleCodeAlreadyExistException) {
            errMsg = e.getMessage();
        }

        if (e instanceof UserAlreadyExistException) {
            errMsg = e.getMessage();
        }

        if (e instanceof PasswordErrException) {
            errMsg = e.getMessage();
        }
        return ResponseEntity.fail(errMsg);
    }


}


