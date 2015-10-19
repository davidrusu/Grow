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
public class Ball extends Item{
    private static double range = 0.06;
    private int id;
    public Ball(double x, double y, double r, int id){
        //super(x, y, r, 0, 0);
        super(x, y, r, Math.random()*range, Math.random()*range);
        super.setId(id);
    }
    
    public Ball(double x, double y, double r, double dx, double dy, int id){
        //super(x, y, r, 0, 0);
        super(x, y, r, dx, dy);
        super.setId(id);
    }

    @Override
    void draw(Graphics2D g) {
        float oneTint = (float)r - (float)GameBoard.player1.r;
        float twoTint = (float)r - (float)GameBoard.player2.r;
        if(oneTint >= 0){
            if(oneTint < r+5){
                oneTint = 0;
            }else{
                oneTint = 0;
            }
        }else{
            oneTint = 1;
        }
        
        if(twoTint >= 0){
            if(twoTint < r+5){
                twoTint = 1;
            }else{
                twoTint = 1;
            }
        }else{
            twoTint = 0;
        }
        
        
        Color fill = new Color(1f, oneTint, twoTint);
        g.setColor(fill);
        g.fillOval((int)x, (int)y, (int)(r*2), (int)(r*2));
    }
}
