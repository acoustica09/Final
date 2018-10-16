import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;  //Collision to multiple objects
public class PROJECTILE extends Actor{
    public static final String STRING = "string";
    public static final int INT = 1;
    public static final boolean BOOLEAN = true;
    //FEILDS:
    private String dir = STRING;
    private boolean[] sur = new boolean[4];
    private boolean[] wet = new boolean[4];
    private boolean[] hot = new boolean[4];
    //STATS:
    private int LIFETIME = 20;
    private int damage   = 1; //Attack Power
    private int speed    = 1; //Speed
    private int dx = 0;
    private int dy = 0;
    private boolean wallsKills = false;
    private boolean waterKills = false;
    private boolean spikeKills = false;
    private boolean falls = false;
    private boolean floats= false;
    private int GRAVITY = 0;
    /**MAIN:
     *    
     * PROJECTILE()
     * act()
     * 
     **/
    public PROJECTILE(){
        
    }
    public void act(){
        
    }
    public boolean Time(){
        SuperWorld W = getWorldOfType(SuperWorld.class);
        if(W.isInInv() || W.isPaused())
            return false;
        return true;
    }
    
    
    /**MUTATORS && ACCESSORS:
     *    
     * setStat(String,int) || getStat(String)
     * setFall(boolean)    || getFall()
     * setFloat(boolean)   || getFloat()
     * setKill(Class)      || getKill(Class)
     * 
     **/
    public void setStat(String stat, String value){
        switch(stat){
            case "direction":
                dir = value;
            break;
            default:
                System.out.println("illegal argument to PROJECTILE.setStat(String,int)");
            break;
        }
    }
    public String getStat(String stat, String literal){
        switch(stat){
            case "direction":
                return dir;
            default:
                System.out.println("illegal argument to PROJECTILE.setStat(String,int)");
                return STRING;
        }
    }
    public void setStat(String stat, int value){
        switch(stat){
            case "lifetime":
                LIFETIME = value;
            break;
            case "damage":
                damage = value;
            break;
            case "speed":
                speed = value;
            break;
            case "dx":
                dx = value;
            break;
            case "dy":
                dy = value;
            break;
            default:
                System.out.println("illegal argument to PROJECTILE.setStat(String,int)");
            break;
        }
    }
    public int getStat(String stat, int literal){
        switch(stat){
            case "lifetime":
                return LIFETIME;
            case "damage":
                return damage;
            case "speed":
                return speed;
            case "dx":
                return dx;
            case "dy":
                return dy;
            default:
                System.out.println("illegal argument to PROJECTILE.getStat(String)");
                return INT;
        }
    }
    public void setFall(boolean _falls){
        falls = _falls;
    }
    public boolean getFall(){
        return falls;
    }
    public void setFloat(boolean _floats){
        floats = _floats;
    }
    public boolean getFloat(){
        return floats;
    }
    public void setKill(Class Type, boolean kills){
        if(Type == WALL.class)
            wallsKills = kills;
        else if(Type == WATER.class)
            waterKills = kills;
        else if(Type == SPIKE.class)
            spikeKills = kills;
    }
    public boolean getKill(Class Type){
        if(Type == WALL.class)
            return wallsKills;
        else if(Type == WATER.class)
            return waterKills;
        else if(Type == SPIKE.class)
            return spikeKills;
        else{
            System.out.println("illegal argument to CHARACTER.getKill(Class)");
            return false;
        }
    }
    
    
    public void HIT(){
        List<CHARACTER> hitList = getIntersectingObjects(CHARACTER.class); //cast may be necessary
        for(int i = 0; hitList.get(i) != null; ){
            CHARACTER temp = hitList.get(i);
            temp.setStat("health",temp.getStat("health",INT) - 1);
        }
    }
    
    
    /**MOVEMENT:
     * 
     * Shift(Actor,String direction,int distance)
     * Gravity(swim)
     * 
     **/
    public void Shift(String Dir, int Dis){
        sur = Walls(this);
        wet = Water(this);
        hot = Spike(this);
        //KILLS DYNAMIC
        if((wallsKills && (sur[0] || sur[1] || sur[2] || sur[3])) ||
           (waterKills && (wet[0] || wet[1] || wet[2] || wet[3])) ||
           (spikeKills && (hot[0] || hot[1] || hot[2] || hot[3])))
            LIFETIME = 0;
        //SLOWING
        Actor swim = getOneObjectAtOffset(0,0,WATER.class);
        if(swim != null)
            Dis--;
        //MOVEMENT;
        switch(Dir){
            case "up":
                dx = 0;
                dy = -Dis;
            break;
            case "left":
                dx = -Dis;
                dy = 0;
            break;
            case "right":
                dx = Dis;
                dy = 0;
            break;
            case "down":
                dx = 0;
                dy = Dis;
            break;
            case "upleft":
                dx = -Dis;
                dy = -Dis;
            break;
            case "upright":
                dx =  Dis;
                dy = -Dis;
            break;
            case "downleft":
                dx = -Dis;
                dy =  Dis;
            break;
            case "downright":
                dx = Dis;
                dy = Dis;
            break;
            default:
                dx = 0;
                dy = 0;
            break;
        }
    }
    public int Gravity(boolean swim){
        //GRAVITY DYNAMICS
        if(!sur[0] && !waterKills && floats && swim)
            GRAVITY = -1;
        else if(falls){
            if(!sur[3] && !waterKills && !floats && swim)
                GRAVITY =  1;
            else if(!sur[3])
                GRAVITY++;
        }else
            GRAVITY = 0;
        return GRAVITY;
    }
    
    
    /**COLLISION:
     * 
     * Walls(Actor)
     * Water(Actor)
     * Spike(Actor)
     * 
     **/
    public boolean[] Walls(Actor Who){
        boolean[] x = new boolean[4];
        Actor above = getOneObjectAtOffset(Who.getX(),  (Who.getY()-(Who.getImage().getHeight()/2)),  WALL.class);
        if(above != null)
            x[0] = true;
        Actor left = getOneObjectAtOffset((Who.getX()-(Who.getImage().getWidth()/2)),  Who.getY(),  WALL.class);
        if(left != null)
            x[1] = true;
        Actor right = getOneObjectAtOffset((Who.getX()+(Who.getImage().getWidth()/2)),  Who.getY(),  WALL.class);
        if(right != null)
            x[2] = true;
        Actor under = getOneObjectAtOffset(Who.getX(),  (Who.getY()+(Who.getImage().getHeight()/2)),  WALL.class);
        if(under != null)
            x[3] = true;
        return x;
    }
    public boolean[] Water(Actor Who){
        boolean[] x = new boolean[4];
        Actor above = getOneObjectAtOffset(Who.getX(),  (Who.getY()-(Who.getImage().getHeight()/2)),  WATER.class);
        if(above != null)
            x[0] = true;
        Actor left = getOneObjectAtOffset((Who.getX()-(Who.getImage().getWidth()/2)),  Who.getY(),  WATER.class);
        if(left != null)
            x[1] = true;
        Actor right = getOneObjectAtOffset((Who.getX()+(Who.getImage().getWidth()/2)),  Who.getY(),  WATER.class);
        if(right != null)
            x[2] = true;
        Actor under = getOneObjectAtOffset(Who.getX(),  (Who.getY()+(Who.getImage().getHeight()/2)),  WATER.class);
        if(under != null)
            x[3] = true;
        return x;
    }
    public boolean[] Spike(Actor Who){
        boolean[] x = new boolean[4];
        Actor above = getOneObjectAtOffset(Who.getX(),  (Who.getY()-(Who.getImage().getHeight()/2)),  SPIKE.class);
        if(above != null)
            x[0] = true;
        Actor left = getOneObjectAtOffset((Who.getX()-(Who.getImage().getWidth()/2)),  Who.getY(),  SPIKE.class);
        if(left != null)
            x[1] = true;
        Actor right = getOneObjectAtOffset((Who.getX()+(Who.getImage().getWidth()/2)),  Who.getY(),  SPIKE.class);
        if(right != null)
            x[2] = true;
        Actor under = getOneObjectAtOffset(Who.getX(),  (Who.getY()+(Who.getImage().getHeight()/2)),  SPIKE.class);
        if(under != null)
            x[3] = true;
        return x;
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
