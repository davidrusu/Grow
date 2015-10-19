/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package grow;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ConcurrentModificationException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author davidrusu
 */
public class GameCore implements Runnable{
    private boolean running = true;
    private static double FPS;
    private long lastRunTime = System.nanoTime();
    public void stop(boolean running){
        this.running = running;
    }
    
    @Override
    public void run() {
        while(running){
            long time = System.nanoTime();
            double timeDiff = (time-lastRunTime)/1000000;
            lastRunTime = time;
            
            try{
            for(Item item: Board.items){
                if(!item.isEmpty()){
                    item.move(Grow.board.gameBoard.getSize(), timeDiff);
                }
            }
            GameBoard.player1.move(Grow.board.gameBoard.getSize(), timeDiff);
            GameBoard.player2.move(Grow.board.gameBoard.getSize(), timeDiff);
            }catch(ConcurrentModificationException e){
                System.out.println("ConcurrentModificationException!!!!!!!!!!!!!!!!!!!!");
            }
            movePlayers();
            
            Grow.board.repaint();
            
            long sleepTime = 16 - (System.nanoTime()-time)/1000000;
            if(sleepTime >= 0){
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException ex) {
                    Logger.getLogger(GameCore.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }else{
                Thread.yield();
            }
            //if(sleepTime != 0){
                //Board.menu.fps = 1000/sleepTime;
                //Board.menu.updateFPS();
            //}else{
            //    Board.menu.fps = 62.5;
            //    Board.menu.updateFPS();
            //}
        }
    }
    
    public void movePlayers(){
        if(Board.keysPressed[0] && Board.keysPressed[1]){
            GameBoard.player1.moveXY(-1,1);
        }else if(Board.keysPressed[1] && Board.keysPressed[2]){
            GameBoard.player1.moveXY(-1,-1);
        }else if(Board.keysPressed[2] && Board.keysPressed[3]){
            GameBoard.player1.moveXY(1,-1);
        }else if(Board.keysPressed[3] && Board.keysPressed[0]){
            GameBoard.player1.moveXY(1,1);
        }else if(Board.keysPressed[0]){
            GameBoard.player1.moveY(-1);
        }else if(Board.keysPressed[1]){
            GameBoard.player1.moveX(-1);
        }else if(Board.keysPressed[2]){
            GameBoard.player1.moveY(1);
        }else if(Board.keysPressed[3]){
            GameBoard.player1.moveX(1);
        }
        
        // Controls for player 2
        if(Board.keysPressed[4] && Board.keysPressed[5]){
            GameBoard.player2.moveXY(-1,1);
        }else if(Board.keysPressed[5] && Board.keysPressed[6]){
            GameBoard.player2.moveXY(-1,-1);
        }else if(Board.keysPressed[6] && Board.keysPressed[7]){
            GameBoard.player2.moveXY(1,-1);
        }else if(Board.keysPressed[7] && Board.keysPressed[4]){
            GameBoard.player2.moveXY(1,1);
        }else if(Board.keysPressed[4]){
            GameBoard.player2.moveY(-1);
        }else if(Board.keysPressed[5]){
            GameBoard.player2.moveX(-1);
        }else if(Board.keysPressed[6]){
            GameBoard.player2.moveY(1);
        }else if(Board.keysPressed[7]){
            GameBoard.player2.moveX(1);
        }
    }
    
    public static double getFPS(){
        return(FPS);
    }
    
}
