import java.util.Scanner;

public class Main {
	static Scanner read = new Scanner(System. in );
	public static void main(String[] args) {
		// setup le panelManager
		PanelManager.getInstance().setup();
		// cree la frame
		new Frame("Project code cesar");
	}

}