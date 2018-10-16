import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
/**
 * The superclass of all worlds where an inventory is required.
 * 
 * @author Chandler Clarke
 * @version 1.0
 */
public class SuperWorld extends World
{
    public boolean invStatus = false;
    public boolean pauseStatus = false;
    public int counter;
    private PLAYER Player;
	private int[] wallLocations;
    public SuperWorld(){
        super(600,600,1);
        counter = 0;
    }

    public void act(){
       if (counter(5)){
           invSwitcher();
           checkIfUserWantsToTogglePause();
       }
    }
    //Switches between inv and player view
    public void invSwitcher(){
        if (Greenfoot.isKeyDown("i") && invStatus == false && pauseStatus == false){
            Button button = new Button();
            addObject(button, 300, 250);
            InventoryBackground inv = new InventoryBackground();
            addObject(inv, 300, 250);
            ItemBackground itemb = new ItemBackground();
            addObject(itemb, 300, 250);
            invStatus = true;
            setPaintOrder(Button.class, ItemBackground.class, InventoryBackground.class);
        }
        else if (Greenfoot.isKeyDown("i") && invStatus == true){
            List<Inventory> inventory = getObjects(Inventory.class);
            for (int i = 0; i<inventory.size(); i++){
                Actor who = inventory.get(i);
                removeObject(who);
            }
            invStatus = false;
        }
    }
    //Unpauses the game, triggered by unpause button or escape key.
    public void unPause(){
        if (pauseStatus == true){
                List<PauseMenu> pauseMenu = getObjects(PauseMenu.class);
                for (int i = 0; i<pauseMenu.size(); i++){
                    Actor who = pauseMenu.get(i);
                    removeObject(who);
                }
                pauseStatus = false;
        }
    }
    //Switches between pause menu and player view
    public void checkIfUserWantsToTogglePause(){
        if (Greenfoot.isKeyDown("escape")){
            if (pauseStatus == true)
                unPause();
            else if (pauseStatus == false && invStatus == false){
                PauseBackground pause = new PauseBackground();
                addObject(pause, 300, 250);
                QuitButton quit = new QuitButton();
                addObject (quit, 300, 400);
                ResumeButton resume = new ResumeButton(this);
                addObject(resume, 300, 200);
                setPaintOrder(ResumeButton.class, QuitButton.class, PauseBackground.class);
                pauseStatus = true;
            }
        }
    }
    //Creates delay between pausing/unpausing and switching in and out of the inventory.
    public boolean counter(int delay){
        counter++;
        if (counter > 1000)
            counter = 500;
        if (counter > delay){
            counter = 0;
            return true;
        }
        return false;
    }
    //For checking if player is in Inv or Pause
    public boolean isInInv(){
        return invStatus;
    }
    public boolean isPaused(){
        return pauseStatus;
    }
    public PLAYER getPlayer(){
        return Player;
    }
	public int[] getWalls(){
		getObjects(WALL.class)
	}
}

