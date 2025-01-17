package chat;

import java.awt.BorderLayout;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.*;

import encryption.Encryption;

import java.security.*;


public class ChatServer extends JFrame {

	private static final String RSA = "RSA";
	private Key privateKey;
	private JTextArea textArea;
	private ServerSocket serverSocket;
	private List<ClientHandler> clientList = new ArrayList<>();
	
	public ChatServer() {
	
		super("Chat Server");

		try {
			privateKey = Encryption.readPrivateKey("keypairs/pkcs8_key");
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("problem loading private key: " + e.getMessage());
			System.exit(1);
		}
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		JScrollPane scrollPane = new JScrollPane(textArea);
		this.add(scrollPane, BorderLayout.CENTER);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(400, 350);
		this.setVisible(true);
		
		try {
			serverSocket = new ServerSocket(9898);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}		
		
	}
	
	
	public void start() {
		textArea.append("Chat server started at " + new Date() + "\n");
		try {
			while (true) {
				Socket socket = serverSocket.accept();
				ClientHandler clientHandler = new ClientHandler(socket);
				new Thread(clientHandler).start();
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	class ClientHandler implements Runnable {
		
		Socket socket;
		String address;
		int clientId;
		DataInputStream dataInputStream;
		DataOutputStream dataOutputStream;
		Key AESKey;
		
		public ClientHandler(Socket socket){
			this.socket = socket;
			InetAddress inetAddress = socket.getInetAddress();
			address = inetAddress.getHostAddress();
			try {
				dataInputStream = new DataInputStream(socket.getInputStream());
				dataOutputStream = new DataOutputStream(socket.getOutputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
			clientId = clientList.size() + 1;
		}
		
		public void sendMsgToClient(String decryptedMsg){
			try {
				String reEncryptedMsg = Encryption.encrypt(AESKey, decryptedMsg);
				dataOutputStream.writeUTF(reEncryptedMsg);
				dataOutputStream.flush();

			} catch (Exception e) {
				throw new RuntimeException(e);
			}

		}

		@Override
		public void run(){
			try {
				while (!dataInputStream.readUTF().equals("HELLO")) {
					
				}
				
				dataOutputStream.writeUTF("CONNECTED");
				dataOutputStream.flush();
				byte[] encryptedSeed = new byte[128];
				dataInputStream.read(encryptedSeed, 0, 128);
				byte[] seed = Encryption.pkDecrypt(privateKey, encryptedSeed);
				AESKey = Encryption.generateAESKey(seed);
				clientList.add(this);
				
				textArea.append("Starting thread for client " + clientId + " at " + new Date() + "\n");
				textArea.append("Client " + clientId + "'s host name is localhost\n");
				textArea.append("Client " + clientId + "'s IP Address is " + address + "\n");
				
				while (true) {
					String encryptedMessage = dataInputStream.readUTF();
					String decryptedMessage = Encryption.decrypt(AESKey, encryptedMessage);
					String message = clientId + ": " + decryptedMessage;
					
					for (ClientHandler client : clientList){
						if (!client.equals(this)){
							client.sendMsgToClient(message);
						}
					}
				}

			} catch (Exception e) {
				throw new RuntimeException(e);
			} finally {
				clientList.remove(this);
				try {
					dataOutputStream.close();
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	public static void main(String[] args) {
		ChatServer chatServer = new ChatServer();
		chatServer.start();
	}
	
	
}


