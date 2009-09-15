import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.component.RichTextField;
import net.rim.device.api.ui.container.MainScreen;
import net.rim.device.api.ui.container.VerticalFieldManager;

public class BummerButton extends UiApplication implements FieldChangeListener
{
        
    /**
     * Entry point.
     */
    public static void main(String[] args)
    {
    	BummerButton theApp = new BummerButton();
        theApp.enterEventDispatcher();
    }

    /**
     * The default constructor. Creates all the RIM UI components and 
     * pushes the application's root screen onto the UI stack.
     */
    BummerButton()
    {
        ButtonField picture;
        
        // MainScreen is the basic screen or frame class of the RIM UI.
        MainScreen mainScreen = new MainScreen();
        
        // Add a field to the title region of the MainScreen. We use a simple LabelField 
        
        mainScreen.setTitle(new LabelField("Bummer Button" , LabelField.ELLIPSIS | LabelField.USE_ALL_WIDTH));

        // Add a vertical field manager containing sample custom button fields.
        VerticalFieldManager vfm = new VerticalFieldManager();
        
        vfm.add(new RichTextField(Field.NON_FOCUSABLE));
        picture = new ButtonField("Picture", Field.FOCUSABLE);
        picture.setChangeListener(this);
        vfm.add(picture);
        
        mainScreen.add(vfm);

        // We've completed construction of our UI objects. Push the MainScreen
        // instance onto the UI stack for rendering.
        pushScreen(mainScreen);
    }

    /**
     * FieldChangeListener implementation.
     * @see net.rim.device.api.ui.FieldChangeListener#fieldChanged(Field, int)
     */
    public void fieldChanged(Field field, int context) 
    {
        if (field instanceof ButtonField) {
        	// play our sound.
            ((ButtonField)field).playSound();
            
        }
    }
}
