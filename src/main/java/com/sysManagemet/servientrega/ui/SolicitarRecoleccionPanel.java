package com.sysManagemet.servientrega.ui;

import com.sysManagemet.servientrega.dto.SolicitudRecoleccionRequestDTO;
import com.sysManagemet.servientrega.dto.SolicitudRecoleccionResponseDTO;
import com.sysManagemet.servientrega.service.usecase.SolicitarRecoleccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;

@Component
public class SolicitarRecoleccionPanel extends JPanel {

    private final SolicitarRecoleccionService solicitarService;

    private final JTextField nombreField = new JTextField(30);
    private final JTextField emailField = new JTextField(30);
    private final JTextField telefonoField = new JTextField(20);
    private final JTextField origenField = new JTextField(40);
    private final JTextField destinoField = new JTextField(40);
    private final JTextField pesoField = new JTextField(8);
    private final JTextField volumenField = new JTextField(8);
    private final JTextField tipoContenidoField = new JTextField(20);
    private final JTextField valorDeclaradoField = new JTextField(10);

    private final JCheckBox fragilCheck = new JCheckBox("Frágil");
    private final JCheckBox urgenteCheck = new JCheckBox("Urgente");
    private final JCheckBox seguroCheck = new JCheckBox("Seguro adicional");

    @Autowired
    public SolicitarRecoleccionPanel(SolicitarRecoleccionService solicitarService) {
        this.solicitarService = solicitarService;
        setLayout(new BorderLayout());
        add(buildForm(), BorderLayout.NORTH);
    }

    private JPanel buildForm() {
        JPanel p = new JPanel();
        p.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(6,6,6,6);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0; c.gridy = 0;

        p.add(new JLabel("Nombre remitente:"), c);
        c.gridx = 1; p.add(nombreField, c);

        c.gridx = 0; c.gridy++; p.add(new JLabel("Email remitente:"), c);
        c.gridx = 1; p.add(emailField, c);

        c.gridx = 0; c.gridy++; p.add(new JLabel("Teléfono remitente:"), c);
        c.gridx = 1; p.add(telefonoField, c);

        c.gridx = 0; c.gridy++; p.add(new JLabel("Dirección origen:"), c);
        c.gridx = 1; p.add(origenField, c);

        c.gridx = 0; c.gridy++; p.add(new JLabel("Dirección destino:"), c);
        c.gridx = 1; p.add(destinoField, c);

        c.gridx = 0; c.gridy++; p.add(new JLabel("Peso (kg):"), c);
        c.gridx = 1; p.add(pesoField, c);

        c.gridx = 0; c.gridy++; p.add(new JLabel("Volumen:"), c);
        c.gridx = 1; p.add(volumenField, c);

        c.gridx = 0; c.gridy++; p.add(new JLabel("Tipo contenido:"), c);
        c.gridx = 1; p.add(tipoContenidoField, c);

        c.gridx = 0; c.gridy++; p.add(new JLabel("Valor declarado:"), c);
        c.gridx = 1; p.add(valorDeclaradoField, c);

        c.gridx = 0; c.gridy++; p.add(fragilCheck, c);
        c.gridx = 1; p.add(urgenteCheck, c);

        c.gridx = 0; c.gridy++; p.add(seguroCheck, c);

        JButton submit = new JButton("Solicitar recolección");
        submit.addActionListener(e -> onSolicitar());
        c.gridx = 0; c.gridy++; c.gridwidth = 2; p.add(submit, c);

        return p;
    }

    private void onSolicitar() {
        try {
            double peso = parseDoubleOrZero(pesoField.getText());
            double volumen = parseDoubleOrZero(volumenField.getText());
            double valorDeclarado = parseDoubleOrZero(valorDeclaradoField.getText());

            SolicitudRecoleccionRequestDTO request = new SolicitudRecoleccionRequestDTO(
                    nombreField.getText(),
                    emailField.getText(),
                    telefonoField.getText(),
                    origenField.getText(),
                    destinoField.getText(),
                    peso,
                    volumen,
                    tipoContenidoField.getText(),
                    fragilCheck.isSelected(),
                    urgenteCheck.isSelected(),
                    seguroCheck.isSelected(),
                    valorDeclarado
            );

            SolicitudRecoleccionResponseDTO resp = solicitarService.solicitar(request);

            JOptionPane.showMessageDialog(this,
                    "Solicitud creada: " + resp.comprobante() + "\nTarifa: " + resp.costoTotal(),
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);

            // limpiar formulario opcional
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private double parseDoubleOrZero(String s) {
        try { return Double.parseDouble(s); } catch (Exception e) { return 0.0; }
    }
}

