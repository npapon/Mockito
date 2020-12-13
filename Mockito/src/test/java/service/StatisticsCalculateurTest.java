package service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import bean.StatisticsCalculateur;

@ExtendWith( MockitoExtension.class )
class StatisticsCalculateurTest {

    @Spy
    IntSummaryStatistics  intSummaryStatistics = new IntSummaryStatistics();
    StatisticsCalculateur statisticsCalculateur;

    @BeforeEach
    public void init() {
        statisticsCalculateur = new StatisticsCalculateur( intSummaryStatistics );
    }

    @Test
    public void statisticsCalculateurMoyenne_doitTraiter_tousLesIntegerPassesEnParametre() {
        // GIVEN

        // on va utliser ArgumentCaptor pour snifer les arguements Integer
        // passés dans tous le mock
        // ici c'est ce qui passe dans la méthode intSummaryStatistics.accept
        // appelé dans statisticsCalculateur.moyenne
        final ArgumentCaptor<Integer> capteurDargumentInteger = ArgumentCaptor.forClass( Integer.class );

        List<Integer> list = Arrays.asList( 1, 2, 3, 4 );
        // WHEN
        statisticsCalculateur.moyenne( list );

        // THEN
        verify( intSummaryStatistics, times( list.size() ) ).accept( capteurDargumentInteger.capture() );

        final List<Integer> integerCapture = capteurDargumentInteger.getAllValues();
        assertThat( integerCapture ).containsExactly( 1, 2, 3, 4 );

        // ou toArray qui transforme une liste en un tableau
        // l'argument ici new Integer[0] permet de préciser le type du tabeau
        // retourné
        assertThat( integerCapture ).containsExactly( list.toArray( new Integer[0] ) );
        assertThat( statisticsCalculateur.moyenne( list ) ).isEqualTo( 2 );
    }

}
