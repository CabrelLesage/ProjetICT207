package System;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ChambreAllocator extends JFrame {
    private JTable table;
    private JButton attribuerChambreButton;
    private List<Etudiant> etudiants;

    public ChambreAllocator() {
        etudiants = new ArrayList<>();

        attribuerChambreButton = new JButton("Attribuer Chambre");
        attribuerChambreButton.addActionListener(e -> attribuerChambre());

        table = new JTable(new EtudiantTableModel(etudiants));

        // Ajoutez le bouton et le tableau à votre interface utilisateur (JFrame) selon votre mise en page souhaitée

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    private void attribuerChambre() {
        List<Etudiant> etudiantsAttribues = new ArrayList<>();

        // Parcours de la liste des étudiants pour attribuer les chambres
        for (Etudiant etudiant : etudiants) {
            if (etudiant.estMineur()) {
                // Attribuer une chambre pour les étudiants mineurs selon les critères de compatibilité
                // Par exemple, vous pouvez rechercher une chambre disponible qui n'est pas déjà occupée par un étudiant mineur
                // et attribuer cette chambre à l'étudiant
                Chambre chambre = rechercherChambreDisponibleMineur();
                etudiant.setChambre(chambre);
            } else {
                // Attribuer une chambre pour les étudiants adultes selon les critères de compatibilité
                // Par exemple, vous pouvez rechercher une chambre disponible qui n'est pas déjà occupée par un étudiant adulte
                // et attribuer cette chambre à l'étudiant
                Chambre chambre = rechercherChambreDisponibleAdulte();
                etudiant.setChambre(chambre);
            }
            etudiantsAttribues.add(etudiant);
        }

        // Mettre à jour la liste des étudiants avec les étudiants attribués
        etudiants = etudiantsAttribues;

        // Rafraîchir le modèle de table pour afficher les données mises à jour
        table.setModel(new EtudiantTableModel(etudiants));

    }

    private void importDataFromExcel(String filePath) {
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                // Supposons que les données se trouvent dans les colonnes A, B et C
                String nom = row.getCell(0).getStringCellValue();
                String ageStr = row.getCell(1).getStringCellValue();
                boolean estMineur = row.getCell(2).getBooleanCellValue();

                int age = Integer.parseInt(ageStr);
                Etudiant etudiant = new Etudiant(nom, age, estMineur);
                etudiants.add(etudiant);
            }

            // Rafraîchissez le modèle de table pour afficher les données importées
            table.repaint();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Chambre rechercherChambreDisponibleAdulte() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private Chambre rechercherChambreDisponibleMineur() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private class Etudiant {
        private String nom;
        private int age;
        private boolean estMineur;
        // Ajoutez d'autres attributs selon vos besoins, comme l'attribut "chambre"

        public Etudiant(String nom, int age, boolean estMineur) {
            this.nom = nom;
            this.age = age;
            this.estMineur = estMineur;
        }

        // Ajoutez des getters et des setters pour accéder aux attributs de l'étudiant

        private Object getNom() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        private Object getAge() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        private boolean estMineur() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        private void setChambre(Chambre chambre) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
    }

    private class EtudiantTableModel extends AbstractTableModel {
        private List<Etudiant> etudiants;
        private String[] columnNames = {"Nom", "Âge", "Mineur"};

        public EtudiantTableModel(List<Etudiant> etudiants) {
            this.etudiants = etudiants;
        }

        @Override
        public int getRowCount() {
            return etudiants.size();
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public String getColumnName(int column) {
            return columnNames[column];
        }

        @Override
        public Object getValueAt(int row, int column) {
            Etudiant etudiant = etudiants.get(row);
            switch (column) {
                case 0:
                    return etudiant.getNom();
                case 1:
                    return etudiant.getAge();
                case 2:
                    return etudiant.estMineur() ? "Oui" : "Non";
                default:
                    return null;
            }
        }
    }

    public static void main(String[] args) {
        ChambreAllocator chambreAllocator = new ChambreAllocator();
        chambreAllocator.importDataFromExcel("chemin/vers/fichier.xlsx");
    }
}