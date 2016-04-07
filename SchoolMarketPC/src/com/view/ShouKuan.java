/**
 *  �տ���棬����ʵ���տ�
 *  
 *  �޸����ڣ�2013-07-27
 *  	1.ϸ���������˼�룬���������һ���ĺ�������߸�����
 *  	2.������Ӧ�Ŀ�ݼ���������ٵĲ���
 *  	  ȷ����ӣ�Enter	ɾ����Delete	��գ�Ctrl+Delete		���ˣ�Ctrl+Enter
 */

package com.view;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import com.model.ShowKuanModel;
import com.mytools.*;

@SuppressWarnings("serial")
public class ShouKuan extends JPanel implements KeyListener, MouseListener, FocusListener {
	
	    // ���ڻ�ô��ڵĴ�С
		final static int width=Toolkit.getDefaultToolkit().getScreenSize().width;
		final static int height=Toolkit.getDefaultToolkit().getScreenSize().height;
		// ������ɫֵ
		Color color = new Color(22, 120, 195);
		// ��ʾ��Ϣ�����
		JPanel addproduct, showtabel, showinfoall, showinfo, handle;
		// ��Ӳ�Ʒ��Ϣ�����ؼ�
		JLabel id, num;
		JTextField getid, getnum;
		JButton confirm;
		// װ����Ϣ�������
		JPanel showjp;
		JTable saltable = null;
		
		JButton delete, clear;
		
		// �Ҳ�������
		JLabel sum, form, to; // ��sum��ʾ�ܵĽ�from��ʾʵ�գ�to��ʾӦ��
		static JLabel setsum, setto;
		JTextField getform;
		JButton jiezhang;
		JPanel jjiezhang;
		
		//����һ�����ָ�������
		Cursor myCursor=new Cursor(Cursor.HAND_CURSOR);//�������
		JScrollPane jsp;
		
		ShowKuanModel skm = new ShowKuanModel();
		String[] paras = {"1"};
		
		// ���ð�ť��ʽ����
		private void setbutton(JButton jb) {
			
			jb.setContentAreaFilled(false);
			jb.setBorderPainted(false);
			jb.setFocusPainted(false);
			jb.addMouseListener(this);
			jb.addKeyListener(this);
			jb.setCursor(myCursor);
			jb.setOpaque(false);
		}
		
		// ���ñ�����ʽ
		private void setlab(JLabel jlb, int i) {
			
			if (i == 1) {
				
				addproduct.add(jlb);
			}
			if (i == 2) {
				
				showinfo.add(jlb);
			}
			jlb.setForeground(Color.WHITE);
			jlb.setFont(MyFont.Infolab);
			jlb.setHorizontalAlignment(JLabel.CENTER);
		}
		
		// �����������ʽ
		private void setjtf(final JTextField jtf, int i) {
			
			if (i == 1) {
				
				addproduct.add(jtf);
				MatteBorder ubderline0 = new MatteBorder(0, 1, 1, 1, color);
				jtf.setBorder(ubderline0);
			}
			if (i == 2) {
				
				showinfo.add(jtf);
				jtf.setForeground(Color.white);
				jtf.setBorder(null);
			}

			jtf.setOpaque(false);
			jtf.setHorizontalAlignment(JTextField.CENTER);
			jtf.setFont(MyFont.Infotext);
			jtf.setSelectionColor(new Color(60, 128, 250));
			jtf.addMouseListener(this);
		}
		
