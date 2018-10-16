import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class PLAYER extends CHARACTER{
    private static final String UN = "null";
    private static final String STRING = "string";
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
    private String dir = R;
    //STATS:
    private String Name = UN;
    //DYNAMIC:
    private int DelayButton = 0;
    //KEYS:
    private String w = "up";
    private String a = "left";
    private String s = "down";
    private String d = "right";
    private String sh= "shift";
    private String sp= "space";
    private String f = "f"; //(fire) or attack
    private String g = "g"; //(guard) or special
    private String e = "e"; //(equipt) or interact
    /**MAIN:
     *    
     * CHARACTER(NAME,STATS[],SKILL[],WEAPON)
     * act()
     * 
     **/
    public PLAYER(String _Name){
        super(_Name);
        Name = _Name;
    }
    public void act(){
        if(getStat("lives",INT) <= 0 || (getY() + getImage().getHeight()/2 >= getWorld().getHeight())){
            //DEATH(this);
        }else{
            super.act();
            if(Time()){
                //ACTIONS
                INPUT();
            } else {
                //DELAY
                //does nothing
            }
        }
    }
    
    
    /**INPUT:
     *    
     * Key(String)
     * INPUT()
     * setKey(String, String)
     * 
     **/
    public boolean Key(String _key){
        if(Greenfoot.isKeyDown(_key))
            return true;
        return false;
    }
    public void INPUT(){
        if(DelayButton != 0)
            DelayButton--;
        //STANDARD INPUT
        if(!(Key(w) && Key(s))){
            if(Key(w) && Key(a) && Key(d))
                dir = U;
            else if(Key(w) && Key(a))
                dir = UL;
            else if(Key(w) && Key(d))
                dir = UR;
            else if(Key(s) && Key(a) && Key(d))
                dir = D;
            else if(Key(s) && Key(a))
                dir = DL;
            else if(Key(s) && Key(d))
                dir = DR;
            else if(Key(w))
                dir = U;
            else if(Key(a))
                dir = L;
            else if(Key(s))
                dir = D;
            else if(Key(d))
                dir = R;
            else
                dir = S;
        }
        else
            dir = S;
        //SPECIAL INPUT    
        if(Key(f))
            ATTACK(dir);
        else if(Key(sp)){
            if(DelayButton == 0){
                if(getOneObjectAtOffset(0,0,ROPE.class) != null){
                    if(getStat("climb",BOOLEAN)){
                        isClimbing = (!isClimbing);
                        isFlying = false;
                    }
                } else {
                    if(getStat("fly",BOOLEAN)){
                        isFlying = (!isFlying);
                        isClimbing = false;
                    }
                }
                DelayButton = 24;
            }
        }
        //SPEED
        if(!Key(sh))    
            Shift(this,dir,getStat("speed",INT));
        else
            Shift(this,dir,getStat("speed",INT) + 2);
    }
    public void setKey(String comm, String key){
        if(comm == "w" || comm == "up")
            w = key;
        else if(comm == "a" || comm == "left")
            a = key;
        else if(comm == "s" || comm == "down")
            s = key;
        else if(comm == "d" || comm == "right")
            d = key;
        else if(comm == "sh"|| comm == "shift")
            sh = key;
        else if(comm == "sp"|| comm == "space")
            sp = key;
        else if(comm == "f" || comm == "fire")
            f = key;
        else if(comm == "g" || comm == "special")
            g = key;
        else if(comm == "e" || comm == "interact")
            e = key;
    }   
}