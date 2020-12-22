package com.company.gui;

import com.company.common.GeneralLogic;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class GUI extends JFrame{
    private JButton buttonFile;
    private JPanel GUIpanel;
    private JButton buttonProcess;
    private JTable tableInput;
    private JTextArea textAreaResult;
    DefaultTableModel tableInputModel;

    public GUI(){
        setSize(800,600);
        setContentPane(GUIpanel);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);

        buttonProcess.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Integer> list = readListFromTable();
                GeneralLogic.process(list);
                writeResultToTextPane(list);
                writeResultToFile();
            }
        });

        buttonFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File("."));
                fileChooser.showOpenDialog(GUIpanel);
                System.out.println(fileChooser.getSelectedFile().getPath());
                Scanner scanner;
                try {
                    scanner = new Scanner(new File(fileChooser.getSelectedFile().getPath()));
                }
                catch (FileNotFoundException ex) {
                    JOptionPane.showInputDialog("No file");
                    return;
                }

                List<Integer> list = new LinkedList<>();
                while (scanner.hasNextInt()){
                    list.add(scanner.nextInt());
                }

                putDataToTable(list);
            }
        });
    }

    private void putDataToTable(List<Integer> list) {
        Object[] columns = new Object[list.size()];
        Object[] values = new Object[list.size()];
        for (int i = 0; i < columns.length; i++) {
            columns[i] = i;
            values[i] = list.get(i);
        }
        tableInputModel = new DefaultTableModel();
        tableInputModel.setColumnIdentifiers(columns);
        tableInput.setModel(tableInputModel);
        tableInputModel.addRow(values);
    }

    private List<Integer> readListFromTable() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < tableInputModel.getColumnCount(); i++) {
            list.add( Integer.parseInt(tableInputModel.getValueAt(0,i).toString()));
        }
        return list;
    }

    private void writeResultToTextPane(List<Integer> list) {
        textAreaResult.setText("");
        for (Integer num: list) {
            textAreaResult.append(num + " ");
        }
    }

    private void writeResultToFile() {
        try {
            FileWriter fileWriter = new FileWriter(new File("src/com/company/Tests/outputFromFile.txt"));
            fileWriter.append(textAreaResult.getText());
            fileWriter.close();
        } catch (IOException ioException) {
            return;
        }
    }
}