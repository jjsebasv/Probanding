package view;
// PORQUE SOLO PUEDO HACER UN SOLO CLICK?
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import banco.Banco;
import banco.Cuenta;

public class UltimosMovimientosJFrame extends JFrame {

	private JPanel contentPane;
	private ConsultasJFrame padre;
	private long dni;
	private JProgressBar progressBar;
	private Timer timer;
	private JLabel lblNewLabel;
	private int j = 0;
	private JButton home;
	
	/**
	 * Create the frame.
	 */
	public UltimosMovimientosJFrame(long dni, ConsultasJFrame consulta) {
		this.dni = dni;
		this.padre = consulta;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setForeground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton button = new JButton("");
		button.setIcon(new ImageIcon("./imagenes/shut-down.png"));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cerrarSesion();
			}
		});
		button.setHorizontalAlignment(SwingConstants.LEFT);
		button.setBounds(396, 228, 48, 44);
		contentPane.add(button);
		
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
		label.setIcon(new ImageIcon("./imagenes/LOGO BBV.gif"));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(new Color(0, 191, 255));
		label.setFont(label.getFont().deriveFont(label.getFont().getStyle() | Font.BOLD | Font.ITALIC, label.getFont().getSize() + 9f));
		label.setBackground(Color.LIGHT_GRAY);
		label.setBounds(6, 6, 440, 62);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("SELECCIONE \nLA CUENTA");
		label_1.setForeground(new Color(30, 144, 255));
		label_1.setFont(label_1.getFont().deriveFont(label_1.getFont().getStyle() | Font.BOLD | Font.ITALIC));
		label_1.setBounds(168, 92, 200, 34);
		contentPane.add(label_1);
		
		progressBar = new JProgressBar();
		progressBar.setToolTipText("Imprimiendo resumen\n");
		progressBar.setBackground(Color.CYAN);
		progressBar.setBounds(6, 252, 378, 20);
		contentPane.add(progressBar);
		progressBar.setVisible(false);
		progressBar.setMaximum(0);
		progressBar.setMaximum(1000);
		
		lblNewLabel = new JLabel("Imprimiendo Resumen...");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setBounds(6, 224, 142, 16);
		contentPane.add(lblNewLabel);
		
		home = new JButton("");
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
		lblNewLabel.setVisible(false);
		
		timer = new Timer(3,new ActionListener(){
			  public void actionPerformed(ActionEvent ae){
			  	llenarBarra();
			  }
			 });
	}
	
	public void eventoClickCombo( String nroCuenta ){
		Long nroCuentaSeleccionada = 0L;
		Cuenta cuentaS = null ;
		
		for (Cuenta cuenta : Banco.recuperarMiBanco().getListaCajasDeAhorro().values()) {
			if ( cuenta.toString().equals(nroCuenta) ){
				nroCuentaSeleccionada = cuenta.getNumeroCuenta();
				cuentaS = Banco.recuperarMiBanco().getListaCajasDeAhorro().get(nroCuentaSeleccionada);
			}
		}
		
		if ( nroCuentaSeleccionada == 0 ){
			for (Cuenta cuenta : Banco.recuperarMiBanco().getListaCuentasCorriente().values()) {
				if ( cuenta.toString().equals(nroCuenta) ){
					nroCuentaSeleccionada = cuenta.getNumeroCuenta();
					cuentaS = Banco.recuperarMiBanco().getListaCuentasCorriente().get(nroCuentaSeleccionada);
				}
			}
		}

		cuentaS.imprimirUltimosMovimientos();
		
		this.progressBar.setVisible(true);
		this.lblNewLabel.setVisible(true);
		this.home.setVisible(false);
		llenarBarra();
	}
	
	public void llenarBarra(){
	{
			timer.start();	
			getContentPane().add(progressBar);
			
			  if(j>1000){
				  timer.stop();
				  progressBar.setVisible(false);
				  this.home.setVisible(true);
				  this.lblNewLabel.setVisible(false);
			  }
			  else{
				  progressBar.setValue(j++);
			  }
	}
	
	
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

