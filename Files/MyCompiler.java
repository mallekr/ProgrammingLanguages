import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

//import Lex.Lexer;

/*
 * This is where you run your compiler.
 * you will need to provide your "code" file to the compiler as a string to your lexer
 * you can create any data structure that will contain your Tokens. One way is to use linked lists
 * */



public class MyCompiler {

	public static void main(String[] args) throws Exception {
		Lexer lex = new Lexer();
		Parser parser = new Parser();
		lex.defineKeywords();
		String filename = "H:/Assignment Files/src/Lex/Code.txt";
		// you need to read the input string from your code and extract the tokens here.
		try{
			List<String> lines = Files.readAllLines(Paths.get(filename));
			for(String i:lines){
				lex.lex(i);
			}
		}catch(IOException e){
			e.printStackTrace();
		}
		//Map<String, String> output = lex.defineKeywords();
		//System.out.println(output);
		lex.printLexemes();
		parser = new Parser(lex.getTokens());
	}
}
