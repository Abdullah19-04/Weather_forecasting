import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import java.net.URL;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;

public class D_display extends JFrame {

	static String id;
	static String[][] value = new String[50][50];
	private JPanel contentPane;
	private JTextField textField;
	static String city;
	private JTextField textField_1;
	private JTextField txtAverageDayTime;
	private JTextField winddirection;
	private JTextField WINDDIRECTION;
	private JTextField txtWindGust;
	private JTextField WINDGUST;
	private JTextField txtHumidity;
	private JTextField textField_3;
	private JTextField txtPrecipiation;
	private JTextField PRECIPITATION;
	private JTextField txtSpeed;
	private JTextField SPEED;
	private JTextField txtVisibility;
	private JTextField VISIBILITY;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField feelslike;
	private JTextField FEELSLIKE;
	private JTextField weathertype;
	private JTextField WEATHERTYPE;
	private JTextField uv;
	private JTextField UV;
	private JButton btnNewButton_1;

	public static void main(String[] args) throws FileNotFoundException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					D_display frame = new D_display();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public D_display() throws IOException, ParseException {

		String day_sequence = "";
		int variable;

		FileReader file2 = new FileReader(
				"D:\\eclipse java projects\\weather_application_Final\\weather_application_Final\\txt\\Temp_location.txt");
		Scanner scan = new Scanner(file2);
		String city = scan.next();
		variable = scan.nextInt();

		scan.close();

		FileReader file = new FileReader(
				"D:\\eclipse java projects\\weather_application_Final\\weather_application_Final\\txt\\location.txt");
		Scanner read = new Scanner(file);
		int i, j;

		for (i = 0; i <= 47; i++) {
			for (j = 0; j <= 1; j++) {
				value[i][j] = read.next();
			}
		}
		for (i = 0; i <= 47; i++) {
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

			while (scanner.hasNext()) {
				inline += scanner.nextLine();
			}

			JSONParser parser = new JSONParser();
			JSONObject data_obj = (JSONObject) parser.parse(inline);
			JSONObject siteRep = (JSONObject) data_obj.get("SiteRep");
			JSONObject DV = (JSONObject) siteRep.get("DV");
			JSONObject location = (JSONObject) DV.get("Location");

			String cityname = (String) location.get("name");
			JSONArray peri = (JSONArray) location.get("Period");

			JSONObject j0 = (JSONObject) peri.get(variable);

			JSONArray rep0 = (JSONArray) j0.get("Rep");
			JSONObject day = (JSONObject) rep0.get(0);

			String day_Wind = (String) day.get("D");
			String day_Gust = (String) day.get("Gn");
			String day_Humidity = (String) day.get("Hn");
			String day_Precipitation = (String) day.get("PPd");
			String day_Speed = (String) day.get("S");
			String day_Visibility = (String) day.get("V");
			String day_Max_Temp = (String) day.get("Dm");
			String day_Feels = (String) day.get("FDm");
			String day_Type = (String) day.get("W");
			String day_UV = (String) day.get("U");
			String day_night = (String) day.get("$");

			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 900, 700);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);

			textField = new JTextField(cityname);
			textField.setEditable(false);
			textField.setHorizontalAlignment(SwingConstants.CENTER);
			textField.setFont(new Font("Times New Roman", Font.BOLD, 15));
			textField.setBounds(460, 10, 385, 72);
			contentPane.add(textField);
			textField.setColumns(10);

			txtAverageDayTime = new JTextField("Average Day Time Weather Report");
			txtAverageDayTime.setEditable(false);
			txtAverageDayTime.setHorizontalAlignment(SwingConstants.CENTER);
			txtAverageDayTime.setFont(new Font("Tahoma", Font.BOLD, 18));
			txtAverageDayTime.setColumns(10);
			txtAverageDayTime.setBounds(10, 10, 335, 72);
			contentPane.add(txtAverageDayTime);

			winddirection = new JTextField("Wind Direction");
			winddirection.setHorizontalAlignment(SwingConstants.CENTER);
			winddirection.setBounds(460, 110, 203, 50);
			winddirection.setFont(new Font("Tahoma", Font.PLAIN, 15));
			contentPane.add(winddirection);
			winddirection.setColumns(10);

