package control;

public abstract class Controller {
	
	private static Controller _instance;
	
	public static Controller getInstance() {
		if (_instance == null)
			_instance = new ControllerImp();
		return _instance;
	}
	
	
	public abstract void action(int event, Object data);
	
	
	
}
