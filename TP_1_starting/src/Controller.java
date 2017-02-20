
public class Controller implements UserInputProcessor {
	private View view;
	private Model model;
	private static int CHECKOUTPROCESS = 1;
	private static int CHECKINPROCESS = 2;
	private static int VIEWRECORDPROCESS = 3;
	private int currentProcess = 0;

	@Override
	public void selectCheckIn() {
		try{
			if(view != null)
			{
				currentProcess = CHECKINPROCESS;
				view.showCopyScanningScreen();
			}
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
		
	}

	@Override
	public void selectCheckOut() {
		try{
			if(view != null)
			{
				currentProcess = CHECKOUTPROCESS;
				view.showIdScanningScreen();
			}
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
		
	}

	@Override
	public void selectViewPatronRecord() {
		try{
			if(view != null)
			{
				currentProcess = VIEWRECORDPROCESS;
				view.showIdScanningScreen();
			}
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
	}

	@Override
	public void scanIdCard(Object scanner) {
		// TODO Auto-generated method stub
	}

	@Override
	public void typeIdNumber(String idNumber) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void scanCopy(Object scanner) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void typeCopyId(String copyId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeCopyFromCheckOut(int copyNumber) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void completeCheckOut() {
		// TODO Auto-generated method stub
		
	}
	
	private void initializeApplication()
	{
		try{
			this.view = new View(); 
			this.model = new Model();
			view.bindUserInputProcessor(this);
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
	}
	
	private void start()
	{
		try{
			if(this.view != null)
			{
				this.view.showMainScreen();
			}
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
	}

	public static void main(String[] args)
	{
		Controller controller = new Controller();
		controller.initializeApplication();
		controller.start();
	}
}
