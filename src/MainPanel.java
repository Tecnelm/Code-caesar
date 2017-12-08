import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;

public class MainPanel extends Panel implements ActionListener {

	private static final long serialVersionUID = 1L;

	private JButton coder = new JButton("coder");
	private JButton decoder = new JButton("décoder");

	private JTextFieldLimit textField = new JTextFieldLimit(3);

	private JTextArea textarea1 = new JTextArea();
	private JTextArea textarea2 = new JTextArea();

	private JScrollPane Textarea1;
	private JScrollPane Textarea2;

	private int id;
	// constructeur de la fonction MainPanel
	public MainPanel(int id) {
		Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
		Font font = new Font("Arial", Font.BOLD, 13);
		Font font1 = new Font("Arial", Font.BOLD, 10);

		this.id = id;

		this.setLayout(null);
		// parametrage des bouton
		coder.setBounds(455, 310, 75, 20);
		coder.setFont(font);
		coder.addActionListener(this);
		decoder.setFont(font);
		decoder.setBounds(725, 310, 100, 20);
		decoder.addActionListener(this);
		// paramettrage des champs de texte
		textField.setFont(font);
		textField.setBorder(border);
		textField.setBounds(555, 310, 150, 20);
		// parametrage des  zone de texte 
		textarea1.setLineWrap(true);
		Textarea1 = new JScrollPane(textarea1);
		Textarea1.setBorder(border);
		Textarea1.setBounds(20, 20, 1240, 270);

		textarea2.setLineWrap(true);
		textarea2.setEditable(false);
		Textarea2 = new JScrollPane(textarea2);
		Textarea2.setBorder(border);
		Textarea2.setBounds(20, 350, 1240, 270);

		// parametrage des consigne 
		JLabel label = new JLabel();
		label.setText("<html>pour coder : rentrez le décalage puis appuyer sur coder.<br>pour décoder : la lettre la plus fréquente ou le décalage puis cliquer sur décoder ou rien.</html>");
		label.setFont(font1);
		label.setBounds(830, 270, 460, 100);
		label.setOpaque(false);

		// ajout de tous le composant a la frame
		this.add(coder);
		this.add(decoder);
		this.add(Textarea1);
		this.add(Textarea2);
		this.add(textField);
		this.add(label);

	}
	// fonction qui regarde quand un bouton est appuyer 
	public void actionPerformed(ActionEvent arg0) {
		if (textarea1.getText().length() != 0) { // si la zone de texte  a des characters
			Action n = new Action(textarea1.getText(), textField.getText());

			if (arg0.getSource() == coder) { // regarde si le bouton presser est coder
				n.code(); // voir classe action 
				this.SetTextTrad(n.getresult()[0]); // redefini le texte a afficher 
			}

			if (arg0.getSource() == decoder) { // si bouton decoder 
				n.decode();
				String[] resultat = n.getresult(); //  recupère le tableau de resultat

				if ((resultat.length) == 1) // si il n'y a que un resultat  le met dans zone 2 eme zone  de texte 
				this.SetTextTrad(resultat[0]); 

				else {
					// si aucune des actions n'a été faite change de panel (affichage trop de resultat )
					PanelManager.getInstance().getJPanel(1).SetTextField(resultat); // definie les text field avec les possibiliter				  
					Frame.getInstance().setVisible(false); // rend invisible cet objet   pour permettre le reload
					Frame.getInstance().remove(this); // le supprime
					Frame.getInstance().add(PanelManager.getInstance().getJPanel(1)); // change le panel
					Frame.getInstance().setVisible(true); // rend visible le nouveau panel  
					
					
				}

			}
		}

	}
	// sers a dire qelle texte afficher dans le texte area de traduction
	public void SetTextTrad(String string) {
		textarea2.setText(null); // suprime le contenu de la zone 
		textarea2.append(string);// ajoute le nouveau 

	}
	// recuperer le text du text a traduire
	public String GetTextToTrad() {
		return textarea1.getText();
	}
	// recupérer l'id du panel
	public int getID() {
		return id;
	}

	@Override
	public void SetTextField(String[] string) {

}

}
