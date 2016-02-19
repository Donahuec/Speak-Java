
import javafx.event.EventHandler;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

import java.io.File;
import java.util.Iterator;

public class PageAlarm extends PageStory {
    public GameText text;
    public String alarmDescription;
    public boolean choiceMade;
    public Rectangle alarmClick;


    public PageAlarm(Speak speak){
        super(speak);
        choiceMade = false;
    }

    /**
     * initializes the scene
     */
    public void begin() {
        speak.getVars().setReturnPage(speak.getVars().ALARM);
        getAssets();
        initialized = true;
        isInteraction = false;
    }

    /**
     * initializes assets for the scene
     */
    public void getAssets() {
        text = new GameText(getTextDir() + "alarm.xml");

        alarmDescription = text.getText("alarmDescOne");

        //add options to hashmap
        options.put("opOne", new TextOption( text.getText("alarmOpOne"),text.getText("alarmDescTwo"), 0,  this));
        options.put("opTwo", new TextOption( text.getText("alarmOpTwo"),text.getText("alarmDescThree"), 1,  this));
        TextOption[] arr = {options.get("opOne"), options.get("opTwo")};

        //add interactions to hashmap
        interactions.put("snooze", new Interaction(this, alarmDescription , arr , 10));

        bg = new Image("file:" + getPicDir() + "alarm" + File.separator + "alarm_bg.png", getWidth(), getHeight(), true, true);

        alarmClick = new Rectangle(getWidth() / 5, getHeight() / 5, (getWidth() / 5) * 3., (getHeight() / 5) * 3  );
    }

    /**
     * cleans up and ends the page
     */
    public void end() {
        text = null;
        alarmDescription = null;
        bg = null;
        choiceMade = false;
        alarmClick = null;
        Iterator<String> keySetIterator = options.keySet().iterator();

        while (keySetIterator.hasNext()) {
            String key = keySetIterator.next();
            options.get(key).destructor();
        }

        options.clear();
        interactions.clear();
        clearDescription();
        initialized = false;
    }

    @Override
    void handleLogic() {
        if (choice == 1) {
            //snooze alarm
            updateTime(0, 15);
            isInteraction = false;
            curInteraction.clear();
            updateAnxiety(15);
        } else if (choice == 2 || choice == 6) {
            //wake up and change scenes
            updateTime(0, 30);
            if (choice == 2) updateAnxiety(-20);
            changePage(P.BEDROOM);
            end();
        }
    }

    @Override
    void drawImages() {
        //draw time on alarm
        getGC().setFill( Color.RED );
        Font alarmFont = Font.loadFont("file:" + getFontDir() + "LCDMN___.TTF", getHeight() / 2);
        getGC().setFont(alarmFont);
        getGC().setEffect(new GaussianBlur());
        getGC().fillText(getTimeString(), getWidth() / 5, getHeight() / 1.5, (getWidth()/5) * 3);
        getGC().fillText(getTimeString(), getWidth() / 5, getHeight() / 1.5, (getWidth()/5) * 3);
        getGC().setEffect(null);
    }

    @Override
    void setEventHandlers() {
        //add event handlers
        getBaseScene().setOnMouseClicked(new MouseClick());
        getBaseScene().setOnKeyPressed(new PressEsc());
        getBaseScene().setOnMouseMoved(new MouseEnter());
    }

    @Override
    void updateDescription() {
        if(!isInteraction) {
            addDescription(alarmDescription);
        }
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

            else if (alarmClick.contains(e.getX(), e.getY()) && !isInteraction){
                curInteraction = interactions.get("snooze");
                isInteraction = true;
            }

        }
    }
}