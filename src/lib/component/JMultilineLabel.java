package lib.component;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class JMultilineLabel extends JTextArea {

	 private static final long serialVersionUID = 1L;
	 public JMultilineLabel(String text){
	    this(text, 3, 5);
	 }
	 
	 public JMultilineLabel(String text, int rows, int columns) {
		 super(text, rows, columns);
		 setEditable(false);  
		 setCursor(null);  
		 setOpaque(false);  
		 setFocusable(false);  
		 setFont(UIManager.getFont("Label.font"));      
		 setWrapStyleWord(true);  
		 setLineWrap(true);
		 //According to Mariana this might improve it
		 setBorder(new EmptyBorder(5, 5, 5, 5));  
		 setAlignmentY(JLabel.LEFT);
	 }
}
