/***
 * @author Julia El Zini
 * The sym_entry data structure represents an entry in the symbol table
 */
public class sym_entry {

	private int val;
	private String name;
	tok_type tok_type;
	
	public sym_entry(int val, String name, tok_type tok_type) {
		super();
		this.val = val;
		this.name = name;
		this.tok_type = tok_type;
	}
	
	public String toString(){
		return val+": "+name+", "+tok_type;
	}
	
	public String getName(){
		return name;
	}

	public int getValue() {
		return val;
	}
}
