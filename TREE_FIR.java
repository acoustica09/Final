import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TREE_FIR here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TREE_FIR extends FOREGROUND
{
    public TREE_FIR(){
        super();
        int x = (Greenfoot.getRandomNumber(101) - 100);
        int y = (Greenfoot.getRandomNumber(101) - 100) + (x * 2);
        getImage().scale(getImage().getWidth() + x, getImage().getHeight() + y);
    }
    
    public void act(){
        // Add your action code here.
    }    
}
