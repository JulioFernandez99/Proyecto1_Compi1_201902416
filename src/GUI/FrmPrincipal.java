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
import javax.swing.plaf.metal.*;
import javax.swing.table.DefaultTableModel;

import com.jtattoo.plaf.acryl.AcrylLookAndFeel;
import com.jtattoo.plaf.aero.AeroLookAndFeel;
import com.jtattoo.plaf.bernstein.BernsteinLookAndFeel;
import com.jtattoo.plaf.fast.FastLookAndFeel;
import com.jtattoo.plaf.hifi.HiFiLookAndFeel;
import com.jtattoo.plaf.luna.LunaLookAndFeel;

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
import javax.swing.LookAndFeel;
import javax.swing.JTable;
import javax.swing.JMenuBar;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

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
	private JMenu mnNewMenu_2;
	private JMenuItem mntmNewMenuItem_4;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmPrincipal frame = new FrmPrincipal();
					
					frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
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
            fichero = new FileWriter("src/Reportes/ERRORES_201902416/" + "Errores" + ".html");
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
	
	public FrmPrincipal() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		setTitle("Proyecto 1");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setBounds(100, 100, 1137, 737);
		
		menuBar = new JMenuBar();
		menuBar.setBorderPainted(false);
		menuBar.setForeground(Color.WHITE);
		menuBar.setBackground(new Color(0, 0, 0));
		setJMenuBar(menuBar);
		
		mnNewMenu = new JMenu("Archivo");
		mnNewMenu.setBackground(new Color(0, 139, 139));
		mnNewMenu.setForeground(Color.WHITE);
		menuBar.add(mnNewMenu);
		
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Abrir");
		mntmNewMenuItem.setBackground(new Color(0, 0, 0));
		mntmNewMenuItem.setForeground(Color.WHITE);
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
		mntmNewMenuItem_1.setForeground(Color.WHITE);
		mntmNewMenuItem_1.setBackground(new Color(0, 0, 0));
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
		mntmNewMenuItem_2.setForeground(Color.WHITE);
		mntmNewMenuItem_2.setBackground(new Color(0, 0, 0));
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser guardar = new JFileChooser();
			    guardar.showSaveDialog(null);
			    guardar.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

			    File archivo = guardar.getSelectedFile();
			    //System.out.println("Ruta a guardar: "+archivo+".olc");
			   
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
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Salir");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mntmNewMenuItem_3.setForeground(Color.WHITE);
		mntmNewMenuItem_3.setBackground(new Color(0, 0, 0));
		mnNewMenu.add(mntmNewMenuItem_3);
		
		JMenu mnNewMenu_1 = new JMenu("Manuales");
		mnNewMenu_1.setBackground(new Color(0, 0, 0));
		mnNewMenu_1.setForeground(Color.WHITE);
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_6 = new JMenuItem("Usuario");
		mntmNewMenuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String url=System.getProperty("user.dir")+"\\src\\Manuales\\ManualDeUsuario.pdf";
				System.out.println(url);
				try {
					ProcessBuilder p=new ProcessBuilder();
					p.command("cmd","/c",url);
					p.start();
				} catch (Exception e1) {
				         e1.fillInStackTrace();
				}
			}
		});
		mntmNewMenuItem_6.setForeground(Color.WHITE);
		mntmNewMenuItem_6.setBackground(new Color(0, 0, 0));
		mnNewMenu_1.add(mntmNewMenuItem_6);
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Tecnico");
		mntmNewMenuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String url=System.getProperty("user.dir")+"\\src\\Manuales\\ManualTecnico.pdf";
				System.out.println(url);
				try {
					ProcessBuilder p=new ProcessBuilder();
					p.command("cmd","/c",url);
					p.start();
				} catch (Exception e1) {
				         e1.fillInStackTrace();
				}
			}
		});
		mntmNewMenuItem_5.setForeground(Color.WHITE);
		mntmNewMenuItem_5.setBackground(new Color(0, 0, 0));
		mnNewMenu_1.add(mntmNewMenuItem_5);
		
		mnNewMenu_2 = new JMenu("Gramatica");
		mnNewMenu_2.setForeground(Color.WHITE);
		mnNewMenu_2.setBackground(new Color(0, 139, 139));
		menuBar.add(mnNewMenu_2);
		
		mntmNewMenuItem_4 = new JMenuItem("Gramatica");
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String url=System.getProperty("user.dir")+"\\src\\Manuales\\Gramatica.pdf";
				//System.out.println(url);
				try {
					ProcessBuilder p=new ProcessBuilder();
					p.command("cmd","/c",url);
					p.start();
				} catch (Exception e1) {
				         e1.fillInStackTrace();
				}
			}
		});
		mntmNewMenuItem_4.setForeground(Color.WHITE);
		mntmNewMenuItem_4.setBackground(new Color(0, 0, 0));
		mnNewMenu_2.add(mntmNewMenuItem_4);
		
		JMenu mnNewMenu_3 = new JMenu("Reportes");
		mnNewMenu_3.setForeground(Color.WHITE);
		menuBar.add(mnNewMenu_3);
		
		JMenuItem mntmNewMenuItem_7 = new JMenuItem("Errores");
		mntmNewMenuItem_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String url=System.getProperty("user.dir")+"\\src\\Reportes\\Errores_201902416\\Errores.html";
				//System.out.println(url);
				try {
					ProcessBuilder p=new ProcessBuilder();
					p.command("cmd","/c",url);
					p.start();
				} catch (Exception e1) {
				         e1.fillInStackTrace();
				}
			}
		});
		mntmNewMenuItem_7.setBackground(Color.BLACK);
		mntmNewMenuItem_7.setForeground(Color.WHITE);
		mnNewMenu_3.add(mntmNewMenuItem_7);
		
		JMenuItem mntmNewMenuItem_8 = new JMenuItem("Salidas");
		mntmNewMenuItem_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String url=System.getProperty("user.dir")+"\\src\\Reportes\\SALIDAS_201902416\\Reporte.JSON";
				//System.out.println(url);
				try {
					ProcessBuilder p=new ProcessBuilder();
					p.command("cmd","/c",url);
					p.start();
				} catch (Exception e1) {
				         e1.fillInStackTrace();
				}
			}
		});
		mntmNewMenuItem_8.setForeground(Color.WHITE);
		mntmNewMenuItem_8.setBackground(Color.BLACK);
		mnNewMenu_3.add(mntmNewMenuItem_8);
		
		JMenuBar menuBar_1 = new JMenuBar();
		menuBar.add(menuBar_1);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(70, 130, 180));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		 scrollPane = new JScrollPane();
		scrollPane.setBounds(52, 87, 444, 389);
		contentPane.add(scrollPane);
		
		txtcod = new JTextArea();
		scrollPane.setViewportView(txtcod);
		
		
		
		
		JButton btnAnalisisLex = new JButton("Analizar");
		btnAnalisisLex.setForeground(Color.WHITE);
		btnAnalisisLex.setBackground(Color.BLACK);
		btnAnalisisLex.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vaciarTable();
				errs="";
				if(!txtcod.getText().equals("")) {
					
				
				//txtreporte.setText(ruta+"olc");
				analizadores.parser parse;
	        	parse = new analizadores.parser( new analizadores.Lexico(new StringReader(txtcod.getText().toString())));
	            try {
	            	
					parse.parse();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					
				}
			}
				else {
					vaciarTable();
					JOptionPane.showMessageDialog(null, "No hay codigo para analizar",
							  "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnAnalisisLex.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnAnalisisLex.setBounds(615, 184, 121, 37);
		contentPane.add(btnAnalisisLex);
		
		s1=new JScrollPane();
		mt=new DefaultTableModel();
		
		String ids[]= {"#","Descripcion","Linea","Columna"};
		mt.setColumnIdentifiers(ids);
		
		table = new JTable(mt);
		table.setBounds(606, 376, 320, 228);
		s1.setBounds(51, 522, 1169, 145);
		s1.setViewportView(table);
		contentPane.add(s1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(830, 87, 390, 389);
		contentPane.add(scrollPane_1);
		
		txtreporte = new JTextArea();
		scrollPane_1.setViewportView(txtreporte);
		
		JLabel lblNewLabel = new JLabel("EXREGAN");
		lblNewLabel.setFont(new Font("Cooper Black", Font.PLAIN, 32));
		lblNewLabel.setBounds(581, 11, 234, 56);
		contentPane.add(lblNewLabel);
		
		JButton btnLimpiiar = new JButton("Limpiar");
		btnLimpiiar.setForeground(Color.WHITE);
		btnLimpiiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vaciarTable();
				txtcod.setText("");
				txtreporte.setText("");
			}
		});
		btnLimpiiar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnLimpiiar.setBackground(Color.BLACK);
		btnLimpiiar.setBounds(615, 267, 121, 37);
		contentPane.add(btnLimpiiar);
		
		
		
		
		
		
		 
		
		
		
	}
}
