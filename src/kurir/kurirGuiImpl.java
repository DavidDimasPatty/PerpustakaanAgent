/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kurir;

/**
 *
 * @author USER
 */
import jade.gui.TimeChooser;

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
public class kurirGuiImpl extends JFrame implements kurirGui {

    private kurirAgent myAgent;

    private JTextField titleTF, desiredCostTF, maxCostTF, deadlineTF, address;
    private JButton setDeadlineB;
    private JButton setCCB, buyB, resetB, exitB;
    private JButton pinjam;
    private JTextArea logTA;
    public JPanel k;

    private JLabel l;
    public int addy = 1;

    private Date deadline;

    public kurirGuiImpl(LinkedList title, LinkedList category, LinkedList qty) {
        super();

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                myAgent.doDelete();
            }
        });

//        JPanel rootPanel = new JPanel();
//        rootPanel.setLayout(new GridBagLayout());
//        rootPanel.setMinimumSize(new Dimension(330, 125));
//        rootPanel.setPreferredSize(new Dimension(330, 125));
//
//        ///////////   
//        // Line 0   
//        ///////////   
//        JLabel l = new JLabel("Find Book:");
//        l.setHorizontalAlignment(SwingConstants.LEFT);
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
//        gridBagConstraints.gridx = 0;
//        gridBagConstraints.gridy = 0;
//        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
//        gridBagConstraints.insets = new java.awt.Insets(5, 3, 0, 3);
//        rootPanel.add(l, gridBagConstraints);
//
//        titleTF = new JTextField(64);
//        titleTF.setMinimumSize(new Dimension(222, 20));
//        titleTF.setPreferredSize(new Dimension(222, 20));
//        gridBagConstraints = new GridBagConstraints();
//        gridBagConstraints.gridx = 1;
//        gridBagConstraints.gridy = 0;
//        gridBagConstraints.gridwidth = 3;
//        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
//        gridBagConstraints.insets = new Insets(5, 3, 0, 3);
//        rootPanel.add(titleTF, gridBagConstraints);
//
//        ///////////   
//        // Line 2   
//        ///////////   
//        l = new JLabel("Category:");
//        l.setHorizontalAlignment(SwingConstants.LEFT);
//        gridBagConstraints = new GridBagConstraints();
//        gridBagConstraints.gridx = 0;
//        gridBagConstraints.gridy = 2;
//        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
//        gridBagConstraints.insets = new java.awt.Insets(5, 3, 0, 3);
//        rootPanel.add(l, gridBagConstraints);
//
//        deadlineTF = new JTextField(64);
//        deadlineTF.setMinimumSize(new Dimension(146, 20));
//        deadlineTF.setPreferredSize(new Dimension(146, 20));
//
//        gridBagConstraints = new GridBagConstraints();
//        gridBagConstraints.gridx = 1;
//        gridBagConstraints.gridy = 2;
//        gridBagConstraints.gridwidth = 2;
//        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
//        gridBagConstraints.insets = new Insets(5, 3, 0, 3);
//        rootPanel.add(deadlineTF, gridBagConstraints);
//
//        l = new JLabel("Name:");
//        l.setHorizontalAlignment(SwingConstants.LEFT);
//        gridBagConstraints = new GridBagConstraints();
//        gridBagConstraints.gridx = 0;
//        gridBagConstraints.gridy = 3;
//        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
//        gridBagConstraints.insets = new java.awt.Insets(5, 3, 0, 3);
//        rootPanel.add(l, gridBagConstraints);
//
//        address = new JTextField(64);
//        address.setMinimumSize(new Dimension(146, 20));
//        address.setPreferredSize(new Dimension(146, 20));
//
//        gridBagConstraints = new GridBagConstraints();
//        gridBagConstraints.gridx = 1;
//        gridBagConstraints.gridy = 3;
//        gridBagConstraints.gridwidth = 2;
//        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
//        gridBagConstraints.insets = new Insets(5, 3, 0, 3);
//        rootPanel.add(address, gridBagConstraints);
//        rootPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
//        getContentPane().add(rootPanel, BorderLayout.NORTH);
//
//        l = new JLabel("Address:");
//        l.setHorizontalAlignment(SwingConstants.LEFT);
//        gridBagConstraints = new GridBagConstraints();
//        gridBagConstraints.gridx = 0;
//        gridBagConstraints.gridy = 4;
//        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
//        gridBagConstraints.insets = new java.awt.Insets(5, 3, 0, 3);
//        rootPanel.add(l, gridBagConstraints);
//
//        address = new JTextField(64);
//        address.setMinimumSize(new Dimension(146, 20));
//        address.setPreferredSize(new Dimension(146, 20));
//
//        gridBagConstraints = new GridBagConstraints();
//        gridBagConstraints.gridx = 1;
//        gridBagConstraints.gridy = 4;
//        gridBagConstraints.gridwidth = 2;
//        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
//        gridBagConstraints.insets = new Insets(5, 3, 0, 3);
//        rootPanel.add(address, gridBagConstraints);
//        rootPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
//        getContentPane().add(rootPanel, BorderLayout.NORTH);
//
//        /////////////////////////PANEL 1/////////////////////////
        /////////////////////////PANEL 2/////////////////////////
        k = new JPanel();
        k.setLayout(new GridBagLayout());
        //k.setMinimumSize(new Dimension(330, 125));
        //k.setPreferredSize(new Dimension(330, 125));
        gridBagConstraints = new GridBagConstraints();
        l = new JLabel("Name");
        l.setHorizontalAlignment(SwingConstants.LEFT);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        //gridBagConstraints.insets = new java.awt.Insets(5, 3, 0, 3);  
        k.add(l, gridBagConstraints);

        l = new JLabel("Adress");
        l.setHorizontalAlignment(SwingConstants.LEFT);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        //gridBagConstraints.insets = new java.awt.Insets(5, 3, 0, 3);  
        k.add(l, gridBagConstraints);

        l = new JLabel("Status");
        l.setHorizontalAlignment(SwingConstants.LEFT);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        //gridBagConstraints.insets = new java.awt.Insets(5, 3, 0, 3);  
        k.add(l, gridBagConstraints);

        l = new JLabel("Harga");
        l.setHorizontalAlignment(SwingConstants.LEFT);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        //gridBagConstraints.insets = new java.awt.Insets(5, 3, 0, 3);  
        k.add(l, gridBagConstraints);

        l = new JLabel("Action");
        l.setHorizontalAlignment(SwingConstants.LEFT);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        //gridBagConstraints.insets = new java.awt.Insets(5, 3, 0, 3);  
        k.add(l, gridBagConstraints);

