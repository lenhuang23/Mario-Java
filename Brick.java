import java.awt.image.BufferedImage;
import java.awt.Graphics;

public class Brick extends Sprite
{
	
	static BufferedImage image = null;
	static BufferedImage image2 = null;
	Model model;

	
boolean update()
{
	int n = (int) Math.random()*10;
	return (n%5==0);
}

	Brick(int x, int y, int w, int h, Model m)
	{
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		coinBrick = false;
		coinCount = 5;
		loadImage();
		model = m;
	}
	
	void loadImage()
	{
		if(image == null)
			image = View.loadImage("Brick.png");

		if(image2 == null)
			image2 = View.loadImage("CoinBrick.png");
	}
	
	//marshal
	Json marshal()
  {
      Json ob = Json.newObject();
      ob.add("x", x);
      ob.add("y", y);
      ob.add("w", w);
      ob.add("h", h);
	  ob.add("coinBrick", coinBrick);
	  ob.add("coinCount", coinCount);
      return ob;
  }
	//unmarshal
	public Brick(Json obj, Model m) {
		x = (int)obj.getLong("x");
		y = (int)obj.getLong("y");
		w = (int)obj.getLong("w");
		h = (int)obj.getLong("h");
		coinBrick = (boolean)obj.getBool("coinBrick");
		coinCount = (int)obj.getLong("coinCount");
		loadImage();
		model = m;
	}
	void draw(Graphics g)
	{
		if(coinBrick && coinCount > 0)
		{
			g.drawImage(image2, x - model.mario.x + model.mario.marioScreenLocation, y, w, h, null);
		}

		else
		{
			g.drawImage(image, x - model.mario.x + model.mario.marioScreenLocation, y, w, h, null);
		}
	}



	@Override
	boolean isBrick()
	{
		return true;
	}
}
