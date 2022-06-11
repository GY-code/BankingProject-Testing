/**
 * 
 * @author Yi Gu,SSE of BJTU
 * 
 */

package banking.ui;

import java.awt.*;
import java.awt.event.*;
import java.text.NumberFormat;
import java.util.Iterator;

import javax.swing.*;

import banking.domain.*;
import banking.io.*;

/**
 * @author Yi Gu,SSE of BJTU
 *
 * @title BankUserUI
 *
 * @description using Swing to support manager of bank for account operations:
 *              create a Customer with new account, delete a account , query an
 *              account by customer, modify a specified account and display all
 *              account information.
 *
 */
public class BankUserUI {

	private JFrame frame;
	private JPanel buttonPanel;
	private JTextArea display;
	private Bank bank = Bank.getBank();

	public BankUserUI() {
		frame = new JFrame("Customer Account Manager of Bank");

	}

	public void launchFrame() {
		// set frame settings
		frame.setLayout(new BorderLayout());
		frame.setBounds(300, 200, 600, 280);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// set buttons settings
		buttonPanel = new JPanel(new GridLayout(7, 0, 10, 10));
		// realize create operation
		JButton button1 = new JButton("Create");
		button1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				creatCustomer();

			}
		});
		// realize delete operation
		JButton button2 = new JButton("Delete");
		button2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				deleteAccount();

			}
		});
		// realize query operation
		JButton button3 = new JButton("Query");
		button3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				queryCustomer();
			}
		});
		// realize modify operation
		JButton button4 = new JButton("Modify");
		button4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				modifyAccount();

			}
		});
		// realize display operation
		JButton button5 = new JButton("Display All");
		button5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				displayAll();

			}
		});
		// realize save operation
		JButton button6 = new JButton("Save");
		button6.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				FileIO.outputFile();

			}
		});
		JLabel label = new JLabel("Customer Account Manager of Bank");
		// add 6 buttons and a label to the side panel
		buttonPanel.add(label);
		buttonPanel.add(button1);
		buttonPanel.add(button2);
		buttonPanel.add(button3);
		buttonPanel.add(button4);
		buttonPanel.add(button5);
		buttonPanel.add(button6);
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		// set display settings
		JPanel displayPanel = new JPanel(new BorderLayout(5, 5));
		displayPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 10, 5));

		display = new JTextArea(10, 50);
		display.setText("initial text.");
		display.setEditable(false);
		JScrollPane jsp = new JScrollPane(display);
		JLabel info = new JLabel("Account Infomations:");

		displayPanel.add(info, BorderLayout.NORTH);
		displayPanel.add(jsp, BorderLayout.CENTER);
		frame.add(buttonPanel, BorderLayout.EAST);
		frame.add(displayPanel, BorderLayout.CENTER);
		// frame pack setting
		frame.pack();

	}

	protected void displayAll() {
		// TODO Auto-generated method stub
		String output = CustomerDisplay.generateReport();
		display.setText(output);
	}

	protected void modifyAccount() {
		JDialog dialog = new JDialog(frame, "Modify...");
		dialog.setBounds(450, 260, 300, 500);
		// set Layout
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		// set JComoboBox settings

		String[] customerItems = new String[bank.getNumOfCustomers()];
		for (int i = 0; i < bank.getNumOfCustomers(); i++) {
			customerItems[i] = bank.getCustomer(i).getFirstName() + "," + bank.getCustomer(i).getLastName();
		}
		JLabel label1 = new JLabel("choose a customer");
		label1.setAlignmentX(Component.CENTER_ALIGNMENT);
		JComboBox<String> cb1 = new JComboBox<>(customerItems);
		cb1.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
		cb1.setSelectedIndex(-1);

		cb1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Customer c = bank.getCustomer(cb1.getSelectedIndex());
				String[] accountItems = new String[c.getNumOfAccounts()];
				for (int j = 0; j < c.getNumOfAccounts(); j++) {
					Account a = c.getAccount(j);
					if (a instanceof SavingsAccount) {
						accountItems[j] = "SavingsAccount:" + a.getBalance();
					}
					if (a instanceof CheckingAccount) {
						accountItems[j] = "CheckingAccount:" + a.getBalance();
					}

				}
				JLabel label2 = new JLabel("choose an account");
				label2.setAlignmentX(Component.CENTER_ALIGNMENT);
				label2.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
				JComboBox<String> cb2 = new JComboBox<>(accountItems);
				cb2.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
				cb2.setSelectedIndex(-1);

				// balance settings
				JPanel balancePanel = new JPanel();
				JLabel label_balance = new JLabel("Balance:");
				JTextField balance = new JTextField(20);
				balancePanel.add(label_balance);
				balancePanel.add(balance);
				balancePanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

				JPanel protectionPanel = new JPanel();
				JLabel label_protection = new JLabel("Overdraft Protection:");
				JTextField protection = new JTextField(20);
				protectionPanel.add(label_protection);
				protectionPanel.add(protection);
				balance.setEditable(false);
				balance.setBackground(new Color(195, 195, 195));
				protection.setEditable(false);
				protection.setBackground(new Color(195, 195, 195));

				// button at last
				JPanel judge = new JPanel(new GridLayout(0, 2, 10, 10));
				JButton sure = new JButton("Sure");
				sure.setEnabled(false);
				sure.addActionListener(new ActionListener() {
					// core function realize
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						double balanceValue = -1;
						double protectionValue = -1;
						Account a = c.getAccount(cb2.getSelectedIndex());
						if (protection.getText().trim().equals("")) {
							protectionValue = 0;
						}
						if (balance.getText().trim().equals("")) {
							balanceValue = 0;
						}
						try {
							if (balanceValue != 0) {
								balanceValue = Double.parseDouble(balance.getText());

							}
							if (protectionValue != 0) {
								protectionValue = Double.parseDouble(protection.getText());

							}
						} catch (NumberFormatException m) {
							display.setText("Please check the format of numbers and try again.");
							dialog.dispose();
							return;
						}

						a.setBalance(balanceValue);

						if (a instanceof CheckingAccount) {
							((CheckingAccount) a).setOverdraftProtection(protectionValue);
						}
						displayAll();
						dialog.dispose();
					}
				});
				JButton cancel = new JButton("Cancel");
				cancel.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						dialog.dispose();

					}
				});

				judge.removeAll();
				judge.add(sure);
				judge.add(cancel);
				judge.setBorder(BorderFactory.createEmptyBorder(30, 10, 0, 10));
				cb2.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						sure.setEnabled(true);
						Account a = c.getAccount(cb2.getSelectedIndex());
						if (a instanceof SavingsAccount) {
							protection.setEditable(false);
							protection.setBackground(new Color(195, 195, 195));
							protection.setText("");
							balance.setEditable(true);
							balance.setBackground(new Color(255, 255, 255));
							balance.setText(a.getBalance() + "");
						}
						if (a instanceof CheckingAccount) {
							balance.setEditable(true);
							balance.setBackground(new Color(255, 255, 255));
							balance.setText(a.getBalance() + "");
							protection.setEditable(true);
							protection.setBackground(new Color(255, 255, 255));
							protection.setText(((CheckingAccount) a).getOverdraftProtection() + "");

						}

					}
				});

				panel.removeAll();
				panel.add(label1);
				panel.add(cb1);
				panel.add(label2);
				panel.add(cb2);
				// balance add
				panel.add(balancePanel);
				panel.add(protectionPanel);

				panel.add(judge);
				dialog.pack();
			}
		});

		// add to panel
		JButton cancel = new JButton("Cancel");
		JPanel cancelPanel = new JPanel();
		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dialog.dispose();
			}
		});

		cancel.setAlignmentX(Component.CENTER_ALIGNMENT);
		cancelPanel.add(cancel);
		cancelPanel.setBorder(BorderFactory.createEmptyBorder(40, 128, 10, 128));
		panel.add(label1);
		panel.add(cb1);
		panel.add(cancelPanel);

		panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		dialog.add(panel);
		// dialog final settings
		dialog.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		dialog.pack();
		dialog.setModal(true);
		dialog.setVisible(true);
	}

	private void queryCustomer() {
		// TODO Auto-generated method stub
		JDialog dialog = new JDialog(frame, "Query...");
		dialog.setBounds(450, 260, 300, 500);
		dialog.setLayout(new BorderLayout(10, 10));
		JLabel notice = new JLabel("Please enter the cutomer's first name and last name to query.");
		// name settings
		JPanel name = new JPanel();
		JLabel label_fn = new JLabel("First name:");
		JLabel label_ln = new JLabel("Last name:");
		JTextField in_firstName = new JTextField(20);
		JTextField in_lastName = new JTextField(20);
		name.add(label_fn);
		name.add(in_firstName);
		name.add(label_ln);
		name.add(in_lastName);
		// button at last
		JPanel judge = new JPanel(new GridLayout(0, 2, 10, 10));
		JButton sure = new JButton("Sure");
		JButton cancel = new JButton("Cancel");
		judge.add(sure);
		judge.add(cancel);
		judge.setBorder(BorderFactory.createEmptyBorder(0, 10, 15, 10));

		sure.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String first = in_firstName.getText();
				String last = in_lastName.getText();
				Iterator<Customer> i = bank.getCustomers();
				Customer customer;
				Customer result = null;
				while (i.hasNext()) {
					customer = i.next();
					if (customer.getFirstName().equals(first) && customer.getLastName().equals(last)) {
						result = customer;
						break;
					}
				}
				if (result == null) {
					display.setText("No such customer found.");
				} else {
					String output;
					NumberFormat currency_format = NumberFormat.getCurrencyInstance();
					output = "   Customer: " + result.getFirstName() + ", " + result.getLastName() + "\n";

					// iterate the account's iterator
					Iterator<Account> j = result.getAccounts();

					while (j.hasNext()) {

						Account account = j.next();
						String account_type = "";
						boolean displayProtection = false;
						double protection = 0;

						if (account instanceof SavingsAccount) {
							account_type = "Savings Account";
						}
						if (account instanceof CheckingAccount) {
							account_type = "Checking Account";
							displayProtection = true;
							protection = ((CheckingAccount) account).getOverdraftProtection();
						}

						output += "   " + account_type + ":current balance is"
								+ currency_format.format(account.getBalance());
						if (displayProtection) {
							output += "  Overdraft Protection is" + currency_format.format(protection);
						}
						output += "\n";
					}
					display.setText("Such customer has found:\n" + output);
				}
				dialog.dispose();
			}
		});
		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dialog.dispose();

			}
		});
		// add to dialog
		dialog.add(notice, BorderLayout.NORTH);
		dialog.add(name, BorderLayout.CENTER);
		dialog.add(judge, BorderLayout.SOUTH);

		// dialog final settings
		dialog.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		dialog.pack();
		dialog.setModal(true);
		dialog.setVisible(true);
	}

	/**
	 * @description delete an Account
	 *
	 */
	private void deleteAccount() {
		// TODO Auto-generated method stub
		JDialog dialog = new JDialog(frame, "Delete...");
		dialog.setBounds(450, 260, 300, 500);
		// set Layout
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		// set JComoboBox settings

		String[] customerItems = new String[bank.getNumOfCustomers()];
		for (int i = 0; i < bank.getNumOfCustomers(); i++) {
			customerItems[i] = bank.getCustomer(i).getFirstName() + "," + bank.getCustomer(i).getLastName();
		}
		JLabel label1 = new JLabel("choose a customer");
		label1.setAlignmentX(Component.CENTER_ALIGNMENT);
		JComboBox<String> cb1 = new JComboBox<>(customerItems);
		cb1.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
		cb1.setSelectedIndex(-1);

		cb1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Customer c = bank.getCustomer(cb1.getSelectedIndex());
				String[] accountItems = new String[c.getNumOfAccounts()];
				for (int j = 0; j < c.getNumOfAccounts(); j++) {
					Account a = c.getAccount(j);
					if (a instanceof SavingsAccount) {
						accountItems[j] = "SavingsAccount:" + a.getBalance();
					}
					if (a instanceof CheckingAccount) {
						accountItems[j] = "CheckingAccount:" + a.getBalance();
					}

				}
				JLabel label2 = new JLabel("choose an account");
				label2.setAlignmentX(Component.CENTER_ALIGNMENT);
				label2.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
				JComboBox<String> cb2 = new JComboBox<>(accountItems);
				cb2.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

				// button at last
				JPanel judge = new JPanel(new GridLayout(0, 2, 10, 10));
				JButton sure = new JButton("Sure");
				sure.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						c.delAccount(cb2.getSelectedIndex());
						if (c.getNumOfAccounts() == 0) {
							bank.delCustomer(cb1.getSelectedIndex());
						}
						displayAll();
						dialog.dispose();
					}
				});
				JButton cancel = new JButton("Cancel");
				cancel.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						dialog.dispose();

					}
				});
				judge.removeAll();
				judge.add(sure);
				judge.add(cancel);

				judge.setBorder(BorderFactory.createEmptyBorder(30, 10, 0, 10));
				panel.removeAll();
				panel.add(label1);
				panel.add(cb1);
				panel.add(label2);
				panel.add(cb2);
				panel.add(judge);
				dialog.pack();
			}
		});

		// add to panel
		JButton cancel = new JButton("Cancel");
		JPanel cancelPanel = new JPanel();
		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dialog.dispose();
			}
		});

		cancel.setAlignmentX(Component.CENTER_ALIGNMENT);
		cancelPanel.add(cancel);
		cancelPanel.setBorder(BorderFactory.createEmptyBorder(40, 46, 10, 46));
		panel.add(label1);
		panel.add(cb1);
		panel.add(cancelPanel);

		panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		dialog.add(panel);
		// dialog final settings
		dialog.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		dialog.pack();
		dialog.setModal(true);
		dialog.setVisible(true);
	}

	/**
	 * @description create a Customer
	 *
	 */
	private void creatCustomer() {
		// TODO Auto-generated method stub

		JDialog dialog = new JDialog(frame, "Create...");
		dialog.setBounds(450, 260, 300, 500);

		// set Layout
		dialog.setLayout(new BorderLayout(10, 10));
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(5, 0, 10, 10));

		// content settings
		// name settings
		JPanel name = new JPanel();
		JLabel label_fn = new JLabel("First name:");
		JLabel label_ln = new JLabel("Last name:");
		JTextField in_firstName = new JTextField(20);
		JTextField in_lastName = new JTextField(20);
		name.add(label_fn);
		name.add(in_firstName);
		name.add(label_ln);
		name.add(in_lastName);
		// radio button settings
		ButtonGroup buttonGroup = new ButtonGroup();
		JPanel option = new JPanel();
		JRadioButton button_savings = new JRadioButton("Savings Account", false);
		JRadioButton button_checking = new JRadioButton("Checking Account", true);

		option.add(button_checking);
		option.add(button_savings);
		buttonGroup.add(button_checking);
		buttonGroup.add(button_savings);

		// balance settings
		JPanel balancePanel = new JPanel();
		JLabel label_balance = new JLabel("Balance:");
		JTextField balance = new JTextField(20);
		balancePanel.add(label_balance);
		balancePanel.add(balance);

		JPanel protectionPanel = new JPanel();
		JLabel label_protection = new JLabel("Overdraft Protection:");
		JTextField protection = new JTextField(20);
		protectionPanel.add(label_protection);
		protectionPanel.add(protection);

		// Disable options
		button_savings.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				protection.setEditable(false);
				protection.setBackground(new Color(195, 195, 195));
			}
		});
		button_checking.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				protection.setEditable(true);
				protection.setBackground(new Color(255, 255, 255));
			}
		});
		// button at last
		JPanel judge = new JPanel(new GridLayout(0, 2, 10, 10));
		JButton sure = new JButton("Sure");
		sure.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// operation variables
				String firstName;
				String lastName;
				boolean checkingAccount;
				double balanceValue = -1;
				double protectionValue = -1;
				firstName = in_firstName.getText();
				lastName = in_lastName.getText();
				checkingAccount = button_checking.isSelected();
				// except error with empty strings
				if (protection.getText().trim().equals("")) {
					protectionValue = 0;
				}
				if (balance.getText().trim().equals("")) {
					balanceValue = 0;
				}
				try {
					if (balanceValue != 0) {
						balanceValue = Double.parseDouble(balance.getText());

					}
					if (protectionValue != 0) {
						protectionValue = Double.parseDouble(protection.getText());

					}
				} catch (NumberFormatException m) {
					display.setText("Please check the format of numbers and try again.");
					dialog.dispose();
					return;
				}
				// judge existence
				Iterator<Customer> i = bank.getCustomers();
				Customer customer1;
				Customer result = null;
				while (i.hasNext()) {
					customer1 = i.next();
					if (customer1.getFirstName().equals(firstName) && customer1.getLastName().equals(lastName)) {
						result = customer1;
						break;
					}
				}
				if (checkingAccount) {
					if (result == null) {
						bank.addCustomer(firstName, lastName);
						Customer customer = bank.getCustomer(bank.getNumOfCustomers() - 1);
						customer.addAccount(new CheckingAccount(balanceValue, protectionValue));
					} else {
						result.addAccount(new CheckingAccount(balanceValue, protectionValue));
					}

				} else {
					if (result == null) {
						bank.addCustomer(firstName, lastName);
						Customer customer = bank.getCustomer(bank.getNumOfCustomers() - 1);
						customer.addAccount(new SavingsAccount(balanceValue, 0.05));
					} else {
						result.addAccount(new SavingsAccount(balanceValue, 0.05));
					}

				}
				displayAll();
				dialog.dispose();
			}
		});
		JButton cancel = new JButton("Cancel");
		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dialog.dispose();

			}
		});
		judge.add(sure);
		judge.add(cancel);
		judge.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		// add to panel
		panel.add(name);
		panel.add(option);
		panel.add(balancePanel);
		panel.add(protectionPanel);
		panel.add(judge);
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		dialog.add(panel, BorderLayout.CENTER);
		// dialog final settings
		dialog.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		dialog.pack();
		dialog.setModal(true);
		dialog.setVisible(true);

	}

	public static void main(String[] args) {

		FileIO.inputFile();
		BankUserUI ui = new BankUserUI();
		ui.launchFrame();

	}

}
