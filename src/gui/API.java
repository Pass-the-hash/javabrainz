package gui;

import basics.*;

import files.APIWrapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class API extends javax.swing.JFrame {
    
    
    public API() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        queryText = new javax.swing.JTextField();
        ExitButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList = new javax.swing.JList<>();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        ProgressBar = new javax.swing.JProgressBar();
        apiSearchButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(java.awt.Color.white);

        jPanel1.setBackground(java.awt.Color.white);
        jPanel1.setLayout(null);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Attribute:");
        jLabel2.setToolTipText("");
        jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.add(jLabel2);
        jLabel2.setBounds(470, 50, 70, 40);

        queryText.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        queryText.setToolTipText("Enter query");
        queryText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                queryTextActionPerformed(evt);
            }
        });
        jPanel1.add(queryText);
        queryText.setBounds(20, 70, 150, 30);

        ExitButton.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        ExitButton.setText("Cancel");
        ExitButton.setToolTipText("Search");
        ExitButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ExitButtonMouseClicked(evt);
            }
        });
        jPanel1.add(ExitButton);
        ExitButton.setBounds(520, 210, 120, 25);

        jList.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Artists", "Releases", "Albums", "Compilations" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jList.setToolTipText("Select type");
        jList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jListValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(jList);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(270, 60, 160, 90);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Type:");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(210, 60, 40, 30);

        jList1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Name" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(jList1);

        jPanel1.add(jScrollPane2);
        jScrollPane2.setBounds(550, 60, 160, 90);
        jPanel1.add(ProgressBar);
        ProgressBar.setBounds(60, 290, 700, 30);

        apiSearchButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        apiSearchButton1.setText("Find");
        apiSearchButton1.setToolTipText("Search");
        apiSearchButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                apiSearchButton1MouseClicked(evt);
            }
        });
        jPanel1.add(apiSearchButton1);
        apiSearchButton1.setBounds(150, 210, 120, 25);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 823, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void queryTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_queryTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_queryTextActionPerformed

    private void apiSearchButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ExitButtonMouseClicked
        /*System.out.println(queryText.getText());
        System.out.println(jList.getSelectedValue());
        System.out.println(jList1.getSelectedValue());*/
        if (jList.getSelectedValue().equals("Artists")){
            if (jList1.getSelectedValue().equals("Name")) try {
                ProgressBar.setValue(10);
                artists=APIWrapper.getArtistsWithName(queryText.getText());
                ProgressBar.setMaximum(WIDTH);
                Details.ContinueWithArtists(artists);
            } catch (IOException | InterruptedException ex) {
                Logger.getLogger(API.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if (jList.getSelectedValue().equals("Releases")){
            if (jList1.getSelectedValue().equals("Name")) try {
                releases=APIWrapper.getReleasesWithName(queryText.getText());
                Details.ContinueWithReleases(releases);
            } catch (IOException ex) {
                Logger.getLogger(API.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if (jList.getSelectedValue().equals("Albums")){
            if (jList1.getSelectedValue().equals("Name")) try {
                albums=APIWrapper.getAlbumsWithName(queryText.getText());
                Details.ContinueWithAlbums(albums);
            } catch (IOException | InterruptedException ex) {
                Logger.getLogger(API.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if (jList.getSelectedValue().equals("Compilations")){
            if (jList1.getSelectedValue().equals("Name")) try {
                compilations=APIWrapper.getCompilationsWithName(queryText.getText());
                Details.ContinueWithCompilations(compilations);
            } catch (IOException | InterruptedException ex) {
                Logger.getLogger(API.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_ExitButtonMouseClicked

    private void jListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jListValueChanged
        
    }//GEN-LAST:event_jListValueChanged

    private void ExitButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_apiSearchButton1MouseClicked
        System.exit(0);
    }//GEN-LAST:event_apiSearchButton1MouseClicked

    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(API.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(API.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(API.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(API.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new API().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ExitButton;
    private javax.swing.JProgressBar ProgressBar;
    private javax.swing.JButton apiSearchButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JList<String> jList;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField queryText;
    // End of variables declaration//GEN-END:variables
    private ArrayList<Artist> artists;
    private ArrayList<Release> releases;
    private ArrayList<Album> albums;
    private ArrayList<Compilation> compilations;
}
