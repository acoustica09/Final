import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class SPIKE extends Actor{
    private static boolean isFire = false;
    public SPIKE(int Lx, int Ly){
        Scale(getImage(),Lx,Ly);
        trimWhite();
    }
        
    public void act(){
        if(isFire && isTouching(WATER.class))
            getWorld().removeObject(this);
    }
    
    public void Scale(GreenfootImage IMG, int Lx, int Ly){
        GreenfootImage newIMG = new GreenfootImage(Lx,Ly);
        for(int x = 0; x < newIMG.getWidth(); x++){
            for(int y = 0; y < newIMG.getHeight(); y++){
                Color color = IMG.getColorAt(x % IMG.getWidth(),y % IMG.getHeight());
                newIMG.setColorAt(x,y,color);
            }
        }
        setImage(newIMG);
    }
    
    public void trimWhite(){
        int range = 15; //Should be in the range 0-255.
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
