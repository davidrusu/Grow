/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package grow;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author davidrusu
 */
public class GameBoard extends JPanel{
    public static int width, height;
    public static double defaultR = 10;
    public static Player player1, player2;


    public GameBoard(int width, int height){
        setSize(width, height);
        setBackground(new Color(10, 10, 10));
        player1 = new Player((getWidth()*0.66)-defaultR, getHeight()/2-defaultR, defaultR, 0, 0, -1);
        player2 = new Player((getWidth()*0.33)-defaultR, getHeight()/2-defaultR, defaultR, 0, 0, -2);
        generateBalls();
    }
    
    public void reset(){
        player1 = new Player((getWidth()*0.66)-defaultR, getHeight()/2-defaultR, defaultR, 0, 0, -1);
        player2 = new Player((getWidth()*0.33)-defaultR, getHeight()/2-defaultR, defaultR, 0, 0, -2);
        generateBalls();
    }
    
    private void generateBalls(){
        for(int i = 0; i <= getWidth()*getHeight()/10000; i++){
            double x, y, area = 30, tdx, tdy, r = Math.random()*defaultR*2, range = 0.1;
            do{
                do{
                    x = Math.random()*(getWidth());
                    y = Math.random()*(getHeight());
                }while(!(x < player1.x-area || x > player1.x+area || y < player1.y-area || y > player1.y+area));
            }while(!(x < player2.x-area || x > player2.x+area && y < player2.y-area || y > player2.y+area));
            
            if(x + r > this.getWidth()/2){
                tdx = -Math.random()*range;
            }else{
                tdx = Math.random()*range;
            }
            
            if(y + r > this.getHeight()/2){
                tdy = -Math.random()*range;
            }else{
                
                tdy = Math.random()*range;
                System.out.println(y + " " + r);
            }
            
            Board.items.add(new Ball(x, y, r, tdx, tdy, i));
        }
    }
    
    public void draw(Graphics2D g, int width, int height){
        this.setSize(width, height);
        this.repaint();
    }
}
