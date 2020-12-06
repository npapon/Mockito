import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import service.BatchCalculateurService;
import service.BatchCalculateurServiceImplementation;
import service.CalculateurService;

public class Main2 {

    public void init() {

    }

    public static void main( String[] args ) {
        // TODO Auto-generated method stub

        BatchCalculateurService batchCalculateurService;

        CalculateurService calculateurService = null;

        batchCalculateurService = new BatchCalculateurServiceImplementation( calculateurService );
        List<String> listOperations = new ArrayList<String>();
        listOperations.add( "6 * 7" );
        listOperations.add( "3 / 2" );
        listOperations.add( "6 - 1" );
        listOperations.add( "2 + 1" );

        Stream<String> operations = listOperations.stream();
        // batchCalculateurService.batchCalcul( operations );

    }

}
