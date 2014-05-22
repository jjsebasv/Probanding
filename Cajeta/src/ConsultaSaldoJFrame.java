// FUNCIONA BIEN, NO TOCAR!
import java.awt.BorderLayout;
import java.awt.EventQueue;
       
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;


public class ConsultaSaldoJFrame extends JFrame {

	private JPanel contentPane;
	private final long dni;
	private String msjDefault = "No registra mas Cuentas.";
	private Long CBU;
	private JTextField saldoResultado;
	private ConsultasJFrame padre;
	

	public long getDni() {
		return dni;
	}

	public Long getCBU(){
		return CBU;
	}
	
	public void setCBU(Long CBU){
		this.CBU = CBU;
	}
	
	
	public ConsultaSaldoJFrame(final long dni, ConsultasJFrame consulta) {
		this.dni = dni;
		this.padre = consulta;
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton cerrar = new JButton("");
		cerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cerrarSesion();
			}
		});
		cerrar.setIcon(new ImageIcon("./imagenes/shut-down.png"));
		cerrar.setHorizontalAlignment(SwingConstants.LEFT);
		cerrar.setBounds(396, 228, 48, 44);
		contentPane.add(cerrar);
		
		int i=0;
		int cantCuentas = Banco.recuperarMiBanco().verCliente(dni).getCuentasMonetarias().size();
		final String[] nombreCuentas = new String[cantCuentas];
		
		for (Long nroCuenta : Banco.recuperarMiBanco().verCliente(dni).getCuentasMonetarias().keySet()) {
			nombreCuentas[i] = Banco.recuperarMiBanco().verCliente(dni).getCuentasMonetarias().get(nroCuenta).toString();
			i++;
		}

		// no se porque aca me tira que tengo que agregarle un final
		final JComboBox cuentas = new JComboBox(nombreCuentas);
		cuentas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String aux = cuentas.getSelectedItem().toString();
				if ( aux != msjDefault)
					eventoClickCombo(aux);
			}
		});
		cuentas.setBounds(125, 119, 209, 50);
		contentPane.add(cuentas);

		cuentas.addItem(msjDefault);
		
		cuentas.setBounds(168, 119, 166, 50);
		contentPane.add(cuentas);			
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("./imagenes/LOGO BBV.gif"));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(new Color(0, 191, 255));
		label.setFont(label.getFont().deriveFont(label.getFont().getStyle() | Font.BOLD | Font.ITALIC, label.getFont().getSize() + 9f));
		label.setBackground(Color.LIGHT_GRAY);
		label.setBounds(6, 6, 440, 62);
		contentPane.add(label);
		
		JLabel lblNewLabel = new JLabel("SELECCIONE \nLA CUENTA");
		lblNewLabel.setFont(lblNewLabel.getFont().deriveFont(lblNewLabel.getFont().getStyle() | Font.BOLD | Font.ITALIC));
		lblNewLabel.setForeground(new Color(30, 144, 255));
		lblNewLabel.setBounds(168, 92, 200, 34);
		contentPane.add(lblNewLabel);
		JLabel lblNewLabel_1 = new JLabel("Su saldo es:");
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.ITALIC, 13));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setForeground(new Color(30, 144, 255));
		lblNewLabel_1.setBounds(83, 167, 93, 16);
		contentPane.add(lblNewLabel_1);
		
		saldoResultado = new JTextField();
		saldoResultado.setEditable(false);
		saldoResultado.setBounds(188, 161, 179, 28);
		contentPane.add(saldoResultado);
		saldoResultado.setColumns(10);
		
		JButton home = new JButton("");
		home.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clickAtras();
			}
		});
		home.setIcon(new ImageIcon("./imagenes/home.png"));
		home.setHorizontalAlignment(SwingConstants.LEFT);
		home.setBounds(6, 228, 48, 44);
		contentPane.add(home);
		saldoResultado.setVisible(false);
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
		
		double saldo = Banco.recuperarMiBanco().verCliente(dni).getCuentasMonetarias().get(nroCuentaSeleccionada).getSaldoActual();
		saldoResultado.setText(String.valueOf(saldo));
		saldoResultado.setVisible(true);
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
