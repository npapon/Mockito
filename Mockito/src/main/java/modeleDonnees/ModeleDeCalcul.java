// défini des opérations avec argument gauche, opérateur, argument droite
// défini des opérations avec argument gauche, opérateur, argument droite et resultat
//dans 2 constructeurs différents
//les operateurs sont récupéré dans l'enum operateur

package modeleDonnees;

import bean.Operateur;

public class ModeleDeCalcul {

    private static final String SEPARATEUR = " ";
    private Integer             argumentGauche;
    private Integer             argumentDroite;
    private Operateur           operateur;
    private Integer             resultat;

    public ModeleDeCalcul( Operateur operateur, Integer argumentGauche, Integer argumentDroite ) {
        this.operateur = operateur;
        this.argumentGauche = argumentGauche;
        this.argumentDroite = argumentDroite;
    }

    public ModeleDeCalcul( Operateur operateur, Integer argumentGauche, Integer argumentDroite, Integer resultat ) {
        this.operateur = operateur;
        this.argumentGauche = argumentGauche;
        this.argumentDroite = argumentDroite;
        this.resultat = resultat;
    }

    // cette méthode est static car elle sert à créer un modele de calcul donc
    // l'objet ici sans instanciation
    // ce qui le cas contraire ferait perdre l'intérêt de cette méthode
    public static ModeleDeCalcul creerUnModeleDeCalculAPartirDuneOperationSaisieEnString( String operationSaisieEnString ) {

        String[] partiesDeLOperation = operationSaisieEnString.split( SEPARATEUR );
        Integer argumentGauche = Integer.parseInt( partiesDeLOperation[0] );
        Operateur operateur = Operateur.retourneOperateur( partiesDeLOperation[1] );
        Integer argumentDroite = Integer.parseInt( partiesDeLOperation[2] );
        return new ModeleDeCalcul( operateur, argumentGauche, argumentDroite );
    }

    public Integer getArgumentGauche() {
        return argumentGauche;
    }

    public void setArgumentGauche( Integer argumentGauche ) {
        this.argumentGauche = argumentGauche;
    }

    public Integer getArgumentDroite() {
        return argumentDroite;
    }

    public void setArgumentDroite( Integer argumentDroite ) {
        this.argumentDroite = argumentDroite;
    }

    public Operateur getOperateur() {
        return operateur;
    }

    public void setOperateur( Operateur operateur ) {
        this.operateur = operateur;
    }

    public Integer getResultat() {
        return resultat;
    }

    public void setResultat( Integer resultat ) {
        this.resultat = resultat;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "argument gauche : " + this.argumentGauche
                + " operateur " + this.operateur
                + " argument droite " + this.argumentDroite
                + " resultat "
                + this.resultat;

    }

}
