public class MenuItemClass extends MenuItem
{
    private Component event_handler;
    
    public MenuItemClass (Component event_handler, String label)
    {
	super (label);
	this.event_handler = event_handler;
    }


    public boolean postEvent (Event e)
    {
	if (event_handler.isValid ())
	    return event_handler.postEvent (e);
	else
	    return false;
    }
}
