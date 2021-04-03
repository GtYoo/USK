package usk;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import util.Common;
import util.Utility;

public class C_ReservationConfirmationList extends JPanel implements ActionListener{
	
	
	private static final long serialVersionUID = 1L;
	
	
	
	private JPanel panel;
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;
	private JLabel label_4;
	private JLabel label_5;
	private JCheckBox checkBox;
	private JCheckBox checkBox_1;
	private JTextField textField;
	private JButton btnPayment;
	private JButton btnConsent;
	private JButton btnReservationCancel;
	private JLabel lblNewLabel;
	private JLabel label_6;
	private JLabel label_7;
	private JTextField tfAdultPrice;
	private JTextField tfChildPrice;
	private JTextField tfOldPrice;
	private JLabel label_8;
	private JLabel label_9;
	private JLabel label_10;
	private JLabel lblNewLabel_1;
	private JLabel label_11;
	private JLabel label_12;
	private JTextField tfChildCheckPrice;
	private JTextField tfAdultCheckPrice;
	private JTextField tfOldCheckPrice;
	private JScrollPane scrollPane;
	
	private MainUI panelMainUI;
	private TicketsReservation TicketsReservation;
//	private B_TicketsReservation TicketsReservation;
	private D_Payment Payment;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JLabel lblNewLabel_2;
	private JLabel label_13;
	private JLabel label_14;
	

