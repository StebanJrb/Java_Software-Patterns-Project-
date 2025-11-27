package com.sysManagemet.servientrega.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.swing.*;

@Component
public class UiStarter implements ApplicationListener<ApplicationReadyEvent> {

    private final MainFrame mainFrame;

    @Autowired
    public UiStarter(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        SwingUtilities.invokeLater(() -> mainFrame.setVisible(true));
    }
}




