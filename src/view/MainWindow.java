package view;

import control.Controller;
import control.Events;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MainWindow extends JFrame {

	private Controller _ctrl;
	private ControlPanel _controlPanel;
	private CalculatorPanel _calculatorPanel;
	private TablesPanel _tablesPanel;
	private JPanel _mainPanel;
	private JButton _resetButton;

	public MainWindow(Controller ctrl) {
		this._ctrl = ctrl;
		this.initGUI();
	}

	private void initGUI() {
		setTitle("Boolean Lerner");
		this._mainPanel = new JPanel(new BorderLayout());
		setContentPane(this._mainPanel);

		// Set the control panel
		this._controlPanel = new ControlPanel(this._ctrl);
		this._mainPanel.add(this._controlPanel, BorderLayout.PAGE_START);

		setPreferredSize(new Dimension(620, 620));
		setLocationRelativeTo(null);
		pack();
		setResizable(true);
		setVisible(true);

	}

	public void showCalculatorView() {
		if (this._calculatorPanel == null) { // Ensures it's only created once
			this._calculatorPanel = new CalculatorPanel(this._ctrl);
			this._mainPanel.add(this._calculatorPanel, BorderLayout.CENTER);
		}

		if (this._tablesPanel != null) {
			this._tablesPanel.setVisible(false);
		}

		if (this._resetButton == null) {
			initializeResetButton();
			this._mainPanel.add(this._resetButton, BorderLayout.SOUTH);
		}

		this._calculatorPanel.setVisible(true);
		this._mainPanel.revalidate(); // Revalidates the panel to reset the layout
		this._mainPanel.repaint(); // Repaints to ensure it's rendered
	}

	public void showTablesView() {
		if (this._tablesPanel == null) { // Ensures it's only created once
			this._tablesPanel = new TablesPanel(this._ctrl);
			this._mainPanel.add(this._tablesPanel, BorderLayout.CENTER);
		}

		if (this._calculatorPanel != null) {
			this._calculatorPanel.setVisible(false);
		}

		if (this._resetButton == null) {
			this._resetButton = new JButton("Reset");
			initializeResetButton();
			this._mainPanel.add(this._resetButton, BorderLayout.SOUTH);
		}

		this._tablesPanel.setVisible(true);
		this._mainPanel.revalidate(); // Revalidates the panel to reset the layout
		this._mainPanel.repaint(); // Repaints to ensure it's rendered
	}

	public void showResult(boolean result) {
		this._calculatorPanel.updateResult(result);
		this._mainPanel.revalidate();
		this._mainPanel.repaint();
	}

	public void showTruthTable(BufferedImage selectedTable) {
		this._tablesPanel.showTable(selectedTable);
		this._mainPanel.revalidate();
		this._mainPanel.repaint();
	}

	public void resetView() {
		if (this._calculatorPanel != null && this._calculatorPanel.isVisible()) {
			this._calculatorPanel.reset();
		}
		if (this._tablesPanel != null && this._tablesPanel.isVisible()) {
			this._tablesPanel.reset();
		}

		this._mainPanel.revalidate();
		this._mainPanel.repaint();

	}

	private void initializeResetButton() {
		this._resetButton = new JButton("Refresh");
		this._resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainWindow.this._ctrl.action(Events.RESET_VIEW, null);
			}
		});

		this._mainPanel.add(this._resetButton, BorderLayout.SOUTH);

	}

}
