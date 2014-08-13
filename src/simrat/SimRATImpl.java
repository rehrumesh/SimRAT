/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package simrat;

import SimRATApp.Image;
import SimRATApp.SimRATFile;
import SimRATApp.SimRATPOA;
import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import org.omg.CORBA.ORB;

/**
 *
 * @author Rumesh
 */
public class SimRATImpl extends SimRATPOA {

    Robot r;
    ORB ob;
    BufferedImage bi;
    Rectangle rectangle;
    ByteArrayOutputStream baos;

    public void setORB(ORB o) {
        this.ob = o;
        rectangle = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        try {
            r = new Robot();
        } catch (AWTException ex) {
            Logger.getLogger(SimRATImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Image getScreenshot() {
        try {
            bi = r.createScreenCapture(rectangle);
            if (baos == null) {
                baos = new ByteArrayOutputStream();
            } else {
                baos.reset();
            }

//            FileOutputStream fo = new FileOutputStream("Screen.jpg");
            ImageIO.write(bi, "jpg", baos);
//            ImageIO.write(bi, "jpg", fo);
//            fo.flush();

            baos.flush();
            Image i = new Image(baos.toByteArray());
            return i;
        } catch (IOException ex) {
            Logger.getLogger(SimRATImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    @Override
    public void mouseMove(int x, int y) {
        r.mouseMove(x, y);
    }

    @Override
    public void mousePress(int x, int y, int button) {
        if (button == 1) {
            r.mouseMove(x, y);
            r.mousePress(InputEvent.BUTTON1_MASK);
        } else if (button == 2) {
            r.mouseMove(x, y);
            r.mousePress(InputEvent.BUTTON2_MASK);
        } else if (button == 3) {
            r.mouseMove(x, y);
            r.mousePress(InputEvent.BUTTON3_MASK);
        }
    }

    @Override
    public void mouseRelease(int x, int y, int button) {
        if (button == 1) {
            r.mouseMove(x, y);
            r.mouseRelease(InputEvent.BUTTON1_MASK);
        } else if (button == 2) {
            r.mouseMove(x, y);
            r.mouseRelease(InputEvent.BUTTON2_MASK);
        } else if (button == 3) {
            r.mouseMove(x, y);
            r.mouseRelease(InputEvent.BUTTON3_MASK);

        }
    }

    @Override
    public void keyPress(int keyCode) {
        r.keyPress(keyCode);
    }

    @Override
    public void keyReleased(int keyCode) {
        r.keyRelease(keyCode);
    }

    @Override
    public void uploadFileToServer(SimRATFile inFile) {
        FileOutputStream fileOuputStream =  null;
        try {
            String path = System.getProperty("user.home") + "/SimRATFiles/";
            File f = new File(path);
            if (!f.exists()) {
                f.mkdir();
            }
            path += inFile.fileName;
            f = new File(path);
            fileOuputStream = new FileOutputStream(f);
            fileOuputStream.write(inFile.file);
	    fileOuputStream.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SimRATImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SimRATImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fileOuputStream.close();
            } catch (IOException ex) {
                Logger.getLogger(SimRATImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @Override
    public SimRATFile downloadFileFromServer(String fPath) {
        File f = new File(fPath);


        if (f.exists()) {
            try {
                byte[] bFile = new byte[(int) f.length()];
                FileInputStream fs = new FileInputStream(f);
                fs.read(bFile);
                return new SimRATFile(f.getName(), bFile);
            } catch (IOException ex) {
                return null;
            }
        } else {
            return null;
        }
    }

    @Override
    public void shutdown() {
        ob.shutdown(false);
    }
}
