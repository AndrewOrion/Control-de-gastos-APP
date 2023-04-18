package vista;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;

import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.awt.Component;

import dao.TransaccionDAO;
import modelo.Transaccion;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.text.DateFormat;
import java.util.Calendar;


import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JTextArea;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.Dimension;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
public class VentanaDatos extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField textCantidad;
	JLabel lblNewLabel_7 = new JLabel("TOTAL CUENTA:");
	JLabel lblTotal = new JLabel("");
    Calendar calendario = Calendar.getInstance();
    private final ButtonGroup buttonGroup = new ButtonGroup();


	/**
	 * Create the frame.
	 */
	public VentanaDatos() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				
				rellenarVentana();
			}
		});
		setTitle("María Belén Haro Dorado");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 771, 673);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(190, 240, 254));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setFont(new Font("Tahoma", Font.PLAIN, 18));
		scrollPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPane.setBounds(10, 109, 737, 223);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setRowMargin(3);
		table.setRowHeight(20);
		table.setIntercellSpacing(new Dimension(4, 4));
		table.setFont(new Font("Tahoma", Font.PLAIN, 15));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Fecha", "Cantidad", "Categor\u00EDa", "Tipo", "Descripci\u00F3n"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, Double.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(35);
		table.getColumnModel().getColumn(3).setPreferredWidth(87);
		table.getColumnModel().getColumn(4).setPreferredWidth(92);
		table.getColumnModel().getColumn(5).setPreferredWidth(152);
		
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel("CONTROL DE GASTOS");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBackground(new Color(64, 0, 128));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBounds(10, 32, 737, 31);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Últimos Gastos:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBackground(new Color(192, 192, 192));
		lblNewLabel_1.setOpaque(true);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(10, 90, 170, 13);
		contentPane.add(lblNewLabel_1);
		
		JButton btnSalir = new JButton("SALIR");
		btnSalir.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnSalir.setBounds(574, 588, 162, 38);
		contentPane.add(btnSalir);
		
		JButton btnModificar = new JButton("MODIFICAR");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int iElemento;
				int iId;
				String sTotal;
				
				iElemento = table.getSelectedRow();
				if (iElemento >= 0) {
					iId = (int) table.getValueAt(iElemento, 0);
					VerTransaccion ventana = new VerTransaccion(iId);
					ventana.setVisible(true);

				}
				else {
					JOptionPane.showMessageDialog(null, "Ninguna fila seleccionada");
				}
			}
		});
		btnModificar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnModificar.setBounds(210, 588, 150, 38);
		contentPane.add(btnModificar);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(226, 247, 254));
		panel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(471, 479, 276, 99);
		contentPane.add(panel);
		panel.setLayout(null);
		lblNewLabel_7.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		lblNewLabel_7.setForeground(new Color(255, 255, 255));
		lblNewLabel_7.setBackground(new Color(0, 128, 255));
		lblNewLabel_7.setOpaque(true);
		
		
		lblNewLabel_7.setBounds(10, 10, 153, 22);
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 18));
		panel.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("€");
		lblNewLabel_8.setBounds(253, 56, 12, 33);
		lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD, 18));
		panel.add(lblNewLabel_8);
		lblTotal.setBackground(new Color(255, 255, 255));
		
		lblTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 35));
		lblTotal.setBounds(50, 51, 193, 38);
		panel.add(lblTotal);
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(217, 249, 255));
		panel_1.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_1.setBounds(10, 353, 451, 225);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblCantidad = new JLabel("Cantidad:");
		lblCantidad.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCantidad.setBounds(10, 24, 59, 22);
		panel_1.add(lblCantidad);
		
		textCantidad = new JTextField();
		textCantidad.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textCantidad.setColumns(10);
		textCantidad.setBounds(97, 21, 119, 27);
		panel_1.add(textCantidad);
		
		JLabel lblNewLabel_2 = new JLabel("€");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setBounds(240, 26, 35, 13);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Tipo:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_3.setBounds(10, 68, 45, 28);
		panel_1.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Categoría:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_4.setBounds(10, 110, 77, 28);
		panel_1.add(lblNewLabel_4);
		
		ImageIcon image0 = new ImageIcon(VentanaDatos.class.getResource("/imagenes/hucha.png"));
		/*
		ImageIcon image1 = new ImageIcon(VentanaDatos.class.getResource("/imagenes/hogar.png"));
		ImageIcon image2 = new ImageIcon(VentanaDatos.class.getResource("/imagenes/pago.png"));
		ImageIcon image3 = new ImageIcon(VentanaDatos.class.getResource("/imagenes/movil.png"));
		ImageIcon image4 = new ImageIcon(VentanaDatos.class.getResource("/imagenes/salud.png"));
		ImageIcon image5 = new ImageIcon(VentanaDatos.class.getResource("/imagenes/coche.png"));
		ImageIcon image6 = new ImageIcon(VentanaDatos.class.getResource("/imagenes/comida.png"));
		ImageIcon image7 = new ImageIcon(VentanaDatos.class.getResource("/imagenes/gasoil.png"));
		ImageIcon image8 = new ImageIcon(VentanaDatos.class.getResource("/imagenes/farmacia.jpg"));
		ImageIcon image9 = new ImageIcon(VentanaDatos.class.getResource("/imagenes/ropa.png"));
		ImageIcon image10 = new ImageIcon(VentanaDatos.class.getResource("/imagenes/bizum.png"));
		ImageIcon image11 = new ImageIcon(VentanaDatos.class.getResource("/imagenes/viaje.png"));
		ImageIcon image12 = new ImageIcon(VentanaDatos.class.getResource("/imagenes/regalo.png"));
		ImageIcon image13 = new ImageIcon(VentanaDatos.class.getResource("/imagenes/ocio.png"));
		ImageIcon image14 = new ImageIcon(VentanaDatos.class.getResource("/imagenes/otros.png"));
		*/
		
        JComboBox comboCategoria = new JComboBox();
       

		comboCategoria.setModel(new DefaultComboBoxModel(new String[] {"Nómina", "Gastos Piso", "Guardo/Ahorro", "Móvil", "Seguro Médico", "Letra Coche", "Salir", "Gasoil", "Farmacia", "Compras", "Bizum", "Viajes", "Regalos", "Ocio", "Otros"}));
		comboCategoria.setForeground(Color.WHITE);
		comboCategoria.setFont(new Font("Tahoma", Font.BOLD, 16));
		comboCategoria.setBackground(new Color(15, 81, 117));
		comboCategoria.setBounds(98, 111, 177, 31);
		panel_1.add(comboCategoria);
		
		JPanel panel_Imagen = new JPanel();
		panel_Imagen.setOpaque(false);
		panel_Imagen.setBounds(308, 40, 133, 102);
		panel_1.add(panel_Imagen);
		panel_Imagen.setLayout(null);
		
		JLabel lblImagen = new JLabel(image0);
		lblImagen.setBounds(0, 0, 133, 102);
		lblImagen.setHorizontalAlignment(JLabel.CENTER);
        lblImagen.setVerticalAlignment(JLabel.CENTER);
        lblImagen.setPreferredSize(getSize());
        
        int anchoLabel = lblImagen.getWidth();
        int altoLabel = lblImagen.getHeight();
        Image imagenRedimensionada = image0.getImage().getScaledInstance(anchoLabel, altoLabel, Image.SCALE_SMOOTH);
        ImageIcon imagenAjustada = new ImageIcon(imagenRedimensionada);
        lblImagen.setIcon(imagenAjustada);

        panel_Imagen.add(lblImagen);

		
		JLabel lblNewLabel_5 = new JLabel("Fecha:");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_5.setBounds(10, 162, 50, 31);
		panel_1.add(lblNewLabel_5);
		
		JSpinner spinnerDia = new JSpinner();
		spinnerDia.setModel(new SpinnerNumberModel(1, 1, 31, 1));
		spinnerDia.setFont(new Font("Tahoma", Font.PLAIN, 14));
		spinnerDia.setBounds(99, 162, 45, 24);
		panel_1.add(spinnerDia);
		spinnerDia.setValue(calendario.get(Calendar.DAY_OF_MONTH));
		
		JSpinner spinnerMes = new JSpinner();
		spinnerMes.setModel(new SpinnerNumberModel(1, 1, 12, 1));
		spinnerMes.setFont(new Font("Tahoma", Font.PLAIN, 14));
		spinnerMes.setBounds(161, 161, 44, 24);
		panel_1.add(spinnerMes);
		spinnerMes.setValue(calendario.get(Calendar.MONTH) + 1);
		
		JSpinner spinnerAno = new JSpinner();
		spinnerAno.setModel(new SpinnerNumberModel(2023, 1988, 5000, 1));
		spinnerAno.setFont(new Font("Tahoma", Font.PLAIN, 14));
		spinnerAno.setBounds(222, 160, 77, 24);
		panel_1.add(spinnerAno);
		spinnerAno.setValue(calendario.get(Calendar.YEAR));
		
		JLabel lblNewLabel_6 = new JLabel("/");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_6.setBounds(209, 169, 16, 13);
		panel_1.add(lblNewLabel_6);
		
		JLabel lblNewLabel_6_1 = new JLabel("/");
		lblNewLabel_6_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_6_1.setBounds(150, 169, 16, 13);
		panel_1.add(lblNewLabel_6_1);
		
		JRadioButton rdbtnGasto = new JRadioButton("GASTO");
		rdbtnGasto.setFont(new Font("Tahoma", Font.BOLD, 12));
		rdbtnGasto.setOpaque(false);
		buttonGroup.add(rdbtnGasto);
		rdbtnGasto.setSelected(true);
		rdbtnGasto.setBounds(96, 73, 70, 21);
		panel_1.add(rdbtnGasto);
		
		JRadioButton rdbtnIngreso = new JRadioButton("INGRESO");
		rdbtnIngreso.setFont(new Font("Tahoma", Font.BOLD, 12));
		rdbtnIngreso.setOpaque(false);
		buttonGroup.add(rdbtnIngreso);
		rdbtnIngreso.setBounds(182, 73, 93, 21);
		panel_1.add(rdbtnIngreso);
		
		
		
		JLabel lblNewLabel_9 = new JLabel("Descripción:");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_9.setBounds(471, 353, 74, 22);
		contentPane.add(lblNewLabel_9);
		
		JTextArea textDescripcion = new JTextArea();
		textDescripcion.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		textDescripcion.setBounds(471, 372, 276, 76);
		contentPane.add(textDescripcion);
		
		JButton btnAnadir = new JButton("AÑADIR");
		btnAnadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String sFecha, sCategoria, sTipo, sDescripcion, sCantidad;
				Double dCantidad = 0.0;
				int iId, iDia, iMes, iAno, iResultado;
				iDia = Integer.valueOf(spinnerDia.getValue().toString());
				iMes = Integer.valueOf(spinnerMes.getValue().toString());
				iAno = Integer.valueOf(spinnerAno.getValue().toString());
				sFecha = iDia + "/" + iMes + "/" + iAno;
				sCategoria = comboCategoria.getSelectedItem().toString();
				sTipo = "Ingreso";
				sDescripcion = textDescripcion.getText();
				sCantidad = textCantidad.getText().replace(',', '.');
				 try {
			            // Intentar convertir la cadena de caracteres a un número de punto flotante (double)
						dCantidad = Double.parseDouble(sCantidad);	
						if (rdbtnGasto.isSelected()) {									
							dCantidad = dCantidad * (-1);	
							sTipo = "Gasto";
						}				
						
						DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
				       
						try {
							java.util.Date fechaUtil = formatoFecha.parse(sFecha);
							Date fechaSql = new Date(fechaUtil.getTime());
							
							Transaccion tra = new Transaccion(fechaSql, dCantidad, sCategoria, 
									sTipo, sDescripcion); 				
							TransaccionDAO transaccionDAO = new TransaccionDAO();
							iResultado = transaccionDAO.insertarTransaccion(tra);		
							if (iResultado == 0) {
								JOptionPane.showMessageDialog(null, "No se ha podido insertar transacción");
							}
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
				            System.out.println("Error al convertir la fecha: " + e1.getMessage());
						}
			        } catch (NumberFormatException e2) {
			            // Si la conversión falla, mostrar un mensaje de error
						JOptionPane.showMessageDialog(null, "Debes introducir un número correcto");
			        }

				rellenarVentana();
			}
		});
		btnAnadir.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnAnadir.setBounds(10, 588, 170, 38);
		contentPane.add(btnAnadir);		
		
		JButton btnNewButton = new JButton("ELIMINAR");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int iElemento=-1;
				int iId;
				int iResultado;
				int iRespuesta;
				
				iElemento = table.getSelectedRow();
				if (iElemento == -1) {
					JOptionPane.showMessageDialog(null, "Ninguna fila seleccionada");
				}
				else {
				iRespuesta = JOptionPane.showConfirmDialog(null, "¿Estás seguro?", "Eliminar", 
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				
				if (iRespuesta == 0)
					{ //si usuario dice SI
						if (iElemento >= 0) {
							iId = (int) table.getValueAt(iElemento, 0); //cojo el id del elemento (fila 0)
							TransaccionDAO transaccionDAO = new TransaccionDAO();
										
							iResultado = transaccionDAO.eliminarTransaccion(iId);
							rellenarVentana();
						
							if (iResultado == 0) {
								JOptionPane.showMessageDialog(null, "No se ha podido eliminar la fila");
							}
						}
					}
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBounds(377, 588, 162, 38);
		contentPane.add(btnNewButton);
	}
	private void rellenarVentana() {
		
		DefaultTableModel modelo = (DefaultTableModel) table.getModel();//siempre
		
		modelo.setRowCount(0);//a cero

      
		TransaccionDAO transaccionDAO = new TransaccionDAO();
		
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
		String sFecha;
		ArrayList<Transaccion> lista = transaccionDAO.obtenerTransacciones();//obtengo y luego recorro
		for (Transaccion tra : lista) {
			Object file [] =
				{
						tra.getId(),
						sFecha = formatoFecha.format(tra.getFecha()),
						
						tra.getCantidad(),
						tra.getCategoria(),
						tra.getTipo(),
						tra.getDescripcion()
				};
		
			modelo.addRow(file);
		}
		
		TransaccionDAO transaccionDAO2 = new TransaccionDAO();
		double tra2 = transaccionDAO2.obtenerTotal();
		String resultadoTexto = String.format("%.2f", tra2);
		if (tra2 <= 0) {
			lblTotal.setForeground(Color.red);
		}
		if (tra2 > 0) {
			lblTotal.setForeground(Color.black);
		}
		
		lblTotal.setText(resultadoTexto);
		
	}
}
