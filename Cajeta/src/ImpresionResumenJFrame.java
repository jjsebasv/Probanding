
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
import javax.swing.JProgressBar;
import javax.swing.JTextField;


public class ImpresionResumenJFrame extends JFrame {

	private JPanel contentPane;
	private final long dni;


	public ImpresionResumenJFrame(long dni) {
		this.dni = dni;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton button = new JButton("");
		button.setIcon(new ImageIcon("/Users/user/Pictures/shut-down.png"));
		button.setHorizontalAlignment(SwingConstants.LEFT);
		button.setBounds(396, 228, 48, 44);
		contentPane.add(button);
		
		JComboBox cuentasBox = new JComboBox();
		cuentasBox.setBounds(133, 111, 218, 50);
		contentPane.add(cuentasBox);
		for (Long nroCuenta : Banco.recuperarMiBanco().getListaCuentasCredito().keySet()) {
			cuentasBox.addItem(Banco.recuperarMiBanco().getListaCuentasCredito().get(nroCuenta));
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
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setForeground(new Color(30, 144, 255));
		label_1.setFont(label_1.getFont().deriveFont(label_1.getFont().getStyle() | Font.BOLD | Font.ITALIC));
		label_1.setBounds(133, 80, 211, 34);
		contentPane.add(label_1);
		
		JLabel lblSeleccionePeriodoLa = new JLabel("SELECCIONE PERIODO");
		lblSeleccionePeriodoLa.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeleccionePeriodoLa.setForeground(new Color(30, 144, 255));
		lblSeleccionePeriodoLa.setFont(lblSeleccionePeriodoLa.getFont().deriveFont(lblSeleccionePeriodoLa.getFont().getStyle() | Font.BOLD | Font.ITALIC));
		lblSeleccionePeriodoLa.setBounds(143, 156, 200, 34);
		contentPane.add(lblSeleccionePeriodoLa);
		
		JComboBox periodosBox = new JComboBox();
		periodosBox.setBounds(133, 187, 218, 50);
		contentPane.add(periodosBox);

		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(0, 249, 384, 20);
		contentPane.add(progressBar);
		progressBar.setVisible(false);
		
		JLabel lblImprimiendoResumen = new JLabel("Imprimiendo Resumen");
		lblImprimiendoResumen.setForeground(Color.RED);
		lblImprimiendoResumen.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		lblImprimiendoResumen.setBounds(0, 221, 131, 16);
		contentPane.add(lblImprimiendoResumen);
		lblImprimiendoResumen.setVisible(false);
		
	}

}
