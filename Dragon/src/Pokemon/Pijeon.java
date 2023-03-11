package Pokemon;
import java.awt.*;



public class Pijeon extends Pokemon{
   
    public Pijeon(int x, int y, Image image){
        super(x, y, 5, 100, 100, image);
    }

    @Override
    public void move() {
        this.setY(this.getY() + Pokemon.MOVE_Y);
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(this.getImage(), this.getX(), this.getY(), this.getWidth(), this.getHeight(), null);
    }
}
