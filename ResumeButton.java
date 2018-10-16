import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
/**
 * This button unpauses the game upon being clicked.
 * 
 * @author Chandler Clarke
 * @version 1.0
 */
public class ResumeButton extends PauseMenu
{
    //Variables
    private SuperWorld mySuperWorld; //Creates variable for super world
    public ResumeButton(SuperWorld sw){  //Passes Superworld into this class
         mySuperWorld = sw;
    }
    public void act() {
        if (Greenfoot.mouseClicked(this))
            mySuperWorld.unPause();
    }
}
