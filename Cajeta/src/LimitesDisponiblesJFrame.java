// FUNCIONA BIEN, NO TOCAR!
// AGREGAR QUEES LO QUE HACE EL BOTON NO REGISTRA CUENTAS
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;

import java.awt.Color;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
 

public class LimitesDisponiblesJFrame extends JFrame {

	private JPanel contentPane;
	private JTextField rtaCuotasExtraccion;
	private JTextField rtaUnPagoCompra;
	private final long dni;
	private ConsultasJFrame padre;
	private String msjDefault = "No registra mas Cuentas.";
	private JLabel dispUnPagoOcompra;
	private JLabel dispEnCuotasoExtracc;

	/**
	 * Create the frame.
	 */
	public LimitesDisponiblesJFrame(long dni, ConsultasJFrame padre) {
		this.padre = padre;
		this.dni = dni;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	
		JButton cerrarSesion = new JButton("");
		cerrarSesion.setIcon(new ImageIcon("./imagenes/shut-down.png"));
		cerrarSesion.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				cerrarSesion();
			}
		});
		cerrarSesion.setHorizontalAlignment(SwingConstants.LEFT);
		cerrarSesion.setBounds(396, 228, 48, 44);
		contentPane.add(cerrarSesion);
		
		
		int cantTarjetas  = Banco.recuperarMiBanco().verCliente(dni).getTarjetasCredito().values().size();
		int i = 0;
		String[] tarjetas = new String[cantTarjetas+2];
		
		for ( Tarjeta tarjeta : Banco.recuperarMiBanco().verCliente(dni).getTarjetasCredito().values() ) {
			tarjetas[i++] = tarjeta.toString();
		}
		if ( Banco.recuperarMiBanco().verCliente(dni).getTajetaDeDebito() != null ){
			tarjetas[i++] = Banco.recuperarMiBanco().verCliente(dni).getTajetaDeDebito().toString();
		}
		tarjetas[i] = msjDefault;
		
		
		final JComboBox listaTarjetas = new JComboBox(tarjetas);
		listaTarjetas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eventoClickCombo(listaTarjetas.getSelectedItem().toString());
			}
		});
		listaTarjetas.setBounds(125, 92, 240, 50);
		contentPane.add(listaTarjetas);
		
		
		JLabel bbva = new JLabel("");
		bbva.setIcon(new ImageIcon("./imagenes/LOGO BBV.gif"));
		bbva.setHorizontalAlignment(SwingConstants.CENTER);
		bbva.setForeground(new Color(0, 191, 255));
		bbva.setFont(bbva.getFont().deriveFont(bbva.getFont().getStyle() | Font.BOLD | Font.ITALIC, bbva.getFont().getSize() + 9f));
		bbva.setBackground(Color.LIGHT_GRAY);
		bbva.setBounds(6, 7, 440, 62);
		contentPane.add(bbva);
		
		JLabel seleccioneCuenta = new JLabel("SELECCIONE \nLA CUENTA");
		seleccioneCuenta.setForeground(new Color(30, 144, 255));
		seleccioneCuenta.setFont(seleccioneCuenta.getFont().deriveFont(seleccioneCuenta.getFont().getStyle() | Font.BOLD | Font.ITALIC));
		seleccioneCuenta.setBounds(155, 62, 200, 34);
		contentPane.add(seleccioneCuenta);
		
		dispEnCuotasoExtracc = new JLabel("");
		dispEnCuotasoExtracc.setHorizontalAlignment(SwingConstants.CENTER);
		dispEnCuotasoExtracc.setForeground(new Color(30, 144, 255));
		dispEnCuotasoExtracc.setFont(dispEnCuotasoExtracc.getFont().deriveFont(dispEnCuotasoExtracc.getFont().getStyle() | Font.ITALIC));
		dispEnCuotasoExtracc.setBounds(0, 152, 122, 30);
		contentPane.add(dispEnCuotasoExtracc);
		
		
		dispUnPagoOcompra = new JLabel("");
		dispUnPagoOcompra.setHorizontalAlignment(SwingConstants.CENTER);
		dispUnPagoOcompra.setForeground(new Color(30, 144, 255));
		dispUnPagoOcompra.setFont(dispUnPagoOcompra.getFont().deriveFont(dispUnPagoOcompra.getFont().getStyle() | Font.ITALIC));
		dispUnPagoOcompra.setBounds(0, 185, 122, 34);
		contentPane.add(dispUnPagoOcompra);
		
		rtaCuotasExtraccion = new JTextField();
		rtaCuotasExtraccion.setEditable(false);
		rtaCuotasExtraccion.setBounds(155, 154, 210, 28);
		contentPane.add(rtaCuotasExtraccion);
		rtaCuotasExtraccion.setColumns(10);
		rtaCuotasExtraccion.setVisible(false);
		
		rtaUnPagoCompra = new JTextField();
		rtaUnPagoCompra.setEditable(false);
		rtaUnPagoCompra.setColumns(10);
		rtaUnPagoCompra.setBounds(155, 188, 210, 28);
		contentPane.add(rtaUnPagoCompra);
		rtaUnPagoCompra.setVisible(false);
		
		JButton button_1 = new JButton("");
		button_1.setIcon(new ImageIcon("./imagenes/home.png"));
		button_1.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
				clickAtras();
			}
		});
		button_1.setHorizontalAlignment(SwingConstants.LEFT);
		button_1.setBounds(6, 228, 48, 44);
		contentPane.add(button_1);
	}
	
	public  void eventoClickCombo(String nroTarjeta) {
		Tarjeta aux = null;
		for (Tarjeta tar : Banco.recuperarMiBanco().getListaTarjetas()) {
			if ( tar.toString().equals(nroTarjeta)){
				aux = tar;
			}
		}
		
		if ( Banco.recuperarMiBanco().verCliente(dni).getTajetaDeDebito() != null && Banco.recuperarMiBanco().verCliente(dni).getTajetaDeDebito().toString().equals(nroTarjeta) ){
			this.dispEnCuotasoExtracc.setText("Disp. Extraccion");
			this.dispUnPagoOcompra.setText("Disp. Compra");
			this.rtaCuotasExtraccion.setText(String.valueOf(Banco.recuperarMiBanco().verCliente(dni).disponibleDebitoExtraccion()));
			this.rtaUnPagoCompra.setText(String.valueOf(Banco.recuperarMiBanco().verCliente(dni).disponibleDebitoCompra()));
		}
		else{
			TarjetaDeCredito tar = (TarjetaDeCredito) aux;
			this.dispEnCuotasoExtracc.setText("Disp. Cuotas");
			this.dispUnPagoOcompra.setText("Disp. un Pago");
			this.rtaCuotasExtraccion.setText(String.valueOf(tar.disponibleCuotas()));
			this.rtaUnPagoCompra.setText(String.valueOf(tar.disponibleUnPago()));
			
		}
		this.dispEnCuotasoExtracc.setVisible(true);
		this.dispUnPagoOcompra.setVisible(true);
		this.rtaCuotasExtraccion.setVisible(true);
		this.rtaUnPagoCompra.setVisible(true);
	}		
	

	public void clickAtras(){
		this.dispose();
		padre.setVisible(true);
	}
	
	public void cerrarSesion(){
		this.padre.cerrarSesion();
		this.dispose();
	}
	
	
	
	
}
