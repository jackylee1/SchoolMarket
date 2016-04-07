/**
 * ������ʾ�͹����Ա��Ϣ�����
 */
package com.view;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import com.mytools.*;
import com.model.*;

@SuppressWarnings("serial")
public class EmpInfo extends JPanel implements MouseListener, FocusListener, KeyListener {
	
	 // ���ڻ�ô��ڵĴ�С
	final static int width=Toolkit.getDefaultToolkit().getScreenSize().width;
	final static int height=Toolkit.getDefaultToolkit().getScreenSize().height;
	// ������ɫֵ
	Color color = new Color(22, 120, 195);
	
	// ��ʾ��Ϣ�����
	JPanel showtabel, showinfoall, showinfo, handle;
	// ȷ����Ӱ�ť�������
	JPanel jadd;
	// װ����Ϣ�������
	JPanel showjp;
	JTable Emptable = null;

	JButton add, addc, modify, delete;
	
	// �Ҳ�������
	JLabel id, name, sex, age, card, phone, adress, todate, job; 
	
	JTextField idt, namet, aget, cardt, phonet, addresst, todatet, jobt;
	JRadioButton boy, gril;
	ButtonGroup sext;
	
	 EmpModel em=new EmpModel();
	 EmpModel emnew = new EmpModel();
	
	//����һ�����ָ�������
	Cursor myCursor=new Cursor(Cursor.HAND_CURSOR);//�������
	
	JScrollPane jsp;
	
	String []paras={"1"};
	
