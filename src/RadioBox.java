import javax.swing.JRadioButton;

public class RadioBox extends JRadioButton {
	// button radio mais avec un id
	private static final long serialVersionUID = 1L;
	private int id;
	public RadioBox(int id) {
		super();
		this.id = id;
	}

	public int getId() {
		return id;
	}
}