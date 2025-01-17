package ui;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import encode.Encoder;
import files.FileUtils;

public class FileDisplay extends JFrame {

	private JTextArea textArea;
	private static final int FRAME_WIDTH = 600;
	private static final int FRAME_HEIGHT = 500;
	private static final int AREA_ROWS = 20;
	private static final int AREA_COLUMNS = 40;
	
	public FileDisplay() {
		createMenu();
		createControlPanel();
		createDataPanel();
		
		pack();
		setLocationRelativeTo(null);
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
	}
	
	private void createMenu() {
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		menuBar.add(createFileMenu());
	}
	
	private JMenu createFileMenu() {
		JMenu menu = new JMenu("File");
		menu.add(createFileOpenItem());
		menu.add(createFileSaveItem());
		menu.add(createFileExitItem());
		return menu;
	}
	
	private JMenuItem createFileOpenItem() {
		JMenuItem item = new JMenuItem("Open File");      
		class MenuItemListener implements ActionListener {
			public void actionPerformed(ActionEvent event) {
				load(".");
			}
		}
		ActionListener listener = new MenuItemListener();
		item.addActionListener(listener);
		return item;
	}
	
	private JMenuItem createFileSaveItem() {
		JMenuItem item = new JMenuItem("Save File");      
		class MenuItemListener implements ActionListener {
			public void actionPerformed(ActionEvent event) {
				save(".");
			}
		}
		ActionListener listener = new MenuItemListener();
		item.addActionListener(listener);
		return item;
	}
	
	private JMenuItem createFileExitItem() {
		JMenuItem item = new JMenuItem("Exit");      
		class MenuItemListener implements ActionListener {
			public void actionPerformed(ActionEvent event) {
				System.exit(0);
			}
		}
		ActionListener listener = new MenuItemListener();
		item.addActionListener(listener);
		return item;
	}
	
	private void createControlPanel() {
		JLabel encodeLabel = new JLabel("Encoding Method ");
		JComboBox<String> encodeComboBox = new JComboBox<>();
		encodeComboBox.addItem("ROT13");
		encodeComboBox.addItem("Numeric");
		encodeComboBox.setEditable(false);
		JButton encodeButton = new JButton("Encode");
		class encodeListener implements ActionListener {
			public void actionPerformed(ActionEvent event) {
				String selectEncoding = (String)encodeComboBox.getSelectedItem();
				String inputText = textArea.getText();
				String encodedText = Encoder.encode(inputText, selectEncoding);
				textArea.setText(encodedText);
			}
		}
		ActionListener listener = new encodeListener();
		encodeButton.addActionListener(listener);
		
		JPanel controlPanel = new JPanel();
		controlPanel.add(encodeLabel);
		controlPanel.add(encodeComboBox);
		controlPanel.add(encodeButton);
		
		this.add(controlPanel, BorderLayout.NORTH);
	}
	
	private void createDataPanel() {
		textArea = new JTextArea(AREA_ROWS, AREA_COLUMNS);
		textArea.setEditable(true);
		textArea.setLineWrap(true);
	    textArea.setWrapStyleWord(true);
		JScrollPane scrollPanel = new JScrollPane(textArea, 
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		JPanel dataPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.weightx = 1.0;
	    gbc.weighty = 1.0;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.NORTH;
		dataPanel.add(scrollPanel, gbc);
		
		this.add(dataPanel);
	}
	
	private void save(String dir) {
		JFileChooser j = new JFileChooser(dir);
		int r = j.showSaveDialog(this);
		 
        // if the user selects a file
        if (r == JFileChooser.APPROVE_OPTION) {
            String fileName = j.getSelectedFile().getAbsolutePath();
            FileUtils.saveFile(fileName, textArea.getText());
        }
	}
	
	private void load(String dir) {
		JFileChooser j = new JFileChooser(dir);
		int r = j.showOpenDialog(this);
		 
        // if the user selects a file
        if (r == JFileChooser.APPROVE_OPTION) {
            String fileName = j.getSelectedFile().getAbsolutePath();
            String content = FileUtils.readFile(fileName);
            textArea.setText(content);
        }
	}
	
	public static void main(String[] args) {
		FileDisplay frame = new FileDisplay();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);;
		frame.setVisible(true);
	}
}
