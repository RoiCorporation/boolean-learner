package control;

import view.MainWindow;

import java.awt.image.BufferedImage;

import model.BooleanLernerModel;

public class ControllerImp extends Controller {

	private MainWindow _window = new MainWindow(this);
	private BooleanLernerModel _model = new BooleanLernerModel(this);

	@Override
	public void action(int event, Object data) {
		switch (event) {
		case Events.QUIT_APP:
			System.exit(0);
			break;
			
		case Events.RESET_VIEW:
			this._window.resetView();
			break;

		case Events.OPEN_CALCULATOR_VIEW:
			this._window.showCalculatorView();
			break;

		case Events.OPEN_TABLES_VIEW:
			this._window.showTablesView();
			break;
			
		case Events.CALCULATE_BOOLEAN_RESULT:
			boolean result = this._model.calculateBooleanResult((String[]) data);
			this._window.showResult(result);
			break;
			
		case Events.DISPLAY_TRUTH_TABLE:
			BufferedImage tableImage = this._model.selectImage((String) data);
			this._window.showTruthTable(tableImage);
			break;

		default:
			break;

		}

	}

}
