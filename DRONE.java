import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class DRONE extends CHARACTER{
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
	private int[] wallLocations;
    private String dir = R;
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
    private int playerX, playerY; //Player X and Y coordinates
    private boolean doesFloat = false;
    private boolean canFly    = false;
    private boolean canClimb  = false;
    private boolean canStagger= false;
    private WEAPON Weapon;
    //DYNAMIC:
    private int TIMEBUFFER = 0;

    public DRONE(String _Name){
        super(_Name);
        Name = _Name;
    }

    public void act(){
        super.act();
        //TIME{
        if(getStat("buffer",INT) > 0)
            TIMEBUFFER = (getStat("buffer",INT) - 1);
        else
            TIMEBUFFER = 0;
        //  }
        if(Time()){
            //ACTIONS
            PHASE();
        } else {
            //DELAY
            //does nothing
        }

    }

    public void PHASE(){

    }
	
	private checkNodeCollision(){
		
		
	}
	
	private void nodeGenerator(){
		if (getObjectsInRange(100, PLAYER.class) != null){
			playerLocator();
			SuperWorld world = (SuperWorld)getWorld();
			
		}
			
	}
	
    private void playerLocator(){
        SuperWorld world = (SuperWorld)getWorld();
        playerX = world.getPlayer().getX();
        playerY = world.getPlayer().getY();
    }
	
	private void wallLocator(){
		SuperWorld world = (SuperWorld)getWorld();
		wallLocations = world.getWalls();
		
	}
}
