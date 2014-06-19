package view;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import banco.Banco;
import banco.CajaDeAhorro;
import banco.Cuenta;
import banco.CuentaCorriente;

// FUNCIONA
// mismo banco --->> Validar!  checked!


public class TransfNroCuentaJFrame extends JFrame {

	private JPanel contentPane;
	private final double monto;
	private final Cuenta cuenta;
	private JTextField destino;
	private JLabel lblNumeroDeCuenta;
	private TransferenciasJFrame padre;
	/**
	 * Create the frame.
	 */
	public TransfNroCuentaJFrame(Cuenta miCuenta, double monto, TransferenciasJFrame padre) {
		this.padre = padre;
		this.cuenta = miCuenta;
		this.monto = monto;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clickAtras();
			}
		});
		button.setIcon(new ImageIcon("./imagenes/shut-down.png"));
		button.setHorizontalAlignment(SwingConstants.LEFT);
		button.setBounds(396, 227, 48, 44);
		contentPane.add(button);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("./imagenes/LOGO BBV.gif"));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(new Color(0, 191, 255));
		label.setFont(label.getFont().deriveFont(label.getFont().getStyle() | Font.BOLD | Font.ITALIC, label.getFont().getSize() + 9f));
		label.setBackground(Color.LIGHT_GRAY);
		label.setBounds(6, 6, 440, 62);
		contentPane.add(label);
		
		JLabel lblNewLabel = new JLabel("Numero de Cuenta Destino");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setFont(new Font("Lucida Grande", Font.ITALIC, 13));
		lblNewLabel.setForeground(new Color(30, 144, 255));
		lblNewLabel.setBounds(38, 116, 213, 16);
		contentPane.add(lblNewLabel);
		
		destino = new JTextField();
		destino.setBounds(255, 110, 134, 28);
		contentPane.add(destino);
		destino.setColumns(10);
		
		JLabel montoLabel = new JLabel("");
		montoLabel.setForeground(new Color(255, 0, 0));
		montoLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		montoLabel.setBounds(255, 150, 125, 16);
		contentPane.add(montoLabel);
		montoLabel.setText("Monto: "+monto);
		
		JLabel cuentaLabel = new JLabel("New label");
		cuentaLabel.setForeground(new Color(255, 0, 0));
		cuentaLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		cuentaLabel.setBounds(255, 178, 125, 16);
		contentPane.add(cuentaLabel);
		cuentaLabel.setText(miCuenta.toString());
		
		lblNumeroDeCuenta = new JLabel("Numero de Cuenta erroneo");
		lblNumeroDeCuenta.setForeground(Color.RED);
		lblNumeroDeCuenta.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNumeroDeCuenta.setEnabled(false);
		lblNumeroDeCuenta.setBounds(193, 217, 193, 14);
		contentPane.add(lblNumeroDeCuenta);
		lblNumeroDeCuenta.setVisible(false);
		
		
		JButton confirmarBoton = new JButton("Confirmar");
		confirmarBoton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println( Banco.recuperarMiBanco().getListaCajasDeAhorro().keySet());
				System.out.println( Banco.recuperarMiBanco().getListaCuentasCorriente().keySet());
				if( destino.getText()!= null && isNumeric( destino.getText() ) ) {
					System.out.println("entro al if");
						if( Banco.recuperarMiBanco().getListaCuentasCorriente().containsKey( Long.parseLong(destino.getText()) ) )  {
							Banco.recuperarMiBanco().transferencia(getMonto(), (CuentaCorriente)getCuenta(), Banco.recuperarMiBanco().getListaCuentasCorriente().get(Long.valueOf(destino.getText() ) ));
							done();
						}
						else if( Banco.recuperarMiBanco().getListaCajasDeAhorro().containsKey(Long.parseLong(destino.getText()) ) )  {
							Banco.recuperarMiBanco().transferencia(getMonto(), (CajaDeAhorro)getCuenta(), Banco.recuperarMiBanco().getListaCajasDeAhorro().get(Long.valueOf(destino.getText() ) ));
							done();
						}
						else {
							lblNumeroDeCuenta.setVisible(true);
						}
				}
				else{
					lblNumeroDeCuenta.setVisible(true);
				}
			}
		});
		confirmarBoton.setBounds(66, 210, 117, 29);
		contentPane.add(confirmarBoton);
		
		
	}

	public double getMonto() {
		return monto;
	}

	public Cuenta getCuenta() {
		return cuenta;
	}

	private static boolean isNumeric(String cadena){
		try {
			Integer.parseInt(cadena);
			return true;
		} catch (NumberFormatException nfe){
			return false;
		}
	}

	public void done(){
		OperacionRealizadaJFrame opRealizada = new OperacionRealizadaJFrame(this);
		opRealizada.setVisible(true);
		this.setVisible(false);
		
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
