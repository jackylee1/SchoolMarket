/**
 *  �鿴��Ʒ����¼����
 */

package com.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.util.Calendar;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.UIManager;
import com.model.ProductModel;
import com.model.RecordModel;
import com.mytools.MyFont;
import com.mytools.Tools;
import com.sun.awt.AWTUtilities;

@SuppressWarnings("serial")
public class LookRecord_View extends JDialog implements ActionListener, MouseListener {
	
	//ȫ�ֵ�λ�ñ��������ڱ�ʾ����ڴ����ϵ�λ��
	static Point origin = new Point();
	 // ���ڻ�ô��ڵĴ�С
	final static int width=Toolkit.getDefaultToolkit().getScreenSize().width;
	final static int height=Toolkit.getDefaultToolkit().getScreenSize().height;
	
	// �������
	JPanel closejp,findpanel, showtabeljp, all;
	
	JButton close;
	
	JComboBox<String> type, startY, startM, startD, endY, endM, endD;
	
	JTable recordtable;
	JScrollPane jsp;
	
	RecordModel rm;
	String[] paras = {"1"};
	
	public void setbutton(JButton jb, int type) {
		
		if (type == 1) {
			
			jb.setContentAreaFilled(false);
		}
		
		jb.setForeground(Color.blue);
		jb.setBorderPainted(false);
		jb.setFocusPainted(false);
		jb.addMouseListener(this);
		jb.setOpaque(false);
		jb.setCursor(new Cursor(Cursor.HAND_CURSOR));
		jb.setFont(MyFont.PaddInfotext);
	}
	// ��ʼ���رհ�ť
	public void initColse() {
			
		// �رհ�ť
		close = new JButton(new ImageIcon("image/JDialogClose.png"));
		close.setRolloverIcon(new ImageIcon("image/JDialogCloseC.png"));
		close.setBounds((int)(width*0.8f)-365, 13, 22, 22);
		close.setToolTipText("�ر�");
		setbutton(close, 1);
		
		closejp.add(close);
	}
	// ��ʼ���������
	public void initfindpanel() {
			
		Vector<String> temp=new Vector<String>();
		temp.add("--��Ʒ����--");
		// �Ӳ�Ʒ���в�ѯ���
		String typesql = "select distinct Ptype from ProductInfo where 1 = ?";
		ProductModel pm = new ProductModel();
		pm.query(typesql, paras);
		// ѭ���ļ���temp��
		for (int i = 0; i < pm.getRowCount(); i++) {
			
			temp.add((String) pm.getValueAt(i, 0));
		}
		
		type = new JComboBox<String>(temp);
		type.addActionListener(this);
		JPanel typejp = new JPanel(new GridLayout(1, 1));
		typejp.setPreferredSize(new Dimension(115, 35));
		typejp.setOpaque(false);
		
		typejp.add(type);
		
		// ��ȡ��ǰϵͳ��������
		Calendar cal=Calendar.getInstance();
		int nowyears = cal.get(Calendar.YEAR);
		
		// ��ʼ��
		Vector<String> temp1=new Vector<String>();
		
		for (int i = 2000; i < nowyears+1; i++) {
			
			temp1.add(String.valueOf(i)+"��");
		}
		startY = new JComboBox<String>(temp1);
		startY.addActionListener(this);
		
		// ��ʼ��
		Vector<String> temp2=new Vector<String>();
		
		for (int i = 1; i < 13; i++) {
			
			temp2.add(String.valueOf(i)+"��");
		}
		startM = new JComboBox<String>(temp2);
		startM.addActionListener(this);

		
		// ��ʼ��
		Vector<String> temp3=new Vector<String>();
		
		for (int i = 1; i < 32; i++) {
			
			temp3.add(String.valueOf(i)+"��");
		}
		startD = new JComboBox<String>(temp3);
		startD.addActionListener(this);
		
		JPanel startjp = new JPanel(new GridLayout(1, 3));
		startjp.setPreferredSize(new Dimension(200, 35));
		startjp.setOpaque(false);
		
		startjp.add(startY);
		startjp.add(startM);
		startjp.add(startD);
		
		// ������
		Vector<String> temp4=new Vector<String>();
		
		for (int i = nowyears; i >= 2000; i--) {
			
			temp4.add(String.valueOf(i)+"��");
		}
		endY = new JComboBox<String>(temp4);
		endY.addActionListener(this);
		
		
		// ������
		Vector<String> temp5=new Vector<String>();
		
		for (int i = 12; i >= 1; i--) {
			
			temp5.add(String.valueOf(i)+"��");
		}
		endM = new JComboBox<String>(temp5);
		endM.addActionListener(this);
		
		// ������
		Vector<String> temp6=new Vector<String>();
		
		for (int i = 31; i >= 1; i--) {
			
			temp6.add(String.valueOf(i)+"��");
		}
		endD = new JComboBox<String>(temp6);
		endD.addActionListener(this);
		//endD.setSelectedIndex(nowday-1);
		
		JPanel endjp = new JPanel(new GridLayout(1, 3));
		endjp.setPreferredSize(new Dimension(200, 35));
		endjp.setOpaque(false);
		
		endjp.add(endY);
		endjp.add(endM);
		endjp.add(endD);
		
		findpanel = new JPanel();
		findpanel.setPreferredSize(new Dimension(this.getX(), 60));
		findpanel.setOpaque(false);
		
		
		findpanel.add(typejp);
		JLabel kong = new JLabel("����������");
		findpanel.add(kong);
		findpanel.add(startjp);
		JLabel to = new JLabel("��--��--��");
		findpanel.add(to);
		findpanel.add(endjp);
		
		closejp = new JPanel(null);
		closejp.setPreferredSize(new Dimension(this.getX(), 45));
		closejp.setOpaque(false);
		
		initColse();
		
		JPanel findpanelall = new JPanel(new BorderLayout());
		findpanelall.setPreferredSize(new Dimension(this.getX(), 100));
		
		findpanelall.add(closejp, "North");
		findpanelall.add(findpanel, "Center");
		findpanelall.setOpaque(false);		
		
		all.add(findpanelall, "North");
	}

