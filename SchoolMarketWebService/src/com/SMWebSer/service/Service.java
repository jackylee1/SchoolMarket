package com.SMWebSer.service;

import java.util.List;

import com.SMWebSer.Domain.User;

public interface Service {

	//����û�
	int addUser(User user);

	//�������е��û�
	List<User> getAllusers();
	
	//ɾ���û�
	void deleteUser(int id);
	
	//��½ʱ��������֤
	User validLogin(String name,String password);
	
	//�����û�ͷ��Ĳ���
	void UpdateUser(String name,String imageName);
}
