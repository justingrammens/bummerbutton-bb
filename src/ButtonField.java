import java.io.InputStream;

import javax.microedition.media.Manager;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Graphics;

import javax.microedition.media.Manager;
import javax.microedition.media.Player;


public class ButtonField extends Field 
{       
    private String _label;
    private int _labelHeight;
    private int _labelWidth;    
    
    private Bitmap _currentPicture;
    private Bitmap _onPicture = Bitmap.getBitmapResource("img/buttonup.png");
    private Bitmap _offPicture = Bitmap.getBitmapResource("img/buttondown.png");    
    
    Player clickplayer = null;
    
    /**
     * Constructor.
     * @param text - the text to be displayed on the button
     * @param style - combination of field style bits to specify display
           attributes 
     */
    ButtonField(String text, long style) 
    {
        super(style);
        _labelHeight = Graphics.getScreenHeight();
        _labelWidth = Graphics.getScreenWidth();
        _currentPicture = _onPicture;
    }
    
    /**
     * Field implementation.
     * @see net.rim.device.api.ui.Field#getPreferredHeight()
     */
    public int getPreferredHeight() 
    {
        return _labelHeight + 4;
    }

    /**
     * Field implementation.
     * @see net.rim.device.api.ui.Field#getPreferredWidth()
     */
    public int getPreferredWidth() 
    {
        return _labelWidth + 8;
    }
    
    /**
     * Field implementation.  Changes the picture when focus is gained.
     * @see net.rim.device.api.ui.Field#onFocus(int)
     */
    protected void onFocus(int direction) 
    {
        _currentPicture = _onPicture;
        invalidate();
    }

    /**
     * Field implementation.  Changes picture back when focus is lost.
     * @see net.rim.device.api.ui.Field#onUnfocus()
     */
    protected void onUnfocus() 
    {
        _currentPicture = _offPicture;
        invalidate();
    }
    
    /**
     * Field implementation.  
     * @see net.rim.device.api.ui.Field#drawFocus(Graphics, boolean)
     */
    protected void drawFocus(Graphics graphics, boolean on) 
    {
        // Do nothing
    }
    
    /**
     * Field implementation.
     * @see net.rim.device.api.ui.Field#layout(int, int)
     */
    protected void layout(int width, int height) 
    {
        setExtent(Math.min( width, getPreferredWidth()), 
        Math.min( height, getPreferredHeight()));
    }

    /**
     * Field implementation.
     * @see net.rim.device.api.ui.Field#paint(Graphics)
     */
    protected void paint(Graphics graphics) 
    {       
    	graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, Graphics.getScreenWidth(), Graphics.getScreenWidth());
        graphics.drawBitmap((Graphics.getScreenWidth()/2)-100, 0, Graphics.getScreenWidth(), Graphics.getScreenHeight(), _currentPicture, 0, 0);
        
        try {
        	Thread.sleep(500);
        } catch (Exception ex ) {
        	ex.printStackTrace();
        }
        
    }
        
    /**
     * If called when the navigation button is clicked.
     */
    protected boolean navigationClick(int status, int time) 
    {
    	 _currentPicture = _offPicture;
         invalidate();  
        return true;
    }
    
    /**
     * Called when the navigation button is unclicked.
     */
    protected boolean navigationUnclick(int status, int time) 
    {
        _currentPicture = _onPicture;
        invalidate();
        fieldChangeNotify(1);
        
        return true;
    }
    
    /**
     * Called by the main program to play the sound
     */
    public void playSound() {
    	try {              
    		InputStream instream = getClass().getResourceAsStream("/img/sound.m4a");			
            clickplayer = Manager.createPlayer(instream, "audio/mp4");			
            clickplayer.realize();			
            clickplayer.setLoopCount(1);
			clickplayer.setMediaTime(0);
			clickplayer.start();
		} catch (Exception e) {
			e.printStackTrace();
		}			
    }
}
