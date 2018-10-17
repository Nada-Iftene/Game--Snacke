package jeuY;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Scanner;
import java.*;

import javax.swing.JPanel;

public class Serpent extends JPanel{
	/*************************oui*/
	public int longeur;
	Graphics g;
	public boolean jouer = true;
	public int nbDePommes = 3;
	Scanner sc;
	ArrayList<Anneau> corps = new ArrayList<Anneau>();
	ArrayList<Pomme> pommes = new ArrayList<Pomme>();
	public int direction = 4;
	
	public Serpent(Graphics g,int longeur){
		this.g = g;
		this.longeur = longeur;
		sc = new Scanner(System.in);
	}
	/*************************oui*/
	public void jouer(){
		creationSerpent();
		while(jouer == true){
			g.setColor(Color.LIGHT_GRAY);
			g.fillRect(0, 0, Fenetre.largeurfen, Fenetre.houteurfen);
			
			creationPommes();
			dessinePommes();
			dessineSerpent();
			
			sleep(200);
			move();
			checkCollision();
		}
	}
	/*************************oui*/
	
	public void sleep(int time){
		try{
			Thread.sleep(time);
			
		}catch(InterruptedException ex){
			Thread.currentThread().interrupt();
		}
	}
	
	/*************************oui*/
	public void creationPommes(){
		int randX , randY;
		boolean creation = true;
		while(pommes.size() < nbDePommes){
			creation = true;
			int widthfen = ((Fenetre.largeurfen-20)/10)-2;
			int heithfen = ((Fenetre.houteurfen-20)/10)-2;
			randX = (int) ((Math.random()*(widthfen))+3);
			randY = (int) ((Math.random()*(heithfen))+3);
			
			randX = (randX*10);
			randY = (randY*10);
			
			for(int i=0; i< corps.size();i++){
				Anneau siExiste = corps.get(i);
				if(randX == siExiste.posX && randY == siExiste.posY){
					creation = false;
					
				}
			}
			if (creation == true) {
				pommes.add(new Pomme(randX,randY,Color.GREEN));
				
			}
			
		}
	}
	/*************************oui*/
	public void creationSerpent(){
		for (int j = 0; j < this.longeur; j++) {
			int hauteur;
			hauteur = ((int) Fenetre.houteurfen/2) / 10;
			hauteur *= 10;
			if (j == 0) {
				corps.add(new Anneau(Fenetre.largeurfen/2+((j)*10),hauteur,Color.RED));
				
				
			}else if (j >0) {
				corps.add(new Anneau(Fenetre.largeurfen/2+((j)*10),hauteur,Color.BLACK));
				
				
			}
		}
		
	}
	/*************************oui*/
	public void dessinePommes(){
		for (int x = 0; x < pommes.size(); x++) {
			Pomme p =pommes.get(x);
			g.setColor(p.couleur);
			g.fillOval(p.posX, p.posY, 10, 10);
			
		}
	}
	/*************************oui*/
	public void dessineSerpent(){
		for (int i = 0; i < corps.size(); i++) {
			Anneau a;
			a = corps.get(i);
			g.setColor(a.couleur);
			g.fillOval(a.posX, a.posY, 10, 10);
			
			showScore();
		}
	}
	
	public void showScore(){
		g.setFont(new Font("TimesRoman",Font.PLAIN,15));
		g.drawString(Integer.toString(corps.size()),10,Fenetre.houteurfen-10);
		
	}
	/*************************oui*/
	public void checkCollision(){
		for (int i = 0; i < pommes.size(); i++) {
			Pomme checkPomme = pommes.get(i);
			Anneau checkSerpent = corps.get(0);
			Anneau lastPosition = corps.get(corps.size()-1);
			if (checkPomme.posX == checkSerpent.posX && checkPomme.posY == checkSerpent.posY) {
				pommes.remove(i);
				corps.add(new Anneau(200+((lastPosition.posX)+10),0,Color.BLACK));
				
			}
			
		}
		for (int i = 1; i < corps.size(); i++) {
			Anneau corpsSerpent = corps.get(i);
			Anneau teteSerpent = corps.get(0);
			
			if(teteSerpent.posX == corpsSerpent.posX && teteSerpent.posY == corpsSerpent.posY)
				jouer = false;
				
			
			 
			
		}
		Anneau teteSerpent = corps.get(0);
		
		if(teteSerpent.posX < 10)
			jouer = false;
		if(teteSerpent.posX > (Fenetre.largeurfen-20))
			jouer = false;
		if(teteSerpent.posY < 30)
			jouer = false;
		if(teteSerpent.posY > (Fenetre.houteurfen-20))
			jouer = false;
	}
	/*************************oui*/
	public void move(){
		int px,py;
		for (int i = corps.size()-1; i > 0; i--) {
			Anneau a;
			a = corps.get(i-1);
			px = a.posX;
			py = a.posY;
			a = corps.get(i);
			a.posX = px;
			a.posY = py;
			
		}
		Anneau b1 = corps.get(0);
		if (direction == 1) {
			b1.posY -=10;
			
		}
		if (direction == 2) {
			b1.posX +=10;
			
		}
		if (direction == 3) {
			b1.posY +=10;
			
		}
		if (direction == 4) {
			b1.posX -=10;
			
		}
	}

}
