package teletext;
/**
 * Input field for Teletext
 */

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TeletextInput extends JPanel
      implements ActionListener
{
  private TeletextList msgList;

  public TeletextInput(TeletextList msgList)
  {
    this.msgList = msgList;
    JTextField input = new JTextField(40);
    input.setBackground(Color.LIGHT_GRAY);
    input.addActionListener(this);
    add(input);
  }

  // Handles input events: called when user presses <Enter>
  public void actionPerformed(ActionEvent e)
  {
    JTextField input = (JTextField)e.getSource();
    String msg = input.getText().trim();
    if ("d".equalsIgnoreCase(msg))
      msgList.delete();
    else
      msgList.insert(msg);
    input.setText("");
  }
}
