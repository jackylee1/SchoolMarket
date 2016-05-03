package com.SMWebSer.dao;

import com.SMWebSer.Domain.User;
import com.SMWebSer.common.dao.BaseDao;

public interface UserDao extends BaseDao<User>{
	// �����û�����������û�
	User findUserByNameAndPass(String name, String password);

	// ����name����user����
	void UpdateUser(String name, String imageName);
}
