package view;
// PREGUNTAR DOBLE COMBO BOX
// AGREGAR BOTON HOME
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
import javax.swing.JProgressBar;
import javax.swing.JTextField;

import banco.Banco;
import banco.Cuenta;
import banco.CuentaCredito;
import banco.Resumen;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class ImpresionResumenJFrame extends JFrame {

	private JPanel contentPane;
	public final long dni;
	private ConsultasJFrame padre;


	public ImpresionResumenJFrame(long dni, ConsultasJFrame consultas) {
		this.padre = consultas;
		this.dni = dni;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		button.setIcon(new ImageIcon("./src/imagenes/shut-down.png"));
		button.setHorizontalAlignment(SwingConstants.LEFT);
		button.setBounds(396, 228, 48, 44);
		contentPane.add(button);
		
		int i=0;
		int cantCuentas = Banco.recuperarMiBanco().verCliente(dni).getCuentasCredito().size();
		String[] nombreCuentas = new String[cantCuentas];
		
		for (Long nroCuenta : Banco.recuperarMiBanco().verCliente(dni).getCuentasCredito().keySet() ) {
			nombreCuentas[i] = Banco.recuperarMiBanco().verCliente(dni).getCuentasCredito().get(nroCuenta).toString();
			i++;
		}
	
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("./src/imagenes/LOGO BBV.gif"));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(new Color(0, 191, 255));
		label.setFont(label.getFont().deriveFont(label.getFont().getStyle() | Font.BOLD | Font.ITALIC, label.getFont().getSize() + 9f));
		label.setBackground(Color.LIGHT_GRAY);
		label.setBounds(6, 6, 440, 62);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("SELECCIONE \nLA CUENTA");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setForeground(new Color(30, 144, 255));
		label_1.setFont(label_1.getFont().deriveFont(label_1.getFont().getStyle() | Font.BOLD | Font.ITALIC));
		label_1.setBounds(126, 80, 211, 34);
		contentPane.add(label_1);
		
		JButton home = new JButton("");
		home.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clickAtras();
			}
		});
		home.setHorizontalAlignment(SwingConstants.LEFT);
		home.setBounds(6, 228, 48, 44);
		contentPane.add(home);
		home.setIcon(new ImageIcon("./src/imagenes/home.png"));
		
		final JComboBox comboBox = new JComboBox(nombreCuentas);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eventoClickComboCuentas(comboBox.getSelectedItem().toString());
			}
		});
		comboBox.setBounds(107, 126, 230, 27);
		contentPane.add(comboBox);
		home.setVisible(true);
		
	
	}
	
	
	// ---------------------- evento combo 1 ----------------------  //

	public void eventoClickComboCuentas( String nroCuenta ){
		Long nroCuentaSeleccionada = 0L;
		CuentaCredito cuentaS = null ;
		for (CuentaCredito cuenta : Banco.recuperarMiBanco().getListaCuentasCredito().values()) {
			if ( cuenta.toString().equals(nroCuenta) ){
				nroCuentaSeleccionada = cuenta.getNroCuenta();
				cuentaS = Banco.recuperarMiBanco().getListaCuentasCredito().get(nroCuentaSeleccionada);
			}
		}
		System.out.println("EN IMPRESON RESUMEN CUENTA SELEC"+cuentaS);
		SeleccionPeriodoJFrame sp = new SeleccionPeriodoJFrame(dni,cuentaS,this);
		sp.setVisible(true);
		this.setVisible(false);
	
	}
	
	
	// ---------------------- evento combo 2 ----------------------  //


	
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