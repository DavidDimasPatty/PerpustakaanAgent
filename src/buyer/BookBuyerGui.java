/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buyer;

/**
 *
 * @author USER
 public interface BookBuyerGui {
    void setAgent(BookBuyerAgent a);
    void show();
    void hide();
    void notifyUser(String message);
}
* */
public interface BookBuyerGui {   
  void setAgent(BookBuyerAgent a);   
  void show();   
  void hide();   
  void notifyUser(String message);   
  void dispose();   
} 