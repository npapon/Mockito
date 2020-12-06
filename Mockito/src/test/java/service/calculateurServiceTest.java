package service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import bean.Calculateur;
import bean.Operateur;
import modeleDonnees.ModeleDeCalcul;

@ExtendWith( MockitoExtension.class )
class calculateurServiceTest {

    /*
     * Calculateur calculateur = new Calculateur(); // CalculateurService
     * calculateurService = new CalculateurServiceImplementation( calculateur );
     */

    @Mock
    Calculateur        calculateur;
    CalculateurService calculateurService;
    @Mock
    ResultatFormateur  resultatFormateur;

    @BeforeEach
    public void init() {
        calculateurService = new CalculateurServiceImplementation( calculateur, resultatFormateur );
    }
    /*
     * @Test void testretourneModeleDeCalculAvecSolution() { ModeleDeCalcul
     * modeleDeCalculSansSolution = ModeleDeCalcul
     * .creerUnModeleDeCalculAPartirDuneOperationSaisieEnString( "6 + 5" );
     * 
     * assertEquals( calculateurService.retourneModeleDeCalculAvecSolution(
     * modeleDeCalculSansSolution ).getResultat(), new ModeleDeCalcul(
     * Operateur.ADDITION, 6, 5, 11 ).getResultat() );
     * 
     * }
     */

    @Test
    public void retourneModeleDeCalculAvecSolution_doitUtiliserLeCalculateur_pourFaireUneAddition() {
        // ETANT DONNE (GIVEN)
        // when et then return sont des méthodes de mockito qui permettent de
        // dire dans un cas précis ce que doit retourner la méthode add de
        // Calculateur sans l'intelligence derriere

        when( calculateur.add( 6, 5 ) ).thenReturn( 11 );

        // QUAND (WHEN)
        ModeleDeCalcul modeleDeCalculSansSolution = new ModeleDeCalcul( Operateur.ADDITION, 6, 5 );
        // calculateurService.retourneModeleDeCalculAvecSolution va utiliser
        // add(6,5) de calculateur du mock defini plus haut
        int solution = calculateurService.retourneModeleDeCalculAvecSolution( modeleDeCalculSansSolution ).getResultat();

        // ALORS (THEN)

        // vérifie sert juste à vérifier que le mock est utilisé dans la
        // simulation de la méthode avec les arguments 6 et 5
        verify( calculateur ).add( 6, 5 );
        assertThat( solution ).isEqualTo( 11 );

    }

    @Test
    public void retourneModeleDeCalculAvecSolution_doitFormerLeResultat_quandOnFaitUneAddition() {
        // GIVEN
        // ca change pas ca, l'addition fait toujours 13000
        when( calculateur.add( 10000, 3000 ) ).thenReturn( 13000 );
        when( resultatFormateur.formatage( 13000 ) ).thenReturn( "13 000" );
        ModeleDeCalcul modeleDeCalculSansSolution = new ModeleDeCalcul( Operateur.ADDITION, 10000, 3000 );

        // WHEN

        final String resultatFormate = calculateurService.retourneModeleDeCalculAvecSolution( modeleDeCalculSansSolution )
                .getResultatFormate();
        // THEN

        resultatFormate.equals( "13 000" );

    }
}
