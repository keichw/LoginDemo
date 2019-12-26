package com.hjc.servlet;

import com.hjc.pojo.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ShowServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置请求编码格式
        //设置响应编码格式
        resp.setContentType("text/html;charset=utf-8");
        //获取请求信息
        HttpSession hs=req.getSession();
        if(hs.getAttribute("user")==null){
            //重定向
            resp.sendRedirect("/login/ck");
            return;
        }
        User u=(User) hs.getAttribute("user");
        //处理请求信息
        //响应请求信息
            //直接响应
        resp.getWriter().write("<html>");
        resp.getWriter().write("<head>");
        resp.getWriter().write("</head>");
        resp.getWriter().write("<body>");
        resp.getWriter().write("<table border='1px'>");
        resp.getWriter().write("<tr>");
        resp.getWriter().write("<td>用户名</td>");
        resp.getWriter().write("<td>"+u.getUname()+"</td>");
        resp.getWriter().write("</tr>");
        resp.getWriter().write("<td>密码</td>");
        resp.getWriter().write("<td>"+u.getPwd()+"</td>");
        resp.getWriter().write("<tr>");
        resp.getWriter().write("</tr>");
        resp.getWriter().write("</table>");
        resp.getWriter().write("</body>");
        resp.getWriter().write("</html>");
            //请求转发
            //重定向
    }
}
