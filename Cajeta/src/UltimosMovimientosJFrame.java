
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
import javax.swing.JProgressBar;


public class UltimosMovimientosJFrame extends JFrame {

	private JPanel contentPane;
	private ConsultasJFrame padre;
	private long dni;

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
			}
		});
		button.setHorizontalAlignment(SwingConstants.LEFT);
		button.setBounds(396, 228, 48, 44);
		contentPane.add(button);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(168, 119, 166, 50);
		contentPane.add(comboBox);
		
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
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setToolTipText("Imprimiendo resumen\n");
		progressBar.setBackground(Color.CYAN);
		progressBar.setBounds(6, 252, 378, 20);
		contentPane.add(progressBar);
		progressBar.setVisible(false);
		
		JLabel lblNewLabel = new JLabel("Imprimiendo Resumen...");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setBounds(6, 224, 142, 16);
		contentPane.add(lblNewLabel);
		lblNewLabel.setVisible(false);
	}
}
