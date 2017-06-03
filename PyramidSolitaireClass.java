import java.awt.*;
//import java.util.Vector;

public class PyramidSolitaireClass
{
    protected CardClass[] pyramidPile;
    protected boolean[] pickedPile;
    //Pile for Pyramid

    protected CardClass[] deckPile;
    protected boolean[] chosenPile;
    //Pile for deck
    private int deckCards;

    protected int Difficulty;
    //1 = easy : 5 layers
    //2 = medium : 6 layers
    //3 = hard : 7 layers

    protected int reDeal;
    //0, 1, or 2

    private int pyramidCards;
    //number of cards in the pyramid

    private int iPointX;
    private int iPointY;
    //coordinates the game is based off of

    private boolean DealAgain;
    //to redeal the cards or not

    //basic constructor class for pyramid solitaire
    public PyramidSolitaireClass ()
    {
	pyramidCards = 0;
	Difficulty = 2;
	reDeal = 3 - Difficulty;
	for (int i = 1 ; i <= Difficulty + 4 ; i++)
	{
	    pyramidCards = pyramidCards + i;
	}
	pyramidPile = new CardClass [pyramidCards];
	pickedPile = new boolean [pyramidCards];
	for (int i = 0 ; i < pyramidCards ; i++)
	{
	    pickedPile [i] = false;
	}
	deckCards = 52 - pyramidCards;
	deckPile = new CardClass [deckCards];
	chosenPile = new boolean [deckCards];
	for (int i = 0 ; i < deckCards ; i++)
	{
	    chosenPile [i] = false;
	}
	iPointX = 800;
	iPointY = 200;
	DealAgain = false;
	createGameDecks ();
    }


    //constructor that allows for the difficulty setting
    public PyramidSolitaireClass (int difficulty)
    {
	pyramidCards = 0;
	if (difficulty >= 0 && difficulty <= 2)
	{
	    Difficulty = difficulty;
	}
	else
	{
	    Difficulty = 2;
	}
	reDeal = 3 - Difficulty;
	for (int i = 1 ; i <= Difficulty + 4 ; i++)
	{
	    pyramidCards = pyramidCards + i;
	}
	pyramidPile = new CardClass [pyramidCards];
	pickedPile = new boolean [pyramidCards];
	for (int i = 0 ; i < pyramidCards ; i++)
	{
	    pickedPile [i] = false;
	}
	deckCards = 52 - pyramidCards;
	deckPile = new CardClass [deckCards];
	chosenPile = new boolean [deckCards];
	for (int i = 0 ; i < deckCards ; i++)
	{
	    chosenPile [i] = false;
	}
	iPointX = 800;
	iPointY = 200;
	DealAgain = false;
	createGameDecks ();
    }


    //delay method
    public void delay (int iDelayTime)
    {
	long lFinalTime = System.currentTimeMillis () + iDelayTime;
	do
	{
	}


	while (lFinalTime >= System.currentTimeMillis ());
    }


    //creates the pyramid deck and dealer deck
    private void createGameDecks ()
    {
	StandardDeckClass gameDeck = new StandardDeckClass ();  // takes a standard deck
	gameDeck.shuffle ();                                    //shuffles the cards
	for (int i = 0 ; i < pyramidCards ; i++)                //adds the first cards to the pyramid deck pile to fill up the array
	{
	    pyramidPile [i] = (gameDeck.deal (0));
	}

	for (int i = 0 ; i < deckCards ; i++)                   //adds the last cards to the dealer deck pile to fill up the array
	{
	    deckPile [i] = (gameDeck.deal (0));
	}
    }


    //draws the pyramid structure
    public void drawPyramid (Graphics g)
    {
	int PointY = iPointY;
	int ctr = 0;
	for (int i = 1 ; i <= Difficulty + 4 ; i++)
	{
	    int PointX = iPointX;
	    PointX = PointX - 50 * i;
	    for (int j = 1 ; j <= i ; j++)
	    {
		if (pickedPile [ctr] == false)                  //checks if the card was already chose
		{
		    CardClass card = new CardClass ();
		    card = pyramidPile [ctr];
		    card.setCentre (PointX, PointY);            //sets the coordinates for the card
		    card.draw (g);                              //draws the card
		}
		PointX = PointX + 100;                          //updates the x-value
		ctr++;
	    }
	    PointY = PointY + 70;                               //updates the y-value
	}
    }


