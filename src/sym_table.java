import java.util.LinkedList;
import java.util.List;

/***
 * 
 * @author Julia El ZIni
 * this class represents a symbol table along with the insert method defined on it
 */
public class sym_table {

	private List<sym_entry> table;
	private int last_ind;
	
	public sym_table(){
		this.table = new LinkedList<sym_entry>();
		this.last_ind = 0;
	}
	
	/**
	 * this methods adds the sym_entry to the table
	 * if the sym_entry is found, it just returns it
	 * @param e, the sym_entry to add
	 * @return the index in the symbol table
	 */
	public sym_entry insert(String name){
		for(sym_entry e: table){
			if(e.getName().equals(name))
				return e; 
		}
		sym_entry e = new sym_entry(last_ind++, name, tok_type.TOK_IDENTIFIER);
		table.add(e);
		return e;
	}
	
	/**
	 * this method inserts keywords into the sym_table
	 * @param token_type
	 * @param name
	 */
	public void insertKeyword(tok_type token_type, String name){
		table.add(new sym_entry(last_ind++, name, token_type));
	}

	public int getLast_ind(){
		return last_ind;
	}
	/**
	 * @return a string representing the table
	 */
	public String toString(){
		String str = "";
		for(sym_entry e: table)
			str += e.toString() + "\n";

		return str;
	}
}
