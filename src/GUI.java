import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;


/**/

@SuppressWarnings("serial")
public class GUI extends JFrame {

	private JPanel contentPane;
	private static JTextArea symbolTableArea;
	private static JTextArea tokensTextArea;
	private static JTextArea warningsArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 839, 690);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		symbolTableArea = new JTextArea();
		symbolTableArea.setWrapStyleWord(true);
		symbolTableArea.setEditable(false);
		symbolTableArea.setBounds(10, 0, 22, 354);
		contentPane.add(symbolTableArea);

		JScrollPane scroll = new JScrollPane(symbolTableArea,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll.setBounds(599, 50, 214, 590);

		contentPane.add(scroll);

		tokensTextArea = new JTextArea();
		tokensTextArea.setEditable(false);
		tokensTextArea.setBounds(31, 275, 511, 156);
		contentPane.add(tokensTextArea);

		JScrollPane scroll2 = new JScrollPane(tokensTextArea,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll2.setBounds(31, 275, 511, 156);
		contentPane.add(scroll2);


		final JTextArea codeTextArea = new JTextArea();
		JScrollPane scrollPane = new JScrollPane((Component) null, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(31, 50, 511, 180);
		contentPane.add(scrollPane);
		scrollPane.setViewportView(codeTextArea);
		JButton btnRunLexicalAnalizer = new JButton("Run Lexical Analizer");
		btnRunLexicalAnalizer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String input = codeTextArea.getText();

				File in = new File("in.pcat");
				if(!in.exists()) {
					try {
						in.createNewFile();
					} catch (IOException e) {
						e.printStackTrace();
					}
				} 
				PrintStream p;
				String[] args = { "in.pcat" };
				try {
					p = new PrintStream(in);
					p.append(input);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				try {
					lexicalAnalyzer(args);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		});
		btnRunLexicalAnalizer.setBounds(377, 229, 165, 23);
		contentPane.add(btnRunLexicalAnalizer);


		JButton btnBrowse = new JButton("Browse");
		btnBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser();

				// we add the filter to force the user to add only properties
				// file
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
						"PCAT File", "pcat");
				chooser.setFileFilter(filter);
				chooser.addChoosableFileFilter(filter);
				int returnVal = chooser.showOpenDialog(null);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					String filePath = chooser.getSelectedFile().getPath();
					File file = new File(filePath);
					java.util.Scanner s;
					try {
						s = new java.util.Scanner(file);
						String code = "";
						while (s.hasNextLine()) {
							code += s.nextLine()+"\n";
						}
						codeTextArea.setText(code);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
				}
			}
		});
		btnBrowse.setBounds(242, 229, 134, 23);
		contentPane.add(btnBrowse);
		
		JLabel lblPcatCode = new JLabel("PCAT Code:");
		lblPcatCode.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
		lblPcatCode.setBounds(31, 21, 115, 29);
		contentPane.add(lblPcatCode);
		
		JLabel lblSymbolTable = new JLabel("Symbol Table");
		lblSymbolTable.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
		lblSymbolTable.setBounds(612, 21, 166, 24);
		contentPane.add(lblSymbolTable);
		
		JLabel lblTokensGenerated = new JLabel("Tokens Generated");
		lblTokensGenerated.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
		lblTokensGenerated.setBounds(31, 241, 200, 33);
		contentPane.add(lblTokensGenerated);
		
		JLabel lblLexicalAnalyzer = new JLabel("Lexical Analyzer");
		lblLexicalAnalyzer.setForeground(Color.BLUE);
		lblLexicalAnalyzer.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		lblLexicalAnalyzer.setBounds(348, 5, 127, 23);
		contentPane.add(lblLexicalAnalyzer);
		
		warningsArea = new JTextArea();
		warningsArea.setEditable(false);
		JScrollPane scrollPane_1 = new JScrollPane((Component) null, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setBounds(31, 484, 511, 156);
		contentPane.add(scrollPane_1);
		
		scrollPane_1.setViewportView(warningsArea);
		
		JLabel lblErrorsAndWarnings = new JLabel("Errors And Warnings");
		lblErrorsAndWarnings.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
		lblErrorsAndWarnings.setBounds(31, 453, 222, 33);
		contentPane.add(lblErrorsAndWarnings);
		
	
		
	}

	private static void lexicalAnalyzer(String[] argv)
			throws FileNotFoundException {
		Scanner s = new Scanner();
		sym_table st = s.symbol_table;
		String tokenString = "";

		int line = 0;
		File err= new File("Errors.txt");
		if(!err.exists()) {
			try {
				err.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} 
		
		
		// In case no file was passed
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
					
					PrintStream err_file= new PrintStream(err);
				//	err_file.print("");
					err_file.close();
					java.io.FileInputStream stream = new java.io.FileInputStream(
							argv[i]);
					java.io.Reader reader = new java.io.InputStreamReader(
							stream, encodingName);
					scanner = new PCATScanner(reader);
					do {
						Yytoken token = scanner.yylex();
						if (token != null) {
							line++;
							tokenString += line + ": ";
							if (token.getTok_type() == tok_type.TOK_IDENTIFIER) {
								st.insert(token.getTokenString());
							}
							tokenString += token + "\n";
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
		
		tokensTextArea.setText(tokenString);
		java.util.Scanner errScan= new java.util.Scanner(err);
		warningsArea.setText("");
		while(errScan.hasNextLine()){
			String errMsg= errScan.nextLine();
			warningsArea.setForeground(Color.red);

			if(errMsg.contains("FATAL")){
				warningsArea.append(errMsg.substring(7)+"\n");
			//	tokensTextArea.setForeground(Color.black);
			}
			else{
			//	tokensTextArea.setForeground(Color.blue);
				warningsArea.append(errMsg+"\n");
			//	tokensTextArea.setForeground(Color.black);
			}
		}
		errScan.close();
		symbolTableArea.setText(st.toString());
	}
}
