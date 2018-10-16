import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class NODE extends Actor{
    
    private int playerX, playerY;
    public NODE(){
        
    }
    public void act(){
        //check for player at A, B
        playerLocator();
    }   
    
    public void playerLocator(){
        SuperWorld world = (SuperWorld)getWorld();
        playerX = world.getPlayer().getX();
        playerY = world.getPlayer().getY();
		turnTowards(playerX, playerY);
    }
}
