package com.gra;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Tamagotchi extends JFrame {

	int lvlFood = 5;
	int lvlFun = 5;
	int lvlSleep = 5;
	
	public void printInfo() {
		System.out.println("=============");
		System.out.println("Food: " + lvlFood);
		System.out.println("Fun: " + lvlFun);
		System.out.println("Sleep: " + lvlSleep);
		System.out.println("=============");
	}
	
	public int getLvl() {
		return Math.min(lvlFood, Math.min(lvlFun, lvlSleep));
	}
	
	class GameWindow extends JPanel {
		public GameWindow() {
			setLayout(null);
			
			addKeys();
		}
		
		public void addKeys() {
			BufferedImage image;
	
			try {
				ImageIcon iconKey = new ImageIcon(ImageIO.read(new File("grafika/klawisz.jpg")));

				JButton foodKey = new JButton();
				foodKey.setBounds(152, 290, 50, 25);
				foodKey.setIcon(iconKey);
				foodKey.setBorderPainted(false);
				foodKey.addActionListener(e -> {
					System.out.println("Om nom nom!");
					if(lvlFood < 5) lvlFood++;
					repaint();
				});
				
				JButton sleepKey = new JButton(iconKey);
				sleepKey.setBounds(258, 290, 50, 25);
				sleepKey.setBorderPainted(false);
				sleepKey.addActionListener(e -> {
					System.out.println("Zzzz!");
					if(lvlSleep < 5) lvlSleep++;
					repaint();
				});

				JButton funKey = new JButton(iconKey);
				funKey.setBounds(205, 310, 50, 25);
				funKey.setBorderPainted(false);
				funKey.addActionListener(e -> {
					System.out.println("He he he!");
					if(lvlFun< 5) lvlFun++;
					repaint();
				});
				
				add(foodKey);
				add(sleepKey);
				add(funKey);
			} catch (Exception ex) {
				System.out.println("incorrect path");
			}
		}
		
		@Override
		public Dimension getPreferredSize() {
			return new Dimension(450, 440);
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			
			BufferedImage image;
			
			try {
				image = ImageIO.read(new File("grafika/"+ getLvl() + ".jpg"));
				
				g.drawImage(image,  0,  0, this);
				
			} catch (Exception ex) {
				System.out.println("incorrect path");
			}
		}
	}
	
	public Tamagotchi(String name) {
		super(name);
		
		GameWindow window = new GameWindow();
		add(window);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		pack();
		
		Thread gameLogic = new Thread(new Runnable() {
			boolean didLose = false;
		
			@Override
			public void run() {
				while(!didLose) {
					try {
						Thread.sleep(1500);
						
						int randomChange = new Random().nextInt(3) +1;
						
						if(randomChange == 1) lvlFood--;
						if(randomChange == 2) lvlFun--;
						if(randomChange == 3) lvlSleep--;
						
						printInfo();
						
						if(getLvl() > 0) {
							repaint();
						} else {
							didLose = true;
						}
						
					} catch(Exception ex) {
						System.out.println("Something");
					}
				}
				
				JOptionPane.showMessageDialog(null, "You lost!");
			}
		});
		
		gameLogic.start();
		
	} 

	public static void main(String[] args) {
		Tamagotchi tamagotchi = new Tamagotchi("Tamagotchi");
		
		tamagotchi.printInfo();

	}

}
