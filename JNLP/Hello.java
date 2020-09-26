import java.awt.*;
import javax.swing.*;
import java.net.*;
import javax.jnlp.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Hello {
  static BasicService basicService = null;
  public static void main(String args[]) {
    JFrame frame = new JFrame("Hello World");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JLabel label = new JLabel();
    Container content = frame.getContentPane();
    content.add(label, BorderLayout.CENTER);
    String message = "Hello World";

    label.setText(message);

    try {
      basicService = (BasicService)
        ServiceManager.lookup("javax.jnlp.BasicService");
    } catch (UnavailableServiceException e) {
      System.err.println("Lookup failed: " + e);
    }

    frame.pack();
    frame.show();
  }
}