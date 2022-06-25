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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.Vector;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        title.clear();
        category.clear();
        qty.clear();
        // Get names of seller agents as arguments   
        ReadDb();
        System.out.println(title.size());

        // Show the GUI to interact with the user   
        myGui = new BookBuyerGuiImpl(title, category, qty);
        myGui.setAgent(this);
        myGui.show();
        addBehaviour(new waitingBehaviour(this));
    }

     public void newsetup() {
        myGui.dispose();
        myGui = new BookBuyerGuiImpl(title, category, qty);
        ReadDb();
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

    public void sendCourrier(String data) {
        System.out.println("masuk: " + data);
        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
        msg.setContent("" + data);
        msg.addReceiver(new AID("kurir", AID.ISLOCALNAME));
        msg.setContent(data);
        send(msg);

    }

    public void bookTaken(String data) {
        System.out.println("masuk: " + data);
        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
        msg.setContent("" + data);
        msg.addReceiver(new AID("sell", AID.ISLOCALNAME));
        msg.setContent(data);
        send(msg);

    }

    class waitingBehaviour extends CyclicBehaviour {

        ACLMessage msg = receive();

        public waitingBehaviour(Agent a) {
            super(a);
        }

        public void action() {
            msg = receive();
            if (msg != null && !msg.getContent().equals("doneRead")) {

                System.out.println("new msg: " + msg.getContent());
                String lastMsg = msg.getContent();
                String[] tokens = lastMsg.split(" ");
                title.add(tokens[0]);
                category.add(tokens[1]);
                qty.add(tokens[2]);
                myGui.dispose();
                setup();
            }

            if (msg != null && msg.getContent().equals("doneRead")) {
                
               
                myGui.dispose();
                setup();
            }
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
}
