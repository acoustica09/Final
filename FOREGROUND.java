import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class FOREGROUND here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FOREGROUND extends Actor
{
    public FOREGROUND(){
        trimWhite();
    }
    
    public void act() 
    {
        // Add your action code here.
    }    
    
    public void trimWhite(){
        int range = 35; //Should be in the range 0-255.
        GreenfootImage img = this.getImage();
        Color transparent = new Color(0, 0, 0, 0);
        for(int x = 0; x < img.getWidth(); x++){
            for(int y = 0; y < img.getHeight(); y++){
                Color color = img.getColorAt(x, y);
                if(color.getRed()   > 255 - range
                && color.getGreen() > 255 - range
                && color.getBlue()  > 255 - range)
                    img.setColorAt(x, y, transparent);
            }
        }
    }
}
