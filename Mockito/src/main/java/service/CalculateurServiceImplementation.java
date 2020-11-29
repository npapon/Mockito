// à partir d'un modele de calcul sans resultat, retourne un modele de calcul avec resultat
//(nos 2 constructeurs de modele de calcul
package service;

import bean.Calculateur;
import bean.Operateur;
import modeleDonnees.ModeleDeCalcul;

public class CalculateurServiceImplementation implements CalculateurService {

    private final Calculateur calculateur;

    public CalculateurServiceImplementation( Calculateur calculateur ) {
        this.calculateur = calculateur;
    }

    @Override
    public ModeleDeCalcul retourneModeleDeCalculAvecSolution( ModeleDeCalcul modeleDeCalcul ) {

        Operateur operateur = modeleDeCalcul.getOperateur();

        switch ( operateur ) {
        case ADDITION:
            modeleDeCalcul
                    .setResultat( calculateur.add( modeleDeCalcul.getArgumentGauche(), modeleDeCalcul.getArgumentDroite() ) );
            break;
        case SOUSTRACTION:
            modeleDeCalcul
                    .setResultat( calculateur.sub( modeleDeCalcul.getArgumentGauche(), modeleDeCalcul.getArgumentDroite() ) );
            break;
        case DIVISION:
            modeleDeCalcul
                    .setResultat( calculateur.divide( modeleDeCalcul.getArgumentGauche(), modeleDeCalcul.getArgumentDroite() ) );
            break;
        case MULTIPLICATION:
            modeleDeCalcul
                    .setResultat(
                            calculateur.multiply( modeleDeCalcul.getArgumentGauche(), modeleDeCalcul.getArgumentDroite() ) );
            break;

        default:
            throw new UnsupportedOperationException();
        }

        return modeleDeCalcul;

    }

}
