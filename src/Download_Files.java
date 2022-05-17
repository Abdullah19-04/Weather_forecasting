import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.json.simple.parser.ParseException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.util.regex.PatternSyntaxException;
import java.awt.event.ActionEvent;

public class Download_Files extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	String city = "";
	String combo = "";
	String address = "";
	String addres = "";
	int sample;
	String url = "";
	static String id;
	static String[][] value = new String[50][50];
	String inline = "";
	String print = "";
	private JTextField textField_2;
	private TableModel model;
	private JTable table_1;
	private JButton btnNewButton_1;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Download_Files frame = new Download_Files();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Download_Files() throws IOException {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Please Select an option");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblNewLabel.setBounds(600, 162, 234, 49);
		contentPane.add(lblNewLabel);

		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 19));
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "Full day Forecast", "3 Hour Interval Forecast" }));
		comboBox.setBounds(600, 220, 234, 49);
		contentPane.add(comboBox);

		textField = new JTextField();
		textField.setBounds(301, 138, 156, 43);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblPleaseEnterCity = new JLabel("Enter City Name From The Following :");
		lblPleaseEnterCity.setHorizontalAlignment(SwingConstants.LEFT);
		lblPleaseEnterCity.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblPleaseEnterCity.setBounds(301, 78, 397, 50);
		contentPane.add(lblPleaseEnterCity);

		textField_1 = new JTextField();
		textField_1.setBounds(468, 359, 366, 43);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Please enter the location to store the file");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setBounds(468, 300, 382, 49);
		contentPane.add(lblNewLabel_1);

		JButton btnNewButton = new JButton("Proceed");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				city = textField.getText().toString();
				combo = comboBox.getSelectedItem().toString();

				addres = textField_1.getText().toString();
				address += (addres + "\\");

				if (combo.equals("Full day Forecast")) {
					sample = 0;

				} else {
					sample = 1;
				}

				FileWriter file;
				try {
					file = new FileWriter(
							"D:\\eclipse java projects\\weather_application_Final\\weather_application_Final\\txt\\downloadfiles.txt");
					file.write(address + "\n" + sample + "\n" + city);
					file.close();

				} catch (IOException e1) {
					e1.printStackTrace();
				}

				try (FileReader file1 = new FileReader(
						"D:\\eclipse java projects\\weather_application_Final\\weather_application_Final\\txt\\downloadfiles.txt")) {
					Scanner scan = new Scanner(file1);

					String address2 = scan.next();
					int day2 = scan.nextInt();
					String city2 = scan.next();
					scan.close();

					FileReader file2 = new FileReader(
							"D:\\eclipse java projects\\weather_application_Final\\weather_application_Final\\txt\\location.txt");
					Scanner read = new Scanner(file2);

					int i, j;
					for (i = 0; i <= 47; i++) {
						for (j = 0; j <= 1; j++) {
							value[i][j] = read.next();
						}
					}

					for (i = 0; i <= 47; i++) {
						for (j = 0; j <= 1; j++) {
							if (value[i][1].contains(city2)) {
								id = value[i][j];
								break;
							}
						}
					}

					if (day2 == 0) {
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

							BufferedWriter write = new BufferedWriter(new FileWriter(address2 + city2 + ".json"));
							write.write(inline.toString());
							write.flush();
							write.close();

							scanner.close();

							print = "Your File of Full day Forecast has been populated successfully";
						}
					} else {

						URL url = new URL("http://datapoint.metoffice.gov.uk/public/data/val/wxfcs/all/json/" + id
								+ "?res=3hourly&key=API address here");

						FileReader file3 = new FileReader(
								"D:\\eclipse java projects\\weather_application_Final\\weather_application_Final\\txt\\location.txt");
						Scanner read1 = new Scanner(file3);

						for (i = 0; i <= 47; i++) {
							for (j = 0; j <= 1; j++) {
								value[i][j] = read1.next();
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

							BufferedWriter write = new BufferedWriter(
									new FileWriter(address2 + city2 + "3Hour" + ".json"));
							write.write(inline.toString());
							write.flush();
							write.close();

							scanner.close();
							print = "Your File of 3 Hour data has been stored successfully";

						}

					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}

				textField_2 = new JTextField();
				textField_2.setHorizontalAlignment(SwingConstants.CENTER);
				textField_2.setText(print);
				textField_2.setFont(new Font("Tahoma", Font.PLAIN, 17));
				textField_2.setBounds(348, 549, 528, 72);
				contentPane.add(textField_2);
				textField_2.setColumns(10);

			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton.setBounds(621, 594, 215, 61);
		contentPane.add(btnNewButton);

		btnNewButton_1 = new JButton("Back");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Defaultpage1 df;
				try {
					df = new Defaultpage1();
					df.setVisible(true);
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (ParseException e1) {
					e1.printStackTrace();
				}

			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
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
					e1.printStackTrace();
				}

			}
		});
		btnNewButton_2_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_2_1.setBounds(10, 152, 228, 72);
		contentPane.add(btnNewButton_2_1);

		Object rows[][] = { { "altnaharra " }, { "aultbea " }, { "baltasound " }, { "birmingham " }, { "braemar " },
				{ "cambridge " }, { "carlisle" }, { "dalwhinnie " }, { "edinburgh" }, { "fair " }, { "foula " },
				{ "glasgow " }, { "killowen " }, { "kinloss" }, { "lancaster " }, { "langdon " }, { "liverpool" },
				{ "london " }, { "lossiemouth " }, { "manchester " }, { "oxford " }, { "scatsta " }, { "scilly " },
				{ "wick " }, { "Altnaharra " }, { "Aultbea " }, { "Baltasound " }, { "Birmingham " }, { "Braemar " },
				{ "Cambridge " }, { "Carlisle" }, { "Dalwhinnie " }, { "Edinburgh" }, { "Fair " }, { "Foula " },
				{ "Glasgow " }, { "Killowen " }, { "Kinloss" }, { "Lancaster " }, { "Langdon " }, { "Liverpool" },
				{ "London " }, { "Lossiemouth " }, { "Manchester " }, { "Oxford " }, { "Scatsta " }, { "Scilly " },
				{ "Wick " } };
		Object columns[] = { "location" };
		model = new DefaultTableModel(rows, columns) {
			public Class getColumnClass(int column) {
				Class returnValue;
				if ((column >= 0) && (column < getColumnCount())) {
					returnValue = getValueAt(0, column).getClass();
				} else {
					returnValue = Object.class;
				}
				return returnValue;
			}
		};

		table_1 = new JTable(model);
		table_1.setEnabled(false);
		table_1.getColumnModel().getColumn(0).setPreferredWidth(328);
		table_1.setFont(new Font("Serif", Font.PLAIN, 20));
		table_1.setRowHeight(table_1.getRowHeight() + 7);
		table_1.setBounds(301, 191, 126, 70);
		contentPane.add(table_1);

		final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
		table_1.setRowSorter(sorter);
		String query = textField.getText().toLowerCase();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String text = textField.getText();
				if (text.length() == 0) {
					sorter.setRowFilter(null);
				} else {
					try {
						sorter.setRowFilter(RowFilter.regexFilter(text));
					} catch (PatternSyntaxException pse) {
						System.out.println("Bad regex pattern");
					}
				}
			}
		});

		JLabel lblNewLabel1 = new JLabel("");
		lblNewLabel1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel1.setIcon(new ImageIcon(
				"D:\\eclipse java projects\\weather_application_Final\\weather_application_Final\\img\\final.jpg"));
		lblNewLabel1.setBounds(0, 10, 900, 700);
		contentPane.add(lblNewLabel1);

	}
}
