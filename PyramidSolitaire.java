import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

public class PyramidSolitaire extends Applet implements ActionListener, MouseListener, MouseMotionListener //, MenuListener
{
    Graphics g;   // declares a graphics canvas for drawing
    private PyramidSolitaireClass pyramidsolitaire;

    //if they have picked the card
    private int picked;

    //holders for the positions of the cards
    private int card1, card2;

    //if the user has already flipped a card the deck
    private boolean flipped;

    //backgournd color
    private Color iColor;

    //difficulty of the game (0-3)
    private int difficulty;

    //if the game has been finished (won or lost)
    private boolean gameover;

    //popup dialog boxes for different events
    private Dialog winScreen, loseScreen, instructionScreen, restartReminder;

    //buttons for the popup windows
    private Button winButton, loseButton, instructionButton, restartButton, restartReminderOKButton;

    //setting the font size
    private Font textFont = new Font ("Serif", Font.ITALIC, 24);

    //layout of dialog boxes
    private FlowLayout layout = new FlowLayout (FlowLayout.LEFT, 20, 25);

    //text for the dialog boxes
    private Label winText, loseText, reminderText;
    private Label[] instructionWords;

    //main gameplay window
    private Frame gameWindow;

    //menubar
    private MenuBar menu;

    //menus on menubar
    private Menu game, level, instructions, color, defeat;

    //submenus for menus
    private MenuItemClass newgame, redeal, exit,
        easy, medium, hard, extreme,
        instructionpage,
        white, blue, lightgray, darkgray, orange, green,
        concede, surrender, admitdefeat;

    public void init ()
    {

        g = getGraphics (); // gets canvas created by browser-replaces new stmt

        //initialize values for variables;
        picked = 1;
        flipped = false;
        iColor = Color.white;
        difficulty = 2;
        gameover = false;
        g.setFont (textFont);
        pyramidsolitaire = new PyramidSolitaireClass (difficulty);

        ///////////////////// M E N U   C R E A T I O N //////////////////////////////
        Object f = getParent ();
        while (!(f instanceof Frame))
            f = ((Component) f).getParent ();

        gameWindow = (Frame) f; //new Frame("MyFrame");

        gameWindow.resize (1200, 900);
        gameWindow.show ();
        gameWindow.setLocation (0, 0);

        menu = new MenuBar ();

        //menu options for game
        menu.add (game = new Menu ("Game"));
        game.add (newgame = new MenuItemClass (this, "New Game"));
        game.add (redeal = new MenuItemClass (this, "Redeal"));
        game.add (exit = new MenuItemClass (this, "Exit"));

        //menu options for level
        menu.add (level = new Menu ("Level"));
        level.add (easy = new MenuItemClass (this, "Easy"));
        level.add (medium = new MenuItemClass (this, "Medium"));
        level.add (hard = new MenuItemClass (this, "Hard"));
        level.add (extreme = new MenuItemClass (this, "Extreme"));

        //menu options for instructions
        menu.add (instructions = new Menu ("Instructions"));
        instructions.add (instructionpage = new MenuItemClass (this, "Instructions"));

        //menu options for background color
        menu.add (color = new Menu ("Background Color"));
        color.add (white = new MenuItemClass (this, "White"));
        color.add (blue = new MenuItemClass (this, "Blue"));
        color.add (lightgray = new MenuItemClass (this, "Light Gray"));
        color.add (darkgray = new MenuItemClass (this, "Dark Gray"));
        color.add (orange = new MenuItemClass (this, "Orange"));
        color.add (green = new MenuItemClass (this, "Green"));

        //menu options for defeat
        menu.add (defeat = new Menu ("Defeat"));
        defeat.add (concede = new MenuItemClass (this, "Concede"));
        defeat.add (surrender = new MenuItemClass (this, "Surrender"));

        gameWindow.setMenuBar (menu);


        ///////////////////// D I A L O G   C R E A T I O N ///////////////////////////
        //sets up the window that pops up when the person wins
        winScreen = new Dialog (gameWindow, "GAME COMPLETE");
        winScreen.setSize (250, 150);
        winScreen.setLocationRelativeTo (null);
        winScreen.setLayout (layout);

        //sets the text when the player wins
        winText = new Label ("Congratulations, you are a winner");
        winScreen.add (winText);

        //creates the button the exit the screen
        winButton = new Button ("             Ok            ");
        winButton.addActionListener (this);
        winScreen.add (winButton);

        winScreen.setVisible (false);

        // //sets up the window that pops up when the person loses
        loseScreen = new Dialog (gameWindow, "GAME OVER");
        loseScreen.setSize (250, 150);
        loseScreen.setLocationRelativeTo (null);
        loseScreen.setLayout (layout);
        loseScreen.requestFocus ();

        //sets the text when the player loses
        loseText = new Label ("Sorry, you have lost");
        loseScreen.add (loseText);

        //creates the button the exit the screen
        loseButton = new Button ("             Ok            ");
        loseButton.addActionListener (this);
        loseScreen.add (loseButton);

        loseScreen.setVisible (false);

        //sets up the window that pops up when the person wants to see the instructions
        instructionScreen = new Dialog (gameWindow, "Instructions for PyramidSolitaire");
        instructionScreen.setSize (400, 500);
        instructionScreen.setLocationRelativeTo (null);
        instructionScreen.setLayout (layout);
        instructionScreen.setResizable (false);
        instructionScreen.requestFocus ();

        //creates the text for the instructions
        instructionWords = new Label [8];
        instructionWords [0] = new Label ("Hello, welcome to PyramidSolitaire           ");
        instructionScreen.add (instructionWords [0]);

        instructionWords [1] = new Label ("Any uncovered card can be chosen         ");
        instructionScreen.add (instructionWords [1]);

        instructionWords [2] = new Label ("You can choose up to two cards           ");
        instructionScreen.add (instructionWords [2]);

        instructionWords [3] = new Label ("Your goal is to add them up to 13            ");
        instructionScreen.add (instructionWords [3]);

        instructionWords [4] = new Label ("A = 1  ||  T = 10  ||  J = 11  ||  Q = 12  ||  K = 13            ");
        instructionScreen.add (instructionWords [4]);

        instructionWords [5] = new Label ("Your goal is to remove all the cards in the pyramid          ");
        instructionScreen.add (instructionWords [5]);

        instructionWords [6] = new Label ("The cards in the deck are not required, but can be used          ");
        instructionScreen.add (instructionWords [6]);

        instructionWords [7] = new Label ("^_^  Good Luck and Have Fun  ^_^                         ");
        instructionScreen.add (instructionWords [7]);

        //creates the button the exit the screen
        instructionButton = new Button ("             Ok            ");
        instructionButton.addActionListener (this);
        instructionScreen.add (instructionButton);

        instructionScreen.setVisible (false);


        restartReminder = new Dialog (gameWindow, "Warning");
        restartReminder.setSize (250, 150);
        restartReminder.setLocationRelativeTo (null);
        restartReminder.setLayout (layout);
        restartReminder.setResizable (false);
        restartReminder.requestFocus ();

        reminderText = new Label ("You have finished this game");
        restartReminder.add (reminderText);

        restartReminderOKButton = new Button ("    Ok    ");
        restartReminderOKButton.addActionListener (this);

        restartButton = new Button ("    New Game    ");
        restartButton.addActionListener (this);

        restartReminder.add (restartReminderOKButton);
        restartReminder.add (restartButton);

        restartReminder.setVisible (false);


        addMouseListener (this);
        addMouseMotionListener (this);
    } // init method


