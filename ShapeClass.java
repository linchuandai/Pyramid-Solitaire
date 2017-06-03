import hsa.Console;
import java.awt.*;

public abstract class ShapeClass
{

    protected int iWidth = 80;
    protected int iHeight = 100;
    protected int iCentreX = 100;
    protected int iCentreY = 100;
    protected Color iColor = Color.black;
    protected boolean isFilled = true;

    public abstract void draw (Console c);
    public abstract void draw (Graphics g);

    public void erase (Console c)
    {
	Color tempColor = iColor;
	iColor = Color.white;
	draw (c);
	iColor = tempColor;
    }


    public void erase (Graphics g)
    {
	Color tempColor = iColor;
	iColor = Color.white;
	draw (g);
	iColor = tempColor;
    }


    public void setColor (Color Colour)
    {
	iColor = Colour;
    }


    public Color getColor ()
    {
	return iColor;
    }


    public void setWidth (int Width)
    {
	iWidth = Width;
    }


    public void setHeight (int Height)
    {
	iHeight = Height;
    }


    public int getWidth ()
    {
	return iWidth;
    }


    public int getHeight ()
    {
	return iHeight;
    }


    public void setCentre (int CentreX, int CentreY)
    {
	iCentreX = CentreX;
	iCentreY = CentreY;
    }


    public int getCentreX ()
    {
	return iCentreX;
    }


    public int getCentreY ()
    {
	return iCentreY;
    }


    public void delay (int iDelayTime)
    {
	long lFinalTime = System.currentTimeMillis () + iDelayTime;
	do
	{
	}

	while (lFinalTime >= System.currentTimeMillis ());
    }


    public void setIsFilled (boolean filled)
    {
	isFilled = filled;
    }


    public boolean getIsFilled ()
    {
	return isFilled;
    }
}
