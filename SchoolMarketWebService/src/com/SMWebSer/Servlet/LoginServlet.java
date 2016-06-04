package com.SMWebSer.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.SMWebSer.Domain.CallBack;
import com.SMWebSer.Domain.User;
import com.SMWebSer.Utils.ServiceUtil;
import com.SMWebSer.service.Service;
import com.google.gson.Gson;

import sun.misc.Cache;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(name = "LoginServlet",urlPatterns = "/login.jsp")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Service service;
       
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setContentType("text/html;charset=utf-8");
    	PrintWriter out = response.getWriter();
    	service = new ServiceUtil(getServletContext()).getServiceBean();
    	
    	//��ȡ�����name,password������User����
    	String name = request.getParameter("name");
    	String password = request.getParameter("password");
    	CallBack callback = validLogin(name, password);
    	Gson gson = new Gson();
    	out.write(gson.toJson(callback));
    }

    public CallBack validLogin(String name,String password){
    	CallBack callback = new CallBack();
    	User user = service.validLogin(name, password);
    	if(user == null){
    		callback = new CallBack(400, "�û������������", null);
    		System.out.println("��½ʧ�ܣ�");
    	}else{
    		callback = new CallBack(200, "��¼�ɹ���", null);
    		System.out.println("��½�ɹ���");
    	}
    	return callback;
    }
}
