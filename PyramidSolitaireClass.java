import java.awt.*;

public class PyramidSolitaireClass
{
    private DeckClass pyramidDeck;
    //Pile for Pyramid
    private int pyramidCards;

    private DeckClass Deck;
    //Pile for deck
    private int deckCards;

    private int Difficulty;
    //0 = easy : 4 layers
    //1 = medium : 5 layers
    //2 = hard : 6 layers
    //3 = extreme : 7 layers

    private int reDeal;
    //0, 1, 2, 3

    private int iPointX;
    private int iPointY;
    //coordinates the game is based off of

    private boolean DealAgain;
    //to redeal the cards or not

    //default constructor class for pyramid solitaire
    public PyramidSolitaireClass ()
    {
	pyramidCards = 0;
	Difficulty = 2;
	reDeal = Difficulty;
	//determines the number of cards in the pyramid
	for (int i = 1 ; i <= Difficulty + 4 ; i++)
	{
	    pyramidCards = pyramidCards + i;
	}
	pyramidDeck = new DeckClass (pyramidCards);
	deckCards = 52 - pyramidCards;
	Deck = new DeckClass (deckCards);
	iPointX = 700;
	iPointY = 200;
	DealAgain = false;
	createGameDecks ();
    }


    //constructor that allows for the difficulty setting
    public PyramidSolitaireClass (int difficulty)
    {

	//checks if the difficulty is in range
	if (difficulty >= 0 && difficulty <= 3)
	{
	    Difficulty = difficulty;
	}

	//sets it to hard otherwise
	else
	{
	    Difficulty = 2;
	}

	reDeal = Difficulty;

	pyramidCards = 0;

	//determines the number of cards in the pyramid
	for (int i = 1 ; i <= Difficulty + 4 ; i++)
	{
	    pyramidCards = pyramidCards + i;
	}
	pyramidDeck = new DeckClass (pyramidCards);
	deckCards = 52 - pyramidCards;
	Deck = new DeckClass (deckCards);
	iPointX = 700;
	iPointY = 200;
	DealAgain = false;
	createGameDecks ();
    }


    //delay method
    public void delay (int iDelayTime)
    {
	//sets the final time to the system time + amount of time set
	long lFinalTime = System.currentTimeMillis () + iDelayTime;

	//does nothing while the timer runs
	do
	{
	}
	while (lFinalTime >= System.currentTimeMillis ());
    }


    //creates the pyramid deck and dealer deck
    private void createGameDecks ()
    {
	//creates a standard deck for the game
	StandardDeckClass gameDeck = new StandardDeckClass ();
	//shuffles the cards
	gameDeck.shuffle ();

	//adds the first cards to the pyramid deck pile to fill up the array
	for (int i = 0 ; i < pyramidCards ; i++)
	{
	    pyramidDeck.addCard (gameDeck.deal (0));
	}

	//adds the last cards to the dealer deck pile to fill up the array
	for (int i = 0 ; i < deckCards ; i++)
	{
	    Deck.addCard (gameDeck.deal (0));
	}
    }


    //draws the pyramid structure
    public void drawPyramid (Graphics g)
    {
	int PointY = iPointY;
	int ctr = 0;

	for (int i = 1 ; i <= Difficulty + 4 ; i++)
	{

	    //sets the x and y coordinates
	    int PointX = iPointX;
	    PointX = PointX - 50 * i;
	    for (int j = 1 ; j <= i ; j++)
	    {

		//checks if the card was already chosen
		if (pyramidDeck.getCard (ctr).Enabled () == true)
		{

		    //sets the coordinates for the card
		    pyramidDeck.getCard (ctr).setCentre (PointX, PointY);
		    //draws the card
		    pyramidDeck.getCard (ctr).draw (g);
		    // System.out.println (ctr);

		}
		//updates the x-value
		PointX = PointX + 100;
		//updates the card counter
		ctr++;
	    }
	    //updates the y-value
	    PointY = PointY + 70;
	}
    }


