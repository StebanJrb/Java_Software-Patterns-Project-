package com.sysManagemet.servientrega.ui;

import com.sysManagemet.servientrega.dto.ClasificarEncomiendaRequestDTO;
import com.sysManagemet.servientrega.dto.ClasificarEncomiendaResponseDTO;
import com.sysManagemet.servientrega.entity.Encomienda;
import com.sysManagemet.servientrega.repository.EncomiendaRepository;
import com.sysManagemet.servientrega.service.usecase.ClasificarEncomiendaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

@Component
public class ClasificarEncomiendaPanel extends JPanel {

    private final ClasificarEncomiendaService clasificarService;
    private final EncomiendaRepository encomiendaRepository;

    private final JTextField idField = new JTextField(10);
    private final JCheckBox manualCheck = new JCheckBox("Clasificación manual");
    private final JComboBox<Long> idCombo = new JComboBox<>();

    private final DefaultTableModel tableModel;
    private final JTable table;

    @Autowired
    public ClasificarEncomiendaPanel(ClasificarEncomiendaService clasificarService,
                                     EncomiendaRepository encomiendaRepository) {

        this.clasificarService = clasificarService;
        this.encomiendaRepository = encomiendaRepository;

        setLayout(new BorderLayout());

        // ---------- PANEL SUPERIOR ----------
        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));

        top.add(new JLabel("ID Encomienda:"));
        top.add(idField);

        top.add(new JLabel("IDs disponibles:"));
        top.add(idCombo);

        top.add(manualCheck);

        JButton btn = new JButton("Clasificar");
        btn.addActionListener(e -> onClasificar());
        top.add(btn);

        add(top, BorderLayout.NORTH);

        // ---------- TABLA ----------
        tableModel = new DefaultTableModel(new Object[]{
                "ID", "Estado", "Tipo Contenido", "Peso", "Volumen"
        }, 0);

        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Cargar IDs y tabla al iniciar
        loadIdsFromDatabase();
        loadTableData();
    }

    private void loadIdsFromDatabase() {
        idCombo.removeAllItems();
        List<Encomienda> lista = encomiendaRepository.findAll();
        for (Encomienda e : lista) {
            idCombo.addItem(e.getId());
        }
    }

    private void loadTableData() {
        tableModel.setRowCount(0);
        List<Encomienda> lista = encomiendaRepository.findAll();

        for (Encomienda e : lista) {
            tableModel.addRow(new Object[]{
                    e.getId(),
                    e.getEstado(),
                    e.getTipoContenido(),
                    e.getPesoKg(),
                    e.getVolumen()
            });
        }
    }

    private void onClasificar() {
        try {
            Long id = Long.parseLong(idField.getText());

            ClasificarEncomiendaRequestDTO req =
                    new ClasificarEncomiendaRequestDTO(id, manualCheck.isSelected());

            ClasificarEncomiendaResponseDTO resp = clasificarService.clasificar(req);

            JOptionPane.showMessageDialog(this,
                    "Estado: " + resp.estado() +
                            "\nTipo: " + resp.tipoClasificacion(),
                    "Clasificación exitosa",
                    JOptionPane.INFORMATION_MESSAGE);

            // Recargar tabla después de clasificar
            loadTableData();
            loadIdsFromDatabase();

        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this, "ID inválido", "Error", JOptionPane.ERROR_MESSAGE);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Error: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}





