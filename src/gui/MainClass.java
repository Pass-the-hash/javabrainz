package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class MainClass {
  protected JFrame theFrame = new JFrame("LNF Switcher");

  protected Container cp;

  protected String curLF = "javax.swing.plaf.metal.MetalLookAndFeel";

  protected JRadioButton previousButton;

  public MainClass() {
    theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    cp = theFrame.getContentPane();
    cp.setLayout(new FlowLayout());

    ButtonGroup bg = new ButtonGroup();

    JRadioButton bJava = new JRadioButton("Java");
    bJava.addActionListener((ActionListener) new LNFSetter("javax.swing.plaf.metal.MetalLookAndFeel", bJava));
    bg.add(bJava);
    cp.add(bJava);

    JRadioButton bMSW = new JRadioButton("MS-Windows");
    bMSW.addActionListener(new LNFSetter("com.sun.java.swing.plaf.windows.WindowsLookAndFeel", bMSW));
    bg.add(bMSW);
    cp.add(bMSW);

    JRadioButton bMotif = new JRadioButton("Motif");
    bMotif.addActionListener(new LNFSetter("com.sun.java.swing.plaf.motif.MotifLookAndFeel", bMotif));
    bg.add(bMotif);
    cp.add(bMotif);

    JRadioButton bMac = new JRadioButton("Sun-MacOS");
    bMac.addActionListener(new LNFSetter("java.swing.plaf.synth.SynthLookAndFeel", bMac));
    bg.add(bMac);
    cp.add(bMac);

    String defaultLookAndFeel = UIManager.getSystemLookAndFeelClassName();
    JRadioButton bDefault = new JRadioButton("Default");
    bDefault.addActionListener(new LNFSetter(defaultLookAndFeel, bDefault));
    bg.add(bDefault);
    cp.add(bDefault);

    (previousButton = bDefault).setSelected(true);

    theFrame.pack();
    theFrame.setVisible(true);
  }

  class LNFSetter implements ActionListener {
    String theLNFName;

    JRadioButton thisButton;

    LNFSetter(String lnfName, JRadioButton me) {
      theLNFName = lnfName;
      thisButton = me;
    }
    public void actionPerformed(ActionEvent e) {
      try {
        UIManager.setLookAndFeel(theLNFName);
        SwingUtilities.updateComponentTreeUI(theFrame);
        theFrame.pack();
      } catch (Exception evt) {
        JOptionPane.showMessageDialog(null, "setLookAndFeel didn't work: " + evt, "UI Failure",
            JOptionPane.INFORMATION_MESSAGE);
        previousButton.setSelected(true); // reset the GUI to agree
      }
      previousButton = thisButton;
    }

        /*public void actionPerformed(ActionEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }*/
  }

  public static void main(String[] argv) {
    new MainClass();
  }
}