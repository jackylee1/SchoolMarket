/**
 *  ���������ʾ���
 *  
 *  ���ڣ�2013-07-27
 *  
 *  ʵ�ֹ���: 1.������ʾ�鿴���۵���Ϣ
 *  	    2.���۵ı���ͳ��
 */

package com.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;

import com.model.ReportModel;
import com.model.SellModel;
import com.mytools.MyFont;
import com.mytools.MySelfTabbedPane;
import com.mytools.Tools;

@SuppressWarnings("serial")
public class SellInfo extends JPanel implements ActionListener, MouseListener, FocusListener {
    
	// ���ڻ�ô��ڵĴ�С
	final static int width=Toolkit.getDefaultToolkit().getScreenSize().width;
	final static int height=Toolkit.getDefaultToolkit().getScreenSize().height;
	
	// ������ɫֵ
	Color color = new Color(22, 120, 195);
	
	// ѡ����,�����������е����
	JTabbedPane alljtp;
	
	// ÿһ��ѡ������
	JPanel SellRecord, AllYear, EveryYear;
	JComboBox<String> selectyear;
	ChartPanel chartpanel;
	
	// ��һ��ѡ���ʾ��Ϣ�����
	JPanel findproduct, showtabel, showinfo, handle;
	
	// ���Ҽ�¼�������Ŀռ�
	JLabel IdorName, type;
	JTextField getIdorName;
	JComboBox<String> gettype;
	JButton find;
	// װ����Ϣ�������
	JPanel showjp;
	JTable sellRecordtable = null;
	
	JComboBox<String> getYear, getMonth, gettypeshow;
	
	JLabel sum, min, max, average, everymonth;
	
	//����һ�����ָ�������
	Cursor myCursor=new Cursor(Cursor.HAND_CURSOR);//�������
	
	JScrollPane jsp;
	
	String[] updateparas = {"1"};
	//convert(varchar(19), a.OutDate ,120),
	String updatesql = "select a.SIid, a.Pid, b.PName, b.Price, a.Num, (b.Price*a.Num), DATE_FORMAT(a.OutDate,'%Y-%m-%d %T'), b.Ptype from SellInfo a, ProductInfo b where a.Pid = b.Pid and 1 = ?";
	SellModel psell = new SellModel();
	
	MySelfTabbedPane all;
	
