
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.JLabel;
import java.awt.Font;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import java.awt.Color;
import javax.swing.JButton;

public class Three_display extends JFrame {

	static String id;
	static String[][] value = new String[50][50];
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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Three_display frame = new Three_display();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws IOException
	 * @throws ParseException
	 */
	public Three_display() throws IOException, ParseException {

		String day_sequence = "";
		int variable;

		FileReader file2 = new FileReader(
				"D:\\eclipse java projects\\weather_application_Final\\weather_application_Final\\txt\\Temp_location.txt");
		Scanner scan = new Scanner(file2);
		String city = scan.next();
		int day_number = scan.nextInt();

		scan.close();

		FileReader file = new FileReader(
				"D:\\eclipse java projects\\weather_application_Final\\weather_application_Final\\txt\\location.txt");
		Scanner read = new Scanner(file);
		int i, j; // declaring variables to be used in program.

		for (i = 0; i <= 47; i++) { // Using for-loop to store file content into array sequentially.
			for (j = 0; j <= 1; j++) {
				value[i][j] = read.next();

			}
		}

		for (i = 0; i <= 47; i++) { // Using for-loop to store file content into array sequentially.
			for (j = 0; j <= 1; j++) {
				if (value[i][1].contains(city)) {
					id = value[i][j];
					break;

				}
			}
		}

		String inline = "";
		URL url = new URL("http://datapoint.metoffice.gov.uk/public/data/val/wxfcs/all/json/" + id
				+ "?res=3hourly&key=API address here");

		HttpURLConnection conn;

		conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.connect();

		int responsecode = conn.getResponseCode();

		if (responsecode != 200) {
			throw new RuntimeException("HttpResponseCode: " + responsecode);
		} else {
			Scanner scanner = new Scanner(url.openStream());

			while (scanner.hasNext()) {
				inline += scanner.nextLine();
			}

			JSONParser parser = new JSONParser();
			JSONObject data_obj = (JSONObject) parser.parse(inline);

			// Get the required object from the above created object
			JSONObject siteRep = (JSONObject) data_obj.get("SiteRep");
			JSONObject DV = (JSONObject) siteRep.get("DV");
			JSONObject location = (JSONObject) DV.get("Location");

			JSONArray peri = (JSONArray) location.get("Period");
			JSONObject j0 = (JSONObject) peri.get(day_number);

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
			cityname.setFont(new Font("Times New Roman", Font.BOLD, 18));
			cityname.setBounds(460, 10, 385, 72);
			contentPane.add(cityname);
			cityname.setColumns(10);

			textField = new JTextField();
			textField.setFont(new Font("Tahoma", Font.PLAIN, 18));
			textField.setText(sample);
			textField.setHorizontalAlignment(SwingConstants.CENTER);
			textField.setEditable(false);
			textField.setBounds(235, 10, 52, 56);
			contentPane.add(textField);
			textField.setColumns(10);

			JLabel lblNewLabel = new JLabel("Current Available Duration");
			lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblNewLabel.setBounds(10, 10, 215, 56);
			contentPane.add(lblNewLabel);

			comboBox = new JComboBox();
			comboBox.setFont(new Font("Tahoma", Font.PLAIN, 16));

			comboBox.setBounds(460, 114, 215, 61);
			contentPane.add(comboBox);

			String one = "";

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
				comboBox.setModel(
						new DefaultComboBoxModel(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8" }));
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

					textField_1 = new JTextField("Wind Direction");
					textField_1.setHorizontalAlignment(SwingConstants.CENTER);
					textField_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
					textField_1.setBounds(460, 195, 188, 42);
					contentPane.add(textField_1);
					textField_1.setColumns(10);

					textField_2 = new JTextField(wind_Direction + " mph");
					textField_2.setEditable(false);
					textField_2.setFont(new Font("Tahoma", Font.BOLD, 16));
					textField_2.setHorizontalAlignment(SwingConstants.CENTER);
					textField_2.setColumns(10);
					textField_2.setBounds(658, 195, 156, 42);
					contentPane.add(textField_2);

					txtFeelsLike = new JTextField("Feels Like Temperature");
					txtFeelsLike.setHorizontalAlignment(SwingConstants.CENTER);
					txtFeelsLike.setFont(new Font("Tahoma", Font.PLAIN, 15));
					txtFeelsLike.setColumns(10);
					txtFeelsLike.setBounds(460, 240, 188, 42);
					contentPane.add(txtFeelsLike);

					textField_3 = new JTextField(feels_temp + " °C");
					textField_3.setEditable(false);
					textField_3.setHorizontalAlignment(SwingConstants.CENTER);
					textField_3.setFont(new Font("Tahoma", Font.BOLD, 16));
					textField_3.setColumns(10);
					textField_3.setBounds(658, 240, 156, 42);
					contentPane.add(textField_3);

					txtWindGust = new JTextField("Wind Gust");
					txtWindGust.setHorizontalAlignment(SwingConstants.CENTER);
					txtWindGust.setFont(new Font("Tahoma", Font.PLAIN, 15));
					txtWindGust.setColumns(10);
					txtWindGust.setBounds(460, 285, 188, 42);
					contentPane.add(txtWindGust);

					textField_4 = new JTextField(wind_Gust + " mph");
					textField_4.setHorizontalAlignment(SwingConstants.CENTER);
					textField_4.setFont(new Font("Tahoma", Font.BOLD, 16));
					textField_4.setEditable(false);
					textField_4.setColumns(10);
					textField_4.setBounds(658, 285, 156, 42);
					contentPane.add(textField_4);

					txtHumidity = new JTextField("humidity");
					txtHumidity.setHorizontalAlignment(SwingConstants.CENTER);
					txtHumidity.setFont(new Font("Tahoma", Font.PLAIN, 15));
					txtHumidity.setColumns(10);
					txtHumidity.setBounds(460, 330, 188, 41);
					contentPane.add(txtHumidity);

					textField_6 = new JTextField(humidity + " %");
					textField_6.setHorizontalAlignment(SwingConstants.CENTER);
					textField_6.setFont(new Font("Tahoma", Font.BOLD, 16));
					textField_6.setEditable(false);
					textField_6.setColumns(10);
					textField_6.setBounds(658, 330, 156, 42);
					contentPane.add(textField_6);

					txtPrecipitation = new JTextField("Precipitation");
					txtPrecipitation.setHorizontalAlignment(SwingConstants.CENTER);
					txtPrecipitation.setFont(new Font("Tahoma", Font.PLAIN, 15));
					txtPrecipitation.setColumns(10);
					txtPrecipitation.setBounds(460, 375, 188, 41);
					contentPane.add(txtPrecipitation);

					textField_5 = new JTextField(precipitation + " %");
					textField_5.setHorizontalAlignment(SwingConstants.CENTER);
					textField_5.setFont(new Font("Tahoma", Font.BOLD, 16));
					textField_5.setEditable(false);
					textField_5.setColumns(10);
					textField_5.setBounds(658, 375, 156, 42);
					contentPane.add(textField_5);

					txtWindSpeed = new JTextField("Wind Speed");
					txtWindSpeed.setHorizontalAlignment(SwingConstants.CENTER);
					txtWindSpeed.setFont(new Font("Tahoma", Font.PLAIN, 15));
					txtWindSpeed.setColumns(10);
					txtWindSpeed.setBounds(460, 420, 188, 41);
					contentPane.add(txtWindSpeed);

					textField_7 = new JTextField(wind_speed + " mile/hour");
					textField_7.setHorizontalAlignment(SwingConstants.CENTER);
					textField_7.setFont(new Font("Tahoma", Font.BOLD, 16));
					textField_7.setEditable(false);
					textField_7.setColumns(10);
					textField_7.setBounds(658, 420, 156, 42);
					contentPane.add(textField_7);

					txtTemperature = new JTextField("Temperature");
					txtTemperature.setHorizontalAlignment(SwingConstants.CENTER);
					txtTemperature.setFont(new Font("Tahoma", Font.PLAIN, 15));
					txtTemperature.setColumns(10);
					txtTemperature.setBounds(460, 465, 188, 41);
					contentPane.add(txtTemperature);

					textField_8 = new JTextField(temperature + " °C");
					textField_8.setHorizontalAlignment(SwingConstants.CENTER);
					textField_8.setFont(new Font("Tahoma", Font.BOLD, 16));
					textField_8.setEditable(false);
					textField_8.setColumns(10);
					textField_8.setBounds(658, 465, 156, 42);
					contentPane.add(textField_8);

					txtVisibility = new JTextField("Visibility");
					txtVisibility.setHorizontalAlignment(SwingConstants.CENTER);
					txtVisibility.setFont(new Font("Tahoma", Font.PLAIN, 15));
					txtVisibility.setColumns(10);
					txtVisibility.setBounds(460, 510, 188, 41);
					contentPane.add(txtVisibility);

					textField_10 = new JTextField(visibility);
					textField_10.setHorizontalAlignment(SwingConstants.CENTER);
					textField_10.setFont(new Font("Tahoma", Font.BOLD, 16));
					textField_10.setEditable(false);
					textField_10.setColumns(10);
					textField_10.setBounds(658, 510, 156, 42);
					contentPane.add(textField_10);

					txtWeatherType = new JTextField("Weather Type");
					txtWeatherType.setHorizontalAlignment(SwingConstants.CENTER);
					txtWeatherType.setFont(new Font("Tahoma", Font.PLAIN, 15));
					txtWeatherType.setColumns(10);
					txtWeatherType.setBounds(460, 555, 188, 41);
					contentPane.add(txtWeatherType);

					textField_9 = new JTextField(weather_Type);
					textField_9.setHorizontalAlignment(SwingConstants.CENTER);
					textField_9.setFont(new Font("Tahoma", Font.BOLD, 16));
					textField_9.setEditable(false);
					textField_9.setColumns(10);
					textField_9.setBounds(658, 555, 156, 42);
					contentPane.add(textField_9);

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
			btnNewButton.setForeground(new Color(0, 0, 0));
			btnNewButton.setBounds(696, 119, 132, 50);
			contentPane.add(btnNewButton);

			JButton btnNewButton_1 = new JButton("Back");
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
					three_Hour th = new three_Hour();
					th.setVisible(true);
				}
			});
			btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
			btnNewButton_1.setBounds(10, 592, 172, 63);
			contentPane.add(btnNewButton_1);

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
			lblNewLabel1.setIcon(new ImageIcon(
					"D:\\eclipse java projects\\weather_application_Final\\weather_application_Final\\img\\final.jpg"));
			lblNewLabel1.setBounds(0, 0, 900, 700);
			contentPane.add(lblNewLabel1);

		}
	}
}
