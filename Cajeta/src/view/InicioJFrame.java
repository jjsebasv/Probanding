package view;
// AYUDA CON ESTE
import java.awt.Color;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import banco.Banco;


public class InicioJFrame extends JFrame {

	private JPanel contentPane;
	private static InicioJFrame Inicio = null;

	/**
	 * Create the frame.
	 */
	private InicioJFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("INGRESAR");
		btnNewButton.setBackground(Color.WHITE);
	
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eventoClickIngresar();
			}
		});
		btnNewButton.setForeground(SystemColor.controlHighlight);
		btnNewButton.setBounds(84, 249, 117, 29);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("");
		ImageIcon imageIcon = new ImageIcon("./imagenes/mzl.fgseuiha.png");
		Image image = imageIcon.getImage().getScaledInstance(320, 278, Image.SCALE_SMOOTH);
		lblNewLabel.setIcon(new ImageIcon(image));
		lblNewLabel.setBounds(70, 0, 320, 278);
		contentPane.add(lblNewLabel);
		
		JButton button = new JButton("");
		button.setIcon(new ImageIcon("./imagenes/shut-down.png"));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		button.setHorizontalAlignment(SwingConstants.LEFT);
		button.setBounds(396, 234, 48, 44);
		contentPane.add(button);
		
		
	}
	
	public void eventoClickIngresar(){
		Banco.save(Banco.recuperarMiBanco());
		AutenticacionJFrame principal = new AutenticacionJFrame(this);
		principal.setVisible(true);
		this.setVisible(false);
	}

	public static InicioJFrame recuperarInicio() {
		  if( Inicio == null) {
			  Inicio = new InicioJFrame();
		  }
		  return Inicio;
	}
}
