import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This button ends the game upon being clicked.
 * 
 * @author Chandler Clarke 
 * @version 1.0
 */
public class QuitButton extends PauseMenu
{
    /**
     * Act - do whatever the QuitButton wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if (Greenfoot.mouseClicked(this))
            Greenfoot.stop();
    }    
}
