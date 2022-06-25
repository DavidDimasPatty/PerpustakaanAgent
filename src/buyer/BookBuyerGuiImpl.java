/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buyer;

/**
 *
 * @author USER
 */
import jade.gui.TimeChooser;
import java.util.concurrent.ThreadLocalRandom;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

import java.util.Date;
import java.util.LinkedList;

/**
 * J2SE (Swing-based) implementation of the GUI of the agent that tries to buy
 * books on behalf of its user
 */
public class BookBuyerGuiImpl extends JFrame implements BookBuyerGui {

    private BookBuyerAgent myAgent;

    private JTextField titleTF, categoryTF, nameTF, addressTF;
    private JButton setDeadlineB;
    private JButton setCCB, buyB, resetB, exitB, findB;
    private JButton pinjam;
    private JTextArea logTA;
    public JPanel k;
    public int addy = 1;

    private Date deadline;

    public BookBuyerGuiImpl(LinkedList title, LinkedList category, LinkedList qty) {
        super();

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                myAgent.doDelete();
            }
        });

        JPanel rootPanel = new JPanel();
        rootPanel.setLayout(new GridBagLayout());
        rootPanel.setMinimumSize(new Dimension(330, 125));
        rootPanel.setPreferredSize(new Dimension(330, 125));

        ///////////   
        // Line 0   
        ///////////   
        JLabel l = new JLabel("Find Book:");
        l.setHorizontalAlignment(SwingConstants.LEFT);
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 3, 0, 3);
        rootPanel.add(l, gridBagConstraints);

        titleTF = new JTextField(64);
        titleTF.setMinimumSize(new Dimension(222, 20));
        titleTF.setPreferredSize(new Dimension(222, 20));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(5, 3, 0, 3);
        rootPanel.add(titleTF, gridBagConstraints);

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

        categoryTF = new JTextField(64);
        categoryTF.setMinimumSize(new Dimension(146, 20));
        categoryTF.setPreferredSize(new Dimension(146, 20));

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(5, 3, 0, 3);
        rootPanel.add(categoryTF, gridBagConstraints);

        l = new JLabel("Name:");
        l.setHorizontalAlignment(SwingConstants.LEFT);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 3, 0, 3);
        rootPanel.add(l, gridBagConstraints);

        nameTF = new JTextField(64);
        nameTF.setMinimumSize(new Dimension(146, 20));
        nameTF.setPreferredSize(new Dimension(146, 20));

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(5, 3, 0, 3);
        rootPanel.add(nameTF, gridBagConstraints);
        rootPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
        getContentPane().add(rootPanel, BorderLayout.NORTH);

        l = new JLabel("Address:");
        l.setHorizontalAlignment(SwingConstants.LEFT);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 3, 0, 3);
        rootPanel.add(l, gridBagConstraints);

        addressTF = new JTextField(64);
        addressTF.setMinimumSize(new Dimension(146, 20));
        addressTF.setPreferredSize(new Dimension(146, 20));

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(5, 3, 0, 3);
        rootPanel.add(addressTF, gridBagConstraints);
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
            if(!qty.get(i - 3).toString().equals("0")){
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
            buyB = new JButton("Pinjam");
            buyB.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (!(nameTF.getText().toString().equals("") && addressTF.getText().toString().equals(""))) {
                        myAgent.sendCourrier(nameTF.getText().toString().trim() + " " + addressTF.getText().toString().trim() + " " + "antar" + " " + ThreadLocalRandom.current().nextInt(3000, 100000 + 1));
                        myAgent.bookTaken(title.get(x).toString().trim());
                    } else {
                        JFrame frame = new JFrame("frame");
                        JOptionPane.showMessageDialog(frame, "Field Name and Adress can't be empty");
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
            }
/////////////////////////PANEL 2/////////////////////////

/////////////////////////PANEL 3/////////////////////////
        JPanel p = new JPanel();
        findB = new JButton("Find");
        findB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String tc = titleTF.getText();
                String cc = categoryTF.getText();
                myAgent.ReadDb();
                System.out.println(tc);
                LinkedList newtitle = new LinkedList();
                LinkedList newcategory = new LinkedList();
                LinkedList newqty = new LinkedList();
                
                for (int i = 0; i < title.size() / 2; i++) {
                    if(tc.equals("")){
                      if (category.get(i).toString().contains(cc)) {
                        System.out.println("tes");
                        newtitle.add(title.get(i).toString());
                        newcategory.add(category.get(i).toString());
                        newqty.add(qty.get(i).toString());

                    }
                    }
                    else if(cc.equals("")){
                    if (title.get(i).toString().contains(tc)) {
                        System.out.println("tes");
                        newtitle.add(title.get(i).toString());
                        newcategory.add(category.get(i).toString());
                        newqty.add(qty.get(i).toString());

                    }    
                    }
                    else{
                    if (title.get(i).toString().contains(tc) || category.get(i).toString().contains(cc)) {
                        System.out.println("tes");
                        newtitle.add(title.get(i).toString());
                        newcategory.add(category.get(i).toString());
                        newqty.add(qty.get(i).toString());

                    }
                    }
                }
                myAgent.title = newtitle;
                myAgent.category = newcategory;
                myAgent.qty = newqty;
                myAgent.newsetup();
            }
        }
        );

        resetB = new JButton("Reset");

        resetB.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                titleTF.setText("");
                categoryTF.setText("");
                nameTF.setText("");
                addressTF.setText("");
            }
        }
        );
        exitB = new JButton("Exit");
        exitB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                myAgent.doDelete();
            }
        });

        exitB.setPreferredSize(resetB.getPreferredSize());

        p.add(findB);
        p.add(resetB);
        p.add(exitB);

        p.setBorder(new BevelBorder(BevelBorder.LOWERED));
        getContentPane().add(p, BorderLayout.SOUTH);

        pack();

    }
/////////////////////////PANEL 3/////////////////////////

    public void setAgent(BookBuyerAgent a) {
        myAgent = a;
        setTitle(myAgent.getName());
    }

    public void notifyUser(String message) {
        logTA.append(message + "\n");
    }
}
