package Pokemon;

import java.awt.*;

// 오른쪽에서 왼쪽 대각선 움직임
public class Freeja extends Pokemon{
    
    public Freeja(int x, int y, Image image){
        super(x, y, 8, 250, 250, image);
    }
    @Override
    public void move() {
    	  this.setX( this.getX() + Pokemon.MOVE_Y);
          this.setY( this.getY() + Pokemon.MOVE_X);
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(this.getImage(), this.getX(), this.getY(), this.getWidth(), this.getHeight(), null);
    }
}
