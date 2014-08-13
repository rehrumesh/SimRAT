package SimRATApp;


/**
* SimRATApp/SimRATOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from SimRAT.idl
* Sunday, June 8, 2014 1:31:33 AM IST
*/

public interface SimRATOperations 
{
  SimRATApp.Image getScreenshot ();
  void mouseMove (int x, int y);
  void mousePress (int x, int y, int button);
  void mouseRelease (int x, int y, int button);
  void keyPress (int keyCode);
  void keyReleased (int keyCode);
  void uploadFileToServer (SimRATApp.SimRATFile inFile);
  SimRATApp.SimRATFile downloadFileFromServer (String fPath);
  void shutdown ();
} // interface SimRATOperations