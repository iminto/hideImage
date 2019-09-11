package tony;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class HiJFrame extends javax.swing.JFrame {

    private String imagePath;
    private ImageHide rc;

    public HiJFrame() {
        initComponents();
        rc = new ImageHide();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        hideText = new javax.swing.JTextPane();
        hideBtn = new javax.swing.JButton();
        recBtn = new javax.swing.JButton();
        imgShowLabel = new javax.swing.JLabel();
        statusBar = new javax.swing.JPanel();
        statusLabel = new javax.swing.JLabel();
        fileMenuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        imgOpen = new javax.swing.JMenuItem();
        txtOpen = new javax.swing.JMenuItem();
        appExit = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        aboutMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setIconImages(null);

        hideText.setFont(new java.awt.Font("宋体", 0, 14)); // NOI18N
        hideText.setText("输入要隐写的内容或从文件菜单中加载本地文本文件");
        jScrollPane2.setViewportView(hideText);

        hideBtn.setFont(new java.awt.Font("宋体", 1, 14)); // NOI18N
        hideBtn.setForeground(new java.awt.Color(0, 204, 204));
        hideBtn.setLabel("隐藏");
        hideBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hideBtnActionPerformed(evt);
            }
        });

        recBtn.setFont(new java.awt.Font("宋体", 1, 14)); // NOI18N
        recBtn.setLabel("提取");
        recBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                recBtnActionPerformed(evt);
            }
        });

        statusBar.setToolTipText("");

        statusLabel.setToolTipText("");

        javax.swing.GroupLayout statusBarLayout = new javax.swing.GroupLayout(statusBar);
        statusBar.setLayout(statusBarLayout);
        statusBarLayout.setHorizontalGroup(
            statusBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(statusLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        statusBarLayout.setVerticalGroup(
            statusBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(statusLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        fileMenuBar.setName("fileMenuBar"); // NOI18N

        fileMenu.setLabel("文件");

        imgOpen.setLabel("选择图片");
        imgOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                imgOpenActionPerformed(evt);
            }
        });
        fileMenu.add(imgOpen);

        txtOpen.setLabel("选择文本文件");
        txtOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtOpenActionPerformed(evt);
            }
        });
        fileMenu.add(txtOpen);

        appExit.setText("退出");
        appExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                appExitActionPerformed(evt);
            }
        });
        fileMenu.add(appExit);

        fileMenuBar.add(fileMenu);

        helpMenu.setLabel("帮助");

        aboutMenuItem.setLabel("关于");
        aboutMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutMenuItemActionPerformed(evt);
            }
        });
        helpMenu.add(aboutMenuItem);

        fileMenuBar.add(helpMenu);

        setJMenuBar(fileMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(imgShowLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(hideBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(recBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(30, 30, 30))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(statusBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(hideBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(recBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(imgShowLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(statusBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtOpenActionPerformed
        JFileChooser txtChooser = new JFileChooser();
        txtChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        txtChooser.setFileFilter(new TxtCanChoose());
        int returnVal = txtChooser.showDialog(new JLabel(), "选择");
        if (returnVal == txtChooser.APPROVE_OPTION) {
            File file = txtChooser.getSelectedFile();
            String content = readFile(file.getAbsolutePath());
            hideText.setText(content);
        }
    }//GEN-LAST:event_txtOpenActionPerformed

    private void imgOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_imgOpenActionPerformed
        JFileChooser pngChooser = new JFileChooser();
        pngChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        pngChooser.setFileFilter(new ImgCanChoose());
        int returnVal = pngChooser.showDialog(new JLabel(), "选择");
        if (returnVal == pngChooser.APPROVE_OPTION) {
            File file = pngChooser.getSelectedFile();
            imagePath = file.getPath();
            try {
                rc.addPng(imagePath);
                imgShowLabel.setIcon(new ImageIcon(file.getPath()));
                long size = file.length();
                int enable=rc.getEnabled();
                statusLabel.setText("图片大小：" + size+"，可隐写字节数："+enable+"(1中文=3字节)");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "错误 ", 0);
            }
        }
    }//GEN-LAST:event_imgOpenActionPerformed

    private void appExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_appExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_appExitActionPerformed

    private void aboutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutMenuItemActionPerformed
        new AboutFrame().setVisible(true);
    }//GEN-LAST:event_aboutMenuItemActionPerformed

    private void hideBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hideBtnActionPerformed
        if (!ImageUtil.isNotEmpty(hideText.getText()) || !ImageUtil.isNotEmpty(imagePath)) {
            JOptionPane.showMessageDialog(this, "图片和文字不可为空！", "错误 ", 0);
        } else {
            try {
                rc.addText(hideText.getText());
                rc.Hide();
                JOptionPane.showMessageDialog(this, "隐写图片已生成至原目录中！", "成功 ", 1);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "错误 ", 0);
            }
        }
    }//GEN-LAST:event_hideBtnActionPerformed

    private void recBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_recBtnActionPerformed
        if (!ImageUtil.isNotEmpty(imagePath)) {
            JOptionPane.showMessageDialog(this, "要提取的图片不可为空！", "错误 ", 0);
        } else {
            try {
                String extractStr = rc.ImageExtract(imagePath);
                hideText.setText(extractStr);
                JOptionPane.showMessageDialog(this, "隐写在图片中的文字已提取到文本框中！", "成功 ", 1);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "隐写文字提取失败！\r\n可能你輸入的图片并非本程序生成", "错误 ", 0);
            }
        }
    }//GEN-LAST:event_recBtnActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                HiJFrame jframe = new HiJFrame();
                jframe.setVisible(true);
                jframe.setTitle("图片隐写助手");
                jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                jframe.setLocationRelativeTo(null);
                ImageIcon icon = new ImageIcon("static/icon.png");
                jframe.setIconImage(icon.getImage());
            }
        });
    }

    public static String readFile(String filePath) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(filePath)), "UTF-8");
            return content;
        } catch (IOException ex) {
            Logger.getLogger(HiJFrame.class.getName()).log(Level.SEVERE, "读取文本文件内容出错", ex);
            return "";
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JMenuItem appExit;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenuBar fileMenuBar;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JButton hideBtn;
    private javax.swing.JTextPane hideText;
    private javax.swing.JMenuItem imgOpen;
    private javax.swing.JLabel imgShowLabel;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton recBtn;
    private javax.swing.JPanel statusBar;
    private javax.swing.JLabel statusLabel;
    private javax.swing.JMenuItem txtOpen;
    // End of variables declaration//GEN-END:variables
}