			WINDDIRECTION = new JTextField(day_Wind);
			WINDDIRECTION.setEditable(false);
			WINDDIRECTION.setHorizontalAlignment(SwingConstants.CENTER);
			WINDDIRECTION.setFont(new Font("Tahoma", Font.BOLD, 16));
			WINDDIRECTION.setColumns(10);
			WINDDIRECTION.setBounds(673, 109, 172, 50);
			contentPane.add(WINDDIRECTION);

			txtWindGust = new JTextField("Wind Gust");
			txtWindGust.setHorizontalAlignment(SwingConstants.CENTER);
			txtWindGust.setFont(new Font("Tahoma", Font.PLAIN, 15));
			txtWindGust.setColumns(10);
			txtWindGust.setBounds(460, 165, 203, 50);
			contentPane.add(txtWindGust);

			WINDGUST = new JTextField(day_Gust + " mph");
			WINDGUST.setEditable(false);
			WINDGUST.setHorizontalAlignment(SwingConstants.CENTER);
			WINDGUST.setFont(new Font("Tahoma", Font.BOLD, 16));
			WINDGUST.setColumns(10);
			WINDGUST.setBounds(673, 164, 172, 50);
			contentPane.add(WINDGUST);

			txtHumidity = new JTextField("Humidity");
			txtHumidity.setHorizontalAlignment(SwingConstants.CENTER);
			txtHumidity.setFont(new Font("Arial", Font.PLAIN, 15));
			txtHumidity.setColumns(10);
			txtHumidity.setBounds(460, 220, 203, 50);
			contentPane.add(txtHumidity);

			textField_3 = new JTextField(day_Humidity + " %");
			textField_3.setEditable(false);
			textField_3.setHorizontalAlignment(SwingConstants.CENTER);
			textField_3.setFont(new Font("Tahoma", Font.BOLD, 16));
			textField_3.setColumns(10);
			textField_3.setBounds(673, 219, 172, 50);
			contentPane.add(textField_3);

			txtPrecipiation = new JTextField("Precipiation");
			txtPrecipiation.setHorizontalAlignment(SwingConstants.CENTER);
			txtPrecipiation.setFont(new Font("Arial", Font.PLAIN, 15));
			txtPrecipiation.setColumns(10);
			txtPrecipiation.setBounds(460, 275, 203, 50);
			contentPane.add(txtPrecipiation);

			PRECIPITATION = new JTextField(day_Precipitation + " %");
			PRECIPITATION.setEditable(false);
			PRECIPITATION.setHorizontalAlignment(SwingConstants.CENTER);
			PRECIPITATION.setFont(new Font("Tahoma", Font.BOLD, 16));
			PRECIPITATION.setColumns(10);
			PRECIPITATION.setBounds(673, 274, 172, 50);
			contentPane.add(PRECIPITATION);

			txtSpeed = new JTextField("Speed");
			txtSpeed.setHorizontalAlignment(SwingConstants.CENTER);
			txtSpeed.setFont(new Font("Arial", Font.PLAIN, 15));
			txtSpeed.setColumns(10);
			txtSpeed.setBounds(460, 330, 203, 50);
			contentPane.add(txtSpeed);

			SPEED = new JTextField(day_Speed + " mile/hour");
			SPEED.setEditable(false);
			SPEED.setHorizontalAlignment(SwingConstants.CENTER);
			SPEED.setFont(new Font("Tahoma", Font.BOLD, 16));
			SPEED.setColumns(10);
			SPEED.setBounds(673, 329, 172, 50);
			contentPane.add(SPEED);

			txtVisibility = new JTextField("Visibility");
			txtVisibility.setHorizontalAlignment(SwingConstants.CENTER);
			txtVisibility.setFont(new Font("Tahoma", Font.PLAIN, 15));
			txtVisibility.setColumns(10);
			txtVisibility.setBounds(460, 385, 203, 50);
			contentPane.add(txtVisibility);

