package service;

public class ResultatFormateurImplementation implements ResultatFormateur {

    @Override
    public String formatage( int solution ) {
        // TODO Auto-generated method stub
        return "Solution formaté "
                + Integer.toString( solution );
    }

}
