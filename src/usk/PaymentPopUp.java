package usk;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;



public class PaymentPopUp extends JDialog implements ActionListener, KeyListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private final JPanel contentPanel = new JPanel();
	private JLabel lblCardInformation;
	private JTextField tfCardNum_1;
	private JTextField tfCardNum_2;
	private JTextField tfCardNum_3;
	private JTextField tfCardNum_4;
	private JLabel lblCvc;
	private JTextField tfCVCNum;
	private JLabel lblabel;
	private JTextField tfCardPass;
	private JLabel lblNewLabel;
	private JComboBox comboBox;
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private PaymentList paymentlist;
	
	private JButton okButton;
	private JButton cancelButton;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			PaymentPopUp dialog = new PaymentPopUp();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public PaymentPopUp() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		lblCardInformation = new JLabel("카드정보");
		lblCardInformation.setBounds(22, 10, 57, 15);
		contentPanel.add(lblCardInformation);
		
		tfCardNum_1 = new JTextField();
		tfCardNum_1.setBounds(58, 92, 57, 21);
		contentPanel.add(tfCardNum_1);
		tfCardNum_1.setColumns(10);
		tfCardNum_1.addKeyListener(this);
		
		
		
		tfCardNum_2 = new JTextField();
		tfCardNum_2.setColumns(10);
		tfCardNum_2.setBounds(145, 92, 57, 21);
		contentPanel.add(tfCardNum_2);
		tfCardNum_2.addKeyListener(this);
		
		tfCardNum_3 = new JTextField();
		tfCardNum_3.setColumns(10);
		tfCardNum_3.setBounds(235, 92, 57, 21);
		contentPanel.add(tfCardNum_3);
		tfCardNum_3.addKeyListener(this);
		
		tfCardNum_4 = new JTextField();
		tfCardNum_4.setColumns(10);
		tfCardNum_4.setBounds(321, 92, 57, 21);
		contentPanel.add(tfCardNum_4);
		tfCardNum_4.addKeyListener(this);
		
		lblCvc = new JLabel("CVC");
		lblCvc.setBounds(293, 146, 26, 15);
		contentPanel.add(lblCvc);
		
		tfCVCNum = new JTextField();
		tfCVCNum.setBounds(325, 143, 57, 21);
		contentPanel.add(tfCVCNum);
		tfCVCNum.setColumns(10);
		tfCVCNum.addKeyListener(this);
		
		lblabel = new JLabel("카드비밀번호");
		lblabel.setBounds(94, 149, 72, 15);
		contentPanel.add(lblabel);
		
		tfCardPass = new JTextField();
		tfCardPass.setBounds(172, 149, 41, 15);
		contentPanel.add(tfCardPass);
		tfCardPass.setColumns(10);
		tfCardPass.addKeyListener(this);
		
		lblNewLabel = new JLabel("**");
		lblNewLabel.setBounds(212, 149, 12, 15);
		contentPanel.add(lblNewLabel);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"--은행선택--", "카카오뱅크", "KB국민은행", "NH농협", "IBK기업", "SC제일", "씨티", "새마을은행", "우리은행", "신한은행", "하나은행", "짱깨건설", "부산은행", "대구은행", "경남은행", "광주은행", "전북은행", "제주은행"}));
		comboBox.setBounds(35, 47, 98, 21);
		contentPanel.add(comboBox);
		
		label = new JLabel("-");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(127, 95, 6, 15);
		contentPanel.add(label);
		
		label_1 = new JLabel("-");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(214, 95, 6, 15);
		contentPanel.add(label_1);
		
		label_2 = new JLabel("-");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(304, 95, 6, 15);
		contentPanel.add(label_2);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
//				JButton okButton = new JButton("OK");
//				okButton.setActionCommand("OK");
//				buttonPane.add(okButton);
//				getRootPane().setDefaultButton(okButton);
//				okButton.addActionListener(this);
				okButton = new JButton("결제");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				okButton.addActionListener(this);
			}
			{
//				JButton cancelButton = new JButton("Cancel");
//				cancelButton.setActionCommand("Cancel");
//				buttonPane.add(cancelButton);
//				cancelButton.addActionListener(this);
				cancelButton = new JButton("결제취소");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				cancelButton.addActionListener(this);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == okButton)
		{
			JOptionPane.showMessageDialog(null, "결제완료");
			System.exit(0);
//			PaymentList paymentlist = new PaymentList();
//			paymentlist.setBounds(0, 0, 1042, 611);
//						
//			Utility.changePanel(Panel, paymentlist);
			
		}
		else if(e.getSource() == cancelButton)
		{
			JOptionPane.showMessageDialog(null, "결제취소");
			System.exit(0);
			
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		//카드번호 글자제한
		String text = tfCardNum_1.getText();
		if (text.length() > 4) {
			tfCardNum_1.setText(text.substring(0 , 4));
		}
		
		String text2 = tfCardNum_2.getText();
		if (text2.length() > 4) {
			tfCardNum_2.setText(text2.substring(0 , 4));
		}
		
		String text3 = tfCardNum_3.getText();
		if (text3.length() > 4) {
			tfCardNum_3.setText(text3.substring(0 , 4));
		}
		
		String text4 = tfCardNum_4.getText();
		if (text4.length() > 4) {
			tfCardNum_4.setText(text4.substring(0 , 4));
		}
		
		// 카드 비밀번호제한
		String text5 = tfCardPass.getText();
		if (text5.length() > 2) {
			tfCardPass.setText(text5.substring(0 , 2));
		}
		
		// CVC번호입력
		String text6 = tfCVCNum.getText();
		if (text6.length() > 2) {
			tfCVCNum.setText(text6.substring(0 , 2));
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
