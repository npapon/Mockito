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
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import modeleDonnees.ModeleDeCalcul;

@ExtendWith( MockitoExtension.class )
class BatchCalculateurTestServiceAvecMockitoEtAnswer {

    BatchCalculateurService batchCalculateurService;

    @Mock
    CalculateurService      calculateurService;

    @BeforeEach
    public void init() {

        batchCalculateurService = new BatchCalculateurServiceImplementation( calculateurService );
    }

    @Test
    void EtantDonneeLeStreamDoperationsSuivant_QuandJeLuiPasseLeBatchDeCalcule_JobientLaListeDeResultatSuivant() {

        // 1 WHEN

        // EN GROS CA SERT JUSTE A CAPTER DES QU UN MODELE DE CALCUL EST PASSE
        // EN ARGUMENT D UNE METHODE
        // CE QUI EST LE CAS ICI DANS LA METHODE ModeleDeCalcul
        // retourneModeleDeCalculAvecSolution(
        // ModeleDeCalcul modeleDeCalcul )
        // APPELER DANS BATCHCALCULATEURSERVICE (MEME SI C EST VIA LE MOCK)

        // ON UTILISE NOTRE WHEN :
        // QUAND ON APPELLE LA METHODE
        // calculateurService.retourneModeleDeCalculAvecSolution AVEC N IMPORTE
        // QUEL ARGUMENT
        // CELA RETOUNE

        List<String> listOperationsEnString = Arrays.asList( "2 + 2", "5 - 4", "6 * 2", "10 / 2" );

        Stream<String> streamOperations = listOperationsEnString.stream();

        // InvocationOnMock et sa méthode getArgument permet de récupéré les
        // paramètres du du mock
        // via la méthode getArgument (qui prend en paramètre un index qui je
        // pense
        // correspond à la position du paramètre passé dans le mock qui nous
        // interesse ici
        // celui de la méthode ModeleDeCalcul
        // retourneModeleDeCalculAvecSolution(ModeleDeCalcul modeleDeCalcul )
        // vu que y en a qu'un arguement c'est le 0
        // et le second argument de getArgument et le type du paramétre du mock
        // qu'on veut récupéré (ici un modeleDeClasse)

        // avec when return et la méthode anwer, on retourne des modele de
        // données avec le resultat qui va bien

        when( calculateurService.retourneModeleDeCalculAvecSolution( any( ModeleDeCalcul.class ) ) )
                .then( new Answer<ModeleDeCalcul>() {

                    @Override
                    public ModeleDeCalcul answer( InvocationOnMock invocationOnMock ) throws Throwable {
                        // TODO Auto-generated method stub
                        final ModeleDeCalcul modeleDeCalcul = invocationOnMock.getArgument( 0, ModeleDeCalcul.class );
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
                    }
                } );

        // ou en lambda

        /*
         * when( calculateurService.retourneModeleDeCalculAvecSolution( any(
         * ModeleDeCalcul.class ) ) ) .then( invocationOnMock -> { final
         * ModeleDeCalcul modeleDeCalcul = invocationOnMock.getArgument( 0,
         * ModeleDeCalcul.class ); switch ( modeleDeCalcul.getOperateur() ) {
         * case ADDITION: modeleDeCalcul.setResultat( 4 );
         * modeleDeCalcul.setResultatFormate( "4" ); break; case SOUSTRACTION:
         * modeleDeCalcul.setResultat( 1 ); modeleDeCalcul.setResultatFormate(
         * "1" ); break; case MULTIPLICATION: modeleDeCalcul.setResultat( 12 );
         * modeleDeCalcul.setResultatFormate( "12" ); break; case DIVISION:
         * modeleDeCalcul.setResultat( 5 ); modeleDeCalcul.setResultatFormate(
         * "5" ); break;
         * 
         * } return modeleDeCalcul; } );
         */

        // 2. WHEN (QUAND ON FAIT L OPERATION)

        final List<ModeleDeCalcul> listeModelesCalculAvecResultat = batchCalculateurService.batchCalcul( streamOperations );
        System.out.println( listeModelesCalculAvecResultat );
        // THEN
        // On en profite pour vérifier que la méthode
        // retourneModeleCalculAvecSolution de la classe CalculateurService a
        // bien
        // été utilisée 4 fois avec les modeles de calculs capturées

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
