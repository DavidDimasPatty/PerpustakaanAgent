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
import jade.gui.TimeChooser;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

import java.util.Date;

/**
 * This is the GUI of the agent that tries to sell books on behalf of its user
 */
public class BookSellerGuiImpl extends JFrame implements BookSellerGui {

    private BookSellerAgent myAgent;

    private JTextField titleTF, desiredPriceTF, minPriceTF, deadlineTF;
    private JButton setDeadlineB;
    private JButton setCCB, sellB, resetB, exitB;
    private JTextArea logTA;

    private Date deadline;

    public void setAgent(BookSellerAgent a) {
        myAgent = a;
        setTitle(myAgent.getName());
    }

    public BookSellerGuiImpl() {
        super();

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                myAgent.doDelete();
            }
        });
        
    /////////////////////////PANEL 1/////////////////////////
        JPanel rootPanel = new JPanel();
        rootPanel.setLayout(new GridBagLayout());
        rootPanel.setMinimumSize(new Dimension(330, 125));
        rootPanel.setPreferredSize(new Dimension(330, 125));

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

        desiredPriceTF = new JTextField(64);
        desiredPriceTF.setMinimumSize(new Dimension(70, 20));
        desiredPriceTF.setPreferredSize(new Dimension(70, 20));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(5, 3, 0, 3);
        rootPanel.add(desiredPriceTF, gridBagConstraints);

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

        deadlineTF = new JTextField(64);
        deadlineTF.setMinimumSize(new Dimension(146, 20));
        deadlineTF.setPreferredSize(new Dimension(146, 20));

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(5, 3, 0, 3);
        rootPanel.add(deadlineTF, gridBagConstraints);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(5, 3, 0, 3);

        rootPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));

        getContentPane().add(rootPanel, BorderLayout.NORTH);
        /////////////////////////PANEL 1/////////////////////////
        
        
      /////////////////////////PANEL 2/////////////////////////
        JPanel k = new JPanel();
        k.setLayout(new GridBagLayout());
        k.setMinimumSize(new Dimension(330, 125));
        k.setPreferredSize(new Dimension(330, 125));

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

        for (int i = 3; i < 10; i++) {
            l = new JLabel("Book");
            l.setHorizontalAlignment(SwingConstants.LEFT);
            gridBagConstraints = new GridBagConstraints();
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = i;
            gridBagConstraints.ipadx = 6;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
            //gridBagConstraints.insets = new java.awt.Insets(5, 3, 0, 3);  
            k.add(l, gridBagConstraints);

            l = new JLabel("Category");
            l.setHorizontalAlignment(SwingConstants.LEFT);
            gridBagConstraints = new GridBagConstraints();
            gridBagConstraints.gridx = 1;
            gridBagConstraints.gridy = i;
            gridBagConstraints.ipadx = 6;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
            //gridBagConstraints.insets = new java.awt.Insets(5, 3, 0, 3);  
            k.add(l, gridBagConstraints);

            l = new JLabel("1");
            l.setHorizontalAlignment(SwingConstants.LEFT);
            gridBagConstraints = new GridBagConstraints();
            gridBagConstraints.gridx = 3;
            gridBagConstraints.gridy = i;
            gridBagConstraints.ipadx = 6;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
            //gridBagConstraints.insets = new java.awt.Insets(5, 3, 0, 3);  
            k.add(l, gridBagConstraints);
            
            JButton buyB = new JButton("Delete");
            gridBagConstraints = new GridBagConstraints();
            gridBagConstraints.gridx = 5;
            gridBagConstraints.gridy = i;
            // gridBagConstraints.insets = new java.awt.Insets(5, 3, 0, 3);  
            k.add(buyB, gridBagConstraints);
            getContentPane().add(k, BorderLayout.WEST);
        }
/////////////////////////PANEL 2/////////////////////////

/////////////////////////PANEL 3/////////////////////////
        JPanel p = new JPanel();
        sellB = new JButton("Sell");
        sellB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String title = titleTF.getText();
                int desiredPrice = -1;
                int minPrice = -1;
                if (title != null && title.length() > 0) {
                    if (deadline != null && deadline.getTime() > System.currentTimeMillis()) {
                        try {
                            desiredPrice = Integer.parseInt(desiredPriceTF.getText());
                            try {
                                minPrice = Integer.parseInt(minPriceTF.getText());
                                if (minPrice <= desiredPrice) {
                                    // myAgent.addToCatalogue(title, desiredPrice, minPrice, deadline.getTime());   
                                    myAgent.putForSale(title, desiredPrice, minPrice, deadline);
                                    notifyUser("PUT FOR SALE: " + title + " between " + desiredPrice + " and " + minPrice + " by " + deadline);
                                } else {
                                    // minPrice > desiredPrice   
                                    JOptionPane.showMessageDialog(BookSellerGuiImpl.this, "Min price must be cheaper than best price", "WARNING", JOptionPane.WARNING_MESSAGE);
                                }
                            } catch (Exception ex1) {
                                // Invalid max cost   
                                JOptionPane.showMessageDialog(BookSellerGuiImpl.this, "Invalid min price", "WARNING", JOptionPane.WARNING_MESSAGE);
                            }
                        } catch (Exception ex2) {
                            // Invalid desired cost   
                            JOptionPane.showMessageDialog(BookSellerGuiImpl.this, "Invalid best price", "WARNING", JOptionPane.WARNING_MESSAGE);
                        }
                    } else {
                        // No deadline specified   
                        JOptionPane.showMessageDialog(BookSellerGuiImpl.this, "Invalid deadline", "WARNING", JOptionPane.WARNING_MESSAGE);
                    }
                } else {
                    // No book title specified   
                    JOptionPane.showMessageDialog(BookSellerGuiImpl.this, "No book title specified", "WARNING", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        resetB = new JButton("Reset");
        resetB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                titleTF.setText("");
                desiredPriceTF.setText("");
                minPriceTF.setText("");
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
}
