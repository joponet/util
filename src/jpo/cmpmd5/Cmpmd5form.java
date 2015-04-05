package jpo.cmpmd5;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Cmpmd5form {
	JFrame window;
	JButton btFile1;
	JButton btFile2;
	JTextField tFile1;
	JTextField tFile2;
	JTextField tSize1;
	JTextField tSize2;
	JTextField tDate1;
	JTextField tDate2;
	JTextField tMd51;
	JTextField tMd52;
	
	public Cmpmd5form() {
		// window
		window = new JFrame();
		window.setLayout(null);
		window.setResizable(false);
		window.setLocation(500, 300);
		window.setTitle("Compara Arxius amb MD5");
		window.setSize(400, 300);
		window.addWindowListener(formAction);
		int x1=50;
		int x2=200;
		int y1=10;
		int yi=0;
		int yy=50;
		int sx=150;
		int sy=30;
		// Buto 1
		btFile1 = new JButton();
		btFile1.setText("Fitxer 1");
		btFile1.setBounds(x1, y1+yy*yi, sx, sy);
		btFile1.setActionCommand("file1");
		btFile1.addActionListener(btListener);
		window.add(btFile1);
		// Buto 2
		btFile2 = new JButton();
		btFile2.setText("Fitxer 2");
		btFile2.setBounds(x2, y1+yy*yi++, sx, sy);
		btFile2.setActionCommand("file2");
		btFile2.addActionListener(btListener);
		window.add(btFile2);
		// File 1
		tFile1 = new JTextField();
		tFile1.setBounds(x1, y1+yy*yi, sx, sy);
		window.add(tFile1);
		// File 2
		tFile2 = new JTextField();
		tFile2.setBounds(x2, y1+yy*yi++, sx, sy);
		window.add(tFile2);
		// Size 1
		tSize1 = new JTextField();
		tSize1.setBounds(x1, y1+yy*yi, sx, sy);
		tSize1.setHorizontalAlignment(SwingConstants.RIGHT);
		window.add(tSize1);
		// Size 2
		tSize2= new JTextField();
		tSize2.setBounds(x2, y1+yy*yi++, sx, sy);
		tSize2.setHorizontalAlignment(SwingConstants.RIGHT);
		window.add(tSize2);
		// Date 1
		tDate1= new JTextField();
		tDate1.setBounds(x1, y1+yy*yi, sx, sy);
		tDate1.setHorizontalAlignment(SwingConstants.RIGHT);
		window.add(tDate1);
		// Date 2
		tDate2= new JTextField();
		tDate2.setBounds(x2, y1+yy*yi++, sx, sy);
		window.add(tDate2);
		tDate2.setHorizontalAlignment(SwingConstants.RIGHT);
		// MD5 1
		tMd51= new JTextField();
		tMd51.setBounds(x1, y1+yy*yi, sx, sy);
		window.add(tMd51);
		// MD5 2
		tMd52= new JTextField();
		tMd52.setBounds(x2, y1+yy*yi++, sx, sy);
		window.add(tMd52);
	}
	
	public void start() {
		window.setVisible(true);
	}
	
	String md5(File file) {
		MessageDigest md=null;
		FileInputStream inputStream = null;
		byte[] buffer = new byte[2048];
		int bread;

		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		md.reset();

		try {
			inputStream = new FileInputStream(file);
			while ((bread=inputStream.read(buffer))>0) {
				md.update(buffer, 0, bread);
			}
			inputStream.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return String.format("%1s", new BigInteger(1,md.digest()).toString(16));
	}
	
	WindowListener formAction = new WindowListener() {

		@Override
		public void windowActivated(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowClosed(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowClosing(WindowEvent arg0) {
			System.exit(0);
			
		}

		@Override
		public void windowDeactivated(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowDeiconified(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowIconified(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowOpened(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

	};
	
	ActionListener btListener = new ActionListener () {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			JFileChooser fc;
			fc = new JFileChooser();
			File cdir = new File("D:\\5-personal\\01-fotos\\01 jordi\\2005\\01-12 Altres");
			fc.setCurrentDirectory(cdir);
			File file;
			switch (arg0.getActionCommand()) {
			case "file1":
				fc.showOpenDialog(window);
				file = fc.getSelectedFile();
				tFile1.setText(file.getName());
				tSize1.setText(String.format("%1$,d", file.length()));
				tDate1.setText(String.format("%1$td-%1$tm-%1$tY %1$tH:%1$tM:%1$tS", file.lastModified()));
				tMd51.setText(md5(file));
				break;
			case "file2":
				fc.showOpenDialog(window);
				file = fc.getSelectedFile();
				tFile2.setText(file.getName());
				tSize2.setText(String.format("%1$,d", file.length()));
				tDate2.setText(String.format("%1$td-%1$tm-%1$tY %1$tH:%1$tM:%1$tS", file.lastModified()));
				tMd52.setText(md5(file));
				break;
			}
			if (tMd51.getText().equals(tMd52.getText())) {
				tMd51.setBackground(Color.GREEN);
				tMd52.setBackground(Color.GREEN);
			}
			else {
				tMd51.setBackground(Color.RED);
				tMd52.setBackground(Color.RED);
			}
		}
		
	};
}
