/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package grow;
import java.awt.Color;
import java.awt.Graphics2D;
/**
 *
 * @author davidrusu
 */
public class Player extends Item{
    private static double speed = 0.04, thrustR = 2, tdx = 0.5, tdy = 0.5;;
    public Player(double x, double y, double r, double dx, double dy, int ID){
        super(x, y, r, dx,dy);
        super.setId(ID);
    }
    
    public void reset(double x, double y, double r, double dx, double dy){
        this.x = x;
        this.y = y;
        this.r = r;
        this.dx = dx;
        this.dy = dy;
    }
    
    public void moveX(int d){
        if(d == -1){
            //Board.items.add(new Ball(x+r+r+thrustR, y+r+thrustR, thrustR, tdx-dx, dy));
        }else{
            //Board.items.add(new Ball(x-thrustR*3, y+r+thrustR, thrustR, -tdx-dx, dy));
        }
        
        super.dx += speed*d;
    }
    
    public void moveY(int d){
        super.dy += speed*d;
        //r -= 0.1;
        if(d == -1){
            //Board.items.add(new Ball(x+r-thrustR, y+r+r+thrustR, thrustR, dx, tdy-dy));
        }else{
            //Board.items.add(new Ball(x+r-thrustR, y-thrustR*3, thrustR, dx, -tdy-dy));
        }
    }
    
    public void moveXY(int x, int y){
        double rr = Math.sqrt(r);
        if(x == 1 && y == 1){
            //Board.items.add(new Ball(this.x+r+rr+thrustR, this.y+r+rr+thrustR, thrustR, tdx-dx, tdy-dy));
            super.dx += speed;
            super.dy -= speed;
        }else if(x == -1 && y == 1){
            //Board.items.add(new Ball(this.x+r-rr-thrustR*3, this.y+r+rr+thrustR, thrustR, -tdx-dx, tdy-dy));
            super.dx -= speed;
            super.dy -= speed;
        }else if(x == -1 && y == -1){
            //Board.items.add(new Ball(this.x+r-rr-thrustR*3, this.y+r-rr-thrustR, thrustR, -tdx-dx, -tdy-dy));
            super.dx -= speed;
            super.dy += speed;
        }else if(x == 1 && y == -1){
            //Board.items.add(new Ball(this.x+r+rr+thrustR*3, this.y+r-rr-thrustR, thrustR, tdx-dx, -tdy-dy));
            super.dx += speed;
            super.dy += speed;
        }
        
        
    }

    @Override
    void draw(Graphics2D g) {
        g.setColor(Color.WHITE);
        g.fillOval((int)x, (int)y, (int)(r*2), (int)(r*2));
    }
    
}