	public void setbutton(JButton jb, int type) {
		
		if (type == 1) {
			
			jb.setContentAreaFilled(false);
			jb.setBorderPainted(false);
			
		}
		
		jb.setFocusPainted(false);
		jb.addMouseListener(this);
		jb.setCursor(myCursor);
		jb.setOpaque(false);
	}
	private void setlab(JLabel jlb, int i) {
		
		if (i == 1) {
			
			findproduct.add(jlb);
			Font infofont = new Font("������", Font.PLAIN, 19);
			jlb.setFont(infofont);
			jlb.setHorizontalAlignment(JLabel.CENTER);
			
		}
		if (i == 2) {
			
			Font infofont = new Font("������", Font.PLAIN, 23);
			jlb.setFont(infofont);
		}		
	}
	private void setjtf(final JTextField jtf, int i) {
		
		if (i == 1) {
			
			findproduct.add(jtf);
			MatteBorder ubderline0 = new MatteBorder(0, 1, 0, 1, color);
			jtf.setBorder(ubderline0);
		}

		jtf.setOpaque(false);
		jtf.setHorizontalAlignment(JTextField.CENTER);
		jtf.setFont(MyFont.Infotext);
	}
	// ������ݺ��·ݵ���ʽ
	private JComboBox<String> setComboBox(Vector<String> vector) {
		
		JComboBox<String> jcb = new JComboBox<String>();
		jcb = new JComboBox<String>(vector);
		jcb.setFocusable(false);
		jcb.setPreferredSize(new Dimension(90, 30));
		jcb.setFont(new Font("������", Font.PLAIN, 15));
		jcb.addActionListener(this);
		
		return jcb;
	}
	// ����һ��vector
	private Vector<String> getaVector(String sql) {
		
		Vector<String> vector = new Vector<String>();
		SellModel find = new SellModel();
		find.query(sql, updateparas);
		// ѭ���ļ���yesrs��
		for (int i = 0; i < find.getRowCount(); i++) {
			
			vector.add((String) find.getValueAt(i, 0));
		}
		
		return vector;
	}
	// ����ͳ�ƺ���
	private void Rough_Statistics() {
		
		String getyear = getYear.getSelectedItem().toString();
		String getmonth = getMonth.getSelectedItem().toString();
		
		String[] find_for_year = {getyear};
		String[] find_for_month = {getyear, getmonth};
		
		// ����������ܶ�
		String yearssql = "select sum(a.Num*b.Price) as �����۶�,DATE_FORMAT(a.OutDate, '%Y') months from SellInfo a, ProductInfo b where a.Pid = b.Pid and DATE_FORMAT(a.OutDate, '%Y') = ?  group by DATE_FORMAT(a.OutDate, '%Y')";
		double sum = SellModel.find(yearssql, find_for_year);
		
		// �����������Сֵ
		String minsql = "select * from (select DATE_FORMAT(a.OutDate, '%M') as whichmonth,sum(a.Num*b.Price) as months from SellInfo a, ProductInfo b where a.Pid = b.Pid and DATE_FORMAT(a.OutDate, '%Y') = ?  group by DATE_FORMAT(a.OutDate, '%M')) as temp order by temp.months";
		String min = String.valueOf(SellModel.find(minsql, find_for_year));
		
		// ������������ֵ
		String maxsql = "select * from (select DATE_FORMAT(a.OutDate, '%M') as whichmonth,sum(a.Num*b.Price) as months from SellInfo a, ProductInfo b where a.Pid = b.Pid and DATE_FORMAT(a.OutDate, '%Y') = ?  group by DATE_FORMAT(a.OutDate, '%M')) as temp order by temp.months desc";
		String max = String.valueOf(SellModel.find(maxsql, find_for_year));
		
		// ���ָ���µ����۶�
		String everysql = "select * from (select sum(a.Num*b.Price) as months, DATE_FORMAT(a.OutDate, '%M') as whichmonth from SellInfo a, ProductInfo b where a.Pid = b.Pid and DATE_FORMAT(a.OutDate, '%Y') = ? and DATE_FORMAT(a.OutDate, '%M') = ?  group by DATE_FORMAT(a.OutDate, '%M')) as temp";
		double onemonth = SellModel.find(everysql, find_for_month);
		
		// ˫��������С����
		DecimalFormat df = new DecimalFormat( "0.00");
		// ��ʾ��ͬ��ֵ
		this.sum.setText(df.format(sum));
		this.min.setText(min.substring(0, min.length()-2)+"��");
		this.max.setText(max.substring(0, min.length()-2)+"��");
		this.average.setText(df.format(sum/12));
		
		// ѡ��ͬ���·ݽ�����ʾ,���û�������ʾ0.0
		this.everymonth.setText(df.format(onemonth));
	}
	// ����ͳ������ʼ��
	private void initHand() {
		
		// ���۴��������ʾ���
		handle = new JPanel(new BorderLayout());
		handle.setPreferredSize(new Dimension((int)(width*0.8), 90));
		handle.setBackground(new Color(186, 231, 253));
		
		JPanel sellYinfo = new JPanel(new FlowLayout(FlowLayout.LEFT));
		sellYinfo.setOpaque(false);
		sellYinfo.setPreferredSize(new Dimension((int)(width*0.8)-120, 45));
		
		// �õ�һ����ݵ�vector
		Vector<String> yesrs= getaVector("select distinct DATE_FORMAT(OutDate, '%Y') from SellInfo where 1 = ? order by DATE_FORMAT(OutDate, '%Y') desc");
	
		getYear = setComboBox(yesrs);
		
		sum = new JLabel("30000");
		setlab(sum, 2);
		
		min = new JLabel("3000");
		setlab(min, 2);
		
		max = new JLabel("5000");
		setlab(max, 2);
		
		average = new JLabel(String.valueOf(30000/12));
		setlab(average, 2);
		
		sellYinfo.add(new JLabel("<html><font style = 'font-size:16'>&nbsp&nbsp"));
		sellYinfo.add(getYear);
		sellYinfo.add(new JLabel("<html><font style = 'font-size:17'>&nbsp�������ܶ"));
		sellYinfo.add(sum);
		sellYinfo.add(new JLabel("<html><font style = 'font-size:17'>&nbsp��С���۶��·ݣ�"));
		sellYinfo.add(min);
		sellYinfo.add(new JLabel("<html><font style = 'font-size:17'>&nbsp������۶��·ݣ�"));
		sellYinfo.add(max);
		sellYinfo.add(new JLabel("<html><font style = 'font-size:17'>&nbsp��ƽ�����۶"));
		sellYinfo.add(average);
		
		JPanel sellMinfo = new JPanel(new FlowLayout(FlowLayout.LEFT));
		sellMinfo.setOpaque(false);
		sellMinfo.setPreferredSize(new Dimension((int)(width*0.8)-130, 45));
		
		// �õ�һ���·ݵ�vector
		Vector<String> month = getaVector("select distinct DATE_FORMAT(OutDate, '%M') from SellInfo where 1 = ?");
		
		getMonth = setComboBox(month);
		
		everymonth = new JLabel("3000");
		setlab(everymonth, 2);
		
		Rough_Statistics();
		
		sellMinfo.add(new JLabel("<html><font style = 'font-size:16'>&nbsp&nbsp"));
		sellMinfo.add(getMonth);
		sellMinfo.add(new JLabel("<html><font style = 'font-size:17'>&nbsp�������ܶ"));
		sellMinfo.add(everymonth);
		
		JPanel danwei = new JPanel(new FlowLayout(FlowLayout.LEFT));
		danwei.setOpaque(false);
		danwei.setPreferredSize(new Dimension(130, 90));
		danwei.add(new JLabel("<html><font style = 'font-size:18'>��λ��Ԫ"));
		
		handle.add(sellYinfo, "North");
		handle.add(sellMinfo, "Center");
		handle.add(danwei, "East");
	}
	
