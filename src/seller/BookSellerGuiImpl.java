/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seller;

/**
 *
 * @author USER
 */
import jade.core.AID;
import jade.gui.TimeChooser;
import jade.lang.acl.ACLMessage;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.*;
import javax.swing.border.*;

import java.util.Date;
import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * This is the GUI of the agent that tries to sell books on behalf of its user
 */
public class BookSellerGuiImpl extends JFrame implements BookSellerGui {

    private BookSellerAgent myAgent;

    private JTextField titleField, qtyField, categoryField;
    private JTextField nameRF, addressRF;
    private JButton setDeadlineB;
    private JButton setCCB, sellB, resetB, exitB;
    private JTextArea logTA;
    public JPanel k;
    private Date deadline;
    public int cury;
    public int addy = 1;

    public void setAgent(BookSellerAgent a) {
        myAgent = a;
        setTitle(myAgent.getName());
    }

    public BookSellerGuiImpl(LinkedList title, LinkedList category, LinkedList qty) {
        super();

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                myAgent.doDelete();
            }
        });
        cury = title.size();
        /////////////////////////PANEL 1/////////////////////////
        JPanel rootPanel = new JPanel();
        rootPanel.setLayout(new GridBagLayout());
        //rootPanel.setMinimumSize(new Dimension(330, 125));
        //rootPanel.setPreferredSize(new Dimension(330, 125));

        ///////////   
        // Line 0   
        ///////////   
        JLabel l = new JLabel("Add Book:");
        l.setHorizontalAlignment(SwingConstants.LEFT);
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 3, 0, 3);
        rootPanel.add(l, gridBagConstraints);

        titleField = new JTextField(64);
        //titleField.setMinimumSize(new Dimension(222, 20));
        //titleField.setPreferredSize(new Dimension(222, 20));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(5, 3, 0, 3);
        rootPanel.add(titleField, gridBagConstraints);

        ///////////   
        // Line 1   
        ///////////   
        l = new JLabel("Quantity Book:");
        l.setHorizontalAlignment(SwingConstants.LEFT);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 3, 0, 3);
        rootPanel.add(l, gridBagConstraints);

        qtyField = new JTextField(64);
        //qtyField.setMinimumSize(new Dimension(70, 20));
        //qtyField.setPreferredSize(new Dimension(70, 20));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(5, 3, 0, 3);
        rootPanel.add(qtyField, gridBagConstraints);

        ///////////   
        // Line 2   
        ///////////   
        l = new JLabel("Category:");
        l.setHorizontalAlignment(SwingConstants.LEFT);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 3, 0, 3);
        rootPanel.add(l, gridBagConstraints);

        categoryField = new JTextField(64);
        // categoryField.setMinimumSize(new Dimension(146, 20));
        // categoryField.setPreferredSize(new Dimension(146, 20));

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(5, 3, 0, 3);
        rootPanel.add(categoryField, gridBagConstraints);

        l = new JLabel("Name Return:");
        l.setHorizontalAlignment(SwingConstants.LEFT);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 3, 0, 3);
        rootPanel.add(l, gridBagConstraints);

        nameRF = new JTextField(64);
        // categoryField.setMinimumSize(new Dimension(146, 20));
        // categoryField.setPreferredSize(new Dimension(146, 20));

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(5, 3, 0, 3);
        rootPanel.add(nameRF, gridBagConstraints);

        l = new JLabel("Adress Return:");
        l.setHorizontalAlignment(SwingConstants.LEFT);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 3, 0, 3);
        rootPanel.add(l, gridBagConstraints);

        addressRF = new JTextField(64);
        // categoryField.setMinimumSize(new Dimension(146, 20));
        // categoryField.setPreferredSize(new Dimension(146, 20));

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(5, 3, 0, 3);
        rootPanel.add(addressRF, gridBagConstraints);

        rootPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));

        getContentPane().add(rootPanel, BorderLayout.NORTH);
        /////////////////////////PANEL 1/////////////////////////

        /////////////////////////PANEL 2/////////////////////////
        k = new JPanel();
        k.setLayout(new GridBagLayout());
        //k.setMinimumSize(new Dimension(330, 125));
        //k.setPreferredSize(new Dimension(330, 125));

        l = new JLabel("Title");
        l.setHorizontalAlignment(SwingConstants.LEFT);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        //gridBagConstraints.insets = new java.awt.Insets(5, 3, 0, 3);  
        k.add(l, gridBagConstraints);

        l = new JLabel("Category");
        l.setHorizontalAlignment(SwingConstants.LEFT);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        //gridBagConstraints.insets = new java.awt.Insets(5, 3, 0, 3);  
        k.add(l, gridBagConstraints);

        l = new JLabel("Quantity");
        l.setHorizontalAlignment(SwingConstants.LEFT);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        //gridBagConstraints.insets = new java.awt.Insets(5, 3, 0, 3);  
        k.add(l, gridBagConstraints);

        l = new JLabel("Status");
        l.setHorizontalAlignment(SwingConstants.LEFT);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        //gridBagConstraints.insets = new java.awt.Insets(5, 3, 0, 3);  
        k.add(l, gridBagConstraints);

        for (int i = 3; i < title.size() + 3; i++) {
            l = new JLabel(title.get(i - 3).toString());
            l.setHorizontalAlignment(SwingConstants.LEFT);
            gridBagConstraints = new GridBagConstraints();
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = i;
            gridBagConstraints.ipadx = 6;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
            //gridBagConstraints.insets = new java.awt.Insets(5, 3, 0, 3);  
            k.add(l, gridBagConstraints);

            l = new JLabel(category.get(i - 3).toString());
            l.setHorizontalAlignment(SwingConstants.LEFT);
            gridBagConstraints = new GridBagConstraints();
            gridBagConstraints.gridx = 1;
            gridBagConstraints.gridy = i;
            gridBagConstraints.ipadx = 6;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
            //gridBagConstraints.insets = new java.awt.Insets(5, 3, 0, 3);  
            k.add(l, gridBagConstraints);

            l = new JLabel(qty.get(i - 3).toString());
            l.setHorizontalAlignment(SwingConstants.LEFT);
            gridBagConstraints = new GridBagConstraints();
            gridBagConstraints.gridx = 3;
            gridBagConstraints.gridy = i;
            gridBagConstraints.ipadx = 6;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
            //gridBagConstraints.insets = new java.awt.Insets(5, 3, 0, 3);  
            k.add(l, gridBagConstraints);
            final int x = i - 3;
            JButton buyB = new JButton("Add");
            buyB.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (!(nameRF.getText().toString().equals("") && nameRF.getText().toString().equals(""))) {
                        myAgent.bookReturn(x + "");
                        myAgent.bookReturnCourier(nameRF.getText().toString().trim() + " " + addressRF.getText().toString().trim() + " " + "jemput" + " " + ThreadLocalRandom.current().nextInt(3000, 100000 + 1));
                    } else {
                        JFrame frame = new JFrame("frame");
                        JOptionPane.showMessageDialog(frame, "Field Nama Return and Adress Return can't be empty");
                    }

                }
            });
            gridBagConstraints = new GridBagConstraints();
            gridBagConstraints.gridx = 5;
            gridBagConstraints.gridy = i;
            // gridBagConstraints.insets = new java.awt.Insets(5, 3, 0, 3);  
            k.add(buyB, gridBagConstraints);
            addy++;
            getContentPane().add(k, BorderLayout.WEST);
        }
