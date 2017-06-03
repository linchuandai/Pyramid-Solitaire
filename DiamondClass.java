import hsa.Console;
import java.awt.*;

public class DiamondClass extends SuitClass
{




    public void draw (Console c)
    {
	int iPointsX[] = new int [4];
	int iPointsY[] = new int [4];

	iPointsX [0] = iCentreX - iWidth / 2;
	iPointsY [0] = iCentreY;
	iPointsX [1] = iCentreX;
	iPointsY [1] = iCentreY - iHeight / 2;
	iPointsX [2] = iCentreX + iWidth / 2;
	iPointsY [2] = iCentreY;
	iPointsX [3] = iCentreX;
	iPointsY [3] = iCentreY + iHeight / 2;
	c.setColor (iColor);

	if (isFilled == true)
	{
	    c.fillPolygon (iPointsX, iPointsY, 4);
	}
	else if (isFilled == false)
	{
	    c.drawPolygon (iPointsX, iPointsY, 4);
	}

    }


    public void draw (Graphics g)
    {
	int iPointsX[] = new int [4];
	int iPointsY[] = new int [4];

	iPointsX [0] = iCentreX - iWidth / 2;
	iPointsY [0] = iCentreY;
	iPointsX [1] = iCentreX;
	iPointsY [1] = iCentreY - iHeight / 2;
	iPointsX [2] = iCentreX + iWidth / 2;
	iPointsY [2] = iCentreY;
	iPointsX [3] = iCentreX;
	iPointsY [3] = iCentreY + iHeight / 2;
	g.setColor (iColor);

	if (isFilled == true)
	{
	    g.fillPolygon (iPointsX, iPointsY, 4);
	}
	else if (isFilled == false)
	{
	    g.drawPolygon (iPointsX, iPointsY, 4);
	}

    }
}


