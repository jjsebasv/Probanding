

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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ConsultasJFrame extends JFrame {

	private JPanel contentPane;
	private final long dni;
	private OperacionJFrame padre;
	/**
	 * Create the frame.
	 */
	public ConsultasJFrame(final long dni, OperacionJFrame operacion) {
		this.padre = operacion;
		this.dni = dni;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSeleccioneElTipo = new JLabel("SELECCIONE EL TIPO DE CONSULTA");
		lblSeleccioneElTipo.setBounds(5, 5, 440, 54);
		lblSeleccioneElTipo.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeleccioneElTipo.setForeground(new Color(0, 191, 255));
		lblSeleccioneElTipo.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 20));
		lblSeleccioneElTipo.setBackground(Color.WHITE);
		contentPane.add(lblSeleccioneElTipo);
		
		JButton btnConsultaDeSaldo = new JButton("CONSULTA DE SALDO");
		btnConsultaDeSaldo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Saldo();
			}
		});
		btnConsultaDeSaldo.setIcon(new ImageIcon("./imagenes/1307051141_737.png"));
		btnConsultaDeSaldo.setHorizontalAlignment(SwingConstants.LEFT);
		btnConsultaDeSaldo.setBounds(28, 71, 178, 29);
		contentPane.add(btnConsultaDeSaldo);
		
		JButton btnConsultaDeCbu = new JButton("CONSULTA DE CBU");
		btnConsultaDeCbu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cbu();
			}
		});
		btnConsultaDeCbu.setIcon(new ImageIcon("./imagenes/1307051141_737.png"));
		btnConsultaDeCbu.setHorizontalAlignment(SwingConstants.LEFT);
		btnConsultaDeCbu.setBounds(28, 112, 178, 29);
		contentPane.add(btnConsultaDeCbu);
		
		JButton btnLimitesDisponibles = new JButton("LIMITES DISPONIBLES");
		btnLimitesDisponibles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Limites();
			}
		});
		btnLimitesDisponibles.setIcon(new ImageIcon("./imagenes/1307051141_737.png"));
		btnLimitesDisponibles.setHorizontalAlignment(SwingConstants.LEFT);
		btnLimitesDisponibles.setBounds(28, 153, 178, 29);
		contentPane.add(btnLimitesDisponibles);
		
		JButton btnUltimosMovimientos = new JButton("ULTIMOS MOVIMIENTOS");
		btnUltimosMovimientos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				movimiento();
			}
		});
		btnUltimosMovimientos.setIcon(new ImageIcon("./imagenes/1307051141_737.png"));
		btnUltimosMovimientos.setHorizontalAlignment(SwingConstants.LEFT);
		btnUltimosMovimientos.setBounds(267, 112, 178, 29);
		contentPane.add(btnUltimosMovimientos);
		
		JButton btnImpresionDeResumen = new JButton("IMPRESION DE RESUMEN");
		btnImpresionDeResumen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				impresion();
			}
		});
		btnImpresionDeResumen.setIcon(new ImageIcon("./imagenes/1307051141_737.png"));
		btnImpresionDeResumen.setHorizontalAlignment(SwingConstants.LEFT);
		btnImpresionDeResumen.setBounds(267, 71, 178, 29);
		contentPane.add(btnImpresionDeResumen);
		
		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cerrarSesion();
			}
		});
		button.setIcon(new ImageIcon("./imagenes/shut-down.png"));
		button.setHorizontalAlignment(SwingConstants.LEFT);
		button.setBounds(397, 228, 48, 44);
		contentPane.add(button);
		
		JButton button_1 = new JButton("");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clickAtras();
			}
		});
		button_1.setIcon(new ImageIcon("./imagenes/home.png"));
		button_1.setHorizontalAlignment(SwingConstants.LEFT);
		button_1.setBounds(5, 228, 48, 44);
		contentPane.add(button_1);
	}

	public void Cbu(){
		CbuJFrame consulta = new CbuJFrame(dni, this);
		consulta.setVisible(true);
	}
	
	public void impresion(){
		ImpresionResumenJFrame impresion = new ImpresionResumenJFrame(dni, this);
		impresion.setVisible(true);
		this.hide();
	}
	
	public void Limites(){
		LimitesDisponiblesJFrame limites = new LimitesDisponiblesJFrame(dni);
		limites.setVisible(true);
		this.hide();
	}
	
	public void Saldo(){
		ConsultaSaldoJFrame consultaSaldo = new ConsultaSaldoJFrame(dni, this);
		consultaSaldo.setVisible(true);
		this.hide();
	}
	
	public void clickAtras(){
		this.padre.clickAtras();
		this.dispose();
	}

	public void cerrarSesion(){
		this.padre.cerrarSesion();
		this.dispose();
	}
	
	public void movimiento(){
		UltimosMovimientosJFrame movimientos = new UltimosMovimientosJFrame(dni, this);
		movimientos.setVisible(true);
		this.hide();
	}
}
