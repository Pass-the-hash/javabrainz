package gui;

import basics.*;
import db.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DB extends javax.swing.JFrame {

    public DB() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        databaseSearchButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel2.setBackground(java.awt.Color.lightGray);
        jPanel2.setLayout(null);

        jLabel1.setBackground(java.awt.Color.white);
        jPanel2.add(jLabel1);
        jLabel1.setBounds(190, 0, 310, 50);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Query:");
        jPanel2.add(jLabel2);
        jLabel2.setBounds(30, 110, 60, 30);

        jTextField1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField1.setToolTipText("Enter query");
        jPanel2.add(jTextField1);
        jTextField1.setBounds(80, 109, 150, 30);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Type:");
        jLabel3.setToolTipText("");
        jPanel2.add(jLabel3);
        jLabel3.setBounds(260, 110, 50, 20);

        jList1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Artists", "Releases", "Albums", "Compilations" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jList1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(jList1);

        jPanel2.add(jScrollPane1);
        jScrollPane1.setBounds(310, 110, 160, 90);

        databaseSearchButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        databaseSearchButton.setText("Database Search");
        databaseSearchButton.setToolTipText("Search");
        databaseSearchButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                databaseSearchButtonMouseClicked(evt);
            }
        });
        jPanel2.add(databaseSearchButton);
        databaseSearchButton.setBounds(500, 110, 150, 30);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 708, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 345, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void databaseSearchButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_databaseSearchButtonMouseClicked
        ArrayList<String> results=new ArrayList();
        
        if (jList1.getSelectedValue().equals("Artists")){
            try {
                artists=Database.ReadArtists(jTextField1.getText());
                /*for (int i=0; i<artists.size(); i++){
                    if (!artists.get(i).getName().contains()) artists.remove(i);
                }*/
            } catch (SQLException ex) {
                Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if (jList1.getSelectedValue().equals("Releases")){
            try {
                releases=Database.ReadReleases(jTextField1.getText());
                /*for (int i=0; i<releases.size(); i++){
                    if (!(releases.get(i).getTitle().contains()) && releases.get(i).getTrackCount()==-1) releases.remove(i);
                }*/
            } catch (SQLException ex) {
                Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if (jList1.getSelectedValue().equals("Albums")){
            try {
                albums=Database.ReadAlbums(jTextField1.getText());
                System.out.println(albums.size());
                /*for (int i=0; i<albums.size(); i++){
                    if (!albums.get(i).getTitle().contains()) albums.remove(i);
                }*/
            } catch (SQLException ex) {
                Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if (jList1.getSelectedValue().equals("Compilations")){
            try {
                compilations=Database.ReadCompilations(jTextField1.getText());
                /*for (int i=0; i<compilations.size(); i++){
                    if (!compilations.get(i).getTitle().contains()) compilations.remove(i);
                }*/
            } catch (SQLException ex) {
                Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if (artists!=null) ArtistDetails.ContinueWithArtists(artists);
        else if (releases!=null) ReleaseDetails.ContinueWithReleases(releases);
        else if (albums!=null) AlbumDetails.ContinueWithAlbums(albums);
        else if (compilations!=null) AlbumDetails.ContinueWithCompilations(compilations);
        else{
            NotInDatabase nodb=new NotInDatabase();
            nodb.setVisible(true);
        }
}//GEN-LAST:event_databaseSearchButtonMouseClicked


    public static void main(String args[]) {
       
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DB.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DB.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DB.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DB.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
       

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new DB().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton databaseSearchButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
    private static ArrayList<Artist> artists;
    private static ArrayList<Release> releases;
    private static ArrayList<Album> albums;
    private static ArrayList<Compilation> compilations;
}
