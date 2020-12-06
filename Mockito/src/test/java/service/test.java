package service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class test {

    BatchCalculateurService batchCalculateurService;

    CalculateurService      calculateurService;

    @BeforeEach
    public void init() {

        batchCalculateurService = new BatchCalculateurServiceImplementation( calculateurService );
    }

    @Test
    void EtantDonneeLeStreamDoperationsSuivant_QuandJeLuiPasseLeBatchDeCalcule_JobientLaListeDeResultatSuivant() {
        batchCalculateurService = new BatchCalculateurServiceImplementation( calculateurService );
        List<String> listOperations = new ArrayList<String>();
        listOperations.add( "6 * 7" );
        listOperations.add( "3 / 2" );
        listOperations.add( "6 - 1" );
        listOperations.add( "2 + 1" );

        Stream<String> operations = listOperations.stream();
        batchCalculateurService.batchCalcul( operations );
    }

}
