package service;

import modeleDonnees.ModeleDeCalcul;

public interface CalculateurService {

    // prend un modele de calcul en paramétre et retourne un modele de calcul
    // avec résultat en retour
    public ModeleDeCalcul retourneModeleDeCalculAvecSolution( ModeleDeCalcul modeleDeCalcul );

}
