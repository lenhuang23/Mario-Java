import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
import javax.swing.JButton;

class View extends JPanel
{
	BufferedImage image;
	Model model;
	int cameraPos;
	BufferedImage background;
	
	View(Controller c, Model m)
	{
		c.setView(this);
		model = m;
		cameraPos = 0;
		background = loadImage("Background.png");
	}
	
	static BufferedImage loadImage(String filename)
	{
		BufferedImage im = null;
		try
		{
			im = ImageIO.read(new File(filename));
			System.out.println(filename + " loaded.");
		}
		catch(Exception e)
		{
			e.printStackTrace(System.err);
			System.exit(1);
		}
		return im;
	}
	
	public void paintComponent(Graphics g)
	{
		g.setColor(new Color(128, 255, 255));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g.drawImage(background, 0 - model.mario.x / 2, 0, 1500, 696, null);
		//draw sprites
		for(int i = 0; i < model.sprites.size(); i++)
		{
			Sprite s = model.sprites.get(i);
			s.draw(g);
			// if(s.isBrick())
			// {
			// 	Sprite s = (Brick)model.sprites.get(i);
			// }
		}
		//g.setColor(Color.gray);
		//g.drawLine(0, 596, 2000, 596);
		//model.mario.draw(g);
	}

}