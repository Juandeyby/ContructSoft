import java.awt.EventQueue;
import java.awt.Image;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.UIManager;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.security.MessageDigest;
import java.util.Arrays;

import org.apache.commons.codec.binary.Base64;

public class Autentificador {

	private JFrame frame;
	private JTextField Usuario_field;
	private JTextField Contrasena_field;
	
	private String usuario = encriptar("admin");
	private String contrasena = encriptar("admin");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Autentificador window = new Autentificador();
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
	public Autentificador() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(UIManager.getColor("Viewport.background"));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Usuario");
		lblNewLabel.setBounds(112, 132, 115, 15);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Contraseña");
		lblNewLabel_1.setBounds(112, 170, 115, 15);
		frame.getContentPane().add(lblNewLabel_1);
		
		//Usuario
		Usuario_field = new JTextField();
		Usuario_field.setBounds(102, 148, 250, 19);
		frame.getContentPane().add(Usuario_field);
		Usuario_field.setColumns(10);
		
		//Contraseña
		Contrasena_field = new JPasswordField();
		Contrasena_field.setBounds(102, 190, 250, 19);
		frame.getContentPane().add(Contrasena_field);
		Contrasena_field.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Universidad Nacional de San Agustín");
		lblNewLabel_2.setBounds(138, 38, 300, 15);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Inicie sesion para acceder");
		lblNewLabel_3.setBounds(181, 67, 200, 15);
		frame.getContentPane().add(lblNewLabel_3);
		
		JButton btnNewButton = new JButton("Ingresar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Codigo para poder enviar a la siguiente venta una vez iniciado sesion
				try {
					String usuario1 = desencriptar(usuario);
					String contrasena1 = desencriptar(contrasena);
					//Encriptación
					if(Contrasena_field.getText().equals(usuario1) &&
							Usuario_field.getText().equals(contrasena1)) {
						JOptionPane.showMessageDialog(null,"Ingreso");
					} else {
						JOptionPane.showMessageDialog(null,"No Ingreso");
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(168, 233, 117, 25);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel_4 = new JLabel("");
		Image img  = new ImageIcon(this.getClass().getResource("/logo_unsa.png")).getImage();
		lblNewLabel_4.setIcon(new ImageIcon(img));
		lblNewLabel_4.setBounds(44, 12, 82, 113);
		frame.getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("¿Olvidate tu contraseña?");
		lblNewLabel_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Olvido_contrasena olvido = new Olvido_contrasena();
				olvido.frame.setVisible(true);
				frame.setVisible(false);
			}
		});
		lblNewLabel_5.setForeground(Color.RED);
		lblNewLabel_5.setFont(new Font("Dialog", Font.BOLD, 10));
		lblNewLabel_5.setBounds(200, 210, 156, 15);
		frame.getContentPane().add(lblNewLabel_5);
	}
	
	
	public static String encriptar(String texto) {

	       String secretKey = "constructSoft"; //llave para encriptar datos
	       String base64EncryptedString = "";

	       try {
	    	   MessageDigest md = MessageDigest.getInstance("MD5");
	           byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
	           byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);

	           SecretKey key = new SecretKeySpec(keyBytes, "DESede");
	           Cipher cipher = Cipher.getInstance("DESede");
	           cipher.init(Cipher.ENCRYPT_MODE, key);

	           byte[] plainTextBytes = texto.getBytes("utf-8");
	           byte[] buf = cipher.doFinal(plainTextBytes);
	           byte[] base64Bytes = Base64.encodeBase64(buf);
	           base64EncryptedString = new String(base64Bytes);

	       } catch (Exception ex) {
	       
	       }
	       return base64EncryptedString;
	}
	
	public static String desencriptar(String textoEncriptado) throws Exception {

	       String secretKey = "constructSoft"; //llave para desenciptar datos
	       String base64EncryptedString = "";

	       try {
	           byte[] message = Base64.decodeBase64(textoEncriptado.getBytes("utf-8"));
	           MessageDigest md = MessageDigest.getInstance("MD5");
	           byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
	           byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
	           SecretKey key = new SecretKeySpec(keyBytes, "DESede");

	           Cipher decipher = Cipher.getInstance("DESede");
	           decipher.init(Cipher.DECRYPT_MODE, key);

	           byte[] plainText = decipher.doFinal(message);

	           base64EncryptedString = new String(plainText, "UTF-8");

	       } catch (Exception ex) {
	       }
	       return base64EncryptedString;
	}
}
