package bean;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.function.Consumer;

public class StatisticsCalculateur {

    private final IntSummaryStatistics intSummaryStatistics;

    public StatisticsCalculateur( IntSummaryStatistics intSummaryStatistics ) {
        this.intSummaryStatistics = intSummaryStatistics;
    }

    // on a le accept du forEach et le accept de IntSummaryStatistics
    public Integer moyenne( List<Integer> listNombre ) {
        listNombre.forEach( new Consumer<Integer>() {

            @Override
            public void accept( Integer nombre ) {
                intSummaryStatistics.accept( nombre );

            }
        } );

        Double moyenneDesNombresDeLaList = intSummaryStatistics.getAverage();
        // intValue ramène la partie entière d'un double

        return moyenneDesNombresDeLaList.intValue();
    }

    public Integer max( List<Integer> listNombre ) {
        listNombre.forEach( ( nombre ) -> intSummaryStatistics.accept( nombre ) );

        Integer maxDesNombresDeLaList = intSummaryStatistics.getMax();

        return maxDesNombresDeLaList;

    }

    public Integer min( List<Integer> listNombre ) {
        listNombre.forEach( intSummaryStatistics::accept );

        Integer maxDesNombresDeLaList = intSummaryStatistics.getMin();

        return maxDesNombresDeLaList;

    }

}
