import java.awt.EventQueue;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

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

import javax.swing.SwingConstants;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Defaultpage1 extends JFrame {
	static String id;
	static String[][] value = new String[50][50];
	private JPanel contentPane;
	private JTextField defaultLocation;
	private JTextField winddirection;
	private JTextField WINDDIRECTION;
	private JTextField weathertype;
	private JTextField WEATHETYPE;
	private JTextField temp;
	private JTextField TEMERARTOEE;
	private JButton btnNewButton_2;
	private JTextField txtTime;
	private JTextField GETDATE;
	private JButton btnNewButton_1_2;
	private JButton btnNewButton_1_3;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Defaultpage1 frame = new Defaultpage1();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Defaultpage1() throws IOException, ParseException {

		String inline2 = "";
		String inline = "";
		String city = "London";
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
			String address = "D:\\eclipse java projects\\weather_application_Final\\weather_application_Final\\txt\\3Hour.json";
			BufferedWriter write = new BufferedWriter(new FileWriter(address));
			write.write(inline.toString());
			write.flush();
			write.close();

			FileReader file2 = new FileReader(address);

			Scanner scanner2 = new Scanner(file2);

			while (scanner2.hasNext()) {
				inline2 += scanner2.nextLine();
			}

			JSONParser parser = new JSONParser();
			JSONObject data_obj = (JSONObject) parser.parse(inline2);

			JSONObject siteRep = (JSONObject) data_obj.get("SiteRep");
			JSONObject DV = (JSONObject) siteRep.get("DV");
			JSONObject location = (JSONObject) DV.get("Location");

			JSONArray peri = (JSONArray) location.get("Period");

			JSONObject j0 = (JSONObject) peri.get(0);
			String date = (String) j0.get("value");

			JSONArray rep0 = (JSONArray) j0.get("Rep");

			JSONObject day = (JSONObject) rep0.get(0);

			String wind_Direction = (String) day.get("D");
			String wind_speed = (String) day.get("S");
			String temperature = (String) day.get("T");
			String weather_Type = (String) day.get("W");
			String time = (String) day.get("$");

			int time1 = Integer.valueOf(time);
			int time2;

			if (time1 >= 60) {

				time2 = time1 / 60;
			} else {
				time2 = time1;
			}
			String time3 = String.valueOf(time2);

			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 900, 700);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);

			defaultLocation = new JTextField(city);
			defaultLocation.setHorizontalAlignment(SwingConstants.CENTER);
			defaultLocation.setFont(new Font("Arial", Font.BOLD, 31));
			defaultLocation.setEditable(false);
			defaultLocation.setColumns(10);
			defaultLocation.setBounds(300, 10, 543, 83);
			contentPane.add(defaultLocation);

			winddirection = new JTextField();
			winddirection.setText("Wind Direction");
			winddirection.setHorizontalAlignment(SwingConstants.CENTER);
			winddirection.setFont(new Font("Tahoma", Font.PLAIN, 16));
			winddirection.setBounds(300, 128, 213, 77);
			contentPane.add(winddirection);
			winddirection.setColumns(10);

			WINDDIRECTION = new JTextField(wind_speed + " mph at " + wind_Direction);
			WINDDIRECTION.setFont(new Font("Tahoma", Font.BOLD, 16));
			WINDDIRECTION.setEditable(false);
			WINDDIRECTION.setHorizontalAlignment(SwingConstants.CENTER);
			WINDDIRECTION.setBounds(539, 128, 155, 77);
			contentPane.add(WINDDIRECTION);
			WINDDIRECTION.setColumns(10);

			weathertype = new JTextField();
			weathertype.setText("Weather Type");
			weathertype.setHorizontalAlignment(SwingConstants.CENTER);
			weathertype.setFont(new Font("Tahoma", Font.PLAIN, 16));
			weathertype.setColumns(10);
			weathertype.setBounds(300, 244, 213, 77);
			contentPane.add(weathertype);

			WEATHETYPE = new JTextField(weather_Type);
			WEATHETYPE.setFont(new Font("Tahoma", Font.BOLD, 16));
			WEATHETYPE.setHorizontalAlignment(SwingConstants.CENTER);
			WEATHETYPE.setEditable(false);
			WEATHETYPE.setColumns(10);
			WEATHETYPE.setBounds(539, 244, 155, 77);
			contentPane.add(WEATHETYPE);

			temp = new JTextField();
			temp.setText("Current Temperature");
			temp.setHorizontalAlignment(SwingConstants.CENTER);
			temp.setFont(new Font("Tahoma", Font.PLAIN, 16));
			temp.setColumns(10);
			temp.setBounds(300, 349, 213, 72);
			contentPane.add(temp);

			TEMERARTOEE = new JTextField(temperature + " °C");
			TEMERARTOEE.setHorizontalAlignment(SwingConstants.CENTER);
			TEMERARTOEE.setFont(new Font("Tahoma", Font.BOLD, 16));
			TEMERARTOEE.setEditable(false);
			TEMERARTOEE.setColumns(10);
			TEMERARTOEE.setBounds(539, 349, 155, 73);
			contentPane.add(TEMERARTOEE);

			JButton btnClose = new JButton("Close");
			btnClose.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
					Defaultpage1 df;
					try {
						df = new Defaultpage1();
						df.dispose();
					} catch (IOException e1) {
						e1.printStackTrace();
					} catch (ParseException e1) {
						e1.printStackTrace();
					}

				}
			});
			btnClose.setFont(new Font("Tahoma", Font.PLAIN, 16));
			btnClose.setBounds(10, 592, 172, 63);
			contentPane.add(btnClose);

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

					btnNewButton_1_2 = new JButton("Average Night");
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

					btnNewButton_1_3 = new JButton("3 Hour Interval");
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

			btnNewButton_2 = new JButton("Read Offline Report");
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

			txtTime = new JTextField();
			txtTime.setText("Weather Date");
			txtTime.setHorizontalAlignment(SwingConstants.CENTER);
			txtTime.setFont(new Font("Tahoma", Font.PLAIN, 16));
			txtTime.setColumns(10);
			txtTime.setBounds(300, 454, 213, 72);
			contentPane.add(txtTime);

			GETDATE = new JTextField(date);
			GETDATE.setHorizontalAlignment(SwingConstants.CENTER);
			GETDATE.setFont(new Font("Tahoma", Font.BOLD, 16));
			GETDATE.setEditable(false);
			GETDATE.setColumns(10);
			GETDATE.setBounds(539, 454, 155, 73);
			contentPane.add(GETDATE);

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