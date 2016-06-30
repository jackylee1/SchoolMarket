/*
 *  ���߼��������ռ�һЩ�����ĺ������ɾ�̬����ɴ���ĸ�����
 */

package com.mytools;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.util.Enumeration;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;

public class Tools {
	
	// �ж�һ���ַ����Ƿ������֣����Ƿ����쳣���ж�
    @SuppressWarnings("unused")
	public static boolean isNum(String ch)  
    {  
        try  
        {     
            double i = Double.parseDouble(ch);
            return true;  
        }  
        catch (NumberFormatException e)  
        {  
            return false;  
        }
    }
    
    // ��������һ���ı�����ʱȫѡ
    public static void selsectAll(JTextField jlb) {
    	
    	jlb.setSelectionStart(0);
    	jlb.setSelectionColor(new Color(60, 148, 212));
    	jlb.setSelectionEnd(jlb.getText().length());
    }
    
    // ���ñ�����ʽ
    @SuppressWarnings("static-access")
	public static void setTableStyle(JTable jtb) {
    	
		//����ѡ���еı���ɫ
    	jtb.setSelectionBackground(new Color(51,153,255));
		//���ñ��ÿ�еĸ߶�
    	jtb.setRowHeight(40);
    	// ���õ����ͷ�Զ�ʵ������
    	jtb.setAutoCreateRowSorter(true);
    	// ���ñ�ͷ���־�����ʾ
		DefaultTableCellRenderer  renderer = (DefaultTableCellRenderer) jtb.getTableHeader().getDefaultRenderer();
		renderer.setHorizontalAlignment(renderer.CENTER);
		
		// ���ñ���е����ݾ�����ʾ
		DefaultTableCellRenderer r=new DefaultTableCellRenderer();
		r.setHorizontalAlignment(JLabel.CENTER);
		jtb.setDefaultRenderer(Object.class,r);
		
		jtb.setFont(new Font("������", Font.PLAIN, 15));
		fitTableColumns(jtb);
    }
    
    // ���������Զ����ڱ����п��
    @SuppressWarnings("rawtypes")
	private static void fitTableColumns(JTable myTable)
    {
         myTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
         JTableHeader header = myTable.getTableHeader();
         int rowCount = myTable.getRowCount();
         Enumeration columns = myTable.getColumnModel().getColumns();
         while(columns.hasMoreElements())
         {
             TableColumn column = (TableColumn)columns.nextElement();
             int col = header.getColumnModel().getColumnIndex(column.getIdentifier());
             int width = (int)header.getDefaultRenderer().getTableCellRendererComponent
             (myTable, column.getIdentifier(), false, false, -1, col).getPreferredSize().getWidth();
             for(int row = 0; row < rowCount; row++)
             {
                 int preferedWidth = (int)myTable.getCellRenderer(row, col).getTableCellRendererComponent
                 (myTable, myTable.getValueAt(row, col), false, false, row, col).getPreferredSize().getWidth();
                 width = Math.max(width, preferedWidth);
             }
             header.setResizingColumn(column); // ���к���Ҫ
             column.setWidth(width+myTable.getIntercellSpacing().width);
         }
    }
    
    // ���ù���������ʽ, ע�⽫����������ó�ȫ�ֵı�������Ȼ�����ˢ�²��˱��
    public static void setJspStyle(JScrollPane jsp) {
    	
		jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		jsp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		jsp.getViewport().setOpaque(false);
		jsp.getVerticalScrollBar().setOpaque(false);
    }
    
    // ��ȡϵͳ��ǰʱ��
    public static String getlocaldatetime() {
    	
        java.util.Calendar c=java.util.Calendar.getInstance();
        java.text.SimpleDateFormat f=new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
        return f.format(c.getTime());
    }
    
    // ���ñ������ʽ
    public static void setReportStyle(JFreeChart chart) {
    	
    	// ��ȡͼ���������
		CategoryPlot plot = chart.getCategoryPlot();
		plot.setBackgroundPaint(new Color(51,153,255));
		plot.setBackgroundAlpha(0.3f);
		// ˮƽ�ײ��б�
		CategoryAxis domainAxis = plot.getDomainAxis(); 
		// ���س߶���
		domainAxis.setAxisLineVisible(false);
		domainAxis.setAxisLineStroke(new BasicStroke(5));
		domainAxis.setCategoryMargin(0.6);
		// ˮƽ�ײ�����
		domainAxis.setLabelFont(new Font("������", Font.BOLD, 14)); 
		// ��ֱ����
		domainAxis.setTickLabelFont(new Font("������", Font.BOLD, 12)); 
		// ��ȡ��״,�����������εĴ�С
		ValueAxis rangeAxis = plot.getRangeAxis();
		rangeAxis.setLabelFont(new Font("������", Font.BOLD, 14));
		// ���ñ�������
		chart.getTitle().setFont(new Font("����", Font.BOLD, 22));
		
		BarRenderer redBarRenderer = (BarRenderer)plot.getRenderer();
		StandardBarPainter barpaint = new StandardBarPainter();
		// ����Ϊ��ͨ������
		redBarRenderer.setBarPainter(barpaint);
		redBarRenderer.setSeriesPaint(0, new Color(51,153,255));
		
    }
}
