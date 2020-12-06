package service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bean.Calculateur;
import modeleDonnees.ModeleDeCalcul;

class batchCalculateurServiceSansMockito {

    BatchCalculateurService batchCalculateurService;

    @BeforeEach
    public void init() {
        Calculateur calculateur = new Calculateur();
        ResultatFormateur resultatFormateur = new ResultatFormateurImplementation();
        CalculateurService calculateurService = new CalculateurServiceImplementation( calculateur, resultatFormateur );
        batchCalculateurService = new BatchCalculateurServiceImplementation( calculateurService );
    }

    @Test
    void EtantDonneeLeStreamDoperationsSuivant_QuandJeLuiPasseLeBatchDeCalcule_JobientLaListeDeResultatSuivant() {

        // GIVEN (PARAMETRE DU PROBLEME)
        List<String> listOperationsEnString = Arrays.asList( "2 + 2", "5 - 4", "6 * 2", "10 / 2" );

        Stream<String> streamOperations = listOperationsEnString.stream();

        // WHEN (QUAND ON FAIT L OPERATION)
        List<ModeleDeCalcul> modelesDeCalculAvecResultat = batchCalculateurService.batchCalcul( streamOperations );
        // THEN

        // sans AssertJ
        List<Integer> solutionsModelesDeCalculAvecResultat = new ArrayList<Integer>();
        for ( ModeleDeCalcul modeleDeCalcul : modelesDeCalculAvecResultat ) {
            solutionsModelesDeCalculAvecResultat.add( modeleDeCalcul.getResultat() );
        }

        List<Integer> collectionDeResultat = Arrays.asList( 4, 1, 12, 5 );
        assertTrue( solutionsModelesDeCalculAvecResultat.containsAll( collectionDeResultat ) );

        // avecAssertJ sans lambda

        assertThat( modelesDeCalculAvecResultat ).extracting( new Function<ModeleDeCalcul, Integer>() {

            @Override
            public Integer apply( ModeleDeCalcul ModeleDeCalculAvecResultat ) {
                // TODO Auto-generated method stub
                return ModeleDeCalculAvecResultat.getResultat();
            }
        } ).containsExactly( 4, 1, 12, 5 );

        assertThat( modelesDeCalculAvecResultat )
                .extracting( ( ModeleDeCalcul ModeleDeCalculAvecResultat ) -> ModeleDeCalculAvecResultat.getResultat() )
                .containsExactly( 4, 1, 12, 5 );

        assertThat( modelesDeCalculAvecResultat )
                .extracting( ModeleDeCalcul::getResultat )
                .containsExactly( 4, 1, 12, 5 );

    }

}
