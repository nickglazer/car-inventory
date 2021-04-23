package main.java.Controllers;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 *
 * @author recheejozil
 */
public class FormHandler {

    public static String stringFromTextfield(JTextField textField) {
        if (textField.getText().equals("")) {
            return null;
        }

        return textField.getText();
    }

    public static String stringFromDropDown(JComboBox comboBox) {
        if (comboBox.getSelectedIndex() == 0) {
            return null;
        }

        return comboBox.getSelectedItem().toString();

    }

    public static JTextField[] panelTextFields(JPanel panel) {
        ArrayList<JTextField> textFieldList = new ArrayList<>();

        Component[] components = panel.getComponents();

        for (Component panelComponent : components) {
            if (panelComponent.getClass() == JTextField.class) {
                textFieldList.add((JTextField) panelComponent);
            }
        }

        return textFieldList.toArray(new JTextField[textFieldList.size()]);
    }
}