    public void paint (Graphics g)
    {
        pyramidsolitaire.drawPyramid (g);
        pyramidsolitaire.drawDeck (g);
        setBackground (iColor);
        g.setColor (Color.decode ("#ff00ff"));
        g.drawString ("You are at card " + pyramidsolitaire.getDeckCards () + " in the deck", 75, 100);
        if (pyramidsolitaire.getReDeals () >= 0)
        {
            g.drawString ("You have " + pyramidsolitaire.getReDeals () + " redeals left", 75, 125);
        }
        else
        {
            g.drawString ("You have now run out of redeals", 75, 125);
        }
    } // paint method


    //checks for any performed actions
    public void actionPerformed (ActionEvent e)
    {
        Object objSource = e.getSource ();

        //checks if the close window button for the win screen has been presed
        if (objSource == winButton)
        {
            winScreen.setVisible (false);
        }

        //checks if the close window button for the lose screen has been presed
        if (objSource == loseButton)
        {
            loseScreen.setVisible (false);
        }

        //checks if the close window button for the instruction screen has been presed
        if (objSource == instructionButton)
        {
            instructionScreen.setVisible (false);
        }

        //checks if the close window button for the reminder screen has been presed
        if (objSource == restartReminderOKButton)
        {
            restartReminder.setVisible (false);
        }

        //checks if the close window button for the restart has been chosen
        if (objSource == restartButton)
        {
            restartReminder.setVisible (false);
            pyramidsolitaire.delay (250);
            pyramidsolitaire = new PyramidSolitaireClass (difficulty);
            gameover = false;
            repaint ();
        }


    } //actionPermored method


