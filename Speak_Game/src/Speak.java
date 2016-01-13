
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
//import javafx.scene.image.Image;

import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.input.KeyEvent;




public class Speak extends Application 
{
	public Variables c = new Variables();
	
    public static void main(String[] args) 
    {
        launch(args);
    }

    @Override
    public void start(Stage gameStage) 
    {
        gameStage.setTitle( "Speak" );
        gameStage.setMaximized(true);
        gameStage.initStyle(StageStyle.UNDECORATED);


        Group root = new Group();
        Scene baseScene = new Scene( root );
        gameStage.setScene( baseScene );
        gameStage.show();
        
        Canvas canvas = new Canvas( gameStage.getWidth(), gameStage.getHeight() );
        root.getChildren().add( canvas );
        
        GraphicsContext gc = canvas.getGraphicsContext2D();
        
        GameLoop gameLoop = new GameLoop(this, gc, gameStage);
        gameLoop.start();
        
        //final long timeStart = System.currentTimeMillis();
        
        
        
        
    }
    
}


class Variables {
	public double circPosition;
	boolean endPress;
	
	public Variables(){
		circPosition = 0;
	}
}




