/**
 * �Ի�Ա��Ϣ��������
 */
package com.model;

import com.db.*;

import javax.swing.table.*;
import java.util.*;
import java.sql.*;

@SuppressWarnings("serial")
public class MemberModel extends AbstractTableModel {
	
	
	Vector<String> colums;// ��
	@SuppressWarnings("rawtypes")
	Vector<Vector> rows;// ��

	// ���ڲ�ѯ��Ҫ��ʾ��Ա����Ϣ
	@SuppressWarnings("rawtypes")
	public void query(String sql, String paras[]) {
		// ��ʼ���У���ÿһ�е�����
		this.colums = new Vector<String>();
		
		colums.add("��Ա���");
		colums.add("��        ��");
		colums.add("��        ��");
		colums.add("��        ��");
		colums.add("��������");
		
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
	
	// Ա����Ϣ���£�������ɾ��
	public boolean Memberupdate(String sql, String[] paras) {
		
		boolean b = false;
		SqlHelper sh = new SqlHelper();
		b = sh.update(sql, paras);
		
		return b;
	}
	
	// ��һ���ֶ�����ѯ
	public boolean checkid(String id) {
		
		boolean b = false;
		String sql = "select count(*) from MemberInfo where Mid = ?";
		String []paras = {id};
		SqlHelper sh=new SqlHelper();
		try {
			
			ResultSet rs = sh.query(sql, paras);
			if(rs.next())
			{
				//�����ȥ����Ƚ�
				System.out.println(rs.getInt(1));
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
