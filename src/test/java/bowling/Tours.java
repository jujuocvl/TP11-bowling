package bowling;

public class Tours {
    private int quillesTour1;
    private int quillesTour2;
    private Tours suivant;

    public Tours(int quillesTour1, int quillesTour2) {
        this.quillesTour1 = quillesTour1;
        this.quillesTour2 = quillesTour2;
    }

    public int score() {
        int scoreTour = 0;
        if (estUnStrike()) {
            scoreTour = 10 + suivant.bonusStrike();
        } else if (estUnSpare()) {
            scoreTour = 10 + suivant.bonusSpare() ;
        } else {
            scoreTour = quillesTour1 + quillesTour2;
        }
        return scoreTour + suivant.score();
    }

    public
    
}
