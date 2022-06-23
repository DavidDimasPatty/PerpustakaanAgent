/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buyer;

import jade.core.*;
import jade.core.behaviours.*;
import jade.lang.acl.*;

import jade.domain.*;
import jade.domain.FIPAAgentManagement.*;

import java.util.Vector;
import java.util.Date;
import java.util.LinkedList;
import seller.BookSellerAgent;

public class BookBuyerAgent extends Agent {
    // The list of known seller agents   

    private Vector sellerAgents = new Vector();

    // The GUI to interact with the user   
    private BookBuyerGui myGui;
    public LinkedList title = new LinkedList();
    public LinkedList category = new LinkedList();
    public LinkedList qty = new LinkedList();

    /**
     * Agent initializations
     *
     */
    protected void setup() {
        // Printout a welcome message   
        System.out.println("Buyer-agent " + getAID().getLocalName() + " is ready.");

        // Get names of seller agents as arguments   
        Object[] args = getArguments();
        if (args != null && args.length > 0) {
            for (int i = 0; i < args.length; ++i) {
                AID seller = new AID((String) args[i], AID.ISLOCALNAME);
                sellerAgents.addElement(seller);
            }
        }

        // Show the GUI to interact with the user   
        myGui = new BookBuyerGuiImpl();
        myGui.setAgent(this);
        myGui.show();
        addBehaviour(new waitingBehaviour(this));
    }

    /**
     * Agent clean-up
     *
     */
    protected void takeDown() {
        // Dispose the GUI if it is there   
        if (myGui != null) {
            myGui.dispose();
        }

        // Printout a dismissal message   
        System.out.println("Buyer-agent " + getAID().getName() + "terminated.");
    }

    class waitingBehaviour extends CyclicBehaviour {

        ACLMessage msg = receive();

        public waitingBehaviour(Agent a) {
            super(a);
        }

        public void action() {
            msg = receive();
            if (msg != null) {
              
                System.out.println("new msg: " + msg.getContent());
                String lastMsg = msg.getContent();
                String[] tokens = lastMsg.split(" ");
                title.add(tokens[0]);
                category.add(tokens[1]);
                qty.add(tokens[2]);

            }
        }
    }
}
