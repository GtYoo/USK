package usk;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;

public class AdminHand extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblCode;
	private JTextField tfCode;
	private JLabel lblName;
	private JTextField tfName;
	private JLabel lblDc;
	private JLabel lblSearch;
	private JTextField tfSearch;
	private JButton btnSearch;
	private JButton btnClear;
	private JTextField tfDc;
	private JButton btnInsert;
	private JButton btnDelete;
	private JButton btnUpdate;
	private JLabel lblImage;
	private JTextField tfImage;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					AdminHand frame = new AdminHand();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public AdminHand() {
		super("관리자 제어창", true, true, true, true);
		
		setBounds(100, 100, 1030, 150);
		getContentPane().setLayout(null);
		setVisible(true);
		lblCode = new JLabel("코드");
		lblCode.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		lblCode.setBounds(191, 57, 52, 15);
		getContentPane().add(lblCode);
		
		tfCode = new JTextField();
		tfCode.setBounds(244, 54, 52, 21);
		getContentPane().add(tfCode);
		tfCode.setColumns(10);
		
		lblName = new JLabel("시설명");
		lblName.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		lblName.setBounds(308, 53, 68, 21);
		getContentPane().add(lblName);
		
		tfName = new JTextField();
		tfName.setColumns(10);
		tfName.setBounds(378, 54, 110, 21);
		getContentPane().add(tfName);
		
		lblDc = new JLabel("내용");
		lblDc.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		lblDc.setBounds(504, 54, 68, 21);
		getContentPane().add(lblDc);
		
		lblSearch = new JLabel("조회");
		lblSearch.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		lblSearch.setBounds(44, 12, 52, 18);
		getContentPane().add(lblSearch);
		
		tfSearch = new JTextField();
		tfSearch.setColumns(10);
		tfSearch.setBounds(97, 11, 82, 21);
		getContentPane().add(tfSearch);
		
		btnSearch = new JButton("조회");
		btnSearch.setBounds(191, 10, 97, 23);
		getContentPane().add(btnSearch);
		
		btnClear = new JButton("초기화");
		btnClear.setBounds(297, 10, 97, 23);
		getContentPane().add(btnClear);
		
		tfDc = new JTextField();
		tfDc.setBounds(555, 54, 375, 56);
		getContentPane().add(tfDc);
		tfDc.setColumns(10);
		
		btnInsert = new JButton("등록");
		btnInsert.setBounds(676, 10, 97, 23);
		getContentPane().add(btnInsert);
		
		btnDelete = new JButton("삭제");
		btnDelete.setBounds(782, 10, 97, 23);
		getContentPane().add(btnDelete);
		
		btnUpdate = new JButton("수정");
		btnUpdate.setBounds(888, 10, 97, 23);
		getContentPane().add(btnUpdate);
		
		lblImage = new JLabel("이미지");
		lblImage.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		lblImage.setBounds(308, 88, 68, 21);
		getContentPane().add(lblImage);
		
		tfImage = new JTextField();
		tfImage.setColumns(10);
		tfImage.setBounds(378, 89, 110, 21);
		getContentPane().add(tfImage);
		
		

	}
}
