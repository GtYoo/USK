package usk;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import dto.LoginDTO;
import util.Common;
import util.Utility;

public class MainController implements ActionListener{

	private JFrame frame;
	private JButton btnLogo;
	private JButton btnTicket;
	private JButton btnAttraction;
	private JButton btnAdi;
	private JButton btnHotel;
	private JPanel panelCommon;
	private JPanel panelMain1;
	private JButton btnSchdul;
	private JButton btnMap;
	private JButton btnJoinMembership;
	private JButton btnLogin;
	private JPanel panelMain2;
	private JPanel panelMain3;
	private JButton btnLogout;
	private JButton btnAdminLogout;
	private JMenuBar mnLogin;
	private JMenu mnMenu;
	private JMenuItem mnData;
	private JMenuItem mnMyResev;
	private JMenuBar mnAdminLogin;
	private JMenu mnAdminMenu;
	private JMenuItem mnAdminData;
	private JMenuItem mnAdminSearch;
	private JMenuItem mnAdminMenuHand;

	private JLabel lblMSG;
	private JMenuItem mnWd;
	private JLabel lblMainImage1;
	private JLabel lblMainImage3;
	private JLabel lblMainImage2;
	private JLabel lblMainImage4;
	private JPanel panelUpCommon;
	
	private LoginDTO loginDto;
	
	private MainUI panelMainUI;
	private MemberSearchUI panelMemberSearchUI;
	private MenuSearchUI panelMenuSearchUI;
	private HotelUI panelHotelUI;
	private TicketUI panelTicketUI;
	private AttractionUI panelAttractionUI;
	private MapUI panelMapUI;
	private AdiUI panelAdiUI;
	private DataSearchUI panelDataSearchUI;
	private SchdulUI panelSchdulUI;
	
//	private JInternalFrame iAdmin;
	ImageIcon loginIm = new ImageIcon("img/mainImage/btnLogin.png");
	ImageIcon logoutIm = new ImageIcon("img/mainImage/btnLogout.png");
	ImageIcon mberIm = new ImageIcon("img/mainImage/btnMber.png");
	
