package com.hjc.servlet;

import com.hjc.pojo.User;
import com.hjc.service.LoginService;
import com.hjc.service.impl.LoginServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Cookie信息校验
 *      判断请求中是否携带正确的Cookie信息
 *      如果有
 *          则校验Cookie信息是否正确
 *              如果校验正确则直接响应主页面给用户
 *              如果校验不正确则响应登陆页面给用户
 *       没有则请求转发给登陆页面
 */

public class CookieServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置请求编码格式
        //设置响应编码格式
        //resp.setContentType("text/html;charset=utf-8");
        //获取请求信息
            //获取Cookie信息
        Cookie[] cks=req.getCookies();
        //处理信息
        if(cks!=null){
            //遍历Cookie信息
            String uid="";
            for (Cookie c: cks){
                if("uid".equals(c.getName())){
                    uid=c.getValue();
                }
            }
            //校验uid是否存在
            if("".equals(uid)){
                //请求转发
                req.getRequestDispatcher("page").forward(req, resp);
                return;
            }else{
                //校验uid用户信息
                    //获取业务层对象
                LoginService ls=new LoginServiceImpl();
                User u=ls.checkLoginService(uid);
                if(u!=null){
                    //将用户数据存储到session对象中
                    req.getSession().setAttribute("user", u);
                    //网页计数器自增
                    int nums=Integer.parseInt((String) this.getServletContext().getAttribute("nums"));
                    nums+=1;
                    this.getServletContext().setAttribute("nums",nums);
                    //重定向
                    resp.sendRedirect("/login/main");
                    return;
                }else{
                    //请求转发
                    req.getRequestDispatcher("page").forward(req, resp);
                }
            }
        }else{
            //响应请求信息
                //直接响应
                //请求转发
            req.getRequestDispatcher("page").forward(req, resp);
                //重定向
        }

    }
}
