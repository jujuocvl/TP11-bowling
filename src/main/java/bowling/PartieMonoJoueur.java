package bowling;
import java.util.ArrayList;
import java.util.List;


/**
 * Cette classe a pour but d'enregistrer le nombre de quilles abattues lors des
 * lancers successifs d'<b>un seul et même</b> joueur, et de calculer le score
 * final de ce joueur
 */
public class PartieMonoJoueur {

	public static final int nb_quilles =10;
	public static final int nb_tours =10;
	private int numTour = 1;
	private List<Tours> partie = new ArrayList<>();

	/**
	 * Constructeur
	 */
	public PartieMonoJoueur() {
		for (int i = 1; i<=nb_tours; i++) {
			partie.add(new Tours(i));
		}
	}

	/**
	 * Cette méthode doit être appelée à chaque lancer de boule
	 *
	 * @param nombreDeQuillesAbattues le nombre de quilles abattues lors de ce lancer
	 * @throws IllegalStateException si la partie est terminée
	 * @return vrai si le joueur doit lancer à nouveau pour continuer son tour, faux sinon	
	 */
	public boolean enregistreLancer(int nbQuillesAbattues) {
		if (estTerminee()) throw new IllegalStateException("la partie est fini !");
		partie.get(numTour-1).lancer(nbQuillesAbattues);
		if (!partie.get(numTour-1).estFini()) return true;
		if (numTour < nb_tours) numTour++;
		return false;
	}

	/**
	 * Cette méthode donne le score du joueur.
	 * Si la partie n'est pas terminée, on considère que les lancers restants
	 * abattent 0 quille.
	 * @return Le score du joueur
	 */
	public int score() {
		int score = 0;
		for (int i = 0; i < nb_tours-1; i++) {
			Tours tour = partie.get(i);
			score += tour.getNbQuilleLancer1() + tour.getNbQuilleLancer2();
			if (tour.estUnSpare()) {
				score+=partie.get(i+1).getNbQuilleLancer1();
			}
			else if (tour.estUnStrike()){
				if (i+1 == nb_tours-1 || !partie.get(i+1).estUnStrike()){
					score+=partie.get(i+1).getNbQuilleLancer1() + partie.get(i+1).getNbQuilleLancer2();
				}
				else {
					score+=partie.get(i+1).getNbQuilleLancer1() + partie.get(i+2).getNbQuilleLancer1();
				}
			}
		}
		score += partie.get(nb_tours-1).getNbQuilleLancer1() + partie.get(nb_tours-1).getNbQuilleLancer2() + partie.get(nb_tours-1).getNbQuilleLancer3();
		System.out.println(partie.get(nb_tours-1).getNbQuilleLancer1() + partie.get(nb_tours-1).getNbQuilleLancer2() + partie.get(nb_tours-1).getNbQuilleLancer3());
		System.out.println("lancer 1 " + partie.get(nb_tours-1).getNbQuilleLancer1());
		System.out.println("lancer 2 " + partie.get(nb_tours-1).getNbQuilleLancer2());
		return score;
		
	}

	/**
	 * @return vrai si la partie est terminée pour ce joueur, faux sinon
	 */
	public boolean estTerminee() {
		 return partie.get(nb_tours-1).estFini();
	}


	/**
	 * @return Le numéro du tour courant [1..10], ou 0 si le jeu est fini
	 */
	public int numeroTourCourant() {
		if (estTerminee()) return 0;
		return numTour;
	}

	/**
	 * @return Le numéro du prochain lancer pour tour courant [1..3], ou 0 si le jeu
	 *         est fini
	 */
	public int numeroProchainLancer() {
		if (estTerminee()) return 0;
		else if (numTour == nb_tours) return partie.get(nb_tours-1).getNumCoup()+1;
		else return partie.get(numTour).getNumCoup();
	}

}
