package bean;

public enum Operateur {

    ADDITION( "addition" ), MULTIPLICATION( "multiplication" ), SOUSTRACTION( "soustraction" ), DIVISION( "division" );

    private String commentaire;

    Operateur( String commentaire ) {
        this.commentaire = commentaire;
    }

    public static Operateur retourneOperateur( String operateur ) {
        switch ( operateur ) {
        case "+":
            return ADDITION;
        case "-":
            return SOUSTRACTION;
        case "/":
            return DIVISION;
        case "*":
            return MULTIPLICATION;
        default:
            throw new UnsupportedOperationException( "Operation n'existe pas" );
        }

    }

}
