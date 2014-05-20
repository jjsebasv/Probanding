
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.ImageIcon;


public class OtrasOperacionesJFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public OtrasOperacionesJFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("SELECCIONE EL TIPO DE OPERACION");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(new Color(0, 191, 255));
		label.setFont(label.getFont().deriveFont(label.getFont().getStyle() | Font.BOLD | Font.ITALIC, label.getFont().getSize() + 5f));
		label.setBackground(Color.WHITE);
		label.setBounds(6, 11, 440, 54);
		contentPane.add(label);
		
		JButton btnRecargaDeCelular = new JButton("RECARGA DE CELULAR");
		btnRecargaDeCelular.setIcon(new ImageIcon("1307051141_737.png"));
		btnRecargaDeCelular.setHorizontalAlignment(SwingConstants.LEFT);
		btnRecargaDeCelular.setBounds(29, 77, 178, 29);
		contentPane.add(btnRecargaDeCelular);
		
		JButton btnAsociacionTcCoord = new JButton("ASOCIACION COORD");
		btnAsociacionTcCoord.setIcon(new ImageIcon("1307051141_737.png"));
		btnAsociacionTcCoord.setHorizontalAlignment(SwingConstants.LEFT);
		btnAsociacionTcCoord.setBounds(29, 118, 178, 29);
		contentPane.add(btnAsociacionTcCoord);
		
		JButton btnRegistroCelular = new JButton("REGISTRO CELULAR");
		btnRegistroCelular.setIcon(new ImageIcon("1307051141_737.png"));
		btnRegistroCelular.setHorizontalAlignment(SwingConstants.LEFT);
		btnRegistroCelular.setBounds(29, 159, 178, 29);
		contentPane.add(btnRegistroCelular);
		
		JButton button_3 = new JButton("ULTIMOS MOVIMIENTOS");
		button_3.setIcon(new ImageIcon("1307051141_737.png"));
		button_3.setHorizontalAlignment(SwingConstants.LEFT);
		button_3.setBounds(268, 118, 178, 29);
		contentPane.add(button_3);
		
		JButton button_4 = new JButton("IMPRESION DE RESUMEN");
		button_4.setIcon(new ImageIcon("1307051141_737.png"));
		button_4.setHorizontalAlignment(SwingConstants.LEFT);
		button_4.setBounds(268, 77, 178, 29);
		contentPane.add(button_4);
		
		JButton button_5 = new JButton("");
		button_5.setIcon(new ImageIcon("shut-down.png"));
		button_5.setHorizontalAlignment(SwingConstants.LEFT);
		button_5.setBounds(398, 234, 48, 44);
		contentPane.add(button_5);
		
		JButton btnAltaSeguro = new JButton("ALTA SEGURO");
		btnAltaSeguro.setIcon(new ImageIcon("1307051141_737.png"));
		btnAltaSeguro.setHorizontalAlignment(SwingConstants.LEFT);
		btnAltaSeguro.setBounds(29, 200, 178, 29);
		contentPane.add(btnAltaSeguro);
	}

}
