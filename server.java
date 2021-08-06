package chatApp;

import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;


public class server extends JFrame implements ActionListener{
		JPanel p1;
		JTextField t1; 
		JButton b1;
		static JTextArea a1;
		static ServerSocket sk;		//to connect to client we are creating sockets 
		static Socket s;
		static DataInputStream din;
		static DataOutputStream dout;
	
	server(){
		
		//to set green color at top
		p1= new JPanel();
		p1.setLayout(null);
		p1.setBackground(new Color(7,94,84));
		p1.setBounds(0,0,350,50);
		add(p1);
		
		
		
		//to place image icon <-
		ImageIcon i1= new ImageIcon(ClassLoader.getSystemResource("chatApp/icons/arrow.png"));
		Image i2= i1.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT);
		ImageIcon i3= new ImageIcon(i2);
		JLabel l1= new JLabel(i3);
		l1.setBounds(5,15,30,30);
		p1.add(l1);
		
		
		//to function back button 
		l1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent ae){
				System.exit(0);
			}
			
		});
		
		
		
		//to add dp
		ImageIcon i4= new ImageIcon(ClassLoader.getSystemResource("chatApp/icons/family.jpg"));
		Image i5= i4.getImage().getScaledInstance(60,60,Image.SCALE_DEFAULT);
		ImageIcon i6= new ImageIcon(i5);
		JLabel l2= new JLabel(i6);
		l2.setBounds(40,3,60,60);
		p1.add(l2);
		
		
		
		//to set the icons
		ImageIcon i7= new ImageIcon(ClassLoader.getSystemResource("chatApp/icons/video.png"));
		Image i8= i7.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT);
		ImageIcon i9= new ImageIcon(i8);
		JLabel l5= new JLabel(i9);
		l5.setBounds(220,12,30,30);
		p1.add(l5);
		
		
		ImageIcon i10= new ImageIcon(ClassLoader.getSystemResource("chatApp/icons/phone.png"));
		Image i11= i10.getImage().getScaledInstance(35,30,Image.SCALE_DEFAULT);
		ImageIcon i12= new ImageIcon(i11);
		JLabel l6= new JLabel(i12);
		l6.setBounds(270,12,30,30);
		p1.add(l6);
		
		
		ImageIcon i13= new ImageIcon(ClassLoader.getSystemResource("chatApp/icons/3icon.png"));
		Image i14= i13.getImage().getScaledInstance(13,25,Image.SCALE_DEFAULT);
		ImageIcon i15= new ImageIcon(i14);
		JLabel l7= new JLabel(i15);
		l7.setBounds(310,15,15,25);
		p1.add(l7);
		
		
		//to set the name 
		 JLabel l3=new JLabel("Server");
		 l3.setFont(new Font("SAN_SERIF",Font.BOLD,20));
		 l3.setForeground(Color.white);
		 l3.setBounds(110,5,100,25);
		 p1.add(l3);
		 
		 JLabel l4=new JLabel("Active now");
		 l4.setFont(new Font("SAN_SERIF",Font.BOLD,10));
		 l4.setForeground(Color.white);
		 l4.setBounds(110,30,100,10);
		 p1.add(l4);
		 
		 
		 //to set the text area
		 a1= new JTextArea();
		 a1.setFont(new Font("SAN_SEIF",Font.PLAIN,18));
		 a1.setBounds(3,50,350,420);
		 a1.setEditable(false);
		/* a1.setLineWrap(true);			// to display on right side
		 a1.setWrapStyleWord(true);*/
		 add(a1);
		 
		 
		//to set the text field
		 t1= new JTextField();
		 t1.setBounds(8,470,260,25);
		 t1.setFont(new Font("SAN_SERIF", Font.PLAIN,16));
		 add(t1);
		 
		
		 //to set send button
		 b1 = new JButton("send");
		 b1.setBounds(270,470,70,25);
		 b1.setBackground(new Color(7,94,84));
		 b1.setForeground(Color.WHITE);
		 b1.addActionListener(this);    	//to perform send button action
		 add(b1);
		 
		 
		 
		//to set the layout
		setLayout(null);
		setSize(350,500);
		setLocation(250,175);
		setUndecorated(true);
		setVisible(true);
	}
	
	
	public  void actionPerformed(ActionEvent ae) {
		try {
		String out = t1.getText();
		a1.setText(a1.getText()+ "\n\t\t" + out);		//add \t to display on right side
		t1.setText("");
		dout.writeUTF(out);							//to send the message 
		
		}catch(Exception e) {}
	}
	
	
	public static void main(String[] args){
		new server().setVisible(true);
		
		
		String msgip ="";
		try {
														//socket code
			sk = new ServerSocket(5000);
			s= sk.accept();
			
			din = new DataInputStream(s.getInputStream());
			dout= new DataOutputStream(s.getOutputStream());
			
			msgip=din.readUTF();
			a1.setText(a1.getText()+ "\n" + msgip);
					
			
			sk.close();
			s.close();
			
		}catch(Exception e) {}
		}
		
	}

