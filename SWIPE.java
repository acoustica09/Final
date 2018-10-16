import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class SWIPE extends PROJECTILE{
    public SWIPE(){
        super();
    }
    public SWIPE(String _dir){
        super();
        setStat("direction",_dir);
    }
    public SWIPE(String _dir, int _wid, int _len){
        super();
        setStat("direction",_dir);
        getImage().scale(_wid,_len);
    }
    public void act(){
        if(getStat("lifetime",INT) <= 0){
            getWorld().removeObject(this);
        } else {
            trimWhite();
            if(Time()){
                //ACTIONS
                setStat("lifetime",getStat("lifetime",INT) - 1);
                HIT();
            } else {
                //DELAY
                //does nothing
            }
        }
    }
    public void offSet(int Dis){
        String _dir = getStat("direction",STRING);
        int dx, dy;
        switch(_dir){
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
}