	private JLabel lblLeftBar;
	private JLabel lblNewLabel;

	
	


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainController window = new MainController();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainController() {
		initialize();
//		mainUI();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 1366, 768);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("U n i v e r s a l   S t u d i o s   K o r e a");
		Common.getHm().put("MAIN_FRAME", frame);
		
		ImageIcon ticket = new ImageIcon("img/mainImage/ticket.png");
		ImageIcon ticketOver = new ImageIcon("img/mainImage/ticketover.png");
		btnTicket = new JButton(ticket);
		btnTicket.setBounds(85, 190, 126, 54);
		frame.getContentPane().add(btnTicket);
		btnTicket.addActionListener(this);
		btnTicket.setBorderPainted(false);
		btnTicket.setFocusPainted(false);
		btnTicket.setContentAreaFilled(false);
		btnTicket.setRolloverIcon(ticketOver);
		
		ImageIcon attraction = new ImageIcon("img/mainImage/attraction.png");
		ImageIcon attractionOver = new ImageIcon("img/mainImage/attractionover.png");
		btnAttraction = new JButton(attraction);
		btnAttraction.setBounds(85, 272, 126, 54);
		frame.getContentPane().add(btnAttraction);
		btnAttraction.addActionListener(this);
		btnAttraction.setBorderPainted(false);
		btnAttraction.setFocusPainted(false);
		btnAttraction.setContentAreaFilled(false);
		btnAttraction.setRolloverIcon(attractionOver);
		
		ImageIcon adi = new ImageIcon("img/mainImage/adi.png");
		ImageIcon adiOver = new ImageIcon("img/mainImage/adiover.png");
		btnAdi = new JButton(adi);
		btnAdi.setBounds(85, 352, 126, 54);
		frame.getContentPane().add(btnAdi);
		btnAdi.addActionListener(this);
		btnAdi.setBorderPainted(false);
		btnAdi.setFocusPainted(false);
		btnAdi.setContentAreaFilled(false);
		btnAdi.setRolloverIcon(adiOver);
		
		ImageIcon hotel = new ImageIcon("img/mainImage/hotel.png");
		ImageIcon hotelOver = new ImageIcon("img/mainImage/hotelover.png");
		btnHotel = new JButton(hotel);
		btnHotel.setBounds(85, 432, 126, 54);
		frame.getContentPane().add(btnHotel);
		btnHotel.addActionListener(this);
		btnHotel.setBorderPainted(false);
		btnHotel.setFocusPainted(false);
		btnHotel.setContentAreaFilled(false);
		btnHotel.setRolloverIcon(hotelOver);
		
		ImageIcon schdul = new ImageIcon("img/mainImage/btnSchdul.png");
		btnSchdul = new JButton(schdul);
		btnSchdul.setBounds(1171, 62, 145, 40);
		frame.getContentPane().add(btnSchdul);
		btnSchdul.addActionListener(this);
		
		ImageIcon map = new ImageIcon("img/mainImage/btnMap.png");
		btnMap = new JButton(map);
		btnMap.setBounds(1008, 63, 145, 40);
		frame.getContentPane().add(btnMap);
		btnMap.addActionListener(this);
		
		
		
		panelCommon = new MainUI();
//		panelCommon = new JPanel();
		panelCommon.setBackground(Color.WHITE);
		panelCommon.setBounds(296, 108, 1042, 611);
		frame.getContentPane().add(panelCommon);

		Common.getHm().put("PANELUP_COMMON", panelUpCommon);
		panelUpCommon = new JPanel();
		panelUpCommon.setBackground(Color.WHITE);
		panelUpCommon.setLayout(null);
		panelUpCommon.setBounds(764, 14, 573, 43);
		frame.getContentPane().add(panelUpCommon);
		
		Common.getHm().put("PANELMAIN1", panelMain1);
		panelMain1 = new JPanel();
		panelMain1.setBackground(Color.WHITE);
		panelMain1.setBounds(0, 0, 573, 43);
		panelUpCommon.add(panelMain1);
		panelMain1.setLayout(null);
		
		btnJoinMembership = new JButton(mberIm);
		btnJoinMembership.setBounds(431, 8, 97, 23);
		panelMain1.add(btnJoinMembership);
		btnJoinMembership.addActionListener(this);
		
	
		btnLogin = new JButton(loginIm);
		btnLogin.setBounds(308, 8, 97, 23);
		panelMain1.add(btnLogin);
		btnLogin.addActionListener(this);
		
		ImageIcon logo = new ImageIcon("img/mainImage/logo.png");
		btnLogo = new JButton(logo);
		btnLogo.setBounds(85, 37, 136, 64);
		frame.getContentPane().add(btnLogo);
		btnLogo.setBorderPainted(false);
		btnLogo.setFocusPainted(false);
		btnLogo.setContentAreaFilled(false);
		
		ImageIcon lefrBar = new ImageIcon("img/mainImage/leftbar.png");
		lblLeftBar = new JLabel("New label");
		lblLeftBar.setBounds(0, 0, 290, 729);
		frame.getContentPane().add(lblLeftBar);
		btnLogo.addActionListener(this);
		lblLeftBar.setIcon(lefrBar);
		
		ImageIcon welcome = new ImageIcon("img/mainImage/welcome.png");
		lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(351, 14, 464, 88);
		frame.getContentPane().add(lblNewLabel);
		lblNewLabel.setIcon(welcome);
		
		frame.setLocationRelativeTo(null);
		
		//*	???????????? ???????????? 	*//
		Common.getHm().put("PANEL_COMMON", panelCommon);
		

		panelMainUI = new MainUI();
		panelMainUI.setBounds(0, 0, 1042, 611);
		panelMemberSearchUI = new MemberSearchUI();
		panelMemberSearchUI.setBounds(0, 0, 1042, 611);
		panelMenuSearchUI = new MenuSearchUI();
		panelMenuSearchUI.setBounds(0, 0, 1042, 611);
		panelDataSearchUI = new DataSearchUI();
		panelDataSearchUI.setBounds(0, 0, 1042, 611);
		panelMapUI = new MapUI();
		panelMapUI.setBounds(0, 0, 1042, 611);
		panelHotelUI = new HotelUI();
		panelHotelUI.setBounds(0, 0, 1042, 611);
		panelTicketUI = new TicketUI();
		panelTicketUI.setBounds(0, 0, 1042, 611);
		panelAttractionUI = new AttractionUI();
		panelAttractionUI.setBounds(0, 0, 1042, 611);
		panelSchdulUI = new SchdulUI();
		panelSchdulUI.setBounds(0, 0, 1042, 611);
		panelAdiUI = new AdiUI();
		panelAdiUI.setBounds(0, 0, 1042, 611);
		
		LoginDTO loginDTO = new LoginDTO();
		loginDTO.setLevel(0);
		loginDTO.setLoginID("visitor");
		Common.getHm().put("LoginDTO", loginDTO);
	
	}
	
