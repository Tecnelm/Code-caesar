import java.text.Normalizer;
import java.util.ArrayList;
public class Action {

	private static ArrayList < Character > text_lettre = new ArrayList < Character > (); // Cette arrayList est le texte rentré par l'utilisateur decomposer en lettre 
	private ArrayList < String > result = new ArrayList < String > (); //  result est un tableau de tous les resultat (codage decodage) possible ceux realiser 
	private String decodeOrder;// il s'agit de l'ordre de decodage a appliquer au texte (une lettre ou un decalage)
	private String textStart; // text Start le texte de base qui est rentré par l'utilisateur (celui a coder ou decoder)
	
	// construteur de l'objet
	public Action(String TextStart, String decodeOrder) {
		text_lettre.clear(); // remet a la liste
		result.clear();// remet a zero les resultat
		this.decodeOrder = decodeOrder.toLowerCase(); // definit l'odre de decalage ^dans la variable global et la met en character minuscul
		this.textStart = TextStart.toLowerCase();	//  met le texte rentré en minuscul
		init();// execute la fonction init

	}

	// cette fonction va regarder si un string est constituer uniquement de nombre
	private boolean IsNumeric() {

		if (decodeOrder.length() == 0) // regarde si la liste et vide et retourne false si vrai  ne peux etre numérique si vide
		return false;
		try {								// tente de tranformer le texte en entier si il y arrive return vrai
			Integer.parseInt(decodeOrder);
			return true;
		} catch(NumberFormatException e) {
			return false;
		}

	}

	// cette fonction va décoder le text chiffré en lui donnant le décalage
	// (decode order) rentrez une lettre =analyse freq ; un chiffre = classic; lettre puis chiffre = la lettre est prise freq ; chiffre puis lettre = error
    public   void decode( ) {
    	
        ArrayList <Integer> decalageList  = new ArrayList<>(); // liste de tous les decalage possible 
        
        boolean run = false; // dire si il faut lancer le decodage ou non
        char lettrefreq; // cette lettre servira au decode par la frequence ce serais la lettre la plus frequente
        if (IsNumeric()) // regarde si decode ordeur est deja le decalage
        {
        	decalageList.add(Integer.parseInt(decodeOrder));			// ajoute l'ordre de decalage a la liste 
            if (decalageList.get(0) < 26 && decalageList.get(0) > -26) // verifie que le decalage est bien compris entre 25 et -25
                run = true;		// autorise le lancement 
            else
                run = false;
        } else if(decodeOrder.length()!=0) {
        	lettrefreq = decodeOrder.charAt(0); // récupere le premier character rentré pour analyser la frequence
            if (Character.isLetter(lettrefreq)) {
            	decalageList.addAll(AnalyseFreq( lettrefreq)); // va realiser l'analyse de frequence de la lettre pour trouver le décalage si deux  
                run = true;									  // lettre on la meme frequence alors il y deux valeurs dans le decalage list
            } else
                run = false;
        }
        else run = false;
        if (run) {			// lance de decalage 
        	run = false;
        	for (int nblist = 0 ; nblist < decalageList.size();nblist++)	// va exectuter le cette parti pour la totalité des valeur de decalage possible 
        	{
        		String text=""; // cette variable seras le texte tradiot 
        		int decalage  = decalageList.get(nblist); // définit le décalage qui sera utiliser dans cette boucle 
	            char lettre = '\0'; // ceci est la variable  qui va recuperer la la lettre dans la liste
	            int nb_charact = text_lettre.size();		// le nombre de character que fais mon texte 

	            for (int i = 0; i < nb_charact; i++) { // pour chaque  character
	                lettre = text_lettre.get(i);		// recupere la lettre associé a  a la position i dans la liste de lettre decomposé
	                if (lettre < 97 || lettre > 122) { // si autre chose qu'une lettre
	                    text += (char) lettre;
	                    continue;
	                }
	                if (decalage > 0) // decalage plus grand que 0 alors tu soustrait 1  car decode 
	                {
	                    for (int nb = 0; nb < decalage; nb++) {
	                        lettre -= 1;
	                        if (lettre < 97) // quand arrive a 'a' retourne a 'z'
	                            lettre = 122;
	                    }
	                   
	                } else // ajoute 1 si le decalage est plus petit que 1 car decode
	                {
	                    for (int nb = 0; nb < -decalage; nb++) {
	                        lettre += 1;
	                        if (lettre > 122) // quand arrive a 'z' retourne a 'a'
	                            lettre = 97;
	                    }
	                    
	                }  
	                text += (char) lettre; // ajoute la lettre au texte
	            }
	            result.add(text); // ajoute le texte entier traduit au resultat
        	}
        } else if (decodeOrder.length() == 0) // si les info plus haut ne sont pas lancer et que la chaine de character est vide alors
        	afficherTous(); //lancer afficherTous va stocker tous les possibilités dans la variable result
    }

