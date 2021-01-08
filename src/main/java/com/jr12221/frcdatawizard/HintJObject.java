package com.jr12221.frcdatawizard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class HintJObject extends JTextField {
    Font gainFont = new Font("Tahoma", Font.PLAIN, 24);
    Font lostFont = new Font("Tahoma", Font.ITALIC, 24);
    public HintJObject(final String hint) {
        setText(hint);
        setFont(lostFont);
        setForeground(Color.GRAY);

        this.addFocusListener(new FocusAdapter() {

            @Override
            public void focusGained(FocusEvent e) {
                setForeground(Color.BLACK);
                if (getText().equals(hint)) {
                    setText("");
                }  else {
                    setText(getText());
                }
                setFont(gainFont);
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (getText().equals(hint) || getText().length() == 0) {
                    setText(hint);
                    setFont(lostFont);
                    setForeground(Color.GRAY);
                } else {
                    setText(getText());
                    setFont(gainFont);
                    setForeground(Color.BLACK);
                }
            }
        });
    }
}
