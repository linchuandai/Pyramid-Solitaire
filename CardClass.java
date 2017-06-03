import hsa.Console;
import java.awt.*;

public class CardClass extends ShapeClass
{

    private int Suit;
    private int CardValue;
    private boolean isFaceUp;
    private boolean isPicked;

    public CardClass ()
    {
	Suit = 1;
	CardValue = 1;
	isFaceUp = true;
	isPicked = false;
    }


    public CardClass (int SuitValue, int CardVal)
    {
	if ((SuitValue >= 1) && (SuitValue <= 4))
	{
	    Suit = SuitValue;
	}
	else
	{
	    Suit = 1;
	}

	if ((CardVal >= 1) && (CardVal <= 13))
	{
	    CardValue = CardVal;
	}
	else
	{
	    CardValue = 1;
	}
	isFaceUp = true;
	isPicked = false;
    }


    public void setCardValue (int CardVal)
    {
	CardValue = CardVal;
    }


    public int getCardValue ()
    {
	return CardValue;
    }


    public void setCardSuit (int SuitValue)
    {
	Suit = SuitValue;
    }


    public int getCardSuit (int SuitValue)
    {
	return Suit;
    }


    public void setFaceUp (boolean FaceUp)
    {
	isFaceUp = FaceUp;
    }


    public boolean isCardFaceUp ()
    {
	return isFaceUp;
    }


    public void setPicked (boolean picked)
    {
	isPicked = picked;
    }


    public boolean isPicked ()
    {
	return isPicked;
    }


    private void drawSuit (Console c)
    {
	if (Suit == 1)
	{
	    DiamondClass diamond = new DiamondClass ();
	    diamond.setCentre (iCentreX, iCentreY);
	    diamond.setHeight (iHeight / 4);
	    diamond.setColor (Color.red);
	    diamond.draw (c);
	}
	else if (Suit == 2)
	{
	    ClubClass club = new ClubClass ();
	    club.setCentre (iCentreX, iCentreY);
	    club.setHeight (iHeight / 4);
	    club.setColor (Color.black);
	    club.draw (c);
	}
	else if (Suit == 3)
	{
	    HeartClass heart = new HeartClass ();
	    heart.setCentre (iCentreX, iCentreY);
	    heart.setHeight (iHeight / 4);
	    heart.setColor (Color.red);
	    heart.draw (c);
	}
	else if (Suit == 4)
	{
	    SpadeClass spade = new SpadeClass ();
	    spade.setCentre (iCentreX, iCentreY);
	    spade.setHeight (iHeight / 4);
	    spade.setColor (Color.black);
	    spade.draw (c);
	}
    }


    public void draw (Console c)
    {
	if (isFaceUp == true)
	{
	    c.setColor (Color.white);
	    c.fillRect (iCentreX - iWidth / 2, iCentreY - iHeight / 2, iWidth, iHeight);
	    if (isPicked == true)
	    {
		c.setColor (Color.pink);
		c.drawRect (iCentreX - iWidth / 2, iCentreY - iHeight / 2, iWidth, iHeight);
	    }
	    else
	    {
		c.setColor (iColor);
		c.drawRect (iCentreX - iWidth / 2, iCentreY - iHeight / 2, iWidth, iHeight);
	    }
	    drawSuit (c);

	    String CardVal = "";
	    if (CardValue <= 9 && CardValue >= 2)
	    {
		CardVal = Integer.toString (CardValue);
	    }
	    else if (CardValue == 1)
	    {
		CardVal = "A";
	    }
	    else if (CardValue == 10)
	    {
		CardVal = "T";
	    }
	    else if (CardValue == 11)
	    {
		CardVal = "J";
	    }
	    else if (CardValue == 12)
	    {
		CardVal = "Q";
	    }
	    else if (CardValue == 13)
	    {
		CardVal = "K";
	    }
	    Font font = new Font ("SandSerif", Font.BOLD, 15);
	    c.setFont (font);
	    c.drawString (CardVal, iCentreX - iWidth * 2 / 5, iCentreY - iHeight * 1 / 3);
	    c.drawString (CardVal, iCentreX + iWidth * 1 / 3, iCentreY + iHeight * 2 / 5);
	}
	else if (isFaceUp == false)
	{
	    c.setColor (iColor);
	    c.fillRect (iCentreX - iWidth / 2, iCentreY - iHeight / 2, iWidth * 101 / 100, iHeight * 101 / 100);
	}
    }