	// cette fonction va codé un text decomposé en character dans une arraylist
	// cette fonction va coder le text chiffré en lui donnant le décalage
	public void code() {

		boolean run = false; // ce boolean servira a lancer le code si tous est corect

		String text = ""; // il s'agit du texte traduit (codé)
		int decalage = 0;
		//cette partie va verifier  si la chaine de character donné est constituer uniquement de chiffre  si c est le cas elle le transforme en
		// int puis se int servira de decalage
		if (IsNumeric()) {
			decalage = Integer.parseInt(decodeOrder);
			if (decalage < 26 && decalage > -26) // verifie que le decalage est bien compris entre 25 et -25
			run = true;
			else run = false;
		} else run = false;
		if (run) {
			run = false;
			char lettre = '\0'; // il s'agit de la lettre qui seras isolé a chaque pour etre codé 
			int nb_charact = text_lettre.size(); // taille du tableau 

			for (int i = 0; i < nb_charact; i++) {
				lettre = text_lettre.get(i);
				if (lettre < 97 || lettre > 122); // si le charactere n est pas une lettre
				else {
					if (decalage > 0) // si le decalage est plus grand que 0 alors ajoute 1 au int du char
					{
						for (int nb = 0; nb < decalage; nb++) {
							lettre++;
							if (lettre > 122) lettre = 97;
						}

					} else // l'inverse  plus  petit que 0 alors enleve 1
					{
						for (int nb = 0; nb < -decalage; nb++) {
							lettre -= 1;
							if (lettre < 97) lettre = 122;
						}
					}
				}
				text += (char) lettre; // cocacene la lettre a une chaine de chractère (qui seras le texte)

			}

			result.add(text);

		}
		text_lettre.clear();
	}

	// cette fonction va stocker dans une ArrayList toute les possibilité de décodage
	// cette fonction va décoder toutes les possibilité de chiffrage
	private void afficherTous() {

		for (int ii = 1; ii <= 25; ii++) // va coder toute les possibiliter de texte puis les stocker
		{

			decodeOrder = "" + ii;
			decode(); // stocke le text dans une liste     
		}
	}

	// cette fonction va analyser la frequence d'une lettre (peu ensuite return le decalage trouvé a modifier
	// mettre int et rapporter le decalage fonctionne dans la fonction decode

	private   ArrayList <Integer> AnalyseFreq( char lettrefreq) {
        int nbmax = 0, nblettre = 0; // mot ayant le meme nombre de lettre ; 
       
        boolean test = false;

        ArrayList < Character > lettre  = new ArrayList < Character > ();
        ArrayList <Integer>  difference =  new ArrayList<>();
        ArrayList < Character > lettreList  = new ArrayList < Character > ();
        
        // elimine les doublon  dans la list newlist
        for (int i = 0; i < text_lettre.size(); i++) {
            Object o = text_lettre.get(i); // type objet qui sera l'index i de liste pricipale ( texte decomposer en char)
            if(!Character.isLetter((char)o))
            	continue;
            if (!lettre.contains(o)) // regarde si la lettre de la liste rentré est deja dans la nouvelle list et l'ajoute si elle n'y est pas 
            	lettre.add((char)o); // ceci va donc enlever les doublon
        }

        //////////////////////////////////////////////
        //cette partie du code va analiser la frequence des lettre codé
        //////////////////////////////////////////////
        for (int i = 0; i < lettre.size(); i++) {

            for (int ii = 0; ii < text_lettre.size(); ii++) {
                test = lettre.get(i) == text_lettre.get(ii);

                if (test)
                    nblettre++;
            }
            
            if (nblettre > nbmax) { // si nouveau nbmax  alors suprime le contenu de la liste et ajoute la nouvelle lettre
            	lettreList.clear();
            	lettreList.add(lettre.get(i));
                nbmax = nblettre;
            }
            else if(nblettre == nbmax)// si les deux lettre sont taper le meme nombre fois  ajoute il l'ajoute a la liste 
            {
            	lettreList.add(lettre.get(i));
            }

            
            nblettre = 0;	// redef le nb de lettre a  0  pour faire l'analyse de frequence avec la nouvelle lettre 
            }
        //////////////////////////////////////////////
        //cette partie du code va s'occuper de donner la difference avec la lettre la plus commune, et redemander de tenter a chaque fois ou donner l'ordre de tous afficher 
        //////////////////////////////////////////////

        for (char e :lettreList) // parcoure la liste 
        {
        	if(e-lettrefreq != 0) // fais la difference pour chaque element de la liste puis l'ajoute 
        	{
        	difference.add(e-lettrefreq); // les decalage 
        	}
        }
        return difference;
    }
    

	// cette partie est la sequence d'initialisation demandant le text et le decomposant en character dans une 
	// Array list base pour les autre fonction

	private void init() {

		String texte = textStart; // le texte qui sera traité 

		texte = Normalizer.normalize(texte, Normalizer.Form.NFD).replaceAll("[\u0300-\u036F]", "");
		// cette fonction va transformer les majuscule en minuscule et enlever les accent ou autre charactère "speciaux"

		int nb_charact = texte.length();

		for (int i = 0; i < nb_charact; i++)
		text_lettre.add(i, texte.charAt(i)); // decomposition du texte en charactère 

	}



	// permet de recuperer les resultats du code/ decodage
	public String[] getresult() {
		if (result.size() == 0) { // si la liste de resultat est vide 
			String[] resultat = {""};
			return resultat;
		}

		String[] resultat = new String[result.size()]; // cree un tableau de la taille du nombre de resultat 

		if (result.size() == 1) 
			resultat[0] = result.get(0); 
		else {// transfome l'array list en list
			for (int i = 0; i < result.size(); i++)
			resultat[i] = result.get(i);
		}
		return resultat;
	}

}