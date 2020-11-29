package service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import bean.Calculateur;
import bean.Operateur;
import modeleDonnees.ModeleDeCalcul;

class calculateurServiceTest {

    Calculateur        calculateur        = new Calculateur();
    //
    CalculateurService calculateurService = new CalculateurServiceImplementation( calculateur );

    @Test
    void testretourneModeleDeCalculAvecSolution() {
        ModeleDeCalcul modeleDeCalculSansSolution = ModeleDeCalcul
                .creerUnModeleDeCalculAPartirDuneOperationSaisieEnString( "6 + 5" );

        assertEquals( calculateurService.retourneModeleDeCalculAvecSolution( modeleDeCalculSansSolution ).getResultat(),
                new ModeleDeCalcul( Operateur.ADDITION, 6, 5, 11 ).getResultat() );

    }

}
