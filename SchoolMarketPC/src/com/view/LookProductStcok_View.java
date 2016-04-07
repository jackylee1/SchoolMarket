/**
 * �鿴��Ʒ�������Ľ���
 */
package com.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
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
import com.model.LookStcokModel;
import com.model.ProductModel;
import com.mytools.MyFont;
import com.mytools.Tools;
import com.sun.awt.AWTUtilities;

@SuppressWarnings("serial")
public class LookProductStcok_View extends JDialog implements ActionListener, MouseListener {
	
	//ȫ�ֵ�λ�ñ��������ڱ�ʾ����ڴ����ϵ�λ��
	static Point origin = new Point();
	 // ���ڻ�ô��ڵĴ�С
	final static int width=Toolkit.getDefaultToolkit().getScreenSize().width;
	final static int height=Toolkit.getDefaultToolkit().getScreenSize().height;
	
	// �������
	JPanel closejp,findpanel, showtabeljp, all;
	
	JButton close;
	
	JComboBox<String> type, startnum, endnum;
	
	JTable lookstcoktable;
	JScrollPane jsp;
	
	String[] paras = {"1"};
	LookStcokModel lsm;
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
					
					AWTUtilities.setWindowOpacity(LookProductStcok_View.this, alpha+=0.1);
				}
				else {
					AWTUtilities.setWindowOpacity(LookProductStcok_View.this, 1);
					Timer source = (Timer) e.getSource();
					source.stop();
				}
			}
		};
		// �����߳̿���
		new Timer(50, lisener).start();
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
		
		
		Font PaddInfotext=new Font("������",Font.PLAIN,20);
		
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
		type.setFont(new Font("������",Font.PLAIN,15));
		JPanel typejp = new JPanel(new GridLayout(1, 1));
		typejp.setPreferredSize(new Dimension(145, 40));
		typejp.setOpaque(false);
		
		typejp.add(type);
		
		// ��ʼ������
		Vector<String> temp1=new Vector<String>();
		
		for (int i = 0; i <= 38; i++) {
			
			temp1.add(String.valueOf(i));
		}
		startnum = new JComboBox<String>(temp1);
		startnum.setEditable(true);
		startnum.setFont(PaddInfotext);
		startnum.addActionListener(this);
		
		JPanel startjp = new JPanel(new GridLayout(1, 1));
		startjp.setPreferredSize(new Dimension(115, 40));
		startjp.add(startnum);
		
		// ������
		Vector<String> temp2=new Vector<String>();
		
		for (int i = 68; i >= 1; i--) {
			
			temp2.add(String.valueOf(i));
		}
		endnum = new JComboBox<String>(temp2);
		endnum.setEditable(true);
		endnum.setFont(PaddInfotext);
		endnum.addActionListener(this);
		
		JPanel endjp = new JPanel(new GridLayout(1, 1));
		endjp.setPreferredSize(new Dimension(115, 40));
		endjp.add(endnum);
		
		findpanel = new JPanel();
		findpanel.setPreferredSize(new Dimension(this.getX(), 60));
		findpanel.setOpaque(false);
		
		
		findpanel.add(typejp);
		JLabel kong = new JLabel("����");
		findpanel.add(kong);
		JLabel info = new JLabel("��Ʒ���������:");
		info.setFont(PaddInfotext);
		findpanel.add(info);
		findpanel.add(startjp);
		JLabel to = new JLabel("----");
		to.setFont(PaddInfotext);
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
		
		String sql = "select a.Pid, b.PName, b.PType, a.Num from Stcok a, ProductInfo b where a.Pid = b.Pid and 1 = ? order by a.Num";
		
		lsm = new LookStcokModel();
		lsm.query(sql, paras);
		
		lookstcoktable = new JTable(lsm);
		Tools.setTableStyle(lookstcoktable);
		
		jsp = new JScrollPane(lookstcoktable);
		Tools.setJspStyle(jsp);
		
		showtabeljp = new JPanel(new BorderLayout());
		showtabeljp.setOpaque(false);
		showtabeljp.setPreferredSize(new Dimension(this.getX(),	398));
		
		showtabeljp.add(jsp);
		
		all.add(showtabeljp, "Center");
	}
	// ���캯��
	public LookProductStcok_View() {
		
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
		//all.setBorder(new MatteBorder(2, 1, 1, 1, Color.GRAY));
		
		initfindpanel();
		initshowtable();
		
		this.add(all);
		
		this.setUndecorated(true);
		this.setSize((int)(width*0.8f)-330, (int)(height*0.8f)-200);
		this.setLocationRelativeTo(null);
		setOpacity();
		WindowMove();
		this.setModal(true);
		this.setVisible(true);
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
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if (e.getSource() == type) {
			
			updatetable();
		}
		if (e.getSource() == startnum) {
			
			updatetable();
		}
		if (e.getSource() == endnum) {
			
			updatetable();
		}
	}
	// ���±����
	private void updatetable() {
		
		check();
		String[] paras = getselect();
		lsm = new LookStcokModel();
		if (paras[0].equals("--��Ʒ����--")) {
			
			String[] newparas = {paras[1],paras[2]};
			String sql = "select a.Pid, b.PName, b.PType, a.Num from Stcok a, ProductInfo b where a.Pid = b.Pid and a.Num between ? and ? order by a.Num";
			lsm.query(sql, newparas);
			
		}else {
			
			String sql = "select a.Pid, b.PName, b.PType, a.Num from Stcok a, ProductInfo b where a.Pid = b.Pid and b.PType = ? and a.Num between ? and ? order by a.Num";
			lsm.query(sql, paras);
		}
		
		lookstcoktable.setModel(lsm);
		Tools.setTableStyle(lookstcoktable);
	}
	// �õ�ÿ��ѡ����е�����
	private String[] getselect() {
		
		// ���
		String gettype = type.getSelectedItem().toString();
		// ��ʼ����
		String getstartnum = startnum.getSelectedItem().toString();
		// ��������
		String getendnum = endnum.getSelectedItem().toString();
		
		String[] tiaojian = {gettype, getstartnum, getendnum};
		return tiaojian;
	}
	// ȷ����ʼ������ҪС�ڽ���������
	private void check() {
		
		if (Integer.valueOf(startnum.getSelectedItem().toString()) > Integer.valueOf(startnum.getSelectedItem().toString())) {
			
			JOptionPane.showMessageDialog(this, "ע�⣬��ʼ������ҪС�ڽ�����������");
			return;
		}
	}
	
}