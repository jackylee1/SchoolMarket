/**
 * 
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
public class ProductTo_View extends JDialog implements ActionListener, MouseListener {
	
	//ȫ�ֵ�λ�ñ��������ڱ�ʾ����ڴ����ϵ�λ��
	static Point origin = new Point();
	
	// �������
	JButton close;
	JPanel showinput, all;
	JLabel id, num, date, who;
	JTextField idt, numt, datet, whot;

	JButton confirm, cancel;
	
	public static String ptype;
	
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
	// ��ʼ������������Ϣ�����
	public void initall() {
		
		// ���ô������ʽΪ��ǰϵͳ����ʽ
		try {
			
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		all = new JPanel(null);
		all.setBackground(Color.white);
		all.setBounds(0, 0, 560, 498);
		all.setBorder(new MatteBorder(3, 3, 3, 3, new Color(60, 148, 212)));		
	}
	// ��ʼ���رհ�ť
	public void initColse() {
			
		// �رհ�ť
		close = new JButton(new ImageIcon("image/JDialogClose.png"));
		close.setRolloverIcon(new ImageIcon("image/JDialogCloseC.png"));
		close.setBounds(525, 13, 22, 22);
		close.setForeground(Color.red);
		setbutton(close, 1);
	}
	// ��ʾ������Ϣ����ʼ��
	private void initShowinput() {
		
		showinput = new JPanel(new GridLayout(4, 2, -100, 10));
		showinput.setBounds(50, 40, 400, 200);
		
		id = new JLabel("  ��Ʒ���");
		setlab(id);
		idt = new JTextField();
		setjtf(idt);
		
		num = new JLabel("  ��Ʒ����");
		setlab(num);
		numt = new JTextField();
		setjtf(numt);
		
		date = new JLabel("  �������");
		setlab(date);
		datet = new JTextField();
		setjtf(datet);
		datet.setText(Tools.getlocaldatetime());
		datet.setEditable(false);
		datet.setForeground(Color.GRAY);
		
		who = new JLabel("�ǼǸ�����");
		setlab(who);
		whot = new JTextField();
		setjtf(whot);
		
		showinput.setOpaque(false);
		
		all.add(showinput);
	}

	public void initWindowsStyle() {
		
		confirm = new JButton("ȷ ��");
		confirm.setBounds(100, 350, 110, 50);
		setbutton(confirm, 2);
		cancel = new JButton("�� ��");
		cancel.setBounds(300, 350, 110, 50);
		setbutton(cancel, 2);
		
		all.add(confirm);
		all.add(cancel);
		
		this.add(close);
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
	// ���캯��1
	public ProductTo_View() {
		
		initall();
		initColse();
		initShowinput();
		initWindowsStyle();
	}
	
	// ���캯��2
	public ProductTo_View(ProductModel pm, int selrow) {
		
		initall();
		initColse();
		initShowinput();
		// ���ò�Ʒ���
		idt.setText(pm.getValueAt(selrow, 0).toString());
		initWindowsStyle();

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
			String num = numt.getText().trim();
			String todate = datet.getText();
			String forwho = whot.getText().trim();
			
			// 1.ȷ��������Ϣ����Ϊ��
			if (pid.equals("") || num.equals("") || todate.equals("") || forwho.equals("")) {
				
				JOptionPane.showMessageDialog(this, "��Ϣ����Ϊ�գ��������Ӧ����Ϣ");
				return;
			}
			
			// 2.ȷ�������ǺϷ���
			if (!Tools.isNum(num) || Double.valueOf(num) <= 0.0) {
				
				JOptionPane.showMessageDialog(this, "<html><font style = 'font-size:16'>����������Ƿ���<br/><br/>�Ƿ�����������⣺<br/>1.�Ƿ����0?<br/>2.�Ƿ��ǺϷ�������?<br/><br/>", " ��ܰ��ʾ", JOptionPane.WARNING_MESSAGE);
				return;
			}
			
			// 3.�ж��Ƿ���ڸò�Ʒ
			if (!ProductModel.check("select count(*) from ProductInfo where Pid = ?", pid)) {
				
				int i = JOptionPane.showConfirmDialog(this, "<html><br/><font style= 'font-size:18'>û�в�Ʒ���Ϊ:<font color = 'red'>"+pid+"</font>���Ĳ�Ʒ��Ϣ��<br/>�Ƿ���Ҫ���?</font><br/><br/>", " ��ܰ��ʾ", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				if (i == 0) {
					
					new Add_Product_View(this.getX()+20, this.getY()+50);
				}else {
					
					return;
				}
			}else {
				
				String addsql = "insert into ProductToInfo values (?, ?, ?, ?)";
				pid = ProductModel.getpid(pid);
				String[] addparas = {pid, num, todate, forwho};
				boolean b = ProductModel.update(addsql, addparas);
				
				while (!b) {
					
					JOptionPane.showMessageDialog(this, "��Ǹ��֪ͨ�������û�гɹ�");
					return;
				}
				// ����ɹ��ˣ�������ʾ�ɹ���Ϣ�����ṩѡ���Ƿ����
				if ( b == true) {
					
					// ���²�Ʒ�����еĿ������
					// 1.���������Ƿ��иò�Ʒ
					if (ProductModel.check("select count(*) from Stcok where Pid = ?", pid)) {
						
						// ��ʾ�У���������
						String[] paras = {pid};
						b = ProductModel.update("update Stcok set Num = Num + num where Pid = ?", paras);
						if (b) {
							System.out.println("���¿��ɹ�");
						}
					} else {
						
						String[] paras = {pid, num};
						b = ProductModel.update("insert into Stcok values (?, ?)", paras);
						if (b) {
							System.out.println("������ɹ�");
						}
					}
					this.dispose();
					int i = JOptionPane.showConfirmDialog(this, "<html><font style = 'font-size:15'>���ɹ����Ƿ�������?<br/>", " ��ܰ��ʾ", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if (i == 0) {				
						
						new ProductTo_View();
					}
				}		
			}
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
					
					AWTUtilities.setWindowOpacity(ProductTo_View.this, alpha+=0.1);
				}
				else {
					AWTUtilities.setWindowOpacity(ProductTo_View.this, 1);
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
		
	}

}