	//* ?????????????????? ????????? ????????????UI *//
	public void inLogin() {
		
		Common.getHm().put("PANELMAIN2", panelMain2);
		panelMain2 = new JPanel();
		panelMain2.setBounds(764, 14, 573, 43);
		panelMain2.setBackground(Color.WHITE);
//		panelUpCommon.add(panelMain2);
		frame.getContentPane().add(panelMain2);
		panelMain2.setLayout(null);
		
		btnLogout = new JButton(logoutIm);
		btnLogout.setBounds(431, 8, 97, 23);
		panelMain2.add(btnLogout);
		btnLogout.addActionListener(this);
		
		mnLogin = new JMenuBar();
		mnLogin.setBounds(320, 9, 97, 23);
		panelMain2.add(mnLogin);
		
		mnMenu = new JMenu("??????");
		mnLogin.add(mnMenu);
		
		mnMyResev = new JMenuItem("?????????");
		mnMenu.add(mnMyResev);
		mnMyResev.addActionListener(this);
		
		mnData = new JMenuItem("????????????");
		mnMenu.add(mnData);
		mnData.addActionListener(this);
		
		mnWd = new JMenuItem("????????????");
		mnMenu.add(mnWd);
		mnWd.addActionListener(this);
		
		lblMSG = new JLabel("???????????????.");
		lblMSG.setBounds(210, 8, 97, 23);
		panelMain2.add(lblMSG);
	}
	
