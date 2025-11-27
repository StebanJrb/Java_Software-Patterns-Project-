package com.sysManagemet.servientrega.ui;

import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;

@Component
public class MainFrame extends JFrame {

    public MainFrame(SolicitarRecoleccionPanel solicitarPanel,
                     ClasificarEncomiendaPanel clasificarPanel) {

        super("Servientrega - Interfaz de Pruebas");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);

        // Tabs
        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Solicitar Recolección", solicitarPanel);
        tabs.addTab("Clasificar Encomienda", clasificarPanel);

        add(tabs, BorderLayout.CENTER);
    }
}



