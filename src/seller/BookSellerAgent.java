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

        ReadDb();
        System.out.println(title.size());
        // Create and show the GUI  
        myGui = new BookSellerGuiImpl(title, category, qty);
        myGui.setAgent(this);
        myGui.show();
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
        System.out.println("masuk: " + data);
        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
        msg.setContent("" + data);
        msg.addReceiver(new AID("buy", AID.ISLOCALNAME));
        msg.setContent(data);
        send(msg);
    }

  
    protected void takeDown() {
        // Dispose the GUI if it is there  
        if (myGui != null) {
            myGui.dispose();
        }

        // Printout a dismissal message  
        System.out.println("Seller-agent " + getAID().getName() + "terminating.");

        try {
            DFService.deregister(this);
        } catch (FIPAException fe) {
            fe.printStackTrace();
        }
    }

}
