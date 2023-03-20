package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import analizadores.parser;
import java_cup.runtime.Symbol;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class FrmPrincipal extends JFrame {

	private JPanel contentPane;
	private JTextField textResult;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmPrincipal frame = new FrmPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 935, 794);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextArea txtcod = new JTextArea();
		txtcod.setBounds(46, 207, 425, 386);
		contentPane.add(txtcod);
		
		JButton btnAnalisisLex = new JButton("Analizar");
		btnAnalisisLex.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				analizadores.parser parse;
	        	parse = new analizadores.parser( new analizadores.Lexico(new StringReader(txtcod.getText().toString())));
	            try {
					parse.parse();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					
				}
				
			}
		});
		btnAnalisisLex.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnAnalisisLex.setBounds(702, 145, 187, 37);
		contentPane.add(btnAnalisisLex);
		JLabel lblNewLabel = new JLabel("Analizador Lexico");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 33));
		lblNewLabel.setBounds(334, 25, 269, 96);
		contentPane.add(lblNewLabel);
		
		textResult = new JTextField();
		textResult.setColumns(10);
		textResult.setBounds(544, 193, 345, 539);
		contentPane.add(textResult);
		
		
	}
}
