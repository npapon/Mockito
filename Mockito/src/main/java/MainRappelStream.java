import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainRappelStream {

    public static void main( String[] args ) {
        System.out.println(
                "RAPPEL SUR LES STREAM : FILTER permet de filtrer le stream, MAP permet de faire une opération sur tous les éléments de la liste" );
        List<String> list = new ArrayList<String>();

        list.add( "Nico Papon" );
        list.add( "Leslie Papon" );
        list.add( "Nicolas Chenu" );

        System.out.println( "stream" );
        Stream<String> stream = list.stream();
        stream.forEach( new Consumer<String>() {

            @Override
            public void accept( String personne ) {
                System.out.println( personne );

            }

        } );

        System.out.println( "\n" );
        System.out.println( "streambis : stream filtré" );
        Stream<String> streambis = list.stream();

        streambis.filter( new Predicate<String>() {

            @Override
            public boolean test( String personne ) {
                if ( personne.contains( "Papon" ) ) {
                    return false;
                } else {
                    return true;
                }
            }
        } ).forEach( new Consumer<String>() {

            @Override
            public void accept( String personne ) {
                System.out.println( personne );

            }

        } );

        System.out.println( "\n" );
        System.out.println( "streamplus : stream avec map" );
        Stream<String> streamplus = list.stream();

        streamplus.map( new Function<String, String>() {

            @Override
            public String apply( String personne ) {
                return personne.toUpperCase();
            }
        } ).forEach( new Consumer<String>() {

            @Override
            public void accept( String personne ) {
                System.out.println( personne );

            }

        } );

        System.out.println( "\n" );
        System.out.println( "stream2" );
        Stream<String> stream2 = list.stream();

        stream2.forEach( ( personne ) -> System.out.println( personne ) );
        System.out.println( "\n" );
        System.out.println( "stream2bis filtré" );
        Stream<String> stream2bis = list.stream();
        stream2bis.filter( ( personne ) -> {
            if ( personne.contains( "Papon" ) ) {
                return false;
            } else {
                return true;
            }
        } ).forEach( ( personne ) -> System.out.println( personne ) );
        ;
        System.out.println( "\n" );
        System.out.println( "stream2plus : stream avec map" );
        Stream<String> stream2plus = list.stream();
        stream2plus.map( personne -> personne.toUpperCase() ).forEach( personne -> System.out.println( personne ) );

        System.out.println( "\n" );
        System.out.println( "stream3" );
        Stream<String> stream3 = list.stream();
        stream3.forEach( System.out::println );

        System.out.println(
                "La méthode collect(format de sortie) permet de transformer le stream en format de sortie : list, set ..." );

        Stream<String> stream4 = list.stream();
        Set<String> set = stream4.map( ( personne ) -> personne.toUpperCase() ).collect( Collectors.toSet() );

        System.out.println( set );

    }

}
