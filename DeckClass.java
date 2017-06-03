import java.awt.*;
import java.util.Vector;

public class DeckClass
{

    protected Vector deck = new Vector (52, 1);

    private boolean isEmpty ()
    {
	return deck.isEmpty ();
    }


    public void addCard (CardClass card)
    {
	deck.add (card);
    }


    public void addCard (CardClass card, int position)
    {
	deck.add (position, card);
    }


    public void removeCard ()
    {
	deck.remove (0);
    }


    public void removeCard (int position)
    {
	deck.remove (position - 1);
    }


    public void clearDeck ()
    {
	deck.clear ();
    }


    public int getDeckSize ()
    {
	return deck.size ();
    }


    public CardClass deal ()
    {
	return (CardClass) deck.remove (0);
    }


    public CardClass deal (int position)
    {

	return (CardClass) deck.remove (position);
    }


    public void shuffle ()
    {
	CardClass card;
	for (int i = 0 ; i < deck.size () ; i++)
	{
	    card = new CardClass ();
	    card = (CardClass) deck.remove (i);
	    deck.add ((int) (Math.random () * (deck.size () + 1)), card);

	}
    }


    public CardClass getCard (int position)
    {
	return (CardClass) deck.get (position - 1);

    }
    

}

