
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

public class N_display extends JFrame {

	private JPanel contentPane;
	static String id;
	static String[][] value = new String[50][50];
	private JTextField cityname;
	private JTextField whichday;
	private JTextField txtWindDirection;
	private JTextField txtWindGust;
	private JTextField txtHumidity;
	private JTextField txtPrecipitation;
	private JTextField txtSpeed;
	private JTextField txtVisibility;
	private JTextField addWinddirection;
	private JTextField addWindgust;
	private JTextField addhumidity;
	private JTextField addprecipitation;
	private JTextField addspeed;
	private JTextField addvisibility;
	private JTextField addmaxtemperature;
	private JTextField addfeelslike;
	private JTextField addweathertype;
	private JTextField addmaxultravoilet;
	private JTextField txtMax;
	private JTextField txtFeelsLike;
	private JTextField txtWeatherType;
	private JTextField txtMaxUltraVoilet;
	private JTextField txtAverageDayTime;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					N_display frame = new N_display();
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
	public N_display() throws IOException, ParseException {

		String day_sequence = "";
		int variable;

		FileReader file2 = new FileReader(
				"D:\\eclipse java projects\\weather_application_Final\\weather_application_Final\\txt\\Temp_location.txt");
		Scanner scan = new Scanner(file2);
		String city = scan.next();
		variable = scan.nextInt();
		while (scan.hasNext()) {
			day_sequence += scan.next();
			day_sequence += " ";

		}
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
				+ "?res=daily&key=API address here");

		HttpURLConnection conn;
		conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.connect();

		int responsecode = conn.getResponseCode();

