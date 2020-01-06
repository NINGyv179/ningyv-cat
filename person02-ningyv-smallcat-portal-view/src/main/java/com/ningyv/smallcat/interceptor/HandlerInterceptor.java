package com.ningyv.smallcat.interceptor;

import com.google.gson.Gson;
import com.ningyv.smallcat.api.AuthoService;
import com.ningyv.smallcat.constant.Constant;
import com.ningyv.smallcat.resultEntity.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author LCX
 * @create 2020-01-04 20:15
 */
@Component
public class HandlerInterceptor  extends HandlerInterceptorAdapter {

    @Autowired
    private AuthoService authenticationRemoteService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        //获取请求参数token判断是否登录
        String token = request.getParameter("token");
        //调用authentication的
        ResultEntity<String> resultEntity = authenticationRemoteService.checkTokenFfective(token);

        if (ResultEntity.FAILED.equals(resultEntity.getResult())) {

            //判断当前请求是ajax请求还是普通请求
            String accept = request.getHeader("Accept");
            String requestedWith = request.getHeader("X-Requested-With");
            if (
                    (accept!=null && accept.contains("application/json"))
                            || (requestedWith!=null && requestedWith.equals("XMLHttpRequest"))) {
                ResultEntity<String> finaResultEntity =
                        new ResultEntity<>(ResultEntity.FAILED, Constant.MSG_ACCESS_DENY, null);
                Gson gson = new Gson();

                String json = gson.toJson(finaResultEntity);

                response.setContentType("application/json;charset=UTF-8");

                response.getWriter().write(json);
            }else {
                //普通請求
                request.setAttribute(Constant.ATTR_NAME_MESSAGE, Constant.MSG_ACCESS_DENY);
                //转发到错误页面
                request.getRequestDispatcher("/error.html").forward(request, response);

            }
            return false;
        }else {
            //已经登录，直接放行
            return true;
        }

    }
}
