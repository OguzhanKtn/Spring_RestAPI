package com.example.restapi.configs;

import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Configuration
public class FilterConfig implements Filter {


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }

        String freeUrl[] = {"/admin/login","/admin/save"};
        String url = request.getRequestURI();
        boolean loginStatus = true;
       for(String item : freeUrl){
           if(url.equals(item)){
               loginStatus = false;
               break;
           }
       }

        if(loginStatus){
            boolean status = request.getSession().getAttribute("user") == null;
            if(status){
                PrintWriter printWriter = response.getWriter();
                printWriter.println("{\n" +
                        "  \"status\": false,\n" +
                        "  \"result\": \"Please Login\"\n" +
                        "}");
                response.setContentType("application/json");
                response.setStatus(401);
            }else{
                filterChain.doFilter(request,response);
            }
        }else{
            filterChain.doFilter(request,response);
        }


    }
}