			VISIBILITY = new JTextField(day_Visibility);
			VISIBILITY.setEditable(false);
			VISIBILITY.setHorizontalAlignment(SwingConstants.CENTER);
			VISIBILITY.setFont(new Font("Tahoma", Font.BOLD, 16));
			VISIBILITY.setColumns(10);
			VISIBILITY.setBounds(673, 384, 172, 50);
			contentPane.add(VISIBILITY);

			textField_4 = new JTextField("Maximum Temperature");
			textField_4.setHorizontalAlignment(SwingConstants.CENTER);
			textField_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
			textField_4.setColumns(10);
			textField_4.setBounds(460, 440, 203, 50);
			contentPane.add(textField_4);

			textField_5 = new JTextField(day_Max_Temp + " °C");
			textField_5.setEditable(false);
			textField_5.setHorizontalAlignment(SwingConstants.CENTER);
			textField_5.setFont(new Font("Tahoma", Font.BOLD, 16));
			textField_5.setColumns(10);
			textField_5.setBounds(673, 439, 172, 50);
			contentPane.add(textField_5);

			feelslike = new JTextField("Feels like");
			feelslike.setHorizontalAlignment(SwingConstants.CENTER);
			feelslike.setFont(new Font("Tahoma", Font.PLAIN, 15));
			feelslike.setColumns(10);
			feelslike.setBounds(460, 495, 203, 50);
			contentPane.add(feelslike);

			FEELSLIKE = new JTextField(day_Feels + " °C");
			FEELSLIKE.setEditable(false);
			FEELSLIKE.setHorizontalAlignment(SwingConstants.CENTER);
			FEELSLIKE.setFont(new Font("Tahoma", Font.BOLD, 16));
			FEELSLIKE.setColumns(10);
			FEELSLIKE.setBounds(673, 494, 172, 50);
			contentPane.add(FEELSLIKE);

			weathertype = new JTextField("Weather Type");
			weathertype.setHorizontalAlignment(SwingConstants.CENTER);
			weathertype.setFont(new Font("Tahoma", Font.PLAIN, 15));
			weathertype.setColumns(10);
			weathertype.setBounds(460, 550, 203, 50);
			contentPane.add(weathertype);

			WEATHERTYPE = new JTextField(day_Type);
			WEATHERTYPE.setEditable(false);
			WEATHERTYPE.setHorizontalAlignment(SwingConstants.CENTER);
			WEATHERTYPE.setFont(new Font("Tahoma", Font.BOLD, 16));
			WEATHERTYPE.setColumns(10);
			WEATHERTYPE.setBounds(673, 549, 172, 50);
			contentPane.add(WEATHERTYPE);

			uv = new JTextField("Max Ultra Voilet ");
			uv.setHorizontalAlignment(SwingConstants.CENTER);
			uv.setFont(new Font("Tahoma", Font.PLAIN, 15));
			uv.setColumns(10);
			uv.setBounds(460, 605, 203, 50);
			contentPane.add(uv);

			UV = new JTextField(day_UV);
			UV.setEditable(false);
			UV.setHorizontalAlignment(SwingConstants.CENTER);
			UV.setFont(new Font("Tahoma", Font.BOLD, 16));
			UV.setColumns(10);
			UV.setBounds(673, 605, 172, 50);
			contentPane.add(UV);

			btnNewButton_1 = new JButton("Back");
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
					Avg_Day ad = new Avg_Day();
					ad.setVisible(true);
				}
			});
			btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
			btnNewButton_1.setBounds(10, 592, 172, 63);
			contentPane.add(btnNewButton_1);

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

					Download_Files df;
					try {
						df = new Download_Files();
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

			JLabel lblNewLabel1 = new JLabel("");
			lblNewLabel1.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel1.setIcon(new ImageIcon(
					"D:\\eclipse java projects\\weather_application_Final\\weather_application_Final\\img\\final.jpg"));
			lblNewLabel1.setBounds(0, 0, 900, 700);
			contentPane.add(lblNewLabel1);
		}

	}
}
