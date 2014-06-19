package view;
// CORREGIR CLICK ATRAS Y CERRAR SESION

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.JButton;

import banco.Banco;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class OperacionRealizadaJFrame extends JFrame {

	private JPanel contentPane;
	private JFrame padre;

	public OperacionRealizadaJFrame( JFrame padre) {
		this.padre = padre;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("./imagenes/symbol-check-icon.png"));
		lblNewLabel.setBounds(160, 49, 141, 112);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("OPERACION REALIZADA");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Apple Color Emoji", Font.BOLD, 24));
		lblNewLabel_1.setForeground(new Color(30, 144, 255));
		lblNewLabel_1.setBounds(25, 168, 401, 38);
		contentPane.add(lblNewLabel_1);
		
		JButton home = new JButton("");
		home.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clickAtras();
			}
		});
		home.setHorizontalAlignment(SwingConstants.LEFT);
		home.setBounds(6, 234, 48, 44);
		contentPane.add(home);
		home.setIcon(new ImageIcon("./imagenes/home.png"));

		
		JButton cerrarSesion = new JButton("");
		cerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cerrarSesion();
			}
		});
		cerrarSesion.setHorizontalAlignment(SwingConstants.LEFT);
		cerrarSesion.setBounds(396, 234, 48, 44);
		contentPane.add(cerrarSesion);
		cerrarSesion.setIcon(new ImageIcon("./imagenes/shut-down.png"));

	}

	
	public void clickAtras(){
		InicioJFrame inicio = InicioJFrame.recuperarInicio();
		Banco.save(Banco.recuperarMiBanco());
		OperacionJFrame operacion = OperacionJFrame.recuperarOperacion();
		operacion.setVisible(true);
		this.dispose();
	}

	public void cerrarSesion(){
		Banco.save(Banco.recuperarMiBanco());
		InicioJFrame inicio = InicioJFrame.recuperarInicio();
		inicio.setVisible(true);
		this.dispose();
		
	}
}
