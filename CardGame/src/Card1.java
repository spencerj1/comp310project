import objectdraw.*;

import java.*;
import java.awt.*;
import java.applet.*;
import java.util.StringTokenizer;
import java.awt.image.*;

public class Card1 extends ActiveObject{
	private Text uppervalue;
	private Text lowervalue;
	public FilledRect outline;
	public FilledRect innerboarder;
	private Location SymbolLoc;
	private FilledOval Heart1, Heart2;
	private Line HeartBottom;
	private Line DiamondTop, DiamondBottom;
	private FilledOval Spade1, Spade2;
	private FilledRect SpadeStem;
	private Line SpadeTop, SpadeBottom;
	private FilledOval Club1, Club2, Club3;
	private FilledRect ClubStem;
	private Line ClubBottom;
	
	public Card1 (Location point, DrawingCanvas aCanvas, int cardnum, int cardsuite ){
		//Card Numbers: 1 = Ace
		//				2 - 10 = Numbered Cards
		//				11 = Jack
		//				12 = Queen
		//				13 = King
		outline = new FilledRect (point, 115, 160, aCanvas);
		outline.setColor(new Color(0, 0, 102));
		
		innerboarder = new FilledRect(point.getX()+5, point.getY()+5, 105, 150, aCanvas);
		innerboarder.setColor(Color.white);
		
		SymbolLoc = new Location(point.getX() + 57, point.getY() + 80);
		
		//Set the value of the Card:
		
		if(cardnum == 1){
			uppervalue = new Text ("A", point.getX() + 10, point.getY() + 5, aCanvas);
			uppervalue.setFontSize(20);
			uppervalue.setBold();
			lowervalue = new Text ("A", point.getX() + 93, point.getY() + 130, aCanvas);
			lowervalue.setFontSize(20);
			lowervalue.setBold();
			
		}
		if(cardnum == 2){
			uppervalue = new Text ("2", point.getX() + 10, point.getY() + 5, aCanvas);
			uppervalue.setFontSize(20);
			uppervalue.setBold();
			lowervalue = new Text ("2", point.getX() + 93, point.getY() + 130, aCanvas);
			lowervalue.setFontSize(20);
			lowervalue.setBold();
			
		}
		if(cardnum == 3){
			uppervalue = new Text ("3", point.getX() + 10, point.getY() + 5, aCanvas);
			uppervalue.setFontSize(20);
			uppervalue.setBold();
			lowervalue = new Text ("3", point.getX() + 93, point.getY() + 130, aCanvas);
			lowervalue.setFontSize(20);
			lowervalue.setBold();
			
		}
		if(cardnum == 4){
			uppervalue = new Text ("4", point.getX() + 10, point.getY() + 5, aCanvas);
			uppervalue.setFontSize(20);
			uppervalue.setBold();
			lowervalue = new Text ("4", point.getX() + 93, point.getY() + 130, aCanvas);
			lowervalue.setFontSize(20);
			lowervalue.setBold();
			
		}
		if(cardnum == 5){
			uppervalue = new Text ("5", point.getX() + 10, point.getY() + 5, aCanvas);
			uppervalue.setFontSize(20);
			uppervalue.setBold();
			lowervalue = new Text ("5", point.getX() + 93, point.getY() + 130, aCanvas);
			lowervalue.setFontSize(20);
			lowervalue.setBold();
			
		}
		if(cardnum == 6){
			uppervalue = new Text ("6", point.getX() + 10, point.getY() + 5, aCanvas);
			uppervalue.setFontSize(20);
			uppervalue.setBold();
			lowervalue = new Text ("6", point.getX() + 93, point.getY() + 130, aCanvas);
			lowervalue.setFontSize(20);
			lowervalue.setBold();
			
		}
		if(cardnum == 7){
			uppervalue = new Text ("7", point.getX() + 10, point.getY() + 5, aCanvas);
			uppervalue.setFontSize(20);
			uppervalue.setBold();
			lowervalue = new Text ("7", point.getX() + 93, point.getY() + 130, aCanvas);
			lowervalue.setFontSize(20);
			lowervalue.setBold();
			
		}
		if(cardnum == 8){
			uppervalue = new Text ("8", point.getX() + 10, point.getY() + 5, aCanvas);
			uppervalue.setFontSize(20);
			uppervalue.setBold();
			lowervalue = new Text ("8", point.getX() + 93, point.getY() + 130, aCanvas);
			lowervalue.setFontSize(20);
			lowervalue.setBold();
			
		}
		if(cardnum == 9){
			uppervalue = new Text ("9", point.getX() + 10, point.getY() + 5, aCanvas);
			uppervalue.setFontSize(20);
			uppervalue.setBold();
			lowervalue = new Text ("9", point.getX() + 93, point.getY() + 130, aCanvas);
			lowervalue.setFontSize(20);
			lowervalue.setBold();
			
		}
		if(cardnum == 10){
			uppervalue = new Text ("10", point.getX() + 10, point.getY() + 5, aCanvas);
			uppervalue.setFontSize(20);
			uppervalue.setBold();
			lowervalue = new Text ("10", point.getX() + 84, point.getY() + 130, aCanvas);
			lowervalue.setFontSize(20);
			lowervalue.setBold();
			
		}
		if(cardnum == 11){
			uppervalue = new Text ("J", point.getX() + 10, point.getY() + 5, aCanvas);
			uppervalue.setFontSize(20);
			uppervalue.setBold();
			lowervalue = new Text ("J", point.getX() + 93, point.getY() + 130, aCanvas);
			lowervalue.setFontSize(20);
			lowervalue.setBold();
			
		}
		if(cardnum == 12){
			uppervalue = new Text ("Q", point.getX() + 10, point.getY() + 5, aCanvas);
			uppervalue.setFontSize(20);
			uppervalue.setBold();
			lowervalue = new Text ("Q", point.getX() + 93, point.getY() + 130, aCanvas);
			lowervalue.setFontSize(20);
			lowervalue.setBold();
			
		}
		if(cardnum == 13){
			uppervalue = new Text ("K", point.getX() + 10, point.getY() + 5, aCanvas);
			uppervalue.setFontSize(20);
			uppervalue.setBold();
			lowervalue = new Text ("K", point.getX() + 93, point.getY() + 130, aCanvas);
			lowervalue.setFontSize(20);
			lowervalue.setBold();
			
		}
		
		//Set the Suite
		
		//Hearts
		if(cardsuite == 1){
			uppervalue.setColor(Color.red);
			lowervalue.setColor(Color.red);
			Heart1 = new FilledOval(SymbolLoc.getX() - 20, SymbolLoc.getY() - 20, 20, 20, aCanvas);
			Heart1.setColor(Color.red);
			Heart2 = new FilledOval(SymbolLoc.getX() - 2, SymbolLoc.getY() - 20, 20, 20, aCanvas);
			Heart2.setColor(Color.red);
			
			for (int i = 0; i < 20; i++){
				HeartBottom = new Line(SymbolLoc.getX() - 20 + i, SymbolLoc.getY() - 8 + i,
						SymbolLoc.getX() + 18 - i, SymbolLoc.getY() - 8 + i, aCanvas);
				HeartBottom.setColor(Color.red);
			}
			
		}
		//Diamonds
		if(cardsuite == 2){
			uppervalue.setColor(Color.red);
			lowervalue.setColor(Color.red);
			for (int i = 0; i < 20; i++){
				DiamondBottom = new Line(SymbolLoc.getX() - 20 + i, SymbolLoc.getY() - 4 + i,
						SymbolLoc.getX() + 18 - i, SymbolLoc.getY() - 4 + i, aCanvas);
				DiamondBottom.setColor(Color.red);
			}
			for (int i = 0; i < 20; i++){
				DiamondTop = new Line(SymbolLoc.getX() - 20 + i, SymbolLoc.getY() - 4 - i,
						SymbolLoc.getX() + 18 - i, SymbolLoc.getY() - 4 - i, aCanvas);
				DiamondTop.setColor(Color.red);
			}
			}
		//Spades	
		if(cardsuite == 3){
			Spade1 = new FilledOval(SymbolLoc.getX() - 20, SymbolLoc.getY() - 13, 20, 20, aCanvas);
			Spade2 = new FilledOval(SymbolLoc.getX() - 2, SymbolLoc.getY() - 13, 20, 20, aCanvas);
			for (int i = 0; i < 20; i++){
				SpadeTop = new Line(SymbolLoc.getX() - 20 + i, SymbolLoc.getY() - 5 - i,
						SymbolLoc.getX() + 18 - i, SymbolLoc.getY() - 5 - i, aCanvas);
			}
			SpadeStem = new FilledRect(SymbolLoc.getX() - 3, SymbolLoc.getY() + 2, 5, 10, aCanvas);
			for (int i = 0; i < 5; i++){
				SpadeBottom = new Line(SymbolLoc.getX() - 1 + i, SymbolLoc.getY() + 9 + i,
						SymbolLoc.getX() - 1 - i, SymbolLoc.getY() + 9 + i, aCanvas);
			}
		}
		//Clubs
		if(cardsuite == 4){
			Club1 = new FilledOval(SymbolLoc.getX() - 20, SymbolLoc.getY() - 13, 20, 20, aCanvas);
			Club2 = new FilledOval(SymbolLoc.getX() - 2, SymbolLoc.getY() - 13, 20, 20, aCanvas);
			Club3 = new FilledOval(SymbolLoc.getX() - 9, SymbolLoc.getY() - 26, 19, 20, aCanvas);
			ClubStem = new FilledRect(SymbolLoc.getX() - 3, SymbolLoc.getY() + 2, 5, 10, aCanvas);
			for (int i = 0; i < 5; i++){
				ClubBottom = new Line(SymbolLoc.getX() - 1 + i, SymbolLoc.getY() + 9 + i,
						SymbolLoc.getX() - 1 - i, SymbolLoc.getY() + 9 + i, aCanvas);
			}
		}
		
	}
	
}
