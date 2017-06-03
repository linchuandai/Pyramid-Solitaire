import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class PyramidSolitaire extends Applet implements ActionListener, MouseListener, MouseMotionListener
{
    Graphics g;   // declares a graphics canvas for drawing
    PyramidSolitaireClass pyramidsolitaire;
    int picked = 1;
    int card1;
    int card2;
    boolean flipped = false;

    Frame window;
    boolean rePlay = false;

    public void init ()
    {
	pyramidsolitaire = new PyramidSolitaireClass (3);
	g = getGraphics (); // gets canvas created by browser-replaces new stmt
	this.setSize (new Dimension (1500, 1300));
	// Place the body of the initialization method here

	Object f = getParent ();
	while (!(f instanceof Frame))
	    f = ((Component) f).getParent ();

	window = (Frame) f; //new Frame("MyFrame");

	window.resize (500, 500);
	window.show ();

	MenuBar mb = new MenuBar ();

	Menu game = new Menu ("Game");
	game.add (new MenuItem ("Open"));
	game.add (new MenuItem ("Redeal"));
	game.add (new MenuItem ("Restart"));
	game.add (new MenuItem ("Exit"));

	Menu level = new Menu ("Level");
	level.add (new MenuItem ("Easy"));
	level.add (new MenuItem ("Medium"));
	level.add (new MenuItem ("Hard"));
	level.add (new MenuItem ("Extreme"));

	Menu instructions = (new Menu ("Instructions"));
	instructions.add (new MenuItem ("Instructions"));

	Menu background = (new Menu ("Background"));
	background.add (new MenuItem ("Table"));
	background.add (new MenuItem ("Grass Table"));
	background.add (new MenuItem ("Poker Table"));
	background.add (new MenuItem ("Wood Table"));

	Menu defeat = (new Menu ("Defeat"));
	defeat.add (new MenuItem ("Admit Defeat"));
	defeat.add (new MenuItem ("Check Defeat"));

	mb.add (game);
	mb.add (level);
	mb.add (instructions);
	mb.add (background);
	mb.add (defeat);

	window.setMenuBar (mb);

	addMouseListener (this);
	addMouseMotionListener (this);

    } // init method


    public void paint (Graphics g)
    {
	// System.out.println (pyramidsolitaire.getCount ());
	pyramidsolitaire.drawPyramid (g);
	pyramidsolitaire.drawDeck (g);
    } // paint method


    public void actionPerformed (ActionEvent e)
    {
	Object objSource = e.getSource ();
	repaint ();

    } //actionPermored method


    public boolean action (Event e, Object o)
    {

	if (e.target instanceof MenuItem)
	{
	    System.out.println ("Hello you pressed a menu item");
	    return true;

	}

	repaint ();
	return true;
    }


    public void mousePressed (MouseEvent e)
    {
	//System.out.println (2);

	if (pyramidsolitaire.isPickable (pyramidsolitaire.whichCard (e.getX (), e.getY ())))
	{
	    //System.out.println (7);
	    if (picked == 1)
	    {
		card1 = pyramidsolitaire.whichCard (e.getX (), e.getY ());
		pyramidsolitaire.chooseCard (card1);
		pyramidsolitaire.reDrawCard (g, card1);

		// System.out.println (card1);
		if (pyramidsolitaire.isPair (card1))
		{

		    pyramidsolitaire.createPair (card1, g);
		    flipped = false;
		    //pyramidsolitaire.delay (500);
		    picked = 1;
		    card1 = 0;
		    repaint ();

		    if (pyramidsolitaire.isWin () == true)
		    {
			System.out.println ("You Win");
		    }
		}
		else if (card1 == 100)
		{
		    flipped = true;
		    picked = 2;
		}
		else
		{
		    picked = 2;
		}
	    }
	    else if (picked == 2)
	    {
		picked = 1;
		//System.out.println (card1);

		card2 = pyramidsolitaire.whichCard (e.getX (), e.getY ());
		//System.out.println (card2);
		pyramidsolitaire.chooseCard (pyramidsolitaire.whichCard (e.getX (), e.getY ()));
		pyramidsolitaire.reDrawCard (g, pyramidsolitaire.whichCard (e.getX (), e.getY ()));

		if (pyramidsolitaire.isPair (card1, card2) == true)
		{
		    pyramidsolitaire.createPair (card1, card2, g);
		    flipped = false;
		    repaint ();

		    if (pyramidsolitaire.isWin () == true)
		    {
			System.out.println ("You Win");
		    }
		}
		else
		{
		    pyramidsolitaire.unchooseCard (card1);
		    pyramidsolitaire.reDrawCard (g, card1);
		    //pyramidsolitaire.delay (50);
		    pyramidsolitaire.unchooseCard (card2);
		    pyramidsolitaire.reDrawCard (g, card2);
		    flipped = false;
		}

		card1 = 0;
		card2 = 0;
	    }
	}


	else if (pyramidsolitaire.flipCard (e.getX (), e.getY ()) && flipped == false)
	{
	    pyramidsolitaire.nextCard ();
	    pyramidsolitaire.drawDeck (g);
	}
    }


    public void mouseReleased (MouseEvent e)
    {

    }


    public void mouseDragged (MouseEvent e)
    {

    }


    public void mouseMoved (MouseEvent e)
    {
    }


    public void mouseClicked (MouseEvent e)
    {
    }


    public void mouseEntered (MouseEvent e)
    {
    }


    public void mouseExited (MouseEvent e)
    {
    }
}


