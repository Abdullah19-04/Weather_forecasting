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

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
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

public class file_Read_three_hour extends JFrame {

	private JPanel contentPane;
	private JTextField cityname;
	private JTextField textField;
	private JComboBox comboBox;
	private String selection = "";
	int selection_1;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField txtFeelsLike;
	private JTextField textField_3;
	private JTextField txtWindGust;
	private JTextField textField_4;
	private JTextField txtHumidity;
	private JTextField textField_6;
	private JTextField txtPrecipitation;
	private JTextField textField_5;
	private JTextField txtWindSpeed;
	private JTextField textField_7;
	private JTextField txtTemperature;
	private JTextField textField_8;
	private JTextField txtVisibility;
	private JTextField textField_10;
	private JTextField txtWeatherType;
	private JTextField textField_9;
	private JTextField Time;
	private JTextField txtTimeOfData;
	private JTextField winddirectun;
	private JTextField textField_13;
	private JTextField txtFeelsLikeTemperature;
	private JTextField textField_12;
	private JTextField txtWindGust_1;
	private JTextField textField_11;
	private JTextField txtHumidity_1;
	private JTextField textField_15;
	private JTextField txtPrecipitation_1;
	private JTextField textField_16;
	private JTextField txtWindSpeed_1;
	private JTextField textField_14;
	private JTextField txtTemperature_1;
	private JTextField textField_18;
	private JTextField txtVisibility_1;
	private JTextField textField_19;
	private JTextField txtWeatherType_1;
	private JTextField textField_17;
	private JButton btnNewButton_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					file_Read_three_hour frame = new file_Read_three_hour();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public file_Read_three_hour() throws FileNotFoundException, ParseException {

		String inline = "";
		String day_sequence = "";
		int variable;

		FileReader file2 = new FileReader("D:\\eclipse java projects\\weather_application_Final\\weather_application_Final\\txt\\File_reader.txt");
		Scanner scan = new Scanner(file2);
		String address = scan.next();
		int days = scan.nextInt();
		while (scan.hasNext()) {
			day_sequence += scan.next();
			day_sequence += " ";

		}
		scan.close();

		FileReader file = new FileReader(address);

		Scanner scanner = new Scanner(file);

		// Write all the JSON data into a string using a scanner
		while (scanner.hasNext()) {
			inline += scanner.nextLine();
		}

		JSONParser parser = new JSONParser();
		JSONObject data_obj = (JSONObject) parser.parse(inline);

		// Get the required object from the above created object
		JSONObject siteRep = (JSONObject) data_obj.get("SiteRep");
		JSONObject DV = (JSONObject) siteRep.get("DV");
		JSONObject location = (JSONObject) DV.get("Location");

		String city = (String) location.get("name");

		JSONArray peri = (JSONArray) location.get("Period");

		JSONObject j0 = (JSONObject) peri.get(days);

		JSONArray rep0 = (JSONArray) j0.get("Rep");

		int samp = rep0.size();

		String sample = String.valueOf((samp));

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		cityname = new JTextField();
		cityname.setText(city);
		cityname.setEditable(false);
		cityname.setHorizontalAlignment(SwingConstants.CENTER);
		cityname.setFont(new Font("Tahoma", Font.BOLD, 20));
		cityname.setBounds(442, 10, 434, 47);
		contentPane.add(cityname);
		cityname.setColumns(10);

		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField.setText(sample);
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setEditable(false);
		textField.setBounds(249, 8, 52, 56);
		contentPane.add(textField);
		textField.setColumns(10);

		comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 16));

		comboBox.setBounds(465, 74, 215, 61);
		contentPane.add(comboBox);

		if (samp == 0) {
			comboBox.setModel(new DefaultComboBoxModel(new String[] { "0" }));
		} else if (samp == 1) {
			comboBox.setModel(new DefaultComboBoxModel(new String[] { "0", "1" }));
		} else if (samp == 2) {
			comboBox.setModel(new DefaultComboBoxModel(new String[] { "0", "1", "2" }));
		} else if (samp == 3) {
			comboBox.setModel(new DefaultComboBoxModel(new String[] { "0", "1", "2", "3" }));
		} else if (samp == 4) {
			comboBox.setModel(new DefaultComboBoxModel(new String[] { "0", "1", "2", "3", "4" }));
		} else if (samp == 5) {
			comboBox.setModel(new DefaultComboBoxModel(new String[] { "0", "1", "2", "3", "4", "5" }));
		} else if (samp == 6) {
			comboBox.setModel(new DefaultComboBoxModel(new String[] { "0", "1", "2", "3", "4", "5", "6" }));
		} else if (samp == 7) {
			comboBox.setModel(new DefaultComboBoxModel(new String[] { "0", "1", "2", "3", "4", "5", "6", "7" }));
		} else if (samp == 8) {
			comboBox.setModel(new DefaultComboBoxModel(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8" }));
		} else if (samp == 9) {
			comboBox.setModel(
					new DefaultComboBoxModel(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" }));
		}

		JButton btnNewButton = new JButton("Select");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				selection = comboBox.getSelectedItem().toString();
				selection_1 = Integer.parseInt(selection);

				JSONObject day = (JSONObject) rep0.get(selection_1);

				String wind_Direction = (String) day.get("D");
				String feels_temp = (String) day.get("F");
				String wind_Gust = (String) day.get("G");
				String humidity = (String) day.get("H");
				String precipitation = (String) day.get("Pp");
				String wind_speed = (String) day.get("S");
				String temperature = (String) day.get("T");
				String visibility = (String) day.get("V");
				String weather_Type = (String) day.get("W");
				String UV = (String) day.get("U");
				String time = (String) day.get("$");

				int time1 = Integer.valueOf(time);
				int time2;

				if (time1 >= 60) {

					time2 = time1 / 60;
				} else {
					time2 = time1;
				}
				String time3 = String.valueOf(time2);

				winddirectun = new JTextField("Wind Direction");
				winddirectun.setHorizontalAlignment(SwingConstants.CENTER);
				winddirectun.setFont(new Font("Tahoma", Font.PLAIN, 15));
				winddirectun.setColumns(10);
				winddirectun.setBounds(460, 195, 188, 42);
				contentPane.add(winddirectun);

				textField_13 = new JTextField(wind_Direction + " mph");
				textField_13.setHorizontalAlignment(SwingConstants.CENTER);
				textField_13.setFont(new Font("Tahoma", Font.BOLD, 16));
				textField_13.setEditable(false);
				textField_13.setColumns(10);
				textField_13.setBounds(658, 195, 156, 42);
				contentPane.add(textField_13);

				txtFeelsLikeTemperature = new JTextField("Feels Like Temperature");
				txtFeelsLikeTemperature.setHorizontalAlignment(SwingConstants.CENTER);
				txtFeelsLikeTemperature.setFont(new Font("Tahoma", Font.PLAIN, 15));
				txtFeelsLikeTemperature.setColumns(10);
				txtFeelsLikeTemperature.setBounds(460, 240, 188, 42);
				contentPane.add(txtFeelsLikeTemperature);

				textField_12 = new JTextField(feels_temp +" °C");
				textField_12.setHorizontalAlignment(SwingConstants.CENTER);
				textField_12.setFont(new Font("Tahoma", Font.BOLD, 16));
				textField_12.setEditable(false);
				textField_12.setColumns(10);
				textField_12.setBounds(658, 240, 156, 42);
				contentPane.add(textField_12);

				txtWindGust_1 = new JTextField("Wind Gust");
				txtWindGust_1.setHorizontalAlignment(SwingConstants.CENTER);
				txtWindGust_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
				txtWindGust_1.setColumns(10);
				txtWindGust_1.setBounds(460, 285, 188, 42);
				contentPane.add(txtWindGust_1);

				textField_11 = new JTextField(wind_Gust +" mph");
				textField_11.setHorizontalAlignment(SwingConstants.CENTER);
				textField_11.setFont(new Font("Tahoma", Font.BOLD, 16));
				textField_11.setEditable(false);
				textField_11.setColumns(10);
				textField_11.setBounds(658, 285, 156, 42);
				contentPane.add(textField_11);

				txtHumidity_1 = new JTextField("Humidity");
				txtHumidity_1.setHorizontalAlignment(SwingConstants.CENTER);
				txtHumidity_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
				txtHumidity_1.setColumns(10);
				txtHumidity_1.setBounds(460, 330, 188, 41);
				contentPane.add(txtHumidity_1);

				textField_15 = new JTextField(humidity +" %");
				textField_15.setHorizontalAlignment(SwingConstants.CENTER);
				textField_15.setFont(new Font("Tahoma", Font.BOLD, 16));
				textField_15.setEditable(false);
				textField_15.setColumns(10);
				textField_15.setBounds(658, 330, 156, 42);
				contentPane.add(textField_15);

				txtPrecipitation_1 = new JTextField("Precipitation");
				txtPrecipitation_1.setHorizontalAlignment(SwingConstants.CENTER);
				txtPrecipitation_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
				txtPrecipitation_1.setColumns(10);
				txtPrecipitation_1.setBounds(460, 375, 188, 41);
				contentPane.add(txtPrecipitation_1);

				textField_16 = new JTextField(precipitation +" %");
				textField_16.setHorizontalAlignment(SwingConstants.CENTER);
				textField_16.setFont(new Font("Tahoma", Font.BOLD, 16));
				textField_16.setEditable(false);
				textField_16.setColumns(10);
				textField_16.setBounds(658, 375, 156, 42);
				contentPane.add(textField_16);

				txtWindSpeed_1 = new JTextField("Wind Speed");
				txtWindSpeed_1.setHorizontalAlignment(SwingConstants.CENTER);
				txtWindSpeed_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
				txtWindSpeed_1.setColumns(10);
				txtWindSpeed_1.setBounds(460, 420, 188, 41);
				contentPane.add(txtWindSpeed_1);

				textField_14 = new JTextField(wind_speed +" mile/hour");
				textField_14.setHorizontalAlignment(SwingConstants.CENTER);
				textField_14.setFont(new Font("Tahoma", Font.BOLD, 16));
				textField_14.setEditable(false);
				textField_14.setColumns(10);
				textField_14.setBounds(658, 420, 156, 42);
				contentPane.add(textField_14);

				txtTemperature_1 = new JTextField("Temperature");
				txtTemperature_1.setHorizontalAlignment(SwingConstants.CENTER);
				txtTemperature_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
				txtTemperature_1.setColumns(10);
				txtTemperature_1.setBounds(460, 465, 188, 41);
				contentPane.add(txtTemperature_1);

				textField_18 = new JTextField(temperature +" °C");
				textField_18.setHorizontalAlignment(SwingConstants.CENTER);
				textField_18.setFont(new Font("Tahoma", Font.BOLD, 16));
				textField_18.setEditable(false);
				textField_18.setColumns(10);
				textField_18.setBounds(658, 465, 156, 42);
				contentPane.add(textField_18);

				txtVisibility_1 = new JTextField("Visibility");
				txtVisibility_1.setHorizontalAlignment(SwingConstants.CENTER);
				txtVisibility_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
				txtVisibility_1.setColumns(10);
				txtVisibility_1.setBounds(460, 510, 188, 41);
				contentPane.add(txtVisibility_1);

				textField_19 = new JTextField(visibility);
				textField_19.setHorizontalAlignment(SwingConstants.CENTER);
				textField_19.setFont(new Font("Tahoma", Font.BOLD, 16));
				textField_19.setEditable(false);
				textField_19.setColumns(10);
				textField_19.setBounds(658, 510, 156, 42);
				contentPane.add(textField_19);

				txtWeatherType_1 = new JTextField("Weather Type");
				txtWeatherType_1.setHorizontalAlignment(SwingConstants.CENTER);
				txtWeatherType_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
				txtWeatherType_1.setColumns(10);
				txtWeatherType_1.setBounds(460, 555, 188, 41);
				contentPane.add(txtWeatherType_1);

				textField_17 = new JTextField(weather_Type);
				textField_17.setHorizontalAlignment(SwingConstants.CENTER);
				textField_17.setFont(new Font("Tahoma", Font.BOLD, 16));
				textField_17.setEditable(false);
				textField_17.setColumns(10);
				textField_17.setBounds(658, 555, 156, 42);
				contentPane.add(textField_17);

				txtTimeOfData = new JTextField("Time");
				txtTimeOfData.setHorizontalAlignment(SwingConstants.CENTER);
				txtTimeOfData.setFont(new Font("Tahoma", Font.PLAIN, 15));
				txtTimeOfData.setColumns(10);
				txtTimeOfData.setBounds(460, 600, 188, 41);
				contentPane.add(txtTimeOfData);

				Time = new JTextField();
				Time.setHorizontalAlignment(SwingConstants.CENTER);
				Time.setFont(new Font("Tahoma", Font.BOLD, 16));
				Time.setEditable(false);
				Time.setText(time3 + " : 00");
				Time.setBounds(658, 600, 156, 42);
				contentPane.add(Time);
				Time.setColumns(10);

			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(712, 79, 132, 50);
		contentPane.add(btnNewButton);

		btnNewButton_1 = new JButton("Back");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				read_File rf = new read_File();
				rf.setVisible(true);
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton_1.setBounds(10, 592, 172, 63);
		contentPane.add(btnNewButton_1);

		JLabel lblNewLabel_4 = new JLabel("Current Available Duration");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_4.setBounds(10, 10, 215, 56);
		contentPane.add(lblNewLabel_4);

		JButton btnNewButton_11 = new JButton("Live Weather Forecasting");
		btnNewButton_11.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_11.addActionListener(new ActionListener() {
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
		btnNewButton_11.setBounds(10, 234, 228, 72);
		contentPane.add(btnNewButton_11);

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
