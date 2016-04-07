/**
 * ��Ӳ�Ʒ��Ϣ����
 */

package com.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;

import com.model.ProductModel;
import com.mytools.MyFont;
import com.mytools.Tools;
import com.sun.awt.AWTUtilities;

@SuppressWarnings("serial")
public class Chang_Product_View extends JDialog implements MouseListener, ActionListener {
	
	//ȫ�ֵ�λ�ñ��������ڱ�ʾ����ڴ����ϵ�λ��
	static Point origin = new Point();
	
	// �������
	JButton close;
	JPanel showinput, all;
	JLabel id, name,price, jifen, type;
	JTextField idt, namet,pricet, jifent;
	JComboBox<String> typet;
	JButton confirm, cancel;
	
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
	private void setlab(JLabel jlb) {
		
		showinput.add(jlb);
		jlb.setFont(MyFont.Infolab);
	}
	private void setjtf(final JTextField jtf) {
		
		showinput.add(jtf);
		jtf.setOpaque(false);
		jtf.setFont(MyFont.PaddInfotext);
	}
	// ��ʾ������Ϣ����ʼ��
	private void initShowinput(ProductModel pm, int selrow) {
		
		showinput = new JPanel(new GridLayout(5, 2, -180, 10));
		showinput.setBounds(50, 40, 400, 250);
		
		id = new JLabel("��Ʒ���");
		setlab(id);
		idt = new JTextField();
		idt.setEditable(false);
		idt.setText(pm.getValueAt(selrow, 0).toString());
		setjtf(idt);
		idt.setForeground(Color.GRAY);
		
		name = new JLabel("��Ʒ����");
		setlab(name);
		namet = new JTextField();
		namet.setText(pm.getValueAt(selrow, 1).toString());
		setjtf(namet);
		
		price = new JLabel("��Ʒ�۸�");
		setlab(price);
		pricet = new JTextField();
		pricet.setText(pm.getValueAt(selrow, 2).toString());
		setjtf(pricet);
		
		jifen = new JLabel("��Ʒ����");
		setlab(jifen);
		jifent = new JTextField();
		jifent.setText(pm.getValueAt(selrow, 3).toString());
		setjtf(jifent);
		
		type = new JLabel("��Ʒ���");
		setlab(type);
		
		typet = new JComboBox<String>();
		typet.addItem("���˻���Ʒ");
		typet.addItem("�Ҿ�����Ʒ");
		typet.addItem("����ʳƷ");
		typet.addItem("�����޲�ױϵ");
		typet.addItem("����������Ʒ");
		typet.addItem("��������ˬϵ");
		typet.addItem("�������ر���");
		typet.addItem("����������Ʒ");
		typet.addItem("�������");
		typet.addActionListener(this);
		typet.setSelectedItem(pm.getValueAt(selrow, 4));
		typet.setFont(MyFont.PaddInfotext);
		showinput.add(typet);
		showinput.setOpaque(false);
		
		all.add(showinput);
	}
	// ���캯��
	public Chang_Product_View(ProductModel pm, int selrow) {
		
		// ���ô������ʽΪ��ǰϵͳ����ʽ
		try {
			
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		// �رհ�ť
		close = new JButton(new ImageIcon("image/JDialogClose.png"));
		close.setRolloverIcon(new ImageIcon("image/JDialogCloseC.png"));
		close.setBounds(525, 13, 22, 22);
		close.setForeground(Color.red);
		setbutton(close, 1);
		
		confirm = new JButton("ȷ ��");
		confirm.setBounds(100, 350, 110, 50);
		setbutton(confirm, 2);
		cancel = new JButton("ȡ ��");
		cancel.setBounds(300, 350, 110, 50);
		setbutton(cancel, 2);
		
		this.add(close);
		
		
		all = new JPanel(null);
		all.setBackground(Color.white);
		all.setBounds(0, 0, 560, 498);
		all.setBorder(new MatteBorder(2, 2, 2, 2, Color.GRAY));
		initShowinput(pm, selrow);
		all.add(confirm);
		all.add(cancel);
		
		this.add(all);
		this.setUndecorated(true);
		this.setLayout(null);
		this.setSize(560, 498);
		this.setLocationRelativeTo(null);
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
		if (e.getSource() == confirm) {
			
			String pid = idt.getText().trim();
			String pname = namet.getText().trim();
			String pprice = pricet.getText().trim();
			String pjifen = jifent.getText().trim();
			String ptype = typet.getSelectedItem().toString().trim();
			
			if (pid.equals("") || pname.equals("") || pprice.equals("") || pjifen.equals("") || ptype.equals("")) {
				
				JOptionPane.showMessageDialog(this, "��Ϣ����Ϊ�գ��������Ӧ����Ϣ");
				return;
			}
			if (!(Tools.isNum(pprice) || Tools.isNum(pjifen))) {
				
				JOptionPane.showMessageDialog(this, "<html><font style = 'font-size:18'>��Ʒ�۸���߲�Ʒ���ַǷ������顡<br/><br/>�Ƿ�����������⣺<br/>1.�Ƿ����0?<br/>2.�Ƿ��ǺϷ�������?<br/><br/>", " ��ܰ��ʾ", JOptionPane.WARNING_MESSAGE);
				return;
			}
			String addsql = "update ProductInfo set PName = ?, Price = ?, JFen = ?, Ptype = ? where Pid = ?";
			String[] addparas = {pname, pprice, pjifen, ptype, pid};
			boolean b = ProductModel.update(addsql, addparas);
			while (!b) {
				
				JOptionPane.showMessageDialog(this, "��Ǹ��֪ͨ�����޸�ʧ��");
				return;
			}
			
			JOptionPane.showMessageDialog(this, "�޸����");
			this.dispose();
		}
		if (e.getSource() == cancel) {
			
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
					
					AWTUtilities.setWindowOpacity(Chang_Product_View.this, alpha+=0.1);
				}
				else {
					AWTUtilities.setWindowOpacity(Chang_Product_View.this, 1);
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
		if (e.getSource() == typet) {
			
			if (typet.getSelectedItem().equals("�������")) {
				
				typet.setEditable(true);
			}else {
				
				typet.setEditable(false);
			}
		}
	}

}



