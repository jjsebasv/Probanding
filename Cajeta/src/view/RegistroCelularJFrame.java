package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;

import banco.Banco;
import banco.Celular;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegistroCelularJFrame extends JFrame {

	private JPanel contentPane;
	private JTextField compField;
	private JTextField numeroField;
	private OtrasOperacionesJFrame padre;
	private long dni;
	
	public RegistroCelularJFrame(long dni, OtrasOperacionesJFrame padre) {
		this.dni = dni;
		this.padre = padre;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cerrarSesion();
			}
		});
		button.setHorizontalAlignment(SwingConstants.LEFT);
		button.setIcon(new ImageIcon("./src/imagenes/shut-down.png"));
		button.setBounds(396, 228, 48, 44);
		contentPane.add(button);
		
		JLabel label = new JLabel("");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(new Color(0, 191, 255));
		label.setFont(label.getFont().deriveFont(label.getFont().getStyle() | Font.BOLD | Font.ITALIC, label.getFont().getSize() + 9f));
		label.setBackground(Color.LIGHT_GRAY);
		label.setBounds(6, 6, 440, 62);
		label.setIcon(new ImageIcon("./src/imagenes/LOGO BBV.gif"));
		contentPane.add(label);
		
		JButton home = new JButton("");
		home.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clickAtras();
			}
		});
		home.setHorizontalAlignment(SwingConstants.LEFT);
		home.setBounds(6, 228, 48, 44);
		home.setIcon(new ImageIcon("./src/imagenes/home.png"));
		contentPane.add(home);
		
		JLabel lblIngreseCompania = new JLabel("INGRESE COMPANIA");
		lblIngreseCompania.setHorizontalAlignment(SwingConstants.CENTER);
		lblIngreseCompania.setForeground(new Color(30, 144, 255));
		lblIngreseCompania.setFont(lblIngreseCompania.getFont().deriveFont(lblIngreseCompania.getFont().getStyle() | Font.BOLD | Font.ITALIC));
		lblIngreseCompania.setBounds(167, 80, 141, 34);
		contentPane.add(lblIngreseCompania);
		
		JLabel lblIngreseNumero = new JLabel("INGRESE NUMERO");
		lblIngreseNumero.setHorizontalAlignment(SwingConstants.CENTER);
		lblIngreseNumero.setForeground(new Color(30, 144, 255));
		lblIngreseNumero.setFont(lblIngreseNumero.getFont().deriveFont(lblIngreseNumero.getFont().getStyle() | Font.BOLD | Font.ITALIC));
		lblIngreseNumero.setBounds(167, 137, 149, 34);
		contentPane.add(lblIngreseNumero);
		
		compField = new JTextField();
		compField.setBounds(135, 112, 208, 28);
		contentPane.add(compField);
		compField.setColumns(10);
		
		numeroField = new JTextField();
		numeroField.setColumns(10);
		numeroField.setBounds(135, 173, 208, 28);
		contentPane.add(numeroField);
		
		JButton btnNewButton = new JButton("Confirmar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				confirmar();
			}
		});
		btnNewButton.setBounds(167, 228, 117, 29);
		contentPane.add(btnNewButton);
	}
	public void clickAtras(){
		Banco.save(Banco.recuperarMiBanco());
		this.padre.clickAtras();
		this.dispose();
	}

	public void cerrarSesion(){
		Banco.save(Banco.recuperarMiBanco());
		this.padre.cerrarSesion();
		this.dispose();
		
	}
	public void confirmar(){
		Celular celular = new Celular(compField.getText(), numeroField.getText());
		Banco.recuperarMiBanco().verCliente(dni).setCelular(celular);
		OperacionRealizadaJFrame op = new OperacionRealizadaJFrame(this);
		op.setVisible(true);
		this.setVisible(false);
	}

}