/////////////////////////PANEL 2/////////////////////////

/////////////////////////PANEL 3/////////////////////////
        JPanel p = new JPanel();
        sellB = new JButton("Add");
        sellB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String add = titleField.getText();
                String qty = qtyField.getText();
                String category = categoryField.getText();
                if (!(add.isEmpty() && qty.isEmpty() && category.isEmpty())) {
                    try {
                        Path path = Paths.get("bookDB.txt");
                        FileWriter myWriter = new FileWriter("bookDB.txt", true);
                        java.util.List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);

                        myWriter.write(add + "\n");
                        myWriter.write(category + "\n");
                        myWriter.write(qty + "\n");

                        myAgent.ReadDb();
                        myWriter.close();
                        System.out.println("Successfully wrote to the file.");
                        //add to ui//
                        GridBagConstraints gridBagConstraints = new GridBagConstraints();

                        JLabel l = new JLabel(add);
                        l.setHorizontalAlignment(SwingConstants.LEFT);
                        gridBagConstraints = new GridBagConstraints();
                        gridBagConstraints.gridx = 0;
                        gridBagConstraints.gridy = cury + 3;
                        gridBagConstraints.ipadx = 6;
                        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
                        //gridBagConstraints.insets = new java.awt.Insets(5, 3, 0, 3);  
                        k.add(l, gridBagConstraints);

                        JLabel s = new JLabel(category);
                        s.setHorizontalAlignment(SwingConstants.LEFT);
                        gridBagConstraints = new GridBagConstraints();
                        gridBagConstraints.gridx = 1;
                        gridBagConstraints.gridy = cury + 3;
                        gridBagConstraints.ipadx = 6;
                        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
                        //gridBagConstraints.insets = new java.awt.Insets(5, 3, 0, 3);  
                        k.add(s, gridBagConstraints);

                        JLabel x = new JLabel(qty);
                        x.setHorizontalAlignment(SwingConstants.LEFT);
                        gridBagConstraints = new GridBagConstraints();
                        gridBagConstraints.gridx = 3;
                        gridBagConstraints.gridy = cury + 3;
                        gridBagConstraints.ipadx = 6;
                        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
                        //gridBagConstraints.insets = new java.awt.Insets(5, 3, 0, 3);  
                        k.add(x, gridBagConstraints);

                        JButton buyB = new JButton("Delete");
                        gridBagConstraints = new GridBagConstraints();
                        gridBagConstraints.gridx = 5;
                        gridBagConstraints.gridy = cury + 3;
                        // gridBagConstraints.insets = new java.awt.Insets(5, 3, 0, 3);  
                        k.add(buyB, gridBagConstraints);
                        getContentPane().add(k, BorderLayout.WEST, 1);
                        ////////////;
                        titleField.setText("");
                        qtyField.setText("");
                        categoryField.setText("");
                        myAgent.title.add(add);
                        myAgent.qty.add(qty);
                        myAgent.category.add(category);
                        myAgent.sendADD(add + " " + category + " " + qty);
                        pack();
                        cury++;
                    } catch (IOException ex) {
                        System.out.println("An error occurred.");
                        ex.printStackTrace();
                    }

                } else {
                    JFrame frame = new JFrame("frame");
                    JOptionPane.showMessageDialog(frame, "Field Add Book, Quantity Book, and Category can't be empty");
                }
            }
        });
        resetB = new JButton("Reset");
        resetB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                titleField.setText("");
                qtyField.setText("");
                categoryField.setText("");
                nameRF.setText("");
                addressRF.setText("");
            }
        });
        exitB = new JButton("Exit");
        exitB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                myAgent.doDelete();
            }
        });

        sellB.setPreferredSize(resetB.getPreferredSize());
        exitB.setPreferredSize(resetB.getPreferredSize());

        p.add(sellB);
        p.add(resetB);
        p.add(exitB);

        p.setBorder(new BevelBorder(BevelBorder.LOWERED));
        getContentPane().add(p, BorderLayout.SOUTH);
/////////////////////////PANEL 3/////////////////////////

        pack();
    }

    public void notifyUser(String message) {
        logTA.append(message + "\n");
    }

    @Override
    public void updateGUI() {
        pack();
    }
}
