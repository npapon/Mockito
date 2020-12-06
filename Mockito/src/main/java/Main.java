import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import bean.Calculateur;
import bean.Operateur;
import modeleDonnees.ModeleDeCalcul;
import service.BatchCalculateurService;
import service.BatchCalculateurServiceImplementation;
import service.CalculateurService;
import service.CalculateurServiceImplementation;
import service.ResultatFormateur;
import service.ResultatFormateurImplementation;

public class Main {

    public static void main( String[] args ) {
        ModeleDeCalcul modeleDeCalcul = ModeleDeCalcul.creerUnModeleDeCalculAPartirDuneOperationSaisieEnString( "2 * 2" );

        System.out.println( modeleDeCalcul );
        ResultatFormateur resultatFormateur = new ResultatFormateurImplementation();

        Calculateur calculateur = new Calculateur();

        CalculateurService calculateurService = new CalculateurServiceImplementation( calculateur, resultatFormateur );

        ModeleDeCalcul modeleDeCalculAvecResultat = calculateurService
                .retourneModeleDeCalculAvecSolution( modeleDeCalcul );

        System.out.println( modeleDeCalculAvecResultat.toString() );

        ;
        System.out.println( "Resultat sans formatage" );
        System.out.println( modeleDeCalculAvecResultat.getResultat() );

        System.out.println( "Resultat avec formatage" );
        System.out.println( modeleDeCalculAvecResultat.getResultatFormate() );

        ;

        BatchCalculateurService batchCalculateurService = new BatchCalculateurServiceImplementation( calculateurService );

        List<String> listOperations = new ArrayList<String>();
        listOperations.add( "6 * 7" );
        listOperations.add( "3 / 2" );
        listOperations.add( "6 - 1" );
        listOperations.add( "2 + 1" );

        Stream<String> operations = listOperations.stream();

        List<ModeleDeCalcul> modelesDeCalculAvecSolution = batchCalculateurService.batchCalcul( operations );

        Iterator<ModeleDeCalcul> iterator = modelesDeCalculAvecSolution.iterator();
        while ( iterator.hasNext() ) {
            System.out.println( iterator.next().toString() );
        }

        Random random = new Random();
        ModeleDeCalcul modeleDeCalculSansSolution = new ModeleDeCalcul( Operateur.DIVISION, random.nextInt(), 0 );
        // WHEN
        calculateurService.retourneModeleDeCalculAvecSolution( modeleDeCalculSansSolution );

    }

}
