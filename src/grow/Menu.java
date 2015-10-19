/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package grow;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
/**
 *
 * @author davidrusu
 */
public class Menu extends JPanel implements ActionListener, ChangeListener{
    JButton reset;
    JSlider time;
    JLabel FPS;
    public double fps;
    public Menu(){
        FlowLayout layout = new FlowLayout();
        this.setSize(800, 50);
        this.setLayout(layout);
        this.setBackground(new Color(0, 0, 0, 0));
        this.setVisible(true);
        
        reset = new JButton("reset");
        reset.addActionListener(this);
        reset.setActionCommand("reset");
        
        time = new JSlider(0, 100, 10);
        time.addChangeListener(this);
        
        FPS = new JLabel();
        FPS.setForeground(Color.WHITE);
        
        this.add(FPS);
        this.add(reset);
        this.add(time);
    }
    
    public void updateFPS(){
        FPS.setText(fps + " FPS");
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String command = ae.getActionCommand();
        if(command.equals("reset")){
            System.out.println("RESET------------");
            Grow.board.reset();
            Grow.board.requestFocus();
        }
    }

    @Override
    public void stateChanged(ChangeEvent ce) {
        int value = time.getValue();
        Item.timeSpeed = value/10.0;
        Grow.board.requestFocus();
    }
    
    //public void paint(Graphics g){
    //    fps = updateFPS();
    //    FPS.repaint();
    //    reset.repaint();
    //    time.repaint();
    //}
    
}
