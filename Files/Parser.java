/*Parser for my compiler.


*/

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

//import Token.tType;

public class Parser {
    private LinkedList<Token> parseList;
    private LinkedList<Token> pLine = new LinkedList<Token>();
    private Token getKeyword;
    private Token work;
    private Map<String, String[]> table = new HashMap<String, String[]>();

    public Parser() {

    }

    public Parser(LinkedList<Token> list) throws Exception {
        parseList = (LinkedList<Token>) list.clone();
        while(!parseList.isEmpty()){
            getKeyword = parseList.getFirst();
            if (getKeyword.typeString() != "Keyword"){
                error("Expetected Keyword");
            }
            else{
                getKeyword = parseList.peekFirst();
                while(getKeyword.typeString() != "Endline"){
                    getKeyword = parseList.pop();
                    pLine.add(getKeyword);    
                }
            }
            System.out.println(pLine);
            checker(pLine);
            pLine.clear();
        }
    }

    private void checker(LinkedList<Token> check) throws Exception {
        work = check.getFirst();
        Token last = check.getLast();
        if(last.typeString().toLowerCase() != "endline"){
            error("Expected ; at end of line.");
        }

        if (work.typeString() == "def") {
            define(check);
        } 
        else if (work.typeString() == "redef") {

        } 
        else if (work.typeString() == "print") {

        } 
        else {
            error("Invalid keyword.");
        }

        // return null;
    }

    private void define(LinkedList<Token> define) throws Exception {
        String name = define.peekFirst().getValue();
        String type = "ID";
        String value = define.peekFirst().getValue();
        table.forEach((k, v) -> {
            if (k == name) {
                try {
                    error("" + name + " already exists.");
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
            else{
                String[] in = {type, value};
                table.put(name, in);
            }
        });
    }

    private void redefine(LinkedList<Token> re){

    }

    public static void error(String e) throws Exception {
        throw new Exception(e);
        //System.exit(1);
    }

}