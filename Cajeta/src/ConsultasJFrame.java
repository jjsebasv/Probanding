

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
	/**
	 * Create the frame.
	 */
	public ConsultasJFrame(final long dni) {
		this.dni = dni;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("SELECCIONE EL TIPO DE OPERACION");
		label.setBounds(5, 5, 440, 54);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(new Color(0, 191, 255));
		label.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 20));
		label.setBackground(Color.WHITE);
		contentPane.add(label);
		
		JButton btnConsultaDeSaldo = new JButton("CONSULTA DE SALDO");
		btnConsultaDeSaldo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
<<<<<<< HEAD
				eventoClickSaldo();
=======
				ConsultaSaldoJFrame consultaSaldo = new ConsultaSaldoJFrame(dni);
				consultaSaldo.setVisible(true);
>>>>>>> 6ee9fd411c7334ca4867e5331446dc120c498f6c
			}
		});
		btnConsultaDeSaldo.setIcon(new ImageIcon("/Users/user/Pictures/1307051141_737.png"));
		btnConsultaDeSaldo.setHorizontalAlignment(SwingConstants.LEFT);
		btnConsultaDeSaldo.setBounds(28, 71, 178, 29);
		contentPane.add(btnConsultaDeSaldo);
		
		JButton btnConsultaDeCbu = new JButton("CONSULTA DE CBU");
		btnConsultaDeCbu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eventoClickCbu();
			}
		});
		btnConsultaDeCbu.setIcon(new ImageIcon("/Users/user/Pictures/1307051141_737.png"));
		btnConsultaDeCbu.setHorizontalAlignment(SwingConstants.LEFT);
		btnConsultaDeCbu.setBounds(28, 112, 178, 29);
		contentPane.add(btnConsultaDeCbu);
		
		JButton btnLimitesDisponibles = new JButton("LIMITES DISPONIBLES");
		btnLimitesDisponibles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eventoClickLimites();
			}
		});
		btnLimitesDisponibles.setIcon(new ImageIcon("/Users/user/Pictures/1307051141_737.png"));
		btnLimitesDisponibles.setHorizontalAlignment(SwingConstants.LEFT);
		btnLimitesDisponibles.setBounds(28, 153, 178, 29);
		contentPane.add(btnLimitesDisponibles);
		
		JButton btnUltimosMovimientos = new JButton("ULTIMOS MOVIMIENTOS");
		btnUltimosMovimientos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UltimosMovimientosJFrame movimientos = new UltimosMovimientosJFrame();
				movimientos.setVisible(true);
			}
		});
		btnUltimosMovimientos.setIcon(new ImageIcon("/Users/user/Pictures/1307051141_737.png"));
		btnUltimosMovimientos.setHorizontalAlignment(SwingConstants.LEFT);
		btnUltimosMovimientos.setBounds(267, 112, 178, 29);
		contentPane.add(btnUltimosMovimientos);
		
		JButton btnImpresionDeResumen = new JButton("IMPRESION DE RESUMEN");
		btnImpresionDeResumen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eventoClickImpresion();
			}
		});
		btnImpresionDeResumen.setIcon(new ImageIcon("/Users/user/Pictures/1307051141_737.png"));
		btnImpresionDeResumen.setHorizontalAlignment(SwingConstants.LEFT);
		btnImpresionDeResumen.setBounds(267, 71, 178, 29);
		contentPane.add(btnImpresionDeResumen);
		
		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		button.setIcon(new ImageIcon("/Users/user/Pictures/shut-down.png"));
		button.setHorizontalAlignment(SwingConstants.LEFT);
		button.setBounds(397, 228, 48, 44);
		contentPane.add(button);
		
		JButton button_1 = new JButton("");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eventoClickAtras();
			}
		});
		button_1.setIcon(new ImageIcon("/Users/user/Pictures/home.png"));
		button_1.setHorizontalAlignment(SwingConstants.LEFT);
		button_1.setBounds(5, 228, 48, 44);
		contentPane.add(button_1);
	}

	public void eventoClickCbu(){
		CbuJFrame consulta = new CbuJFrame(dni);
		consulta.setVisible(true);
	}
	
	public void eventoClickImpresion(){
		ImpresionResumenJFrame impresion = new ImpresionResumenJFrame(dni);
		impresion.setVisible(true);
	}
	
	public void eventoClickLimites(){
		LimitesDisponiblesJFrame limites = new LimitesDisponiblesJFrame(dni);
		limites.setVisible(true);
	}
	
	public void eventoClickAtras(){
		OperacionJFrame operacion = new OperacionJFrame(dni);
		operacion.setVisible(true);
	}
	
	public void eventoClickSaldo(){
		ConsultaSaldoJFrame consultaSaldo = new ConsultaSaldoJFrame(dni);
		consultaSaldo.setVisible(true);
	}
}
