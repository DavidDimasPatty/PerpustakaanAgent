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
public interface BookSellerGui {       
  void setAgent(BookSellerAgent a);   
  void show();   
  void hide();   
  void notifyUser(String message);   
  void dispose();   

    public void updateGUI();
} 