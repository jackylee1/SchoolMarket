package com.SMWebSer.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.SMWebSer.Domain.CallBack;
import com.SMWebSer.Domain.User;
import com.SMWebSer.Utils.ServiceUtil;
import com.SMWebSer.service.Service;
import com.google.gson.Gson;

/**
 * Servlet implementation class RegisteServlet
 */
@WebServlet(name = "RegisteServlet",urlPatterns = "/regist.jsp")
public class RegisteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Service service;

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// TODO Auto-generated method stub
    	response.setContentType("text/html;charset = utf-8");
    	PrintWriter out = response.getWriter();
    	service = new ServiceUtil(getServletContext()).getServiceBean();
    	
    	String name = request.getParameter("name");
    	String password = request.getParameter("password");
    	CallBack callback = validRegist(name, password, "default.jpg");
    	
    	Gson gson = new Gson();
    	out.write(gson.toJson(callback));
    	System.out.println(gson.toJson(callback));
    }
    
    //�����Ƿ���û����Ѿ���ע���ˣ����з�����ע���û���
    public CallBack validRegist(String name,String password,String imageName){
    	CallBack callback = new CallBack();
    	
    	User user = new User(name,password,imageName);
    	User user_find = service.validRegist(name);
    	//�û��������ˣ��򷵻ش���Ϊ401�����û���ID
    	if(user_find != null){
    		//����ע����û�����IDת��json��ʽ
    		JSONObject jsonObject = new JSONObject();
    		try {
				jsonObject.put("user_id", user_find.getId());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		callback = new CallBack(401,"���û����ѱ�ע��",jsonObject);
    		System.out.print("���û����ѱ�ע�ᣡ");
    		return callback;
    	}else{
    		service.addUser(user);
    		callback = new CallBack(201,"ע��ɹ�",null);
    		System.out.println("ע��ɹ���");
    	}
    	return callback;
    }
}
