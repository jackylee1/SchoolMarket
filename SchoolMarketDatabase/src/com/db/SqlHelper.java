package com.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SqlHelper {
	// ������Ҫ�Ķ���
	Connection ct = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	// �������ݿ���Ҫ���ַ���
	String driver = "com.mysql.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/myshopdb";
	String user = "root";
	String passwd = "1314520";
	
	// ���캯������ʼ��ct
		public SqlHelper() {
			try {
				// ��������
				Class.forName(driver);
				// �õ�����
				ct = DriverManager.getConnection(url, user, passwd);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("����û�м��سɹ�,ԭ����û�е�������������");
			} catch (NullPointerException e) {
				// TODO Auto-generated catch bloc
				e.printStackTrace();
				System.out.println("���ݿ����û�п�����������ݿ����������");
			} catch (SQLException e) {
				// TODO Auto-generated catch bloc
				e.printStackTrace();
			} 
		}
		
		// �ر���Դ�ķ���
		public void close() {
			try {
				if (rs != null) {
					rs.close();
				}

				if (ps != null) {
					ps.close();
				}

				if (ct != null) {
					ct.close();
				}

			} catch (Exception e) {

				e.printStackTrace();
			}
		}
		
		// []paras��ͨ��?��ֵ��ʽ���Է�ֹ©��ע�뷽ʽ����֤��ȫ��
		public ResultSet query(String sql, String[] paras) {
			try {
				ps = ct.prepareStatement(sql);
				// ��sql�Ĳ�����ֵ
				for (int i = 0; i < paras.length; i++) {
					ps.setString(i + 1, paras[i]);
				}
				// ִ�в�ѯ
				rs = ps.executeQuery();
			} catch (Exception e) {
				e.printStackTrace();
			}
			// ���ؽ����
			return rs;
		}
		
		// ��ɾ�ķ���
		public boolean update(String sql, String[] paras) {
			
			boolean b = true;
			try {

				ps = ct.prepareStatement(sql);
				// ѭ���Ķ�paras��ֵ������ֵ��
				for (int i = 0; i < paras.length; i++) {
					ps.setString(i+1, paras[i]);
				}
				// ִ�в���
				ps.execute();
				
			} catch (Exception e) {
				// TODO:  handle exception
				e.printStackTrace();
				System.out.println("SqlHelper����ɾ���г����������飡");
				b = false;
			}
			return b;
		} 
}
