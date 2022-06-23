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
import jade.core.Agent;
import jade.core.behaviours.*;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.domain.*;
import jade.domain.FIPAAgentManagement.*;
import java.io.File;
import java.io.FileNotFoundException;

import java.util.*;

public class BookSellerAgent extends Agent {

    // The catalogue of books available for sale  
    private Vector buyerAgents = new Vector();
    private Map catalogue = new HashMap();
    public LinkedList title = new LinkedList();
    public LinkedList category = new LinkedList();
    public LinkedList qty = new LinkedList();

    // The GUI to interact with the user  
    private BookSellerGui myGui;

    /**
     * Agent initializations
     *
     */
    protected void setup() {
        // Printout a welcome message  
        System.out.println("Seller-agent " + getAID().getName() + " is ready.");
        
        Object[] args = getArguments();   
        if (args != null && args.length > 0) {   
          for (int i = 0; i < args.length; ++i) {   
            AID buyer= new AID((String) args[i], AID.ISLOCALNAME);   
           buyerAgents.addElement(buyer);   
          }   
        }
        
        ReadDb();
        System.out.println(title.size());
        // Create and show the GUI  
        myGui = new BookSellerGuiImpl(title, category, qty);
        myGui.setAgent(this);
        myGui.show();

        // Add the behaviour serving calls for price from buyer agents  
        addBehaviour(new CallForOfferServer());

        // Add the behaviour serving purchase requests from buyer agents  
        // addBehaviour(new PurchaseOrderServer());  
        /**
         * This piece of code, to register services with the DF, is explained in
         * the book in section 4.4.2.1, page 73
         *
         */
        // Register the book-selling service in the yellow pages  
        DFAgentDescription dfd = new DFAgentDescription();
        dfd.setName(getAID());
        ServiceDescription sd = new ServiceDescription();
        sd.setType("Book-selling");
        sd.setName(getLocalName() + "-Book-selling");
        dfd.addServices(sd);
        try {
            DFService.register(this, dfd);
        } catch (FIPAException fe) {
            fe.printStackTrace();
        }
    }

    public void ReadDb() {
        try {
            File myObj = new File("bookDB.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                this.title.add(myReader.nextLine());
                this.category.add(myReader.nextLine());
                this.qty.add(myReader.nextLine());
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void sendADD(String data) {
        System.out.println("masuk: "+data);
        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
        msg.setContent("" + data);
        msg.addReceiver(new AID("buy", AID.ISLOCALNAME));
        msg.setContent(data);
        send(msg);
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
        System.out.println("Seller-agent " + getAID().getName() + "terminating.");

        /**
         * This piece of code, to deregister with the DF, is explained in the
         * book in section 4.4.2.1, page 73
         *
         */
        // Deregister from the yellow pages  
        try {
            DFService.deregister(this);
        } catch (FIPAException fe) {
            fe.printStackTrace();
        }
    }

    /**
     * This method is called by the GUI when the user inserts a new book for
     * sale
     *
     * @param title The title of the book for sale
     * @param initialPrice The initial price
     * @param minPrice The minimum price
     * @param deadline The deadline by which to sell the book
     *
     */
    public void putForSale(String title, int initPrice, int minPrice, Date deadline) {
        addBehaviour(new PriceManager(this, title, initPrice, minPrice, deadline));
    }

    private class PriceManager extends TickerBehaviour {

        private int minPrice, currentPrice, initPrice, deltaP;
        private long initTime, deadline, deltaT;

        private PriceManager(Agent a, String t, int ip, int mp, Date d) {
            super(a, 60000); // tick every minute  
            initPrice = ip;
            currentPrice = initPrice;
            deltaP = initPrice - mp;
            deadline = d.getTime();
            initTime = System.currentTimeMillis();
            deltaT = ((deadline - initTime) > 0 ? (deadline - initTime) : 60000);
        }

        public void onStart() {
            // Insert the book in the catalogue of books available for sale  
            super.onStart();

        }

        public void onTick() {
            long currentTime = System.currentTimeMillis();
            if (currentTime > deadline) {
                // Deadline expired  
                myGui.notifyUser("Cannot sell book " + title);
                catalogue.remove(title);
                stop();
            } else {
                // Compute the current price  
                long elapsedTime = currentTime - initTime;
                currentPrice = (int) Math.round(initPrice - 1.0 * deltaP * (1.0 * elapsedTime / deltaT));
            }
        }

        public int getCurrentPrice() {
            return currentPrice;
        }
    }

    /**
     * Section 4.3.3 , page 67. Inner class CallForOfferServer. This is the
     * behaviour used by Book-seller agents to serve incoming call for offer
     * from buyer agents. If the indicated book is in the local catalogue, the
     * seller agent replies with a PROPOSE message specifying the price.
     * Otherwise a REFUSE message is sent back.
     *
     */
    private class CallForOfferServer extends CyclicBehaviour {

        private MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.CFP);

        public void action() {
            ACLMessage msg = myAgent.receive(mt);
            if (msg != null) {
                // CFP Message received. Process it  
                String title = msg.getContent();
                myGui.notifyUser("Received Proposal to buy " + title);
                ACLMessage reply = msg.createReply();
                PriceManager pm = (PriceManager) catalogue.get(title);
                if (pm != null) {
                    // The requested book is available for sale. Reply with the price  
                    reply.setPerformative(ACLMessage.PROPOSE);
                    reply.setContent(String.valueOf(pm.getCurrentPrice()));
                } else {
                    // The requested book is NOT available for sale.  
                    reply.setPerformative(ACLMessage.REFUSE);
                }
                myAgent.send(reply);
                myGui.notifyUser(pm != null ? "Sent Proposal to sell at " + reply.getContent() : "Refused Proposal as the book is not for sale");
            } else {
                block();
            }
        }
    } // End of inner class CallForOfferServer  

}
