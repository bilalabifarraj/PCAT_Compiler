import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
%%

%public
%class PCATScanner
%extends Scanner


%line
%column
%unicode
%init{
	super();
%init}

%{
	File errors= new File("Errors.txt");
	PrintStream eLog;
	
	public void printInFile(String str) throws FileNotFoundException{
		if(!errors.exists()) {
			try {
				errors.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} 
		if(eLog==null)
			eLog= new PrintStream(errors);
		eLog.println(str);
	}
	
	public boolean isZzAtEOF() {
		return zzAtEOF;
	}
	
	public void setZzAtEOF(boolean zzAtEOF) {
		this.zzAtEOF = zzAtEOF;
	}
	
	public Yytoken token(tok_type type, String tok, int val){
		return new Yytoken(type, tok, val, yyline, yycolumn);
	}
	
	public Yytoken token(tok_type type, String tok, String val){
		return new Yytoken(type, tok, val, yyline, yycolumn);
	}
	
	public Yytoken token(tok_type type, String tok, tok_val val){
		return new Yytoken(type, tok, val, yyline, yycolumn);
	}
	
	public Yytoken token(tok_type type, Integer tok, int val){
		return new Yytoken(type, ""+tok, val, yyline, yycolumn);
	}
	
	public Yytoken token(tok_type type, String tok){
		return new Yytoken(type, tok, yyline, yycolumn);
	}
%}

/* main character classes */
LineTerminator = \r|\n|\r\n


WhiteSpace = {LineTerminator} | [ \t\f]

/* comments */
TOK_COMMENT = "(*" [^*] ~"*)" | "(*" "*"+ ")" | "(*" "*"+ ")" 


/* identifiers */

letter = [a-zA-Z]
digit = [0-9]
id = {letter}({letter}|{digit})* 
integer = [0-9]+
real = [0-9]+\.[0-9]+
str = "\"" [^(\"|\\t|\\n)]* [\\\"]* "\""


/* string and character literals */
TOK_STRLIT = "\"" [^\"\\]* "\""


%%

<YYINITIAL> {

/*KEYWORDS*/
	"AND" 			{return token(tok_type.TOK_OP_AND, "AND", tok_val.OP_AND);}
	"ARRAY"			{return token(tok_type.TOK_ARRAY, "ARRAY");}
	"BEGIN"			{return token(tok_type.TOK_BEGIN, "BEGIN");}
	"BY"			{return token(tok_type.TOK_BY, "BY");}
	"DIV"			{return token(tok_type.TOK_OP_TIMES, "DIV", tok_val.OP_MUL_DIV);}
	"DO"			{return token(tok_type.TOK_DO, "DO");}
	"ELSE"			{return token(tok_type.TOK_ELSE, "ELSE");}
	"ELSEIF"		{return token(tok_type.TOK_ELSEIF, "ELSEIF");}
	"END"			{return token(tok_type.TOK_END, "END");}
	"EXIT"			{return token(tok_type.TOK_EXIT, "EXIT");}
	"FOR"			{return token(tok_type.TOK_FOR, "FOR");}
	"IF"			{return token(tok_type.TOK_IF, "IF");}
	"IS"			{return token(tok_type.TOK_IS, "IS");}
	"LOOP"			{return token(tok_type.TOK_LOOP, "LOOP");}
	"MOD"			{return token(tok_type.TOK_OP_TIMES, "MOD", tok_val.OP_MUL_MOD);}
	"NOT"			{return token(tok_type.TOK_OP_NOT, "NOT", tok_val.OP_NOT);}
	"OF"			{return token(tok_type.TOK_OF, "OF");}
	"OR"			{return token(tok_type.TOK_OP_OR, "OR", tok_val.OP_OR);}
	"PROCEDURE"		{return token(tok_type.TOK_PROCEDURE, "PROCEDURE");}
	"PROGRAM"		{return token(tok_type.TOK_PROGRAM, "PROGRAM");}
	"READ"			{return token(tok_type.TOK_READ, "READ");}
	"RECORD"		{return token(tok_type.TOK_RECORD, "RECORD");}
	"RETURN"		{return token(tok_type.TOK_RETURN, "RETURN");}
	"THEN"			{return token(tok_type.TOK_THEN, "THEN");}
	"TO"			{return token(tok_type.TOK_TO, "TO");}
	"TYPE"			{return token(tok_type.TOK_TYPE, "TYPE");}
	"VAR"			{return token(tok_type.TOK_VAR, "VAR");}
	"WHILE"			{return token(tok_type.TOK_WHILE, "WHILE");}
	"WRITE"			{return token(tok_type.TOK_WRITE, "WRITE");}

/*DELIMITERS*/
	","				{return token(tok_type.TOK_COMMA, ",");}
	":"				{return token(tok_type.TOK_COLON, ":");}
	";"				{return token(tok_type.TOK_SEMI, ";");}
	"."				{return token(tok_type.TOK_DOT, ".");}
	"["				{return token(tok_type.TOK_LB, "[");}
	"]"				{return token(tok_type.TOK_RB, "]");}
	"{"				{return token(tok_type.TOK_LC, "{");}
	"}"				{return token(tok_type.TOK_RC, "}");}
	"("				{return token(tok_type.TOK_LP, "(");}
	")"				{return token(tok_type.TOK_RP, ")");}

/*OPERATORS*/	
	"="				{return token(tok_type.TOK_OP_REL, "=", tok_val.OP_EQ);}
	"<>"			{return token(tok_type.TOK_OP_REL, "<>", tok_val.OP_NE);}
	"<"				{return token(tok_type.TOK_OP_REL, "<", tok_val.OP_LT);}
	"<="			{return token(tok_type.TOK_OP_REL, "<=", tok_val.OP_LE);}
	">"				{return token(tok_type.TOK_OP_REL, ">", tok_val.OP_GT);}
	">="			{return token(tok_type.TOK_OP_REL, ">=", tok_val.OP_GE);}
	":="			{return token(tok_type.TOK_OP_REL, ":=", tok_val.OP_ASSIGN);}
	"+"				{return token(tok_type.TOK_OP_REL, "+", tok_val.OP_ADD_PLUS);}
	"-"				{return token(tok_type.TOK_OP_REL, "-", tok_val.OP_ADD_MINUS);}
	"*"				{return token(tok_type.TOK_OP_REL, "*", tok_val.OP_MUL_TIMES);}
	"/"				{return token(tok_type.TOK_OP_REL, "/", tok_val.OP_MUL_DIV);}
	
	{WhiteSpace}	{ /* ignore */ }
	{integer}		{return token(tok_type.TOK_INTLIT, (int)(new Integer(yytext())), (int)(new Integer(yytext())));}
	{TOK_COMMENT}	{ /* ignore */ }
	{id}			{	if(yylength() > 10) {
							printInFile("Identifier length > 255 of identifier: "+yytext()+"\" at line "+yyline+", column "+yycolumn);
											throw new RuntimeException("Identifer "+yytext()+" exceeded 255 length at line "+yyline+", column "+yycolumn); }
						
						else{
						int value = addIdentifier(yytext());
						return token(tok_type.TOK_IDENTIFIER, yytext(), value);
						}} /*TODO */
	{str}			{ return token(tok_type.TOK_STRLIT, yytext(), yytext());}
	{real}			{return token(tok_type.TOK_REALLIT, yytext(), yytext());}
	
	
	
	/*error type 2*/
	({digit}|"_")({letter}|{digit})+ 	 	{printInFile("Error at line "+yyline+", column "+yycolumn+": identifier "+yytext()+" must begin with a letter. Skipping"); }
	
	/*error type 3*/
	"\"" [^\"\n]*   		     			{printInFile("Error at line "+yyline+", column "+yycolumn+": "+yytext()+"...Unterminated string. Skipping");}
	
	
	/*error type 4 dangling comments*/
	"(*"[^*)]*							 	{printInFile("FATAL: Dangling comment \""+yytext()+"\" at line "+yyline+", column "+yycolumn);
											throw new RuntimeException("Dangling comment \""+yytext()+"\" at line "+yyline+", column "+yycolumn); }
	
	/*error type 4 unmatched (**/
	"*)"								 	{eLog.println("FATAL: Unmatched \""+yytext()+"\" at line "+yyline+", column "+yycolumn);
												throw new RuntimeException("Unmatched \""+yytext()+"\" at line "+yyline+", column "+yycolumn); }
	
	
}


/* error type 1 */
[^]                              { printInFile("Error at line "+yyline+", column "+yycolumn+": Unrecognized symbol "+yytext()+". Skipping"); }
