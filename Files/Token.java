	/* Token class is needed for you to be able to create token object
	 * you need to have a type and a value for each token.
	 * you need to be able to st and get the types and values of each token
	 * */

//package Lex;

public class Token {
	//KeyWords key;
	
	private String value;
	private String description;
	private tType ttype;

	public static enum tType{
		Comment("?c.*\n"),
		Integer("-?[0-9]+"),
		Decimal("-?[0-9]+.[0-9]+"),
		Operator("[+|-|*|/]"),
		Assignment("="),
		Identifier("[a-z][A-Z]+.*"),
		Whitespace("[\n\t\f\r]+"),
		String("\".*\""),
		Keyword(""),
		EndLine(";");

		public final String pattern;

		private tType(String pattern){
			this.pattern = pattern;
		}

	}
	
	public Token(tType t, String v){ //t is token type, v is token value
		this.ttype = t;
		this.value = v;
		if(v.equals("def")){
			this.description = "Defines a variable.";
		}
		else if(v.equals("print")){
			this.description = "Prints out to the screen.";
		}
		else if(v.equals("redef")){
			this.description = "Re-defines a variable";
		}
	}

	public String typeString(){
		switch(ttype){
			case Comment: return "Comment"; 
			case Integer: return "Integer"; 
			case Decimal: return "Decimal";
			case Assignment: return "Assignment";
			case EndLine: return "Endline";
			case Identifier: return "ID";
			case Operator: return "Operator";
			case String: return "String";
			case Whitespace: return "Whitespace";
			case Keyword: return "Keyword";
			default: return null;
		}
	}
	
	public tType getType(){
		return this.ttype;
	}

	public String getValue(){
		return this.value;
	}

	public String getDesc(){
		return this.description;
	}

	public String toString(){
		if(ttype == Token.tType.Keyword){
			return("Value: " + this.getValue() + "\t\t Type:  " + this.getType() + "\t\t Description:" + this.getDesc());
		}
		return("Value: " + this.getValue() + "\t\t Type:  " + this.getType());
	}
}
