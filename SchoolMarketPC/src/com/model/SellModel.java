/**
 *  �����۱���в���������ģ��
 *  ���ڣ� 2013-07-28
 *  
 *  ����˵����
 *  	1.��ɶ����۱���Ϣ�Ĳ�ѯ
 *  	2.���Է���ͳ����Ҫ�ĸ�������
 */

package com.model;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import com.db.SqlHelper;

@SuppressWarnings("serial")
public class SellModel  extends AbstractTableModel{

	Vector<String> colums;// ��
	@SuppressWarnings("rawtypes")
	Vector<Vector> rows;// ��

	// ���ڲ�ѯ��Ҫ��ʾ��Ա����Ϣ
	@SuppressWarnings("rawtypes")
	public void query(String sql, String[] paras) {
		// ��ʼ���У���ÿһ�е�����
		this.colums = new Vector<String>();
		
		colums.add("���۱��");
		colums.add("��Ʒ���");
		colums.add("��Ʒ����");
		colums.add("��Ʒ�۸�");
		colums.add("��������");
		colums.add("�ϼƽ��");
		colums.add("��������");
		colums.add("��Ʒ����");
		
		this.rows = new Vector<Vector>();
		// ����sqlhelper����
		SqlHelper sh = new SqlHelper();
		ResultSet rs = sh.query(sql, paras);
		try {

			// ��rs�����п��Եõ�һ��ResultSetMetaData
			// rsmt���Եõ�����ж����У����ҿ���֪��ÿ�е����֣������ͷ����Ϣ
			ResultSetMetaData rsmt =  rs.getMetaData();
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
	
	
	// ��ѯ����������һ������ֵ
	public static boolean check(String sql, String[] paras) {
		
		boolean b = false;
		SqlHelper sh=new SqlHelper();
		try {
			
			ResultSet rs = sh.query(sql, paras);
			if(rs.next())
			{
				//�����ȥ����Ƚ�
				if (rs.getInt(1) >= 1) {
					
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
	
	// �õ���Ҫ����ֵ
	public static double find(String sql, String[] paras) {
		
		double i = 0.0;
		SqlHelper sh=new SqlHelper();
		try {
			
			ResultSet rs = sh.query(sql, paras);
			while(rs.next())
			{
				//�����ȥ����ȡ����һ��ֵ
				i = rs.getDouble(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			sh.close();
		}
		
		return i;
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
