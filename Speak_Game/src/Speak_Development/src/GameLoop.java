import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class GameLoop extends AnimationTimer {
	protected Speak speak;
	private GraphicsContext gc;
	private Stage gameStage;
    
	public GameLoop(Speak speak, GraphicsContext gc, Stage gameStage){
        this.speak = speak;
        this.gc = gc;
        this.gameStage = gameStage;
        
        
    }
    @Override
    public void handle(long now) {
    	gameStage.getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
    		@Override
    		public void handle(KeyEvent event) {
    			if (event.getCode() == KeyCode.ESCAPE){
    				stop();
    				Platform.exit();
    			}
    		}
    	});
    	gc.clearRect(0, 0, gameStage.getWidth(),gameStage.getHeight());
		speak.c.circPosition += 2;
		if (speak.c.circPosition > gameStage.getWidth()){
			speak.c.circPosition = -100;
		}
		gc.fillOval(speak.c.circPosition,gameStage.getHeight() /2 ,100 , 100);
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
}