    public boolean action (Event e, Object o)
    {
        /////////////////////////////////////// G A M E ///////////////////////////////////////////////////////////////////////
        if (e.target == newgame)
        {
            pyramidsolitaire.delay (250);
            pyramidsolitaire = new PyramidSolitaireClass (difficulty);
            gameover = false;
        }
        if (e.target == redeal)
        {
            pyramidsolitaire.delay (250);
            pyramidsolitaire.reDeal (g);
            gameover = false;
        }
        if (e.target == exit)
        {
            System.exit (0);
        }

        /////////////////////////////////////// L E V E L /////////////////////////////////////////////////////////////////////
        if (e.target == easy)
        {
            difficulty = 0;
        }
        else if (e.target == medium)
        {
            difficulty = 1;
        }
        else if (e.target == hard)
        {
            difficulty = 2;
        }
        else if (e.target == extreme)
        {
            difficulty = 3;
        }

        ///////////////////////////// B A C K G R O U N D    C O L O U R //////////////////////////////////////////////////////
        if (e.target == white)
        {
            iColor = Color.white;
        }
        if (e.target == blue)
        {
            iColor = Color.decode ("#3377ff");
        }
        if (e.target == lightgray)
        {
            iColor = Color.lightGray;
        }
        if (e.target == darkgray)
        {
            iColor = Color.darkGray;
        }
        if (e.target == orange)
        {
            iColor = Color.orange;
        }
        if (e.target == green)
        {
            iColor = Color.decode ("#008000");
        }

        ////////////////////////////////// I N S T R U C T I O N S //////////////////////////////////////////////////////////////////
        if (e.target == instructionpage)
        {
            instructionScreen.setVisible (true);
        }

        /////////////////////////////////////// D E F E A T /////////////////////////////////////////////////////////////////////
        if (e.target == concede || e.target == surrender)
        {
            gameover = true;
            loseScreen.setVisible (true);
        }

        repaint ();
        return true;
    }


    public void mousePressed (MouseEvent e)
    {
        //checks if they have surrendered the game
        if (gameover == false)
        {

            //checks if the card is able to be picked
            if (pyramidsolitaire.isPickable (pyramidsolitaire.whichCard (e.getX (), e.getY ())))
            {

                //if the card is the first one to be picked
                if (picked == 1)
                {
                    card1 = pyramidsolitaire.whichCard (e.getX (), e.getY ());
                    pyramidsolitaire.chooseCard (card1);
                    pyramidsolitaire.reDrawCard (g, card1);

                    //checks if the first card is a pair (13)
                    if (pyramidsolitaire.isPair (card1))
                    {
                        pyramidsolitaire.delay (100);

                        //creates a pair out of the card
                        pyramidsolitaire.createPair (card1, g);
                        flipped = false;
                        picked = 1;
                        card1 = 0;
                        repaint ();

                        //checks if the player has won
                        if (pyramidsolitaire.isWin () == true)
                        {
                            winScreen.setVisible (true);

                            gameover = true;
                        }
                    }

                    //if the card is from the deck
                    else if (card1 == 100)
                    {
                        flipped = true;
                        picked = 2;
                    }

                    //if the card is from the pyramid
                    else
                    {
                        picked = 2;
                    }
                }

                //if the card is the second on to be picked
                else if (picked == 2)
                {
                    picked = 1;

                    card2 = pyramidsolitaire.whichCard (e.getX (), e.getY ());
                    pyramidsolitaire.chooseCard (pyramidsolitaire.whichCard (e.getX (), e.getY ()));
                    pyramidsolitaire.reDrawCard (g, pyramidsolitaire.whichCard (e.getX (), e.getY ()));

                    //checks if the two cards are a pair
                    if (pyramidsolitaire.isPair (card1, card2) == true)
                    {
                        pyramidsolitaire.delay (100);
                        pyramidsolitaire.createPair (card1, card2, g);
                        flipped = false;
                        repaint ();

                        if (pyramidsolitaire.isWin () == true)
                        {
                            winScreen.setVisible (true);
                            gameover = true;
                        }
                    }

                    else
                    {
                        pyramidsolitaire.delay (100);

                        //unchooses the cards and resets it to default
                        pyramidsolitaire.unchooseCard (card1);
                        pyramidsolitaire.reDrawCard (g, card1);
                        pyramidsolitaire.unchooseCard (card2);
                        pyramidsolitaire.reDrawCard (g, card2);
                        flipped = false;
                    }
                    card1 = 0;
                    card2 = 0;
                }
            }

            //checks if the deck has been clicked to request another cards
            else if (pyramidsolitaire.flipCard (e.getX (), e.getY ()) && flipped == false)
            {
                pyramidsolitaire.nextCard ();
                repaint ();

            }
        }
        else if (gameover == true)
        {
            restartReminder.setVisible (true);
        }
        repaint ();
    }


    //unused methods for the mouse
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


