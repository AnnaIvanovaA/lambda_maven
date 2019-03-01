package com.jet.reproduce;

import java.awt.Component;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JPanel;

public class NoDebugJDK {
        public static final void main(final String[] args)
        {
            final Component component = new JPanel();

            component.addFocusListener(new FocusAdapter()
            {
                @Override
                public void focusGained(FocusEvent e)
                {
                    System.out.println("Focus Gained");
                }
            });
        }
    }
