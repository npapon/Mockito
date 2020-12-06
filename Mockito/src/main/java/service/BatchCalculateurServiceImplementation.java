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
        // ici on à un stream d'opération avec des String
        // pour chaque élément du stream, map applique la méthode
        // retourneModeleDeCalculAvecSolution qui va pour chaque opération créé
        // un modeleDeCalculAvecSolution
        // collect transforme enfin le stream en une list / map / array ...
        return operationsEcritesEnString.map( operationEcriteEnString -> calculateurService.retourneModeleDeCalculAvecSolution(
                ModeleDeCalcul.creerUnModeleDeCalculAPartirDuneOperationSaisieEnString( operationEcriteEnString ) ) )
                .collect( Collectors.toList() );
    }

}