	//* ?????????????????? ????????? ?????????UI *//
	public void adminLogin() {
		
		panelMain3 = new JPanel();
		panelMain3.setBounds(764, 14, 573, 43);
		panelMain3.setBackground(Color.WHITE);
//		panelUpCommon.add(panelMain2);
		frame.getContentPane().add(panelMain3);
		panelMain3.setLayout(null);
		
		btnAdminLogout = new JButton(logoutIm);
		btnAdminLogout.setBounds(431, 8, 97, 23);
		panelMain3.add(btnAdminLogout);
		btnAdminLogout.addActionListener(this);
		
		mnAdminLogin = new JMenuBar();
		mnAdminLogin.setBounds(320, 9, 97, 23);
		panelMain3.add(mnAdminLogin);
		
		mnAdminMenu = new JMenu("??????");
		mnAdminLogin.add(mnAdminMenu);
		
		mnAdminData = new JMenuItem("???????????????");
		mnAdminMenu.add(mnAdminData);
		mnAdminData.addActionListener(this);
		
		mnAdminSearch = new JMenuItem("????????????");
		mnAdminMenu.add(mnAdminSearch);
		mnAdminSearch.addActionListener(this);
		
		mnAdminMenuHand = new JMenuItem("???????????????");
		mnAdminMenu.add(mnAdminMenuHand);
		mnAdminMenuHand.addActionListener(this);	
		
		lblMSG = new JLabel("???????????????.");
		lblMSG.setBounds(210, 8, 97, 23);
		panelMain3.add(lblMSG);
		
//		iAdmin = new AdminHand();
//		iAdmin.setBounds(305, 570, 1030, 150);
//		frame.getContentPane().add(iAdmin);
//		iAdmin.setVisible(true);
		
	}
	
	
	//* ????????????????????? ??????UI *//
	public void mainUI() {
		
		ImageIcon mainImage1 = new ImageIcon("img/mainImage/nomalImage1.png");
		lblMainImage1 = new JLabel("???????????????1");
		lblMainImage1.setBounds(58, 36, 453, 250);
		panelCommon.add(lblMainImage1);
		lblMainImage1.setIcon(mainImage1);
		
		ImageIcon mainImage2 = new ImageIcon("img/mainImage/nomalImage2.png");
		lblMainImage2 = new JLabel("???????????????2");
		lblMainImage2.setBounds(536, 36, 453, 250);
		panelCommon.add(lblMainImage2);
		lblMainImage2.setIcon(mainImage2);
		
		ImageIcon mainImage3 = new ImageIcon("img/mainImage/nomalImage3.png");
		lblMainImage3 = new JLabel("???????????????3");
		lblMainImage3.setBounds(58, 314, 453, 250);
		panelCommon.add(lblMainImage3);
		lblMainImage3.setIcon(mainImage3);
		
		ImageIcon mainImage4 = new ImageIcon("img/mainImage/nomalImage4.png");
		lblMainImage4 = new JLabel("???????????????4");
		lblMainImage4.setBounds(536, 314, 453, 250);
		panelCommon.add(lblMainImage4);
		lblMainImage4.setIcon(mainImage4);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
			
		if(e.getSource() == btnLogin)			//???????????????
		{
//			Utility.changePanel(panelUpCommon, panelMain2);
			loginDto = new LoginDTO(); //?????????
			HashMap<String , Object> hm = Common.getHm();
			hm.put("LoginDTO", loginDto);

			
			new LoginPopup();	// ????????? ???????????????
			
//			JOptionPane.showMessageDialog(null, loginDto.getLoginID() + loginDto.getLevel());
			
			if(loginDto.getLevel() == 2)
			{
				JOptionPane.showMessageDialog(null, loginDto.getLoginID() + loginDto.getLevel()+ " " + "???????????????. ????????????!");
				panelUpCommon.setVisible(false); //???????????? ?????????
				adminLogin();					 //??????????????? ??????
//				ifAdminHand.setVisible(true);
//				iAdmin.setVisible(true);
//				JFrame iFrame = new JFrame();
//				iFrame.getContentPane().add(iAdmin);
//				iFrame.setAlwaysOnTop(true);
//				new AdminHand();
			}
			else if(loginDto.getLevel() == 1)	
			{
				JOptionPane.showMessageDialog(frame, "???????????????. ???????????????!");
				panelUpCommon.setVisible(false); //???????????? ?????????
				inLogin();						 //??????????????? ??????	
			}
			else if(loginDto.getLevel() == 0)    //???????????? ???????????? ????????? ??????????????? ???????????? ??????
			{
//				JOptionPane.showMessageDialog(frame, "???????????? ??????????????? ??????????????????.");
//				return;
			}
		}
		else if(e.getSource() == btnLogout)				//??????????????????
		{
			JOptionPane.showMessageDialog(frame, "???????????????????????????.");
			panelMain2.setVisible(false);
			panelUpCommon.setVisible(true);
			Utility.changePanel(panelCommon, panelMainUI);
		}
		else if(e.getSource() == btnAdminLogout)		//???????????????????????????
		{
//			Utility.changePanel(panelUpCommon, panelMain1);
			JOptionPane.showMessageDialog(frame, "???????????????????????????.");
			panelMain3.setVisible(false);
			panelUpCommon.setVisible(true);
			Utility.changePanel(panelCommon, panelMainUI);
//			mainUI();
		}
		else if(e.getSource() == btnJoinMembership)		//??????????????????
		{
			new MberJoinPopup();			//??????????????????
		}
		else if(e.getSource() == btnSchdul)				//???????????????
		{
			Utility.changePanel(panelCommon, panelSchdulUI);
		}
		else if(e.getSource() == btnMap)				//?????????
		{
			Utility.changePanel(panelCommon, panelMapUI);
//			JOptionPane.showMessageDialog(frame, "????????????");
		}
		else if(e.getSource() == btnHotel)				//????????????
		{
//			panelHotelUI = new HotelUI();
//			panelHotelUI.setBounds(0, 0, 1042, 611);
//			Common.getHm().put("HotelUI", panelHotelUI);
//			Common.getHm().get("HotelUI");
			Utility.changePanel(panelCommon, panelHotelUI);
		}
		else if(e.getSource() == btnLogo)				//????????????
		{
			Utility.changePanel(panelCommon, panelMainUI);
			
		}
		else if(e.getSource() == btnTicket)				//????????????
		{
//			panelTicketUI = new TicketUI();
//			panelTicketUI.setBounds(0, 0, 1042, 611);
			Utility.changePanel(panelCommon, panelTicketUI);
		}
		else if(e.getSource() == btnAttraction)			//??????????????????
		{
//			panelAttractionUI = new AttractionUI();
//			panelAttractionUI.setBounds(0, 0, 1042, 611);
			Utility.changePanel(panelCommon, panelAttractionUI);
		}
		else if(e.getSource() == btnAdi)				//??????????????????
		{
//			panelAdiUI = new AdiUI();
//			panelAdiUI.setBounds(0, 0, 1042, 611);
			Utility.changePanel(panelCommon, panelAdiUI);
		}
		else if(e.getSource() == mnAdminData)			//????????? ??????????????? ??????
		{

			Utility.changePanel(panelCommon, panelDataSearchUI);
		}
		else if(e.getSource() == mnAdminSearch)			//????????? ???????????? ??????
		{

			Utility.changePanel(panelCommon, panelMemberSearchUI);
		}
		else if(e.getSource() == mnAdminMenuHand)		//????????? ??????
		{

			Utility.changePanel(panelCommon, panelMenuSearchUI);
		}
		else if(e.getSource() == mnMyResev)				//?????? ?????????
		{

			
			ReservationConfirmationList reservationConfirmationList = new ReservationConfirmationList();
			Utility.changePanel(panelCommon, reservationConfirmationList);
		}
		else if(e.getSource() == mnData)				//?????? ????????????
		{

			new MberModify();
		}
		else if(e.getSource() == mnWd)					//?????? ????????????
		{
			JOptionPane.showMessageDialog(frame, "??????!");
			new MberModify();
		}
	}
}
