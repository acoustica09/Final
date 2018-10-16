import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class WALL extends Actor{
    public WALL(int Lx, int Ly){
        getImage().scale(Lx,Ly);
    }
    
    public WALL(int Lx, int Ly, int Tilt){
        getImage().scale(Lx,Ly);
        turn(Tilt);
    }
    
    public void act(){
        // Add your action code here.
    }    
}
