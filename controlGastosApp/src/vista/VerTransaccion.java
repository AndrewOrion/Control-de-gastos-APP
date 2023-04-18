package vista;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dao.TransaccionDAO;
import modelo.Transaccion;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JSpinner;
import javax.swing.JTextArea;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SpinnerNumberModel;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.sql.Date;
import java.util.Calendar;
import javax.swing.SwingConstants;

public class VerTransaccion extends JFrame {

	private JPanel contentPane;
	private int iId;
	private JTextField textId;
	private JTextField textCantidad;
	private String sTotal;
	JComboBox comboTipo = new JComboBox();
	JComboBox comboCategoria = new JComboBox();
	JSpinner spinnerDia = new JSpinner();
	JSpinner spinnerMes = new JSpinner();
	JSpinner spinnerAno = new JSpinner();
	JLabel lblF = new JLabel("");
	JTextArea textDescripcion = new JTextArea();
    Calendar calendario = Calendar.getInstance();


	/**
	 * Create the frame.
	 */
	public VerTransaccion(int id) {
		this.iId = id;
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				ArrayList<Transaccion> lista = new ArrayList<Transaccion>();
				String sId = Integer.toString(iId);
				textId.setText(sId);
				if (sId.equals("") == false) {
					Transaccion tra = new Transaccion();
					TransaccionDAO transaccionDAO = new TransaccionDAO();
					tra = transaccionDAO.obtenerTransaccion(id);
					String sCantidad = Double.toString(tra.getCantidad());
					textCantidad.setText(sCantidad.replace("-",""));
					comboTipo.setSelectedItem(tra.getTipo());
					comboCategoria.setSelectedItem(tra.getCategoria());
					Date fecha = tra.getFecha();
				    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
				    String cadenaFecha = formato.format(fecha);
				    lblF.setText(cadenaFecha);
				    textDescripcion.setText(tra.getDescripcion());
					
				}				
			}	
		});
		setTitle("Modificar transacción");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 894, 547);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(191, 225, 251));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ID:");
		lblNewLabel.setBounds(27, 95, 45, 13);
		contentPane.add(lblNewLabel);
		
		textId = new JTextField();
		textId.setEditable(false);
		textId.setBounds(114, 92, 72, 19);
		contentPane.add(textId);
		textId.setColumns(10);
		
		JLabel lblCantidad = new JLabel("Cantidad:");
		lblCantidad.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCantidad.setBounds(27, 147, 59, 22);
		contentPane.add(lblCantidad);
		
		textCantidad = new JTextField();
		textCantidad.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textCantidad.setColumns(10);
		textCantidad.setBounds(114, 144, 119, 27);
		contentPane.add(textCantidad);
		
		JLabel lblNewLabel_2 = new JLabel("€");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setBounds(257, 149, 35, 13);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Tipo:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_3.setBounds(27, 215, 45, 28);
		contentPane.add(lblNewLabel_3);
		comboTipo.setModel(new DefaultComboBoxModel(new String[] {"Gasto", "Ingreso"}));
		
		comboTipo.setForeground(Color.WHITE);
		comboTipo.setFont(new Font("Tahoma", Font.BOLD, 14));
		comboTipo.setBackground(new Color(15, 81, 117));
		comboTipo.setBounds(114, 213, 96, 31);
		contentPane.add(comboTipo);
		
		JLabel lblNewLabel_4 = new JLabel("Categoría:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_4.setBounds(27, 310, 77, 28);
		contentPane.add(lblNewLabel_4);
		comboCategoria.setModel(new DefaultComboBoxModel(new String[] {"Comida/Bebida", "Pago/Bizum", "Nómina", "Hogar", "Regalo", "Ocio", "Ropa", "Viaje", "Salud", "Otros"}));
		
		comboCategoria.setForeground(Color.WHITE);
		comboCategoria.setFont(new Font("Tahoma", Font.BOLD, 14));
		comboCategoria.setBackground(new Color(15, 81, 117));
		comboCategoria.setBounds(114, 308, 162, 31);
		contentPane.add(comboCategoria);
		
		JLabel lblNewLabel_5 = new JLabel("Fecha:");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_5.setBounds(340, 215, 50, 31);
		contentPane.add(lblNewLabel_5);
		spinnerDia.setModel(new SpinnerNumberModel(1, 1, 31, 1));
		
		spinnerDia.setFont(new Font("Tahoma", Font.PLAIN, 14));
		spinnerDia.setBounds(454, 217, 45, 24);
		contentPane.add(spinnerDia);
		spinnerDia.setValue(calendario.get(Calendar.DAY_OF_MONTH));

		spinnerMes.setModel(new SpinnerNumberModel(1, 1, 12, 1));
		
		spinnerMes.setFont(new Font("Tahoma", Font.PLAIN, 14));
		spinnerMes.setBounds(525, 217, 44, 24);
		contentPane.add(spinnerMes);
		spinnerMes.setValue(calendario.get(Calendar.MONTH) + 1);

		spinnerAno.setModel(new SpinnerNumberModel(2023, 1988, 5000, 1));
		
		spinnerAno.setFont(new Font("Tahoma", Font.PLAIN, 14));
		spinnerAno.setBounds(589, 217, 77, 24);
		contentPane.add(spinnerAno);
		spinnerAno.setValue(calendario.get(Calendar.YEAR)); 

	
		JLabel lblNewLabel_6_1 = new JLabel("/");
		lblNewLabel_6_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_6_1.setBounds(508, 222, 16, 13);
		contentPane.add(lblNewLabel_6_1);
		
		JLabel lblNewLabel_6 = new JLabel("/");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_6.setBounds(579, 222, 16, 13);
		contentPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_9 = new JLabel("Descripción:");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_9.setBounds(340, 144, 96, 22);
		contentPane.add(lblNewLabel_9);
		
		textDescripcion.setBounds(454, 132, 315, 44);
		contentPane.add(textDescripcion);
		
		lblF.setBounds(558, 258, 146, 13);
		contentPane.add(lblF);
		
		JButton btnNewButton = new JButton("ACEPTAR");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sFecha, sCategoria, sTipo, sDescripcion, sCantidad;
				Double dCantidad = 0.0, dTotal;
				int iId, iDia, iMes, iAno, iResultado=0;
				iDia = Integer.valueOf(spinnerDia.getValue().toString());
				iMes = Integer.valueOf(spinnerMes.getValue().toString());
				iAno = Integer.valueOf(spinnerAno.getValue().toString());
				sFecha = iDia + "/" + iMes + "/" + iAno;
				sCategoria = comboCategoria.getSelectedItem().toString();
				sTipo = comboTipo.getSelectedItem().toString();
				sDescripcion = textDescripcion.getText();
				sCantidad = textCantidad.getText().replace(',', '.');
				
				
				iId = Integer.valueOf(textId.getText());
				
				sTipo = comboTipo.getSelectedItem().toString();
				sCategoria = comboCategoria.getSelectedItem().toString();
				sDescripcion = textDescripcion.getText();
				
			    DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
				       
						try {
							java.util.Date fechaUtil = formatoFecha.parse(sFecha);
							Date fechaSql = new Date(fechaUtil.getTime());
							dCantidad = Double.parseDouble(sCantidad);
							if (comboTipo.getSelectedIndex() == 0) {
								dCantidad = dCantidad * (-1);								
							}
							
							
							Transaccion tra = new Transaccion(iId, fechaSql, dCantidad, sCategoria, 
									sTipo, sDescripcion); 				
							TransaccionDAO transaccionDAO = new TransaccionDAO();
							iResultado = transaccionDAO.modificarTransaccion(tra);		
							System.out.println(tra);
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
				            System.out.println("Error al convertir la fecha: " + e1.getMessage());
						}

				if (iResultado == 0) {
					JOptionPane.showMessageDialog(null, "No se ha podido insertar transacción");
				}
				else {
					dispose();
					
				}
			
		}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.setBounds(155, 415, 159, 44);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("SALIR");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_1.setBounds(472, 415, 162, 44);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_1 = new JLabel("(Fecha anterior):");
		lblNewLabel_1.setBounds(454, 258, 106, 13);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_7 = new JLabel("MODIFICAR TRANSACCIÓN");
		lblNewLabel_7.setForeground(new Color(255, 255, 255));
		lblNewLabel_7.setBackground(new Color(0, 64, 128));
		lblNewLabel_7.setOpaque(true);
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel_7.setBounds(10, 23, 799, 31);
		contentPane.add(lblNewLabel_7);
		this.iId = id;
		
		
		
	}
}
