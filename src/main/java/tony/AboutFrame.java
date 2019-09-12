package tony;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.net.URL;

public class AboutFrame extends javax.swing.JFrame {
    public AboutFrame() {
        initComponents();
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("关于");
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("宋体", 0, 14)); // NOI18N
        jLabel1.setText("<html><body>本APP可实现图片隐写功能，将文本写入PNG图片中且不影响图片正常使用，并且可以随时提取。 \n<br>作者：waitfox@qq.com\n<br><a href=\"https://github.com/iminto/hideImage\">https://github.com/iminto/hideImage</a>\n<br>如果中文编码出错，请使用java -jar -Dfile.encoding=UTF-8 hide.jar形式运行</body></html>");
        jLabel1.setToolTipText("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AboutFrame aboutFrame=new AboutFrame();
                aboutFrame.setVisible(true);
                aboutFrame.setTitle("关于");
                aboutFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                URL imUrl = getClass().getResource("/icon.png");
                ImageIcon icon = new ImageIcon(imUrl);
                aboutFrame.setIconImage(icon.getImage());
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
