import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class file_Read_Night extends JFrame {

	private JPanel contentPane;
	private JTextField txtAsasas;
	private JTextField winddirection;
	private JTextField WINDDIRECTION;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField SPEED;
	private JTextField txtSpeed;
	private JTextField txtVisibility;
	private JTextField VISIBILITY;
	private JTextField feelslike;
	private JTextField FEELSLIKE;
	private JTextField weathertype;
	private JTextField WEATHERTYPE;
	private JTextField uv;
	private JTextField textField_8;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					file_Read_Night frame = new file_Read_Night();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public file_Read_Night() throws FileNotFoundException, ParseException {
		setTitle("Weather Forecasting Application");

		String inline = "";
		String day_sequence = "";
		int number;
		FileReader file2 = new FileReader("D:\\eclipse java projects\\weather_application_Final\\weather_application_Final\\txt\\File_reader.txt");
		Scanner scan = new Scanner(file2);
		String day_types = scan.next();
		number = scan.nextInt();
		while (scan.hasNext()) {
			day_sequence += scan.next();
			day_sequence += " ";
		}

		FileReader file = new FileReader(day_types);

		Scanner scanner = new Scanner(file);
		while (scanner.hasNext()) {
			inline += scanner.nextLine();
		}
		JSONParser parser = new JSONParser();
		JSONObject data_obj = (JSONObject) parser.parse(inline);

		JSONObject siteRep = (JSONObject) data_obj.get("SiteRep");
		JSONObject DV = (JSONObject) siteRep.get("DV");
		JSONObject location = (JSONObject) DV.get("Location");
		String ID = (String) location.get("i");
		String name = (String) location.get("name");
		String country = (String) location.get("country");
		String continent = (String) location.get("continent");
		String eleva = (String) location.get("elevation");

		JSONArray peri = (JSONArray) location.get("Period");
		JSONObject j0 = (JSONObject) peri.get(number);
		JSONArray rep0 = (JSONArray) j0.get("Rep");
		JSONObject day = (JSONObject) rep0.get(1);

		String day_Wind = (String) day.get("D"); 
		String day_Gust = (String) day.get("Gm");
		String day_Humidity = (String) day.get("Hm"); 
		String day_Precipitation = (String) day.get("PPn"); 
		String day_Speed = (String) day.get("S"); 
		String day_Visibility = (String) day.get("V"); 
		String day_Max_Temp = (String) day.get("Nm"); 
		String day_Feels = (String) day.get("FNm"); 
		String day_Type = (String) day.get("W"); 

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		txtAsasas = new JTextField(name);
		txtAsasas.setForeground(Color.WHITE);
		txtAsasas.setBackground(Color.LIGHT_GRAY);
		txtAsasas.setHorizontalAlignment(SwingConstants.CENTER);
		txtAsasas.setFont(new Font("Times New Roman", Font.BOLD, 15));
		txtAsasas.setEditable(false);
		txtAsasas.setBounds(460, 10, 385, 65);
		contentPane.add(txtAsasas);
		txtAsasas.setColumns(10);

		winddirection = new JTextField("Wind Direction");
		winddirection.setBounds(460, 110, 203, 50);
		winddirection.setHorizontalAlignment(SwingConstants.CENTER);
		winddirection.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(winddirection);
		winddirection.setColumns(10);

		WINDDIRECTION = new JTextField(day_Wind);
		WINDDIRECTION.setEditable(false);
		WINDDIRECTION.setBounds(673, 109, 172, 50);
		WINDDIRECTION.setHorizontalAlignment(SwingConstants.CENTER);
		WINDDIRECTION.setFont(new Font("Tahoma", Font.BOLD, 16));
		WINDDIRECTION.setColumns(10);
		contentPane.add(WINDDIRECTION);

		textField = new JTextField("Wind Gust");
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField.setColumns(10);
		textField.setBounds(460, 165, 203, 50);
		contentPane.add(textField);

		textField_1 = new JTextField(day_Gust + " mph");
		textField_1.setEditable(false);
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		textField_1.setColumns(10);
		textField_1.setBounds(673, 164, 172, 50);
		contentPane.add(textField_1);

		textField_2 = new JTextField("Humidity");
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_2.setColumns(10);
		textField_2.setBounds(460, 220, 203, 50);
		contentPane.add(textField_2);

		textField_3 = new JTextField(day_Humidity  +" %");
		textField_3.setEditable(false);
		textField_3.setHorizontalAlignment(SwingConstants.CENTER);
		textField_3.setFont(new Font("Tahoma", Font.BOLD, 16));
		textField_3.setColumns(10);
		textField_3.setBounds(673, 219, 172, 50);
		contentPane.add(textField_3);

		textField_4 = new JTextField("Precipiation");
		textField_4.setHorizontalAlignment(SwingConstants.CENTER);
		textField_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_4.setColumns(10);
		textField_4.setBounds(460, 275, 203, 50);
		contentPane.add(textField_4);

		textField_5 = new JTextField(day_Precipitation +" %");
		textField_5.setEditable(false);
		textField_5.setHorizontalAlignment(SwingConstants.CENTER);
		textField_5.setFont(new Font("Tahoma", Font.BOLD, 16));
		textField_5.setColumns(10);
		textField_5.setBounds(673, 274, 172, 50);
		contentPane.add(textField_5);

		txtSpeed = new JTextField("Speed");
		txtSpeed.setBounds(460, 330, 203, 50);
		txtSpeed.setHorizontalAlignment(SwingConstants.CENTER);
		txtSpeed.setFont(new Font("Arial", Font.PLAIN, 15));
		txtSpeed.setColumns(10);
		contentPane.add(txtSpeed);

		SPEED = new JTextField(day_Speed +" mile/hour");
		SPEED.setEditable(false);
		SPEED.setBounds(673, 329, 172, 50);
		SPEED.setHorizontalAlignment(SwingConstants.CENTER);
		SPEED.setFont(new Font("Tahoma", Font.BOLD, 16));
		SPEED.setColumns(10);
		contentPane.add(SPEED);

		txtVisibility = new JTextField("Visibility");
		txtVisibility.setBounds(460, 385, 203, 50);
		txtVisibility.setHorizontalAlignment(SwingConstants.CENTER);
		txtVisibility.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtVisibility.setColumns(10);
		contentPane.add(txtVisibility);

		VISIBILITY = new JTextField(day_Visibility);
		VISIBILITY.setEditable(false);
		VISIBILITY.setBounds(673, 384, 172, 50);
		VISIBILITY.setHorizontalAlignment(SwingConstants.CENTER);
		VISIBILITY.setFont(new Font("Tahoma", Font.BOLD, 16));
		VISIBILITY.setColumns(10);
		contentPane.add(VISIBILITY);

		textField_6 = new JTextField("Maximum Temperature");
		textField_6.setBounds(460, 440, 203, 50);
		textField_6.setHorizontalAlignment(SwingConstants.CENTER);
		textField_6.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_6.setColumns(10);
		contentPane.add(textField_6);

		textField_7 = new JTextField(day_Max_Temp +" °C"); 
		textField_7.setEditable(false);
		textField_7.setBounds(673, 439, 172, 50);
		textField_7.setHorizontalAlignment(SwingConstants.CENTER);
		textField_7.setFont(new Font("Tahoma", Font.BOLD, 16));
		textField_7.setColumns(10);
		contentPane.add(textField_7);

		feelslike = new JTextField("Feels like");
		feelslike.setBounds(460, 495, 203, 50);
		feelslike.setHorizontalAlignment(SwingConstants.CENTER);
		feelslike.setFont(new Font("Tahoma", Font.PLAIN, 15));
		feelslike.setColumns(10);
		contentPane.add(feelslike);

		FEELSLIKE = new JTextField(day_Feels +" °C");
		FEELSLIKE.setEditable(false);
		FEELSLIKE.setBounds(673, 494, 172, 50);
		FEELSLIKE.setHorizontalAlignment(SwingConstants.CENTER);
		FEELSLIKE.setFont(new Font("Tahoma", Font.BOLD, 16));
		FEELSLIKE.setColumns(10);
		contentPane.add(FEELSLIKE);

		weathertype = new JTextField("Weather Type");
		weathertype.setBounds(460, 550, 203, 50);
		weathertype.setHorizontalAlignment(SwingConstants.CENTER);
		weathertype.setFont(new Font("Tahoma", Font.PLAIN, 15));
		weathertype.setColumns(10);
		contentPane.add(weathertype);

		WEATHERTYPE = new JTextField(day_Type);
		WEATHERTYPE.setEditable(false);
		WEATHERTYPE.setBounds(673, 549, 172, 50);
		WEATHERTYPE.setHorizontalAlignment(SwingConstants.CENTER);
		WEATHERTYPE.setFont(new Font("Tahoma", Font.BOLD, 16));
		WEATHERTYPE.setColumns(10);
		contentPane.add(WEATHERTYPE);

		JButton btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				read_File rf = new read_File();
				rf.setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton.setBounds(10, 592, 172, 63);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Live Weather Forecasting");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_1.addActionListener(new ActionListener() {
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
		btnNewButton_1.setBounds(10, 234, 228, 72);
		contentPane.add(btnNewButton_1);

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