		if (responsecode != 200) {
			throw new RuntimeException("HttpResponseCode: " + responsecode);
		} else {
			Scanner scanner = new Scanner(url.openStream());

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

			String cityname = (String) location.get("name");
			JSONArray peri = (JSONArray) location.get("Period");
			JSONObject j0 = (JSONObject) peri.get(variable);
			JSONArray rep0 = (JSONArray) j0.get("Rep");
			JSONObject day = (JSONObject) rep0.get(1);

			String day_Wind = (String) day.get("D"); // Wind Direction
			String day_Gust = (String) day.get("Gm"); // Wind Gust Noon
			String day_Humidity = (String) day.get("Hm"); // Screen Relative Humidity Noon
			String day_Precipitation = (String) day.get("PPn"); // Precipitation Probability Day
			String day_Speed = (String) day.get("S"); // Wind Speed in mile/hour
			String day_Visibility = (String) day.get("V"); // Visibility
			String day_Max_Temp = (String) day.get("Nm"); // Day Maximum Temperature
			String day_Feels = (String) day.get("FNm"); // Feels Like Day Maximum Temperature
			String day_Type = (String) day.get("W");

			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 900, 700);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);

			JTextField textField = new JTextField(cityname);
			textField.setEditable(false);
			textField.setHorizontalAlignment(SwingConstants.CENTER);
			textField.setFont(new Font("Times New Roman", Font.BOLD, 15));
			textField.setBounds(460, 10, 385, 72);
			contentPane.add(textField);
			textField.setColumns(10);

			txtWindDirection = new JTextField();
			txtWindDirection.setText("Wind Direction");
			txtWindDirection.setHorizontalAlignment(SwingConstants.CENTER);
			txtWindDirection.setFont(new Font("Tahoma", Font.PLAIN, 15));
			txtWindDirection.setBounds(460, 110, 203, 50);
			contentPane.add(txtWindDirection);
			txtWindDirection.setColumns(10);

			addWinddirection = new JTextField(day_Wind);
			addWinddirection.setEditable(false);
			addWinddirection.setFont(new Font("Tahoma", Font.PLAIN, 15));
			addWinddirection.setHorizontalAlignment(SwingConstants.CENTER);
			addWinddirection.setBounds(673, 109, 172, 50);
			contentPane.add(addWinddirection);
			addWinddirection.setColumns(10);

			txtWindGust = new JTextField();
			txtWindGust.setText("Wind Gust");
			txtWindGust.setHorizontalAlignment(SwingConstants.CENTER);
			txtWindGust.setFont(new Font("Tahoma", Font.PLAIN, 15));
			txtWindGust.setColumns(10);
			txtWindGust.setBounds(460, 165, 203, 50);
			contentPane.add(txtWindGust);

			addWindgust = new JTextField(day_Gust + " mph");
			addWindgust.setEditable(false);
			addWindgust.setHorizontalAlignment(SwingConstants.CENTER);
			addWindgust.setFont(new Font("Tahoma", Font.PLAIN, 15));
			addWindgust.setColumns(10);
			addWindgust.setBounds(673, 164, 172, 50);
			contentPane.add(addWindgust);

			txtHumidity = new JTextField();
			txtHumidity.setText("Humidity");
			txtHumidity.setHorizontalAlignment(SwingConstants.CENTER);
			txtHumidity.setFont(new Font("Tahoma", Font.PLAIN, 15));
			txtHumidity.setColumns(10);
			txtHumidity.setBounds(460, 220, 203, 50);
			contentPane.add(txtHumidity);

			addhumidity = new JTextField(day_Humidity + " %");
			addhumidity.setEditable(false);
			addhumidity.setHorizontalAlignment(SwingConstants.CENTER);
			addhumidity.setFont(new Font("Tahoma", Font.PLAIN, 15));
			addhumidity.setColumns(10);
			addhumidity.setBounds(673, 219, 172, 50);
			contentPane.add(addhumidity);

			txtPrecipitation = new JTextField();
			txtPrecipitation.setText("Precipitation");
			txtPrecipitation.setHorizontalAlignment(SwingConstants.CENTER);
			txtPrecipitation.setFont(new Font("Tahoma", Font.PLAIN, 15));
			txtPrecipitation.setColumns(10);
			txtPrecipitation.setBounds(460, 275, 203, 50);
			contentPane.add(txtPrecipitation);

			addprecipitation = new JTextField(day_Precipitation + " %");
			addprecipitation.setEditable(false);
			addprecipitation.setHorizontalAlignment(SwingConstants.CENTER);
			addprecipitation.setFont(new Font("Tahoma", Font.PLAIN, 15));
			addprecipitation.setColumns(10);
			addprecipitation.setBounds(673, 274, 172, 50);
			contentPane.add(addprecipitation);

			txtSpeed = new JTextField();
			txtSpeed.setText("Speed");
			txtSpeed.setHorizontalAlignment(SwingConstants.CENTER);
			txtSpeed.setFont(new Font("Tahoma", Font.PLAIN, 15));
			txtSpeed.setColumns(10);
			txtSpeed.setBounds(460, 330, 203, 50);
			contentPane.add(txtSpeed);

			addspeed = new JTextField(day_Speed + " mile/hour");
			addspeed.setEditable(false);
			addspeed.setHorizontalAlignment(SwingConstants.CENTER);
			addspeed.setFont(new Font("Tahoma", Font.PLAIN, 15));
			addspeed.setColumns(10);
			addspeed.setBounds(673, 329, 172, 50);
			contentPane.add(addspeed);

			txtVisibility = new JTextField();
			txtVisibility.setForeground(new Color(0, 0, 0));
			txtVisibility.setText("Visibility");
			txtVisibility.setHorizontalAlignment(SwingConstants.CENTER);
			txtVisibility.setFont(new Font("Tahoma", Font.PLAIN, 15));
			txtVisibility.setColumns(10);
			txtVisibility.setBounds(460, 385, 203, 50);
			contentPane.add(txtVisibility);

			addvisibility = new JTextField(day_Visibility);
			addvisibility.setEditable(false);
			addvisibility.setHorizontalAlignment(SwingConstants.CENTER);
			addvisibility.setFont(new Font("Tahoma", Font.PLAIN, 15));
			addvisibility.setColumns(10);
			addvisibility.setBounds(673, 384, 172, 50);
			contentPane.add(addvisibility);

			txtMax = new JTextField();
			txtMax.setText("Maximum Temperature");
			txtMax.setHorizontalAlignment(SwingConstants.CENTER);
			txtMax.setForeground(Color.BLACK);
			txtMax.setFont(new Font("Tahoma", Font.PLAIN, 15));
			txtMax.setColumns(10);
			txtMax.setBounds(460, 440, 203, 50);
			contentPane.add(txtMax);

			addmaxtemperature = new JTextField(day_Max_Temp + " °C");
			addmaxtemperature.setEditable(false);
			addmaxtemperature.setHorizontalAlignment(SwingConstants.CENTER);
			addmaxtemperature.setFont(new Font("Tahoma", Font.PLAIN, 15));
			addmaxtemperature.setColumns(10);
			addmaxtemperature.setBounds(673, 439, 172, 50);
			contentPane.add(addmaxtemperature);

			txtFeelsLike = new JTextField();
			txtFeelsLike.setText("Feels like");
			txtFeelsLike.setHorizontalAlignment(SwingConstants.CENTER);
			txtFeelsLike.setForeground(Color.BLACK);
			txtFeelsLike.setFont(new Font("Tahoma", Font.PLAIN, 15));
			txtFeelsLike.setColumns(10);
			txtFeelsLike.setBounds(460, 495, 203, 50);
			contentPane.add(txtFeelsLike);

			addfeelslike = new JTextField(day_Feels + " °C");
			addfeelslike.setEditable(false);
			addfeelslike.setHorizontalAlignment(SwingConstants.CENTER);
			addfeelslike.setFont(new Font("Tahoma", Font.PLAIN, 15));
			addfeelslike.setColumns(10);
			addfeelslike.setBounds(673, 494, 172, 50);
			contentPane.add(addfeelslike);

			txtWeatherType = new JTextField();
			txtWeatherType.setText("Weather Type");
			txtWeatherType.setHorizontalAlignment(SwingConstants.CENTER);
			txtWeatherType.setForeground(Color.BLACK);
			txtWeatherType.setFont(new Font("Tahoma", Font.PLAIN, 15));
			txtWeatherType.setColumns(10);
			txtWeatherType.setBounds(460, 550, 203, 50);
			contentPane.add(txtWeatherType);

			addweathertype = new JTextField(day_Type);
			addweathertype.setEditable(false);
			addweathertype.setHorizontalAlignment(SwingConstants.CENTER);
			addweathertype.setFont(new Font("Tahoma", Font.PLAIN, 15));
			addweathertype.setColumns(10);
			addweathertype.setBounds(673, 549, 172, 50);
			contentPane.add(addweathertype);

			txtAverageDayTime = new JTextField("Average Night Time Weather Report");
			txtAverageDayTime.setHorizontalAlignment(SwingConstants.CENTER);
			txtAverageDayTime.setFont(new Font("Tahoma", Font.BOLD, 18));
			txtAverageDayTime.setEditable(false);
			txtAverageDayTime.setColumns(10);
			txtAverageDayTime.setBounds(10, 10, 345, 72);
			contentPane.add(txtAverageDayTime);

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
			btnNewButton_2.setBounds(10, 75, 228, 72);
			contentPane.add(btnNewButton_2);

			JButton btnNewButton_2_1 = new JButton("Download Offline Report");
			btnNewButton_2_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					try {
						Download_Files df = new Download_Files();
						df.setVisible(true);
						dispose();
					} catch (IOException e1) {
						e1.printStackTrace();
					}

				}
			});
			btnNewButton_2_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
			btnNewButton_2_1.setBounds(10, 152, 228, 72);
			contentPane.add(btnNewButton_2_1);

			btnNewButton_1 = new JButton("Back");
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
					Avg_Night an = new Avg_Night();
					an.setVisible(true);
				}
			});
			btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
			btnNewButton_1.setBounds(10, 592, 172, 63);
			contentPane.add(btnNewButton_1);

			JLabel lblNewLabel1 = new JLabel("");
			lblNewLabel1.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel1.setIcon(new ImageIcon(
					"D:\\eclipse java projects\\weather_application_Final\\weather_application_Final\\img\\final.jpg"));
			lblNewLabel1.setBounds(0, 0, 900, 700);
			contentPane.add(lblNewLabel1);

		}
	}
}
