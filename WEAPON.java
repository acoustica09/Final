import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class WEAPON extends Actor{
    private static final String UN = "null";
    private static final String STRING = "lol";
    private static final int INT = 1;
    private static final boolean BOOLEAN = true;
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
    private int USES = 1;
    private int speed = 0; //attack speed
    private int delay = 0; //attack delay
    private String img = UN;
    private PROJECTILE Projectile;
    /**MAIN:
     *    
     * WEAPON(NAME)
     * act()
     * 
     **/
    public WEAPON(String _Name){
        Name = _Name;
    }
    public void act(){
        if(USES <= 0){
            getWorld().removeObject(this);
        } else {
            trimWhite();
        }
    }
    
    
    public void setProjectile(PROJECTILE x){
        Projectile = x;
    }
    public PROJECTILE getProjectile(){
        return Projectile;
    }
    
    
    public void STRIKE(String Dir){
        PROJECTILE newP = Projectile;
        if(newP != null){
            newP.setStat("direction",Dir);
            switch(Dir){
                case "up":
                    getWorld().addObject(newP, getX(), getY() - (getImage().getHeight()/2));
                break;
                case "upleft":
                    getWorld().addObject(newP, getX() - (getImage().getWidth()/2), getY() - (getImage().getHeight()/2));
                break;
                case "upright":
                    getWorld().addObject(newP, getX() + (getImage().getWidth()/2), getY() - (getImage().getHeight()/2));
                break;
                case "left":
                    getWorld().addObject(newP, getX() - (getImage().getWidth()/2), getY());
                break;
                case "right":
                    getWorld().addObject(newP, getX() + (getImage().getWidth()/2), getY());
                break;
                case "down":
                    getWorld().addObject(newP, getX(), getY() + (getImage().getHeight()/2));
                break;
                case "downleft":
                    getWorld().addObject(newP, getX() - (getImage().getWidth()/2), getY() + (getImage().getHeight()/2));
                break;
                case "downright":
                    getWorld().addObject(newP, getX() + (getImage().getWidth()/2), getY() + (getImage().getHeight()/2));
                break;
                default:
                    System.out.println("illegal argument to WEAPON.STRIKE(String)");
                break;
            }
        }
    }
    
    
    public void trimWhite(){
        int range = 15; //Should be in the range 0-255.
        GreenfootImage img = this.getImage();
        Color transparent = new Color(0, 0, 0, 0);
        for(int x = 0; x < img.getWidth(); x++)
        {
            for(int y = 0; y < img.getHeight(); y++)
            {
                Color color = img.getColorAt(x, y);
                if(color.getRed()   > 255 - range
                && color.getGreen() > 255 - range
                && color.getBlue()  > 255 - range)
                    img.setColorAt(x, y, transparent);
            }
        }
    }
}
