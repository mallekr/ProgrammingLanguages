import java.util.LinkedList;

public class Expression {
    private LinkedList<Token> exp;
    private String name;
    private String type;
    private String kind;
    private String expKind;
    private Token peek;
    private LinkedList<Term> terms = new LinkedList<Term>();


    public Expression(){

    }

    public Expression(LinkedList<Token> exp) throws Exception {
        this.exp = exp;
        peek = exp.getFirst();
        name = peek.getValue();
        kind = peek.typeString();
        if(peek.typeString().toLowerCase().equals("id")){
            nextToken();
            if(peek.typeString().toLowerCase().equals("assignment")){
                nextToken();
            }
            else{
                error("Expected assignment.");
            }
        }
        else{
            error("Expected ID.");
        }
        start();
    }

    
    
    private void start() throws Exception{   
        Term term = vari();
        
        String peekS = peek.typeString().toLowerCase();
        if(peekS != "endline"){
            error("Expected end of line.");
        }
        else{
            this.type = checktype();
            System.out.println("Parsed successfully");
        }
    }

    private Token vari() throws Exception {
        //plusmin();
        Token t = plusmin();
        return t;
    }

    private Token plusmin() throws Exception {
        Token t = multdiv();
        String test = peek.getValue();
        while(test.equals("+") || test.equals("-")){
            return peek;
        }
    }

    private Token multdiv() throws Exception {
        Term term = value();
        String test = peek.getValue();
        while(test.equals("*") || test.equals("/")){
            return term;
        }
    }

    private Term value() throws Exception {
        String test = peek.typeString().toLowerCase();
        if(test.equals("integer") || test.equals("decimal")){
            Term term = peek;
            plusmin();
        }
        else{
            error("Unexpected type.");
        }
    }

    private String checktype(){
        for(int i = 0; i < terms.size(); i++){
            String test = terms.get(i).getOp().toLowerCase();
            if(test == "decimal"){
                return "Decimal";
            }
        }
        return "Integer";
    }
    /*
    private void start() throws Exception {
        if(peek.typeString().toLowerCase() == "id"){
            nextToken();
            if(peek.typeString().toLowerCase() == "assignment"){
                nextToken();
                runthrough();
            }
            else{
                error("Expected assignment.");
            }
        }
        else{
            error("Expected ID.");
        }
    }

    
    private void runthrough() throws Exception {
        String test = peek.typeString().toLowerCase();
        if(test != "integer" || test != "decimal"){
            error("Unexpected value after assignment operator.");
        }
        else{
            terms.add(new Term(exp.pop()));
            addsub();
        }
    }

    private void addsub(){
        String plusmin = peek.getValue();
        if(plusmin == "+" || plusmin == "-"){

        }
    }
    */
    private void nextToken(){
        exp.pop();
        if(exp.getFirst().typeString().toLowerCase().equals("endline")){
            exp.pop();
        }
        else{
            peek = exp.getFirst();
        }
    }

    public static void error(String e) throws Exception {
        throw new Exception(e);
        //System.exit(1);
    }
}