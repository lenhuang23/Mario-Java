import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

class Controller implements ActionListener, MouseListener, KeyListener
{
	
	boolean keyLeft;
	boolean keyRight;
	boolean keyUp;
	boolean keyDown;
	boolean keySpace;
	Model model;
	View view;
	Mario mario;
	
	int x;
	int y;
	int w;
	int h;
	
	Brick brick;
	boolean edit = false;
	
	Controller(Model m)
	{
		model = m;
		edit = false;
	}
	
	void setView(View v)
	{
		view = v;
	}
	
public void actionPerformed(ActionEvent e)
	{
	}
	
public void mousePressed(MouseEvent e)
{
	model.setDestination(e.getX(), e.getY());
	x = e.getX() - model.mario.marioScreenLocation + model.mario.x;
	y = e.getY();
}

public void mouseReleased(MouseEvent e)
{  
	w = e.getX() - x + model.mario.x - model.mario.marioScreenLocation;
	h = e.getY() - y;
	if(edit == true)
	{
		Brick brick = new Brick(x, y, w, h, model);
		model.sprites.add(brick);
	}
}
public void mouseEntered(MouseEvent e) {    }
public void mouseExited(MouseEvent e) {    }
public void mouseClicked(MouseEvent e) {    }

public void keyPressed(KeyEvent e)
{
	
	switch(e.getKeyCode())
	{
		case KeyEvent.VK_RIGHT: keyRight = true; break;
		case KeyEvent.VK_LEFT: keyLeft = true; break;
		case KeyEvent.VK_UP: keyUp = true; break;
		case KeyEvent.VK_DOWN: keyDown = true; break;
		case KeyEvent.VK_SPACE: keySpace = true; break;
	}
}

public void keyReleased(KeyEvent e)
{
	switch(e.getKeyCode())
	{
		case KeyEvent.VK_RIGHT: keyRight = false; break;
		case KeyEvent.VK_LEFT: keyLeft = false; break;
		case KeyEvent.VK_UP: keyUp = false; break;
		case KeyEvent.VK_DOWN: keyDown = false; break;
		case KeyEvent.VK_SPACE: keySpace = false; break;
		case KeyEvent.VK_ESCAPE: System.out.println("Exiting out....");
		System.exit(0);
		break;
		
	}
	char c = e.getKeyChar();
if(c == 'q'  || c =='Q') {
		System.out.println("Exiting out....");
		System.exit(0);
	}
	if(c == 's'  || c =='S') {
		model.marshal().save("map.json");
		System.out.println("Lmao its been saved");
	}
	if(c == 'l' || c == 'L') {
		Json j = Json.load("map.json");
		model.unmarshal(j);
		System.out.println("loaded map.json");
	}
	if(c == 'e' || c == 'E')
	{
		edit = !edit;
	}
		
}

public void keyTyped(KeyEvent e)
{
}

void update()
{
	model.mario.savePreCoords();
	// model.mario.preY = model.mario.y;
	// model.mario.preX = model.mario.x;
	if(keyRight) 
	{
		// model.mario.preX = model.mario.x;
		model.mario.x += 5;
		model.mario.updateImageNum();
	}
	if(keyLeft)
	{
		//model.mario.preX = model.mario.x;
		model.mario.x -= 5;
		model.mario.updateImageNum();
	}
	if(keySpace)
	{
		if(model.mario.spaceCounter > 5)
		model.mario.preY = model.mario.y;
		model.mario.jump();
		
	}
}
}