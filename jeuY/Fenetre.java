package jeuY;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Fenetre extends JFrame  implements KeyListener{
	
	JPanel container = new JPanel();
	public static int largeurfen = 200;
	public static int houteurfen = 150;
	Serpent anaconda;
	
	JLabel gameOver = new JLabel();
	
	public Fenetre(){
		this.setTitle("Snake");
		this.setSize(largeurfen, houteurfen);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		container.setBackground(Color.LIGHT_GRAY);
		
		gameOver.setBounds((largeurfen/2)-40, 0, 100, 20);
		this.addKeyListener(this);
		
		this.setContentPane(container);
		this.setVisible(true);
		
		anaconda = new Serpent(this.getGraphics(),4);
		
	}
	/*************************oui*/
	
	public void startGame(){
		anaconda.jouer();
		gameOver();
	}
	
	public void gameOver(){
		gameOver.setText("Game Over !");
		container.add(gameOver);
		container.repaint();
	}/*************************oui*/
	@Override
	public void keyPressed(KeyEvent arg0){
		if(arg0.getKeyCode() == KeyEvent.VK_UP && anaconda.direction != 3)
			anaconda.direction =1;
		if(arg0.getKeyCode() == KeyEvent.VK_RIGHT && anaconda.direction != 4)
			anaconda.direction =2;
		if(arg0.getKeyCode() == KeyEvent.VK_DOWN && anaconda.direction != 1)
			anaconda.direction =3;
		if(arg0.getKeyCode() == KeyEvent.VK_LEFT && anaconda.direction != 2)
			anaconda.direction =4;
	}
	@Override
	public void keyReleased(KeyEvent arg0){
		
	}
	@Override
	public void keyTyped(KeyEvent arg0){
		
	}
	
	
	

}
