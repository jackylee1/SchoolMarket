/**
 *  ���۱���ͳ�Ƶ�����ģ��
 *  ���ڣ� 2013-07-29
 *  
 *  ���ã�
 *  	1.���ݲ�ͬ��ͳ��������Է��ز�ͬ�����ݼ�����ɲ�ͬ����Ĵ���
 */

package com.model;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Vector;

import org.jfree.data.*;
import org.jfree.data.category.*;
import org.jfree.data.general.DatasetUtilities;

import com.db.SqlHelper;

	
public class ReportModel {
	
	// ���������õ���Ҫ�����ݼ���������ɲ�ͬ��Ҫ�������ݼ��Ĵ���
	public static CategoryDataset getsum(String titletext, String sql, String[] paras) {
		
		SqlHelper sh = new SqlHelper();

		ResultSet rs = sh.query(sql, paras);
		
		DefaultKeyedValues keyvalues = new DefaultKeyedValues();
		
		// ȡ�����
		try {
			
			ResultSetMetaData rsmt = rs.getMetaData();
			while (rs.next()) {
				
				Vector<String> temp = new Vector<String>();
				for (int i = 0; i < rsmt.getColumnCount(); i++) {
					
					temp.add(rs.getString(i + 1));
				}
				keyvalues.addValue(temp.get(0), Double.valueOf(temp.get(1)));
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			
			sh.close();
		}
		// �������ݼ�ʵ��
		CategoryDataset dateset = DatasetUtilities.createCategoryDataset(titletext, keyvalues);
		
		return dateset;
	}	
}
