package service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import bean.Operateur;
import modeleDonnees.ModeleDeCalcul;

@ExtendWith( MockitoExtension.class )
class BatchCalculateurServiceAvecMockitoQuiMarche {

    BatchCalculateurService batchCalculateurService;
    /*
     * Calculateur calculateur; ResultatFormateur resultatFormateur;
     */
    @Mock
    CalculateurService      calculateurService;

    @BeforeEach
    public void init() {
        /*
         * calculateur = new Calculateur(); resultatFormateur = new
         * ResultatFormateurImplementation(); calculateurService = new
         * CalculateurServiceImplementation( calculateur, resultatFormateur );
         */
        batchCalculateurService = new BatchCalculateurServiceImplementation( calculateurService );
    }

    @Test
    void EtantDonneeLeStreamDoperationsSuivant_QuandJeLuiPasseLeBatchDeCalcule_JobientLaListeDeResultatSuivant() {

        // GIVEN (PARAMETRE DU PROBLEME)
        // ArgumentCaptor. Son rôle est d'enregistrer les arguments utilisés
        // lors d'un appel de mock.
        // Ici, on veut capturer les modèles de calcul
        // en gros, il faut quand meme avoir fait l'interface CalculateurService
        // et sa méthode
        // public ModeleDeCalcul retourneModeleDeCalculAvecSolution(
        // ModeleDeCalcul modeleDeCalcul );
        // qui prend en argument un modele de calcul
        // ce sont ces arguments qu'on va récupérer
        // car la méthode batch qu'on teste utilise la méthode mockée
        // retourneModeleDeCalculAvecSolution de calculateurService
        // qui prend des ModeleDeCalcul en param contruit à partir des
        // opérations en string (donc on les a !!!) via
        // ModeleDeCalcul.creerUnModeleDeCalculAPartirDuneOperationSaisieEnString(
        // operationEcriteEnString ) de
        // calculateurService.retourneModeleDeCalculAvecSolution(
        // ModeleDeCalcul.creerUnModeleDeCalculAPartirDuneOperationSaisieEnString(
        // operationEcriteEnString )

        // EN GROS CA SERT JUSTE A CAPTER DES QU UN MODELE DE CALCUL EST PASSE
        // EN ARGUMENT D UNE METHOD
        // CE QUI EST LE CAS ICI DANS LA METHODE ModeleDeCalcul
        // retourneModeleDeCalculAvecSolution(
        // ModeleDeCalcul modeleDeCalcul )
        // APPELER DANS BATCHCALCULATEURSERVICE (MEME SI C EST VIA LE MOCK)
        final ArgumentCaptor<ModeleDeCalcul> argumentCaptorModeleDeCalcul = ArgumentCaptor.forClass( ModeleDeCalcul.class );
        List<String> listOperationsEnString = Arrays.asList( "2 + 2", "5 - 4", "6 * 2", "10 / 2" );

        Stream<String> streamOperations = listOperationsEnString.stream();

        // WHEN (QUAND ON FAIT L OPERATION)
        batchCalculateurService.batchCalcul( streamOperations );

        // THEN
        // On en profite pour vérifier que la méthode
        // retourneModeleCalculAvecSolution de la classe CalculateurService a
        // bien
        // été utilisée 4 fois avec les modeles de calculs capturées

        verify( calculateurService, times( 4 ) ).retourneModeleDeCalculAvecSolution( argumentCaptorModeleDeCalcul.capture() );

        // on met ces modeles de calculs dans une liste via cette methode
        final List<ModeleDeCalcul> modelesDeCalcul = argumentCaptorModeleDeCalcul.getAllValues();

        // et la on verifie que les modeles de calcul passé en paramètre
        // contiennent bien les infos en paramètres dans l'ordre grace à
        // la méthode extracting qui récupère les arguments
        // de les modeles de calcul de la liste
        // et avec containsExactly et tupple qui permet juste de mettre
        // plusieurs arguments on vérifie que ca matche
        // on teste pas la solution car on l'a pas calculé comme on met un mock
        // sur CalculateurService
        // apres j'aurai pu tenter avec un when pour récupérer le résultat ainsi
        // mais cela va être l'option prise dans le prochain tuto

        for ( ModeleDeCalcul modeleDeCalcul : modelesDeCalcul ) {

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
            }

        }
        System.out.println( modelesDeCalcul );

        assertThat( modelesDeCalcul ).extracting( new Function<ModeleDeCalcul, Integer>() {

            @Override
            public Integer apply( ModeleDeCalcul modeleDeCalcul ) {

                return modeleDeCalcul.getArgumentGauche();
            }
        },
                new Function<ModeleDeCalcul, Operateur>() {

                    @Override
                    public Operateur apply( ModeleDeCalcul modeleDeCalcul ) {

                        return modeleDeCalcul.getOperateur();
                    }
                },
                new Function<ModeleDeCalcul, Integer>() {

                    @Override
                    public Integer apply( ModeleDeCalcul modeleDeCalcul ) {

                        return modeleDeCalcul.getArgumentDroite();
                    }
                }

        ).containsExactly( tuple( 2, Operateur.ADDITION, 2 ), tuple( 5, Operateur.SOUSTRACTION, 4 ),
                tuple( 6, Operateur.MULTIPLICATION, 2 ), tuple( 10, Operateur.DIVISION, 2 ) );

        // avec lambda

        assertThat( modelesDeCalcul ).extracting(
                ( ModeleDeCalcul modeleDeCalcul ) -> modeleDeCalcul.getArgumentGauche(),

                ( ModeleDeCalcul modeleDeCalcul ) -> modeleDeCalcul.getOperateur(),
                ( ModeleDeCalcul modeleDeCalcul ) -> modeleDeCalcul.getArgumentDroite()

        ).containsExactly( tuple( 2, Operateur.ADDITION, 2 ), tuple( 5, Operateur.SOUSTRACTION, 4 ),
                tuple( 6, Operateur.MULTIPLICATION, 2 ), tuple( 10, Operateur.DIVISION, 2 ) );

        // avec référence de méthode

        assertThat( modelesDeCalcul ).extracting(
                ModeleDeCalcul::getArgumentGauche,
                ModeleDeCalcul::getOperateur,
                ModeleDeCalcul::getArgumentDroite

        ).containsExactly( tuple( 2, Operateur.ADDITION, 2 ), tuple( 5, Operateur.SOUSTRACTION, 4 ),
                tuple( 6, Operateur.MULTIPLICATION, 2 ), tuple( 10, Operateur.DIVISION, 2 ) );

    }

}
