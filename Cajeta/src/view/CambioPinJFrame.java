package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JTextField;

import banco.Banco;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CambioPinJFrame extends JFrame {

	private JPanel contentPane;
	private JTextField pinField;
	private long dni;
	private InicioJFrame padre;


	public CambioPinJFrame(long dni, OperacionJFrame padre) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton cerrarSesion = new JButton("");
		cerrarSesion.setHorizontalAlignment(SwingConstants.LEFT);
		cerrarSesion.setIcon(new ImageIcon("./imagenes/shut-down.png"));
		cerrarSesion.setBounds(396, 228, 48, 44);
		contentPane.add(cerrarSesion);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("./imagenes/LOGO BBV.gif"));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(new Color(0, 191, 255));
		label.setFont(label.getFont().deriveFont(label.getFont().getStyle() | Font.BOLD | Font.ITALIC, label.getFont().getSize() + 9f));
		label.setBackground(Color.LIGHT_GRAY);
		label.setBounds(6, 6, 440, 62);
		contentPane.add(label);
		
		JLabel lblIngreseNuevoPin = new JLabel("INGRESE NUEVO PIN");
		lblIngreseNuevoPin.setForeground(new Color(30, 144, 255));
		lblIngreseNuevoPin.setFont(lblIngreseNuevoPin.getFont().deriveFont(lblIngreseNuevoPin.getFont().getStyle() | Font.BOLD | Font.ITALIC));
		lblIngreseNuevoPin.setBounds(139, 101, 200, 34);
		contentPane.add(lblIngreseNuevoPin);
		
		JButton home = new JButton("");
		home.setHorizontalAlignment(SwingConstants.LEFT);
		home.setBounds(6, 228, 48, 44);
		contentPane.add(home);
		home.setIcon(new ImageIcon("./imagenes/home.png"));

		
		pinField = new JTextField();
		pinField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		pinField.setBounds(108, 135, 200, 28);
		contentPane.add(pinField);
		pinField.setColumns(10);
		
		JButton btnNewButton = new JButton("Confirmar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eventoConfirmar();
			}
		});
		btnNewButton.setBounds(152, 185, 117, 29);
		contentPane.add(btnNewButton);
	}
	
	public void eventoConfirmar(){
		int pin = Integer.valueOf(pinField.getText());
		Banco.recuperarMiBanco().generacionClavePin(pin, dni);
	}
	
	public void clickAtras(){
		Banco.save(Banco.recuperarMiBanco());
		this.setVisible(true);
	}

	public void cerrarSesion(){
		Banco.save(Banco.recuperarMiBanco());
		padre.setVisible(true);
		this.dispose();
	}

}
