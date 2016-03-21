/**
 * 
 * @author Julia El Zini *
 */
public class Yytoken {

	private boolean has_value;
	private tok_type type;
	private String token_string;
	private tok_val value;
	private int row;
	private int column;
	private int val_int;
	private String val_str;

	/** different constructors **/
	// if the value is a tok_val => it is an operator
	public Yytoken(tok_type type, String token_string, tok_val val, int row,
			int column) {
		super();
		this.has_value = true;
		this.type = type;
		this.token_string = token_string;
		this.row = row;
		this.column = column;
		this.value = val;
	}

	// if the value is int => int literal
	public Yytoken(tok_type type, String token_string, int val, int row,
			int column) {
		super();
		this.has_value = true;
		this.type = type;
		this.token_string = token_string;
		this.row = row;
		this.column = column;
		this.val_int = val;
	}

	// if the value is string => string litteral
	public Yytoken(tok_type type, String token_string, String val, int row,
			int column) {
		super();
		this.has_value = true;
		this.type = type;
		this.token_string = token_string;
		this.row = row;
		this.column = column;
		this.val_str = val;
	}

	// no value => delimiter
	public Yytoken(tok_type type, String tok, int yyline, int yycolumn) {
		this.type = type;
		this.token_string = tok;
		this.row = yyline;
		this.column = yycolumn;
		this.has_value = false;
	}

	/**
	 * TODO use string formatter
	 */
	public String toString() {
		if (!has_value) {
			return type + ": line " + row + ", column " + column + ", "
					+ token_string;

		}
		if (value != null)
			return type + ": line " + row + ", column " + column + ", "
					+ token_string + ", value: " + value;
		if (val_str != null)
			return type + ": line " + row + ", column " + column + ", "
					+ token_string + ", value: " + val_str;

		else
			return type + ": line " + row + ", column " + column + ", "
					+ token_string + ", value: " + val_int;

	}

	public tok_type getTok_type() {
		return type;
	}

	public String getTokenString() {
		return token_string;
	}

}
