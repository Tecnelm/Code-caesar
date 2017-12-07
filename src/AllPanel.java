import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class AllPanel extends Panel implements ActionListener {

	private static final long serialVersionUID = 1L;
////////////////////////////
///// definition de composante du panel
////////////////////////////
	private JButton valider = new JButton("valider");
	private int id;
	private static ArrayList < JTextField > textfield = new ArrayList < >();
	private static ArrayList < RadioBox > radio = new ArrayList < >();
	private String[] alltext = new String[24];

	private ButtonGroup groupBox = new ButtonGroup();
	private JLabel label = new JLabel();
	Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
////////////////////////////
/////initialisation
////////////////////////////
	private void setup() {
		int x = 20,
		y = 15;
		for (int i = 0; i < 25; i++) // creation des champs de text
		{
			textfield.add(new JTextField());
		}
		for (JTextField text: textfield) // champ de text parametrage
		{
			text.setBounds(x, y, 1000, 20);
			text.setBorder(border);
			y += 27;
			text.setEditable(false);
			this.add(text);
		}
		x = 1040;
		y = 18;
		for (int i = 0; i < 25; i++) // idem que en haut mais pour les bouton
		{
			radio.add(new RadioBox(i));

		}
		for (RadioBox box: radio) {
			groupBox.add(box);
			box.setBounds(x, y, 15, 15);
			box.setBorder(border);
			y += 27;
			this.add(box);
		}

	}
	//cpnstructeur de AllPanel
	public AllPanel(int id) {

		Font font = new Font("Arial", Font.BOLD, 13);     // police pour les boutons et texte
		this.id = id;	// donne un id au panel permettant de l'identifier dans le panel manager
		this.setup();
		this.setLayout(null);		// dit que l'ont place les composant de manière absolu (px)
// parametrage du bouton et texte
		valider.setFont(font);
		valider.setBounds(1150, 15, 100, 20);
		valider.addActionListener(this);

		label.setText("<html>selectionner le bon texte</html>");
		label.setFont(font);
		label.setBounds(1100, 15, 460, 100);
		label.setOpaque(false);

		this.add(valider);
		this.add(label);

	}

	//regarde si le bouton est pressé 
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == valider) {
			String text = null;
			for (RadioBox box: radio) // regarde qu'elle est la box coché 
			{
				if (box.isSelected()) text = alltext[box.getId()];

			}

			PanelManager.getInstance().getJPanel(0).SetTextTrad(text);
			Frame.getInstance().setVisible(false);
			Frame.getInstance().remove(this);
			Frame.getInstance().add(PanelManager.getInstance().getJPanel(0));
			Frame.getInstance().setVisible(true);
		}
	}
	// fonction qui deffinie les contenu des textfield
	public void SetTextField(String[] string) {
		this.alltext = string; // stoke le tableau donné en entré 
		for(JTextField textfield: textfield)// remet a 0 toute les zones de texte
			textfield.setText(null);
		String affichNB6[] = new String[string.length]; // cette string va recuperer les 6 premiers mot du texte 
		
		for (int i = 0; i < affichNB6.length; i++) {
			String str = "";
			String[] part = string[i].split(" "); // recupere le texte index 1  traduit puis le separe en mot  grace au espace 
			if (part.length >= 6) { // si la chaine fais au moins 6 mot

				for (int ii = 0; ii < 6; ii++) {
					str = str + " " + part[ii]; // ajoute les 6 premier mot a str 
				}
			} else {
				for (String st: part) { // si la chaine  fais moins de 6 mot recompose le texte 
					str = str + " " + st;
				}
			}
			affichNB6[i] = str; // recomposer tous les texte traduit dans une liste a la bonne position

		}

		for (int i = 0 ;i  < affichNB6.length;i++)// affiche chaque texte dans la bonne zone 
		{
			textfield.get(i).setText(affichNB6[i]);
		}
	}

	public int getID() {
		return id;
	}

	@Override
	public void SetTextTrad(String string) {

}
}