package gui;

import basics.*;
import db.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;


public class AlbumDetails extends javax.swing.JFrame{
    public static void ShowDB(ArrayList<String> arr){
        ArtistDetails details=new ArtistDetails();
        details.setVisible(true);
        //jList=new javax.swing.JList(arr.toArray());
    }
    
    public static void ContinueWithAlbums(ArrayList<Album> arr){
        albums=arr;
        AlbumDetails details=new AlbumDetails();
        AddAlbumRows();
        details.setVisible(true);
    }
    
    public static void ContinueWithCompilations(ArrayList<Compilation> arr){
        compilations=arr;
        AlbumDetails details=new AlbumDetails();
        AddCompilationRows();
        details.setVisible(true);
    }
    
    public AlbumDetails() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]{},
            new String [] {
                "ID", "Title",  "Release date", "Artist", "Artists"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("Yes");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
                    jButton1MouseClicked(evt);
                } catch (SQLException ex) {
                    Logger.getLogger(ReleaseDetails.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        jButton2.setText("No");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });

        jLabel1.setText("Continue?");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 269, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(229, 229, 229)
                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(274, 274, 274))))
            .addGroup(layout.createSequentialGroup()
                .addGap(411, 411, 411)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(44, 44, 44))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) throws SQLException {
            if (albums!=null) Database.WriteAlbums(albums);
            if (compilations!=null) Database.WriteCompilations(compilations);
            this.dispose();
        
    }

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {
        this.dispose();
    }
    
    private static void AddAlbumRows(){
        DefaultTableModel model=(DefaultTableModel) jTable1.getModel();
        Object[] rowdata=new Object[10];
        for (int i=0; i<albums.size(); i++){
            rowdata[0]=albums.get(i).getID();
            rowdata[1]=albums.get(i).getTitle();
            rowdata[2]=albums.get(i).getReleaseDate();
            if (albums.get(i).getArtist()!=null) rowdata[3]=albums.get(i).getArtist().getName();
            else rowdata[3]=null;
            model.addRow(rowdata);
        }
    }
    private static void AddCompilationRows(){
        DefaultTableModel model=(DefaultTableModel) jTable1.getModel();
        Object[] rowdata=new Object[10];
        for (int i=0; i<compilations.size(); i++){
            rowdata[0]=compilations.get(i).getID();
            rowdata[1]=compilations.get(i).getTitle();
            rowdata[2]=compilations.get(i).getReleaseDate();
            String artists="";
            for (int j=0; j<compilations.get(i).getArtists().size(); j++){
                artists=artists + compilations.get(i).getArtists().get(j).getName() + ",";
            }
            rowdata[4]=artists;
            model.addRow(rowdata);
        }
    }
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
            java.util.logging.Logger.getLogger(ArtistDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ArtistDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ArtistDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ArtistDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ArtistDetails().setVisible(true);
            }
        });
    }

    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private static javax.swing.JTable jTable1;
    private static ArrayList<Album> albums;
    private static ArrayList<Compilation> compilations;

}