	// ��ʼ�����ۼ�¼���
	private void initSellRecord() {
		
		// �������
		findproduct = new JPanel(new GridLayout(1, 4));
		findproduct.setBackground(new Color(186, 231, 253));
		findproduct.setPreferredSize(new Dimension((int)(width*0.8)-255, 80));
		IdorName = new JLabel("<html>��Ʒ��Ż��Ʒ����<br/>&nbsp(�����ִ�Сд)");
		setlab(IdorName, 1);
		
		getIdorName = new JTextField(10);
		getIdorName.addFocusListener(this);
		setjtf(getIdorName, 1);
		
		find = new JButton(new ImageIcon("image/find.png"));
		setbutton(find, 1);
		findproduct.add(find);
		
		type = new JLabel("������Ʒ����");
		type.setFont(MyFont.Infolab);
		setlab(type, 1);
		Vector<String> temp=new Vector<String>();
		temp.add("--���в�Ʒ--");
		// �Ӳ�Ʒ���в�ѯ���
		String typesql = "select distinct Ptype from ProductInfo where 1 = ?";
		psell.query(typesql, updateparas);
		// ѭ���ļ���temp��
		for (int i = 0; i < psell.getRowCount(); i++) {
			
			temp.add((String) psell.getValueAt(i, 0));
		}
		
		gettype = new JComboBox<String>(temp);
		gettype.setFocusable(false);
		gettype.setOpaque(false);
		gettype.setBounds(0, 20, 150, 40);
		gettype.setBackground(Color.white);
		gettype.setFont(new Font("������",Font.PLAIN,15));
		
		gettype.addActionListener(this);
		
		JPanel jtype = new JPanel(null);
		jtype.setOpaque(false);
		jtype.add(gettype);
		
		findproduct.add(jtype);
		
		// ��Ʊ��
		psell.query(updatesql, updateparas);
		sellRecordtable = new JTable(psell);
		
		// ���ù���Tools���е����ñ����ʽ����
		Tools.setTableStyle(sellRecordtable);
		sellRecordtable.addMouseListener(this);
		sellRecordtable.setOpaque(false);
		
		// �������
		jsp=new JScrollPane(sellRecordtable);
		jsp.setBorder(new MatteBorder(1, 0, 1, 0, color));
		Tools.setJspStyle(jsp);
		
		showtabel = new JPanel(new BorderLayout());
		showtabel.setOpaque(false);
		// �������Ĵ�С
		showtabel.setPreferredSize(new Dimension((int)(width*0.8), (int)(height*0.8)-155));
		
		showtabel.add(jsp, "Center");		
		
		// ���ó�ʼ��ͳ����庯��
		initHand();
		
		showjp = new JPanel(new BorderLayout());
		showjp.setOpaque(false);
		// �����������ı߿�
		showjp.setBorder(new MatteBorder(0, 0, 0, 0, color));
		showjp.add(findproduct, "North");
		showjp.add(showtabel, "Center");
		showjp.add(handle, "South");		
		
		
		SellRecord = new JPanel(new BorderLayout());
		
		SellRecord.add(showjp, "Center");
		
		alljtp.add(SellRecord, "��Ʒ���ۼ�¼");
	}
	
