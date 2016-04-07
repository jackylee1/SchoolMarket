/**
 * ���տ�������������ģ��
 */
package com.model;

import com.db.*;

import javax.swing.table.*;

import java.util.*;
import java.sql.*;

@SuppressWarnings("serial")
public class ShowKuanModel extends AbstractTableModel {

	Vector<String> colums;// ��
	@SuppressWarnings("rawtypes")
	Vector<Vector> rows;// ��

	// ���ڲ�ѯ��Ҫ��ʾ��Ա����Ϣ
	@SuppressWarnings("rawtypes")
	public void query(String sql, String[] paras) {
		// ��ʼ���У���ÿһ�е�����
		this.colums = new Vector<String>();
		
		colums.add("��Ʒ���");
		colums.add("��Ʒ����");
		colums.add("��        ��");
		colums.add("��������");
		colums.add("�ϼƽ��");
		
		this.rows = new Vector<Vector>();
		// ����sqlhelper����
		SqlHelper sh = new SqlHelper();
		ResultSet rs = sh.query(sql, paras);
		try {

			// ��rs�����п��Եõ�һ��ResultSetMetaData
			// rsmt���Եĵ�����ж����У����ҿ���֪��ÿ�е����֣������ͷ����Ϣ
			ResultSetMetaData rsmt = rs.getMetaData();
//			for (int i = 0; i < rsmt.getColumnCount(); i++) {
//				this.colums.add(rsmt.getColumnName(i + 1));
//			}
			// ��rs�Ľ�����뵽rows
			while (rs.next()) {

				Vector<String> temp = new Vector<String>();
				for (int i = 0; i < rsmt.getColumnCount(); i++) {
					temp.add(rs.getString(i + 1));
				}
				rows.add(temp);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			sh.close();
		}
		
	}
	
	// ��Ϣ���£�������ɾ��
	static public boolean update(String sql, String[] pid) {
		
		boolean b = false;
		SqlHelper sh = new SqlHelper();
		b = sh.update(sql, pid);
		
		return b;
	}
	
	// �ò�Ʒid����Ʒ�Ƿ����
	public static boolean check(String sql,String pid) {
		
		boolean b = false;
		String[] paras = {pid};
		SqlHelper sh=new SqlHelper();
		try {
			
			ResultSet rs = sh.query(sql, paras);
			if(rs.next())
			{
				//�����ȥ����Ƚ�
				if (rs.getInt(1) == 1) {
					
					b = true;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			sh.close();
		}
		return b;
	}
	
	// ���ؿ��
	public static int get_p_num(String pid) {
		
		int num = 0;
		
		String[] paras = {pid};
		String sql = "select sum(Num) from Stcok where Pid = ";
		SqlHelper sh=new SqlHelper();
		try {
			
			ResultSet rs = sh.query(sql, paras);
			if(rs.next())
			{
				num = rs.getInt(1);
			}			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			sh.close();
		}
		
		return num;
	}
	
	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return this.colums.get(column);
	}

	public int getRowCount() {
		// TODO Auto-generated method stub
		return this.rows.size();
	}

	public int getColumnCount() {
		// TODO Auto-generated method stub
		return this.colums.size();
	}

	@SuppressWarnings("rawtypes")
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return ((Vector) rows.get(rowIndex)).get(columnIndex);
	}
}