    //draws the dealer deck
    public void drawDeck (Graphics g)
    {
	int PointX = iPointX - 500;
	int PointY = iPointY;
	if (DealAgain == false)                                 //checks if the deck is at the end and has to deal again
	{
	    if (reDeal != 0)                                    //checks the amount of redeals left
	    {
		if (chosenPile [deckCards - 1] == false)        //decks if the card has already been chosen
		{
		    CardClass card = new CardClass ();
		    card = deckPile [deckCards - 1];
		    card.setCentre (PointX, PointY);
		    card.setFaceUp (false);
		    card.draw (g);                              //draws the face-down file of cards
		    card.setFaceUp (true);
		    card.setCentre (PointX + 100, PointY);
		    card.draw (g);                              //draws the face-up card
		    if (deckCards == 1)                         //checks if the deck is at the end
		    {
			DealAgain = true;                       //sets for the deck to be redealt
			reDeal--;                               //reduces the amount of times the deck can be redealt
		    }
		}
		else
		{
		    nextCard ();                                //moves onto next card
		    drawDeck (g);                               //redraws the deck
		}
	    }
	    else
	    {
		CardClass card = new CardClass ();
		card = deckPile [0];
		card.setCentre (PointX, PointY);
		card.setFaceUp (false);
		card.draw (g);
		card.setFaceUp (true);
		card.setCentre (PointX + 100, PointY);
		card.draw (g);
	    }
	}
	else if (DealAgain == true)
	{
	    deckCards = 52 - pyramidCards;                      //sets the amount of cards in the deck back to original
	    DealAgain = false;                                  //does not deal again
	    drawDeck (g);                                       //draws the deck
	}
    }


    //moves onto the next card
    public void nextCard ()
    {
	//System.out.println ("4. " + deckCards);
	if (reDeal != 0)                                        //checks it they are out of redeals
	{
	    deckCards--;                                        //moves onto the next card in the pile
	}
    }


    //goes back to the last card after a card has been chosen
    public void lastCard ()
    {
	int temp = deckCards;
	do
	{
	    if (deckCards < 52 - pyramidCards)                  //checks to see if we have surmounted the acutal amount of cards in the dekc
	    {
		deckCards++;                                    //increments the cards
	    }
	    else
	    {
		break;                                          //exits the loop if it excees the amount
	    }
	}
	while (chosenPile [deckCards - 1] == true);
	if (deckCards == 52 - pyramidCards)                     //moves the cards forward instead of back if the amount of card exceeds the original amount
	{
	    deckCards = temp;
	    nextCard ();
	}
    }


    //redraws the cad if it has been chosen
    public void reDrawCard (Graphics g, int cardPosition)
    {
	int PointY = iPointY;
	int ctr = 0;
	if (cardPosition == 100)
	{
	    int PointX = iPointX - 400;
	    CardClass card = new CardClass ();
	    card = deckPile [deckCards - 1];
	    card.setCentre (PointX, PointY);
	    card.draw (g);
	}
	else
	{
	    for (int i = 1 ; i <= Difficulty + 4 ; i++)
	    {
		int PointX = iPointX;
		PointX = PointX - 50 * i;
		for (int j = 1 ; j <= i ; j++)
		{
		    if (cardPosition - 1 == ctr)
		    {
			CardClass card = new CardClass ();
			card = pyramidPile [ctr];
			card.setCentre (PointX, PointY);
			card.draw (g);
			//delay (20);
		    }
		    PointX = PointX + 100;
		    ctr++;
		}
		PointY = PointY + 70;
	    }
	}
    }


    public void eraseCard (Graphics g, int cardPosition)
    {
	int PointY = iPointY;
	int ctr = 0;

	for (int i = 1 ; i <= Difficulty + 4 ; i++)
	{
	    int PointX = iPointX;
	    PointX = PointX - 50 * i;
	    for (int j = 1 ; j <= i ; j++)
	    {
		if (cardPosition - 1 == ctr)
		{
		    CardClass card = new CardClass ();
		    card = pyramidPile [ctr];
		    card.setCentre (PointX, PointY);
		    card.erase (g);
		}
		PointX = PointX + 100;
		ctr++;
	    }
	    PointY = PointY + 70;
	}
    }


    public boolean flipCard (int X, int Y)
    {
	int PointX = iPointX - 500;
	int PointY = iPointY;
	if ((X >= PointX - 35) && X <= (PointX + 35) && (Y >= PointY - 45) && (Y <= PointY + 45))
	{
	    return true;
	}
	return false;
    }


    public int whichCard (int X, int Y)
    {
	int PointY = iPointY;
	int PointX = iPointX - 400;
	int ctr = 0;
	if ((X >= PointX - 35) && X <= (PointX + 35) && (Y >= PointY - 45) && (Y <= PointY + 45))
	{
	    if (chosenPile [deckCards - 1] == false)
	    {
		return 100;
	    }
	    else
	    {
		return 0;
	    }
	}
	else
	{
	    for (int i = 1 ; i <= Difficulty + 4 ; i++)
	    {
		PointX = iPointX;
		PointX = PointX - 50 * i;
		for (int j = 1 ; j <= i ; j++)
		{
		    if ((X >= PointX - 35) && X <= (PointX + 35) && (Y >= PointY - 45) && (Y <= PointY + 45))
		    {
			if (pickedPile [ctr] == false)
			{
			    return ctr + 1;
			}
			else
			{
			    return 0;
			}
		    }
		    PointX = PointX + 100;
		    ctr++;
		}
		PointY = PointY + 70;
	    }
	    return 0;
	}
    }


