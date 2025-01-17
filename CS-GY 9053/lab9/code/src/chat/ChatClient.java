package chat;

import java.awt.BorderLayout;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.security.*;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.*;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.*;

import encryption.Encryption;


public class ChatClient extends JFrame {

	private static final String RSA = "RSA";
	private static final String SERVER_PUBLIC_KEY = "MIGeMA0GCSqGSIb3DQEBAQUAA4GMADCBiAKBgGk9wUQ4G9PChyL5SUkCyuHjTNOglEy5h4KEi0xpgjxi/UbIH27NXLXOr94JP1N5pa1BbaVSxlvpuCDF0jF9jlZw5IbBg1OW2R1zUACK+NrUIAYHWtagG7KB/YcyNXHOZ6Icv2lXXd7MbIao3ShrUVXo3u+5BJFCEibd8a/JD/KpAgMBAAE=";
	private PublicKey serverPublicKey;
	private Key communicationKey;
	
	Socket socket;
	DataInputStream dataInputStream;
	DataOutputStream dataOutputStream;

	JTextArea textArea;
	JTextField textField;
	
	public ChatClient() {
		super("Chat Client");
		
		try {
			serverPublicKey = Encryption.readPublicKey(SERVER_PUBLIC_KEY);			
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("error getting server public key: " + e.getMessage());
		}
		
		textArea = new JTextArea(16, 50);
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		JScrollPane scrollPane = new JScrollPane(textArea);
		this.add(scrollPane, BorderLayout.NORTH);
		textField = new JTextField();
		textField.addActionListener(e -> {
			String msg = textField.getText();
			textArea.append(msg + "\n");
			try {
				String encryptedMsg = Encryption.encrypt(communicationKey, msg);
				dataOutputStream.writeUTF(encryptedMsg);
				dataOutputStream.flush();
			} catch (Exception exception){
				exception.printStackTrace();
			}
			textField.setText("");
		});
		this.add(textField);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(400, 350);
		this.setVisible(true);

		try {
			socket = new Socket("127.0.0.1", 9898);
			dataInputStream = new DataInputStream(socket.getInputStream());
			dataOutputStream = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		try {
			dataOutputStream.writeUTF("HELLO");
			dataOutputStream.flush();
			while (!dataInputStream.readUTF().equals("CONNECTED")){
				
			}
			byte[] seed = Encryption.generateSeed();
			byte[] encryptedSeed = Encryption.pkEncrypt(serverPublicKey, seed);
			dataOutputStream.write(encryptedSeed);
			dataOutputStream.flush();
			communicationKey = Encryption.generateAESKey(seed);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		textArea.append("connected\n");
		
	}
	
	public void start() {
		while (true) {
			try {
				String encryptedMsg = dataInputStream.readUTF();
				String msg = Encryption.decrypt(communicationKey, encryptedMsg);
				textArea.append(msg + "\n");
			} catch (Exception e) {
				e.printStackTrace();
				System.exit(0);
			}
		}
	}
	
	
	public static void main(String[] args) {
		ChatClient chatClient = new ChatClient();
		chatClient.start();
	}
}
