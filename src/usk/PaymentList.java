package usk;

import java.awt.Font;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class PaymentList extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JScrollPane scrollPane;
	private JPanel panel_1;
	private JLabel lblNewLabel;
	private JLabel label;
	private JLabel lblNewLabel_1;
	private JTextArea textArea;
	private JPanel panel_2;
	private JPanel panel_3;
	private JCheckBox checkBox;
	private JLabel label_1;
	private JTextField textField;
	private JPanel panel_4;
	private JCheckBox checkBox_1;
	private JLabel label_2;
	private JTextField textField_1;
	private JPanel panel_5;
	private JCheckBox checkBox_2;
	private JLabel label_3;
	private JTextField textField_2;
	private JTable table;

	/**
	 * Create the panel.
	 */
	public PaymentList() {
		setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(12, 29, 755, 545);
		add(panel);
		panel.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 404, 731, 131);
		panel.add(scrollPane);
		
		panel_2 = new JPanel();
		scrollPane.setViewportView(panel_2);
		panel_2.setLayout(null);
		
		panel_3 = new JPanel();
		panel_3.setBounds(0, 10, 729, 33);
		panel_2.add(panel_3);
		
		checkBox = new JCheckBox("ONE/TWO DAY TICHETS");
		panel_3.add(checkBox);
		
		label_1 = new JLabel("티켓번호");
		panel_3.add(label_1);
		
		textField = new JTextField();
		textField.setText("121515");
		textField.setHorizontalAlignment(SwingConstants.RIGHT);
		textField.setColumns(10);
		panel_3.add(textField);
		
		panel_4 = new JPanel();
		panel_4.setBounds(0, 53, 729, 33);
		panel_2.add(panel_4);
		
		checkBox_1 = new JCheckBox("ONE/TWO DAY TICHETS");
		panel_4.add(checkBox_1);
		
		label_2 = new JLabel("티켓번호");
		panel_4.add(label_2);
		
		textField_1 = new JTextField();
		textField_1.setText("121515");
		textField_1.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_1.setColumns(10);
		panel_4.add(textField_1);
		
		panel_5 = new JPanel();
		panel_5.setBounds(0, 96, 729, 33);
		panel_2.add(panel_5);
		
		checkBox_2 = new JCheckBox("ONE/TWO DAY TICHETS");
		panel_5.add(checkBox_2);
		
		label_3 = new JLabel("티켓번호");
		panel_5.add(label_3);
		
		textField_2 = new JTextField();
		textField_2.setText("121515");
		textField_2.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_2.setColumns(10);
		panel_5.add(textField_2);
		
		panel_1 = new JPanel();
		panel_1.setBounds(12, 10, 731, 384);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		lblNewLabel = new JLabel("회원번호");
		lblNewLabel.setBounds(43, 63, 57, 15);
		panel_1.add(lblNewLabel);
		
		label = new JLabel("결제완료");
		label.setBounds(12, 10, 109, 45);
		label.setFont(new Font("궁서", Font.BOLD, 25));
		panel_1.add(label);
		
		lblNewLabel_1 = new JLabel("000");
		lblNewLabel_1.setBounds(103, 63, 57, 15);
		panel_1.add(lblNewLabel_1);
		
		textArea = new JTextArea();
		textArea.setBounds(12, 230, 707, 144);
		textArea.setText("⩥ 주의사항\r\n결제완료후의 결제취소는 일절 불가능합니다.\r\n(단, 법률상의 해제 또는 무효사유등이 고객여러분들에게 인정되는 경우에는 해당되지 않습니다.)\r\n선택하신 내용에 잘못이 없는지 반드시 확인한 후에, 결제해주세요.\r\n또한, 해당 파크에서는, 티켓의 전매행위를 엄격하게 금지하고 있습니다.\r\n해당규약에 위반시, 위반자가 구입한 티켓의 QR코드를 무효시킵니다.\r\n이쪽에 동의 하시는 경우에만, 체크를 하고 하기의 버튼으로부터 다음단계로 진행해주세요.");
		panel_1.add(textArea);
		
		table = new JTable();
		table.setBounds(187, 63, 277, 164);
		panel_1.add(table);

	}
}