    public boolean isPickable (int cardPosition)
    {
	if (cardPosition == 100)
	{
	    return true;
	}
	else if (cardPosition > (pyramidCards - (Difficulty + 4)))
	{
	    return true;
	}
	else
	{
	    int addValue = 0;
	    if (cardPosition <= 1)
	    {
		addValue = 1;
	    }
	    else if (cardPosition <= 3)
	    {
		addValue = 2;
	    }
	    else if (cardPosition <= 6)
	    {
		addValue = 3;
	    }
	    else if (cardPosition <= 10)
	    {
		addValue = 4;
	    }
	    else if (cardPosition <= 15)
	    {
		addValue = 5;
	    }
	    else
	    {
		addValue = 6;
	    }

	    if (pickedPile [cardPosition + addValue - 1] == true && pyramidPile [cardPosition + addValue].isPicked () == true)
	    {
		return true;
	    }
	    else
	    {
		return false;
	    }
	}
    }


    public void chooseCard (int cardPosition)
    {
	if (cardPosition == 100)
	{
	    deckPile [deckCards - 1].setPicked (true);
	}
	else
	{
	    pyramidPile [cardPosition - 1].setPicked (true);
	}
    }


    public void unchooseCard (int cardPosition)
    {
	if (cardPosition == 100)
	{
	    deckPile [deckCards - 1].setPicked (false);
	}
	else
	{
	    pyramidPile [cardPosition - 1].setPicked (false);
	}
    }



    public boolean isPair (int cardPosition)
    {
	//get value for  card

	if (cardPosition == 100)
	{
	    if (deckPile [deckCards - 1].getCardValue () == 13)
	    {
		return true;
	    }


	    else
	    {
		return false;
	    }
	}
	else
	{
	    if (pyramidPile [cardPosition - 1].getCardValue () == 13)
	    {
		return true;
	    }

	    else
	    {
		return false;
	    }
	}
    }


    public boolean isPair (int cardPosition, int card2Position)
    {
	int Value = 0;

	if (cardPosition == 100 && card2Position == 100)
	{
	    //get value for 1st card
	    Value = Value + deckPile [deckCards - 1].getCardValue ();

	    //get value for 2nd card
	    Value = Value + deckPile [deckCards - 1].getCardValue ();

	}
	else if (cardPosition == 100)
	{
	    //get value for 1st card
	    Value = Value + deckPile [deckCards - 1].getCardValue ();

	    //add value for 2nd card
	    Value = Value + pyramidPile [card2Position - 1].getCardValue ();
	}
	else if (card2Position == 100)
	{
	    //get value for 1st card
	    Value = Value + pyramidPile [cardPosition - 1].getCardValue ();

	    //get value for 2nd card
	    Value = Value + deckPile [deckCards - 1].getCardValue ();
	}
	else
	{
	    //get value for 1st card
	    Value = Value + pyramidPile [cardPosition - 1].getCardValue ();

	    //add value for 2nd card
	    Value = Value + pyramidPile [card2Position - 1].getCardValue ();
	}
	//System.out.println (Value);


	if (Value == 13)
	{
	    return true;
	}
	else
	{
	    return false;
	}
    }


    public void createPair (int cardPosition, Graphics g)
    {
	if (cardPosition == 100)
	{
	    chosenPile [deckCards - 1] = true;
	    nextCard ();
	    drawDeck (g);
	    lastCard ();
	}
	else
	{
	    pickedPile [cardPosition - 1] = true;
	    eraseCard (g, cardPosition);
	}

    }


    public void createPair (int cardPosition, int card2Position, Graphics g)
    {
	if (cardPosition == 100)
	{
	    chosenPile [deckCards - 1] = true;
	    pickedPile [card2Position - 1] = true;
	    lastCard ();
	    drawDeck (g);
	    eraseCard (g, card2Position);
	}
	else if (card2Position == 100)
	{
	    pickedPile [cardPosition - 1] = true;
	    chosenPile [deckCards - 1] = true;
	    lastCard ();
	    eraseCard (g, cardPosition);
	    drawDeck (g);

	}
	else
	{
	    pickedPile [cardPosition - 1] = true;
	    pickedPile [card2Position - 1] = true;
	    eraseCard (g, cardPosition);
	    drawDeck (g);
	}
    }


    public boolean isWin ()
    {
	for (int i = 0 ; i < pyramidCards ; i++)
	{
	    if (pickedPile [i] == false)
	    {
		return false;
	    }
	}
	return true;
    }


    public boolean isLose ()
    {

	if (reDeal == 0 && deckCards == 1)
	{
	    return true;
	}

	return false;
    }
}
