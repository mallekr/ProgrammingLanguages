/*
 * the lexer class is used to extract the tokens (lexemes) from the input String
 * you will define what tokens are valid in your language.
 * you will need some kind of data structure, probably some kind of a list
 * 
 * */
//package Lex;

import java.util.Scanner;

//import Token.tType;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.io.File;

public class Lexer {
	//you need to define the data structure to hold your tokens
	// lex(String s ):   This method is the heart of your lexer, it is where you extract tokens
	// you should be able to process the string and return a list of tokens
	Map<String, String> read = new HashMap<String, String>();
	//private Token token;
	private String integer = ("\\p{Blank}?-?[0-9]+");
	private String decimal = ("\\p{Blank}?-?[0-9]+.[0-9]+");
	private String ID = ("\\p{Blank}?[a-z]+.*|\\p{Blank}?[A-Z]+.*");
	private String string = ("\\p{Blank}?\".*\"");
	private String operator = ("\\p{Blank}?[+|-|*|/]");
	private String assignment = ("\\p{Blank}?=");
	private String comment = ("^\\?.*");
	private String end = ("\\p{Blank}?;");
	private String keyword = ("\\p{Blank}?def|\\p{Blank}?redef|\\p{Blank}?print");
	private LinkedList<Token> tokenList = new LinkedList<Token>();
	
	public void lex(String l){
		
		String[] lineArray = l.split("");	
		String input = "";
		boolean pass = false;
		for(int i = 0; i < lineArray.length; i++){
			input += lineArray[i];
			if(i == lineArray.length-1||lineArray[i+1].equals(" ")||lineArray[i+1].equals(";")){
				if(input.matches(comment)){
					break;
				}
				pass = matchToken(input);
				if(pass){
					input = "";
				}
			}
		}
	}
	//This method creates the individual tokens and puts them into the LinkedList
	public boolean matchToken(String input){
		if(input.matches(keyword)){
			tokenList.add(new Token(Token.tType.Keyword, input.trim()));
			return true;
		}
		else if(input.matches(integer)){
			tokenList.add(new Token(Token.tType.Integer, input.trim()));
			return true;
		}
		else if(input.matches(decimal)){
			tokenList.add(new Token(Token.tType.Decimal, input.trim()));
			return true;
		}
		else if(input.matches(ID)){
			tokenList.add(new Token(Token.tType.Identifier, input.trim()));
			return true;
		}
		else if(input.matches(string)){
			input = input.substring(2,input.length()-1);
			tokenList.add(new Token(Token.tType.String, input.trim()));
			return true;
		}
		else if(input.matches(end)){
			tokenList.add(new Token(Token.tType.EndLine, input.trim()));
			return true;
		}
		else if(input.matches(assignment)){
			tokenList.add(new Token(Token.tType.Assignment, input.trim()));
			return true;
		}
		else if(input.matches(operator)){
			tokenList.add(new Token(Token.tType.Operator, input.trim()));
			return true;
		}
		else if(input.matches(comment)){
			tokenList.add(new Token(Token.tType.Comment, input.trim()));
			return true;
		}
		else return false;
	}
	// This method prints the Tokens (Lexemes), you can use another method if you need to
	public void printLexemes() { 
		for(int i =0; i < tokenList.size(); i++){
			System.out.println(tokenList.get(i));
		}
	}

	public LinkedList<Token> getTokens(){
		return tokenList;
	}

	public Map<String, String> defineKeywords(){
		
		try{
			File code = new File("H:/Assignment Files/src/Lex/keywords.txt");
			Scanner in = new Scanner(code);
			String split[] = new String[2];
			while(in.hasNextLine()){
				split = in.nextLine().split(",");
				read.put(split[0], split[1]);
			}
			in.close();
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}	
		return read;
	}
}