package System;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class ExcelImporter {

    private JTextField filePathField;

    public static void main(String[] args) {
        ExcelImporter importer = new ExcelImporter();
        importer.createUI();
    }

    private void createUI() {
        JFrame frame = new JFrame("Excel Importer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        filePathField = new JTextField(30);
        JButton importButton = new JButton("Importer");
        importButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                importFile();
            }
        });

        frame.getContentPane().add(filePathField);
        frame.getContentPane().add(importButton);
        frame.pack();
        frame.setVisible(true);
    }

    private void importFile() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String filePath = selectedFile.getAbsolutePath();
            filePathField.setText(filePath);

            // Appeler la méthode pour importer le fichier Excel et mettre à jour la base de données
            importExcel(filePath);
        }
    }

    private void importExcel(String filePath) {
        // Code pour importer le fichier Excel et mettre à jour la base de données
        // Utilisez les étapes mentionnées précédemment pour lire le fichier et insérer les données dans la base de données
    }
}