	// ��ʼ����������ͳ�����
	private void initAllYears() {
		
		// �õ�һ����ݵ�vector
		Vector<String> yesrs= getaVector("select distinct DATE_FORMAT(OutDate, '%Y') from SellInfo where 1 = ? order by DATE_FORMAT(OutDate, '%Y')");
		// ��������ͼͳ�����
		
		// 1.�õ����ݼ�
		String getyearssql = "select DATE_FORMAT(a.OutDate, '%Y') ���, sum(a.Num*b.Price) �������ܶ� from SellInfo a, ProductInfo b where a.Pid = b.Pid and 1 = ? group by DATE_FORMAT(a.OutDate, '%Y')";
		CategoryDataset dateset =  ReportModel.getsum("��������۶�",getyearssql, updateparas);
		// 2.����JfreeChart����
		JFreeChart chart = ChartFactory.createBarChart(yesrs.get(0)+"�ꡪ"+yesrs.get(yesrs.size()-1)+"��"+"�������ͳ��(��λ:Ԫ)", // ͼ�����
				"", // X���ǩ
				"���۶�",  // Y���ǩ
				dateset, // ���ݼ�
				PlotOrientation.VERTICAL, // ͼ����
				false, // �Ƿ���ʾͼ��(���ڼ򵥵���״ͼ������false)
				true, // �Ƿ����ɹ���
				false // �Ƿ�����URL����
				);

		// 3.��JfreeChart���󴴽�һ��ChartPanel���
		ChartPanel chartpanel = new ChartPanel(chart);
		chartpanel.setOpaque(false);
		chartpanel.setPreferredSize(new Dimension((int)(width*0.8)-60, (int)(height*0.8)-240));
		// ������ʽ
		Tools.setReportStyle(chart);
		
		
		AllYear = new JPanel(new BorderLayout());
		AllYear.setOpaque(false);
		
		JPanel top = new JPanel();
		top.setPreferredSize(new Dimension((int)(width*0.8), 20));
		top.setBackground(Color.white);
		
		JPanel up = new JPanel();
		up.setPreferredSize(new Dimension((int)(width*0.8), 40));
		up.add(new JLabel("<html><font size = '5'>���(ָ�������ۼ�¼�����)<br/><br/>"));
		up.setBackground(Color.white);
		
		JPanel left = new JPanel();
		left.setPreferredSize(new Dimension(30, (int)(height*0.8)));
		left.setBackground(Color.white);
		
		JPanel right = new JPanel();
		right.setPreferredSize(new Dimension(30, (int)(height*0.8)));
		right.setBackground(Color.white);
		
		AllYear.add(top, "North");
		AllYear.add(chartpanel);
		AllYear.add(left, "East");
		AllYear.add(right, "West");
		AllYear.add(up, "South");
		
		alljtp.add(AllYear, "��������ͳ��");
	}
	// ����һ��ָ����ݵ���ͳ�Ʊ������
	private void getchart_forYear() {
		
		
		// ��������ͼͳ�����
		
		// 1.�õ����ݼ�
		String[] monthparas = {selectyear.getSelectedItem().toString()};
		String getmonthsql = "select DATE_FORMAT(a.OutDate, '%M') �·�, sum(a.Num*b.Price) �������ܶ� from SellInfo a, ProductInfo b where a.Pid = b.Pid and DATE_FORMAT(a.OutDate, '%Y') = ? group by DATE_FORMAT(a.OutDate, '%M')";
		CategoryDataset dateset =  ReportModel.getsum("�¾������۶�",getmonthsql, monthparas);
		// 2.����JfreeChart����
		JFreeChart chart = ChartFactory.createBarChart("", // ͼ�����
				"", // X���ǩ
				"���۶�",  // Y���ǩ
				dateset, // ���ݼ�
				PlotOrientation.VERTICAL, // ͼ����
				false, // �Ƿ���ʾͼ��(���ڼ򵥵���״ͼ������false)
				true, // �Ƿ����ɹ���
				false // �Ƿ�����URL����
				);

		
		// 3.��JfreeChart���󴴽�һ��ChartPanel���
		chartpanel = new ChartPanel(chart);
		chartpanel.setOpaque(false);
		chartpanel.setPreferredSize(new Dimension((int)(width*0.8)-60, (int)(height*0.8)-240));
		// ������ʽ
		Tools.setReportStyle(chart);
		
		EveryYear.add(chartpanel);
	}
	// ��ʼ��ÿ������ͳ�����
	private void initEveryYear() {
		
		
		// �õ�һ����ݵ�vector
		Vector<String> yesrs= getaVector("select distinct DATE_FORMAT(OutDate, '%Y') from SellInfo where 1 = ?");
	
		this.selectyear = setComboBox(yesrs);
		
		EveryYear = new JPanel(new BorderLayout());
		EveryYear.setOpaque(false);
		
		JPanel top = new JPanel();
		top.setPreferredSize(new Dimension((int)(width*0.8), 40));
		top.add(selectyear);
		top.add(new JLabel("<html><font size = '6'>��&nbsp&nbspÿ���������ͳ��"));
		top.setBackground(Color.white);
		
		getchart_forYear();
		
		JPanel up = new JPanel();
		up.setPreferredSize(new Dimension((int)(width*0.8), 40));
		up.add(new JLabel("<html><font size = '5'>�·�(ָ�������ۼ�¼���·�)<br/><br/>", JLabel.CENTER));
		up.setBackground(Color.white);
		
		JPanel left = new JPanel();
		left.setPreferredSize(new Dimension(30, (int)(height*0.8)));
		left.setBackground(Color.white);
		
		JPanel right = new JPanel();
		right.setPreferredSize(new Dimension(30, (int)(height*0.8)));
		right.setBackground(Color.white);
		
		
		EveryYear.add(top, "North");
		
		EveryYear.add(left, "East");
		EveryYear.add(right, "West");
		EveryYear.add(up, "South");
		
		alljtp.add(EveryYear, "ÿ������ͳ��");
	}
	public SellInfo() {
		
		// ���ô������ʽΪ��ǰϵͳ����ʽ
		try {
			
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	    UIManager.getLookAndFeelDefaults().put("defaultFont", new Font("΢���ź�", Font.PLAIN, 12));
		
		alljtp = new JTabbedPane();
		
		// ��ʼ��ÿһ��ѡ����
		initSellRecord();
		initAllYears();
		initEveryYear();
							
		alljtp.setFont(new Font("������", Font.PLAIN, 16));
		alljtp.setBorder(new MatteBorder(0, 2, 2, 2, color));
		
		MySelfTabbedPane ui = new MySelfTabbedPane();
		
		alljtp.setUI(ui);
		alljtp.setOpaque(false);
		
		this.setLayout(new BorderLayout());
		this.add(alljtp);
		this.setOpaque(false);
		this.setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == find) {
			
			findsellrecord();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == find) {
			
			find.setIcon(new ImageIcon("image/findC.png"));
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == find) {
			
			find.setIcon(new ImageIcon("image/find.png"));
		}
	}
	
