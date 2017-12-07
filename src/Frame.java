import javax.swing. * ;

public class Frame extends JFrame {
	private static Frame instance;
	public static Frame getInstance() {
		return instance;
	}
	// parametrage de la fenettre + instance du PanelManager
	private static final long serialVersionUID = 1L;
	Panel pan = PanelManager.getInstance().getJPanel(0);
	public Frame(String name) {
		instance = this;
		this.setTitle(name);
		this.setSize(1280, 720);
		this.setVisible(true);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setResizable(false);

		this.setLocationRelativeTo(null);
		this.setAlwaysOnTop(true);
		this.add(pan);
	}

}