    /*

	//draws the dealer deck
	public void drawDeck (Graphics g)
	{

	    int PointX = iPointX - 600;
	    int PointY = iPointY;

	    //checks if the derck is empty
	    if (isDeckEmpty () == false)
	    {
		//checks the amount of redeals left
		if (reDeal >= 0)
		{
		    //checks if the deck is at the end and has to deal again
		    if (DealAgain == false)
		    {

			if (deckCards >= 1)
			{
			    //checks if the card has already been chosen
			    if (Deck.getCard (deckCards - 1).Enabled () == true)
			    {
				System.out.println (deckCards);
				//draws the face down card
				Deck.getCard (deckCards - 1).setCentre (PointX, PointY);
				Deck.getCard (deckCards - 1).setFaceUp (false);
				Deck.getCard (deckCards - 1).draw (g);

				//draws the face up card
				Deck.getCard (deckCards - 1).setFaceUp (true);
				Deck.getCard (deckCards - 1).setCentre (PointX + 100, PointY);
				//draws the face-up card
				Deck.getCard (deckCards - 1).draw (g);
			    }
			    else
			    {
				//moves onto next card
				nextCard ();
				//redraws the deck
				drawDeck (g);
			    }

			}
			else if (DealAgain == true)
			{
			    //sets the amount of cards in the deck back to original
			    deckCards = 52 - pyramidCards;
			    //does not deal again
			    DealAgain = false;
			    //draws the deck to show the next card
			    drawDeck (g);
			}
		    }
		    else
		    {
			deckCards = 1;
			//checks if the last card has been chosen
			if (Deck.getCard (deckCards - 1).Enabled () == false)
			{
			    //increments the cards while the consecutive cards have been chosen
			    do
			    {
				deckCards++;
			    }
			    while (Deck.getCard (deckCards - 1).Enabled () == false);
			}
			//draws the face down card
			Deck.getCard (deckCards - 1).setCentre (PointX, PointY);
			Deck.getCard (deckCards - 1).setFaceUp (false);
			Deck.getCard (deckCards - 1).draw (g);

			//draws the face up card
			Deck.getCard (deckCards - 1).setFaceUp (true);
			Deck.getCard (deckCards - 1).setCentre (PointX + 100, PointY);
			//draws the face-up card
			Deck.getCard (deckCards - 1).draw (g);
		    }
		}
		else
		{
		    Deck.getCard (0).setCentre (PointX, PointY);
		    Deck.getCard (0).setFaceUp (false);
		    Deck.getCard (0).draw (g);
		}
	    }
	}
    */

    public void drawDeck (Graphics g)
    {
	int PointX = iPointX - 600;
	int PointY = iPointY;

	if ((DealAgain == false) && (reDeal >= 0))
	{
	    if (deckCards >= 1)
	    {
		//checks if the card has already been chosen
		if (Deck.getCard (deckCards - 1).Enabled () == true)
		{
		    //System.out.println (deckCards);
		    //draws the face down card
		    Deck.getCard (deckCards - 1).setCentre (PointX, PointY);
		    Deck.getCard (deckCards - 1).setFaceUp (false);
		    Deck.getCard (deckCards - 1).draw (g);

		    //draws the face up card
		    Deck.getCard (deckCards - 1).setFaceUp (true);
		    Deck.getCard (deckCards - 1).setCentre (PointX + 100, PointY);
		    //draws the face-up card
		    Deck.getCard (deckCards - 1).draw (g);
		}
		else
		{
		    //moves onto next card
		    nextCard ();
		    //redraws the deck
		    drawDeck (g);
		}
	    }
	    else
	    {
		DealAgain = true;
		drawDeck (g);
	    }
	}
	else if ((DealAgain == true) && (reDeal >= 0))
	{
	    //sets the amount of cards in the deck back to original
	    deckCards = 52 - pyramidCards;
	    //does not deal again
	    DealAgain = false;
	    //Decreases the amount of times to redeal
	    reDeal --;
	    //draws the deck to show the next card
	    drawDeck (g);
	}
	else if (reDeal < 0)
	{
	    deckCards = 1;
	    //checks if the last card has been chosen
	    if (Deck.getCard (deckCards - 1).Enabled () == false)
	    {
		//increments the cards while the consecutive cards have been chosen
		do
		{
		    deckCards++;
		}
		while (Deck.getCard (deckCards - 1).Enabled () == false);
	    }
	    //draws the face down card
	    Deck.getCard (deckCards - 1).setCentre (PointX, PointY);
	    Deck.getCard (deckCards - 1).setFaceUp (false);
	    Deck.getCard (deckCards - 1).draw (g);

	    //draws the face up card
	    Deck.getCard (deckCards - 1).setFaceUp (true);
	    Deck.getCard (deckCards - 1).setCentre (PointX + 100, PointY);
	    //draws the face-up card
	    Deck.getCard (deckCards - 1).draw (g);
	}
	else if (isDeckEmpty () == true)
	{
	    Deck.getCard (0).setCentre (PointX, PointY);
	    Deck.getCard (0).setFaceUp (false);
	    Deck.getCard (0).draw (g);
	}

    }



