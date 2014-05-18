
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


public class ConsultaSaldoJFrame extends JFrame {

	private JPanel contentPane;
	private final long dni;

	/**
	 * Create the frame.
	 */
	public ConsultaSaldoJFrame(long dni) {
		this.dni = dni;
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
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(168, 119, 166, 50);
		contentPane.add(comboBox);
		
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
	}
}
