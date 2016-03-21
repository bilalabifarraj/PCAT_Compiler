/***
 * 
 * @author Julia El Zini
 * encapsulates the types of tokens we can have
 *
 */
public enum tok_type {
	
	//KEYWORDS
	TOK_OP_AND, 
	TOK_ARRAY, 
	TOK_BEGIN, 
	TOK_BY, 
	TOK_OP_TIMES, 
	TOK_DO, 
	TOK_ELSE, 
	TOK_ELSEIF, 
	TOK_END,
	TOK_EXIT, 
	TOK_FOR, 
	TOK_IF, 
	TOK_IS, 
	TOK_LOOP, 
	TOK_OP_NOT, 
	TOK_OF, 
	TOK_OP_OR, 
	TOK_PROCEDURE, 
	TOK_PROGRAM, 
	TOK_READ, 
	TOK_RECORD, 
	TOK_RETURN, 
	TOK_THEN, 
	TOK_TO, 
	TOK_TYPE, 
	TOK_VAR, 
	TOK_WHILE,
	TOK_WRITE, 
 
	TOK_OP_ASSIGN, 
	TOK_OP_REL,
	TOK_OP_ADD, 
	//DELIMITER
	TOK_COMMA, 
	TOK_COLON, 
	TOK_SEMI, 
	TOK_DOT,
	TOK_LB, 
	TOK_LC, 
	TOK_RC, 
	TOK_LP, 
	TOK_RP,
	TOK_RB,
	
	//Literals
	TOK_INTLIT, 
	TOK_IDENTIFIER, 
	TOK_STRLIT, 
	TOK_REALLIT
}