    private void drawSuit (Graphics g)
    {
	if (Suit == 1)
	{
	    DiamondClass diamond = new DiamondClass ();
	    diamond.setCentre (iCentreX, iCentreY);
	    diamond.setHeight (iHeight / 4);
	    diamond.setColor (Color.red);
	    diamond.draw (g);
	}
	else if (Suit == 2)
	{
	    ClubClass club = new ClubClass ();
	    club.setCentre (iCentreX, iCentreY);
	    club.setHeight (iHeight / 4);
	    club.setColor (Color.black);
	    club.draw (g);
	}
	else if (Suit == 3)
	{
	    HeartClass heart = new HeartClass ();
	    heart.setCentre (iCentreX, iCentreY);
	    heart.setHeight (iHeight / 4);
	    heart.setColor (Color.red);
	    heart.draw (g);
	}
	else if (Suit == 4)
	{
	    SpadeClass spade = new SpadeClass ();
	    spade.setCentre (iCentreX, iCentreY);
	    spade.setHeight (iHeight / 4);
	    spade.setColor (Color.black);
	    spade.draw (g);
	}
    }


    public void draw (Graphics g)
    {
	if (isFaceUp == true)
	{
	    g.setColor (Color.white);
	    g.fillRect (iCentreX - iWidth / 2, iCentreY - iHeight / 2, iWidth, iHeight);
	    if (isPicked == true)
	    {
		g.setColor (Color.pink);
		g.drawRect (iCentreX - iWidth / 2, iCentreY - iHeight / 2, iWidth, iHeight);
	    }
	    else
	    {
		g.setColor (iColor);
		g.drawRect (iCentreX - iWidth / 2, iCentreY - iHeight / 2, iWidth, iHeight);
	    }
	    drawSuit (g);

	    String CardVal = "";
	    if (CardValue <= 9 && CardValue >= 2)
	    {
		CardVal = Integer.toString (CardValue);
	    }
	    else if (CardValue == 1)
	    {
		CardVal = "A";
	    }
	    else if (CardValue == 10)
	    {
		CardVal = "T";
	    }
	    else if (CardValue == 11)
	    {
		CardVal = "J";
	    }
	    else if (CardValue == 12)
	    {
		CardVal = "Q";
	    }
	    else if (CardValue == 13)
	    {
		CardVal = "K";
	    }
	    Font font = new Font ("SandSerif", Font.BOLD, 15);
	    g.setFont (font);
	    g.drawString (CardVal, iCentreX - iWidth * 2 / 5, iCentreY - iHeight * 1 / 3);
	    g.drawString (CardVal, iCentreX + iWidth * 1 / 3, iCentreY + iHeight * 2 / 5);
	}
	else if (isFaceUp == false)
	{
	    g.setColor (iColor);
	    g.fillRect (iCentreX - iWidth / 2, iCentreY - iHeight / 2, iWidth * 101 / 100, iHeight * 101 / 100);
	}
    }


    public void erase (Console c)
    {
	Color tempColor = iColor;
	iColor = Color.white;
	c.setColor (iColor);
	c.fillRect (iCentreX - iWidth / 2, iCentreY - iHeight / 2, iWidth * 102 / 100, iHeight * 101 / 100);
	iColor = tempColor;
    }


    public void erase (Graphics g)
    {
	Color tempColor = iColor;
	iColor = Color.white;
	g.setColor (iColor);
	g.fillRect (iCentreX - iWidth / 2, iCentreY - iHeight / 2, iWidth * 102 / 100, iHeight * 101 / 100);
	iColor = tempColor;
    }
}


