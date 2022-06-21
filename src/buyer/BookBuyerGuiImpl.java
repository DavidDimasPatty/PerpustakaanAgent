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
   
import java.awt.*;   
import java.awt.event.*;   
import javax.swing.*;   
import javax.swing.border.*;   
   
import java.util.Date;   
   
/**  
   J2SE (Swing-based) implementation of the GUI of the agent that   
   tries to buy books on behalf of its user  
 */   
public class BookBuyerGuiImpl extends JFrame implements BookBuyerGui {   
    private BookBuyerAgent myAgent;   
       
    private JTextField titleTF, desiredCostTF, maxCostTF, deadlineTF, address;   
    private JButton setDeadlineB;   
    private JButton setCCB, buyB, resetB, exitB;   
    private JTextArea logTA;   
       
    private Date deadline;   
       
    public BookBuyerGuiImpl() {   
        super();   
           
        addWindowListener(new   WindowAdapter() {   
            public void windowClosing(WindowEvent e) {   
                myAgent.doDelete();   
            }   
        } );   
   
           
   
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
    // Line 1   
    ///////////   
//        l = new JLabel("Best cost:");   
//    l.setHorizontalAlignment(SwingConstants.LEFT);   
//    gridBagConstraints = new GridBagConstraints();   
//    gridBagConstraints.gridx = 0;   
//    gridBagConstraints.gridy = 1;   
//    gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;   
//    gridBagConstraints.insets = new java.awt.Insets(5, 3, 0, 3);   
//    rootPanel.add(l, gridBagConstraints);   
//   
//    desiredCostTF = new JTextField(64);   
//    desiredCostTF.setMinimumSize(new Dimension(70, 20));   
//    desiredCostTF.setPreferredSize(new Dimension(70, 20));   
//    desiredCostTF.setEditable(false); //FIXME just for this example   
//    gridBagConstraints = new GridBagConstraints();   
//    gridBagConstraints.gridx = 1;   
//    gridBagConstraints.gridy = 1;   
//    gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;   
//    gridBagConstraints.insets = new Insets(5, 3, 0, 3);   
//    rootPanel.add(desiredCostTF, gridBagConstraints);   
   
       
   
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
    
    
    l = new JLabel("Address:");   
    l.setHorizontalAlignment(SwingConstants.LEFT);   
    gridBagConstraints = new GridBagConstraints();   
    gridBagConstraints.gridx = 0;   
    gridBagConstraints.gridy = 3;   
    gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;   
    gridBagConstraints.insets = new java.awt.Insets(5, 3, 0, 3);   
    rootPanel.add(l, gridBagConstraints);   
   
    address = new JTextField(64);   
    address.setMinimumSize(new Dimension(146, 20));   
    address.setPreferredSize(new Dimension(146, 20));   
    
    gridBagConstraints = new GridBagConstraints();   
    gridBagConstraints.gridx = 1;   
    gridBagConstraints.gridy = 3;   
    gridBagConstraints.gridwidth = 2;   
    gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;   
    gridBagConstraints.insets = new Insets(5, 3, 0, 3);   
    rootPanel.add(address, gridBagConstraints);   
   
    
   
           
    rootPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));   
       
    getContentPane().add(rootPanel, BorderLayout.NORTH);   
       
       
    logTA = new JTextArea();   
    logTA.setEnabled(false);   
    JScrollPane jsp = new JScrollPane(logTA);   
    jsp.setMinimumSize(new Dimension(300, 180));   
    jsp.setPreferredSize(new Dimension(300, 180));   
    JPanel p = new JPanel();   
    p.setBorder(new BevelBorder(BevelBorder.LOWERED));   
    p.add(jsp);   
    getContentPane().add(p, BorderLayout.CENTER);   
     
       
    p = new JPanel();   
    buyB = new JButton("Buy");   
        buyB.addActionListener(new ActionListener(){   
        public void actionPerformed(ActionEvent e) {   
            String title = titleTF.getText();   
            int desiredCost = -1;   
            int maxCost = -1;              
            if (title != null && title.length() > 0) {   
                if (deadline != null && deadline.getTime() > System.currentTimeMillis()) {   
                    try {   
                        //desiredCost = Integer.parseInt(desiredCostTF.getText());   
                        try {   
                            maxCost = Integer.parseInt(maxCostTF.getText());   
                            // if (maxCost >= desiredCost) {   
                                // myAgent.purchase(title, desiredCost, maxCost, deadline.getTime());   
                                myAgent.purchase(title, maxCost, deadline);   
                  notifyUser("PUT FOR BUY: "+title+" at max "+maxCost+" by "+deadline);    
                            //}   
                            //else {   
                                // Max cost < desiredCost   
                                //JOptionPane.showMessageDialog(BookBuyerGuiImpl.this, "Max cost must be greater than best cost", "WARNING", JOptionPane.WARNING_MESSAGE);   
                            //}                                
                        }   
                        catch (Exception ex1) {   
                            // Invalid max cost   
                            JOptionPane.showMessageDialog(BookBuyerGuiImpl.this, "Invalid max cost", "WARNING", JOptionPane.WARNING_MESSAGE);   
                        }   
                    }   
                    catch (Exception ex2) {   
                        // Invalid desired cost   
                        JOptionPane.showMessageDialog(BookBuyerGuiImpl.this, "Invalid best cost", "WARNING", JOptionPane.WARNING_MESSAGE);   
                    }   
                }   
                else {   
                    // No deadline specified   
                    JOptionPane.showMessageDialog(BookBuyerGuiImpl.this, "Invalid deadline", "WARNING", JOptionPane.WARNING_MESSAGE);   
                }   
            }   
            else {   
                // No book title specified   
                JOptionPane.showMessageDialog(BookBuyerGuiImpl.this, "No book title specified", "WARNING", JOptionPane.WARNING_MESSAGE);   
            }   
        }   
        } );   
    resetB = new JButton("Reset");   
        resetB.addActionListener(new ActionListener(){   
        public void actionPerformed(ActionEvent e) {   
            titleTF.setText("");   
            desiredCostTF.setText("");   
            maxCostTF.setText("");   
            deadlineTF.setText("");   
            deadline = null;   
        }   
        } );   
    exitB = new JButton("Exit");   
        exitB.addActionListener(new ActionListener(){   
        public void actionPerformed(ActionEvent e) {   
            myAgent.doDelete();   
        }   
        } );   
           
    buyB.setPreferredSize(resetB.getPreferredSize());   
    exitB.setPreferredSize(resetB.getPreferredSize());   
       
    p.add(buyB);   
    p.add(resetB);   
    p.add(exitB);   
       
    p.setBorder(new BevelBorder(BevelBorder.LOWERED));   
    getContentPane().add(p, BorderLayout.SOUTH);   
       
    pack();   
       
    setResizable(false);   
    }   
   
    public void setAgent(BookBuyerAgent a) {   
        myAgent = a;   
        setTitle(myAgent.getName());   
    }   
           
    public void notifyUser(String message) {   
        logTA.append(message+"\n");   
    }   
}     