
public class Controller implements UserInputProcessor {
	private View view;
	private Model model;

	@Override
	public void selectCheckIn() {
		try{
			if(view != null)
			{
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
	public void typeCopyISBN(String copyISBN) {
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
