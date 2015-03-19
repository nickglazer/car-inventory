package Controllers;

import javax.swing.*;

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
}
