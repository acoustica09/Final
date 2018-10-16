import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

public class DEMOWORLD extends SuperWorld{
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
    
    PLAYER Juan;
    RANGE gun;
    int TIME = 0;
    public DEMOWORLD(){
        super();
        
        //Step 1: Make a Player
            Juan = new PLAYER("Juan");
            addObject(Juan,300,200);
        //Step 2:
            prepare();
        //Step 3:
            givePlayerStats();
        //Step 4:
            spawnWeapon();
            setPaintOrder(WALL.class,WATER.class,SPIKE.class,PROJECTILE.class,WEAPON.class,CHARACTER.class,ROPE.class);
    }
    public void act(){
        TIME++;
        //if(Juan.getWeapon(0) != gun)
          //  if(Juan.intersects(gun))
          ///      Juan.setWeapon(0,gun);
        //if(TIME % 100 == 0){
            //addObject(new BULLET(U,3), 100, 100);
            //addObject(new BULLET(UL,2), 100, 100);
            //addObject(new BULLET(UR,2), 100, 100);
            //addObject(new BULLET(L,3), 100, 100);
            //addObject(new BULLET(R,3), 100, 100);
            //addObject(new BULLET(D,3), 100, 100);
            //addObject(new BULLET(DL,2), 100, 100);
            //addObject(new BULLET(DR,2), 100, 100);
          //  addObject(new SWIPE(R,15,45),Juan.getX(),Juan.getY());
        //}
    }
    
    public void prepare(){
        //Step 2: Make a Floor
        WALL Floor = new WALL(600,50);
        addObject(Floor,300,375);
        WATER Sea1 = new WATER(200,250);
        addObject(Sea1,475,225);
        ROPE Rope1 = new ROPE(100,300);
        addObject(Rope1,50,200);
    }
    public void givePlayerStats(){
        //Step 3: Give the Player Stats
        Juan.setStat("jump",15);
        Juan.setStat("speed",3);
        Juan.setStat("climb",true);
        Juan.setStat("buoyancy",0);
    }
    public void spawnWeapon(){
        //Step 4: Create / Spawn a Weapon
        gun = new RANGE("pistol");
        addObject(gun,250,325);
        BULLET bullet = new BULLET();
        gun.setProjectile(bullet);
    }
}
