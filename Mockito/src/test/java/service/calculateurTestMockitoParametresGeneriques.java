package service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import bean.Calculateur;
import bean.Operateur;
import modeleDonnees.ModeleDeCalcul;

@ExtendWith( MockitoExtension.class )
class calculateurTestMockitoParametresGeneriques {

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

    @Test
    public void retourneModeleDeCalculAvecSolution_doitUtiliserLeCalculateur_pourFaireUneAddition() {

        Random random = new Random();
        when( calculateur.add( any( Integer.class ), any( Integer.class ) ) ).thenReturn( 11 );
        when( resultatFormateur.formatage( 11 ) ).thenReturn( "11" );
        ModeleDeCalcul modeleDeCalculSansSolution = new ModeleDeCalcul( Operateur.ADDITION, random.nextInt(), random.nextInt() );

        int solution = calculateurService.retourneModeleDeCalculAvecSolution( modeleDeCalculSansSolution ).getResultat();

        verify( calculateur, times( 1 ) ).add( any( Integer.class ), any( Integer.class ) );
        verify( calculateur, times( 0 ) ).sub( any( Integer.class ), any( Integer.class ) );

        verify( calculateur, never() ).divide( any( Integer.class ), any( Integer.class ) );

        assertThat( solution ).isEqualTo( 11 );

    }

    @Test
    public void retourneModeleDeCalculAvecSolution_doitLancerUneIllegalArgumentException_QuandOnDivisePar0() {
        // GIVEN
        when( calculateur.divide( 2, 0 ) ).thenThrow( new ArithmeticException() );
        Random random = new Random();

        // WHEN
        ModeleDeCalcul modeleDeCalculSansSolution = new ModeleDeCalcul( Operateur.DIVISION, 2, 0 );
        // THEN
        assertThrows( ArithmeticException.class,
                () -> calculateurService.retourneModeleDeCalculAvecSolution( modeleDeCalculSansSolution ) );

        // et l'écriture sans lamda
        assertThrows( ArithmeticException.class,
                new Executable() {
                    @Override
                    public void execute() throws Throwable {
                        calculateurService.retourneModeleDeCalculAvecSolution( modeleDeCalculSansSolution );
                    }

                } );

    }
}
