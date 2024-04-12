import java.awt.Graphics;
import java.awt.image.BufferedImage;

class Mario extends Sprite
{
	float vert_vel;
	BufferedImage[] images = null;
	int imageNum;
	int marioScreenLocation;
	int preX;
	int preY;
	int spaceCounter;
	Model model;
	
	public Mario(Model m)
	{
		model = m;
		marioScreenLocation = 200;
		x = 0;
		y = 0;
		w = 60;
		h = 95;
		preX = x;
		preY = y;
		imageNum = 0;
		vert_vel = 0;
		if(images == null)
		{
			images = new BufferedImage[5];
			images[0] = View.loadImage("mario1.png");
			images[1] = View.loadImage("mario2.png");
			images[2] = View.loadImage("mario3.png");
			images[3] = View.loadImage("mario4.png");
			images[4] = View.loadImage("mario5.png");
		}
	}

	public boolean update()
	{
		spaceCounter++;
		vert_vel += 4.9;
		y += vert_vel;
		if(y > 500)
		{
			spaceCounter = 0;
			vert_vel = 0;
			y = 500;
		}
		return true;
	}

	void pullOutOfObstacle(Sprite s)
	{
		if(this.x + this.w >= s.x && this.preX + this.w <= s.x)//right side of mario
		this.x = s.x - this.w;

		else if(this.x <= s.x + s.w && this.preX >= s.x + s.w)//left side of mario
		this.x = s.x + s.w;

		else if(this.y + this.h >= s.y && this.preY + this.h <= s.y)//bottom of mario
		{
			spaceCounter = 0;
			this.y = s.y - this.h;
 			vert_vel = 0;
		}
		else if(this.y <= s.y + s.h && this.preY >= s.y + s.h)//top of mario
		{
			this.y = s.y + s.h;
			vert_vel = 0;
			if(s.coinBrick == true && s.coinCount > 0)
			{
				model.newCoin = true;
				model.x1 = s.x;
				model.y1 = s.y;
				s.coinCount = s.coinCount - 1; 
			}
		}

	}

	public void updateImageNum()
	{
		imageNum++;
		if(imageNum > 4)
			imageNum = 0;
	}

	void draw(Graphics g)
	{
		g.drawImage(images[imageNum], marioScreenLocation, y, null);
	}

	void savePreCoords()
	{
		preX = x;
		preY = y;
	}

	@Override
	boolean isMario()
	{
		return true;
	}
	// void pullOut(Brick brick)//Pulling mario out of brick
	// {
		
	// 	if(x + w >= brick.x && preX <= brick.x)
	// 	{
	// 		x = brick.x - w;
	// 	}
	// 	else if(x <= brick.x + brick.w && preX >= brick.x + brick.w)
	// 	{
	// 		x = brick.x + brick.w;
	// 	}
	// 	else if(y + h >= brick.y && preY <= brick.y)
	// 	{
	// 		spaceCounter = 0;
	// 		y = brick.y - h;
	// 		vert_vel = 0;
	// 	}
	// 	else if(y <= brick.y + brick.h && preY >= brick.y + brick.h)
	// 	{
	// 		y = brick.y + brick.h;
	// 	}
	// }
	void jump()
	{
		if(spaceCounter <= 5)
		{
			vert_vel -= 12;
		}
	}
}