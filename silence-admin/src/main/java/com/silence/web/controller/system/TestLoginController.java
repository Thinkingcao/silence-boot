package com.silence.web.controller.system;

import com.silence.common.core.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <pre>
 * @desc:
 * @title: TestController
 * @author: cao_wencao
 * @date: 2019-06-12 16:25
 * @version: 1.0
 * </pre>
 */
@Controller
public class TestLoginController extends BaseController {
    @GetMapping("/test")
    public String login(HttpServletRequest request, HttpServletResponse response)
    {

        return "test";
    }
}
