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
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        title.clear();
        category.clear();
        qty.clear();
        ReadDb();
        System.out.println(title.size());
        // Create and show the GUI  
        myGui = new BookSellerGuiImpl(title, category, qty);
        myGui.setAgent(this);
        myGui.show();
        addBehaviour(new waitingBehaviour(this));
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

    public void bookReturnCourier(String data) {
        System.out.println("masuk: " + data);
        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
        msg.setContent("" + data);
        msg.addReceiver(new AID("kurir", AID.ISLOCALNAME));
        msg.setContent(data);
        send(msg);

    }

    public void bookReturn(String data) {
        int qt = Integer.parseInt(qty.get(Integer.parseInt(data)).toString().trim());
        int add = qt + 1;

        myGui.dispose();

        Path path = Paths.get("bookDB.txt");
        List<String> lines;

        try {
            lines = Files.readAllLines(path, StandardCharsets.UTF_8);
            lines.set(((Integer.parseInt(data) + 1) * 3) - 1, Integer.toString(add));
            Files.write(path, lines, StandardCharsets.UTF_8);

        } catch (IOException ex) {
            Logger.getLogger(BookSellerAgent.class.getName()).log(Level.SEVERE, null, ex);
        }

        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
        msg.setContent("doneRead");
        msg.addReceiver(new AID("buy", AID.ISLOCALNAME));
        send(msg);

        setup();
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

                int pos = title.indexOf(msg.getContent());
                System.out.println("pos= " + pos);
                int qt = Integer.parseInt(qty.get(pos).toString().trim());
                int kurang = qt - 1;

                String book = title.get(pos).toString();
                String categ = category.get(pos).toString();

                myGui.dispose();

                Path path = Paths.get("bookDB.txt");
                List<String> lines;

                try {
                    lines = Files.readAllLines(path, StandardCharsets.UTF_8);
                    lines.set(((pos)*3)+2, Integer.toString(kurang));
                    Files.write(path, lines, StandardCharsets.UTF_8);

                } catch (IOException ex) {
                    Logger.getLogger(BookSellerAgent.class.getName()).log(Level.SEVERE, null, ex);
                }

                ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
                msg.setContent("doneRead");
                msg.addReceiver(new AID("buy", AID.ISLOCALNAME));
                send(msg);
                setup();
            }
        }
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
