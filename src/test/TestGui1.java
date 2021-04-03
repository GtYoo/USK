package test;


import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import javax.swing.*;

public class TestGui1 extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private List<JTextField> fields = new ArrayList<JTextField>();

	public TestGui1() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(createPane(3, "One ", Color.red));
        this.add(createPane(3, "Two ", Color.green));
        this.add(createPane(10, "Three ", Color.blue));
    }
	
	private JPanel createPane(int n, String s, Color c) {
        JPanel outer = new JPanel();
        outer.setLayout(new BoxLayout(outer, BoxLayout.Y_AXIS));
        outer.setBorder(BorderFactory.createLineBorder(c, 2));
        for (int i = 0; i < n; i++) {
            JPanel inner = new JPanel();
            inner.setLayout(new BoxLayout(inner, BoxLayout.X_AXIS));
            JLabel label = new JLabel(s + i + ":", JLabel.RIGHT);
            label.setPreferredSize(new Dimension(80, 32));
            inner.add(label);
            JTextField tf = new JTextField("Stackoverflow!", 32);
            inner.add(tf);
            fields.add(tf);
            outer.add(inner);
        }
        return outer;
    }
	
	private void display() {
		JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JScrollPane jsp = new JScrollPane(this,
            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.validate();
        Dimension d = this.getPreferredSize();
        d.height /= 2;
        jsp.getViewport().setPreferredSize(d);
        jsp.getVerticalScrollBar().setUnitIncrement(
            this.getPreferredSize().height / fields.size());
        f.add(jsp);
        f.pack();
        f.setVisible(true);
    }
	    
	public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
	            @Override
            public void run() {
                new TestGui1().display();
            }
        });
    }
}

