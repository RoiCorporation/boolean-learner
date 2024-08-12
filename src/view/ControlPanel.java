package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import control.Controller;
import control.Events;

@SuppressWarnings("serial")
public class ControlPanel extends JPanel {

	// ATTRIBUTES
	private Controller _ctrl;
	private JToolBar _toolBar;
	private JButton _calculatorViewButton, _tablesViewButton, _exitButton;

	// CONSTANTS
	private static final String EXIT_BUTTON_TEXT = "Quit";
	private static final String CALCULATOR_VIEW_BUTTON_TEXT = "Open the boolean calculator";
	private static final String TABLES_VIEW_BUTTON_TEXT = "Check the boolean tables";

	public ControlPanel(Controller ctrl) {
		this._ctrl = ctrl;
		this.initGUI();
	}

	private void initGUI() {
		setLayout(new BorderLayout());
		this._toolBar = new JToolBar();
		add(this._toolBar, BorderLayout.PAGE_START);

		// Configures the buttons in the toolbar
		this.setCalculatorViewButton();
		this.setTablesViewButton();
		this.setExitButton();

	}

	private void setCalculatorViewButton() {
		this._calculatorViewButton = new JButton();
		this._calculatorViewButton.setToolTipText(CALCULATOR_VIEW_BUTTON_TEXT);
		this._calculatorViewButton.setIcon(new ImageIcon("resources/icons/calculator.png"));
		this._calculatorViewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ControlPanel.this._ctrl.action(Events.OPEN_CALCULATOR_VIEW, null);
			}
		});

		this._toolBar.add(this._calculatorViewButton);

	}

	private void setTablesViewButton() {
		this._tablesViewButton = new JButton();
		this._tablesViewButton.setToolTipText(TABLES_VIEW_BUTTON_TEXT);
		this._tablesViewButton.setIcon(new ImageIcon("resources/icons/table.png"));
		this._tablesViewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ControlPanel.this._ctrl.action(Events.OPEN_TABLES_VIEW, null);
			}
		});

		this._toolBar.add(this._tablesViewButton);

	}

	private void setExitButton() {
		this._toolBar.add(Box.createGlue());
		this._exitButton = new JButton();
		this._exitButton.setToolTipText(EXIT_BUTTON_TEXT);
		this._exitButton.setIcon(new ImageIcon("resources/icons/exit.png"));
		this._exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ControlPanel.this._ctrl.action(Events.QUIT_APP, null);
			}
		});

		this._toolBar.add(this._exitButton);

	}

}
