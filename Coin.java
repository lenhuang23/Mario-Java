import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.util.Random;

public class Coin extends Sprite
{
	int hori_vel;
    int vert_vel = -3;
	static BufferedImage image = null;
	Model model;
	Random rand = new Random();
	
	int upperbound = 25;
	int int_random = rand.nextInt(upperbound);
	
boolean update()
{
	vert_vel += 1;
    y += vert_vel;
	//n = 4;

    //hori_vel = rand;
    x += int_random;
	//x += rand;
    return true;
}

	public Coin(Model m, int x1, int y1)
	{
		this.x = x1;
		this.y = y1 - 75;
		w = 75;
		h = 75;
        vert_vel = -3;
        hori_vel = 5;
		loadImage();
		model = m;
	}
	
    Coin()
    {

    }

	void loadImage()
	{
		if(image == null)
			image = View.loadImage("Coin.png");
	}
	
	
	void draw(Graphics g)
	{
		g.drawImage(image, x - model.mario.x + model.mario.marioScreenLocation, y, w, h, null);
	}

	@Override
	boolean isBrick()
	{
		return true;
	}
}