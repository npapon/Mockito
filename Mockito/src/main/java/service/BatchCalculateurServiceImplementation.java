package service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import modeleDonnees.ModeleDeCalcul;

public class BatchCalculateurServiceImplementation implements BatchCalculateurService {

    private final CalculateurService calculateurService;

    public BatchCalculateurServiceImplementation( CalculateurService calculateurService ) {
        this.calculateurService = calculateurService;

    }

    // cette fonction prend en param un stream d'opérations écritures en string
    // et retourne une liste de modeledecalcul avec resultat à partir de ces
    // opérations écrites en string
    @Override
    public List<ModeleDeCalcul> batchCalcul( Stream<String> operationsEcritesEnString ) {

        // map permet d'appliquer des actions sur des éléments d'un stream.
        return operationsEcritesEnString.map( operationEcriteEnString -> calculateurService.retourneModeleDeCalculAvecSolution(
                ModeleDeCalcul.creerUnModeleDeCalculAPartirDuneOperationSaisieEnString( operationEcriteEnString ) ) )
                .collect( Collectors.toList() );
    }

}
