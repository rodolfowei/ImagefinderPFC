package FindMethod;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.SwingConstants;

import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Ventana {
	
	String ruta_inIMG;
	String IMG_name;

	private JFrame frame;
	private JTextField text_path;
	private JTextField txt_in;
	private JTextField txt_out;
	private JLabel label_in;
	private JLabel label_out;

	
	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ventana window = new Ventana();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	
	public Ventana() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		text_path = new JTextField();
		text_path.setBounds(36, 44, 227, 20);
		frame.getContentPane().add(text_path);
		text_path.setColumns(10);
		
		JButton ButtonBrowse = new JButton("Browse");
		ButtonBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent click) {
			clickBrowse(click);
			}
		});
		
		ButtonBrowse.setBounds(304, 43, 89, 23);
		frame.getContentPane().add(ButtonBrowse);
		
		JButton buttonSearchDB = new JButton("SearchDB");
		buttonSearchDB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent click) {
				clickSearchDBA(click);
			}
		});
		
		buttonSearchDB.setBounds(304, 79, 89, 23);
		frame.getContentPane().add(buttonSearchDB);
		
		JPanel panel_imIN = new JPanel();
		panel_imIN.setBackground(Color.GRAY);
		panel_imIN.setBounds(38, 137, 100, 100);
		frame.getContentPane().add(panel_imIN);
		panel_imIN.setLayout(null);
		
		label_in = new JLabel("IN");
		label_in.setHorizontalAlignment(SwingConstants.CENTER);
		label_in.setBounds(0, 0, 100, 100);
		panel_imIN.add(label_in);
		
		JPanel panel_imOUT = new JPanel();
		panel_imOUT.setBackground(Color.GRAY);
		panel_imOUT.setBounds(148, 137, 100, 100);
		frame.getContentPane().add(panel_imOUT);
		panel_imOUT.setLayout(null);
		
		label_out = new JLabel("OUT");
		label_out.setHorizontalAlignment(SwingConstants.CENTER);
		label_out.setBounds(0, 0, 100, 100);
		panel_imOUT.add(label_out);
		
		
		txt_in = new JTextField();
		txt_in.setHorizontalAlignment(SwingConstants.CENTER);
		txt_in.setText("IN IMAGE");
		txt_in.setBounds(36, 106, 102, 20);
		frame.getContentPane().add(txt_in);
		txt_in.setColumns(10);
		
		txt_out = new JTextField();
		txt_out.setHorizontalAlignment(SwingConstants.CENTER);
		txt_out.setText("FOUND IMAGE");
		txt_out.setBounds(148, 106, 100, 20);
		frame.getContentPane().add(txt_out);
		txt_out.setColumns(10);
	}
	
	private void clickBrowse(ActionEvent click)
	{
		final JFileChooser rutaIMG = new JFileChooser();
		rutaIMG.setMultiSelectionEnabled(false);
		int fichero = rutaIMG.showOpenDialog(rutaIMG);
		
		if (fichero == JFileChooser.APPROVE_OPTION)
		{
			ruta_inIMG = rutaIMG.getSelectedFile().getAbsolutePath();
			IMG_name = rutaIMG.getSelectedFile().getName();
			text_path.setText(ruta_inIMG);
			Image previewIN = Toolkit.getDefaultToolkit().getImage(ruta_inIMG);
			if(previewIN != null)
			{
				
				label_in.setText("");
				ImageIcon icon = new ImageIcon(previewIN.getScaledInstance(label_in.getWidth(), 
																		   label_in.getHeight(), 
																		   Image.SCALE_DEFAULT));
				
				label_in.setIcon(icon);
				
				
			}
		}
	}
	
	
	
	private void clickSearchDBA(ActionEvent click)
	{
		if(!ruta_inIMG.equals(""))
		{
			String in_imagePath = ruta_inIMG;
			
			ImageComparison comparatorobject = new ImageComparison();
			Producto encontrado = comparatorobject.obtainbestmatch(in_imagePath);
			
			String imagefind = encontrado.imagepath;
			IMG_name = encontrado.productname;
			Image find_prod = Toolkit.getDefaultToolkit().getImage(imagefind);
			
			if(find_prod != null)
			{
				label_out.setText("");
				ImageIcon icon = new ImageIcon(find_prod.getScaledInstance(label_out.getWidth(), 
																		   label_out.getHeight(),
																		   Image.SCALE_DEFAULT));
				label_out.setIcon(icon);
			}
			
			
		}
	}
	
	public void runwindow()
	{
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				try {
					Ventana window = new Ventana();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
	}
	
}
