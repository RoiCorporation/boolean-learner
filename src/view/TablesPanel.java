package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import control.Controller;
import control.Events;
import model.Operation;

@SuppressWarnings("serial")
public class TablesPanel extends JPanel {

	private Controller _ctrl;

	private DefaultComboBoxModel<String> _operationModel;
	private JLabel _tableImageLabel;

	private static final int DEFAULT_VALUE_INDEX = -1;

	public TablesPanel(Controller ctrl) {
		this._ctrl = ctrl;
		this.initGUI();
	}

	private void initGUI() {
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		JLabel opLabel = new JLabel("Operation: ");
		this.add(opLabel);

		// Configures the combobox model
		this._operationModel = new DefaultComboBoxModel<String>();
		List<Operation> allowedOperations = Arrays.asList(Operation.values());
		for (Operation currentOperation : allowedOperations) {
			this._operationModel.addElement(currentOperation.toString());
		}

		// Sets the combobox with the prepared model
		JComboBox<String> operationsComboBox = this.getComboBoxGivenModel(this._operationModel);
		this.add(operationsComboBox, BorderLayout.CENTER);

		// Edit the table text label
		JLabel tableLabel = new JLabel("Truth table: ");
		this.add(tableLabel);

		// Sets the combobox' Action Listener
		operationsComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TablesPanel.this._ctrl.action(Events.DISPLAY_TRUTH_TABLE,
						TablesPanel.this._operationModel.getSelectedItem().toString());
			}
		});

		setPreferredSize(new Dimension(400, 400));
	}

	public void showTable(BufferedImage selectedTable) {
		if (this._tableImageLabel != null)
			this.remove(this._tableImageLabel);

		this._tableImageLabel = new JLabel(new ImageIcon(selectedTable));
		this.add(this._tableImageLabel);
	}

	public void reset() {
		removeAll();
		this.initGUI();
	}

	private JComboBox<String> getComboBoxGivenModel(DefaultComboBoxModel<String> comboBoxModel) {
		// Initializes the combobox
		JComboBox<String> resultingComboBox = new JComboBox<String>(comboBoxModel);

		// Adds the combobox to the main panel
		this.add(resultingComboBox);

		// Adjusts the size of the combobox to fit the largest of its elements
		resultingComboBox.setMaximumSize(resultingComboBox.getPreferredSize());

		// Gives the combobox a blank value by default
		resultingComboBox.setSelectedIndex(DEFAULT_VALUE_INDEX);

		// Returns the configured combobox
		return resultingComboBox;

	}

}