	// ���±��ģ�ͺ������������Ҫ�����ǵ���
	public void updatetable(String sql, String[] paras) {
		
		psell = new SellModel();
		psell.query(sql, paras);
		sellRecordtable.setModel(psell);
		Tools.setTableStyle(sellRecordtable);
	}
	
	// ��ѯ����
	private void findsellrecord() {
		
		// �õ����ҵ�����
		String tiaojian = getIdorName.getText().trim();
		
		while (tiaojian.isEmpty()) {
			
			JOptionPane.showMessageDialog(this, "������Ҫ���ҵ�����");
			return;
		}
		
		String[] tiaojianparas = {tiaojian, tiaojian};
		
		if (gettype.getSelectedItem().equals("--���в�Ʒ--")) {
			
			// ��SellInfo ProductInfo��������ϲ�ѯ
	 		if (!SellModel.check("select count(*) from SellInfo a, ProductInfo b where a.Pid = b.Pid and (a.Pid like '%'+?+'%' or b.PName like '%'+?+'%')", tiaojianparas)) {
				
				JOptionPane.showMessageDialog(this, "��Ǹ��û����ز�Ʒ�����ۼ�¼");
	 		}	
	 		
	 		updatetable("select a.SIId, a.Pid, b.PName, b.Price, a.Num, (b.Price*a.Num), DATE_FORMAT(a.OutDate,'%Y-%m-%d %T'), b.Ptype from SellInfo a, ProductInfo b where a.Pid = b.Pid and (a.Pid like '%'+?+'%' or b.PName like '%'+?+'%')", tiaojianparas);
			
		} else {
			
			String[] newtiaojianparas = {tiaojian, tiaojian, gettype.getSelectedItem().toString()};
			
			// ��SellInfo ProductInfo��������ϲ�ѯ
	 		if (!SellModel.check("select count(*) from SellInfo a, ProductInfo b where a.Pid = b.Pid" +
	 				" and (a.Pid like '%'+?+'%' or b.PName like '%'+?+'%') and b.Ptype = ?", newtiaojianparas)) {
				
	 			JOptionPane.showMessageDialog(this, "<html><br/><font size = '5'>�ڲ�Ʒ���<font color = 'red'>"+gettype.getSelectedItem()+"</font>��<br/>û���ҵ��룺<font color = 'red'>"+tiaojian+"</font>&nbsp&nbsp��ص����ۼ�¼</font><br/><br/>");
	 		}
			updatetable("select a.SIId, a.Pid, b.PName, b.Price, a.Num, (b.Price*a.Num), DATE_FORMAT(a.OutDate,'%Y-%m-%d %T'), b.Ptype " +
						"from SellInfo a, ProductInfo b where a.Pid = b.Pid and (a.Pid like '%'+?+'%' or b.PName like '%'+?+'%') " +
						"and b.Ptype = ?", newtiaojianparas);
		}

	}
	// �鿴ֻ��ָ����ݵ����ۼ�¼
	private void look_for_year() {
		
		String getyear = this.getYear.getSelectedItem().toString();

		String gettype = this.gettype.getSelectedItem().toString();
		
		String[] look_more = {getyear};
		String[] look_more_withtype = {getyear, gettype};
		
		if (gettype.equals("--���в�Ʒ--")) {
			
				
	 		updatetable("select a.SIId, a.Pid, b.PName, b.Price, a.Num, (b.Price*a.Num), DATE_FORMAT(a.OutDate,'%Y-%m-%d %T'), b.Ptype from SellInfo a, ProductInfo b where a.Pid = b.Pid and DATE_FORMAT(a.OutDate, '%Y') = ? ", look_more);
	 		
			
		}else {
			
			// ��SellInfo ProductInfo��������ϲ�ѯ
	 		if (!SellModel.check("select count(*) from SellInfo a, ProductInfo b where a.Pid = b.Pid" +
	 				" and DATE_FORMAT(OutDate, '%Y') = ? and b.Ptype = ?", look_more_withtype)) {
				
	 			JOptionPane.showMessageDialog(this, "<html><br/><font size = '4'><font color = 'red'>"+getyear+"��"+"&nbsp&nbsp</font><font color = 'red'>"+gettype+"</font>���"+"&nbsp&nbspû�����ۼ�¼</font><br/>");
	 			
				
			}
				
			updatetable("select a.SIId, a.Pid, b.PName, b.Price, a.Num, (b.Price*a.Num), DATE_FORMAT(a.OutDate,'%Y-%m-%d %T'), b.Ptype " +
						"from SellInfo a, ProductInfo b where a.Pid = b.Pid " +
						"and DATE_FORMAT(OutDate, '%Y') = ? and b.Ptype = ?", look_more_withtype);
			
		}
	}
	// �鿴ָ�����µ����ۼ�¼
	private void look_more_sellrecord() {
		
		String getyear = this.getYear.getSelectedItem().toString();
		String getmonth = this.getMonth.getSelectedItem().toString();
		String gettype = this.gettype.getSelectedItem().toString();
		
		String[] look_more = {getyear, getmonth};
		String[] look_more_withtype = {getyear, getmonth, gettype};
		
		if (gettype.equals("--���в�Ʒ--")) {
			
			// ������Ʒ���ĸ���
	 		if (!SellModel.check("select count(*) from SellInfo  where DATE_FORMAT(OutDate, '%Y') = ? and DATE_FORMAT(OutDate, '%M') = ?", look_more)) {
				
				JOptionPane.showMessageDialog(this, "<html><br/><font color = 'red'>"+getyear+"��"+getmonth+"��"+"</font>&nbsp&nbspû�����ۼ�¼<br/>");	
			}
				
	 		updatetable("select a.SIId, a.Pid, b.PName, b.Price, a.Num, (b.Price*a.Num), DATE_FORMAT(a.OutDate,'%Y-%m-%d %T'), b.Ptype from SellInfo a, ProductInfo b where a.Pid = b.Pid and DATE_FORMAT(a.OutDate, '%Y') = ? and DATE_FORMAT(a.OutDate, '%M') = ?", look_more);
	 		
		}else {
			
			// ��SellInfo ProductInfo��������ϲ�ѯ
	 		if (!SellModel.check("select count(*) from SellInfo a, ProductInfo b where a.Pid = b.Pid" +
	 				" and DATE_FORMAT(OutDate, '%Y') = ? and DATE_FORMAT(OutDate, '%M') = ? and b.Ptype = ?", look_more_withtype)) {
				
	 			JOptionPane.showMessageDialog(this, "<html><br/><font size = '4'><font color = 'red'>"+getyear+"��"+getmonth+"��</font><font color = 'red'>"+gettype+"</font>���"+"&nbsp&nbspû�����ۼ�¼</font>");
	 			
				
			}
				
			updatetable("select a.SIId, a.Pid, b.PName, b.Price, a.Num, (b.Price*a.Num), DATE_FORMAT(a.OutDate,'%Y-%m-%d %T'), b.Ptype " +
						"from SellInfo a, ProductInfo b where a.Pid = b.Pid " +
						"and DATE_FORMAT(OutDate, '%M') = ? and DATE_FORMAT(OutDate, '%M') = ? and b.Ptype = ?", look_more_withtype);
			
		}
	}
	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		Component comp =e.getComponent();
		if (comp instanceof JTextField) {
			
			Tools.selsectAll(getIdorName);
		}
	}
	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
		Component comp =e.getComponent();
		if (comp instanceof JTextField) {
			
			
		}
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == this.gettype) {
			
			if (getIdorName.getText().trim().isEmpty()) {
				
				if (gettype.getSelectedItem().equals("--���в�Ʒ--")) {
					
					updatetable(updatesql, updateparas);
					
				}else {
					
					String[] parasnew={gettype.getSelectedItem().toString()};
					String sql = "select a.SIId, a.Pid, b.PName, b.Price, a.Num, (b.Price*a.Num), DATE_FORMAT(a.OutDate,'%Y-%m-%d %T'), b.Ptype from SellInfo a, ProductInfo b where a.Pid = b.Pid and b.Ptype = ?";
					updatetable(sql, parasnew);
				}
				
			} else {
				
				findsellrecord();
			}
		}
		
		if (e.getSource() == this.getYear) {
			
			this.Rough_Statistics();
			look_for_year();
		}
		if (e.getSource() == this.getMonth) {
			
			this.Rough_Statistics();
			look_more_sellrecord();
		}
		
		if (e.getSource() == this.selectyear) {
			
			this.EveryYear.remove(chartpanel);
			getchart_forYear();
		}
	}
}
