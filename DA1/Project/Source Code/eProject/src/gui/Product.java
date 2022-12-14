package gui;

import gui.ComboListener;
import gui.SetImage;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.Window;
import java.awt.event.ContainerListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class Product extends javax.swing.JFrame {

    DefaultTableModel tblModelSP, tblModelLoaiSP;
    DefaultComboBoxModel cbModelSP, cbModelLoaiSP;
    Connection conSP, conLoaiSP;
    ResultSet rsSP, rsLoaiSP, rsIDPr, rsTen, rsTK, rsAdd1;
    server.DBHelper db = new server.DBHelper();
    PreparedStatement psSP, psLoaiSP, psSP1, psTen, psTK, psAdd1, psAdd2;
    Vector rowSP, rowLoaiSP, vecTK, vecTen, rowTen;
    private int[] sponsorIndexArr;

    public Product() {
        initComponents();
        conSP = db.getCon();
        conLoaiSP = db.getCon();
        //----------hinh button-------------
        btnAddSP.setSize(20, 20);
        new SetImage().setImageButton(btnAddSP, "image//add.png");
        btnDeleteSP.setSize(20, 20);
        new SetImage().setImageButton(btnDeleteSP, "image//deleted.png");
        btnResetSP.setSize(20, 20);
        new gui.SetImage().setImageButton(btnResetSP, "image//refresh.png");
        btnUpdateSP.setSize(20, 20);
        new SetImage().setImageButton(btnUpdateSP, "image//edit2.png");
        btnThemLoaiSP.setSize(20, 20);
        new SetImage().setImageButton(btnThemLoaiSP, "image//add.png");
        btnSuaLoaiSP.setSize(20, 20);
        new SetImage().setImageButton(btnSuaLoaiSP, "image//edit2.png");
        btnResetLoaiSP.setSize(20, 20);
        new SetImage().setImageButton(btnResetLoaiSP, "image//refresh.png");
        btnXoaLoaiSP.setSize(20, 20);
        new gui.SetImage().setImageButton(btnXoaLoaiSP, "image//deleted.png");

        //---------------------End Hinh button-------------------------
        btnAddSP.setForeground(Color.BLUE);
        btnUpdateSP.setForeground(Color.BLUE);
        btnDeleteSP.setForeground(Color.RED);
        btnResetSP.setForeground(Color.BLUE);
        //--------------------------------------------
//        tblSP.setBackground(Color.DARK_GRAY);

        btnDeleteSP.setEnabled(false);
        btnUpdateSP.setEnabled(false);
        btnXoaLoaiSP.setEnabled(false);
        btnSuaLoaiSP.setEnabled(false);

        tblModelSP = new DefaultTableModel();
        tblModelSP.addColumn("ID");
        tblModelSP.addColumn("T??n");
        tblModelSP.addColumn("Lo???i");
        tblModelSP.addColumn("Gi??");
        tblModelSP.addColumn("K??ch c???");
        tblSP.setModel(tblModelSP);
        loadDataSP();

        tblModelLoaiSP = new DefaultTableModel();
        tblModelLoaiSP.addColumn("ID");
        tblModelLoaiSP.addColumn("Lo???i");
        tblModelLoaiSP.addColumn("K??ch c???");
        tblLoaiSP.setModel(tblModelLoaiSP);
        loadDataLoaiSP();
        btnTimKiem.setVisible(false);
        cbTen.setVisible(false);
        PanelTimKiem.setVisible(false);
    }

    private void loadDataSP() {
        try {
            String sql = "select * from Product inner join ProductType on Product.IDType=ProductType.IDType";
            psSP = conSP.prepareStatement(sql);
            rsSP = psSP.executeQuery();
            while (rsSP.next()) {
                rowSP = new Vector();
                rowSP.add(rsSP.getString("IDProduct"));
                rowSP.add(rsSP.getString("ProductName"));
                rowSP.add(rsSP.getString("IDType"));
                rowSP.add(rsSP.getString("Price"));
                rowSP.add(rsSP.getString("Size"));
                tblModelSP.addRow(rowSP);
            }
            tblSP.setModel(tblModelSP);
        } catch (Exception e) {
        }
        cbNameType.removeAllItems();
        try {
            String sql = "select DISTINCT TypeName from  ProductType";
            psSP = conSP.prepareStatement(sql);
            rsSP = psSP.executeQuery();
            while (rsSP.next()) {
                cbNameType.addItem(rsSP.getString(1));
            }
        } catch (Exception e) {
        }

//        tblSP.setDefaultRenderer(Object.class, new MyRenderer());
//        tblSP.setDefaultRenderer(Object.class, new AlignRenderer());
//        tblSP.setDefaultRenderer(Object.class, new ImageRenderer());
//        tblSP.setDefaultRenderer(Object.class, new CountryCellRenderer());
//        tblSP.getColumnModel().getColumn(0).setPreferredWidth(100);
    }

    private void loadDataLoaiSP() {
        try {
            conLoaiSP = db.getCon();
            String sql = "select * from ProductType";
            psLoaiSP = conLoaiSP.prepareStatement(sql);
            rsLoaiSP = psLoaiSP.executeQuery();
            while (rsLoaiSP.next()) {
                rowLoaiSP = new Vector();
                rowLoaiSP.add(rsLoaiSP.getString(1));
                rowLoaiSP.add(rsLoaiSP.getString(2));
                rowLoaiSP.add(rsLoaiSP.getString(3));
                tblModelLoaiSP.addRow(rowLoaiSP);
            }
            tblLoaiSP.setModel(tblModelLoaiSP);
        } catch (Exception e) {
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel(){
            ImageIcon icon = new ImageIcon("image//hinhnen3.jpg");
            public void paintComponent(Graphics g){
                Dimension d = getSize();
                g.drawImage(icon.getImage(), 0, 0, d.width, d.height, null);
                setOpaque(false);
                super.paintComponent(g);
            }
        };
        jPanel2 = new javax.swing.JPanel(){
            ImageIcon icon = new ImageIcon("src//image//trongsuot.png");
            public void paintComponent(Graphics g){
                Dimension d = getSize();
                g.drawImage(icon.getImage(), 0, 0, d.width, d.height, null);
                setOpaque(false);
                super.paintComponent(g);
            }
        };
        jTabbedPane1 = new javax.swing.JTabbedPane(){
            ImageIcon icon = new ImageIcon("src//image//trongsuot.png");
            public void paintComponent(Graphics g){
                Dimension d = getSize();
                g.drawImage(icon.getImage(), 0, 0, d.width, d.height, null);
                setOpaque(false);
                super.paintComponent(g);
            }
        };
        jPanel4 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel(){
            ImageIcon icon = new ImageIcon("image//trongsuot.jpg");
            public void paintComponent(Graphics g){
                Dimension d = getSize();
                g.drawImage(icon.getImage(), 0, 0, d.width, d.height, null);
                setOpaque(false);
                super.paintComponent(g);
            }
        };
        btnThemLoaiSP = new javax.swing.JButton();
        btnResetLoaiSP = new javax.swing.JButton();
        btnSuaLoaiSP = new javax.swing.JButton();
        btnXoaLoaiSP = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblLoaiSP = new javax.swing.JTable(){
            ImageIcon icon = new ImageIcon("src//image//trongsuot.jpg");
            public void paintComponent(Graphics g){
                Dimension d = getSize();
                g.drawImage(icon.getImage(), 0, 0, d.width, d.height, null);
                setOpaque(false);
                super.paintComponent(g);
            }
        };
        cbSizeType = new javax.swing.JComboBox();
        txtNameType = new javax.swing.JTextField();
        txtIDProductType = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btnThongke = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtIDProduct = new javax.swing.JTextField();
        txtNameproduct = new javax.swing.JTextField();
        cbNameType = new javax.swing.JComboBox();
        txtPrice = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel(){
            ImageIcon icon = new ImageIcon("image//trongsuot.jpg");
            public void paintComponent(Graphics g){
                Dimension d = getSize();
                g.drawImage(icon.getImage(), 0, 0, d.width, d.height, null);
                setOpaque(false);
                super.paintComponent(g);
            }
        };
        btnAddSP = new javax.swing.JButton();
        btnResetSP = new javax.swing.JButton();
        btnUpdateSP = new javax.swing.JButton();
        btnDeleteSP = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSP = new javax.swing.JTable(){
            ImageIcon icon = new ImageIcon("src//image//trongsuot.jpg");
            public void paintComponent(Graphics g){
                Dimension d = getSize();
                g.drawImage(icon.getImage(), 0, 0, d.width, d.height, null);
                setOpaque(false);
                super.paintComponent(g);
            }
        };
        jLabel9 = new javax.swing.JLabel();
        cbKichCo = new javax.swing.JComboBox();
        PanelTimKiem = new javax.swing.JPanel();
        cbTen = new javax.swing.JComboBox();
        lbGiaTu = new javax.swing.JLabel();
        lbDen = new javax.swing.JLabel();
        txtGiaTu = new javax.swing.JTextField();
        txtDen = new javax.swing.JTextField();
        lbNhom = new javax.swing.JLabel();
        lbLoai = new javax.swing.JLabel();
        cbLoaiTK = new javax.swing.JComboBox();
        cbKichThuoc = new javax.swing.JComboBox();
        lbVND = new javax.swing.JLabel();
        lbVND1 = new javax.swing.JLabel();
        cbChonTimKiem = new javax.swing.JComboBox();
        btnTimKiem = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Qu???n l?? s???n ph???m");

        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });

        jPanel6.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        btnThemLoaiSP.setForeground(new java.awt.Color(0, 0, 153));
        btnThemLoaiSP.setText("Th??m M???i");
        btnThemLoaiSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemLoaiSPActionPerformed(evt);
            }
        });

        btnResetLoaiSP.setForeground(new java.awt.Color(0, 0, 153));
        btnResetLoaiSP.setText("L??m M???i");
        btnResetLoaiSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetLoaiSPActionPerformed(evt);
            }
        });

        btnSuaLoaiSP.setForeground(new java.awt.Color(0, 0, 153));
        btnSuaLoaiSP.setText("S???a");
        btnSuaLoaiSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaLoaiSPActionPerformed(evt);
            }
        });

        btnXoaLoaiSP.setForeground(new java.awt.Color(255, 0, 0));
        btnXoaLoaiSP.setText("X??a");
        btnXoaLoaiSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaLoaiSPActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnThemLoaiSP)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSuaLoaiSP)
                .addGap(18, 18, 18)
                .addComponent(btnXoaLoaiSP)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnResetLoaiSP)
                .addGap(250, 250, 250))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnSuaLoaiSP)
                        .addComponent(btnThemLoaiSP))
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnXoaLoaiSP)
                        .addComponent(btnResetLoaiSP)))
                .addContainerGap())
        );

        tblLoaiSP.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        tblLoaiSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblLoaiSP.setRowHeight(30);
        tblLoaiSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblLoaiSPMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblLoaiSP);

        cbSizeType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Nh???", "V???a", "L???n" }));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 0, 0));
        jLabel8.setText("ID Lo???i S???n Ph???m :");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 0, 0));
        jLabel5.setText("T??n Lo???i S???n Ph???m :");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 0, 0));
        jLabel6.setText("K??ch C??? :");

        btnThongke.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnThongke.setForeground(new java.awt.Color(0, 204, 204));
        btnThongke.setText("Th???ng k??");
        btnThongke.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThongkeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(32, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(168, 168, 168))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnThongke)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(32, 32, 32)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel5)
                        .addComponent(jLabel6)
                        .addComponent(jLabel8))
                    .addGap(61, 61, 61)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtIDProductType)
                        .addComponent(txtNameType)
                        .addComponent(cbSizeType, 0, 205, Short.MAX_VALUE))
                    .addContainerGap(228, Short.MAX_VALUE)))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(248, Short.MAX_VALUE)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(btnThongke)
                .addGap(41, 41, 41)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(76, 76, 76)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(txtIDProductType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(25, 25, 25)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtNameType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5))
                    .addGap(28, 28, 28)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel6)
                        .addComponent(cbSizeType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(442, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("Lo???i S???n Ph???m", jPanel4);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel1.setText("ID S???n Ph???m :");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 0, 0));
        jLabel2.setText("T??n S???n Ph???m :");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 0, 0));
        jLabel3.setText("Lo???i S???n Ph???m :");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 0, 0));
        jLabel7.setText("Gi?? S???n Ph???m :");

        cbNameType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jPanel5.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        btnAddSP.setText("Th??m M???i");
        btnAddSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddSPActionPerformed(evt);
            }
        });

        btnResetSP.setText("L??m M???i");
        btnResetSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetSPActionPerformed(evt);
            }
        });

        btnUpdateSP.setText("S???a");
        btnUpdateSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateSPActionPerformed(evt);
            }
        });

        btnDeleteSP.setText("X??a");
        btnDeleteSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteSPActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAddSP)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnUpdateSP)
                .addGap(18, 18, 18)
                .addComponent(btnDeleteSP)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnResetSP)
                .addGap(250, 250, 250))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnUpdateSP)
                        .addComponent(btnAddSP))
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnDeleteSP)
                        .addComponent(btnResetSP)))
                .addContainerGap())
        );

        tblSP.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        tblSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblSP.setRowHeight(30);
        tblSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSPMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSP);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 0, 0));
        jLabel9.setText("K??ch c??? :");

        cbKichCo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "L???n", "V???a", "Nh???" }));

        cbTen.setEditable(true);
        cbTen.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lbGiaTu.setText("Gi?? t???:");

        lbDen.setText("?????n :");

        txtGiaTu.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        txtDen.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        lbNhom.setText("Nh??m:");

        lbLoai.setText("K??ch Th?????c:");

        cbLoaiTK.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbKichThuoc.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Nh???", "V???a", "L???n" }));

        lbVND.setText("VN??");

        lbVND1.setText("VN??");

        javax.swing.GroupLayout PanelTimKiemLayout = new javax.swing.GroupLayout(PanelTimKiem);
        PanelTimKiem.setLayout(PanelTimKiemLayout);
        PanelTimKiemLayout.setHorizontalGroup(
            PanelTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelTimKiemLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbTen, 0, 208, Short.MAX_VALUE)
                    .addGroup(PanelTimKiemLayout.createSequentialGroup()
                        .addGroup(PanelTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbGiaTu)
                            .addComponent(lbDen))
                        .addGap(18, 18, 18)
                        .addGroup(PanelTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtGiaTu, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                            .addComponent(txtDen))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PanelTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbVND1)
                            .addComponent(lbVND))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(PanelTimKiemLayout.createSequentialGroup()
                        .addComponent(lbLoai)
                        .addGap(28, 28, 28)
                        .addComponent(cbKichThuoc, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(PanelTimKiemLayout.createSequentialGroup()
                        .addComponent(lbNhom)
                        .addGap(18, 18, 18)
                        .addComponent(cbLoaiTK, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        PanelTimKiemLayout.setVerticalGroup(
            PanelTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelTimKiemLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cbTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtGiaTu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbVND)
                    .addComponent(lbGiaTu))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbDen)
                    .addComponent(txtDen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbVND1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PanelTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbNhom)
                    .addComponent(cbLoaiTK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbKichThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbLoai))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        cbChonTimKiem.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "---T??m ki???m theo---", "T??n", "Gi??", "Nh??m" }));
        cbChonTimKiem.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        cbChonTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbChonTimKiemActionPerformed(evt);
            }
        });

        btnTimKiem.setText("T??m ki???m");
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel9)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel2)
                                            .addComponent(jLabel1)
                                            .addComponent(jLabel3))
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                            .addGap(10, 10, 10)
                                            .addComponent(jLabel7))))
                                .addGap(49, 49, 49)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtPrice)
                                    .addComponent(txtIDProduct)
                                    .addComponent(txtNameproduct)
                                    .addComponent(cbNameType, 0, 190, Short.MAX_VALUE)
                                    .addComponent(cbKichCo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(46, 46, 46)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(PanelTimKiem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbChonTimKiem, 0, 232, Short.MAX_VALUE)
                                    .addComponent(btnTimKiem)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 513, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(13, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtIDProduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbChonTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtNameproduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(cbNameType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(cbKichCo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(PanelTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnTimKiem)))
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("S???n Ph???m", jPanel3);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(94, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 715, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 677, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(129, 129, 129)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(129, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddSPActionPerformed

// TODO add your handling code here:
        int line = tblModelSP.getRowCount();//diem quet so dong kiem tra trung nhau
        int click = JOptionPane.showConfirmDialog(null, "B???n c?? mu???n th??m s???n ph???m kh??ng?");
        if (click == 0) {
            try {
                psAdd1 = conLoaiSP.prepareStatement("select * from ProductType where TypeName=? and Size=?");
                psAdd1.setString(1, (String) cbNameType.getSelectedItem());
                psAdd1.setString(2, (String) cbKichCo.getSelectedItem());
                rsAdd1 = psAdd1.executeQuery();
                if (rsAdd1.next()) {
                    while (true) {
                        for (int i = 0; i < line; i++) {
                            if (txtIDProduct.getText().trim().equals(tblModelSP.getValueAt(i, 0))) {
                                JOptionPane.showMessageDialog(this, "ID s???n ph???m tr??ng");
                                txtIDProduct.grabFocus();
                                return;
                            }
                        }

                        if (txtIDProduct.getText().trim().equals("")) {
                            JOptionPane.showMessageDialog(this, "ID kh??ng ???????c ????? tr???ng");
                            txtIDProduct.grabFocus();
                            return;
                        } else if (!txtIDProduct.getText().trim().matches("[A-Za-z]{2}\\d{2,2}")) {
                            JOptionPane.showMessageDialog(this, "ID ph???i b???t ?????u b???ng 2 ch??? v?? 2 s??? ");
                            txtIDProduct.grabFocus();
                            return;
                        } else {
                            break;
                        }
                    }

                    while (true) {
                        if (txtNameproduct.getText().trim().equals("")) {
                            JOptionPane.showMessageDialog(this, "T??n kh??ng ???????c ????? tr???ng");
                            txtNameproduct.grabFocus();
                            return;
                        } else {
                            break;
                        }
                    }
                    while (true) {
                        if (txtPrice.getText().trim().equals("")) {
                            JOptionPane.showMessageDialog(this, "Gi?? kh??ng ???????c ????? tr???ng");
                            txtPrice.grabFocus();
                            return;
                        } else if (!txtPrice.getText().trim().matches("[0-9]+")) {
                            JOptionPane.showMessageDialog(this, "Gi?? ph???i l?? s??? v?? l???n h??n 0");
                            txtPrice.grabFocus();
                            return;
                        } else if ((Integer.parseInt(txtPrice.getText())) <= 0 || Integer.parseInt(txtPrice.getText()) > 200000) {
                            JOptionPane.showMessageDialog(this, "Gi?? ph???i l???n h??n 0 v?? nh??? h??n 200.000");
                            txtPrice.grabFocus();
                            return;
                        } else {
                            break;
                        }
                    }
                    int line1 = tblSP.getRowCount();
                    while (true) {
                        String sql3 = "select * from ProductType where TypeName=? and Size=?";
                        String Loaisp, Size, IDType;
                        psSP = conSP.prepareStatement(sql3);
                        psSP.setString(1, (String) cbNameType.getSelectedItem());
                        psSP.setString(2, (String) cbKichCo.getSelectedItem());
                        rsSP = psSP.executeQuery();
                        if (rsSP.next()) {
                            IDType = rsSP.getString(1);
                            for (int i = 0; i < line1; i++) {
                                if (txtNameproduct.getText().equals(tblSP.getValueAt(i, 1)) && tblSP.getValueAt(i, 2).equals(IDType) && cbKichCo.getSelectedItem().equals(tblSP.getValueAt(i, 4))) {
                                    JOptionPane.showMessageDialog(null, "S???n ph???m ???? t???n t???i");
                                    return;
                                }
                            }
                            break;
                        }
                    }

                    String sql = "insert into Product values(?,?,?,?)";
                    psAdd2 = conSP.prepareStatement(sql);
                    psAdd2.setString(1, txtIDProduct.getText());
                    psAdd2.setString(2, txtNameproduct.getText());
                    psAdd2.setString(3, rsAdd1.getString("IDType"));//cbBox
                    psAdd2.setInt(4, Integer.parseInt(txtPrice.getText()));//kieu int
                    psAdd2.executeUpdate();
                    tblModelSP.getDataVector().removeAllElements();
                    loadDataSP();
                    btnResetActionPerformed(null);
                    JOptionPane.showMessageDialog(null, "Th??m s???n ph???m th??nh c??ng");
                } else {
                    JOptionPane.showMessageDialog(null, "Kh??ng t??m th???y nh??m s???n ph???m.");
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }//GEN-LAST:event_btnAddSPActionPerformed

    private void btnResetSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetSPActionPerformed
        // TODO add your handling code here:
        txtIDProduct.setText("");
        txtNameproduct.setText("");
        txtPrice.setText("");
        cbNameType.setSelectedIndex(0);//re set combobox
        cbKichCo.setSelectedIndex(0);//re set combobox
        txtIDProduct.setEnabled(true);
        btnDeleteSP.setEnabled(false);
        btnUpdateSP.setEnabled(false);
        btnAddSP.setEnabled(true);
        cbChonTimKiem.setSelectedIndex(0);
    }//GEN-LAST:event_btnResetSPActionPerformed

    private void btnUpdateSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateSPActionPerformed
        // TODO add your handling code here:
        int a = JOptionPane.showConfirmDialog(null, "B???n c?? mu???n s???a s???n ph???m kh??ng?");
        if (a == 0) {

            try {
                String sql2 = "select * from ProductType where TypeName=? and Size=?";
                psLoaiSP = conSP.prepareStatement(sql2);
                psLoaiSP.setString(1, (String) cbNameType.getSelectedItem());
                psLoaiSP.setString(2, (String) cbKichCo.getSelectedItem());
                rsLoaiSP = psLoaiSP.executeQuery();
                if (rsLoaiSP.next()) {
                    String sql = "update Product set ProductName=?, IDType=?, Price=? where IDProduct=?";
                    psSP = conSP.prepareStatement(sql);
                    psSP.setString(1, txtNameproduct.getText());
                    psSP.setString(2, rsLoaiSP.getString(1));//cbBox
                    psSP.setInt(3, Integer.parseInt(txtPrice.getText()));//kieu int
                    psSP.setString(4, txtIDProduct.getText());
                    psSP.executeUpdate();
                    tblModelSP.getDataVector().removeAllElements();
                    loadDataSP();
                    btnResetActionPerformed(null);
                    JOptionPane.showMessageDialog(null, "S???a s???n ph???m th??nh c??ng");
                } else {
                    JOptionPane.showMessageDialog(null, "Kh??ng t??m th???y nh??m s???n ph???m.");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }//GEN-LAST:event_btnUpdateSPActionPerformed

    private void btnDeleteSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteSPActionPerformed
        // TODO add your handling code here:
        int de = JOptionPane.showConfirmDialog(null, "B???n c?? mu???n x??a s???n ph???m kh??ng?");
        if (de == 0) {
            try {
                String sql1 = "select * from OrderDetails where IDProduct=?";
                psSP = conSP.prepareStatement(sql1);
                psSP.setString(1, txtIDProduct.getText());
                rsIDPr = psSP.executeQuery();
                if (rsIDPr.next()) {
                    JOptionPane.showMessageDialog(null, "S???n ph???m ???? c?? ????n h??ng, kh??ng th??? x??a s???n ph???m.");
                } else {
                    try {
                        String sql = "Delete Product where IDProduct=?";
                        psSP = conSP.prepareStatement(sql);
                        psSP.setString(1, txtIDProduct.getText());
                        psSP.executeUpdate();
                        tblModelSP.getDataVector().removeAllElements();
                        loadDataSP();
                        btnResetActionPerformed(null);
                        JOptionPane.showMessageDialog(null, "X??a s???n ph???m th??nh c??ng.");
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, e);
                    }
                }
            } catch (SQLException e) {
            }

        }
    }//GEN-LAST:event_btnDeleteSPActionPerformed

    private void tblSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSPMouseClicked
        // TODO add your handling code here:
        String DepName = null;
        btnDeleteSP.setEnabled(true);
        btnUpdateSP.setEnabled(true);
        btnAddSP.setEnabled(false);
        int line = tblSP.getSelectedRow();
        String ID = (String) tblModelSP.getValueAt(line, 0);
        String Name = (String) tblModelSP.getValueAt(line, 1);
        String Type = (String) tblModelSP.getValueAt(line, 2);
        String Price = (String) tblModelSP.getValueAt(line, 3);
        String Size = (String) tblModelSP.getValueAt(line, 4);
        //gan du lieu vao textfile
        txtIDProduct.setText(ID);
        txtNameproduct.setText(Name);
        try {
            String sql = "select * from ProductType where IDType=?";
            psSP = conSP.prepareStatement(sql);
            psSP.setString(1, Type);
            rsSP = psSP.executeQuery();
            if (rsSP.next()) {
                DepName = rsSP.getString(2);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        cbNameType.setSelectedItem(DepName);
//        cbNameType.setSelectedItem(Type);
        txtPrice.setText(Price);
        cbKichCo.setSelectedItem(Size);
        txtIDProduct.setEnabled(false);
    }//GEN-LAST:event_tblSPMouseClicked

    private void tblLoaiSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblLoaiSPMouseClicked
        // TODO add your handling code here:
        btnXoaLoaiSP.setEnabled(true);
        btnSuaLoaiSP.setEnabled(true);
        btnThemLoaiSP.setEnabled(false);
        int line1 = tblLoaiSP.getSelectedRow();
        String ID1 = (String) tblModelLoaiSP.getValueAt(line1, 0);
        String Name1 = (String) tblModelLoaiSP.getValueAt(line1, 1);
        String Size = (String) tblModelLoaiSP.getValueAt(line1, 2);
        //gan du lieu vao textfile
        txtIDProductType.setText(ID1);
        txtNameType.setText(Name1);
        cbSizeType.setSelectedItem(Size);
        txtIDProductType.setEnabled(false);
    }//GEN-LAST:event_tblLoaiSPMouseClicked

    private void btnXoaLoaiSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaLoaiSPActionPerformed
        // TODO add your handling code here:
        String xoa = "select * from Product where IDType=?";

        try {
            psLoaiSP = conLoaiSP.prepareStatement(xoa);
            psLoaiSP.setString(1, txtIDProductType.getText());
            rsLoaiSP = psLoaiSP.executeQuery();
            if (!rsLoaiSP.next()) {
                int click = JOptionPane.showConfirmDialog(null, "B???n c?? mu???n x??a lo???i s???n ph???m kh??ng?");
                if (click == 0) {
                    try {
                        String sql = "Delete ProductType where IDType=?";
                        psLoaiSP = conLoaiSP.prepareStatement(sql);
                        psLoaiSP.setString(1, txtIDProductType.getText());
                        psLoaiSP.executeUpdate();
                        tblModelLoaiSP.getDataVector().removeAllElements();
                        loadDataLoaiSP();
                        btnResetActionPerformed(null);
                        JOptionPane.showMessageDialog(null, "X??a lo???i s???n ph???m th??nh c??ng");
                    } catch (Exception e) {
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "X??a lo???i s???n ph???m kh??ng th??nh c??ng");
                }
            } else {
                JOptionPane.showMessageDialog(null, "S???n ph???m c??n t???n t???i, kh??ng th??? x??a");
            }
        } catch (SQLException ex) {
        }
        tblModelLoaiSP.getDataVector().removeAllElements();
        loadDataLoaiSP();
    }//GEN-LAST:event_btnXoaLoaiSPActionPerformed

    private void btnSuaLoaiSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaLoaiSPActionPerformed
        // TODO add your handling code here:

        JOptionPane.showConfirmDialog(null, "B???n c?? mu???n s???a lo???i s???n ph???m kh??ng?");
        if (JOptionPane.YES_OPTION == 0) {
            try {
                String sql = "update ProductType set TypeName=?, Size=? where IDType=?";
                psLoaiSP = conLoaiSP.prepareStatement(sql);
                psLoaiSP.setString(1, txtNameType.getText());
                psLoaiSP.setString(2, (String) cbSizeType.getSelectedItem());
                psLoaiSP.setString(3, txtIDProductType.getText());
                psLoaiSP.executeUpdate();
                tblModelLoaiSP.getDataVector().removeAllElements();
                loadDataLoaiSP();
                btnResetActionPerformed(null);
                JOptionPane.showMessageDialog(null, "S???a lo???i s???n ph???m th??nh c??ng");
                tblModelLoaiSP.getDataVector().removeAllElements();
                loadDataLoaiSP();
            } catch (Exception e) {
            }
        } else {
            JOptionPane.showMessageDialog(null, "S???a lo???i s???n ph???m kh??ng th??nh c??ng");
        }
    }//GEN-LAST:event_btnSuaLoaiSPActionPerformed

    private void btnResetLoaiSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetLoaiSPActionPerformed
        txtIDProductType.setText("");
        txtNameType.setText("");
        cbSizeType.setSelectedIndex(0);//re set combobox
        txtIDProductType.setEnabled(true);
        btnXoaLoaiSP.setEnabled(false);
        btnSuaLoaiSP.setEnabled(false);
        btnThemLoaiSP.setEnabled(true);
    }//GEN-LAST:event_btnResetLoaiSPActionPerformed

    private void btnThemLoaiSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemLoaiSPActionPerformed
        // TODO add your handling code here:
        int line = tblModelLoaiSP.getRowCount();
        int click = JOptionPane.showConfirmDialog(null, "B???n c?? mu???n th??m lo???i s???n ph???m kh??ng?");
        if (click == 0) {
            try {
                while (true) {
                    for (int i = 0; i < line; i++) {
                        if (txtIDProductType.getText().trim().equals(tblModelLoaiSP.getValueAt(i, 0))) {
                            JOptionPane.showMessageDialog(this, "ID tr??ng");
                            txtIDProductType.grabFocus();
                            return;
                        }
                    }
                    if (txtIDProductType.getText().trim().equals("")) {
                        JOptionPane.showMessageDialog(this, "ID kh??ng ???????c ????? tr???ng");
                        txtIDProductType.grabFocus();
                        return;
                    } else if (!txtIDProductType.getText().trim().matches("T[0-9]{2}")) {
                        JOptionPane.showMessageDialog(this, "ID ph???i b???t ?????u b???ng ch??? T v?? 2 s??? ");
                        txtIDProductType.grabFocus();
                        return;
                    } else {
                        break;
                    }
                }

                while (true) {
                    String sql2 = "select * from ProductType";
                    psLoaiSP = conSP.prepareStatement(sql2);
//                psLoaiSP.setString(1, (String) cbNameType.getSelectedItem());
//                psLoaiSP.setString(2, (String) cbKichCo.getSelectedItem());
                    rsLoaiSP = psLoaiSP.executeQuery();
                    if (rsLoaiSP.next()) {
                        String idprotype = rsLoaiSP.getString(1);

                    }
                    for (int i = 0; i < line; i++) {
                        if (txtNameType.getText().equals(tblModelLoaiSP.getValueAt(i, 1))
                                && (cbSizeType.getSelectedItem().equals(tblModelLoaiSP.getValueAt(i, 2)))) {
                            JOptionPane.showMessageDialog(this, "Lo???i s???n ph???m ???? t???n t???i");
                            txtNameType.grabFocus();
                            return;
                        }
                    }

                    if (txtNameType.getText().trim().equals("")) {
                        JOptionPane.showMessageDialog(this, "T??n kh??ng ???????c ????? tr???ng");
                        txtNameType.grabFocus();
                        return;
                    } else {
                        break;
                    }
                }
                String sql = "insert into ProductType values(?,?,?)";
                psLoaiSP = conLoaiSP.prepareStatement(sql);
                psLoaiSP.setString(1, txtIDProductType.getText());
                psLoaiSP.setString(2, txtNameType.getText());
                psLoaiSP.setString(3, (String) cbSizeType.getSelectedItem());//cbBox
                psLoaiSP.executeUpdate();
                tblModelLoaiSP.getDataVector().removeAllElements();
                loadDataLoaiSP();
                btnResetActionPerformed(null);
                JOptionPane.showMessageDialog(null, "Th??m lo???i s???n ph???m th??nh c??ng");
            } catch (Exception e) {
            }
        }
    }//GEN-LAST:event_btnThemLoaiSPActionPerformed

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked
        // TODO add your handling code here:
        loadDataSP();
    }//GEN-LAST:event_jTabbedPane1MouseClicked

    private void cbChonTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbChonTimKiemActionPerformed
        PanelTimKiem.setVisible(true);
        if (cbChonTimKiem.getSelectedIndex() == 0) {
            cbTen.setVisible(false);
            txtGiaTu.setVisible(false);
            txtDen.setVisible(false);
            cbLoaiTK.setVisible(false);
            cbKichThuoc.setVisible(false);
            btnTimKiem.setVisible(false);
            lbDen.setVisible(false);
            lbGiaTu.setVisible(false);
            lbLoai.setVisible(false);
            lbNhom.setVisible(false);
            lbVND1.setVisible(false);
            lbVND.setVisible(false);

        } else if (cbChonTimKiem.getSelectedItem().equals("T??n")) {
            cbTen.setVisible(true);
            txtGiaTu.setVisible(false);
            txtDen.setVisible(false);
            cbLoaiTK.setVisible(false);
            cbKichThuoc.setVisible(false);
            btnTimKiem.setVisible(true);
            lbDen.setVisible(false);
            lbGiaTu.setVisible(false);
            lbLoai.setVisible(false);
            lbNhom.setVisible(false);
            lbVND1.setVisible(false);
            lbVND.setVisible(false);

            cbTen.removeAllItems();
            try {
                String url = "Select DISTINCT ProductName from Product";
                psTen = conSP.prepareStatement(url);
                rsTen = psTen.executeQuery();
                vecTen = new Vector();
                while (rsTen.next()) {
                    vecTen.add(rsTen.getString("ProductName"));
                }
                JTextField text = (JTextField) cbTen.getEditor().getEditorComponent();
                text.setText("");
                text.addKeyListener(new ComboListener(cbTen, vecTen));
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "L???i 101:: Kh??ng th??? k???t n???i ?????n m??y ch???");
            }
        } else if (cbChonTimKiem.getSelectedItem().equals("Gi??")) {
            txtGiaTu.setVisible(true);
            txtDen.setVisible(true);
            cbTen.setVisible(false);
            cbLoaiTK.setVisible(false);
            cbKichThuoc.setVisible(false);
            cbTen.setVisible(false);
            btnTimKiem.setVisible(true);

            lbDen.setVisible(true);
            lbGiaTu.setVisible(true);
            lbLoai.setVisible(false);
            lbNhom.setVisible(false);
            lbVND1.setVisible(true);
            lbVND.setVisible(true);
        } else if (cbChonTimKiem.getSelectedItem().equals("Nh??m")) {
            txtGiaTu.setVisible(false);
            txtDen.setVisible(false);
            cbTen.setVisible(false);
            cbLoaiTK.setVisible(true);
            cbKichThuoc.setVisible(true);
            btnTimKiem.setVisible(true);
            lbDen.setVisible(false);
            lbGiaTu.setVisible(false);
            lbLoai.setVisible(true);
            lbNhom.setVisible(true);
            lbVND1.setVisible(false);
            lbVND.setVisible(false);

            cbLoaiTK.removeAllItems();
            try {
                String sql = "select Distinct TypeName from ProductType";
                psSP = conSP.prepareStatement(sql);
                rsSP = psSP.executeQuery();
                while (rsSP.next()) {
                    cbLoaiTK.addItem(rsSP.getString(1));
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }//GEN-LAST:event_cbChonTimKiemActionPerformed

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        if (cbChonTimKiem.getSelectedItem().equals("T??n")) {
            tblModelSP.getDataVector().removeAllElements();
            try {
                psTK = conSP.prepareStatement("select * from Product inner join ProductType on Product.IDType=ProductType.IDType where ProductName=?");
                psTK.setString(1, (String) cbTen.getSelectedItem());
                rsTK = psTK.executeQuery();
                if (rsTK.next()) {
                    psTK = conSP.prepareStatement("select * from Product inner join ProductType on Product.IDType=ProductType.IDType where ProductName=?");
                    psTK.setString(1, (String) cbTen.getSelectedItem());
                    rsTK = psTK.executeQuery();
                    while (rsTK.next()) {
                        rowTen = new Vector();
                        rowTen.add(rsTK.getString("IDProduct"));
                        rowTen.add(rsTK.getString("ProductName"));
                        rowTen.add(rsTK.getString("IDType"));
                        rowTen.add(rsTK.getString("Price"));
                        rowTen.add(rsTK.getString("Size"));
                        tblModelSP.addRow(rowTen);
                    }
                    tblSP.setModel(tblModelSP);
                } else {
                    JOptionPane.showMessageDialog(null, "Kh??ng t??m ???????c s???n ph???m");
                    loadDataSP();
                }
            } catch (Exception e) {
            }
        } else if (cbChonTimKiem.getSelectedItem().equals("Gi??")) {
            while (true) {
                if (txtGiaTu.getText().trim().equals("")) {
                    JOptionPane.showMessageDialog(this, "Gi?? kh??ng ???????c ????? tr???ng");
                    txtGiaTu.grabFocus();
                    return;
                } else if (!txtGiaTu.getText().trim().matches("[0-9]+")) {
                    JOptionPane.showMessageDialog(this, "Gi?? ph???i l?? s???");
                    txtGiaTu.grabFocus();
                    return;
                } else if ((Integer.parseInt(txtGiaTu.getText())) <= 0 || Integer.parseInt(txtGiaTu.getText()) > 200000) {
                    JOptionPane.showMessageDialog(this, "Gi?? ph???i l???n h??n 0 v?? nh??? h??n 200.000");
                    txtGiaTu.grabFocus();
                    return;
                } else {
                    break;
                }
            }
            while (true) {
                if (txtDen.getText().trim().equals("")) {
                    JOptionPane.showMessageDialog(this, "Gi?? kh??ng ???????c ????? tr???ng");
                    txtDen.grabFocus();
                    return;
                } else if (!txtDen.getText().trim().matches("[0-9]+")) {
                    JOptionPane.showMessageDialog(this, "Gi?? ph???i l?? s???");
                    txtDen.grabFocus();
                    return;
                } else if ((Integer.parseInt(txtDen.getText())) <= 0 || Integer.parseInt(txtDen.getText()) > 200000) {
                    JOptionPane.showMessageDialog(this, "Gi?? ph???i l???n h??n 0 v?? nh??? h??n 200.000");
                    txtPrice.grabFocus();
                    return;
                } else if ((Integer.parseInt(txtGiaTu.getText())) >= Integer.parseInt(txtDen.getText())) {
                    JOptionPane.showMessageDialog(this, "Gi?? ph???i t??? nh??? ?????n l???n");
                    txtDen.grabFocus();
                    return;
                } else {
                    break;
                }
            }
            tblModelSP.getDataVector().removeAllElements();
            try {
                psTK = conSP.prepareStatement("select * from Product inner join ProductType on Product.IDType=ProductType.IDType where Price > ? and Price < ?");
                psTK.setInt(1, Integer.parseInt(txtGiaTu.getText().trim()));
                psTK.setInt(2, Integer.parseInt(txtDen.getText().trim()));
                rsTK = psTK.executeQuery();
                if (rsTK.next()) {
                    psTK = conSP.prepareStatement("select * from Product inner join ProductType on Product.IDType=ProductType.IDType where Price > ? and Price < ?");
                    psTK.setInt(1, Integer.parseInt(txtGiaTu.getText().trim()));
                    psTK.setInt(2, Integer.parseInt(txtDen.getText().trim()));
                    rsTK = psTK.executeQuery();
                    while (rsTK.next()) {
                        rowTen = new Vector();
                        rowTen.add(rsTK.getString("IDProduct"));
                        rowTen.add(rsTK.getString("ProductName"));
                        rowTen.add(rsTK.getString("IDType"));
                        rowTen.add(rsTK.getString("Price"));
                        rowTen.add(rsTK.getString("Size"));
                        tblModelSP.addRow(rowTen);
                    }
                    tblSP.setModel(tblModelSP);
                } else {
                    JOptionPane.showMessageDialog(null, "Kh??ng t??m ???????c s???n ph???m");
                    loadDataSP();
                }
            } catch (Exception e) {
            }
        } else if (cbChonTimKiem.getSelectedItem().equals("Nh??m")) {
            tblModelSP.getDataVector().removeAllElements();
            try {
                psTK = conSP.prepareStatement("select * from Product inner join ProductType on Product.IDType=ProductType.IDType where TypeName= ? and Size= ?");
                psTK.setString(1, (String) cbLoaiTK.getSelectedItem());
                psTK.setString(2, (String) cbKichThuoc.getSelectedItem());
                rsTK = psTK.executeQuery();
                if (rsTK.next()) {
                    psTK = conSP.prepareStatement("select * from Product inner join ProductType on Product.IDType=ProductType.IDType where TypeName= ? and Size= ?");
                    psTK.setString(1, (String) cbLoaiTK.getSelectedItem());
                    psTK.setString(2, (String) cbKichThuoc.getSelectedItem());
                    rsTK = psTK.executeQuery();
                    while (rsTK.next()) {
                        rowTen = new Vector();
                        rowTen.add(rsTK.getString("IDProduct"));
                        rowTen.add(rsTK.getString("ProductName"));
                        rowTen.add(rsTK.getString("IDType"));
                        rowTen.add(rsTK.getString("Price"));
                        rowTen.add(rsTK.getString("Size"));
                        tblModelSP.addRow(rowTen);
                    }
                    tblSP.setModel(tblModelSP);
                } else {
                    JOptionPane.showMessageDialog(null, "Kh??ng t??m ???????c s???n ph???m");
                    loadDataSP();
                }
            } catch (Exception e) {
            }
        }
    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void btnThongkeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThongkeActionPerformed
        new ThongKe().setVisible(true);
    }//GEN-LAST:event_btnThongkeActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelTimKiem;
    private javax.swing.JButton btnAddSP;
    private javax.swing.JButton btnDeleteSP;
    private javax.swing.JButton btnResetLoaiSP;
    private javax.swing.JButton btnResetSP;
    private javax.swing.JButton btnSuaLoaiSP;
    private javax.swing.JButton btnThemLoaiSP;
    private javax.swing.JButton btnThongke;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btnUpdateSP;
    private javax.swing.JButton btnXoaLoaiSP;
    private javax.swing.JComboBox cbChonTimKiem;
    private javax.swing.JComboBox cbKichCo;
    private javax.swing.JComboBox cbKichThuoc;
    private javax.swing.JComboBox cbLoaiTK;
    private javax.swing.JComboBox cbNameType;
    private javax.swing.JComboBox cbSizeType;
    private javax.swing.JComboBox cbTen;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lbDen;
    private javax.swing.JLabel lbGiaTu;
    private javax.swing.JLabel lbLoai;
    private javax.swing.JLabel lbNhom;
    private javax.swing.JLabel lbVND;
    private javax.swing.JLabel lbVND1;
    private javax.swing.JTable tblLoaiSP;
    private javax.swing.JTable tblSP;
    private javax.swing.JTextField txtDen;
    private javax.swing.JTextField txtGiaTu;
    private javax.swing.JTextField txtIDProduct;
    private javax.swing.JTextField txtIDProductType;
    private javax.swing.JTextField txtNameType;
    private javax.swing.JTextField txtNameproduct;
    private javax.swing.JTextField txtPrice;
    // End of variables declaration//GEN-END:variables

    private void btnResetActionPerformed(Object object) {
        txtIDProduct.setText("");
        txtNameproduct.setText("");
        txtPrice.setText("");
        cbNameType.setSelectedIndex(0);//re set combobox
        txtIDProduct.setEnabled(true);
        btnDeleteSP.setEnabled(false);
        btnUpdateSP.setEnabled(false);
        btnAddSP.setEnabled(true);
    }
}
