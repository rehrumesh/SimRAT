/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package simrat;

import SimRATApp.Image;
import SimRATApp.SimRAT;
import SimRATApp.SimRATFile;
import SimRATApp.SimRATHelper;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileView;
import javax.swing.plaf.FileChooserUI;
import org.omg.CORBA.ORB;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.NotFound;

/**
 *
 * @author Rumesh
 */
public class Client extends javax.swing.JFrame {

    static ORB orb;
    static SimRAT implObj;
    Thread t;
    Image imgArr;
    int labelWidth;
    int refreshRate;
    int originalWidth;
    int originalHeight;
    ImageIcon ico;
    boolean started;

    /**
     * Creates new form Client
     */
    public Client() {
        try {
            initComponents();
            setLocationRelativeTo(null);

            String[] args = {"-ORBInitialPort","1050","-ORBInitialHost","Rumesh-Laptop"};
            orb = ORB.init(args, null);
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
            implObj = SimRATHelper.narrow(ncRef.resolve_str("SimRAT"));


            started = false;
            labelWidth = lblPicture.getWidth();
            refreshRate = 10;
            t = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            imgArr = implObj.getScreenshot();
                            InputStream in = new ByteArrayInputStream(imgArr.img);
                            BufferedImage bi = ImageIO.read(in);

                            originalWidth = bi.getWidth();
                            originalHeight = bi.getHeight();
                            //System.out.println(originalWidth+"  X  "+originalHeight);


                            java.awt.Image img = new ImageIcon(bi).getImage().getScaledInstance(labelWidth, labelWidth * originalHeight / originalWidth, java.awt.Image.SCALE_SMOOTH);

                            lblPicture.setBounds(5, 5, lblPicture.getWidth(), labelWidth * originalHeight / originalWidth);
                            lblPicture.setIcon(new ImageIcon(img));
                            ico = (ImageIcon) lblPicture.getIcon();
                            started = true;
                            Thread.sleep(refreshRate);
                        } catch (IOException ex) {
                            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            });
            lblPicture.requestFocusInWindow();
        } catch (InvalidName ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotFound ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CannotProceed ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (org.omg.CosNaming.NamingContextPackage.InvalidName ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblPicture = new javax.swing.JLabel();
        btnStart = new javax.swing.JButton();
        chkMouseMove = new javax.swing.JCheckBox();
        btnGetFile = new javax.swing.JButton();
        btnSendFile = new javax.swing.JButton();
        spinRefresh = new javax.swing.JSpinner();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SimRAT Viewer");

        lblPicture.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblPicture.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblPictureMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                lblPictureMouseReleased(evt);
            }
        });
        lblPicture.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lblPictureMouseMoved(evt);
            }
        });
        lblPicture.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                lblPictureKeyReleased(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                lblPictureKeyPressed(evt);
            }
        });

        btnStart.setText("Start");
        btnStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartActionPerformed(evt);
            }
        });

        chkMouseMove.setText("Capture Mouse Move");
        chkMouseMove.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                chkMouseMoveMouseReleased(evt);
            }
        });

        btnGetFile.setText("Get File");
        btnGetFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGetFileActionPerformed(evt);
            }
        });

        btnSendFile.setText("Send File");
        btnSendFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendFileActionPerformed(evt);
            }
        });

        spinRefresh.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(20), Integer.valueOf(0), null, Integer.valueOf(1)));
        spinRefresh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                spinRefreshMouseReleased(evt);
            }
        });
        spinRefresh.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                spinRefreshPropertyChange(evt);
            }
        });

        jLabel1.setText("Frame Refresh Rate");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPicture, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnStart, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chkMouseMove)
                        .addGap(44, 44, 44)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(spinRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 205, Short.MAX_VALUE)
                        .addComponent(btnSendFile, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnGetFile, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnGetFile, btnSendFile});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblPicture, javax.swing.GroupLayout.DEFAULT_SIZE, 506, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnStart)
                    .addComponent(chkMouseMove)
                    .addComponent(btnGetFile)
                    .addComponent(btnSendFile)
                    .addComponent(spinRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartActionPerformed
        t.start();
        spinRefresh.setEnabled(false);
        lblPicture.requestFocusInWindow();
    }//GEN-LAST:event_btnStartActionPerformed

    private void btnGetFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGetFileActionPerformed
        String path = JOptionPane.showInputDialog("Please enter file path.");
        if (path != null) {
            if (path.trim().length() > 0) {
                SimRATFile simFile = implObj.downloadFileFromServer(path);
//                SimRATFile simFile = new SimRATFile();
//                simFile.fileName = "app.txt";
                if (simFile != null) {
                    JFileChooser fc = new JFileChooser();
                    fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                    fc.setDialogTitle("Select a path to save the file");
                    int returnVal = fc.showSaveDialog(this);


                    if (returnVal == JFileChooser.APPROVE_OPTION) {

                        FileOutputStream fileOuputStream = null;
                        try {
                            File file = fc.getSelectedFile();
                            String pt = file.getAbsolutePath() + "/" + simFile.fileName;
                            fileOuputStream = new FileOutputStream(pt);

                            fileOuputStream.write(simFile.file);
                            fileOuputStream.close();
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IOException ex) {
                            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                        } finally {
                            try {
                                fileOuputStream.close();
                            } catch (IOException ex) {
                                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "File save cancelled.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "File does not exist in remote machine.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Please enter a valid path");
            }
        }
        lblPicture.requestFocusInWindow();
    }//GEN-LAST:event_btnGetFileActionPerformed

    private void chkMouseMoveMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chkMouseMoveMouseReleased
        lblPicture.requestFocusInWindow();
    }//GEN-LAST:event_chkMouseMoveMouseReleased

    private void btnSendFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendFileActionPerformed
        //implemnt
        JFileChooser fc = new JFileChooser();
        int returnVal = fc.showOpenDialog(this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            FileInputStream fs = null;
            try {
                File f = fc.getSelectedFile();
                //This is where a real application would open the file.
                //log.append("Opening: " + file.getName() + "." + newline);
                byte[] bFile = new byte[(int) f.length()];
                fs = new FileInputStream(f);
                fs.read(bFile);
                implObj.uploadFileToServer(new SimRATFile(f.getName(), bFile));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    fs.close();
                } catch (IOException ex) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
        }

        lblPicture.requestFocusInWindow();
    }//GEN-LAST:event_btnSendFileActionPerformed

    private void lblPictureMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPictureMousePressed


        int x = evt.getX();
        int y = evt.getY();
        implObj.mousePress(x * originalWidth / ico.getIconWidth(), y * originalHeight / ico.getIconHeight(), evt.getButton());
        System.out.println("Pressed");

    }//GEN-LAST:event_lblPictureMousePressed

    private void lblPictureMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPictureMouseReleased


        int x = evt.getX();
        int y = evt.getY();
        implObj.mouseRelease(x * originalWidth / ico.getIconWidth(), y * originalHeight / ico.getIconHeight(), evt.getButton());
        System.out.println("Released");
    }//GEN-LAST:event_lblPictureMouseReleased

    private void lblPictureKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lblPictureKeyPressed
        if (started) {
            implObj.keyPress(evt.getKeyCode());
        }
    }//GEN-LAST:event_lblPictureKeyPressed

    private void lblPictureKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lblPictureKeyReleased
        if (started) {
            implObj.keyReleased(evt.getKeyCode());
        }
    }//GEN-LAST:event_lblPictureKeyReleased

    private void spinRefreshPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_spinRefreshPropertyChange
        lblPicture.requestFocusInWindow();
    }//GEN-LAST:event_spinRefreshPropertyChange

    private void spinRefreshMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_spinRefreshMouseReleased
        lblPicture.requestFocusInWindow();
    }//GEN-LAST:event_spinRefreshMouseReleased

    private void lblPictureMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPictureMouseMoved
        if (started) {
            if (chkMouseMove.isSelected()) {

                int x = evt.getX();
                int y = evt.getY();
                implObj.mouseMove(x * originalWidth / ico.getIconWidth(), y * originalHeight / ico.getIconHeight());
            }
        }
    }//GEN-LAST:event_lblPictureMouseMoved

    /**
     * @param args the command line arguments
     */
    public static void main(final String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Client().setVisible(true);

//                    orb = ORB.init(args, null);
//                    org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
//                    NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
//                    implObj = SimRATHelper.narrow(ncRef.resolve_str("SimRAT"));

//                } catch (InvalidName ex) {
//                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
//                    JOptionPane.showMessageDialog(null, "CORBA ORB creation error");
//                } catch (NotFound ex) {
//                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
//                    JOptionPane.showMessageDialog(null, "CORBA ORB creation error");
//                } catch (CannotProceed ex) {
//                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
//                    JOptionPane.showMessageDialog(null, "CORBA ORB creation error");
//                } catch (org.omg.CosNaming.NamingContextPackage.InvalidName ex) {
//                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
//                    JOptionPane.showMessageDialog(null, "CORBA ORB creation error");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "CORBA ORB creation error");
                }
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGetFile;
    private javax.swing.JButton btnSendFile;
    private javax.swing.JButton btnStart;
    private javax.swing.JCheckBox chkMouseMove;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblPicture;
    private javax.swing.JSpinner spinRefresh;
    // End of variables declaration//GEN-END:variables
}
