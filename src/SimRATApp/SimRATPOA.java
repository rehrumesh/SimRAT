package SimRATApp;


/**
* SimRATApp/SimRATPOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from SimRAT.idl
* Sunday, June 8, 2014 1:31:33 AM IST
*/

public abstract class SimRATPOA extends org.omg.PortableServer.Servant
 implements SimRATApp.SimRATOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("getScreenshot", new java.lang.Integer (0));
    _methods.put ("mouseMove", new java.lang.Integer (1));
    _methods.put ("mousePress", new java.lang.Integer (2));
    _methods.put ("mouseRelease", new java.lang.Integer (3));
    _methods.put ("keyPress", new java.lang.Integer (4));
    _methods.put ("keyReleased", new java.lang.Integer (5));
    _methods.put ("uploadFileToServer", new java.lang.Integer (6));
    _methods.put ("downloadFileFromServer", new java.lang.Integer (7));
    _methods.put ("shutdown", new java.lang.Integer (8));
  }

  public org.omg.CORBA.portable.OutputStream _invoke (String $method,
                                org.omg.CORBA.portable.InputStream in,
                                org.omg.CORBA.portable.ResponseHandler $rh)
  {
    org.omg.CORBA.portable.OutputStream out = null;
    java.lang.Integer __method = (java.lang.Integer)_methods.get ($method);
    if (__method == null)
      throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);

    switch (__method.intValue ())
    {
       case 0:  // SimRATApp/SimRAT/getScreenshot
       {
         SimRATApp.Image $result = null;
         $result = this.getScreenshot ();
         out = $rh.createReply();
         SimRATApp.ImageHelper.write (out, $result);
         break;
       }

       case 1:  // SimRATApp/SimRAT/mouseMove
       {
         int x = in.read_long ();
         int y = in.read_long ();
         this.mouseMove (x, y);
         out = $rh.createReply();
         break;
       }

       case 2:  // SimRATApp/SimRAT/mousePress
       {
         int x = in.read_long ();
         int y = in.read_long ();
         int button = in.read_long ();
         this.mousePress (x, y, button);
         out = $rh.createReply();
         break;
       }

       case 3:  // SimRATApp/SimRAT/mouseRelease
       {
         int x = in.read_long ();
         int y = in.read_long ();
         int button = in.read_long ();
         this.mouseRelease (x, y, button);
         out = $rh.createReply();
         break;
       }

       case 4:  // SimRATApp/SimRAT/keyPress
       {
         int keyCode = in.read_long ();
         this.keyPress (keyCode);
         out = $rh.createReply();
         break;
       }

       case 5:  // SimRATApp/SimRAT/keyReleased
       {
         int keyCode = in.read_long ();
         this.keyReleased (keyCode);
         out = $rh.createReply();
         break;
       }

       case 6:  // SimRATApp/SimRAT/uploadFileToServer
       {
         SimRATApp.SimRATFile inFile = SimRATApp.SimRATFileHelper.read (in);
         this.uploadFileToServer (inFile);
         out = $rh.createReply();
         break;
       }

       case 7:  // SimRATApp/SimRAT/downloadFileFromServer
       {
         String fPath = in.read_string ();
         SimRATApp.SimRATFile $result = null;
         $result = this.downloadFileFromServer (fPath);
         out = $rh.createReply();
         SimRATApp.SimRATFileHelper.write (out, $result);
         break;
       }

       case 8:  // SimRATApp/SimRAT/shutdown
       {
         this.shutdown ();
         out = $rh.createReply();
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:SimRATApp/SimRAT:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public SimRAT _this() 
  {
    return SimRATHelper.narrow(
    super._this_object());
  }

  public SimRAT _this(org.omg.CORBA.ORB orb) 
  {
    return SimRATHelper.narrow(
    super._this_object(orb));
  }


} // class SimRATPOA
