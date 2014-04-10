package com.badlogic.PokerGame;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import java.util.List;

	
public class PokerGame extends ApplicationAdapter {
	Stage stage;
//	SpriteBatch batch;
	Texture img;
	long time;
	ImageButton img1, img2, img3, img4, img5, imgDeck;
	Card card1, card2, card3, card4, card5;
	
	@Override
	public void create () {
		stage = new Stage();
		
		// The stage will respond to input from Gdx (keyboard, mouse, touch, game controller)
		Gdx.input.setInputProcessor(stage);
		List<Card> deck = Card.deck();
		// Draw the cards (should later be put in a buttonListener method)
		drawCards(deck);
		
		// Create a button with two images
		img1 = new ImageButton(
				new SpriteDrawable(new Sprite(new Texture(card1.getImage()))));
				//new SpriteDrawable(new Sprite(new Texture("3_of_clubs.png"))));
		
		// Layout that button
		img1.setSize(100, 200);
		img1.setPosition(20, 20);
		
		// Handle events (aka controller in MVC)
		img1.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int mouseButton) {
				System.out.println("Hi, down there!");
				// Demonstrate that we can move the button around, too
				img1.setPosition(img1.getX()+5, img1.getY()+5);
				return true;
			}
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				System.out.println("Hi, up there!");
			}
		}
		);
		
		// The button is on the stage
		stage.addActor(img1);
//		batch = new SpriteBatch();
//		img = new Texture(new Pixmap(Gdx.files.internal("2_of_clubs.png")));
//		try {
//			new FreeTypeFontGenerator(Gdx.files.internal("test.fnt"));
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
//		Bullet.init();
		time = System.currentTimeMillis();
	}
		
	@Override
	public void render () {
		Gdx.gl.glClearColor(0, (float)0.5, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
//		batch.begin();
//		batch.draw(img, 20, 20, 100, 200);
//		batch.end();
		stage.draw();
		if (System.currentTimeMillis() - time < 3000) { // This method enlarges a card
			img1.setSize(img1.getWidth()+1, img1.getHeight()+1);
		}
	}
	
	public void drawCards(List<Card> deck) {
		if (card1 != null) {
			card1 = deck.get(0);
			deck.remove(0);
		}
	}
}


