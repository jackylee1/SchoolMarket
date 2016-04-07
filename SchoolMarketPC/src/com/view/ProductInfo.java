/**
 *  ��Ʒ��Ϣ�������
 */

package com.view;

import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import com.mytools.*;
import com.model.*;

@SuppressWarnings("serial")
public class ProductInfo extends JPanel implements ActionListener, MouseListener, FocusListener {
    // ���ڻ�ô��ڵĴ�С
	final static int width=Toolkit.getDefaultToolkit().getScreenSize().width;
	final static int height=Toolkit.getDefaultToolkit().getScreenSize().height;
	// ������ɫֵ
	Color color = new Color(22, 120, 195);
	// ��ʾ��Ϣ�����
	JPanel findproduct, showtabel, showinfo, handle;
	// ��Ӳ�Ʒ��Ϣ�����ؼ�
	JLabel IdorName, type;
	JTextField getIdorName;
	JComboBox<String> gettype;
	JButton find;
	// װ����Ϣ�������
	JPanel showjp;
	JTable producttable = null;
	
	JButton add, modify, delete;
	
	// �Ҳ�������
	JButton put_in_storage, record, look_stcok;
	JPanel jbuttonpanel;
	
	//����һ�����ָ�������
	Cursor myCursor=new Cursor(Cursor.HAND_CURSOR);//�������
	
	JScrollPane jsp;
	
	String[] updateparas = {"1"};
	String updatesql = "select * from ProductInfo where 1 = ? order by Ptype";
	ProductModel pm = new ProductModel();
	
