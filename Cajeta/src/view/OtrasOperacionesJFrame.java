package view;

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

import banco.Banco;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class OtrasOperacionesJFrame extends JFrame {

	private JPanel contentPane;
	private OperacionJFrame padre;
	private long dni;

	/**
	 * Create the frame.
	 */
	public OtrasOperacionesJFrame(long dni, OperacionJFrame padre) {
		this.dni = dni;
		this.padre = padre;
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
		btnRecargaDeCelular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				recargaCelular();
			}
		});
		btnRecargaDeCelular.setIcon(new ImageIcon("./src/imagenes/1307051141_737.png"));
		btnRecargaDeCelular.setHorizontalAlignment(SwingConstants.LEFT);
		btnRecargaDeCelular.setBounds(29, 77, 178, 29);
		contentPane.add(btnRecargaDeCelular);
		
		JButton btnAsociacionTcCoord = new JButton("ASOCIACION COORD");
		btnAsociacionTcCoord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Coordenadas();
			}
		});
		btnAsociacionTcCoord.setIcon(new ImageIcon("./src/imagenes/1307051141_737.png"));
		btnAsociacionTcCoord.setHorizontalAlignment(SwingConstants.LEFT);
		btnAsociacionTcCoord.setBounds(29, 118, 178, 29);
		contentPane.add(btnAsociacionTcCoord);
		
		JButton btnRegistroCelular = new JButton("REGISTRO CELULAR");
		btnRegistroCelular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registroCelular();
			}
		});
		btnRegistroCelular.setIcon(new ImageIcon("./src/imagenes/1307051141_737.png"));
		btnRegistroCelular.setHorizontalAlignment(SwingConstants.LEFT);
		btnRegistroCelular.setBounds(239, 77, 178, 29);
		contentPane.add(btnRegistroCelular);
		
		JButton button_5 = new JButton("");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cerrarSesion();
			}
		});
		button_5.setIcon(new ImageIcon("./src/imagenes/shut-down.png"));
		button_5.setHorizontalAlignment(SwingConstants.LEFT);
		button_5.setBounds(398, 234, 48, 44);
		contentPane.add(button_5);
		
		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clickAtras();
			}
		});
		button.setIcon(new ImageIcon("./src/imagenes/home.png"));
		button.setHorizontalAlignment(SwingConstants.LEFT);
		button.setBounds(6, 234, 48, 44);
		contentPane.add(button);
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
	
	public void Coordenadas(){
		AsociacionCoordenadasJFrame rc = new AsociacionCoordenadasJFrame(dni,this);
		rc.setVisible(true);
		this.setVisible(false);
	}
	
	public void registroCelular(){
		RegistroCelularJFrame rc = new RegistroCelularJFrame(dni,this);
		rc.setVisible(true);
		this.setVisible(false);
	}
	
	public void recargaCelular(){
		RecargaDeCelularJFrame rc = new RecargaDeCelularJFrame(dni,this);
		rc.setVisible(true);
		this.setVisible(false);
	}
}