		// ��ʼ�����
		private void initLeft() {
			
			// �������
			addproduct = new JPanel(new GridLayout(1, 4));
			addproduct.setOpaque(false);
			addproduct.setPreferredSize(new Dimension((int)(width*0.8)-255, 80));
			
			id = new JLabel("��ƷID");
			setlab(id, 1);
			
			getid = new JTextField(10);
			setjtf(getid, 1);
			getid.addFocusListener(this);
			
			num = new JLabel("��������");
			setlab(num, 1);
			
			getnum = new JTextField("1");
			setjtf(getnum, 1);
			getnum.addFocusListener(this);
			
			confirm = new JButton(new ImageIcon("image/confirm.png"));
			setbutton(confirm);
			addproduct.add(confirm);
			
			// ���ÿ�ݼ�
			ActionListener addListener = new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
			    	confirm.doClick();
			        addproduct();   
				}
			};
			
			setQkey(addListener, KeyEvent.VK_ENTER, 0);
			
			//1.���jtable
			skm.query("select Pid, Pname, Price, Num, (Price*Num) as Allsum from temp where 1=?", paras);
			saltable = new JTable(skm);
			
			// ���ù���Tools���е����ñ����ʽ����
			Tools.setTableStyle(saltable);
			
			// �������
			jsp = new JScrollPane(saltable);
			jsp.setBorder(new MatteBorder(1, 1, 1, 0, color));
			Tools.setJspStyle(jsp);
			
			showtabel = new JPanel(new BorderLayout());
			showtabel.setBackground(Color.white);
			
			// �������Ĵ�С
			showtabel.setPreferredSize(new Dimension((int)(width*0.8)-250, (int)(height*0.8)-155));
			
			showtabel.add(jsp);
			
			handle = new JPanel(new GridLayout(1, 2, 250, 4));
			handle.setPreferredSize(new Dimension((int)(width*0.8)-250, 91));
			handle.setOpaque(false);
			delete = new JButton(new ImageIcon("image/del.png"));
			setbutton(delete);
			
			// ����ɾ���Ŀ�ݼ�  		����Ϊ��delete
			ActionListener delListener = new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					delete.doClick();
			    	delproduct();
				}
			};
			setQkey(delListener, KeyEvent.VK_DELETE, 0);
			
			
			clear = new JButton(new ImageIcon("image/clear.png"));
			setbutton(clear);
			
			// ������յĿ�ݼ� ����Ϊ��ctrl+delete
			ActionListener clearListener = new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					clear.doClick();
			    	clearproduct();
				}
			};
			setQkey(clearListener, KeyEvent.VK_DELETE, InputEvent.CTRL_DOWN_MASK);
			
			handle.add(delete);
			handle.add(clear);
			
			showjp = new JPanel(new BorderLayout());
			showjp.setOpaque(false);
			// �����������ı߿�
			MatteBorder border = new MatteBorder(0, 1, 0, 1, color);
			showjp.setBorder(border);
			showjp.add(addproduct, "North");
			showjp.add(showtabel, "Center");
			showjp.add(handle, "South");
		}
		
		// ��ʼ���Ҳ�
		private void initRight() {
			
			// �����Ҳ�
			showinfo = new JPanel(new GridLayout(1, 2, -100, -20));
			showinfo.setPreferredSize(new Dimension(350, (int)(height*0.8)-91));
			showinfo.setOpaque(false);
			
			sum = new JLabel("�ϼƣ�");
			setlab(sum, 2);
			setsum = new JLabel("0.0");
			updatesum();
			setlab(setsum, 2);
		
			jiezhang = new JButton(new ImageIcon("image/jiezhang.png"));
			setbutton(jiezhang);
			
			// ���ÿ�ݼ�
			ActionListener jiezhangListener = new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
			    	jiezhang.doClick();
			    	jiezhang();
				}
			};
			
			setQkey(jiezhangListener, KeyEvent.VK_ENTER, InputEvent.CTRL_DOWN_MASK);
			
			jjiezhang = new JPanel();
			jjiezhang.setOpaque(false);
			jjiezhang.add(jiezhang);
			jjiezhang.setPreferredSize(new Dimension(350, 400));
			
			showinfoall = new JPanel(new BorderLayout());
			showinfoall.setOpaque(false);
			showinfoall.addKeyListener(this);
			showinfoall.add(showinfo, "Center");
			showinfoall.add(jjiezhang, "South");	
		}
		
		// �տ���幹�캯��
		public ShouKuan() {
			
			initLeft();
			initRight();
			
			this.setOpaque(false);
			this.setFocusable(false);
			this.setLayout(new BorderLayout());
			this.add(showjp, "Center");
			this.add(showinfoall, "East");	
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == confirm) {
				
				// ������Ӻ���
				addproduct();
				Tools.selsectAll(getid);
			}
			if (e.getSource() == delete) {
				
				// ����ɾ������
				delproduct();
			}
			if (e.getSource() == clear) {
				
				// ������պ���
				clearproduct();
			}
			if (e.getSource() == jiezhang) {
				
				// ���ý��˺���
				jiezhang();
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
			if (e.getSource() == confirm) {
				
				confirm.setIcon(new ImageIcon("image/confirmC.png"));
			}
			if (e.getSource() == delete) {
				
				delete.setIcon(new ImageIcon("image/delC.png"));
			}
			if (e.getSource() == clear) {
				
				clear.setIcon(new ImageIcon("image/clearC.png"));
			}
			if (e.getSource() == jiezhang) {
				
				jiezhang.setIcon(new ImageIcon("image/jiezhangC.png"));
			}
		}
		
		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == confirm) {
				
				confirm.setIcon(new ImageIcon("image/confirm.png"));
			}
			if (e.getSource() == delete) {
				
				delete.setIcon(new ImageIcon("image/del.png"));
			}
			if (e.getSource() == clear) {
				
				clear.setIcon(new ImageIcon("image/clear.png"));
			}
			if (e.getSource() == jiezhang) {
				
				jiezhang.setIcon(new ImageIcon("image/jiezhang.png"));
			}
		}
		// ���±��ģ�ͺ������������Ҫ�����ǵ���
		private void updatetable() {
			
			skm = new ShowKuanModel();
			skm.query("select Pid, Pname, Price, Num, (Price*Num) as Allsum from temp where 1=?", paras);
			saltable.setModel(skm);
			Tools.setTableStyle(saltable);
		}
		
		// ���ºϼ��ܶ���ܽ��仯ʱ����
		private void updatesum() {
			
			ShowKuanModel skmp = new ShowKuanModel();
			String setsum = "select sum(Price*Num) as �ϼ� from temp where 1=?";
			skmp.query(setsum, paras);
			// �жϺϼ��Ƿ�Ϊ�գ���������Ϊ0.0
			String i = (String)skmp.getValueAt(0, 0);
			if (i == null) {
				
				ShouKuan.setsum.setText("0.0");
				return;
			}
			ShouKuan.setsum.setText((String)skmp.getValueAt(0, 0));
		}
		
		// ���temp����,��պͽ����е���
		private void cleartemp() {
			
			String[] pid = {"1"};
			String sql = "delete from temp where 1 = ?";
			ShowKuanModel.update(sql, pid);
			updatetable();
			updatesum();
		}
		
		// ȷ����ť����Ӳ�Ʒ�ĺ���
		private void addproduct() {
			
			String pid = getid.getText();
			String num = getnum.getText();
			while (pid.isEmpty() && num.isEmpty()) {
				
				JOptionPane.showMessageDialog(this, "�������Ʒ��źͲ�Ʒ����");
				return;
			}
			while (pid.isEmpty()) {
				
				JOptionPane.showMessageDialog(this, "��Ʒ���Ϊ�գ��������Ʒ��ţ�");
				return;
			}
			while (num.isEmpty()) {
				
				JOptionPane.showMessageDialog(this, "��Ʒ����Ϊ�գ��������Ʒ������");
				return;
			}
			// �ж��Ƿ��ǺϷ�������
			while (!Tools.isNum(num) || Integer.valueOf(num) < 0) {
				
				JOptionPane.showMessageDialog(this, "���������Ƿ������������0������");
				return;
			}
			
			// ����Ʒ�Ƿ����
			while (!ShowKuanModel.check("select count(*) from ProductInfo where Pid = ?",pid)) {
				
				JOptionPane.showMessageDialog(this, "��Ǹ��û�иò�Ʒ������������", "����ʾ", JOptionPane.WARNING_MESSAGE);
				return;
			}
			// ��������Ƿ��иò�Ʒ
			if (!ShowKuanModel.check("select count(*) from Stcok where Pid = ?", pid)) {
				
				JOptionPane.showMessageDialog(this, "<html><font size = '3'>�ò�Ʒû�п������������ӣ��������", "����ܰ��ʾ", JOptionPane.WARNING_MESSAGE);
				return;
			}
			// ����Ʒ�Ŀ�����Ƿ����o
			int truenum = ShowKuanModel.get_p_num(pid);
			while (Integer.valueOf(num) > truenum) {
				
				JOptionPane.showMessageDialog(this, "<html><font style = 'font-size:12'>�ò�Ʒ�Ŀ����Ϊ��<font color = 'red'>"+truenum+"��</font><br/>��治�㣬�����������ӣ��������</font><br/><br/>", " ��ܰ��ʾ", JOptionPane.WARNING_MESSAGE);
				return;
			}
			// ��Pidȥ�õ���Ʒ��Ϣ�Ľ����
			String[] parasp = {pid};
			String sql = "select Pid,PName,Price from ProductInfo where Pid =?";
			ShowKuanModel skmp = new ShowKuanModel();
			skmp.query(sql, parasp);
			// ��ȡ����temp������
			String id = (String)skmp.getValueAt(0, 0);
			String pname = (String)skmp.getValueAt(0, 1);
			String price = (String)skmp.getValueAt(0, 2);
			
			String[] addparas = {id,pname,price,num};
			
			// �ж�temp���Ƿ��Ѿ���ͬ���Ĳ�Ʒ
			if (ShowKuanModel.check("select count(*) from temp where Pid = ?", pid)) {
				
				String setnum = "update temp set Num = ?+Num where Pid = ?";
				String[] updatenum = {num, pid};
				ShowKuanModel.update(setnum, updatenum);
			}else {
				
				String addsql = "insert into temp values (?,?,?,?)";
				ShowKuanModel.update(addsql, addparas);
			}
			
			updatetable();
			
			// �����ܵĺϼƽ��
			updatesum();
		}

		// ɾ������
		private void delproduct() {
			
			double sum = Double.parseDouble(ShouKuan.setsum.getText());
			
			// �ж��Ƿ��й������Ʒ
			if (sum == 0.0) {
				
				JOptionPane.showMessageDialog(this, "<html>û�й�����Ʒ����ѡ������Ʒ");
				return;
			}
			
			int selrow=saltable.getSelectedRow();
			int i = saltable.getSelectedColumnCount();
			if (i > 1) {
				
				JOptionPane.showMessageDialog(this, "ֻ��ѡ��һ��ɾ����");
				return;
			}
			if(selrow == -1)
			{
				JOptionPane.showMessageDialog(this, "��ѡ��Ҫɾ������Ʒ��");
				return;
			}
			
			int j = JOptionPane.showConfirmDialog(this, "<html><font size = '5'>�Ƿ�Ҫɾ��ѡ�е���Ʒ��<br /><br /><font size = '4' color = 'red'>��ע�����ز���<br /><br />", "��ܰ��ʾ", 
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (j ==0 ) {
				
				String[] pid={(String)skm.getValueAt(selrow, 0)};
				String sql = "delete from temp where Pid = ?";
				ShowKuanModel.update(sql, pid);
				
				updatetable();
				updatesum();
			}
		}
		
		// ��պ���
		private void clearproduct() {
			
			double sum = Double.parseDouble(ShouKuan.setsum.getText());
			
			// �ж��Ƿ��й������Ʒ
			if (sum == 0.0) {
				
				JOptionPane.showMessageDialog(this, "<html>û�й�����Ʒ����ѡ������Ʒ");
				return;
			}
			
			int i = JOptionPane.showConfirmDialog(this, "<html><font size = '4'>�Ƿ�Ҫ��չ������Ʒ��<br /><br /><font size = '4' color = 'red'>��ע�����ز���<br /><br />", "��ܰ��ʾ", 
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			
			if (i == 0) {
				
				cleartemp();
			}
		}
		// ���˰�ť�����˺���
		private void jiezhang() {
			
			double sum = Double.parseDouble(ShouKuan.setsum.getText());
			double from = 0.0;
			saltable.clearSelection();
			// �ж��Ƿ��й������Ʒ
			if (sum == 0.0) {
				
				JOptionPane.showMessageDialog(this, "<html>û�й�����Ʒ����ѡ������Ʒ");
				return;
			}
			
			String input;
			// ȷ���տ�������������
			do {
				
				// ��������ʵ�յ������
				input = JOptionPane.showInputDialog(this, "<html><font style = 'font-size:12'>ʵ�գ�", "����ʵ�ս��", JOptionPane.PLAIN_MESSAGE);
				
			} while (!Tools.isNum(input));
			
			// ʵ��Ҫ�����ܺϼƽ��
			do {
				
				from = Double.parseDouble(input);
				if (sum > from){
					
					input = JOptionPane.showInputDialog(this, "<html><font style = 'font-size:12'>�������룺", "ʵ�ս��С�ںϼƽ��", JOptionPane.PLAIN_MESSAGE);
					from = Double.parseDouble(input);
				}
			} while (sum > from);
			
			// ��ʾӦ��
			double to = from - sum;
			
			int i = JOptionPane.showConfirmDialog(this, "<html><font size = '5'>&nbsp&nbsp&nbsp&nbsp�ϼƣ�"+sum+"&nbsp&nbsp<br/>&nbsp&nbsp&nbsp&nbspʵ�գ�"+from+"<hr>&nbsp&nbsp<br/><font size = '13' color = 'blue'>&nbsp&nbspӦ�ң�"+to+"<br/><br/>", "�� ��ɽ���", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
			
			// ��ɽ��ˣ��������temp��ͽ����ݲ������۱�
			if (i == 0 ) {
				
				// ��Pidȥ�õ���Ʒ��Ϣ�Ľ����
				String[] parasp = {"1"};
				String sql = "select Pid,Num from temp where 1 =?";
				ShowKuanModel skmp = new ShowKuanModel();
				skmp.query(sql, parasp);
				
				// ѭ���Ľ��������Ʒ����������Ϣ�����ӿ���м�ȥ���Ӧ�Ĺ�������
				int j = 0;
				boolean b = false;
				System.out.println(skmp.getRowCount());
				while (j <= skmp.getRowCount()-1) {
					
					String id = (String)skmp.getValueAt(j, 0);
					String num = (String)skmp.getValueAt(j, 1);
					
					// ��ȡ����temp������
					String[] addrecord = {id, num};
					// ��һ�μ������е������Ƿ�����
					int truenum = ShowKuanModel.get_p_num(id);
					if (truenum < Integer.valueOf(num)) {
						
						JOptionPane.showMessageDialog(this, "<html><font size = '5'>"+id+"��Ʒ�Ŀ��Ϊ��<font color = 'red'>"+truenum+"��<br/></font>��������ɹ�����Ϊ"+num+"�Ĺ���</font>��", "����ܰ��ʾ", JOptionPane.WARNING_MESSAGE);
						return;
					}
					// 1.����������Ϣ��
					String SalRecord = "insert into SellInfo (Pid, Num, OutDate) values (?, ?, getdate())";
					b = ShowKuanModel.update(SalRecord, addrecord);
					
					// 2.��ȥ����ж�Ӧ������
					String[] subparas = {num, id};
					ShowKuanModel.update("update Stcok set Num = Num-? where Pid = ?", subparas);
					j++;
				}
				
				// �������ɹ������temp�����Ϣ
				if (b == true) {
					
					cleartemp();
				}
			}
		}
		// ���ÿ�ݼ�����
		private void setQkey(ActionListener actionListener, int KeyEvent, int InputEvent) {
			
			Tools.selsectAll(getid);
			KeyStroke keystroke = KeyStroke.getKeyStroke(KeyEvent, InputEvent);
			registerKeyboardAction(actionListener,keystroke,JComponent.WHEN_IN_FOCUSED_WINDOW);
		}
		@Override
		public void focusGained(FocusEvent e) {
			// TODO Auto-generated method stub
			Component comp =e.getComponent();
			if (comp instanceof JTextField) {
				
				Tools.selsectAll(getid);
				Tools.selsectAll(getnum);
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
