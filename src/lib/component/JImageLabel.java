package lib.component;

import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class JImageLabel extends JLabel implements JImage{



	private static final long serialVersionUID = 1L;
	
	
	private static final int WIDTH = 459;
	private static final int HEIGHT = 260;
	
	private String src = "";
	
	public JImageLabel() {
		super();
		super.setSize(WIDTH, HEIGHT);
	}
	
	public JImageLabel(String src) {
		super();
		setSize(WIDTH, HEIGHT);
	}
	
	public JImageLabel(String src, int width, int height) {
		setImage(src, width, height);
	}

	
	
	// getter & setter
	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		
		this.src = src;
		if (getWidth() == 0 || getHeight() == 0) {
			super.setSize(WIDTH, HEIGHT);
		}
	}
	
	public void setImage(String src) {
		setSrc(src);
		setImage();
	}
	
	@Override
	public void setImage(String src, int width, int height) {
		
		setSrc(src);
		setSize(width, height);
		setImage();

	}
	
	@Override
	public void setImage() {
		if (src == null || "".equals(src)) {
			return;
		}
		
		this.setBorder(null);
		Image img = Toolkit.getDefaultToolkit().getImage(src);
		img = img.getScaledInstance(super.getWidth(), super.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon imgIcon = new ImageIcon(img);
		setIcon(imgIcon);
	}
	
	@Override
	public void setSize(int width, int height) {
		super.setSize(width, height);
		setImage();
	}
	
	@Override
	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
		setImage();
	}
	
	
}
