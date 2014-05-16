
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
import javax.swing.JTextField;


public class CbuJFrame extends JFrame {

	private JPanel contentPane;
	private JTextField CbuField;
	public final long dni;


	/**
	 * Create the frame.
	 */
	public CbuJFrame(long dni) {
		this.dni = dni;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton button = new JButton("");
		button.setIcon(new ImageIcon("/Users/user/Pictures/shut-down.png"));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		button.setHorizontalAlignment(SwingConstants.LEFT);
		button.setBounds(396, 228, 48, 44);
		contentPane.add(button);
		
		JComboBox Cuentas = new JComboBox();
		Cuentas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//eventoClickSeleccion((Cuenta) this.getSelectedItem());
			}
		});
		Cuentas.setBounds(128, 119, 240, 50);
		contentPane.add(Cuentas);
		
		for (Long nroCuenta : Banco.recuperarMiBanco().verCliente(dni).getCuentasMonetarias().keySet()) {
			Cuentas.addItem(Banco.recuperarMiBanco().verCliente(dni).getCuentasMonetarias().get(nroCuenta));
		}
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("/Users/user/Pictures/LOGO BBV.gif"));
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
		
		CbuField = new JTextField();
		CbuField.setFont(CbuField.getFont().deriveFont(CbuField.getFont().getStyle() | Font.ITALIC));
		CbuField.setForeground(new Color(30, 144, 255));
		CbuField.setText("Su CBU es:");
		CbuField.setBounds(129, 168, 240, 28);
		contentPane.add(CbuField);
		CbuField.setColumns(10);
	}
	
	public void eventoClickSeleccion(Cuenta cuenta){
		CbuField.setText(String.valueOf(cuenta.getCBU()));
	}
}
