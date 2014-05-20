
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
	private JTextField textField;
	private JTextField textField_1;
	private final long dni;

	/**
	 * Create the frame.
	 */
	public LimitesDisponiblesJFrame(long dni) {
		this.dni = dni;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton button = new JButton("");
		button.setIcon(new ImageIcon("shut-down.png"));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button.setHorizontalAlignment(SwingConstants.LEFT);
		button.setBounds(396, 228, 48, 44);
		contentPane.add(button);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(115, 100, 240, 50);
		contentPane.add(comboBox);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("LOGO BBV.gif"));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(new Color(0, 191, 255));
		label.setFont(label.getFont().deriveFont(label.getFont().getStyle() | Font.BOLD | Font.ITALIC, label.getFont().getSize() + 9f));
		label.setBackground(Color.LIGHT_GRAY);
		label.setBounds(6, 7, 440, 62);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("SELECCIONE \nLA CUENTA");
		label_1.setForeground(new Color(30, 144, 255));
		label_1.setFont(label_1.getFont().deriveFont(label_1.getFont().getStyle() | Font.BOLD | Font.ITALIC));
		label_1.setBounds(147, 80, 200, 34);
		contentPane.add(label_1);
		
		JLabel lblSuDisponibleEn = new JLabel("Su disponible en Cuotas:");
		lblSuDisponibleEn.setForeground(new Color(30, 144, 255));
		lblSuDisponibleEn.setFont(lblSuDisponibleEn.getFont().deriveFont(lblSuDisponibleEn.getFont().getStyle() | Font.ITALIC));
		lblSuDisponibleEn.setBounds(33, 143, 158, 34);
		contentPane.add(lblSuDisponibleEn);
		
		JLabel lblSuDisponibleEn_1 = new JLabel("Su disponible en un Pago:");
		lblSuDisponibleEn_1.setForeground(new Color(30, 144, 255));
		lblSuDisponibleEn_1.setFont(lblSuDisponibleEn_1.getFont().deriveFont(lblSuDisponibleEn_1.getFont().getStyle() | Font.ITALIC));
		lblSuDisponibleEn_1.setBounds(22, 185, 169, 34);
		contentPane.add(lblSuDisponibleEn_1);
		
		textField = new JTextField();
		textField.setBounds(203, 146, 225, 28);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(203, 188, 225, 28);
		contentPane.add(textField_1);
		
		JButton button_1 = new JButton("");
		button_1.setIcon(new ImageIcon("home.png"));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button_1.setHorizontalAlignment(SwingConstants.LEFT);
		button_1.setBounds(6, 228, 48, 44);
		contentPane.add(button_1);
	}
	
	public void eventoClickAtras(){
//		OperacionJFrame operacion = new OperacionJFrame(dni);
//		operacion.setVisible(true);
	}
}
