import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;


public class SaldoDisponibleJFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField titulo;
	private JTextField saldoDisp;

	private Long DNI;
	private Long CBU;
	

	/**
	 * Create the frame.
	 */
	public SaldoDisponibleJFrame(Long dni, Long cbu) {
		this.DNI = dni;
		this.CBU = cbu;
		
		String tituloF = Banco.recuperarMiBanco().verUsuario(this.DNI).getApellido() + ", " + Banco.recuperarMiBanco().verUsuario(this.DNI).getNombre() + " - " + this.CBU.toString();
		Double saldoDisponible = Banco.recuperarMiBanco().verCliente(this.DNI).getCuentasMonetarias().get(this.CBU).getSaldoActual();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		
		titulo = new JTextField();
		contentPane.add(titulo, BorderLayout.NORTH);
		titulo.setColumns(10);
		titulo.setText(tituloF);
		
		saldoDisp = new JTextField();
		contentPane.add(saldoDisp, BorderLayout.CENTER);
		saldoDisp.setColumns(10);
		saldoDisp.setText(saldoDisponible.toString());
		
		JButton Atras = new JButton("New button");
		contentPane.add(Atras, BorderLayout.SOUTH);
	}

}
