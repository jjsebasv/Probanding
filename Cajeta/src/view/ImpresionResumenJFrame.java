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
import banco.CuentaCredito;
import banco.Resumen;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class ImpresionResumenJFrame extends JFrame {

	private JPanel contentPane;
	public final long dni;
	private long nroCuenta = 0L ;
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
		button.setIcon(new ImageIcon("./imagenes/shut-down.png"));
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
		
		final JComboBox cuentasBox = new JComboBox(nombreCuentas);
		cuentasBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eventoClickComboCuentas(cuentasBox.getSelectedItem().toString());
			}
		});
		cuentasBox.setBounds(126, 108, 218, 50);
		contentPane.add(cuentasBox);
		
	
		
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("./imagenes/LOGO BBV.gif"));
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
		
		JLabel lblSeleccionePeriodoLa = new JLabel("SELECCIONE PERIODO");
		lblSeleccionePeriodoLa.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeleccionePeriodoLa.setForeground(new Color(30, 144, 255));
		lblSeleccionePeriodoLa.setFont(lblSeleccionePeriodoLa.getFont().deriveFont(lblSeleccionePeriodoLa.getFont().getStyle() | Font.BOLD | Font.ITALIC));
		lblSeleccionePeriodoLa.setBounds(136, 146, 200, 34);
		contentPane.add(lblSeleccionePeriodoLa);
		

		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(10, 249, 434, 20);
		contentPane.add(progressBar);
		progressBar.setVisible(false);
		
		JLabel lblImprimiendoResumen = new JLabel("Imprimiendo Resumen");
		lblImprimiendoResumen.setForeground(Color.RED);
		lblImprimiendoResumen.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		lblImprimiendoResumen.setBounds(6, 221, 131, 16);
		contentPane.add(lblImprimiendoResumen);
		
		JComboBox periodoBox = new JComboBox();
		periodoBox.setBounds(126, 182, 218, 27);
		contentPane.add(periodoBox);
		
		JButton home = new JButton("");
		home.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clickAtras();
			}
		});
		home.setHorizontalAlignment(SwingConstants.LEFT);
		home.setBounds(6, 228, 48, 44);
		contentPane.add(home);
		home.setIcon(new ImageIcon("./imagenes/home.png"));
		home.setVisible(true);
		periodoBox.setVisible(false);
		
		lblImprimiendoResumen.setVisible(false);
		
	
	}
	
	
	// ---------------------- evento combo 1 ----------------------  //

	public void eventoClickComboCuentas( String nroCuenta ){
		Long nroC = 0L;
		int j=0;
		int cantR = Banco.recuperarMiBanco().verCliente(dni).getCuentasCredito().get(this.nroCuenta).getResumenes().size();
		String[]  nombreResumenes = new String[cantR];
		
		for (CuentaCredito cuenta : Banco.recuperarMiBanco().getListaCuentasCredito().values()) {
			System.out.println(nroCuenta);
			System.out.println(cuenta.getNroCuenta());
			
			if ( cuenta.getNroCuenta() == Long.valueOf(nroCuenta) ){
				System.out.println("hola");
				nroC = cuenta.getNroCuenta();
			}
		}
		this.nroCuenta = nroC;
	
	
		for (Resumen resumen : Banco.recuperarMiBanco().verCliente(dni).getCuentasCredito().get(this.nroCuenta).getResumenes().values() ) {
			nombreResumenes[j] = resumen.getFechaCierre().toString();
		}
	
		final JComboBox periodosBox = new JComboBox(nombreResumenes);
		periodosBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eventoClickComboPeriodos(periodosBox.getSelectedItem().toString());
			}
		});
		periodosBox.setBounds(133, 187, 218, 50);
		contentPane.add(periodosBox);
		periodosBox.setVisible(true);
}
	
	// ---------------------- evento combo 2 ----------------------  //

	public void eventoClickComboPeriodos( String nroResumen ){
		
	}
	
	public void clickAtras(){
		this.padre.clickAtras();
		this.dispose();
	}

	public void cerrarSesion(){
		this.padre.cerrarSesion();
		this.dispose();
	}
}