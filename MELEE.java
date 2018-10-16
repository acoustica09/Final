import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class MELEE extends WEAPON{
    private static String UN = "null";
    //DIRECTION:
    private static String U = "up";
    private static String D = "down";
    private static String L = "left";
    private static String R = "right";
    private static String UL = "upleft";
    private static String UR = "upright";
    private static String DL = "downleft";
    private static String DR = "downright";
    private static String S = "stop";
    private String face = R;
    //STATS:
    private String Name = UN;
    private int USES = 0;
    private int speed = 0; //attack speed
    private int delay = 0; //attack delay
    private SWIPE Projectile;
    private int AB = 0; //action buffer
    public MELEE(String _Name){
        super(_Name);
        Name = _Name;
    }
    public void act(){
        super.act();
    }
    
    
    public void setProjectile(SWIPE x){
        Projectile = x;
    }
    public SWIPE getProjectile(){
        return Projectile;
    }
}
