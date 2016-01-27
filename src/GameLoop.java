/**
* Handles the GameLoop
*/

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;


public class GameLoop extends AnimationTimer {
	//make these variables of Speak so they dont have
	//to be passed as parameters.
	protected Speak speak;
	private GraphicsContext gc;
	private Stage gameStage;
    
	public GameLoop(Speak speak){
        this.speak = speak;
        this.gc = speak.gc;
        this.gameStage = speak.gameStage;
    }

    @Override
    public void handle(long now) {
		//make sure canvas is clear
    	gc.clearRect(0, 0, gameStage.getWidth(),gameStage.getHeight());
		checkSceneChange();
		speak.vars.getCurrentPage().update();
		gameStage.show();

	}

    @Override
    public void start(){
        super.start();
    }
    @Override
    public void stop(){
        super.stop();
    }


	/**
	 * Checks if the scene has changed since the update, and sets up the new scene
	 */
	private void checkSceneChange() {
		if (!speak.vars.getCurrentPage().initialized) speak.vars.getCurrentPage().begin();
	}

}
