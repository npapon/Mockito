package service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import bean.Calculateur;
import modeleDonnees.ModeleDeCalcul;

@ExtendWith( MockitoExtension.class )
class batchCalculateurServiceAvecMockito {

    BatchCalculateurService batchCalculateurService;
    @Mock
    Calculateur             calculateur;

    CalculateurService      calculateurService;
    @Mock
    ResultatFormateur       resultatFormateur;

    @BeforeEach
    public void init() {

        calculateurService = new CalculateurServiceImplementation( calculateur, resultatFormateur );
        batchCalculateurService = new BatchCalculateurServiceImplementation( calculateurService );
    }

    @Test
    void EtantDonneeLeStreamDoperationsSuivant_QuandJeLuiPasseLeBatchDeCalcule_JobientLaListeDeResultatSuivant() {

        // GIVEN (PARAMETRE DU PROBLEME)

        when( calculateur.add( 2, 2 ) ).thenReturn( 4 );
        when( calculateur.sub( 5, 4 ) ).thenReturn( 1 );
        when( calculateur.multiply( 6, 2 ) ).thenReturn( 12 );
        when( calculateur.divide( 10, 2 ) ).thenReturn( 5 );

        List<String> listOperationsEnString = Arrays.asList( "2 + 2", "5 - 4", "6 * 2", "10 / 2" );

        Stream<String> streamOperations = listOperationsEnString.stream();

        // WHEN (QUAND ON FAIT L OPERATION)
        List<ModeleDeCalcul> modelesDeCalculAvecResultat = batchCalculateurService.batchCalcul( streamOperations );

        // THEN

        // sans AssertJ
        System.out.println( modelesDeCalculAvecResultat );
        List<Integer> solutionsModelesDeCalculAvecResultat = new ArrayList<Integer>();
        for ( ModeleDeCalcul modeleDeCalcul : modelesDeCalculAvecResultat ) {
            System.out.println( modeleDeCalcul );
            // A AUCUN MOMENT ON PEUT APPLIQUE LE WHEN SUR LA METHODE FORMATAGE
            // SUR MODELEDECALCUL GETRESULTAT
            resultatFormateur.formatage( modeleDeCalcul.getResultat() );
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

        verify( calculateur ).add( 2, 2 );

        verify( calculateur ).sub( 5, 4 );
        verify( calculateur ).multiply( 6, 2 );
        verify( calculateur ).divide( 10, 2 );

    }

}
