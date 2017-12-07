import java.util.ArrayList;

// cette classe va gerer le pannel a afficher 
public class PanelManager {
	private ArrayList < Panel > jPanels = new ArrayList < >();
	private static PanelManager instance = new PanelManager();
	// permet d'eviter de  regénerer le pannel manageur a chaque fois
	public static PanelManager getInstance() {
		return instance;
	}
	public void setup() {
		jPanels.add(new MainPanel(0));
		jPanels.add(new AllPanel(1));
		instance = this;
	}

	public ArrayList < Panel > getJPanels() {
		return jPanels;
	}
	// parcoure la liste pour touver le bon pannel a afficher
	public Panel getJPanel(int id) {
		for (Panel panel: jPanels) {
			if (panel.getID() == id) return panel;
		}
		return null;
	}
}