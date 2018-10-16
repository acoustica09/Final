import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class BULLET extends PROJECTILE{
    public BULLET(){
        super();
    }
    public BULLET(String _dir){
        super();
        setStat("direction",_dir);
    }
    public BULLET(String _dir, int _speed){
        super();
        setStat("direction",_dir);
        setStat("speed",_speed);
    }
    public void act(){
        if(getStat("lifetime",INT) <= 0){
            getWorld().removeObject(this);
        } else {
            trimWhite();
            if(Time()){
                //ACTIONS
                Shift(getStat("direction",STRING),getStat("speed",INT));
                setLocation(getX() + getStat("dx",INT), getY() + getStat("dy",INT));
                setStat("lifetime",getStat("lifetime",INT) - 1);
                HIT();
            } else {
                //DELAY
                //does nothing
            }
        }
    }    
}
