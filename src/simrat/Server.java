/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package simrat;

import SimRATApp.SimRAT;
import SimRATApp.SimRATHelper;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import org.omg.CORBA.ORB;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;
import org.omg.PortableServer.POAManagerPackage.AdapterInactive;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;

/**
 *
 * @author Rumesh
 */
public class Server {

    public SimRATMain sim;
    
    public void startServer() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String[] argss = {"-ORBInitialPort", "1050", "-ORBInitialHost", "localhost"};
                    ORB orb = ORB.init(argss, null);

                    POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
                    rootpoa.the_POAManager().activate();

                    SimRATImpl impl = new SimRATImpl();
                    impl.setORB(orb);

                    org.omg.CORBA.Object ref = rootpoa.servant_to_reference(impl);
                    SimRAT href = SimRATHelper.narrow(ref);

                    org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
                    NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

                    NameComponent path[] = ncRef.to_name("SimRAT");
                    ncRef.rebind(path, href);

                    System.out.println("Server Ready and waiting...");
                    JOptionPane.showMessageDialog(null, "Server created successfully", "SimRAT Server", 1);
                    //sim.server.setEnabled(false);
                    orb.run();

                    sim.server.setEnabled(true);



                } catch (InvalidName ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Server creation failed", "SimRAT Server", 1);
                } catch (AdapterInactive ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                    
                    JOptionPane.showMessageDialog(null, "Server creation failed", "SimRAT Server", 1);
                } catch (ServantNotActive ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, "Server creation failed", "SimRAT Server", 1);
                } catch (WrongPolicy ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, "Server creation failed", "SimRAT Server", 1);
                } catch (org.omg.CosNaming.NamingContextPackage.InvalidName ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, "Server creation failed", "SimRAT Server", 1);
                } catch (NotFound ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, "Server creation failed", "SimRAT Server", 1);
                } catch (CannotProceed ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, "Server creation failed", "SimRAT Server", 1);
                    ex.printStackTrace();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Server creation failed", "SimRAT Server", 1);
                    e.printStackTrace();
                }
                sim.server.setEnabled(true);
            }
        });
        t.start();
    }

    public static void main(String[] args) {
        try {


            //String[] args = {};
            //args = new String[]{"-ORBInitialPort 1059"};
            String[] argss = {"-ORBInitialPort", "1050", "-ORBInitialHost", "localhost"};
            ORB orb = ORB.init(argss, null);

            POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            rootpoa.the_POAManager().activate();

            SimRATImpl impl = new SimRATImpl();
            impl.setORB(orb);

            org.omg.CORBA.Object ref = rootpoa.servant_to_reference(impl);
            SimRAT href = SimRATHelper.narrow(ref);

            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            NameComponent path[] = ncRef.to_name("SimRAT");
            ncRef.rebind(path, href);

            System.out.println("Server Ready and waiting...");
            JOptionPane.showMessageDialog(null, "Server created successfully", "SimRAT Server", 1);
            //sim.server.setEnabled(false);
            orb.run();

            //sim.server.setEnabled(true);



        } catch (InvalidName ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Server creation failed", "SimRAT Server", 1);
        } catch (AdapterInactive ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Server creation failed", "SimRAT Server", 1);
        } catch (ServantNotActive ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Server creation failed", "SimRAT Server", 1);
        } catch (WrongPolicy ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Server creation failed", "SimRAT Server", 1);
        } catch (org.omg.CosNaming.NamingContextPackage.InvalidName ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Server creation failed", "SimRAT Server", 1);
        } catch (NotFound ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Server creation failed", "SimRAT Server", 1);
        } catch (CannotProceed ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Server creation failed", "SimRAT Server", 1);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Server creation failed", "SimRAT Server", 1);
            e.printStackTrace();
        }
        //sim.server.setEnabled(true);

    }
}
