package lib.component;

import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class JImageButton extends JButton implements JImage{

	private static final long serialVersionUID = 1L;
	
	private static final int WIDTH 		= 368;
	private static final int HEIGHT 	= 268;
	
	private String src;
	
	
	
	public JImageButton() {
		this(WIDTH, HEIGHT);	// default크기 (468, 268)
	}
	
	public JImageButton(int width, int height) {
		super();
		setSize(width, height);	// 새로 추가한 메소드
	}
	
	public JImageButton(String src) {
		this(src, WIDTH, HEIGHT);
	}
	
	public JImageButton(String src, int width, int height) {
		setImage(src, width, height);
	}
	
	@Override
	public void setImage() {
		if (src == null || "".equals(src)) {
			return;
		}
		
		this.setBorder(null);
		Image img = Toolkit.getDefaultToolkit().getImage(src);
		if (super.getWidth() == 0 || super.getHeight() == 0) {
			super.setSize(WIDTH, HEIGHT);
		}
		img = img.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
		ImageIcon imgIcon = new ImageIcon(img);
		setIcon(imgIcon);
	}
	
	public void setImage(String src) {
		setImage(src, WIDTH, HEIGHT);
	}
	
	@Override
	public void setImage(String src, int width, int height) {

		super.setSize(width, height);
		setSrc(src);
		setImage();
		
	}
	
	@Override
	public void setSize(int width, int height) {
		super.setSize(width, height);
		setImage();	// 이미지 크기가 반영되도록 수정
	}
	
	@Override
	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
		setImage();
	}
	
	
	// getter & setter
	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

		
	
}
