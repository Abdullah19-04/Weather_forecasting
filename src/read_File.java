import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.json.simple.parser.ParseException;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

public class read_File extends JFrame {

	public static String address = "";
	private JPanel contentPane;
	private JTextField textField;
	public static String selection = "";
	public static String selection_1 = "";
	public static int number_1;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					read_File frame = new read_File();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public read_File() {

		Calendar c = Calendar.getInstance();
		Date today = c.getTime();

		// Tomorrow
		c.add(Calendar.DATE, 1);
		c.add(Calendar.MONDAY, 0);
		c.add(Calendar.YEAR, 0);
		Date tomorrow = c.getTime();

		// Day after Tomorrow
		c.add(Calendar.DATE, 3 / 2);
		c.add(Calendar.MONDAY, 0);
		c.add(Calendar.YEAR, 0);
		Date day_after_tomorrow = c.getTime();

		SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");

		String TOday = dateformat.format(today);
		String ToMorrow = dateformat.format(tomorrow);
		String after_tomorrow = dateformat.format(day_after_tomorrow);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Select the day");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(620, 130, 180, 31);
		contentPane.add(lblNewLabel_1);

		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] { TOday, ToMorrow, after_tomorrow }));
		comboBox_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		comboBox_1.setBounds(620, 180, 202, 49);
		contentPane.add(comboBox_1);

		JLabel lblNewLabel = new JLabel("Select the file type");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(291, 121, 213, 49);
		contentPane.add(lblNewLabel);

		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 20));
		comboBox.setModel(
				new DefaultComboBoxModel(new String[] { "Average Day", "Average Night", "3 Hour forecasting" }));
		comboBox.setBounds(291, 180, 202, 49);
		contentPane.add(comboBox);

		JLabel pasteaddress = new JLabel("Copy and paste address of the file");
		pasteaddress.setFont(new Font("Arial", Font.PLAIN, 20));
		pasteaddress.setHorizontalAlignment(SwingConstants.LEFT);
		pasteaddress.setBounds(339, 305, 340, 31);
		contentPane.add(pasteaddress);

		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.LEFT);
		textField.setBounds(335, 364, 328, 39);
		contentPane.add(textField);
		textField.setColumns(10);

		JButton btnNewButton = new JButton("Open File");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				selection = comboBox.getSelectedItem().toString();
				selection_1 = comboBox_1.getSelectedItem().toString();

				if (selection_1.equals(TOday)) {

					number_1 = 0;
				} else if (selection_1.equals(ToMorrow)) {

					number_1 = 1;
				} else
					number_1 = 2;

				address = textField.getText();

				try {
					FileWriter file = new FileWriter("D:\\eclipse java projects\\weather_application_Final\\weather_application_Final\\txt\\File_reader.txt");
					file.write(address + "\n" + number_1 + "\n" + selection);
					file.close();
				} catch (IOException e1) {

					e1.printStackTrace();
				}

				if (selection.equals("Average Day")) {

					dispose();
					file_Read_Day frd;
					try {
						frd = new file_Read_Day();
						frd.setVisible(true);

					} catch (FileNotFoundException | ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				} else if (selection.equals("Average Night")) {
					file_Read_Night frn;
					dispose();
					try {
						frn = new file_Read_Night();
						frn.setVisible(true);

					} catch (FileNotFoundException | ParseException e1) {
						e1.printStackTrace();
					}
				} else {
					file_Read_three_hour frth;
					dispose();
					try {
						try {
							frth = new file_Read_three_hour();
							frth.setVisible(true);
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnNewButton.setBounds(621, 594, 215, 61);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Defaultpage1 df;
				try {
					df = new Defaultpage1();
					df.setVisible(true);

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_1.setBounds(10, 592, 172, 63);
		contentPane.add(btnNewButton_1);

		JButton btnNewButton_1111 = new JButton("Live Weather Forecasting");
		btnNewButton_1111.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_1111.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JButton btnNewButton_1_1 = new JButton("Average Day");
				btnNewButton_1_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						Avg_Day ad = new Avg_Day();
						ad.setVisible(true);
						dispose();

					}
				});
				btnNewButton_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
				btnNewButton_1_1.setBounds(10, 298, 141, 50);
				contentPane.add(btnNewButton_1_1);

				JButton btnNewButton_1_2 = new JButton("Average Night");
				btnNewButton_1_2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Avg_Night an = new Avg_Night();
						an.setVisible(true);
						dispose();

					}
				});
				btnNewButton_1_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
				btnNewButton_1_2.setBounds(10, 346, 141, 50);
				contentPane.add(btnNewButton_1_2);

				JButton btnNewButton_1_3 = new JButton("3 Hour Interval");
				btnNewButton_1_3.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						three_Hour th = new three_Hour();
						th.setVisible(true);
						dispose();

					}
				});
				btnNewButton_1_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
				btnNewButton_1_3.setBounds(10, 396, 156, 50);
				contentPane.add(btnNewButton_1_3);

			}
		});
		btnNewButton_1111.setBounds(10, 234, 228, 72);
		contentPane.add(btnNewButton_1111);

		JButton btnNewButton_2 = new JButton("Read Offline Report");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				read_File rf = new read_File();
				rf.setVisible(true);
				dispose();

			}
		});

		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_2.setBounds(10, 68, 228, 72);
		contentPane.add(btnNewButton_2);

		JButton btnNewButton_2_1 = new JButton("Download Offline Report");
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Download_Files df;
				try {
					df = new Download_Files();
					df.setVisible(true);
					dispose();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnNewButton_2_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_2_1.setBounds(10, 152, 228, 72);
		contentPane.add(btnNewButton_2_1);		
		
		JLabel lblNewLabel1 = new JLabel("");
		lblNewLabel1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel1.setIcon(new ImageIcon("D:\\eclipse java projects\\weather_application_Final\\weather_application_Final\\img\\final.jpg"));
		lblNewLabel1.setBounds(0, 0, 900, 700);
		contentPane.add(lblNewLabel1);

	}
}
