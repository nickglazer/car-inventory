/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Controllers.Database;
import Controllers.FormHandler;

import java.util.HashMap;
import java.util.Map;

import Models.Car;

import javax.swing.table.DefaultTableModel;

/**
 * @author Nicholas
 */
public class Search extends javax.swing.JPanel
{
    private Car[] currentCars;

    /**
     * Creates new form Search
     */
    public Search() {
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jtfYear = new javax.swing.JTextField();
        jcbMake = new javax.swing.JComboBox();
        jcbModel = new javax.swing.JComboBox();
        jcbTransmission = new javax.swing.JComboBox();
        jcbBodyType = new javax.swing.JComboBox();
        jcbColor = new javax.swing.JComboBox();
        jbBuy = new javax.swing.JButton();
        jcbCarType = new javax.swing.JComboBox();
        jbDelete = new javax.swing.JButton();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null}
                },
                new String[]{
                        "Make", "Model", "Year", "Mileage"
                }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jtfYear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfYearActionPerformed(evt);
            }
        });

        jcbMake.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"-Make-", "Acura", "Chevrolet", "Dodge", "Honda", "Toyota"}));
        jcbMake.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbMakeActionPerformed(evt);
            }
        });

        jcbModel.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"-Model-"}));
        jcbModel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbModelActionPerformed(evt);
            }
        });

        jcbTransmission.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"-Transmission-", "Automatic", "Manual", "Combo"}));
        jcbTransmission.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbTransmissionActionPerformed(evt);
            }
        });

        jcbBodyType.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"-Body-", "2 Door", "4 Door"}));
        jcbBodyType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbBodyTypeActionPerformed(evt);
            }
        });

        jcbColor.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"-Color-", "Black", "Blue", "Brown", "Green", "Orange", "Red", "White", "Yellow"}));
        jcbColor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbColorActionPerformed(evt);
            }
        });

        jbBuy.setText("Buy");
        jbBuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbBuyActionPerformed(evt);
            }
        });

        jcbCarType.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"-Style-", "Coupe", "Hatchback", "Sedan", "Van"}));
        jcbCarType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbCarTypeActionPerformed(evt);
            }
        });

        jbDelete.setText("Delete");
        jbDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jcbMake, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jcbModel, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jtfYear)
                                        .addComponent(jcbTransmission, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jcbBodyType, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jcbColor, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jcbCarType, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(30, 30, 30)
                                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jbDelete)
                                                        .addComponent(jbBuy))))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(jcbMake, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(2, 2, 2)
                                                                .addComponent(jcbModel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jtfYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jcbTransmission, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jcbBodyType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jcbCarType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jcbColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(jbDelete)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                                .addComponent(jbBuy)))
                                .addContainerGap())
        );


        this.showAllCars();

    }// </editor-fold>//GEN-END:initComponents

    private void showAllCars()
    {
        Database database = new Database();

        this.currentCars = database.getAllCars();
        this.performSearch();
    }

    private void performFilter()
    {
        this.setCurrentCars();
        this.performSearch();
    }

    private void performSearch() {

        DefaultTableModel tableModel = (DefaultTableModel) this.jTable1.getModel();

        //Delete all rows 
        tableModel.setRowCount(0);

        if (this.currentCars == null) {
            return;
        }

        for (Car currentCar : this.currentCars) {
            String[] carModel = this.carModel(currentCar);
            tableModel.addRow(carModel);
        }

        this.jTable1.setModel(tableModel);
    }

    private void setCurrentCars()
    {
        String make = FormHandler.stringFromDropDown(jcbMake);
        String model = FormHandler.stringFromDropDown(jcbModel);
        String year = FormHandler.stringFromTextfield(jtfYear);
        String transmission = FormHandler.stringFromDropDown(jcbTransmission);
        String bodyType = FormHandler.stringFromDropDown(jcbBodyType);
        String vehicleType = FormHandler.stringFromDropDown(jcbCarType);
        String color = FormHandler.stringFromDropDown(jcbColor);

        Map stringFields = new HashMap<String, String>();
        Map intFields = new HashMap<String, String>();
        stringFields.put("Make", make);
        stringFields.put("Model", model);
        stringFields.put("Transmission", transmission);
        stringFields.put("Body_Type", bodyType);
        stringFields.put("Vehicle_Type", vehicleType);
        stringFields.put("Color", color);
        intFields.put("Year", year);

        Database database = new Database();
        this.currentCars = database.searchForCars(stringFields, intFields);
    }

    /**
     * Takes a car and returns its car model. A "car model" is an array that is used in the search page's table
     *
     * @param carToModel The car that you're interesting in getting its model
     * @return A string array that represents the car's model.
     */
    private String[] carModel(Car carToModel) {
        String[] model = {carToModel.getMake(), carToModel.getModel(), Integer.toString(carToModel.getYear()), Integer.toString(carToModel.getMileage())};

        return model;
    }

    private void jcbMakeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbMakeActionPerformed
        // TODO add your handling code here:
        this.performFilter();
    }//GEN-LAST:event_jcbMakeActionPerformed

    private void jcbBodyTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbBodyTypeActionPerformed
        // TODO add your handling code here:
        this.performFilter();
    }//GEN-LAST:event_jcbBodyTypeActionPerformed

    private void jbDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbDeleteActionPerformed

        int[] selectedRows = this.jTable1.getSelectedRows();

        int totalRows = this.jTable1.getRowCount();

        if (selectedRows.length == 0) {
            /**
             * If there's nothing selected, let's get out
             * TODO: Show error message
             */
            return;
        }

        DefaultTableModel tableModel = (DefaultTableModel) this.jTable1.getModel();

        String deleteStatement = "delete from Cars where CarID = ";

        int numDeletedRows = 0;
        for (int i = totalRows - 1; i > -1; i--) {
            if (this.jTable1.isRowSelected(i)) {
                Car selectedCar = this.currentCars[i];

                if (numDeletedRows == 0) {
                    deleteStatement += Integer.toString(selectedCar.getCarID());
                } else {
                    deleteStatement += String.format(" or CarID = %d", selectedCar.getCarID());
                }

                tableModel.removeRow(i);
                numDeletedRows++;
            }
        }

        Database database = new Database();

        int affectedRows = database.executeUpdate(deleteStatement);

        this.jTable1.setModel(tableModel);

    }//GEN-LAST:event_jbDeleteActionPerformed

    private void jcbModelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbModelActionPerformed
        // TODO add your handling code here:
        this.performFilter();
    }//GEN-LAST:event_jcbModelActionPerformed

    private void jtfYearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfYearActionPerformed
        // TODO add your handling code here:
        this.performFilter();
    }//GEN-LAST:event_jtfYearActionPerformed

    private void jcbTransmissionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbTransmissionActionPerformed
        // TODO add your handling code here:
        this.performFilter();
    }//GEN-LAST:event_jcbTransmissionActionPerformed

    private void jcbCarTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbCarTypeActionPerformed
        // TODO add your handling code here:
        this.performFilter();
    }//GEN-LAST:event_jcbCarTypeActionPerformed

    private void jcbColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbColorActionPerformed
        // TODO add your handling code here:
        this.performFilter();
    }//GEN-LAST:event_jcbColorActionPerformed

    private void jbBuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbBuyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbBuyActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton jbBuy;
    private javax.swing.JButton jbDelete;
    private javax.swing.JComboBox jcbBodyType;
    private javax.swing.JComboBox jcbCarType;
    private javax.swing.JComboBox jcbColor;
    private javax.swing.JComboBox jcbMake;
    private javax.swing.JComboBox jcbModel;
    private javax.swing.JComboBox jcbTransmission;
    private javax.swing.JTextField jtfYear;
    // End of variables declaration//GEN-END:variables
}
