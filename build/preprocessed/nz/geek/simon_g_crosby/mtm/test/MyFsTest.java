package nz.geek.simon_g_crosby.mtm.test;

import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

/**
 *
 * @author Tommi Laukkanen (tlaukkanen [at] gmail [dot] com)  Laukkanen (tlaukkanen at gmail dot com)
 */
public class MyFsTest extends MIDlet {

    private Controller controller;
    
    /** Default constructor for midlet */
    public GtdMidlet() {
        try{
            controller = Controller.getInstance(this);
            controller.showSplash();            
        }catch(Exception any){
            any.printStackTrace();
        }
    }
    
    public void startApp() {
    }
    
    public void pauseApp() {
    }

    void quit() {
        notifyDestroyed();
    }

    public void destroyApp(boolean unconditional) throws MIDletStateChangeException {
    }

}
