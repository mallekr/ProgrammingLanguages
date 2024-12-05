public class Term{
    private String name;
    private String op;

    public Term(){

    }

    public Term(Token t){
        this.name = t.getValue();
        this.op = t.typeString();
    }

    public String getOp(){
        return op;
    }
}