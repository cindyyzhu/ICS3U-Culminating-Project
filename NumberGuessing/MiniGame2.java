//puts it within the NumberGuessing package
package NumberGuessing;

//imports the necessary libraries
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MiniGame2 {
  private JTextField inputField;
  private int randomNum;
  private int numGuess;
  private int numAttempts;

public MiniGame2() {
  //variables to allow the game to work
  randomNum = (int) (10 * Math.random()) + 1;
  numGuess = 0;
  numAttempts = 0;
  
  // instruction panel that shows that the game is about
  JOptionPane.showMessageDialog(null, "I am thinking of a number from 1-10. Guess what \n the number is in less than 4 tries to win the game.");

  //takes user input for their "guesses"
  inputField = new JTextField(10);
  JButton submitButton = new JButton("Submit");

  JPanel panel = new JPanel();
  panel.setLayout(new FlowLayout());
  panel.add(inputField);
  panel.add(submitButton);

  JFrame frame = new JFrame();
  frame.setAlwaysOnTop( true ); //always puts this screen on top of the others
  
  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  frame.setTitle("Number Guessing Game");
  frame.setSize(500, 360); //sets to default size
  frame.getContentPane().add(panel, BorderLayout.CENTER);
  frame.setVisible(true);

  submitButton.addActionListener(new ActionListener() {
      @Override
      //gets user's input 
        public void actionPerformed(ActionEvent e) {
        String input = inputField.getText();
        try {
        int guess = Integer.parseInt(input);

        //checks to see if the user's input is out of range
        if (guess < 1 || guess > 10) {
        JOptionPane.showMessageDialog(null, "Please enter a number between 1 and 10.");
      } else {
        numGuess = guess;
        numAttempts++;

      //congratulatory message if the user was correct
      if (numGuess == randomNum) {
        JOptionPane.showMessageDialog(null, "Congratulations! You guessed the correct number.");
        frame.setVisible(false);

        } 
      //wrong guess message
      else {
        JOptionPane.showMessageDialog(null, "Wrong guess. Try again.");

      //if user does not guess the number by the end
        if (numAttempts >= 4 && numGuess != randomNum) {
          JOptionPane.showMessageDialog(null, "Sorry, you have reached the maximum number of attempts. You lose!");
          JOptionPane.showMessageDialog(null, "The correct number was: " + randomNum);
          //hides the frame
          frame.setVisible(false);
             }
           }
     }
   } catch (NumberFormatException ex) {
    JOptionPane.showMessageDialog(null, "Invalid input. Please enter a number."); //if the user puts something else
      }
    inputField.setText("");
            }
        });
    }
    public static void minigame2(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MiniGame2 game = new MiniGame2();
            }
        });
    }
}
