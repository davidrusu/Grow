/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package grow;
import java.awt.Dimension;
import javax.swing.JFrame;
import java.awt.Toolkit;
/**
 *
 * @author davidrusu
 */
public class Grow extends JFrame{
    public static Board board;
    private int width = 800, height = 600;
    public Grow(){
        //Toolkit tk = Toolkit.getDefaultToolkit();
        //setSize(tk.getScreenSize());
        setSize(width, height);
        JFrame.setDefaultLookAndFeelDecorated(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //setFocusable(true);
        board = new Board(this);
        board.setSize(this.getSize());
        this.setContentPane(board);
        this.setVisible(true);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        Grow game = new Grow();
        Thread gameCore = new Thread(new GameCore());
        gameCore.start();
    }
}
