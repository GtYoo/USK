package usk;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class D_Payment extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JScrollPane scrollPane;
	private JPanel panel;
	private JLabel label;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JTextArea textArea;

	/**
	 * Create the panel.
	 */
	public D_Payment() {
		setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(32, 55, 861, 574);
		add(scrollPane);
		
		panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(null);
		
		label = new JLabel("결제");
		label.setFont(new Font("궁서", Font.BOLD, 25));
		label.setBounds(12, 10, 50, 30);
		panel.add(label);
		
		lblNewLabel = new JLabel("회원번호");
		lblNewLabel.setBounds(64, 86, 57, 15);
		panel.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("000");
		lblNewLabel_1.setBounds(151, 86, 57, 15);
		panel.add(lblNewLabel_1);
		
		textArea = new JTextArea();
		textArea.setText("⩥ 주의 사항\r\n결제완료후의 결제취소는 일절 불가능합니다.\r\n(단, 법률상의 해제 또는 무효사유등이 고객여러분들에게 인정되는 경우에는 해당되지 않습니다.)\r\n선택하신 내용에 잘못이 없는지 반드시 확인한 후에, 결제해주세요.\r\n또한, 해당 파크에서는, 티켓의 전매행위를 엄격하게 금지하고 있습니다.\r\n해당규약에 위반시, 위반자가 구입한 티켓의 QR코드를 무효시킵니다.\r\n이쪽에 동의 하시는 경우에만, 체크를 하고 하기의 버튼으로부터 다음단계로 진행해주세요.");
		textArea.setBounds(12, 392, 602, 144);
		panel.add(textArea);

	}
}
