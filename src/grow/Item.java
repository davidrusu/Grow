/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package grow;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.ConcurrentModificationException;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author davidrusu
 */
public abstract class Item {
    protected double x = 0, y = 0, r = 0, dx = 0, dy = 0;
    protected static double ddx = 0, ddy = 0, airFriction = 0.99, density = 2;
    private boolean empty = false;
    public static double timeSpeed = 0.1, shrink = 0.003;
    private int id;
    
    public Item(double x, double y, double r, double dx, double dy){
        this.x = x;
        this.y = y;
        this.r = r;
        this.dx = dx;
        this.dy = dy;
        //System.out.println(this.id + " " + this.r);
    }
    
    public void move(Dimension panel, double t){
        if(r > 0 && !Double.isNaN(r)){
            double mass = 1;
            t *= timeSpeed;
            
            // Handles shrink and centering balls
            r -= shrink*t;
            x += shrink*t;
            y += shrink*t;
            
            dx *= airFriction*mass;
            dy *= airFriction*mass;
            //System.out.println(airFriction*mass);
            x += (dx*t + 0.5*ddx*t*t);
            y += (dy*t + 0.5*ddy*t*t);
            //System.out.println("Item move x,y:" + x + ", " + y);

            if(x <= 0 || x+r*2 >= panel.getWidth()){
                //t -= x - panel.getWidth()/dx;
                dx *= -1;
                if (x>0){
                    x = panel.getWidth()-r*2;
                }else{
                    x = 0;
                }
                //x += dx*t + 0.5*ddx*t*t;
            }
            if(y <= 0 || y+r*2 >= panel.getHeight()){
                //t -= y - panel.getHeight()/dy;
                dy *= -1;
                if(y > 0){
                    y = panel.getHeight()-r*2;
                }else{
                    y = 0;
                }
                //y += dy*t +0.5*ddx*t*t;
            }
            checkCollision();
            if(id < 0){
                checkPlayerCollision();
            }
        }else{
            empty = true;
        }
        //System.out.println(this.id + " " + this.r);
    }
    
    public boolean isEmpty(){
        return(empty);
    }
    
    public void resize(double bigR, double smallR, double c, double dir){
        double largeArea = 3.14*bigR*bigR;
        double smallArea = 3.14*smallR*smallR;
        double interR = c - bigR - smallR;
        //System.out.println(interR + " " + r);
        //System.out.println("id: " + id + " bR, sR, R: " + bigR  + ", " + smallR + ", " + r);
        System.out.println("ID bigR smallR R: " + this.id + ", " + bigR + " , " + smallR + ", " + r + isEmpty());
        if(!Double.isNaN(smallR) && !Double.isNaN(bigR)){
            if(interR < r){
                double intersectArea = smallArea - 3.14*(smallR-interR)*(smallR-interR);
                double newR = Math.sqrt((3.14*r*r-intersectArea*dir)/3.14);

                
                //System.out.println("largeArea : " + largeArea + " smallArea: " + smallArea + " interR: " + interR + " intersectArea: " + intersectArea + " newR: " + newR);
                x += (r-newR);
                y += (r-newR);
                r = newR;
                //System.out.println(this.r);
                //System.out.println(intersect + " " + this.r + " " + r + " " + direction);
                if(r <= 0.5 || Double.isNaN(r)){
                    empty = true;
                }
            }else{
                //System.out.println("oops!!" + "id: " + id + " bR, sR, R: " + bigR  + ", " + smallR + ", " + r);
                if(dir == 1){
                    r += Math.sqrt(smallArea/3.14);
                }else{
                    r = 0;
                }
                empty = true;
            }
        }//else{
            //empty = true;
        //}
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    private void checkCollision(){
        for(Item item: Board.items){

            // need to switch from checking the radius' to determine if they are the same item to checking the item ID
            if(!item.isEmpty() && item.r != r){
                //System.out.println("item r, r:: " + item.id + " " + item.r + ", "+ this.id + " " + this.r);
                double a = Math.abs(item.x+item.r - x-r);
                double b = Math.abs(item.y+item.r - y-r);
                double c = a*a + b*b;
                //System.out.println("after    :: " + item.id + " " + item.r + ", "+ this.id + " " + this.r);
                if(c < (item.r + r)*(item.r + r)){
                    c = Math.sqrt(c);
                    if(item.r > r){
                        item.resize(item.r, r, c, 1);
                        resize(item.r, r, c, -1);

                    }else{
                        item.resize(r, item.r, c, -1);
                        resize(r, item.r, c, 1);
                        if(this.id < 0){
                            resize(item.r, r, c, -1);
                        }
                    }
                }
            }
        }
    }
    
    private void checkPlayerCollision(){
        if(id == -1){
            double a = Math.abs(GameBoard.player2.x+GameBoard.player2.r - x-r);
            double b = Math.abs(GameBoard.player2.y+GameBoard.player2.r - y-r);
            double c = a*a + b*b;
            //System.out.println("after    :: " + item.id + " " + item.r + ", "+ this.id + " " + this.r);
            if(c < (GameBoard.player2.r + r)*(GameBoard.player2.r + r)){
                c = Math.sqrt(c);
                if(GameBoard.player2.r > r){
                    GameBoard.player2.resize(GameBoard.player2.r, r, c, 1);
                    resize(GameBoard.player2.r, r, c, -1);

                }else{
                    GameBoard.player2.resize(r, GameBoard.player2.r, c, -1);
                    resize(r, GameBoard.player2.r, c, 1);
                }
            }
        }else{
            double a = Math.abs(GameBoard.player1.x+GameBoard.player1.r - x-r);
            double b = Math.abs(GameBoard.player1.y+GameBoard.player1.r - y-r);
            double c = a*a + b*b;
            //System.out.println("after    :: " + item.id + " " + item.r + ", "+ this.id + " " + this.r);
            if(c < (GameBoard.player1.r + r)*(GameBoard.player1.r + r)){
                c = Math.sqrt(c);
                if(GameBoard.player1.r > r){
                    GameBoard.player1.resize(GameBoard.player1.r, r, c, 1);
                    resize(GameBoard.player1.r, r, c, -1);

                }else{
                    GameBoard.player1.resize(r, GameBoard.player2.r, c, -1);
                    resize(r, GameBoard.player1.r, c, 1);
                }
            }
        }
    }
    
    abstract void draw(Graphics2D g);
}
