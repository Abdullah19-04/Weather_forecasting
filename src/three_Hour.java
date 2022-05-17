
import java.awt.BorderLayout;
import java.awt.Container;
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
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.PatternSyntaxException;
import java.awt.event.ActionEvent;

public class three_Hour extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	public String selection = "";
	public String city = "";
	int variable;
	private TableModel model;
	private JTable table_1;
	private JButton btnNewButton_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					three_Hour frame = new three_Hour();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public three_Hour() {

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

		JLabel cityname = new JLabel("Enter City Name From The Following :");
		cityname.setFont(new Font("Tahoma", Font.BOLD, 18));
		cityname.setBounds(332, 78, 397, 50);
		contentPane.add(cityname);

		textField = new JTextField();
		textField.setText("");
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setColumns(10);
		textField.setBounds(332, 138, 156, 43);
		contentPane.add(textField);

		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] { TOday, ToMorrow, after_tomorrow }));
		comboBox_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		comboBox_1.setBounds(590, 238, 215, 61);
		contentPane.add(comboBox_1);

		JButton btnNewButton = new JButton("Proceed Further");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				city = textField.getText();
				String number = comboBox_1.getSelectedItem().toString();

				if (number.contains(TOday)) {
					variable = 0;
				} else if (number.contains(ToMorrow)) {
					variable = 1;
				} else
					variable = 2;

				FileWriter file;
				try {
					file = new FileWriter("D:\\eclipse java projects\\weather_application_Final\\weather_application_Final\\txt\\Temp_location.txt");
					file.write(city + "\n" + variable);
					file.close();

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				dispose();
				Three_display td;
				try {
					td = new Three_display();
					td.setVisible(true);
				} catch (IOException | ParseException e1) {
					// TODO Auto-generated catch block
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
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton_1.setBounds(10, 592, 172, 63);
		contentPane.add(btnNewButton_1);

		JLabel lblNewLabel = new JLabel("Choose Your Desired Date");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setBounds(590, 187, 246, 37);
		contentPane.add(lblNewLabel);

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
		lblNewLabel1.setIcon(new ImageIcon("D:\\eclipse java projects\\weather_application_Final\\weather_application_Final\\img\\final.jpg"));
		lblNewLabel1.setBounds(0, 0, 900, 700);
		contentPane.add(lblNewLabel1);

	
	}
}
