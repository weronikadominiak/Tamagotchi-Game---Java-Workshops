package com.gra;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
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
	
	
	class GameWindow extends JPanel {
		
		public GameWindow() {
			setLayout(null);
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
				image = ImageIO.read(new File("grafika/5.jpg"));
				
				g.drawImage(image,  0,  0, this);
				
			} catch(Exception ex) {
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
	} 

	public static void main(String[] args) {
		Tamagotchi tamagotchi = new Tamagotchi("Tamagotchi");
		
		tamagotchi.printInfo();

	}

}
