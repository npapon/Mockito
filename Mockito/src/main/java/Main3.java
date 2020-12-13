import java.util.Arrays;
import java.util.IntSummaryStatistics;

import bean.StatisticsCalculateur;

public class Main3 {

    public static void main( String[] args ) {
        // TODO Auto-generated method stub
        StatisticsCalculateur statisticsCalculateur = new StatisticsCalculateur( new IntSummaryStatistics() );

        System.out.println( statisticsCalculateur.moyenne( Arrays.asList( 3, 3, 4 ) ) );

        Double unDouble = 20.32;
        System.out.println( unDouble.intValue() );

        System.out.println( statisticsCalculateur.max( Arrays.asList( 3, 3, 4 ) ) );

        System.out.println( statisticsCalculateur.min( Arrays.asList( 3, 3, 4 ) ) );
    }
}
