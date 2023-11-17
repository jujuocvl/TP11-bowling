package bowling;

public class Tours {
    private int numTour;
    private int quillesTour1=0;
    private int quillesTour2=0;
    private int quillesTour3=0;
    private int numCoup=1;
    boolean estFini = false;

    public Tours(int quillesTour1, int quillesTour2) {
        this.quillesTour1 = quillesTour1;
        this.quillesTour2 = quillesTour2;
    }
    public Tours(int numTour) {
        this.numTour = numTour;
    }

    public boolean estFini() {
        return estFini;
    }

    public boolean estUnStrike() {
        return quillesTour1 == PartieMonoJoueur.nb_quilles;
    }

    public boolean estUnSpare() {
        return numCoup == 2 && quillesTour1+quillesTour2 == PartieMonoJoueur.nb_quilles && numTour != PartieMonoJoueur.nb_tours;
    }

    public void lancer(int valeur){
		switch (numCoup){
			case 1:
				quillesTour1 += valeur;
				if (quillesTour1 == PartieMonoJoueur.nb_quilles && numTour != PartieMonoJoueur.nb_tours) estFini = true;
				else if (quillesTour1 == PartieMonoJoueur.nb_quilles) numCoup += 1;
				else numCoup++;
				break;
			case 2:
				quillesTour2 += valeur;
				estFini = true;
				if (numTour == PartieMonoJoueur.nb_tours) {
					if (!(quillesTour1+quillesTour2 < PartieMonoJoueur.nb_tours)){
						numCoup++;
						estFini = false;
					}
				}
				break;
			case 3:
				quillesTour3 += valeur;
				estFini = true;
				break;
		}
	}

    public int getNbQuilleLancer1() {
        return quillesTour1;
    }
    public int getNbQuilleLancer2() {
        return quillesTour2;
    }
    public int getNbQuilleLancer3() {
        return quillesTour3;
    }
    public int getNumCoup() {
        return numCoup;
    }
    public int getNumTour() {
        return numTour;
    }
}
