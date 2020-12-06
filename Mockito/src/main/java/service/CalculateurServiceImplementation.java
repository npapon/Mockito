// à partir d'un modele de calcul sans resultat, retourne un modele de calcul avec resultat
//(nos 2 constructeurs de modele de calcul
package service;

import bean.Calculateur;
import bean.Operateur;
import modeleDonnees.ModeleDeCalcul;

public class CalculateurServiceImplementation implements CalculateurService {

    private final Calculateur       calculateur;
    private final ResultatFormateur resultatFormateur;

    public CalculateurServiceImplementation( Calculateur calculateur, ResultatFormateur resultatFormateur ) {
        this.calculateur = calculateur;
        this.resultatFormateur = resultatFormateur;
    }

    @Override
    public ModeleDeCalcul retourneModeleDeCalculAvecSolution( ModeleDeCalcul modeleDeCalcul ) {

        Operateur operateur = modeleDeCalcul.getOperateur();

        switch ( operateur ) {
        case ADDITION:
            modeleDeCalcul
                    .setResultat( calculateur.add( modeleDeCalcul.getArgumentGauche(), modeleDeCalcul.getArgumentDroite() ) );
            // on va mettre dans l'attribut resultatFormate de ModeleDeCalcul le
            // resultat passé à la moulinette du formattage grace à l'interface
            // resultatFormateur
            modeleDeCalcul.setResultatFormate( resultatFormateur.formatage( modeleDeCalcul.getResultat() ) );

            break;
        case SOUSTRACTION:
            modeleDeCalcul
                    .setResultat( calculateur.sub( modeleDeCalcul.getArgumentGauche(), modeleDeCalcul.getArgumentDroite() ) );
            modeleDeCalcul.setResultatFormate( resultatFormateur.formatage( modeleDeCalcul.getResultat() ) );
            break;
        case DIVISION:
            modeleDeCalcul
                    .setResultat( calculateur.divide( modeleDeCalcul.getArgumentGauche(), modeleDeCalcul.getArgumentDroite() ) );
            modeleDeCalcul.setResultatFormate( resultatFormateur.formatage( modeleDeCalcul.getResultat() ) );
            break;
        case MULTIPLICATION:
            modeleDeCalcul
                    .setResultat(
                            calculateur.multiply( modeleDeCalcul.getArgumentGauche(), modeleDeCalcul.getArgumentDroite() ) );
            modeleDeCalcul.setResultatFormate( resultatFormateur.formatage( modeleDeCalcul.getResultat() ) );
            break;

        default:
            throw new UnsupportedOperationException();
        }

        return modeleDeCalcul;

    }

}
