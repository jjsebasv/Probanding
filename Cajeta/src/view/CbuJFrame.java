package view;
// FUNCIONA BIEN, NO TOCAR!
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;

import banco.Banco;
import banco.Cuenta;
import view.ConsultasJFrame;




public class CbuJFrame extends JFrame {

	private JPanel contentPane;
	public final long dni;
	private JTextField cbuResultado;
	private ConsultasJFrame padre;


	public CbuJFrame(final long dni, ConsultasJFrame consulta) {
		this.padre = consulta;
		this.dni = dni;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton cerrarSesion = new JButton("");
		cerrarSesion.setIcon(new ImageIcon("./src/imagenes/shut-down.png"));
		cerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cerrarSesion();
			}
		});
		cerrarSesion.setHorizontalAlignment(SwingConstants.LEFT);
		cerrarSesion.setBounds(396, 228, 48, 44);
		contentPane.add(cerrarSesion);
		
		int i=0;
		int cantCuentas = Banco.recuperarMiBanco().verCliente(dni).getCuentasMonetarias().size();
		final String[] nombreCuentas = new String[cantCuentas];
		
		for (Long nroCuenta : Banco.recuperarMiBanco().verCliente(dni).getCuentasMonetarias().keySet()) {
			nombreCuentas[i] = Banco.recuperarMiBanco().verCliente(dni).getCuentasMonetarias().get(nroCuenta).toString();
			i++;
		}
		
		// no se porque aca me tira que tengo que agregarle un final
		final JComboBox Cuentas = new JComboBox(nombreCuentas);
		Cuentas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eventoClickCombo(Cuentas.getSelectedItem().toString());
			}
		});
		Cuentas.setBounds(128, 119, 240, 50);
		contentPane.add(Cuentas);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("./src/imagenes/LOGO BBV.gif"));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(new Color(0, 191, 255));
		label.setFont(label.getFont().deriveFont(label.getFont().getStyle() | Font.BOLD | Font.ITALIC, label.getFont().getSize() + 9f));
		label.setBackground(Color.LIGHT_GRAY);
		label.setBounds(6, 6, 440, 62);
		contentPane.add(label);
		
		JLabel seleccione = new JLabel("SELECCIONE \nLA CUENTA");
		seleccione.setForeground(new Color(30, 144, 255));
		seleccione.setFont(seleccione.getFont().deriveFont(seleccione.getFont().getStyle() | Font.BOLD | Font.ITALIC));
		seleccione.setBounds(168, 92, 200, 34);
		contentPane.add(seleccione);
		
		JLabel cbuNumero = new JLabel("Su CBU es:");
		cbuNumero.setFont(cbuNumero.getFont().deriveFont(cbuNumero.getFont().getStyle() | Font.ITALIC));
		cbuNumero.setForeground(new Color(30, 144, 255));
		cbuNumero.setBounds(128, 181, 77, 34);
		contentPane.add(cbuNumero);
		
		JButton home = new JButton("");
		home.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clickAtras();
			}
		});
		home.setIcon(new ImageIcon("./src/imagenes/home.png"));
		home.setHorizontalAlignment(SwingConstants.LEFT);
		home.setBounds(6, 228, 48, 44);
		contentPane.add(home);
		
		cbuResultado = new JTextField();
		cbuResultado.setHorizontalAlignment(SwingConstants.CENTER);
		cbuResultado.setBounds(210, 184, 134, 28);
		contentPane.add(cbuResultado);
		cbuResultado.setColumns(10);
		cbuResultado.setVisible(false);
		
	}
	
	public void eventoClickCombo( String nroCuenta ){
		Long nroCuentaSeleccionada = 0L;
		
		for (Cuenta cuenta : Banco.recuperarMiBanco().getListaCajasDeAhorro().values()) {
			if ( cuenta.toString().equals(nroCuenta) ){
				nroCuentaSeleccionada = cuenta.getNumeroCuenta();
			}
		}
		
		if ( nroCuentaSeleccionada == 0 ){
			for (Cuenta cuenta : Banco.recuperarMiBanco().getListaCuentasCorriente().values()) {
				if ( cuenta.toString().equals(nroCuenta) ){
					nroCuentaSeleccionada = cuenta.getNumeroCuenta();
				}
			}
		}

		long cbu = Banco.recuperarMiBanco().verCliente(dni).getCuentasMonetarias().get(nroCuentaSeleccionada).getCBU();
		cbuResultado.setText(String.valueOf(cbu));
		cbuResultado.setVisible(true);
		
	}

	
	public void clickAtras(){
		Banco.save(Banco.recuperarMiBanco());
		this.padre.clickAtras();
		this.dispose();
	}

	public void cerrarSesion(){
		Banco.save(Banco.recuperarMiBanco());
		this.padre.cerrarSesion();
		this.dispose();
		
	}
	
}
