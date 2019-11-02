/*
*   Lookup table for variables and tokens
*
*
*/


public class LookupTable{
    private String name;
    private String type;
    private String value;

    public LookupTable(){

    }

    public LookupTable(Token t){
        this.name = t.getValue();
        this.value = t.typeString();
        
    }

}