/*public class PokerGame extends WindowController implements ActionListener{
	private FilledRect background;
	private img1 img1, card2, card3, card4, card5;
	private int img1value, card2value, card3value, card4value, card5value;
	private int img1suite, card2suite, card3suite, card4suite, card5suite;
	private RandomIntGenerator value;
	private RandomIntGenerator suite;
	private Location img1loc, card2loc, card3loc, card4loc, card5loc;
	private JButton bet1, bet10, bet25, bet100, dealbutton, pickcards;

	private Boolean clicked1 = false;
	private Boolean clicked2 = false;
	private Boolean clicked3 = false;
	private Boolean clicked4 = false;
	private Boolean clicked5 = false;
	
	private Boolean traded = false;
	private LinkedList cards = new LinkedList(); // new
	
	
	public void begin(){
		//Set Canvas and Background
		
		setSize(620, 500);
		background = new FilledRect(0,0, 620, 500, canvas);
		background.setColor( new Color(0, 153, 0));
		
		JPanel northPanel = new JPanel();
		JPanel southPanel = new JPanel();
		
		JLabel bets = new JLabel("Amount of Bet");
		
		bet1 = new JButton(" $1.00 ");
		bet10 = new JButton(" $10.00 ");
		bet25 = new JButton(" $25.00 ");
		bet100 = new JButton(" $100.00 ");
		dealbutton = new JButton(" Deal Cards ");
		pickcards = new JButton(" Trade in Chosen Cards ");
		
		bet1.addActionListener(this);
		bet10.addActionListener(this);
		bet25.addActionListener(this);
		bet100.addActionListener(this);
		dealbutton.addActionListener(this);
		pickcards.addActionListener(this);
		
		southPanel.add(bets);
		southPanel.add(bet1);
		southPanel.add(bet10);
		southPanel.add(bet25);
		southPanel.add(bet100);
		northPanel.add(dealbutton);
		northPanel.add(pickcards);
		
		
		Container contentPane = getContentPane();
		contentPane.add( northPanel, BorderLayout.NORTH );
		contentPane.add( southPanel, BorderLayout.SOUTH );
		contentPane.validate();
		
		//Card Locations
		img1loc = new Location(10, 180);
		card2loc = new Location(130, 180);
		card3loc = new Location(250, 180);
		card4loc = new Location(370, 180);
		card5loc = new Location(490, 180);
		
		//Value and Suite Randomizer
		value = new RandomIntGenerator(1, 13);
		suite = new RandomIntGenerator(1, 4);
		
		
		
	}
	
	public void actionPerformed(ActionEvent arg0) {
		
		if (arg0.getSource() == dealbutton){
		img1value = value.nextValue();
		card2value = value.nextValue();
		card3value = value.nextValue();
		card4value = value.nextValue();
		card5value = value.nextValue();
		
		img1suite = suite.nextValue();
		card2suite = suite.nextValue();
		card3suite = suite.nextValue();
		card4suite = suite.nextValue();
		card5suite = suite.nextValue();
		
		img1 = new img1 (img1loc, canvas, img1value, img1suite);
		card2 = new img1 (card2loc, canvas, card2value, card2suite);
		card3 = new img1 (card3loc, canvas, card3value, card3suite);
		card4 = new img1 (card4loc, canvas, card4value, card4suite);
		card5 = new img1 (card5loc, canvas, card5value, card5suite);
		
		traded = false;
		
		while (CheckForDupes(img1value, img1suite, 1) == true){
			img1value = value.nextValue();
			img1suite = suite.nextValue();
			img1 = new img1 (img1loc, canvas, img1value, img1suite);
			CheckForDupes(img1value, img1suite, 1);
		}
		while (CheckForDupes(card2value, card2suite, 2) == true){
			card2value = value.nextValue();
			card2suite = suite.nextValue();
			card2 = new img1 (card2loc, canvas, card2value, card2suite);
			CheckForDupes(card2value, card2suite, 2);
		}
		while (CheckForDupes(card3value, card3suite, 3) == true){
			card3value = value.nextValue();
			card3suite = suite.nextValue();
			card3 = new img1 (card3loc, canvas, card3value, card3suite);
			CheckForDupes(card3value, card3suite, 3);
		}
		while (CheckForDupes(card4value, card4suite, 4) == true){
			card4value = value.nextValue();
			card4suite = suite.nextValue();
			card4 = new img1 (card4loc, canvas, card4value, card4suite);
			CheckForDupes(card4value, card4suite, 4);
		}
		}
		
		if (arg0.getSource() == pickcards){
			if (traded == false){
			if (clicked1 == true){
				img1value = value.nextValue();
				img1suite = suite.nextValue();
				img1 = new img1 (img1loc, canvas, img1value, img1suite);
			}
			if (clicked2 == true){
				card2value = value.nextValue();
				card2suite = suite.nextValue();
				card2 = new img1 (card2loc, canvas, card2value, card2suite);
			}
			if (clicked3 == true){
				card3value = value.nextValue();
				card3suite = suite.nextValue();
				card3 = new img1 (card3loc, canvas, card3value, card3suite);
			}
			if (clicked4 == true){
				card4value = value.nextValue();
				card4suite = suite.nextValue();
				card4 = new img1 (card4loc, canvas, card4value, card4suite);
			}
			if (clicked5 == true){
				card5value = value.nextValue();
				card5suite = suite.nextValue();
				card5 = new img1 (card5loc, canvas, card5value, card5suite);
			}
			traded = true;
			clicked1 = false;
			clicked2 = false;
			clicked3 = false;
			clicked4 = false;
			clicked5 = false;
			}
			
		}
		
	}
	
	public void onMousePress (Location point){
		if(traded == false){
		if (img1.outline.contains(point)){
			if(clicked1 == false){
			img1.innerboarder.setColor(Color.CYAN);
			clicked1 = true;
			}else{
				img1.innerboarder.setColor(Color.WHITE);
				clicked1 = false;
			}
		}
		if (card2.outline.contains(point)){
			if(clicked2 == false){
			card2.innerboarder.setColor(Color.CYAN);
			clicked2 = true;
			}else {
				card2.innerboarder.setColor(Color.WHITE);
				clicked2 = false;
			}
		}
		if (card3.outline.contains(point)){
			if(clicked3 == false){
				card3.innerboarder.setColor(Color.CYAN);
				clicked3 = true;
				}else {
					card3.innerboarder.setColor(Color.WHITE);
					clicked3 = false;
				}
		}
		if (card4.outline.contains(point)){
			if(clicked4 == false){
				card4.innerboarder.setColor(Color.CYAN);
				clicked4 = true;
				}else {
					card4.innerboarder.setColor(Color.WHITE);
					clicked4 = false;
				}
		}
		if (card5.outline.contains(point)){
			if(clicked5 == false){
				card5.innerboarder.setColor(Color.CYAN);
				clicked5 = true;
				}else {
					card5.innerboarder.setColor(Color.WHITE);
					clicked5 = false;
				}
		}
		}
	}
	
	public Boolean CheckForDupes(int CValue, int CSuite, int cardnum){
		int[] v = new int[4];
		int[] s = new int[4];
		Boolean vb = false;
		Boolean sb = false;
		if (cardnum == 1){
			v[0] = card5value;
			v[1] = card2value;
			v[2] = card3value;
			v[3] = card4value;
			
			s[0] = card4suite;
			s[1] = card2suite;
			s[2] = card3suite;
			s[3] = card5suite;
		}
		if (cardnum == 2){
			v[0] = img1value;
			v[1] = card5value;
			v[2] = card3value;
			v[3] = card4value;
			
			s[0] = img1suite;
			s[1] = card4suite;
			s[2] = card3suite;
			s[3] = card5suite;
			}
		if (cardnum == 3){
			v[0] = card5value;
			v[1] = card2value;
			v[2] = img1value;
			v[3] = card4value;
			
			s[0] = img1suite;
			s[1] = card2suite;
			s[2] = card4suite;
			s[3] = card5suite;
			}
		if (cardnum == 4){
			v[0] = card5value;
			v[1] = card2value;
			v[2] = card3value;
			v[3] = img1value;
			
			s[0] = img1suite;
			s[1] = card2suite;
			s[2] = card3suite;
			s[3] = card5suite;
			}
		if (cardnum == 5){
			v[0] = img1value;
			v[1] = card2value;
			v[2] = card3value;
			v[3] = card4value;
			
			s[0] = img1suite;
			s[1] = card2suite;
			s[2] = card3suite;
			s[3] = card4suite;
			}
		
		for (int i = 0; i < 4; i++){
			if (CValue == v[i]){
				vb = true;
			}
		}
		for (int i = 0; i < 4; i++){
			if (CSuite == s[i]){
				sb = true;
			}
		}
		if (vb == true & sb == true){
			return true;
		}else{
			return false;
		}
	}

}*/