//        for (int i = 3; i < title.size() + 3; i++) {
//            l = new JLabel(title.get(i - 3).toString());
//            l.setHorizontalAlignment(SwingConstants.LEFT);
//            gridBagConstraints = new GridBagConstraints();
//            gridBagConstraints.gridx = 0;
//            gridBagConstraints.gridy = i;
//            gridBagConstraints.ipadx = 6;
//            gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
//            //gridBagConstraints.insets = new java.awt.Insets(5, 3, 0, 3);  
//            k.add(l, gridBagConstraints);
//
//            l = new JLabel(category.get(i - 3).toString());
//            l.setHorizontalAlignment(SwingConstants.LEFT);
//            gridBagConstraints = new GridBagConstraints();
//            gridBagConstraints.gridx = 1;
//            gridBagConstraints.gridy = i;
//            gridBagConstraints.ipadx = 6;
//            gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
//            //gridBagConstraints.insets = new java.awt.Insets(5, 3, 0, 3);  
//            k.add(l, gridBagConstraints);
//
//            l = new JLabel(qty.get(i - 3).toString());
//            l.setHorizontalAlignment(SwingConstants.LEFT);
//            gridBagConstraints = new GridBagConstraints();
//            gridBagConstraints.gridx = 3;
//            gridBagConstraints.gridy = i;
//            gridBagConstraints.ipadx = 6;
//            gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
//            //gridBagConstraints.insets = new java.awt.Insets(5, 3, 0, 3);  
//            k.add(l, gridBagConstraints);
//
//            l = new JLabel(qty.get(i - 3).toString());
//            l.setHorizontalAlignment(SwingConstants.LEFT);
//            gridBagConstraints = new GridBagConstraints();
//            gridBagConstraints.gridx = 5;
//            gridBagConstraints.gridy = i;
//            gridBagConstraints.ipadx = 6;
//            gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
//            //gridBagConstraints.insets = new java.awt.Insets(5, 3, 0, 3);  
//            k.add(l, gridBagConstraints);
//
//            JButton buyB = new JButton("Done");
//            buyB.addActionListener(new ActionListener() {
//                int getIndex = addy - 3;
//
//                public void actionPerformed(ActionEvent e) {
//
//                }
//            });
//            gridBagConstraints = new GridBagConstraints();
//            gridBagConstraints.gridx = 7;
//            gridBagConstraints.gridy = i;
//            // gridBagConstraints.insets = new java.awt.Insets(5, 3, 0, 3);  
//            k.add(buyB, gridBagConstraints);
//            addy++;
            getContentPane().add(k, BorderLayout.WEST);
