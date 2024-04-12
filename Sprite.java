import java.awt.Graphics;
import java.awt.image.BufferedImage;

abstract class Sprite

{
    int x, y, w, h;
    boolean coinBrick;
    int coinCount;
    abstract boolean update();
    abstract void draw(Graphics g);

    boolean isCoinBrick()
    {
        return false;
    }

    boolean isBrick()
    {
        return false;
    }

    boolean isMario()
    {
        return false;
    }

    public boolean collision(Sprite s)
    {
        if(this.x + this.w <= s.x)
        return false;

        if(this.x >= s.x + s.w)
        return false;

        if(this.y + this.h <= s.y)
        return false;

        if(this.y >= s.y + s.h)
        return false;
        System.out.print("collision ");
        return true;
    }
}