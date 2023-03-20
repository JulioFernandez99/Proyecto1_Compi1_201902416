package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.StringReader;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import analizadores.parser;
import java_cup.runtime.Symbol;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.Font;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JTable;
import javax.swing.JMenuBar;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class FrmPrincipal extends JFrame {

	private JPanel contentPane;
	public String cadena="";
	public static JTable table;
	public static DefaultTableModel mt;
	private JScrollPane s1;
	public static JScrollPane scrollPane;
	private static int cont=1;
	public static JTextArea txtcod;
	private JMenuBar menuBar;
	private JMenu mnNewMenu;
	public static String errs="";
	private JMenuItem mntmNewMenuItem_1;
	public static boolean archOp=false;
	public static String ruta="";
	private JMenuItem mntmNewMenuItem_2;
	public static JTextArea txtreporte;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					
					FrmPrincipal frame = new FrmPrincipal();
				
					frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
					NumeroLinea numeroLinea;
					numeroLinea=new NumeroLinea(txtcod);
					scrollPane.setRowHeaderView(numeroLinea);
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
	public static void generarReporte() {
		String cod="";
		cod+="<!DOCTYPE html>\r\n"
				+ "<html lang=\"en\">\r\n"
				+ "<head>\r\n"
				+ "    <meta charset=\"UTF-8\">\r\n"
				+ "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n"
				+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
				+ "    <title>Document</title>\r\n"
				+ "</head>\r\n"
				+ "<style type=\"text/css\">\r\n"
				+ "    table,th,td{\r\n"
				+ "        border: 1px solid black;\r\n"
				+ "        border-collapse: collapse;\r\n"
				+ "    }\r\n"
				+ "</style>\r\n"
				+ "<body>\r\n"
				+ "    \r\n"
				+ "<table style=\"width: 100%;\">\r\n"
				+ "\r\n"
				+ "    <tr>\r\n"
				+ "  \r\n"
				+ "      <th>#</th>\r\n"
				+ "  \r\n"
				+ "      <th>Descripcion</th>\r\n"
				+ "  \r\n"
				+ "      <th>Linea</th>\r\n"
				+ "    \r\n"
				+ "      <th>Columna</th>\r\n"
				+ "\r\n"
				+ "    </tr>\r\n"
				+errs;
		
		cod+=" </table>\r\n"
				+ "</body>\r\n"
				+ "</html>";
		
		FileWriter fichero = null;
        PrintWriter pw = null;
        try {
            fichero = new FileWriter("src/Reportes/" + "Errores" + ".html");
            pw = new PrintWriter(fichero);
            pw.println(cod);
        } catch (Exception e) {
            System.out.println("error, no se realizo el archivo"+e);
        } finally {
            try {
                if (null != fichero) {
                    fichero.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
		
		
		System.out.println(cod);
	}
	public static void agregarTable(String descripcion,String linea,String columna) {
		
		errs+="    <tr>\r\n";
		errs+="<td>"+cont+"</td>\r\n"
				+ "  \r\n"
				+ "      <td>"+"El caracter \""+descripcion+"\" no pertenece al lenguaje"+"</td>\r\n"
				+ "  \r\n"
				+ "      <td>"+linea+"</td>\r\n"
				+ "      <td>"+columna+"</td>";
		errs+="    </tr>\r\n";
		generarReporte();
		
		mt.addRow(new Object[] {String.valueOf(cont),
				"El caracter \""+descripcion+"\" no pertenece al lenguaje"
				,linea,columna});
		cont++;
		
	}
	public static void vaciarTable(){
		for (int i = 0; i < table.getRowCount(); i++) {
			mt.removeRow(i);
			i-=1;
		}
	}
	
	public FrmPrincipal() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setBounds(100, 100, 1137, 737);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnNewMenu = new JMenu("Archivo");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Abrir");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("hola desde abrir");
				
				JFileChooser chooser=new JFileChooser();
				chooser.showOpenDialog(null);
				File archivo=new File(chooser.getSelectedFile().getAbsolutePath());
				System.out.println("Ruta: "+archivo);
				ruta=archivo.toString();
				
				try {
					String st=new String(Files.readAllBytes(archivo.toPath()));
					txtcod.setText(st);
					archOp=true;
				} catch (Exception e2) {
					// TODO: handle exception
				}
				
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		
		mntmNewMenuItem_1 = new JMenuItem("Guardar");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(archOp==true) {
					FileWriter fichero = null;
			        PrintWriter pw = null;
			        try {
			            fichero = new FileWriter(ruta);
			            pw = new PrintWriter(fichero);
			            pw.println(txtcod.getText());
			        } catch (Exception s) {
			            System.out.println("error, no se realizo el archivo"+s);
			        } finally {
			            try {
			                if (null != fichero) {
			                    fichero.close();
			                }
			            } catch (Exception e2) {
			                e2.printStackTrace();
			            }
			        }
				}
				
			}
		});
		mnNewMenu.add(mntmNewMenuItem_1);
		
		mntmNewMenuItem_2 = new JMenuItem("Guardar como");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser guardar = new JFileChooser();
			    guardar.showSaveDialog(null);
			    guardar.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

			    File archivo = guardar.getSelectedFile();
			    System.out.println("Ruta a guardar: "+archivo+".olc");
			   
			    FileWriter fichero = null;
		        PrintWriter pw = null;
		        try {
		            fichero = new FileWriter(archivo+".olc");
		            pw = new PrintWriter(fichero);
		            pw.println(txtcod.getText());
		        } catch (Exception s) {
		            System.out.println("error, no se realizo el archivo"+s);
		        } finally {
		            try {
		                if (null != fichero) {
		                    fichero.close();
		                }
		            } catch (Exception e2) {
		                e2.printStackTrace();
		            }
		        }
				
				
			}
		});
		mnNewMenu.add(mntmNewMenuItem_2);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		 scrollPane = new JScrollPane();
		scrollPane.setBounds(52, 87, 444, 389);
		contentPane.add(scrollPane);
		
		txtcod = new JTextArea();
		scrollPane.setViewportView(txtcod);
		
		
		
		
		JButton btnAnalisisLex = new JButton("Analizar");
		btnAnalisisLex.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vaciarTable();
				
				txtreporte.setText(ruta+"olc");
				analizadores.parser parse;
	        	parse = new analizadores.parser( new analizadores.Lexico(new StringReader(txtcod.getText().toString())));
	            try {
	            	
					parse.parse();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					
				}
				
			}
		});
		btnAnalisisLex.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnAnalisisLex.setBounds(577, 139, 121, 37);
		contentPane.add(btnAnalisisLex);
		
		s1=new JScrollPane();
		mt=new DefaultTableModel();
		
		String ids[]= {"#","Descripcion","Linea","Columna"};
		mt.setColumnIdentifiers(ids);
		
		table = new JTable(mt);
		table.setBounds(606, 376, 320, 228);
		s1.setBounds(51, 528, 1169, 145);
		s1.setViewportView(table);
		contentPane.add(s1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(830, 87, 390, 389);
		contentPane.add(scrollPane_1);
		
		txtreporte = new JTextArea();
		scrollPane_1.setViewportView(txtreporte);
		
		
		
		
		
		
		 
		
		
		
	}
}