	public void initshowtable() {
		
		String sql = "select a.ToId, a.Pid, b.PName, b.PType, a.Num, convert(varchar(19), a.ToDate, 120), a.ForWho from ProductToInfo a, ProductInfo b where a.Pid = b.Pid and 1 = ?";
		rm = new RecordModel();
		rm.query(sql, paras);
		
		recordtable = new JTable(rm);
		Tools.setTableStyle(recordtable);
		
		jsp = new JScrollPane(recordtable);
		Tools.setJspStyle(jsp);
		
		showtabeljp = new JPanel(new BorderLayout());
		showtabeljp.setOpaque(false);
		showtabeljp.setPreferredSize(new Dimension(this.getX(),	398));
		
		showtabeljp.add(jsp);
		
		all.add(showtabeljp, "Center");
	}
	// ���캯��
	public LookRecord_View() {
		
		// ���ÿؼ�����ʽΪ��ǰϵͳ����ʽ
		try {
			
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		
		all = new JPanel(new BorderLayout());
		all.setPreferredSize(new Dimension(560,	498));
		all.setBackground(new Color(60, 148, 212));
		all.setBorder(BorderFactory.createEtchedBorder());
		
		initfindpanel();
		initshowtable();
		
		this.add(all);
		
		this.setUndecorated(true);
		this.setSize((int)(width*0.8f)-330, (int)(height*0.8f)-200);
		this.setLocationRelativeTo(null);
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		WindowMove();
		setOpacity();
		this.setModal(true);
		this.setVisible(true);
	}

	// �����ƶ�����
	public void WindowMove() {
		
		//����û�б���Ĵ��ڿ����϶�
		this.addMouseListener(new MouseAdapter() 
		{
	        public void mousePressed(MouseEvent e)
	        {  //���£�mousePressed ���ǵ����������걻����û��̧��
	                origin.x = e.getX();  //����갴�µ�ʱ���ô��ڵ�ǰ��λ��
	                origin.y = e.getY();
	        }
		});
		this.addMouseMotionListener(new MouseMotionAdapter()
		{
	        public void mouseDragged(MouseEvent e) 
	        {  
	                Point p =getLocation();  //������϶�ʱ��ȡ���ڵ�ǰλ��
	                //���ô��ڵ�λ��
	                //���ڵ�ǰ��λ�� + ��굱ǰ�ڴ��ڵ�λ�� - ��갴�µ�ʱ���ڴ��ڵ�λ��
	                setLocation(p.x + e.getX() - origin.x, p.y + e.getY() - origin.y);
	        }
	     });
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == close) {
			
			dispose();
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
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	// ���ڵ��뵭������
	public void setOpacity() {
		
		// �������õ��뵭�������
		AWTUtilities.setWindowOpacity(this, 0f);
		ActionListener lisener = new ActionListener() {
			
			float alpha = 0;
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (alpha < 0.9) {
					
					AWTUtilities.setWindowOpacity(LookRecord_View.this, alpha+=0.1);
				}
				else {
					AWTUtilities.setWindowOpacity(LookRecord_View.this, 1);
					Timer source = (Timer) e.getSource();
					source.stop();
				}
			}
		};
		// �����߳̿���
		new Timer(50, lisener).start();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if (e.getSource() == type) {
			
			updatetable();
		}
		if (e.getSource() == startY) {
			
			updatetable();
		}
		if (e.getSource() == startM) {
			
			updatetable();
		}
		if (e.getSource() == startD) {
			
			updatetable();
		}
		if (e.getSource() == endY) {
			
			updatetable();
		}
		if (e.getSource() == endM) {
			
			updatetable();
		}
		if (e.getSource() == endD) {
			
			updatetable();
		}
	}
	// ���±����
	private void updatetable() {
		
		check();
		rm = new RecordModel();
		String[] paras = getselect();
		if (paras[0].equals("--��Ʒ����--")) {
			
			String[] newparas = {paras[1],paras[2]};
			String sql = "select a.ToId, a.Pid, b.PName, b.PType, a.Num, convert(varchar(19), a.ToDate, 120), a.ForWho from ProductToInfo a, ProductInfo b where a.Pid = b.Pid and a.ToDate between ? and ?";
			rm.query(sql, newparas);
			
		}else {
			
			String sql = "select a.ToId, a.Pid, b.PName, b.PType, a.Num, convert(varchar(19), a.ToDate, 120), a.ForWho from ProductToInfo a, ProductInfo b where a.Pid = b.Pid and b.PType = ? and a.ToDate between ? and ?";
			rm.query(sql, paras);
		}
		
		recordtable.setModel(rm);
		Tools.setTableStyle(recordtable);
	}
	// �õ�ÿ��ѡ����е�����
	private String[] getselect() {
		
		String gettype = type.getSelectedItem().toString();
		
		// �õ���ʼ����
		String getstart = getsubdate(startY)+"-"+getsubdate(startM)+"-"+getsubdate(startD);

		// �õ�����������
		String getend = getsubdate(endY)+"-"+getsubdate(endM)+"-"+getsubdate(endD);
		
		String[] tiaojian = {gettype, getstart, getend};
		return tiaojian;
	}
	// ȷ����ʼ����ҪС�ڽ���������
	private void check() {
		
		if (Integer.valueOf(getsubdate(startY)) > Integer.valueOf(getsubdate(endY))) {
			
			JOptionPane.showMessageDialog(this, "��������Ҫ������ʼ����");
			return;
		}else if (Integer.valueOf(getsubdate(startM)) > Integer.valueOf(getsubdate(endM))) {
			
			JOptionPane.showMessageDialog(this, "��������Ҫ������ʼ����");
			return;
		}else if (Integer.valueOf(getsubdate(startD)) > Integer.valueOf(getsubdate(endD))) {
			
			JOptionPane.showMessageDialog(this, "��������Ҫ������ʼ����");
			return;
		}
	}
	// �õ�ȥ�������ֵ�������
	private String getsubdate(JComboBox<String> jcb) {
		
		return jcb.getSelectedItem().toString().substring(0, jcb.getSelectedItem().toString().length()-1);
	}
}
