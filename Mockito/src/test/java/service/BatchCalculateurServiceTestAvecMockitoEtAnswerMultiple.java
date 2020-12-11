package service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import bean.Operateur;
import modeleDonnees.ModeleDeCalcul;

@ExtendWith( MockitoExtension.class )
class BatchCalculateurServiceTestAvecMockitoEtAnswerMultiple {

    BatchCalculateurService batchCalculateurService;

    @Mock
    CalculateurService      calculateurService;

    @BeforeEach
    public void init() {

        batchCalculateurService = new BatchCalculateurServiceImplementation( calculateurService );
    }

    @Test
    void EtantDonneeLeStreamDoperationsSuivant_QuandJeLuiPasseLeBatchDeCalcule_JobientLaListeDeResultatSuivant() {

        // 1. GIVEN

        List<String> listOperationsEnString = Arrays.asList( "2 + 2", "5 - 4", "6 * 2", "10 / 2" );

        Stream<String> streamOperations = listOperationsEnString.stream();

        when( calculateurService.retourneModeleDeCalculAvecSolution( any( ModeleDeCalcul.class ) ) )
                .thenReturn( new ModeleDeCalcul( Operateur.ADDITION, 2, 2, 4, "4" ) )
                .thenReturn( new ModeleDeCalcul( Operateur.SOUSTRACTION, 5, 4, 1, "1" ) )
                .thenReturn( new ModeleDeCalcul( Operateur.MULTIPLICATION, 6, 2, 12, "12" ) )
                .thenReturn( new ModeleDeCalcul( Operateur.DIVISION, 10, 2, 5, "5" ) );

        // ou en lambda

        // 2. WHEN

        final List<ModeleDeCalcul> listeModelesCalculAvecResultat = batchCalculateurService.batchCalcul( streamOperations );
        System.out.println( listeModelesCalculAvecResultat );

        // 3. THEN
        verify( calculateurService, times( 4 ) ).retourneModeleDeCalculAvecSolution( any( ModeleDeCalcul.class ) );
        assertThat( listeModelesCalculAvecResultat ).extracting( new Function<ModeleDeCalcul, Integer>() {

            @Override
            public Integer apply( ModeleDeCalcul modeleDeCalcul ) {
                // TODO Auto-generated method stub
                return modeleDeCalcul.getResultat();
            }
        } ).containsExactly( 4, 1, 12, 5 );

    }

}
