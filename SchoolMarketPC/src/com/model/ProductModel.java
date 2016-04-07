package com.model;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import com.db.SqlHelper;

@SuppressWarnings("serial")
public class ProductModel extends AbstractTableModel {
	
	Vector<String> colums;// ��
	@SuppressWarnings("rawtypes")
	Vector<Vector> rows;// ��

	// ���ڲ�ѯ��Ҫ��ʾ��Ա����Ϣ
	@SuppressWarnings("rawtypes")
	public void query(String sql, String paras[]) {
		// ��ʼ���У���ÿһ�е�����
		this.colums = new Vector<String>();
		
		colums.add("��Ʒ���");
		colums.add("��Ʒ����");
		colums.add("��        ��");
		colums.add("��Ʒ����");
		colums.add("��Ʒ����");
		
		this.rows = new Vector<Vector>();
		// ����sqlhelper����
		SqlHelper sh = new SqlHelper();
		ResultSet rs = sh.query(sql, paras);
		try {

			// ��rs�����п��Եõ�һ��ResultSetMetaData
			// rsmt���Եĵ�����ж����У����ҿ���֪��ÿ�е����֣������ͷ����Ϣ
			ResultSetMetaData rsmt = rs.getMetaData();
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
	static public boolean update(String sql, String[] paras) {
		
		boolean b = false;
		SqlHelper sh = new SqlHelper();
		b = sh.update(sql, paras);
		
		return b;
	}
	
	// ��һ���ֶ�����ѯ
	public static boolean check(String sql, String tiaojian) {
		
		boolean b = false;
		String []paras = {tiaojian};
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
	
	// ��һ���ֶ�����ѯ
	public static boolean checknum(String sql, String[] paras) {
		
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
	
	// �õ���Ʒ��id
	public static String getpid(String tiaojian) {
		
		String pid = null;
		String []paras = {tiaojian};
		String sql = "select Pid from ProductInfo where Pid = ?";
		SqlHelper sh=new SqlHelper();
		try {
			
			ResultSet rs = sh.query(sql, paras);
			if(rs.next())
			{
				pid = rs.getString(1);	
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			sh.close();
		}
		
		return pid;
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
