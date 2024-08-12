package view;

import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import control.Controller;
import control.Events;
import model.BooleanValue;
import model.Operation;

@SuppressWarnings("serial")
public class CalculatorPanel extends JPanel {

	private Controller _ctrl;
	private String[] _currentBooleanExpression;

	private DefaultComboBoxModel<String> _leftOperandModel;
	private DefaultComboBoxModel<String> _rightOperandModel;
	private DefaultComboBoxModel<String> _operationModel;

	private JComboBox<String> _leftOperandComboBox;
	private JLabel _resultLabel;

	private static final int DEFAULT_VALUE_INDEX = -1;
	private static final int LEFT_OPERAND_INDEX = 0;
	private static final int RIGHT_OPERAND_INDEX = 2;
	private static final int OPERATION_INDEX = 1;

	public CalculatorPanel(Controller ctrl) {
		this._ctrl = ctrl;
		this.initGUI();
	}

	private void initGUI() {

		this._currentBooleanExpression = new String[3];
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setPreferredSize(new Dimension(900, 400));

		// Configures the combobox models
		this.setComboBoxModels();

		// Initializes the comboboxes
		this._leftOperandComboBox = this.getComboBoxGivenModel(this._leftOperandModel);
		JComboBox<String> rightOperandComboBox = this.getComboBoxGivenModel(this._rightOperandModel);
		JComboBox<String> operationsComboBox = this.getComboBoxGivenModel(this._operationModel);

		// Adds tbe comboboxes to the panel
		this.add(this._leftOperandComboBox, BorderLayout.CENTER);
		this.add(operationsComboBox, BorderLayout.CENTER);
		this.add(rightOperandComboBox, BorderLayout.CENTER);

		// Creates the labels
		JLabel resultTextLabel = new JLabel("Result: ");
		this.add(resultTextLabel);

		// Configures the action listener for every combobox

		this._leftOperandComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CalculatorPanel.this._currentBooleanExpression[LEFT_OPERAND_INDEX] = CalculatorPanel.this._leftOperandModel
						.getSelectedItem().toString();
				CalculatorPanel.this.checkBooleanExpression();
			}
		});

		rightOperandComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CalculatorPanel.this._currentBooleanExpression[RIGHT_OPERAND_INDEX] = CalculatorPanel.this._rightOperandModel
						.getSelectedItem().toString();
				CalculatorPanel.this.checkBooleanExpression();
			}
		});

		operationsComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CalculatorPanel.this._currentBooleanExpression[OPERATION_INDEX] = CalculatorPanel.this._operationModel
						.getSelectedItem().toString();
				CalculatorPanel.this.checkBooleanExpression();
			}
		});

	}

	public void updateResult(boolean result) {
		if (this._resultLabel != null)
			this.remove(this._resultLabel);

		this._resultLabel = new JLabel(String.valueOf(result).toUpperCase());
		this.add(this._resultLabel);
	}

	public void reset() {
		this.removeAll();
		this.initGUI();
	}

	private void setComboBoxModels() {
		// Initializes the comboboxes' models
		this._leftOperandModel = new DefaultComboBoxModel<String>();
		this._rightOperandModel = new DefaultComboBoxModel<String>();
		this._operationModel = new DefaultComboBoxModel<String>();

		List<Operation> allowedOperations = Arrays.asList(Operation.values());
		List<BooleanValue> booleanValues = Arrays.asList(BooleanValue.values());

		// Adds the corresponding values to the models
		for (BooleanValue currentValue : booleanValues) {
			this._leftOperandModel.addElement(currentValue.toString());
			this._rightOperandModel.addElement(currentValue.toString());
		}

		for (Operation currentOperation : allowedOperations) {
			this._operationModel.addElement(currentOperation.toString());
		}

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

	private void checkBooleanExpression() {
		this.chekIfNotOperationSelected();
		int i = 0, numberOfInputs = 0;
		while (i < this._currentBooleanExpression.length) {
			if (this._currentBooleanExpression[i] != null)
				numberOfInputs++;
			i++;
		}

		if (this._currentBooleanExpression[OPERATION_INDEX] == Operation.NOT.toString()
				&& numberOfInputs == this._currentBooleanExpression.length - 1) {
			this._leftOperandComboBox.setVisible(false);
			CalculatorPanel.this._ctrl.action(Events.CALCULATE_BOOLEAN_RESULT,
					CalculatorPanel.this._currentBooleanExpression);
		} else if (numberOfInputs == this._currentBooleanExpression.length) {
			CalculatorPanel.this._ctrl.action(Events.CALCULATE_BOOLEAN_RESULT,
					CalculatorPanel.this._currentBooleanExpression);
		}
	}

	private void chekIfNotOperationSelected() {
		if (this._currentBooleanExpression[OPERATION_INDEX] == Operation.NOT.toString())
			this._leftOperandComboBox.setVisible(false);
		else
			this._leftOperandComboBox.setVisible(true);
	}

}
