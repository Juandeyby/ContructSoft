import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Olvido_contrasena {

	public JFrame frame;
	private JTextField textField;
	private JTextField nueva_contrasena_field;
	private JTextField pregunta_field;
	private JTextField respuesta_field;
	private JTextField repita_contrasena_field;

	private String pregunta = "¿Quien es el precursor de la informática moderna?";
	private String respuesta = "Turing";
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Olvido_contrasena window = new Olvido_contrasena();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Olvido_contrasena() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel(pregunta);
		lblNewLabel.setBounds(40, 12, 380, 15);
		frame.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(85, 34, 261, 19);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("------------------------------------------------------------------------------------");
		lblNewLabel_4.setBounds(12, 102, 426, 15);
		frame.getContentPane().add(lblNewLabel_4);
		
		//Invisible
		
		nueva_contrasena_field = new JTextField();
		nueva_contrasena_field.setBounds(256, 183, 114, 19);
		frame.getContentPane().add(nueva_contrasena_field);
		nueva_contrasena_field.setColumns(10);
		nueva_contrasena_field.setVisible(false);
		
		pregunta_field = new JTextField();
		pregunta_field.setBounds(190, 129, 212, 19);
		frame.getContentPane().add(pregunta_field);
		pregunta_field.setColumns(10);
		pregunta_field.setVisible(false);
		
		JLabel nueva_pregunta = new JLabel("Nueva Pregunta");
		nueva_pregunta.setBounds(40, 131, 133, 15);
		frame.getContentPane().add(nueva_pregunta);
		nueva_pregunta.setVisible(false);
		
		JLabel nueva_contrasena_label = new JLabel("Nueva Contraseña");
		nueva_contrasena_label.setBounds(109, 185, 153, 15);
		frame.getContentPane().add(nueva_contrasena_label);
		nueva_contrasena_label.setVisible(false);
		
		JButton restabler_boton = new JButton("Guardar Cambios");
		restabler_boton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pregunta = pregunta_field.getText().toLowerCase();
				respuesta = respuesta_field.getText().toLowerCase();
				
				if (nueva_contrasena_field.getText().equals(repita_contrasena_field.getText())) {
					JOptionPane.showMessageDialog(null,"Exito en Cambiar Pregunta y Contraseña");
					Sesion_administrador sesion = new Sesion_administrador();
					sesion.frame.setVisible(true);
					frame.setVisible(false);
					
				} else {
					nueva_contrasena_field.setText("");
					repita_contrasena_field.setText("");
					JOptionPane.showMessageDialog(null,"Vuelva a Introducir la Contraseña");
				}
			}
		});
		restabler_boton.setBounds(119, 238, 250, 25);
		frame.getContentPane().add(restabler_boton);
		restabler_boton.setVisible(false);
		
		JLabel respuesta_label = new JLabel("Respuesta");
		respuesta_label.setBounds(40, 158, 141, 15);
		frame.getContentPane().add(respuesta_label);
		respuesta_label.setVisible(false);
		
		respuesta_field = new JTextField();
		respuesta_field.setBounds(190, 156, 212, 19);
		frame.getContentPane().add(respuesta_field);
		respuesta_field.setColumns(10);
		respuesta_field.setVisible(false);
		
		JLabel repita_contrasena_label = new JLabel("Repita Contraseña");
		repita_contrasena_label.setBounds(109, 211, 153, 15);
		frame.getContentPane().add(repita_contrasena_label);
		repita_contrasena_label.setVisible(false);
		
		repita_contrasena_field = new JTextField();
		repita_contrasena_field.setBounds(256, 209, 114, 19);
		frame.getContentPane().add(repita_contrasena_field);
		repita_contrasena_field.setColumns(10);
		repita_contrasena_field.setVisible(false);
		
		JButton btnNewButton = new JButton("Restablecer Contraseña");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textField.getText().toUpperCase().equals(respuesta.toUpperCase())) {
					respuesta_label.setVisible(true);
					respuesta_field.setVisible(true);
					repita_contrasena_label.setVisible(true);
					repita_contrasena_field.setVisible(true);
					restabler_boton.setVisible(true);
					nueva_pregunta.setVisible(true);
					nueva_contrasena_label.setVisible(true);
					pregunta_field.setVisible(true);
					nueva_contrasena_field.setVisible(true);
				};
			}
		});
		btnNewButton.setBounds(85, 65, 261, 25);
		frame.getContentPane().add(btnNewButton);
	}

}
