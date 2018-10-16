import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java. util. Arrays; //Max index

public class CHARACTER extends Actor{
    private static final String UN = "null";
    private static final String STRING = "string";
    private static final int INT = 1;
    private static final boolean BOOLEAN = true;
    //DIRECTION:
    private static String U = "up";
    private static String D = "down";
    private static String L = "left";
    private static String R = "right";
    private static String UL= "upleft";
    private static String UR= "upright";
    private static String DL= "downleft";
    private static String DR= "downright";
    private static String S = "stop";
    private String dir = S;
    private int dx = 0;
    private int dy = 0;
    private String face = R;
    private String anime = UN;
    private boolean[] sur = new boolean[4];
    private boolean[] wet = new boolean[4];
    private boolean[] hot = new boolean[4];
    //STATS:
    private String Name = UN;
    private int LIVES = 1;
    private int HP = 1; //health points
    private int MP = 0; //special move points
    private int AR = 0; //armor rating
    private int MS = 1; //move speed
    private int JH = 1; //jump height
    private int BU = 1; //buoyancy
    private boolean canFly    = false;
    private boolean canClimb  = false;
    private boolean canStagger= true;
    private WEAPON Weapon;
    private static final int inventorySize = 4;
    private WEAPON[] inventory = new WEAPON[inventorySize]; //EXCLUSIVE TO PLAYER
    //PHASE:
    public boolean isFlying    = false;
    public boolean isClimbing  = false;
    public boolean isStaggered = false;
    //GRAVITY:
    private int GRAVITY = 0;
    private int JT = 0; //jump timer
    private int GT = 0; //gravity timer
    private int GB = 24;//gravity buffer
    private boolean normalGRAVITY = true;
    /**MAIN:
     *    
     * CHARACTER(NAME)
     * act()
     * Time()
     * 
     **/
    public CHARACTER(String _Name){
        Name = _Name;
    }
    public void act(){
        if(LIVES <= 0 || (getY() + getImage().getHeight()/2 >= getWorld().getHeight())){
            DEATH(this);
        }else{
            trimWhite();
            if(Weapon != null)
                Weapon.setLocation(getX(), getY());
        }
    }
    public boolean Time(){
        SuperWorld W = getWorldOfType(SuperWorld.class);
        if(W.isInInv() || W.isPaused())
            return false;
        return true;
    }
    
    
    /**MUTATORS & ACCESSORS:
     *    
     * setStat(String, auto)
     * getStat(String, auto)
     * setWeapon(WEAPON)
     * getWeapon()
     * 
     **/
    public void setStat(String stat, String value){
        switch(stat){
            case "name":
                Name = value;
            break;
            case "dir":
                dir = value;
            break;
            case "direction":
                dir = value;
            break;
            case "anime":
                anime = value;
            break;
            default:
                System.out.println("illegal argument to CHARACTER.setStat(String,String)");
            break;
        }
    }
    public String getStat(String stat,String literal){
        switch(stat){
            case "name":
                return Name;
            case "dir":
                return dir;
            case "direction":
                return dir;
            case "anime":
                return anime;
            default:
                System.out.println("illegal argument to CHARACTER.setStat(String,String)");
                return "";
        }
    }
    public void setStat(String stat, int value){
        switch(stat){
            case "lives":
                LIVES = value;
            break;
            case "HP":
                HP = value;
            break;
            case "health":
                HP = value;
            break;
            case "MP":
                MP = value;
            break;
            case "special":
                MP = value;
            break;
            case "AR":
                AR = value;
            break;
            case "armor":
                AR = value;
            break;
            case "MS":
                MS = value;
            break;
            case "speed":
                MS = value;
            break;
            case "JH":
                JH = value;
            break;
            case "jump":
                JH = value;
            break;
            case "BU":
                BU = value;
            break;
            case "buoyancy":
                BU = value;
            break;
            default:
                System.out.println("illegal argument to CHARACTER.setStat(String,int)");
            break;
        }
    }
    public int getStat(String stat,int literal){
        switch(stat){
            case "lives":
                return LIVES;
            case "HP":
                return HP;
            case "health":
                return HP;
            case "MP":
                return MP;
            case "special":
                return MP;
            case "AR":
                return AR;
            case "armor":
                return AR;
            case "MS":
                return MS;
            case "speed":
                return MS;
            case "JH":
                return JH;
            case "jump":
                return JH;
            case "BU":
                return BU;
            case "buoyancy":
                return BU;
            default:
                System.out.println("illegal argument to CHARACTER.setStat(String,int)");
                return 0;
        }
    }
    public void setStat(String stat, boolean value){
        switch(stat){
            case "fly":
                canFly = value;
            break;
            case "climb":
                canClimb = value;
            break;
            case "stagger":
                canStagger = value;
            break;
            default:
                System.out.println("illegal argument to CHARACTER.setStat(String,boolean)");
            break;
        }
    }
    public boolean getStat(String stat,boolean literal){
        switch(stat){
            case "fly":
                return canFly;
            case "climb":
                return canClimb;
            case "stagger":
                return canStagger;
            default:
                return false;
        }
    }
    public WEAPON getWeapon(){
        return Weapon;
    }
    public void setWeapon(WEAPON _Weapon){
        Weapon = _Weapon;
    }
    public WEAPON getWeapon(int i){
        return inventory[i];
    }
    public void setWeapon(int i, WEAPON _Weapon){
        inventory[i] = _Weapon;
        Weapon = _Weapon;
    }
    
    
    /**MOVEMENT:
     * 
     * Tele(Actor,x,y)
     * Shift(Actor,String direction,int distance)
     * Gravity(inWater?)
     * 
     **/
    public void Tele(Actor Who, int dx, int dy){
        setLocation((getX() + dx), (getY() + dy));
    }
    public void Shift(Actor Who, String Dir, int Dis){
        if(Who != null){
            dx = 0;
            dy = 0;
            if(Dir == R || Dir == UR || Dir == DR)
                face = R;
            else if(Dir == L || Dir == UL || Dir == DL)
                face = L;
            //SLOWING
            Actor swim = getOneObjectAtOffset(0,0,WATER.class);
            Actor climb= getOneObjectAtOffset(0,0,ROPE.class);
            if(!(climb != null))
                isClimbing = false;
            if(isClimbing || (canFly && !isFlying) || (wet[3] && (BU > 0 || (Dir == U || Dir == UL || Dir == UR))))
                Dis--;
            //SPIKE DYNAMICS
            if(hot[0] || hot[1] || hot[2] || hot[3])
                isStaggered = true;
            //MOVEMENT
            if(canStagger && isStaggered)
                STAGGER(Dis * 10);
            else if(swim != null && BU >= 0)
                SWIM(Dir, Dis);
            else if(isFlying)
                FLY(Dir, Dis);
            else if(isClimbing)     
                CLIMB(Dir, Dis);
            else if(!sur[3])
                FALL(Dir, Dis);
            else if(Dir == U || Dir == UL || Dir == UR)
                JUMP(Dir, Dis);
            else if(Dir == L || Dir == R)
                RUN(Dir, Dis);
            else if(Dir == D)
                DUCK();
            else if(Dir == DL || Dir == DR)
                ROLL(Dir, Dis);
            else
                STAND();
            //GRAVITY DYNAMICS
            dy += Gravity(swim != null);
            //COLLISION
            sur = toWalls();
            wet = toWater();
            hot = toSpike();
            if(sur[0] && (dy < 0))
                dy = 0;
            if(sur[1] && (dx < 0))
                dx = 0;
            if(sur[2] && (dx > 0))
                dx = 0;
            if(sur[3] && (dy > 0))
                dy = 0;
            Tele(this,dx,dy);
            if(getOneObjectAtOffset(0,2 * getImage().getHeight()/5, WALL.class) != null)
                Tele(this,0,-2);
        }
    }
    public int Gravity(boolean swim){
        //GRAVITY DYNAMICS
        if(normalGRAVITY){
            if(isClimbing || isFlying || (swim && BU == 0)){
                GT = 0;
                GRAVITY = 0;
            }
            else if(!sur[0] && swim && BU > 0)
                GRAVITY = -BU;
            else if(!sur[3] && wet[3] && BU < 0)
                GRAVITY = -BU;
            else if(!sur[3]){
                if(GT % GB == 0)
                    GRAVITY++;
            } else {
                GT = 0;
                GRAVITY = 0;
            }
        }
        return GRAVITY;
    }
    
    
    /**LOCOMOTIONS:     animatable
     * 
     * SPAWN(x,y)
     * STAND()
     * JUMP(String direction,int distance)
     * FALL(String direction, int distance)
     * RUN(String direction,int distance)
     * ROLL(String direction,int distance)
     * FLY(String direction,int distance)
     * SWIM(String direction,int distance)
     * CLIMB(String direction,int distance)
     * DUCK()
     * ATTACK(String direction)
     * BLOCK()
     * CRAFT(Actor,x,y)
     * STAGGER(int distance)
     * DEATH(Actor Who)    #define Who >> replace player upon death
     * 
     **/
    public void SPAWN(int x, int y){
        anime = "spawn";
        setLocation(x,y);
    }
    public void STAND(){
        anime = "stand";
    }
    public void JUMP(String Dir, int Dis){
        anime = "jump";
        JT = JH;
        if(Dir == U){
                dx = 0;
        }
        else if(Dir == UL){
                dx = -Dis;
        }
        else if(Dir == UR){
                dx =  Dis;
        }
        dy = -(Dis);
    }
    public void FALL(String Dir, int Dis){
        if(Dir == R || Dir == UR || Dir == DR)
            RUN(R, Dis + 2);
        else if(Dir == L || Dir == UL || Dir == DL)
            RUN(L, Dis + 2);
            JT--;
        if(JT < 0)
            JT = 0;
        dy = -JT;
        anime = "fall";
    }
    public void RUN(String Dir, int Dis){
        anime = "run";
        if(Dir == L){
                dx = -Dis;
        } 
        else if(Dir == R){
                dx = Dis;
        }
        dy = 0;
    }
    public void ROLL(String Dir, int Dis){
        anime = "roll";
        if(Dir == DL){
                dx = -Dis;
        }
        else if(Dir == DR){
                dx = Dis;
        }
        dy = 0;
    }
    public void FLY(String Dir, int Dis){
        anime = "fly";
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
            break;
        }
    }
    public void SWIM(String Dir, int Dis){
        FLY(Dir, Dis);
        anime = "swim";
    }
    public void CLIMB(String Dir, int Dis){
        FLY(Dir, Dis);
        anime = "climb";
    }
    public void DUCK(){
        anime = "duck";
    }
    public void STAGGER(int Dis){
        anime = "stagger";
        if(hot[0])
            dy = Dis;
        else if(hot[3])
            dy = -Dis;
        if(hot[1])
            dx =  Dis;
        else if(hot[2])
            dx = -Dis;
        if(dx >= 0)
            face = R;
        else
            face = L;
        isStaggered = false;
    }
    public void DEATH(Actor Who){
        anime = "death";
        this.getWorld().removeObject(Who);
    }
    
    
    /**ITEM MANIPULATION:
     * 
     * ATTACK(direction)
     * SPECIAL()
     * INTERACT()
     * CRAFT(Actor, x, y)
     * 
     **/
    public void ATTACK(String Dir){
        anime = "attack";
        if(Weapon != null){
            if(Dir == S)
                Weapon.STRIKE(face);
            else
                Weapon.STRIKE(Dir);
        }
    }
    public void SPECIAL(){
        anime = "block";
    }
    public void INTERACT(){
        anime = "interact";
    }
    public void CRAFT(Actor Who, int x, int y){
        anime = "craft";
        getWorld().addObject(Who,x,y);
    }
    
    
    /**SURROUNDINGS:
     * 
     * Walls() || toWalls()
     * Water() || toWater()
     * Spike() || toSpike()
     * 
     **/
    public boolean[] Walls(){
        boolean[] x = new boolean[4];
        Actor above = getOneObjectAtOffset(0,(0-(getImage().getHeight()/2)), WALL.class);
        if(above != null)
            x[0] = true;
        Actor left =  getOneObjectAtOffset(0-(getImage().getWidth()/2),0, WALL.class);
        if(left != null)
            x[1] = true;
        Actor right = getOneObjectAtOffset(0+(getImage().getWidth()/2),0, WALL.class);
        if(right != null)
            x[2] = true;
        Actor under = getOneObjectAtOffset(0,(0+(getImage().getHeight()/2)), WALL.class);
        if(under != null)
            x[3] = true;
        return x;
    }
    public boolean[] toWalls(){
        boolean[] x = new boolean[4];
        Actor above = getOneObjectAtOffset(dx,(dy-(getImage().getHeight()/2)), WALL.class);
        if(above != null)
            x[0] = true;
        Actor left =  getOneObjectAtOffset(dx-(getImage().getWidth()/2),dy, WALL.class);
        if(left != null)
            x[1] = true;
        Actor right = getOneObjectAtOffset(dx+(getImage().getWidth()/2),dy, WALL.class);
        if(right != null)
            x[2] = true;
        Actor under = getOneObjectAtOffset(dx,(dy+(getImage().getHeight()/2)), WALL.class);
        if(under != null)
            x[3] = true;
        return x;
    }
    public boolean[] Water(){
        boolean[] x = new boolean[4];
        Actor above = getOneObjectAtOffset(0,(0-(getImage().getHeight()/2)), WATER.class);
        if(above != null)
            x[0] = true;
        Actor left =  getOneObjectAtOffset(0-(getImage().getWidth()/2),0, WATER.class);
        if(left != null)
            x[1] = true;
        Actor right = getOneObjectAtOffset(0+(getImage().getWidth()/2),0, WATER.class);
        if(right != null)
            x[2] = true;
        Actor under = getOneObjectAtOffset(0,(0+(getImage().getHeight()/2)), WATER.class);
        if(under != null)
            x[3] = true;
        return x;
    }
    public boolean[] toWater(){
        boolean[] x = new boolean[4];
        Actor above = getOneObjectAtOffset(dx,(dy-(getImage().getHeight()/2)), WATER.class);
        if(above != null)
            x[0] = true;
        Actor left =  getOneObjectAtOffset(dx-(getImage().getWidth()/2),dy, WATER.class);
        if(left != null)
            x[1] = true;
        Actor right = getOneObjectAtOffset(dx+(getImage().getWidth()/2),dy, WATER.class);
        if(right != null)
            x[2] = true;
        Actor under = getOneObjectAtOffset(dx,(dy+(getImage().getHeight()/2)), WATER.class);
        if(under != null)
            x[3] = true;
        return x;
    }
    public boolean[] Spike(){
        boolean[] x = new boolean[4];
        Actor above = getOneObjectAtOffset(0,(0-(getImage().getHeight()/2)), SPIKE.class);
        if(above != null)
            x[0] = true;
        Actor left =  getOneObjectAtOffset(0-(getImage().getWidth()/2),0, SPIKE.class);
        if(left != null)
            x[1] = true;
        Actor right = getOneObjectAtOffset(0+(getImage().getWidth()/2),0, SPIKE.class);
        if(right != null)
            x[2] = true;
        Actor under = getOneObjectAtOffset(0,(0+(getImage().getHeight()/2)), SPIKE.class);
        if(under != null)
            x[3] = true;
        return x;
    }
    public boolean[] toSpike(){
        boolean[] x = new boolean[4];
        Actor above = getOneObjectAtOffset(dx,(dy-(getImage().getHeight()/2)), SPIKE.class);
        if(above != null)
            x[0] = true;
        Actor left =  getOneObjectAtOffset(dx-(getImage().getWidth()/2),dy, SPIKE.class);
        if(left != null)
            x[1] = true;
        Actor right = getOneObjectAtOffset(dx+(getImage().getWidth()/2),dy, SPIKE.class);
        if(right != null)
            x[2] = true;
        Actor under = getOneObjectAtOffset(dx,(dy+(getImage().getHeight()/2)), SPIKE.class);
        if(under != null)
            x[3] = true;
        return x;
    }
    
    
    /**ANIMATIONS:
     * 
     * Anime(IMG)
     * Anime(String)
     * trimWhite()
     * 
     **/
    public void Anime(GreenfootImage IMG){
        setImage(IMG);
        trimWhite();
    }
    public void Anime(String file){
        setImage(file);
        trimWhite();
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
    public void vanish(Actor Who){
        int range = 255; //Should be in the range 0-255.
        GreenfootImage img = Who.getImage();
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
