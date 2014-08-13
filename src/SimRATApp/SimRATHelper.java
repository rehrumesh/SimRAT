package SimRATApp;


/**
* SimRATApp/SimRATHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from SimRAT.idl
* Sunday, June 8, 2014 1:31:33 AM IST
*/

abstract public class SimRATHelper
{
  private static String  _id = "IDL:SimRATApp/SimRAT:1.0";

  public static void insert (org.omg.CORBA.Any a, SimRATApp.SimRAT that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static SimRATApp.SimRAT extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (SimRATApp.SimRATHelper.id (), "SimRAT");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static SimRATApp.SimRAT read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_SimRATStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, SimRATApp.SimRAT value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static SimRATApp.SimRAT narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof SimRATApp.SimRAT)
      return (SimRATApp.SimRAT)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      SimRATApp._SimRATStub stub = new SimRATApp._SimRATStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static SimRATApp.SimRAT unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof SimRATApp.SimRAT)
      return (SimRATApp.SimRAT)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      SimRATApp._SimRATStub stub = new SimRATApp._SimRATStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}
