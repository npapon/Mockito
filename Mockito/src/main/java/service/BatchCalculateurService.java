package service;

import java.util.List;
import java.util.stream.Stream;

import modeleDonnees.ModeleDeCalcul;

public interface BatchCalculateurService {

    // Retourne une liste de modele de calcul avec resultat

    public List<ModeleDeCalcul> batchCalcul( Stream<String> operations );

}