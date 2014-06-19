package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import banco.Banco;
import banco.CuentaCredito;
import banco.Resumen;

import java.awt.Color;

import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.SwingConstants;
import javax.swing.JComboBox;

public class SeleccionPeriodoJFrame extends JFrame {

	private JPanel contentPane;
	private ImpresionResumenJFrame padre;
	private final long dni;
	private CuentaCredito cuenta; 
	/**
	 * Create the frame.
	 */
	public SeleccionPeriodoJFrame(long dni, CuentaCredito cuenta, ImpresionResumenJFrame padre) {
		this.cuenta = cuenta;
		this.dni = dni;
		this.padre = padre;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("./imagenes/LOGO BBV.gif"));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(new Color(0, 191, 255));
		label.setFont(label.getFont().deriveFont(label.getFont().getStyle() | Font.BOLD | Font.ITALIC, label.getFont().getSize() + 9f));
		label.setBackground(Color.LIGHT_GRAY);
		label.setBounds(6, 6, 440, 62);
		contentPane.add(label);
		
		int i = 0;
		int cantR = cuenta.getResumenes().size();
		String[] nombreResumenes = new String[cantR];
		
		for (Long nroR : cuenta.getResumenes().keySet()) {
			nombreResumenes[i] = cuenta.getResumenes().get(nroR).toString();
			i++;
		}

		final JComboBox comboBox = new JComboBox(nombreResumenes);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					imprimirResumen(comboBox.getSelectedItem().toString());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		comboBox.setBounds(126, 127, 218, 50);
		contentPane.add(comboBox);
		
		JLabel label_1 = new JLabel("SELECCIONE \nLA CUENTA");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setForeground(new Color(30, 144, 255));
		label_1.setFont(label_1.getFont().deriveFont(label_1.getFont().getStyle() | Font.BOLD | Font.ITALIC));
		label_1.setBounds(133, 98, 211, 34);
		contentPane.add(label_1);
		
		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		button.setIcon(new ImageIcon("./imagenes/shut-down.png"));
		button.setHorizontalAlignment(SwingConstants.LEFT);
		button.setBounds(396, 228, 48, 44);
		contentPane.add(button);
		
		
		JButton home = new JButton("");
		home.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clickAtras();
			}
		});
		home.setHorizontalAlignment(SwingConstants.LEFT);
		home.setBounds(6, 228, 48, 44);
		contentPane.add(home);
		home.setIcon(new ImageIcon("./imagenes/home.png"));
		home.setVisible(true);
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
	
	public void imprimirResumen(String nroR) throws IOException{
		Long nroResumen = 0L;
		for (Resumen resumen : cuenta.getResumenes().values()) {
			if ( resumen.toString().equals(nroR)){
				nroResumen = resumen.getNumeroResumen();
			}
		}
		Resumen r = cuenta.getResumenes().get(nroResumen);
		r.impresionResumen();
		OperacionRealizadaJFrame op = new OperacionRealizadaJFrame(this);
		this.setVisible(false);
		op.setVisible(true);
	}
}