    //moves onto the next card
    public void nextCard ()
    {

	//checks it they are out of redeals
	if (reDeal >= 0 && deckCards >= 1)
	{
	    //moves onto the next card in the pile
	    deckCards--;
	}
    }



    //goes back to the last card after a card has been chosen
    public void lastCard ()
    {

	int temp = deckCards;

	do
	{
	    //checks to see if we have surmounted the acutal amount of cards in the deck
	    if (deckCards < 52 - pyramidCards)
	    {
		// increments the cards
		deckCards++;
	    }

	    else
	    {
		//exits the loop if it excees the amount
		break;
	    }
	}
	while (Deck.getCard (deckCards - 1).Enabled () == false);

	//moves the cards forward instead of back if the amount of card exceeds the original amount
	if ((deckCards == 52 - pyramidCards) && (Deck.getCard (deckCards - 1).Enabled () == false))
	{
	    deckCards = temp;
	    nextCard ();
	}
	//System.out.println ("LastCard: " + deckCards);
    }


    public int getDeckCards ()
    {
	return deckCards;
    }


    public int getReDeals ()
    {
	return reDeal;
    }


    //redraws the cad if it has been chosen
    //good for drawing a chosen card without
    //having to repaint the entire pyramid
    public void reDrawCard (Graphics g, int cardPosition)
    {
	int PointY = iPointY;
	int ctr = 0;

	//if the card is in the deck
	if (cardPosition == 100)
	{
	    int PointX = iPointX - 500;

	    //redraws the card
	    Deck.getCard (deckCards - 1).setCentre (PointX, PointY);
	    Deck.getCard (deckCards - 1).draw (g);
	}
	else
	{
	    for (int i = 1 ; i <= Difficulty + 4 ; i++)
	    {
		int PointX = iPointX;
		PointX = PointX - 50 * i;
		for (int j = 1 ; j <= i ; j++)
		{

		    //checks if the card is the one that has to be redrawn
		    if (cardPosition - 1 == ctr)
		    {
			//redraws the card
			pyramidDeck.getCard (ctr).setCentre (PointX, PointY);
			pyramidDeck.getCard (ctr).draw (g);
		    }
		    PointX = PointX + 100;
		    ctr++;
		}
		PointY = PointY + 70;
	    }
	}
    }


    //erases the card in the given spot
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

