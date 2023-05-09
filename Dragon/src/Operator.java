public class Operator {
	Database db = null;
	LoginFrame lf = null;
	JoinFrame jf = null;
	ChangeFrame cf = null;
	DeleteFrame df = null;
//	DragonFlight d = null;
	
	
	
	public static void main(String[] args) {
		Operator opt = new Operator();
		opt.db = new Database();
		opt.lf = new LoginFrame(opt);
		opt.jf = new JoinFrame(opt);
		opt.cf = new ChangeFrame(opt);
		opt.df = new DeleteFrame(opt);
//		opt.d = new DragonFlight();
		


	}
}