import java.util.ArrayList;
import java.util.Iterator;

class Model
{
	/* ArrayList<Brick> bricks;*/ 
	int cameraPos;
	Mario mario;
	Coin coin;
	ArrayList<Sprite> sprites; 
	boolean newCoin;
	int x1;
	int y1;
	
	Model()
	{ 
		sprites = new ArrayList<Sprite>();
		mario = new Mario(this);
		coin = new Coin();
		sprites.add(mario);
		Json j = Json.load("map.json");
		this.unmarshal(j);
		System.out.println("loaded map.json");
	}

	public void update()
	{
		ArrayList<Sprite> coins = new ArrayList<Sprite>();
		Iterator<Sprite> iter = sprites.iterator();
		while(iter.hasNext())
		{
			Sprite s = iter.next();
			if( !s.update() )
			{
				iter.remove();
				break;
			}
			if(s.isBrick())
			{
				if(mario.collision(s))
					mario.pullOutOfObstacle(s);
			}
			if(newCoin)
			{
				System.out.println("added");
				coins.add(coin);
				newCoin = false;
			}
		}
		for(int i = 0; i < coins.size(); i++)
			sprites.add(new Coin(this, x1, y1));
	}

	public void setDestination(int x, int y)
	{

	}
	// Marshals this object into a JSON DOM
  Json marshal()
  {
      Json ob = Json.newObject();
     
      Json tmpList = Json.newList();
      ob.add("brick", tmpList);
	  for(int i = 0; i < sprites.size(); i++)
	  {
		Sprite s = sprites.get(i);
		if(s.isBrick())
		{
			Brick b = (Brick)s;
			tmpList.add(b.marshal());
		}
	  }
    //   for(int i = 0; i < sprites.size(); i++)
	//   {
	// 		Sprite s = sprites.get(i);
	// 		if(s.isBrick())
    // 		// tmpList.add(((Brick)s).marshal());
	// 		Brick b = (Brick)s;
	// 		tmpList.add(b.marshal());
	//   }
      return ob;
  }
  void unmarshal(Json obj) {
	  
	  sprites = new ArrayList<Sprite>();
	  mario = new Mario(this);
	  sprites.add(mario);
	  Json tmpList = obj.get("brick");
	  for(int i = 0; i < tmpList.size(); i++)
		  sprites.add(new Brick(tmpList.get(i), this));
		  
  }
//   boolean collision(Brick brick)//checks if mario is colliding with bricks
//   {
	  
// 	if(mario.x + mario.w < brick.x)
// 	return false;
// 	if(mario.x > brick.x + brick.w)
// 	return false;
// 	if(mario.y + mario.h < brick.y) // assumes bigger is downward
// 	return false;
// 	if(mario.y > brick.y + brick.h) // assumes bigger is downward
// 	return false;
	
// 	return true;
//   }
//   void collision2()
//   {
// 	  for (int i = 0; i < bricks.size(); i++)
// 	  {
// 		  Brick brick = bricks.get(i);
// 		  if (collision(brick))
// 		  {
// 			  mario.pullOut(brick);
// 		  }
// 	  }
//   }

}