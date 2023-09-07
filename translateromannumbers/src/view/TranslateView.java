package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


import service.TranslateService;

public class TranslateView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel jPanelTitle;
	private Font fonttitle;
	private Font font;
	private Font numberFont;

	private JLabel jLabelTitle;
	private JPanel jPanelprogram;
	private JLabel jPanelNotification;

	private JLabel jLabelValue;
	private JLabel jLabelResult;
	private JTextField jTextFieldValue;
	private JLabel result;

	private TranslateService translateService;
	
	private Border panelBorder = BorderFactory.createEmptyBorder(20, 20, 20, 20);
	private Border bluedLineBorder = BorderFactory.createLineBorder(Color.blue);
	private Border redLineBorder = BorderFactory.createLineBorder(Color.red);
	


	public TranslateView() {
		super();
		translateService = new TranslateService();
		this.init();
		this.setVisible(true);
	}

	public void init() {
		this.setTitle("Convert roman numbers");
		this.setSize(600, 250);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(new BorderLayout(10, 10));

		fonttitle = new Font("Aria", Font.BOLD, 25);
		font = new Font("Aria", Font.BOLD, 18);
		numberFont = new Font("Aria", Font.BOLD, 25);

		jPanelTitle = new JPanel(); 

		jLabelTitle = new JLabel("Convert Roman Numbers");
		jLabelTitle.setFont(fonttitle);
		jPanelTitle.add(jLabelTitle);

		jPanelprogram = new JPanel(); 

		jLabelValue = new JLabel("Enter roman number");
		jLabelValue.setFont(font);
		jLabelResult = new JLabel("Result");
		jLabelResult.setFont(font);


		jTextFieldValue = new JTextField();
		jTextFieldValue.setFont(numberFont);
		jTextFieldValue.setBorder(bluedLineBorder);
		jTextFieldValue.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				if(jTextFieldValue.getText().length() >= 50) {
					e.consume();
				}
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				
			}
		});
		jTextFieldValue.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				convertData();
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				convertData();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {

			}
		});

		result = new JLabel();
		result.setFont(numberFont);
		result.setForeground(Color.red);
		result.setBorder(redLineBorder);

		jPanelprogram.setLayout(new GridLayout(2, 2, 10, 10));
		jPanelprogram.setBorder(panelBorder);
		jPanelprogram.add(jLabelValue);
		jPanelprogram.add(jTextFieldValue);
		jPanelprogram.add(jLabelResult);
		jPanelprogram.add(result);

		jPanelNotification = new JLabel("Ký tự nhập sai. Vui lòng nhập các ký tự 'IVXLCDM'", SwingConstants.CENTER);
		jPanelNotification.setFont(font);
		jPanelNotification.setForeground(Color.red);
		jPanelNotification.setVisible(false);

		this.add(jPanelTitle, BorderLayout.NORTH);
		this.add(jPanelprogram, BorderLayout.CENTER);
		this.add(jPanelNotification, BorderLayout.SOUTH);

	}

	public void convertData() {
		String data = jTextFieldValue.getText();
		if (data == null || data.equals("")) {
			showMessage(false);
			result.setText("");
			return;
		}
		boolean isValid = isStringOnlyRomanCharacter(data);
		if (!isValid) {
			showMessage(true);
			return;
		}
		showMessage(false);

		try {
			int resultData = translateService.convertRomanToInt(data);
			result.setText(String.valueOf(resultData));
		} catch (Exception e) {
			e.printStackTrace();
			showMessage(true);
		}

	}

	//hàm kiểm tra chuỗi ký tự số la mã
	private boolean isStringOnlyRomanCharacter(String str) {
		return ((str != null) && (!str.equals("")) && (str.matches("^[IVXLCDM]*$")));
		//^[IVXLCDM]*: dòng text phải khớp với điểm bắt đầu và ở cuối dòng, xuất hiện 0 hoặc nhiều lần và kiểm tra kết thúc dòng
	}

	private void showMessage(boolean isVisible) {
		jPanelNotification.setVisible(isVisible);
	}
	
	
	public static void main(String[] args) {
		new TranslateView();
	}

	
}