	public void setbutton(JButton jb, int type) {
		
		if (type == 1) {
			
			jb.setContentAreaFilled(false);
			jb.setBorderPainted(false);
			
		}
		if (type == 2) {
			
			jbuttonpanel.add(jb);
		}
		jb.setFocusPainted(false);
		jb.addMouseListener(this);
		jb.setCursor(myCursor);
		jb.setOpaque(false);
	}
	private void setlab(JLabel jlb, int i) {
		
		if (i == 1) {
			
			findproduct.add(jlb);
			
		}
		if (i == 2) {
			
			showinfo.add(jlb);
			jlb.setHorizontalAlignment(JLabel.CENTER);
			jlb.setFont(MyFont.Infolab);
			
		}
		jlb.setForeground(Color.WHITE);
	}
	private void setjtf(final JTextField jtf, int i) {
		
		if (i == 1) {
			
			findproduct.add(jtf);
			MatteBorder ubderline0 = new MatteBorder(0, 1, 1, 1, color);
			jtf.setBorder(ubderline0);
		}
		if (i == 2) {
			
			showinfo.add(jtf);
			jtf.setForeground(Color.white);
			MatteBorder ubderline = new MatteBorder(0, 0, 0, 0, Color.white);
			jtf.setBorder(ubderline);
		}

		jtf.setOpaque(false);
		jtf.setHorizontalAlignment(JTextField.CENTER);
		jtf.setFont(MyFont.Infotext);
	}
	public ProductInfo() {
		
		// �������
		findproduct = new JPanel(new GridLayout(1, 4));
		findproduct.setOpaque(false);
		findproduct.setPreferredSize(new Dimension((int)(width*0.8)-255, 80));
		IdorName = new JLabel("<html>����Ʒ��Ż��Ʒ����<br/>&nbsp��(�����ִ�Сд)");
		IdorName.setFont(new Font("������",Font.PLAIN,15));
		setlab(IdorName, 1);
		
		getIdorName = new JTextField(10);
		getIdorName.addFocusListener(this);
		setjtf(getIdorName, 1);
		
		find = new JButton(new ImageIcon("image/find.png"));
		setbutton(find, 1);
		findproduct.add(find);
		
		type = new JLabel("��Ʒ����");
		type.setFont(MyFont.Infolab);
		setlab(type, 1);
		Vector<String> temp=new Vector<String>();
		temp.add("--���в�Ʒ--");
		// �Ӳ�Ʒ���в�ѯ���
		String typesql = "select distinct Ptype from ProductInfo where 1 = ?";
		pm.query(typesql, updateparas);
		// ѭ���ļ���temp��
		for (int i = 0; i < pm.getRowCount(); i++) {
			
			temp.add((String) pm.getValueAt(i, 0));
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
		
		showtabel = new JPanel();
		showtabel.setBackground(Color.white);
		// �������Ĵ�С
		showtabel.setPreferredSize(new Dimension((int)(width*0.8)-250, (int)(height*0.8)-155));
		
		// ��Ʊ��
		pm.query(updatesql, updateparas);
		producttable = new JTable(pm);
		
		// ���ù���Tools���е����ñ����ʽ����
		Tools.setTableStyle(producttable);
		producttable.addMouseListener(this);
		
		// �������
		jsp=new JScrollPane(producttable);
		jsp.setBorder(new MatteBorder(1, 1, 1, 1, color));
		Tools.setJspStyle(jsp);
		
		showtabel = new JPanel(new BorderLayout());
		showtabel.setBackground(Color.white);
		// �������Ĵ�С
		showtabel.setPreferredSize(new Dimension((int)(width*0.8)-250, (int)(height*0.8)-155));
		
		showtabel.add(jsp, "Center");
		
		handle = new JPanel(new GridLayout(1, 3, ((int)(width*0.8)-625)/6, 10));
		handle.setPreferredSize(new Dimension((int)(width*0.8)-250, 91));
		handle.setOpaque(false);
		add = new JButton(new ImageIcon("image/add.png"));
		//add.setToolTipText("���һ��Ա����Ϣ");
		setbutton(add, 1);
		modify = new JButton(new ImageIcon("image/modify.png"));
		//modify.setToolTipText("�޸�Ա������Ϣ");
		setbutton(modify, 1);
		delete = new JButton(new ImageIcon("image/del.png"));
		//delete.setToolTipText("ɾ��ѡ�е�Ա��");
		setbutton(delete, 1);
		
		handle.add(add);
		handle.add(modify);
		handle.add(delete);
		
		showjp = new JPanel(new BorderLayout());
		showjp.setOpaque(false);
		// �����������ı߿�
		showjp.setBorder(new MatteBorder(0, 1, 0, 1, color));
		showjp.add(findproduct, "North");
		showjp.add(showtabel, "Center");
		showjp.add(handle, "South");
		
		
		// �����Ҳ�		
		// װ�ذ�ť���
		jbuttonpanel = new JPanel(new GridLayout(3, 1, 10, 50));
		jbuttonpanel.setPreferredSize(new Dimension(217, (int)(height*0.8)-354));
		//jbuttonpanel.setBounds(x, y, width, height)
		jbuttonpanel.setOpaque(false);
		
		put_in_storage = new JButton(new ImageIcon("./image/put_in_storage.png"));
		setbutton(put_in_storage, 2);
		put_in_storage.setRolloverIcon(new ImageIcon("./image/put_in_storageC.png"));
		
		record = new JButton(new ImageIcon("./image/record.png"));
		setbutton(record, 2);
		record.setRolloverIcon(new ImageIcon("./image/recordC.png"));
		
		look_stcok = new JButton(new ImageIcon("./image/lookstcok.png"));
		setbutton(look_stcok, 2);
		look_stcok.setRolloverIcon(new ImageIcon("./image/lookstcokC.png"));
		
		showinfo = new JPanel();
		showinfo.setPreferredSize(new Dimension(350, (int)(height*0.8)));
		showinfo.setOpaque(false);
		
		JPanel nulljp = new JPanel();
		nulljp.setPreferredSize(new Dimension(350, 100));
		nulljp.setOpaque(false);
		
		showinfo.add(nulljp);
		showinfo.add(jbuttonpanel);
		
		// ���ô������ʽΪ��ǰϵͳ����ʽ
		try {
			
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		this.setOpaque(false);
		this.setLayout(new BorderLayout());
		this.add(showjp, "Center");
		this.add(showinfo, "East");
		this.setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == find) {
			
			// ���ò��Һ���
			findproductinfo();
		}
		// ��Ӳ�Ʒ��Ϣ����
		if (e.getSource() == add) {
			
			// ������Ӵ���
			new Add_Product_View((width-560)/2,(height-498)/2);
			
			// ���±��
			updatetable(updatesql, updateparas);
		}
		// �޸Ĳ�Ʒ��Ϣ����
		if (e.getSource() == modify) {
			
			int selrow=producttable.getSelectedRow();
			if(selrow == -1)
			{
				JOptionPane.showMessageDialog(this, "��ѡ��һ����Ҫ�޸ĵĲ�Ʒ��Ϣ");
				return;
			}
			
			// ���Ҫ�޸ĵ����ݣ�ͬʱ�����޸Ĳ�������
			new Chang_Product_View(pm, selrow);
			
			// ���±��
			updatetable(updatesql, updateparas);
		}
		// ˫������޸�
		if (e.getSource() == producttable) {
			
			if (e.getClickCount() == 2) {
				
				int selrow=producttable.getSelectedRow();
				
				// ���Ҫ�޸ĵ����ݣ�ͬʱ�����޸Ĳ�������
				new Chang_Product_View(pm, selrow);
				
				// ���±��
				updatetable(updatesql, updateparas);
			}
		}
		// ɾ����Ʒ��Ϣ����
		if (e.getSource() == delete) {
			
			int selrow = producttable.getSelectedRow();
			if(selrow == -1)
			{
				JOptionPane.showMessageDialog(this, "��ѡ��һ�У��ٽ��в���");
				return;
			}
			int i = JOptionPane.showConfirmDialog(this, "<html><font size = '5'>�Ƿ�Ҫɾ��ѡ�еĲ�Ʒ��Ϣ��<br /><br /><font size = '5' color = 'red'>��ע�����ز���<br /><br />", "��ܰ��ʾ", 
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (i == 0) {
				
				String[] pid={(String)pm.getValueAt(selrow, 0)};
				String sql = "delete from ProductInfo where Pid = ?";
				boolean result = ProductModel.update(sql, pid);
				if (result) {
					
					JOptionPane.showMessageDialog(this, "<html><font size = '5' color = 'blue'>��ϲ����ɾ���ɹ���");
					updatetable(updatesql, updateparas);
				}else {
					
					JOptionPane.showMessageDialog(this, "<html><font size = '5' color = red>��Ǹ��֪ͨ����ɾ��û�гɹ�!<br />����ԭ��");
				}
			}else {
				
				return;
			}
		}
		// ��Ʒ���Ǽǲ���
		if (e.getSource() == put_in_storage) {
			
			int selrow = producttable.getSelectedRow();
			
			if (selrow == -1) {
				
				// û��ѡ���򴴽��µ�
				new ProductTo_View();
			}else {
				
				// ����б������ѡ�У��򽫲�Ʒ��Ŵ���������
				new ProductTo_View(pm, selrow);
			}
		}
		// �鿴��Ʒ����¼
		if (e.getSource() == record) {
			
			new LookRecord_View();
		}
		// ��Ʒ��Ʒ�Ŀ�����
		if (e.getSource() == look_stcok) {
			
			new LookProductStcok_View();
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
		if (e.getSource() == add) {
			
			add.setIcon(new ImageIcon("image/addC.png"));
		}
		if (e.getSource() == modify) {
			
			modify.setIcon(new ImageIcon("image/modifyC.png"));
		}
		if (e.getSource() == delete) {
			
			delete.setIcon(new ImageIcon("image/delC.png"));
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == find) {
			
			find.setIcon(new ImageIcon("image/find.png"));
		}
		if (e.getSource() == add) {
			
			add.setIcon(new ImageIcon("image/add.png"));
		}
		if (e.getSource() == modify) {
			
			modify.setIcon(new ImageIcon("image/modify.png"));
		}
		if (e.getSource() == delete) {
			
			delete.setIcon(new ImageIcon("image/del.png"));
		}
	}
	
	// ���±��ģ�ͺ������������Ҫ�����ǵ���
	public void updatetable(String sql, String[] paras) {
		
		pm = new ProductModel();
		pm.query(sql, paras);
		producttable.setModel(pm);
		Tools.setTableStyle(producttable);
	}
	
	// �����Ż�����
	private void findproductinfo() {
		
		// �õ����ҵ�����
		String tiaojian = getIdorName.getText().trim();
		
		while (tiaojian.isEmpty()) {
			
			JOptionPane.showMessageDialog(this, "������Ҫ���ҵ�����");
			return;
		}
		
		String[] tiaojianparas = {tiaojian, tiaojian};
		
		// ��������в�Ʒ���򲻴��ϲ�Ʒ���Ĳ�ѯ������ ����else
		if (gettype.getSelectedItem().equals("--���в�Ʒ--")) {
			
			// ��SellInfo ProductInfo��������ϲ�ѯ
	 		if (!ProductModel.checknum("select count(*) from ProductInfo where Pid like '%'+?+'%' or PName like '%'+?+'%'", tiaojianparas)) {
				
				JOptionPane.showMessageDialog(this, "��Ǹ��û���ҵ���صĲ�Ʒ��Ϣ");
				return;
				
			}else {
				
				updatetable("select * from ProductInfo where Pid like '%'+?+'%' or PName like '%'+?+'%'", tiaojianparas);
			}
		} else {
			
			String[] newtiaojianparas = {tiaojian, tiaojian, gettype.getSelectedItem().toString()};
			
			// ��SellInfo ProductInfo��������ϲ�ѯ
	 		if (!SellModel.check("select count(*) from ProductInfo where (Pid like '%'+?+'%' or PName like '%'+?+'%') and Ptype = ?", newtiaojianparas)) {
				
	 			
				JOptionPane.showMessageDialog(this, "<html><br/><font size = '5'>�ڲ�Ʒ���<font color = 'red'>"+gettype.getSelectedItem()+"</font>��<br/>û���ҵ��룺<font color = 'red'>"+tiaojian+"</font>&nbsp&nbsp��صĲ�Ʒ��Ϣ</font><br/><br/>");
				return;
				
			}else {
				
				updatetable("select * from ProductInfo where (Pid like '%'+?+'%' or PName like '%'+?+'%') and Ptype = ?", newtiaojianparas);
			}
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
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if (e.getSource() == this.gettype) {

			if (getIdorName.getText().trim().isEmpty()) {

				if (gettype.getSelectedItem().equals("--���в�Ʒ--")) {

					updatetable(updatesql, updateparas);

				} else {

					String[] parasnew = { gettype.getSelectedItem().toString() };
					updatetable(
							"select * from ProductInfo where Ptype = ? order by Ptype",
							parasnew);
				}
			} else {
				
				findproductinfo();
			}
		}
	}
}
