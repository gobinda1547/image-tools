package org.gobinda;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileSystemView;

import java.awt.CardLayout;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class MainApplication extends JFrame {

	private JPanel contentPane;
	private JLabel outputLabel;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainApplication frame = new MainApplication();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainApplication() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 767, 476);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		JPanel optionPanel = new JPanel();
		contentPane.add(optionPanel, "name_101628146287374");
		optionPanel.setLayout(null);
		
		JButton btnMakeAnImage = new JButton("Make an Image Rouned");
		btnMakeAnImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					File imageFile = getFileAccordingToUserChoice();
					if(imageFile == null) {
						outputLabel.setText("Image Rouned operation failed! File = NULL");
						return;
					}
					BufferedImage inputImage = ImageIO.read(imageFile);
					int radius = Integer.parseInt(JOptionPane.showInputDialog(null, "Choose Corner Radius!"));
					BufferedImage outputImage = ImageOperationManager.makeRoundedCorner(inputImage, radius);
					String outputPathAbsolutePathString = imageFile.getParent()+"/gp_"+imageFile.getName();
					ImageIO.write(outputImage, "png", new File(outputPathAbsolutePathString));
				} catch (Exception e) {
					outputLabel.setText("Image Rouned operation failed! Choose valid Image!");
					return;
				}
				outputLabel.setText("Successfully rounded the image! Check the input folder!");
				return;
			}
		});
		btnMakeAnImage.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnMakeAnImage.setBounds(10, 73, 277, 37);
		optionPanel.add(btnMakeAnImage);
		
		JLabel lblNewLabel = new JLabel("Choose your option from below");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
	
		lblNewLabel.setBounds(10, 11, 731, 37);
		optionPanel.add(lblNewLabel);
		
		outputLabel = new JLabel("New label");
		outputLabel.setHorizontalAlignment(SwingConstants.CENTER);
		outputLabel.setBounds(10, 412, 731, 14);
		optionPanel.add(outputLabel);
	}

	protected File getFileAccordingToUserChoice() {
		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		int returnValue = jfc.showOpenDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			return jfc.getSelectedFile();
		}
		return null;
	}
}
