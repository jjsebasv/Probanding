
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
<<<<<<< HEAD
=======
	private String msjDefault = "No registra mas Cuentas.";
	private Long CBU;
	
	

	public long getDni() {
		return dni;
	}

	public Long getCBU(){
		return CBU;
	}
	
	public void setCBU(Long CBU){
		this.CBU = CBU;
	}

>>>>>>> 6ee9fd411c7334ca4867e5331446dc120c498f6c

	/**
	 * Create the frame.
	 */
<<<<<<< HEAD
	public ConsultaSaldoJFrame(long dni) {
		this.dni = dni;
=======
	public ConsultaSaldoJFrame(final long dni) {
		this.dni = dni;
		
>>>>>>> 6ee9fd411c7334ca4867e5331446dc120c498f6c
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton button_1 = new JButton("");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		button_1.setIcon(new ImageIcon("/Users/user/Pictures/shut-down.png"));
		button_1.setHorizontalAlignment(SwingConstants.LEFT);
		button_1.setBounds(396, 228, 48, 44);
		contentPane.add(button_1);
		
		final JButton cbuElegido = new JButton("New button");
		cbuElegido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				SaldoDisponibleJFrame saldos = new SaldoDisponibleJFrame(getDni(), getCBU());
				saldos.setVisible(true);
								
			}
		});
		
		cbuElegido.setBounds(168, 189, 166, 34);
		contentPane.add(cbuElegido);
		
		final JComboBox cuentas = new JComboBox();
		cuentas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				String mensaje = cuentas.getSelectedItem().toString();
				cbuElegido.setText(mensaje);
				setCBU(Long.valueOf(mensaje));
			}
		});
		
		cuentas.setBounds(168, 119, 166, 50);
		contentPane.add(cuentas);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("/Users/user/Pictures/LOGO BBV.gif"));
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
		
<<<<<<< HEAD
		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eventoClickAtras();
			}
		});
		button.setIcon(new ImageIcon("/Users/user/Pictures/home.png"));
		button.setHorizontalAlignment(SwingConstants.LEFT);
		button.setBounds(6, 228, 48, 44);
		contentPane.add(button);
		
		JLabel saldoNumero = new JLabel("");
		saldoNumero.setBounds(91, 171, 243, 16);
		contentPane.add(saldoNumero);
	}
	
	public void eventoClickAtras(){
		OperacionJFrame operacion = new OperacionJFrame(dni);
		operacion.setVisible(true);
=======
		
				
		Cliente cliente = Banco.recuperarMiBanco().verCliente(this.dni);
		for (Cuenta c : cliente.getCuentasMonetarias().values()) {
			cuentas.addItem(c.getCBU());	
		}
		
		cuentas.addItem(msjDefault);
		
		
		
		
		
		
>>>>>>> 6ee9fd411c7334ca4867e5331446dc120c498f6c
	}
}
