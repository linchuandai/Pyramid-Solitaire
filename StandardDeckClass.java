import java.awt.*;

class StandardDeckClass extends DeckClass
{

    public StandardDeckClass ()
    {
	CardClass card;
	for (int i = 1 ; i <= 4 ; i++)
	{
	    for (int j = 1 ; j <= 13 ; j++)
	    {
		card = new CardClass (i, j);
		addCard (card);
	    }
	}

    }
}
