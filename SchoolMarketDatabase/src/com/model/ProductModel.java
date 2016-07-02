package com.model;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import com.db.SqlHelper;
@SuppressWarnings("serial")
public class ProductModel extends AbstractTableModel{
	Vector<String> colums;// ÁÐ
	@SuppressWarnings("rawtypes")
	Vector<Vector> rows;// ÐÐ

public Object getValueAt(int rowIndex, int columnIndex) {
			// TODO Auto-generated method stub
			return ((Vector) rows.get(rowIndex)).get(columnIndex);
		}

}
