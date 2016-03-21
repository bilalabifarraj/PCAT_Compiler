import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

/***
 * 
 * @author Julia El Zini Main class to test the lexical analyzer it takes the
 *         name of the input file from the command line and invokes the lexical
 *         analysis process
 * 
 */
public class Test {

	@SuppressWarnings("resource")
	
	/**
	 * Main method, checks if the input file is present and runs the lexical analyzer on it.
	 * Populates the symbol table found in "output.txt" and prints out the tokens for the user
	 * with all the errors committed.
	 * @param argv
	 * @throws FileNotFoundException
	 */
	public static void main(String argv[]) throws FileNotFoundException {
		Scanner s = new Scanner();
		sym_table st = s.symbol_table;
		File out = new File("output\\output.txt");
		PrintStream printOut = new PrintStream(out);

		int line = 0;

		//In case no file was passed
		if (argv.length == 0) {
			System.out
					.println("Usage : java PCATScanner [ --encoding <name> ] <inputfile(s)>");
		} else {
			int firstFilePos = 0;
			String encodingName = "UTF-8";
			if (argv[0].equals("--encoding")) {
				firstFilePos = 2;
				encodingName = argv[1];
				try {
					java.nio.charset.Charset.forName(encodingName);
				} catch (Exception e) {
					System.out.println("Invalid encoding '" + encodingName
							+ "'");
					return;
				}
			}
			// System.out.println(firstFilePos);
			
			for (int i = firstFilePos; i < argv.length; i++) {
				PCATScanner scanner = null;
				try {
					java.io.FileInputStream stream = new java.io.FileInputStream(
							argv[i]);
					java.io.Reader reader = new java.io.InputStreamReader(
							stream, encodingName);
					scanner = new PCATScanner(reader);
					do {
						Yytoken token = scanner.yylex();
						if (token != null) {
							line++;
							System.out.print(line + ": ");
							if (token.getTok_type() == tok_type.TOK_IDENTIFIER) {
								st.insert(token.getTokenString());
							}
							System.out.println(token);
						}
					} while (!scanner.isZzAtEOF());
				} catch (java.io.FileNotFoundException e) {
					System.out.println("File not found : \"" + argv[i] + "\"");
				} catch (java.io.IOException e) {
					System.out.println("IO error scanning file \"" + argv[i]
							+ "\"");
					System.out.println(e);
				} catch (Exception e) {
					System.out.println("Unexpected exception:");
					e.printStackTrace();
				}
			}
		}
		printOut.append(st.toString());
		printOut.close();
	}
}
