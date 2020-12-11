package service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import modeleDonnees.ModeleDeCalcul;

@ExtendWith( MockitoExtension.class )
class Help {

    BatchCalculateurService batchCalculateurService;

    @Mock
    CalculateurService      calculateurService;

    @BeforeEach
    public void init() {

        batchCalculateurService = new BatchCalculateurServiceImplementation( calculateurService );
    }

    @Test
    public void givenOperationsList_whenbatchCalculate_thenCallsServiceAndReturnsAnswer()
            throws IOException, URISyntaxException {
        // GIVEN
        final Stream<String> operations = Arrays.asList( "2 + 2", "5 - 4", "6 * 2", "10 / 2" ).stream();
        when( calculateurService.retourneModeleDeCalculAvecSolution( any( ModeleDeCalcul.class ) ) )
                .then( invocation -> {
                    final ModeleDeCalcul modeleDeCalcul = invocation.getArgument( 0, ModeleDeCalcul.class );
                    switch ( modeleDeCalcul.getOperateur() ) {
                    case ADDITION:
                        modeleDeCalcul.setResultat( 4 );
                        modeleDeCalcul.setResultatFormate( "4" );
                        break;
                    case SOUSTRACTION:
                        modeleDeCalcul.setResultat( 1 );
                        modeleDeCalcul.setResultatFormate( "1" );
                        break;
                    case MULTIPLICATION:
                        modeleDeCalcul.setResultat( 12 );
                        modeleDeCalcul.setResultatFormate( "12" );
                        break;
                    case DIVISION:
                        modeleDeCalcul.setResultat( 5 );
                        modeleDeCalcul.setResultatFormate( "5" );
                        break;
                    default:

                    }
                    return modeleDeCalcul;
                } );

        // WHEN
        final List<ModeleDeCalcul> results = batchCalculateurService.batchCalcul( operations );

        // THEN
        verify( calculateurService, times( 4 ) ).retourneModeleDeCalculAvecSolution( any( ModeleDeCalcul.class ) );

    }

}
