/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kurir;

import jade.core.*;
import jade.core.behaviours.*;
import jade.lang.acl.*;

import jade.domain.*;
import jade.domain.FIPAAgentManagement.*;
import java.io.File;
import java.io.FileNotFoundException;

import java.util.Vector;
import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;
import seller.BookSellerAgent;

public class kurirAgent extends Agent {
    // The list of known seller agents   

    private Vector sellerAgents = new Vector();

    // The GUI to interact with the user   
    private kurirGuiImpl myGui;
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
        ReadDb();
        System.out.println(title.size());

        // Show the GUI to interact with the user   
        myGui = new kurirGuiImpl(title, category, qty);
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
                myGui.tambah(lastMsg);
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