	public void setbutton(JButton jb) {
		
		jb.setContentAreaFilled(false);
		jb.setBorderPainted(false);
		jb.setFocusPainted(false);
		jb.addMouseListener(this);
		jb.setCursor(myCursor);
		jb.setOpaque(false);
	}
	private void setlab(JLabel jlb) {
		
		showinfo.add(jlb);
		jlb.setFont(MyFont.Infolab);
		jlb.setForeground(Color.WHITE);
	}
	private void setjtf(final JTextField jtf) {
		
		showinfo.add(jtf);
		MatteBorder ubderline = new MatteBorder(0, 0, 1, 0, Color.white);
		jtf.setBorder(ubderline);
		jtf.setOpaque(false);
		jtf.setFont(MyFont.Infotext);
		jtf.setForeground(Color.white);
	}
	public EmpInfo() {
		
		// �������
		//1.���jtable
		em.query("select Eid, EName, Sex, Age, Job from EmployeeInfo where 1=?", paras);
		Emptable = new JTable(em);
		
		// ���ù���Tools���е����ñ����ʽ����
		Tools.setTableStyle(Emptable);
		Emptable.addMouseListener(this);
		Emptable.addKeyListener(this);
		Emptable.setOpaque(false);
		
		// �������
		jsp = new JScrollPane(Emptable);
		jsp.setBorder(new MatteBorder(0, 1, 1, 0, color));
		Tools.setJspStyle(jsp);
		
		showtabel = new JPanel(new BorderLayout());
		showtabel.setBackground(Color.white);
		// ���ñ߿�
		MatteBorder border = new MatteBorder(0, 1, 1, 0, color);
		showtabel.setBorder(border);
		// �������Ĵ�С
		showtabel.setPreferredSize(new Dimension((int)(width*0.8)-250, (int)(height*0.8)-155));
		
		showtabel.add(jsp);
		
		handle = new JPanel(new GridLayout(1, 3, ((int)(width*0.8)-625)/6, 10));
		handle.setPreferredSize(new Dimension((int)(width*0.8)-250, 91));
		// ����ֻ���ұ߿�
		MatteBorder border2 = new MatteBorder(0, 0, 0, 1, new Color(22, 120, 195));
		handle.setBorder(border2);
		handle.setOpaque(false);
		add = new JButton(new ImageIcon("image/add.png"));
		//add.setToolTipText("���һ��Ա����Ϣ");
		setbutton(add);
		modify = new JButton(new ImageIcon("image/modify.png"));
		//modify.setToolTipText("�޸�Ա������Ϣ");
		setbutton(modify);
		delete = new JButton(new ImageIcon("image/del.png"));
		//delete.setToolTipText("ɾ��ѡ�е�Ա��");
		setbutton(delete);
		
		handle.add(add);
		handle.add(modify);
		handle.add(delete);
		
		showjp = new JPanel(new BorderLayout());
		showjp.setOpaque(false);
		showjp.add(showtabel, "Center");
		showjp.add(handle, "South");
		
		// �����Ҳ�
		showinfo = new JPanel(new GridLayout(9, 2, -90, 30));
		showinfo.setPreferredSize(new Dimension(350, (int)(height*0.8)-85));
		showinfo.setOpaque(false);
		
		// ��һ��
		id = new JLabel(" Ա�����");
		setlab(id);
		idt = new JTextField(10);
		idt.addFocusListener(this);
		setjtf(idt);
		
		name = new JLabel(" Ա������");
		setlab(name);
		namet = new JTextField(10);
		setjtf(namet);
		
		sex = new JLabel(" ��    ��");
		setlab(sex);
		boy =new JRadioButton("��");
		boy.setOpaque(false);
		boy.setFocusPainted(false);
		boy.setBorderPainted(false);
		
		gril = new JRadioButton("Ů");
		gril.setOpaque(false);
		gril.setFocusPainted(false);
		gril.setBorderPainted(false);
		sext = new ButtonGroup();
		sext.add(boy);
		sext.add(gril);
		JPanel sextp = new JPanel(new GridLayout(1, 2));
		sextp.setOpaque(false);
		sextp.add(boy);
		sextp.add(gril);
		showinfo.add(sextp);
		
		age = new JLabel(" ��    ��");
		setlab(age);
		aget = new JTextField(10);
		setjtf(aget);
		
		card = new JLabel(" ���֤��");
		setlab(card);
		cardt = new JTextField(10);
		setjtf(cardt);
		 
		phone = new JLabel(" ��ϵ�绰");
		setlab(phone);
		phonet =new JTextField(10);
		setjtf(phonet);
		
		adress = new JLabel(" ��ϵ��ַ");
		setlab(adress);
		addresst = new JTextField(10);
		setjtf(addresst);
		addresst.setFont(new Font("������",Font.PLAIN,13));
		
		todate = new JLabel(" ��ְ����");
		setlab(todate);
		todatet = new JTextField(10);
		setjtf(todatet);
		
		job = new JLabel(" ְ    λ");
		setlab(job);
		jobt = new JTextField(10);
		setjtf(jobt);
		
		jadd = new JPanel();
		jadd.setPreferredSize(new Dimension(350, 85));
		jadd.setOpaque(false);
		
		addc = new JButton(new ImageIcon("image/addconfirm.png"));
		addc.setVisible(false);
		setbutton(addc);
		
		jadd.add(addc);
		
		showinfoall = new JPanel(new BorderLayout());
		showinfoall.setOpaque(false);
		showinfoall.setPreferredSize(new Dimension(350, (int)(height*0.8)));
		
		showinfoall.add(showinfo, "Center");
		showinfoall.add(jadd, "South");
		
		this.setOpaque(false);
		this.setLayout(new BorderLayout());
		this.add(showjp, "Center");
		this.add(showinfoall, "East");
		this.setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == Emptable) {
			
			emnew.query("select * from EmployeeInfo where 1 = ?", paras);
			idt.setText((String)emnew.getValueAt(Emptable.getSelectedRow(), 0));
			idt.setEditable(false);
			idt.setForeground(Color.lightGray);
			namet.setText((String)emnew.getValueAt(Emptable.getSelectedRow(), 1));
			// �����Ա����ʾ
			if (emnew.getValueAt(Emptable.getSelectedRow(), 2).equals("��")) {
				boy.setSelected(true);
			}else {
				gril.setSelected(true);
			}
			aget.setText((String)emnew.getValueAt(Emptable.getSelectedRow(), 3));
			cardt.setText((String)emnew.getValueAt(Emptable.getSelectedRow(), 4));
			phonet.setText((String)emnew.getValueAt(Emptable.getSelectedRow(), 5));
			addresst.setText((String)emnew.getValueAt(Emptable.getSelectedRow(), 6));
			String fdate = (String)emnew.getValueAt(Emptable.getSelectedRow(), 7);
			todatet.setText(fdate.substring(0, 19));
			jobt.setText((String)emnew.getValueAt(Emptable.getSelectedRow(), 8));
			
			addc.setVisible(false);
			delete.setEnabled(true);
			modify.setEnabled(true);
			idt.removeFocusListener(this);
		}
		// ��Ӱ�ť
		if (e.getSource() == add) {
			
			// 1.������е���Ϣ
			idt.setText("");
			idt.setEditable(true);
			idt.setForeground(Color.WHITE);
			namet.setText("");
			//boy.setSelected(false);
			gril.setSelected(true);
			aget.setText("");
			cardt.setText("");
			phonet.setText("");
			addresst.setText("");
			todatet.setText(Tools.getlocaldatetime());
			jobt.setText("");
			
			addc.setVisible(true);
			Emptable.clearSelection();
			delete.setEnabled(false);
			modify.setEnabled(false);
			
			idt.addFocusListener(this);
		}
		if (e.getSource() == addc) {
			
			// 1.�õ���Ϣ
			String str1 = idt.getText();
			String str2 = namet.getText();
			String str3 =null;
			if (boy.isSelected()) {
				
				str3 = boy.getText();
			} else if (gril.isSelected()) {
				
				str3 = gril.getText();
			}
			
			String str4 = aget.getText();
			String str5 = cardt.getText().trim();
			String str6 = phonet.getText();
			String str7 = addresst.getText();
			String str8 = todatet.getText();
			String str9 = jobt.getText();
			
			// 2.�ж���Ϣ�Ƿ�Ϊ��
			if (str1.equals("")||str2.equals("")||str3.equals("")||str4.equals("")||str5.equals("")
					||str6.equals("")||str7.equals("")||str8.equals("")||str9.equals("")) {
				
				JOptionPane.showMessageDialog(this, "<html><font color = 'red'>����Ϊ�գ���������Ӧ����Ϣ��");
				return;
			}
			
			// 3.��Ӳ���
			String[] newparas={str1, str2, str3, str4, str5, str6, str7, str8, str9};
			String sql="insert into EmployeeInfo values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			boolean result = emnew.Empupdate(sql, newparas);
			if (result) {
				
				JOptionPane.showMessageDialog(this, "<html><font size = '5' color = 'blue'>��ӳɹ�");
			}else {
				
				JOptionPane.showMessageDialog(this, "<html><font size = '5' color = red>��Ǹ��֪ͨ����û����ӳɹ�!</font>" +
						"<br />������Ϣ�Ƿ����Ҫ��<br />" +
						"�������⣺<br />���������䳬����Χ<br />�������������ڸ�ʽ������(��-��-��,��:2013-07-20) <br />���������֤�Ƿ�Ϊ18λ");
			}
			em = new EmpModel();
			em.query("select Eid, EName, Sex, Age, Job from EmployeeInfo where 1 = ?", paras);
			Emptable.setModel(em);
		}
		if (e.getSource() == modify) {
			
			if (modify.isEnabled()) {
				int selrow=Emptable.getSelectedRow();
				int i = Emptable.getSelectedRowCount();
				while(i > 1)
				{
					JOptionPane.showMessageDialog(this, "ֻ�ܲ���һ�����ݣ���ѡ��һ�в���");
					return;
				}
				while(selrow==-1)
				{
					JOptionPane.showMessageDialog(this, "��ѡ��һ�У��ٽ��в���");
					return;
				}
				
				// 1.�õ��޸ĵ���������
				String str1 = idt.getText();
				String str2 = namet.getText();
				String str3 =null;
				if (boy.isSelected()) {
					
					str3 = boy.getText();
				} else if (gril.isSelected()) {
					
					str3 = gril.getText();
				}
				
				String str4 = aget.getText();
				String str5 = cardt.getText().trim();
				String str6 = phonet.getText();
				String str7 = addresst.getText();
				String str8 = todatet.getText();
				String str9 = jobt.getText();
				
				if (mesconfirm() && str3.equals((String)emnew.getValueAt(Emptable.getSelectedRow(), 2))) {
					
					JOptionPane.showMessageDialog(this, "<html><font size = '5' color = 'red'>�ף���Ϣû���޸Ĺ���</font><br /><font size = '5' color = 'blue'>��ܰ��ʾ:<br />�����Զ��Ҳ���Ӧ����Ϣ�޸�֮���ٱ����޸�");
					return;
				}
				if(str2.equals("")||str3.equals("")||str4.equals("")||str5.equals("")
						||str6.equals("")||str7.equals("")||str8.equals("")||str9.equals(""))
				{
					JOptionPane.showMessageDialog(this, "<html><font color = 'red'>����Ϊ�գ���������Ӧ����Ϣ��");
					return;
					
				}
				String[] newparas={str2, str3, str4, str5, str6, str7, str8, str9, str1};
				String sql="update EmployeeInfo set EName=?,Sex=?,Age=?,Card=?, Phone=?,Adress=?,ToDate=?,Job=? where Eid=?";
				EmpModel emnew = new EmpModel();
				boolean result = emnew.Empupdate(sql, newparas);
				if (result) {
					
					JOptionPane.showMessageDialog(this, "<html><font size = '4' color = 'blue'>�޸ĳɹ�");
				}else {
					
					JOptionPane.showMessageDialog(this, "<html><font size = '5' color = red>��Ǹ��֪ͨ�����޸�û�гɹ�!</font>" +
							"<br />������Ϣ�Ƿ����Ҫ��<br />" +
							"�������⣺<br />���������䳬����Χ<br />�������������ڸ�ʽ������(��-��-��)��");
				}
				em = new EmpModel();
				em.query("select Eid, EName, Sex, Age, Job from EmployeeInfo where 1 = ?", paras);
				Emptable.setModel(em);
			}

		}
		if (e.getSource() == delete) {
			
			if (delete.isEnabled()) {
				
				int selrow=Emptable.getSelectedRow();
				int i = Emptable.getSelectedRowCount();
				while(i > 1)
				{
					JOptionPane.showMessageDialog(this, "ֻ�ܲ���һ�����ݣ���ѡ��һ�в���");
					return;
				}
				if(selrow == -1)
				{
					JOptionPane.showMessageDialog(this, "��ѡ��һ�У��ٽ��в���");
					return;
				}
				int j = JOptionPane.showConfirmDialog(this, "<html><font size = '5'>�Ƿ�Ҫɾ��ѡ��Ա����Ϣ��<br /><br /><font size = '5' color = 'red'>��ע�����ز���<br /><br />", "��ܰ��ʾ", 
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (j == 0) {
					
					String[] eid={(String)em.getValueAt(selrow, 0)};
					String sql = "delete from EmployeeInfo where Eid = ?";
					boolean result = emnew.Empupdate(sql, eid);
					if (result) {
						

						JOptionPane.showMessageDialog(this, "<html><font size = '5' color = 'blue'>��ϲ����ɾ���ɹ���");
						em = new EmpModel();
						em.query("select Eid, EName, Sex, Age, Job from EmployeeInfo where 1 = ?", paras);
						Emptable.setModel(em);
					}else {
						
						JOptionPane.showMessageDialog(this, "<html><font size = '5' color = red>��Ǹ��֪ͨ����ɾ��û�гɹ�!<br />����ԭ��");
					}
				}else {
					
					return;
				}
			}
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
		if (e.getSource() == add) {
			
			add.setIcon(new ImageIcon("image/addC.png"));
		}
		if (e.getSource() == addc) {
			
			addc.setIcon(new ImageIcon("image/addconfirmC.png"));
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
		if (e.getSource() == add) {
			
			add.setIcon(new ImageIcon("image/add.png"));
		}
		if (e.getSource() == addc) {
			
			addc.setIcon(new ImageIcon("image/addconfirm.png"));
		}
		if (e.getSource() == modify) {
			
			modify.setIcon(new ImageIcon("image/modify.png"));
		}
		if (e.getSource() == delete) {
			
			delete.setIcon(new ImageIcon("image/del.png"));
		}
	}
	
	// ��Ϣ�жϺ���
	private boolean mesconfirm() {
		
		boolean b = false;
		if (idt.getText().equals((String)emnew.getValueAt(Emptable.getSelectedRow(), 0)) 
				&& namet.getText().equals((String)emnew.getValueAt(Emptable.getSelectedRow(), 1)) 
				&& aget.getText().equals((String)emnew.getValueAt(Emptable.getSelectedRow(), 3))
				&& cardt.getText().equals((String)emnew.getValueAt(Emptable.getSelectedRow(), 4))
				&& phonet.getText().equals((String)emnew.getValueAt(Emptable.getSelectedRow(), 5))
				&& addresst.getText().equals((String)emnew.getValueAt(Emptable.getSelectedRow(), 6))
				&& todatet.getText().equals(((String)emnew.getValueAt(Emptable.getSelectedRow(), 7)).substring(0, 19))
				&& jobt.getText().equals((String)emnew.getValueAt(Emptable.getSelectedRow(), 8))
			) 
		{
			b = true;
		}
		
		return b;
	}
	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub

		
	}
	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
		Component comp =e.getComponent();
		if (comp instanceof JTextField) {
			
			if (!idt.getText().trim().isEmpty()) {
				
				if (!Tools.isNum(idt.getText().trim()) || Integer.valueOf(idt.getText().trim()) < 0) {
						
						JOptionPane.showMessageDialog(this, "<html>Ա�����ֻ����������ɣ����������룡");
						return;
				} else {
					
					if (emnew.checkid(idt.getText().trim())) {
						
						JOptionPane.showMessageDialog(this, "<html><br /><font size = '5'>��Ǹ��֪ͨ����Ա�����:<font color = 'red'>"+idt.getText()+"</font>�Ѿ����ڡ�<br/>���������������ԣ�<br />");
					}
				}

			}
		}
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
