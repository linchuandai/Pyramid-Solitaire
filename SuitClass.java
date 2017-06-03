import hsa.Console;
import java.awt.*;

public abstract class SuitClass extends ShapeClass
{

    public void setWidth (int Width)
    {
	iWidth = Width;
	iHeight = Width * 5 / 4;
    }


    public void setHeight (int Height)
    {
	iWidth = Height * 4 / 5;
	iHeight = Height;
    }
}
