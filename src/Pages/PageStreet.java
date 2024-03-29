package Pages;

import GameProcessing.Speak;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.File;


public class PageStreet extends PageStory {
    private Image askDir;
    @FXML public ImageView background;
    @FXML public ImageView man;

    public PageStreet(Speak speak) {
        super(speak);
    }

    /**
     * initializes the scene
     */
    public void begin() {
        speak.getVars().setReturnPage(speak.getVars().STREET);
        getAssets();
        initialized = true;
    }

    /**
     * initializes assets for the scene
     */
    public void getAssets() {
        bg = new Image("file:" + getPicDir() + "street" + File.separator + "street_bg.png", getWidth(), getHeight(), false, true);
        askDir = new Image("file:" + getPicDir() + "street" + File.separator + "directions.png", getWidth() / 4, getHeight(), false, true);

    }


    /**
     * cleans up and ends the page
     */
    public void end() {
    }

    @Override
    public void handleLogic() {
    }

    @Override
    public void drawImages() {
        //getGC().drawImage(askDir, getWidth() * .75, 0);
    }

    @Override
    public void setEventHandlers() {
        getBaseScene().setOnMouseClicked(new MouseClick());
        getBaseScene().setOnKeyPressed(new PressEsc());
        getBaseScene().setOnMouseMoved(new MouseEnter());
    }

    @Override
    public void updateDescription() {
    }

    /**
     * Handler for the mouse hovering on items
     *
     */
    class MouseEnter implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent e) {
            if (isInteraction) {
                getInteractionHover(e);
            }
        }
    }

    /**
     * Handler for pressing End button
     */
    class MouseClick implements EventHandler<MouseEvent>{
        @Override
        public void handle(MouseEvent e)
        {
            if (isInteraction) {
                getInteractionChoice(e);
            }
        }
    }
}
