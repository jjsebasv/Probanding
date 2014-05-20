
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.SystemColor;


public class InicioJFrame extends JFrame {

	private JPanel contentPane;


	/**
	 * Create the frame.
	 */
	public InicioJFrame() {
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
		lblNewLabel.setIcon(new ImageIcon("/Users/user/Pictures/Pictures/mzl.fgseuiha-001.jpg"));
		lblNewLabel.setBounds(70, 0, 320, 278);
		contentPane.add(lblNewLabel);
		
		JButton button = new JButton("");
		button.setIcon(new ImageIcon("/Users/user/Pictures/shut-down.png"));
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
		AutenticacionJFrame principal = new AutenticacionJFrame(this);
		principal.setVisible(true);
		this.hide();
	}

}