		//checks if the card is the one that has to be redrawn
		if (ctr == cardPosition - 1)
		{

		    //erases the card
		    pyramidDeck.getCard (ctr).setCentre (PointX, PointY);
		    pyramidDeck.getCard (ctr).erase (g);
		}
		PointX = PointX + 100;
		ctr++;
	    }
	    PointY = PointY + 70;
	}
    }


    //checks if there is a need to flip the next card in the deck
    public boolean flipCard (int X, int Y)
    {

	int PointX = iPointX - 600;
	int PointY = iPointY;
	if ((X >= PointX - 35) && X <= (PointX + 35) && (Y >= PointY - 45) && (Y <= PointY + 45))
	{
	    return true;
	}
	return false;
    }


    //returns the card that has been chosen by the user
    public int whichCard (int X, int Y)
    {
	int PointY = iPointY;
	int PointX = iPointX - 500;
	int ctr = 0;

	//checks if the card is in the deck
	if ((X >= PointX - 35) && X <= (PointX + 35) && (Y >= PointY - 45) && (Y <= PointY + 45))
	{
	    if (Deck.getCard (deckCards - 1).Enabled () == true)
	    {
		return 100;
	    }
	    else
	    {
		return 0;
	    }
	}

	//checks if the card is in the pyramid
	else
	{
	    PointY = iPointY;
	    for (int i = 1 ; i <= Difficulty + 4 ; i++)
	    {
		PointX = iPointX;
		PointX = PointX - 50 * i;

		//goes through each of the cards in the pyramid
		for (int j = 1 ; j <= i ; j++)
		{

		    //checks the coordinates of the click
		    if ((X >= PointX - 35) && X <= (PointX + 35) && (Y >= PointY - 45) && (Y <= PointY + 45))
		    {
			if (pyramidDeck.getCard (ctr).Enabled () == true)
			{
			    return ctr + 1;
			}
		    }
		    PointX = PointX + 100;
		    ctr++;
		}
		PointY = PointY + 70;
	    }
	}
	return 0;
    }


    //tells if the card is pickable (if the card is uncovered)
    public boolean isPickable (int cardPosition)
    {

	if (cardPosition == 100)
	{
	    return true;
	}

	//checks if if card is in the bottom row
	else if (cardPosition > (pyramidCards - (Difficulty + 4)))
	{
	    return true;
	}

	//checks if the cards covering the chosen card has been chosen
	else
	{
	    //the values to be added to check for the cards
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
	    //checks the 2 cards beneath the chosen card
	    if (pyramidDeck.getCard (cardPosition + addValue - 1).Enabled () == false && pyramidDeck.getCard (cardPosition + addValue).Enabled () == false)
	    {
		return true;
	    }
	    else
	    {
		return false;
	    }
	}
    }


    //marks the card as chosen
    public void chooseCard (int cardPosition)
    {

	//if the card is in the deck
	if (cardPosition == 100)
	{
	    Deck.getCard (deckCards - 1).setPicked (true);
	}

	//if the card is in the pyramid
	else
	{
	    pyramidDeck.getCard (cardPosition - 1).setPicked (true);
	}
    }


    //marks the card as unchosen
    public void unchooseCard (int cardPosition)
    {

	//if the card is in the deck
	if (cardPosition == 100)
	{
	    Deck.getCard (deckCards - 1).setPicked (false);
	}

	//if the card is in the pyramid
	else
	{
	    pyramidDeck.getCard (cardPosition - 1).setPicked (false);
	}
    }



    //checks if the cards add up to 13 (one card)
    public boolean isPair (int cardPosition)
    {
	//if the card in in the deck
	if (cardPosition == 100)
	{
	    //checks if the value is 13 (a King)
	    if (Deck.getCard (deckCards - 1).getCardValue () == 13)
	    {
		return true;
	    }


	    else
	    {
		return false;
	    }
	}

	//if the card is in the pyramid
	else
	{
	    //checks if the value is 13 (a King)
	    if (pyramidDeck.getCard (cardPosition - 1).getCardValue () == 13)
	    {
		return true;
	    }

	    else
	    {
		return false;
	    }
	}
    }


    //checks if the cards add up to 13 (two cards)
    public boolean isPair (int cardPosition, int card2Position)
    {

	//sets the starting value at 0
	int Value = 0;

	if (cardPosition == 100 && card2Position == 100)
	{
	    //get value for 1st card
	    Value = Value + Deck.getCard (deckCards - 1).getCardValue ();
	    //get value for 2nd card
	    Value = Value + Deck.getCard (deckCards - 1).getCardValue ();
	}

	else if (cardPosition == 100)
	{
	    //get value for 1st card
	    Value = Value + pyramidDeck.getCard (card2Position - 1).getCardValue ();
	    //get value for 2nd card
	    Value = Value + Deck.getCard (deckCards - 1).getCardValue ();
	}

	else if (card2Position == 100)
	{
	    //get value for 1st card
	    Value = Value + Deck.getCard (deckCards - 1).getCardValue ();
	    //get value for 2nd card
	    Value = Value + pyramidDeck.getCard (cardPosition - 1).getCardValue ();
	}
	else
	{
	    //get value for 1st card
	    Value = Value + pyramidDeck.getCard (cardPosition - 1).getCardValue ();
	    //get value for 2nd card
	    Value = Value + pyramidDeck.getCard (card2Position - 1).getCardValue ();
	}

	//checks if the sum of the cards is 13
	if (Value == 13)
	{
	    return true;
	}
	else
	{
	    return false;
	}
    }



    //creates a pair from the cards (one card)
    public void createPair (int cardPosition, Graphics g)
    {
	//if the card is in the deck
	if (cardPosition == 100)
	{
	    //disables the card
	    Deck.getCard (deckCards - 1).Disable ();

	    //goes to the last deck card
	    //System.out.println ("createPair: " + deckCards);
	    lastCard ();
	    drawDeck (g);
	}
	else
	{
	    //disables the card
	    pyramidDeck.getCard (cardPosition - 1).Disable ();

	    //erases the chosen card in the pyramid
	    eraseCard (g, cardPosition - 1);
	}
    }


    //creates a pair from the cards (two cards)
    public void createPair (int cardPosition, int card2Position, Graphics g)
    {
	if (cardPosition == 100)
	{
	    //disables the cards
	    Deck.getCard (deckCards - 1).Disable ();
	    pyramidDeck.getCard (card2Position - 1).Disable ();

	    //goes to the last card in the deck
	    lastCard ();
	    drawDeck (g);

	    //erases the choesn card in the pyramid
	    eraseCard (g, card2Position);

	}
	else if (card2Position == 100)
	{
	    //disables the cards
	    pyramidDeck.getCard (cardPosition - 1).Disable ();
	    Deck.getCard (deckCards - 1).Disable ();

	    //goes to the last card in the deck
	    lastCard ();
	    drawDeck (g);

	    //erases the choesn card in the pyramid
	    eraseCard (g, cardPosition);
	}
	else
	{
	    //disables the cards
	    pyramidDeck.getCard (cardPosition - 1).Disable ();
	    pyramidDeck.getCard (card2Position - 1).Disable ();

	    //erases the choesn cards in the pyramid
	    eraseCard (g, cardPosition);
	    eraseCard (g, card2Position);
	}
    }


    //checks if the player has won
    public boolean isWin ()
    {
	for (int i = 0 ; i < pyramidCards ; i++)
	{
	    //checks if all the cards in the deck have been chosen
	    if (pyramidDeck.getCard (i).Enabled () == true)
	    {
		return false;
	    }
	}
	return true;
    }


    //checks if the deck is empty
    public boolean isDeckEmpty ()
    {
	for (int i = 0 ; i < 52 - pyramidCards ; i++)
	{
	    //checks if all the cards in the deck have been chosen
	    if (Deck.getCard (i).Enabled () == true)
	    {
		return false;
	    }
	}
	return true;
    }


    //redeals the deck to replay the game
    public void reDeal (Graphics g)
    {
	//sets the cards in the pyramid back to default
	for (int i = 0 ; i < pyramidCards ; i++)
	{
	    pyramidDeck.getCard (i).Enable ();
	    pyramidDeck.getCard (i).setPicked (false);
	}

	//sets the cards in the deck back to default
	for (int i = 0 ; i < deckCards ; i++)
	{
	    Deck.getCard (i).Enable ();
	    Deck.getCard (i).setPicked (false);
	}

	//resets the card deal counters
	reDeal = Difficulty;
	deckCards = 52 - pyramidCards;
    }
}