//        }
/////////////////////////PANEL 2/////////////////////////

/////////////////////////PANEL 3/////////////////////////
        JPanel p = new JPanel();

        resetB = new JButton("Reset");
        resetB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                titleTF.setText("");
                desiredCostTF.setText("");
                maxCostTF.setText("");
                deadlineTF.setText("");
                deadline = null;
            }
        });
        exitB = new JButton("Exit");
        exitB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                myAgent.doDelete();
            }
        });

        exitB.setPreferredSize(resetB.getPreferredSize());

        p.add(resetB);
        p.add(exitB);

        p.setBorder(new BevelBorder(BevelBorder.LOWERED));
        getContentPane().add(p, BorderLayout.SOUTH);

        pack();

    }
/////////////////////////PANEL 3/////////////////////////

    public void setAgent(kurirAgent a) {
        myAgent = a;
        setTitle(myAgent.getName());
    }

    public void notifyUser(String message) {
        logTA.append(message + "\n");
    }

    public void tambah(String msg) {
        String[] tokens = msg.split(" ");
        System.out.println(tokens[0]);
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        l = new JLabel(tokens[0]);
        l.setHorizontalAlignment(SwingConstants.LEFT);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = addy + 2;
        gridBagConstraints.ipadx = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        //gridBagConstraints.insets = new java.awt.Insets(5, 3, 0, 3);  
        k.add(l, gridBagConstraints);

        l = new JLabel(tokens[1]);
        l.setHorizontalAlignment(SwingConstants.LEFT);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = addy + 2;
        gridBagConstraints.ipadx = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        //gridBagConstraints.insets = new java.awt.Insets(5, 3, 0, 3);  
        k.add(l, gridBagConstraints);

        l = new JLabel(tokens[2]);
        l.setHorizontalAlignment(SwingConstants.LEFT);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = addy + 2;
        gridBagConstraints.ipadx = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        //gridBagConstraints.insets = new java.awt.Insets(5, 3, 0, 3);  
        k.add(l, gridBagConstraints);

        l = new JLabel(tokens[3]);
        l.setHorizontalAlignment(SwingConstants.LEFT);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = addy + 2;
        gridBagConstraints.ipadx = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        //gridBagConstraints.insets = new java.awt.Insets(5, 3, 0, 3);  
        k.add(l, gridBagConstraints);

        JButton buyB = new JButton("Done");
        buyB.addActionListener(new ActionListener() {
            int getIndex = addy - 3;

            public void actionPerformed(ActionEvent e) {
                
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = addy+2;
        // gridBagConstraints.insets = new java.awt.Insets(5, 3, 0, 3);  
        k.add(buyB, gridBagConstraints);
        addy++;
        getContentPane().add(k, BorderLayout.WEST);
        pack();
    }

}
