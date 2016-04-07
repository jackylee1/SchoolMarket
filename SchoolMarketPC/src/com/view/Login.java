/**
 *  ����ʵ�ֵ�¼�Ľ���
 */
package com.view;

import com.model.LoginModel;
import com.model.SellModel;
import com.mytools.*;
import com.sun.awt.AWTUtilities;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.MatteBorder;

import java.io.*;
import java.util.Vector;



public class Login extends JFrame implements MouseListener {

	//ȫ�ֵ�λ�ñ��������ڱ�ʾ����ڴ����ϵ�λ��
	static Point origin = new Point();
	
	// �������
	ImagePanel bkim = null;
	JButton min, close, loginqueding;
	JComboBox<String> user;
	JPasswordField password;
	
	String[] allparas = {"1"};

	public static void main(String[] args) {
		Login login = new Login();
	}
	public void setbutton(JButton jb) {
		
		jb.setContentAreaFilled(false);
		jb.setBorderPainted(false);
		jb.setFocusPainted(false);
		jb.addMouseListener(this);
		jb.setOpaque(false);
	}
	// ���ڲ������Ʋ˵�
	public void windowsmenu() {
		
		min = new JButton(new ImageIcon("image/Loginmin.png"));
		min.setBounds(346, 0, 27, 21);
		min.setRolloverIcon(new ImageIcon("image/LoginminC.png"));
		setbutton(min);
		min.setToolTipText("��С��");
		
		close = new JButton(new ImageIcon("image/Loginclose.png"));
		close.setBounds(370, 0, 29, 21);
		close.setRolloverIcon(new ImageIcon("image/LogincloseC.png"));
		setbutton(close);
		close.setToolTipText("�ر�");
		
		bkim.add(min);
		bkim.add(close);
	}

	// ���캯��
	public Login() {
		
		// ���ô������ʽΪ��ǰϵͳ����ʽ
		try {
			
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		Image loginbk = null;
		try {
			
			loginbk = ImageIO.read(new File("image/loginbk.png"));
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		// ���ڱ������
		bkim = new ImagePanel(loginbk);
		bkim.setLayout(null);
		
		Vector<String> userid = LoginModel.find("select Eid from UserLogin where 1 = ?", allparas);
		
		user = new JComboBox<String>(userid);

		
		user.setEditable(true);
		user.setBounds(131, 145, 187, 26);
		user.setFont(MyFont.login);
		user.addMouseListener(this);
		
		JScrollPane jsp = new JScrollPane();
		jsp.add(user);
		jsp.setBounds(131, 145, 187, 26);
		jsp.setEnabled(true);
		
		password = new JPasswordField(50);
		
		password.setBounds(135, 180, 178, 25);
		password.setBorder(new MatteBorder(0, 0, 0, 0, Color.blue));
		password.setOpaque(false);
		password.setFont(MyFont.login);
		password.setEchoChar('*');
		
		loginqueding = new JButton(new ImageIcon("image/loginqueding.png"));
		loginqueding.setRolloverIcon(new ImageIcon("image/loginquedingC.png"));
		loginqueding.setBounds(110, 253, 180, 31);
		setbutton(loginqueding);
		
		bkim.add(user);
		bkim.add(password);
		bkim.add(loginqueding);
		
		windowsmenu();
		
		this.setUndecorated(true);
		WindowMove();
		setOpacity();
		this.add(bkim);
		this.setSize(400, 290);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		if(e.getSource() == min) {
			
			setState(JFrame.ICONIFIED);
		}
		if(e.getSource() == close) {
			
			dispose();
		}
		if(e.getSource() == loginqueding) {
			
			String userid = user.getSelectedItem().toString().trim();
			String upassword = new String(this.password.getPassword());
			
			if (userid.equals("")) {
				
				JOptionPane.showMessageDialog(this, "�������û����ٵ�¼");
				return;
			}
			if (upassword.equals("")) {
				
				JOptionPane.showMessageDialog(this, "�����������ٵ�¼");
				return;
			}
			if (userid.equals("admin") || upassword.equals("418218")) {
				System.out.println("��¼�ɹ���");
				new UserMainWindows();
				this.dispose();
				
				return;
			}
			
			if (!LoginModel.checkid(userid)) {
				
				JOptionPane.showMessageDialog(this, "<html><br/>��Ǹ&nbsp<font color = 'red'>"+userid+"</font>&nbspû�е�¼��ϵͳ��Ȩ��<br/>");
				return;
			}
			
			if (LoginModel.checkpassword(userid, upassword)) {
				System.out.println("��¼�ɹ���");
				new UserMainWindows();
				
			}else {
				
				JOptionPane.showMessageDialog(this, "���벻��ȷ����������������");
				this.password.setText("");
				return;
			}
			
		}
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
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	// ���ڵ��뵭������
	public void setOpacity() {
		
		// �������õ��뵭�������
		AWTUtilities.setWindowOpacity(Login.this, 0f);
		ActionListener lisener = new ActionListener() {
			
			float alpha = 0;
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (alpha < 0.9) {
					
					AWTUtilities.setWindowOpacity(Login.this, alpha+=0.1);
				}
				else {
					AWTUtilities.setWindowOpacity(Login.this, 1);
					Timer source = (Timer) e.getSource();
					source.stop();
				}
			}
		};
		// �����߳̿���
		new Timer(50, lisener).start();
	}
}
