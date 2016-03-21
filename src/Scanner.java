import java.util.LinkedList;
import java.util.List;

/***
 * 
 * @author Julia El Zini
 *
 *This class encapsulates some functionalities for the scanner
 *such that adding all the keywords ahead of time in the symbol table
 *and has methods to insert into the symbol table 
 *and get the tokens
 */
public class Scanner {

	protected sym_table symbol_table;
	protected List<Yytoken> tokens;
	
	public Scanner(){
		symbol_table = new sym_table();
		tokens = new LinkedList<Yytoken>();
		//add the keywords
		String[] keywords = {"AND",
				"ARRAY", 
				"BEGIN", 
				"BY",
				"DIV", 
				"DO", 
				"ELSE", 
				"ELSEIF", 
				"END", 
				"EXIT", 
				"FOR", 
				"IF", 
				"IS", 
				"LOOP", 
				"MOD", 
				"NOT", 
				"OF", 
				"OR", 
				"PROCEDURE", 
				"PROGRAM", 
				"READ", 
				"RECORD", 
				"RETURN",
				"THEN", 
				"TO",
				"TYPE",
				"VAR",
				"WHILE",
				"WRITE"};
		for(int i = 0; i< keywords.length; i++){
			symbol_table.insertKeyword(tok_type.values()[i + 1], keywords[i]);
		}
	}
	/**
	 * @param tok_name
	 * @return symbol_table with the new token name added to it
	 */
	public int addIdentifier(String tok_name){
		return symbol_table.insert(tok_name).getValue();
	}
	
	/**
	 * @return a list of all the tokens in the symbol table 
	 */
	public List<Yytoken> getTokens(){
		return tokens;
	}
	
	/**
	 * @return the symbol table itself
	 */
	public sym_table getSymbolTable(){
		return symbol_table;
	}
} 
