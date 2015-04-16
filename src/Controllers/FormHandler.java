package Controllers;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by recheejozil on 3/19/15.
 */
public class FormHandler
{
    public static String stringFromTextfield(JTextField textField)
    {
        String textFieldString;

        if(textField.getText().equals(""))
        {
            return null;
        }

        return textField.getText();
    }

    public static String stringFromDropDown(JComboBox comboBox)
    {
        String dropDownString;
        if(comboBox.getSelectedIndex() == 0)
        {
            return null;
        }

        return (String) comboBox.getSelectedItem();

    }

    public static JTextField[] panelTextFields(JPanel panel)
    {
        ArrayList<JTextField> textFieldList = new ArrayList<JTextField>();
        ArrayList<JComboBox> comoBoxList = new ArrayList<JComboBox>();

        Component[] components = panel.getComponents();

        for(Component panelComponent : components)
        {
            if(panelComponent.getClass() == JTextField.class)
            {
                textFieldList.add((JTextField) panelComponent);
            }
        }

        return textFieldList.toArray(new JTextField[textFieldList.size()]);
    }
}
