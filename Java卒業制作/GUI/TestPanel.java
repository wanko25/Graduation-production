package GUI;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

class TestPanel extends JPanel{
	BufferedImage image;
	TestPanel(){
		image = new BufferedImage(1200,800,BufferedImage.TYPE_INT_RGB);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image,0,0,this);
	}
	
	public void draw() {
		this.repaint();
	}
}
