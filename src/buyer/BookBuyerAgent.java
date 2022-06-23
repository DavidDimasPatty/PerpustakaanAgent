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
import seller.BookSellerAgent;

public class BookBuyerAgent extends Agent {
    // The list of known seller agents   

    private Vector sellerAgents = new Vector();

    // The GUI to interact with the user   
    private BookBuyerGui myGui;

    /**
     * Agent initializations  
   *
     */
    protected void setup() {
        // Printout a welcome message   
        System.out.println("Buyer-agent " + getAID().getName() + " is ready.");

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

        /**
         * This piece of code, to search services with the DF, is explained in
         * the book in section 4.4.3, page 74  
    *
         */
        // Update the list of seller agents every minute   
        addBehaviour(new TickerBehaviour(this, 60000) {
            protected void onTick() {
                // Update the list of seller agents   
                DFAgentDescription template = new DFAgentDescription();
                ServiceDescription sd = new ServiceDescription();
                sd.setType("Book-selling");
                template.addServices(sd);
                try {
                    DFAgentDescription[] result = DFService.search(myAgent, template);
                    sellerAgents.clear();
                    for (int i = 0; i < result.length; ++i) {
                        sellerAgents.addElement(result[i].getName());
                    }
                } catch (FIPAException fe) {
                    fe.printStackTrace();
                }
            }
        });
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

    /**
     * This method is called by the GUI when the user inserts a new book to buy
     *
     * @param title The title of the book to buy
     * @param maxPrice The maximum acceptable price to buy the book
     * @param deadline The deadline by which to buy the book  
  *
     */
    public void purchase(String title, int maxPrice, Date deadline) {
        // the following line is in the book at page 62   
        addBehaviour(new PurchaseManager(this, title, maxPrice, deadline));
    }

    /**
     * This method is called by the GUI. At the moment it is not implemented.  
   *
     */
    public void setCreditCard(String creditCarNumber) {
    }

    /**
     * Section 4.2.4, Page 62  
   *
     */
    private class PurchaseManager extends TickerBehaviour {

        private String title;
        private int maxPrice, startPrice;
        private long deadline, initTime, deltaT;

        private PurchaseManager(Agent a, String t, int mp, Date d) {
            super(a, 60000); // tick every minute   
            title = t;
            maxPrice = mp;
            deadline = d.getTime();
            initTime = System.currentTimeMillis();
            deltaT = deadline - initTime;
        }

        public void onTick() {
            long currentTime = System.currentTimeMillis();
            if (currentTime > deadline) {
                // Deadline expired   
                myGui.notifyUser("Cannot buy book " + title);
                stop();
            } else {
                // Compute the currently acceptable price and start a negotiation   
                long elapsedTime = currentTime - initTime;
                int acceptablePrice = (int) Math.round(1.0 * maxPrice * (1.0 * elapsedTime / deltaT));
                // System.out.println("elapsedTime"+elapsedTime+"deltaT"+deltaT+"acceptablePrice"+acceptablePrice+"maxPrice="+maxPrice);   
                myAgent.addBehaviour(new BookNegotiator(title, acceptablePrice, this));
            }
        }
    }

    class waitingBehaviour extends CyclicBehaviour {

        ACLMessage msg = receive();

        public waitingBehaviour(Agent a) {
            super(a);
        }

        public void action() {
            
                msg = receive();
                if (msg != null) {
                    String lastMsg = msg.getContent();
                    msg = new ACLMessage(ACLMessage.INFORM);
                    // menjawab tawaran
                    msg.setContent("Okay!");
                    msg.addReceiver(new AID("Player2", AID.ISLOCALNAME));
                    System.out.println("Player2-> Player1: " + msg.getContent());
                    send(msg); // masuk ke tahap bermain
                
               
            }
        }
    }

    /**
     * Section 4.3.5 of the book, page 69 Inner class BookNegotiator. This is
     * the behaviour used by Book-buyer agents to actually negotiate with seller
     * agents the purchase of a book.  
  *
     */
    private class BookNegotiator extends Behaviour {

        private String title;
        private int maxPrice;
        private PurchaseManager manager;
        private AID bestSeller; // The seller agent who provides the best offer   
        private int bestPrice; // The best offered price   
        private int repliesCnt = 0; // The counter of replies from seller agents   
        private MessageTemplate mt; // The template to receive replies   
        private int step = 0;

        public BookNegotiator(String t, int p, PurchaseManager m) {
            super(null);
            title = t;
            maxPrice = p;
            manager = m;
        }

        public void action() {
            switch (step) {
                case 0:
                    // Send the cfp to all sellers   
                    ACLMessage cfp = new ACLMessage(ACLMessage.CFP);
                    for (int i = 0; i < sellerAgents.size(); ++i) {
                        cfp.addReceiver((AID) sellerAgents.elementAt(i));
                    }
                    cfp.setContent(title);
                    cfp.setConversationId("book-trade");
                    cfp.setReplyWith("cfp" + System.currentTimeMillis()); // Unique value   
                    myAgent.send(cfp);
                    myGui.notifyUser("Sent Call for Proposal");

                    // Prepare the template to get proposals   
                    mt = MessageTemplate.and(
                            MessageTemplate.MatchConversationId("book-trade"),
                            MessageTemplate.MatchInReplyTo(cfp.getReplyWith()));
                    step = 1;
                    break;
                case 1:
                    // Receive all proposals/refusals from seller agents   
                    ACLMessage reply = myAgent.receive(mt);
                    if (reply != null) {
                        // Reply received   
                        if (reply.getPerformative() == ACLMessage.PROPOSE) {
                            // This is an offer   
                            int price = Integer.parseInt(reply.getContent());
                            myGui.notifyUser("Received Proposal at " + price + " when maximum acceptable price was " + maxPrice);
                            if (bestSeller == null || price < bestPrice) {
                                // This is the best offer at present   
                                bestPrice = price;
                                bestSeller = reply.getSender();
                            }
                        }
                        repliesCnt++;
                        if (repliesCnt >= sellerAgents.size()) {
                            // We received all replies   
                            step = 2;
                        }
                    } else {
                        block();
                    }
                    break;
                case 2:
                    if (bestSeller != null && bestPrice <= maxPrice) {
                        // Send the purchase order to the seller that provided the best offer   
                        ACLMessage order = new ACLMessage(ACLMessage.ACCEPT_PROPOSAL);
                        order.addReceiver(bestSeller);
                        order.setContent(title);
                        order.setConversationId("book-trade");
                        order.setReplyWith("order" + System.currentTimeMillis());
                        myAgent.send(order);
                        myGui.notifyUser("sent Accept Proposal");
                        // Prepare the template to get the purchase order reply   
                        mt = MessageTemplate.and(
                                MessageTemplate.MatchConversationId("book-trade"),
                                MessageTemplate.MatchInReplyTo(order.getReplyWith()));
                        step = 3;
                    } else {
                        // If we received no acceptable proposals, terminate   
                        step = 4;
                    }
                    break;
                case 3:
                    // Receive the purchase order reply   
                    reply = myAgent.receive(mt);
                    if (reply != null) {
                        // Purchase order reply received   
                        if (reply.getPerformative() == ACLMessage.INFORM) {
                            // Purchase successful. We can terminate   
                            myGui.notifyUser("Book " + title + " successfully purchased. Price = " + bestPrice);
                            manager.stop();
                        }
                        step = 4;
                    } else {
                        block();
                    }
                    break;
            } // end of switch   
        }

        public boolean done() {
            return step == 4;
        }
    } // End of inner class BookNegotiator   
}
