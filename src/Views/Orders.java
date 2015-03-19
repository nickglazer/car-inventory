/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

/**
 *
 * @author Nicholas
 */
public class Orders extends javax.swing.JPanel {

    /**
     * Creates new form Orders
     */
    public Orders() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jlCarID = new javax.swing.JLabel();
        jlDownPayment = new javax.swing.JLabel();
        jlBank = new javax.swing.JLabel();
        jlLoanNumber = new javax.swing.JLabel();
        jlLoanDuration = new javax.swing.JLabel();
        jlOrderID = new javax.swing.JLabel();
        jlDate = new javax.swing.JLabel();
        jtfDownPayment = new javax.swing.JTextField();
        jtfCarID = new javax.swing.JTextField();
        jtfBank = new javax.swing.JTextField();
        jtfLoanNumber = new javax.swing.JTextField();
        jtfLoanDuration = new javax.swing.JTextField();
        jtfOrderID = new javax.swing.JTextField();
        jtfDate = new javax.swing.JTextField();
        jbViewCustomer = new javax.swing.JButton();
        jbSave = new javax.swing.JButton();
        jbAddCustomer = new javax.swing.JButton();

        jlCarID.setText("Car ID");

        jlDownPayment.setText("Down Payment");

        jlBank.setText("Bank");

        jlLoanNumber.setText("Loan Number");

        jlLoanDuration.setText("Loan Duration");

        jlOrderID.setText("Order ID");

        jlDate.setText("Date");

        jtfLoanNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfLoanNumberActionPerformed(evt);
            }
        });

        jbViewCustomer.setText("View Customer");

        jbSave.setText("Save");

        jbAddCustomer.setText("Add Customer");
        jbAddCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbAddCustomerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlDownPayment)
                            .addComponent(jlCarID)
                            .addComponent(jlBank)
                            .addComponent(jlLoanNumber)
                            .addComponent(jlLoanDuration)
                            .addComponent(jlOrderID)
                            .addComponent(jlDate))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(90, 90, 90)
                                .addComponent(jbViewCustomer)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbAddCustomer))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jtfDate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                                .addComponent(jtfOrderID, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jtfLoanDuration, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jtfLoanNumber, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jtfBank, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jtfDownPayment, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jtfCarID, javax.swing.GroupLayout.Alignment.LEADING))))
                    .addComponent(jbSave))
                .addContainerGap(277, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbViewCustomer)
                    .addComponent(jbAddCustomer))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlCarID)
                    .addComponent(jtfCarID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlDownPayment)
                    .addComponent(jtfDownPayment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlBank)
                    .addComponent(jtfBank, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfLoanNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlLoanNumber))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfLoanDuration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlLoanDuration))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlOrderID)
                    .addComponent(jtfOrderID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlDate)
                    .addComponent(jtfDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 142, Short.MAX_VALUE)
                .addComponent(jbSave)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jbAddCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbAddCustomerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbAddCustomerActionPerformed

    private void jtfLoanNumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfLoanNumberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfLoanNumberActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbAddCustomer;
    private javax.swing.JButton jbSave;
    private javax.swing.JButton jbViewCustomer;
    private javax.swing.JLabel jlBank;
    private javax.swing.JLabel jlCarID;
    private javax.swing.JLabel jlDate;
    private javax.swing.JLabel jlDownPayment;
    private javax.swing.JLabel jlLoanDuration;
    private javax.swing.JLabel jlLoanNumber;
    private javax.swing.JLabel jlOrderID;
    private javax.swing.JTextField jtfBank;
    private javax.swing.JTextField jtfCarID;
    private javax.swing.JTextField jtfDate;
    private javax.swing.JTextField jtfDownPayment;
    private javax.swing.JTextField jtfLoanDuration;
    private javax.swing.JTextField jtfLoanNumber;
    private javax.swing.JTextField jtfOrderID;
    // End of variables declaration//GEN-END:variables
}