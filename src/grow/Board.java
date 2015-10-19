/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package grow;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

/**
 *
 * @author davidrusu
 */
public class Board extends JLayeredPane implements KeyListener{
    public static GameBoard gameBoard;
    public static ArrayList<Item> items = new ArrayList<Item>();
    public static boolean keysPressed[] = {false, false, false, false, false, false, false, false};
    public static Menu menu;
    public Board(JFrame frame){
        setSize(frame.getSize());
        setBackground(Color.BLUE);
        menu = new Menu();
        gameBoard = new GameBoard(getWidth(), getHeight());
        this.addKeyListener(this);
        this.setFocusable(true);
        this.requestFocus();
        
        this.add(menu, JLayeredPane.PALETTE_LAYER);
        this.add(gameBoard, JLayeredPane.DEFAULT_LAYER);
    }
    
    public void reset(){
        items.clear();
        gameBoard.reset();
        
        keysPressed[0] = false;
        keysPressed[1] = false;
        keysPressed[2] = false;
        keysPressed[3] = false;
        keysPressed[4] = false;
        keysPressed[5] = false;
        keysPressed[6] = false;
        keysPressed[7] = false;
    }
    
    
    
    public void drawRelations(Graphics2D g){
        for(Item item: items){
            if(!item.isEmpty()){
                g.setColor(new Color(0.5f, 0.5f, 0.5f, 1f));
                g.drawLine((int)(item.x+item.r), (int)(item.y+item.r), (int)(gameBoard.player1.x+gameBoard.player1.r), (int)(gameBoard.player1.y+gameBoard.player1.r));
            }
            
        }
    }
    
    

    @Override
    public void keyTyped(KeyEvent ke) {
        
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        int keyCode = ke.getKeyCode();
        switch(keyCode){
            case KeyEvent.VK_UP: keysPressed[0] = true;break;
            case KeyEvent.VK_LEFT: keysPressed[1] = true;break;
            case KeyEvent.VK_DOWN: keysPressed[2] = true;break;
            case KeyEvent.VK_RIGHT: keysPressed[3] = true;break;
            case KeyEvent.VK_W: keysPressed[4] = true;break;
            case KeyEvent.VK_A: keysPressed[5] = true;break;
            case KeyEvent.VK_S: keysPressed[6] = true;break;
            case KeyEvent.VK_D: keysPressed[7] = true;break;
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        int keyCode = ke.getKeyCode();
        switch(keyCode){
            case KeyEvent.VK_UP: keysPressed[0] = false;break;
            case KeyEvent.VK_LEFT: keysPressed[1] = false;break;
            case KeyEvent.VK_DOWN: keysPressed[2] = false;break;
            case KeyEvent.VK_RIGHT: keysPressed[3] = false;break;
            case KeyEvent.VK_W: keysPressed[4] = false;break;
            case KeyEvent.VK_A: keysPressed[5] = false;break;
            case KeyEvent.VK_S: keysPressed[6] = false;break;
            case KeyEvent.VK_D: keysPressed[7] = false;break;
        }
    }
    
    @Override
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D gr = (Graphics2D)g;
        
        gameBoard.draw(gr, this.getWidth(), this.getHeight());
        //drawRelations(gr);
        gameBoard.player1.draw(gr);
        gameBoard.player2.draw(gr);
        try{
            for(Item item: items){
                if(!item.isEmpty()){
                    item.draw(gr);
                }
            }
        }catch(ConcurrentModificationException e){
            System.out.println("Board.paint Oops!");
        }
        //gameBoard.repaint();
        menu.repaint();
    }
    
}
