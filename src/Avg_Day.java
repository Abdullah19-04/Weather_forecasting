
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
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.PatternSyntaxException;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Avg_Day extends JFrame {

	public JPanel contentPane;
	public static JTextField TFcityname;
	public static JComboBox comboBox;
	public JButton btnNewButton;
	public String city = "";
	public String number_day = "";
	private JButton btnNewButton_1;
	private JButton btnNewButton_1_2;
	private JButton btnNewButton_1_3;
	private JButton btnNewButton_2;
	static int variable;
	private JTable table;
	private JTextField txtL;
	private TableModel model;
	private JTable table_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Avg_Day frame = new Avg_Day();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Avg_Day() {

		Calendar c = Calendar.getInstance();
		Date today = c.getTime();

		c.add(Calendar.DATE, 1);
		c.add(Calendar.MONDAY, 0);
		c.add(Calendar.YEAR, 0);
		Date tomorrow = c.getTime();

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

		JLabel cityname = new JLabel("Enter City Name From The Following :");
		cityname.setFont(new Font("Tahoma", Font.BOLD, 27));
		cityname.setBounds(332, 34, 544, 94);
		contentPane.add(cityname);

		TFcityname = new JTextField();
		TFcityname.setHorizontalAlignment(SwingConstants.CENTER);
		TFcityname.setText("");
		TFcityname.setBounds(332, 138, 156, 43);
		contentPane.add(TFcityname);
		TFcityname.setColumns(10);

		comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 20));
		comboBox.setModel(new DefaultComboBoxModel(new String[] { TOday, ToMorrow, after_tomorrow }));
		comboBox.setBounds(332, 376, 215, 61);
		contentPane.add(comboBox);

		btnNewButton = new JButton("Proceed Further");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				city = TFcityname.getText();
				String number = comboBox.getSelectedItem().toString();

				if (number.contains(TOday)) {
					variable = 0;
				} else if (number.contains(ToMorrow)) {
					variable = 1;
				} else
					variable = 2;

				try {
					FileWriter file = new FileWriter("D:\\eclipse java projects\\weather_application_Final\\weather_application_Final\\txt\\Temp_location.txt");
					file.write(city + "\n" + variable);
					file.close();

				} catch (IOException e1) {
					e1.printStackTrace();
				}

				dispose();
				D_display d;
				try {
					d = new D_display();
					d.setVisible(true);

				} catch (IOException | ParseException e1) {
					e1.printStackTrace();
				}

			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
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
		
		JLabel lblNewLabel = new JLabel("Choose Your Desired Date");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel.setBounds(332, 303, 462, 63);
		contentPane.add(lblNewLabel);
		JButton btnNewButton_2_1 = new JButton("Download Offline Report");
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Download_Files df = new Download_Files();
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
		table_1.setBounds(332, 191, 126, 70);
		contentPane.add(table_1);

		final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
		table_1.setRowSorter(sorter);
		String query = TFcityname.getText().toLowerCase();
		TFcityname.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String text = TFcityname.getText();
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
		lblNewLabel1.setIcon(new ImageIcon("D:\\eclipse java projects\\weather_application_Final\\weather_application_Final\\img\\final.jpg"));
		lblNewLabel1.setBounds(0, 0, 900, 700);
		contentPane.add(lblNewLabel1);
		
	}
}
