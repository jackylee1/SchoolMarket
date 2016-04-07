/**
 * ������Ʒ�����Ϣ�������ģ��
 * ���ڣ� 2013-07-25
 */
package com.model;

import com.db.*;
import javax.swing.table.*;
import java.util.*;
import java.sql.*;

@SuppressWarnings("serial")
public class RecordModel extends AbstractTableModel {

	Vector<String> colums;// ��
	@SuppressWarnings("rawtypes")
	Vector<Vector> rows;// ��

	// ���ڲ�ѯ��Ҫ��ʾ��Ա����Ϣ
	@SuppressWarnings("rawtypes")
	public void query(String sql, String paras[]) {
		// ��ʼ���У���ÿһ�е�����
		this.colums = new Vector<String>();
		
		colums.add("�����");
		colums.add("��Ʒ���");
		colums.add("��Ʒ����");
		colums.add("��Ʒ���");
		colums.add("�������");
		colums.add("�������");
		colums.add("��  ��  ��");
		
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
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return ((Vector) rows.get(rowIndex)).get(columnIndex);
	}
}