	/**
	 * Create the panel.
	 */
	public C_ReservationConfirmationList(){
		
//		TicketsReservation = new B_TicketsReservation();
		
		setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(-1, -8, 586, 508);
		add(scrollPane);
		
		panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(null);
		
		label = new JLabel("예약목록");
		label.setBounds(12, 26, 109, 45);
		label.setFont(new Font("궁서", Font.BOLD, 25));
		panel.add(label);
		
		label_1 = new JLabel("회원번호");
		label_1.setBounds(48, 132, 82, 27);
		label_1.setHorizontalAlignment(SwingConstants.LEFT);
		label_1.setFont(new Font("굴림", Font.PLAIN, 18));
		panel.add(label_1);
		
		label_2 = new JLabel("티켓종류");
		label_2.setBounds(159, 134, 82, 25);
		label_2.setHorizontalAlignment(SwingConstants.LEFT);
		label_2.setFont(new Font("굴림", Font.PLAIN, 18));
		panel.add(label_2);
		
		label_3 = new JLabel("구분");
		label_3.setBounds(270, 134, 42, 27);
		label_3.setHorizontalAlignment(SwingConstants.LEFT);
		label_3.setFont(new Font("굴림", Font.PLAIN, 18));
		panel.add(label_3);
		
		label_4 = new JLabel("매수");
		label_4.setBounds(375, 134, 42, 27);
		label_4.setHorizontalAlignment(SwingConstants.LEFT);
		label_4.setFont(new Font("굴림", Font.PLAIN, 18));
		panel.add(label_4);
		
		label_5 = new JLabel("가격");
		label_5.setBounds(498, 133, 42, 25);
		label_5.setHorizontalAlignment(SwingConstants.LEFT);
		label_5.setFont(new Font("굴림", Font.PLAIN, 18));
		panel.add(label_5);
		
		checkBox = new JCheckBox("A0458");
		checkBox.setBounds(26, 204, 115, 23);
		panel.add(checkBox);
		
		checkBox_1 = new JCheckBox("A0458");
		checkBox_1.setBounds(26, 295, 115, 23);
		panel.add(checkBox_1);
		
		textField = new JTextField();
		textField.setBounds(48, 324, 70, 21);
		textField.setText("결제여부");
		panel.add(textField);
		textField.setColumns(10);
		
		btnPayment = new JButton("결제");
		btnPayment.setBounds(255, 409, 82, 23);
		panel.add(btnPayment);
		btnPayment.addActionListener(this);
		
		btnConsent = new JButton("확인");
		btnConsent.setBounds(366, 409, 82, 23);
		panel.add(btnConsent);
		btnConsent.addActionListener(this);
		
		btnReservationCancel = new JButton("예약취소");
		btnReservationCancel.setBounds(471, 409, 81, 23);
		panel.add(btnReservationCancel);
		btnReservationCancel.addActionListener(this);
		
		lblNewLabel = new JLabel("어른");
		lblNewLabel.setBounds(270, 185, 57, 15);
		panel.add(lblNewLabel);
		
		label_6 = new JLabel("어린이");
		label_6.setBounds(270, 209, 57, 15);
		panel.add(label_6);
		
		label_7 = new JLabel("노인");
		label_7.setBounds(270, 236, 57, 15);
		panel.add(label_7);
		
		tfAdultPrice = new JTextField();
		tfAdultPrice.setHorizontalAlignment(SwingConstants.RIGHT);
		tfAdultPrice.setBounds(375, 182, 42, 21);
		panel.add(tfAdultPrice);
		tfAdultPrice.setColumns(10);
		System.out.println(String.valueOf(Common.getHm().get("ADULT")));
		tfAdultPrice.setText(String.valueOf(Common.getHm().get("ADULT")));
		
		
		tfChildPrice = new JTextField();
		tfChildPrice.setHorizontalAlignment(SwingConstants.RIGHT);
		tfChildPrice.setBounds(375, 206, 42, 21);
		tfChildPrice.setColumns(10);
		panel.add(tfChildPrice);
		tfChildPrice.setText(String.valueOf(Common.getHm().get("CHILD")));
		
		
		tfOldPrice = new JTextField();
		tfOldPrice.setHorizontalAlignment(SwingConstants.RIGHT);
		tfOldPrice.setBounds(375, 233, 42, 21);
		tfOldPrice.setColumns(10);
		panel.add(tfOldPrice);
		tfOldPrice.setText(String.valueOf(Common.getHm().get("OLD")));;
		
		
		label_8 = new JLabel("노인");
		label_8.setBounds(270, 327, 57, 15);
		panel.add(label_8);
		
		label_9 = new JLabel("어린이");
		label_9.setBounds(270, 303, 57, 15);
		panel.add(label_9);
		
		label_10 = new JLabel("어른");
		label_10.setBounds(270, 276, 57, 15);
		panel.add(label_10);
		
		lblNewLabel_1 = new JLabel("₩ 60,000");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(483, 185, 57, 15);
		panel.add(lblNewLabel_1);
		
		label_11 = new JLabel("₩ 12,000");
		label_11.setHorizontalAlignment(SwingConstants.RIGHT);
		label_11.setBounds(483, 208, 57, 15);
		panel.add(label_11);
		
		label_12 = new JLabel("₩ 10,000");
		label_12.setHorizontalAlignment(SwingConstants.RIGHT);
		label_12.setBounds(483, 233, 57, 15);
		panel.add(label_12);
		
		tfChildCheckPrice = new JTextField();
		tfChildCheckPrice.setHorizontalAlignment(SwingConstants.RIGHT);
		tfChildCheckPrice.setBounds(375, 297, 42, 21);
		tfChildCheckPrice.setColumns(10);
		panel.add(tfChildCheckPrice);
		
		tfAdultCheckPrice = new JTextField();
		tfAdultCheckPrice.setHorizontalAlignment(SwingConstants.RIGHT);
		tfAdultCheckPrice.setBounds(375, 273, 42, 21);
		tfAdultCheckPrice.setColumns(10);
		panel.add(tfAdultCheckPrice);
		
		tfOldCheckPrice = new JTextField();
		tfOldCheckPrice.setHorizontalAlignment(SwingConstants.RIGHT);
		tfOldCheckPrice.setBounds(375, 321, 42, 21);
		tfOldCheckPrice.setColumns(10);
		panel.add(tfOldCheckPrice);
		
		textField_1 = new JTextField();
		textField_1.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_1.setColumns(10);
		textField_1.setBounds(506, 273, 46, 21);
		panel.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_2.setColumns(10);
		textField_2.setBounds(506, 299, 46, 21);
		panel.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_3.setColumns(10);
		textField_3.setBounds(506, 324, 46, 21);
		panel.add(textField_3);
		
		lblNewLabel_2 = new JLabel("₩");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setBounds(460, 276, 42, 15);
		panel.add(lblNewLabel_2);
		
		label_13 = new JLabel("₩");
		label_13.setHorizontalAlignment(SwingConstants.RIGHT);
		label_13.setBounds(460, 303, 42, 15);
		panel.add(label_13);
		
		label_14 = new JLabel("₩");
		label_14.setHorizontalAlignment(SwingConstants.RIGHT);
		label_14.setBounds(460, 327, 42, 15);
		panel.add(label_14);

	}

//	btnPayment 결제
//  btnCofirm 확인
//	btnReservationCancel 예약취소
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == btnPayment)
			{	
			Payment = new D_Payment();
			Payment.setBounds(0, 0, 1042, 611);
//				panel.removeAll();
				
				
			Utility.changePanel(panel, Payment);
					
			}
		else if(e.getSource() == btnConsent)
		{
//			System.exit(0);
			
			panelMainUI = new MainUI();
			panelMainUI.setBounds(0, 0, 1042, 611);
						
			Utility.changePanel(panel, panelMainUI);
		
		}
		else if(e.getSource() == btnReservationCancel)
		{
			TicketsReservation = new TicketsReservation();
			TicketsReservation.setBounds(0,0,1042,611);
			
			Utility.changePanel(panel, TicketsReservation);
		}
		
			
		
	}
}
