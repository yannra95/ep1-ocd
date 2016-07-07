import java.awt.EventQueue;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

public class Assembler {

	private JFrame frame;
	private JScrollPane scrollPane;
	private JTable table;
	private DefaultTableModel model;
	private Memoria memoria;
	private Processador processador;
	
	private final int TAM_BITS_OPERAND = 14;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Assembler window = new Assembler();
					// window.frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Assembler() {
		processador = new Processador();
		memoria = new Memoria();
		initialize();
	}

	public String completaZerosEsquerda(String s){
		String retorno = "";
		
		int lim = TAM_BITS_OPERAND - s.length();
		char[] aux = new char[TAM_BITS_OPERAND];
		
		for (int i = 0; i < lim; i++) {
			aux[i] = '0';
		}
		
		System.out.println("tamanho aux: "+lim);
		
		for (int k = 0; k < s.length(); k++) {
			aux[lim+k] = s.charAt(k);
		}
		
		retorno = String.valueOf(aux);
		System.out.println("retorno: "+ retorno +"\t tamanho: "+ retorno.length());
		return retorno;
	}

	/**
	 * Devolve o OPCODE para dada instru��o, para ser guardado na memoria
	 * 
	 * @return opCode
	 */
	public String getOpCode(String instrucao) {
		String opCode = "";
		String aux;
		switch (instrucao) {
		case "ax":
			opCode = "10000000000000";
			break;
		case "[ax]":
			opCode = "11000000000000";
			break;
		case "bx":
			opCode = "10000000000001";
			break;
		case "[bx]":
			opCode = "11000000000001";
			break;
		case "cx":
			opCode = "10000000000010";
			break;
		case "[cx]":
			opCode = "11000000000010";
			break;
		case "dx":
			opCode = "10000000000011";
			break;
		case "[dx]":
			opCode = "11000000000011";
			break;
		case "mov":
			opCode = "0100";
			break;
		case "add":
			opCode = "0101";
			break;
		case "sub":
			opCode = "0110";
			break;
		case "mul":
			opCode = "0111";
			break;
		case "div":
			opCode = "1000";
		case "jmp":
			opCode = "1001";
			break;

		default:
			break;
		}

		// Se tiver um numero na instru��o
		if (instrucao.matches(".*\\d+.*")) {
			// Se for uma indire��o
			if (instrucao.contains("[")) {
				aux = "01";
				opCode = aux.concat(completaZerosEsquerda(Integer.toBinaryString(Integer
						.parseInt(instrucao.substring(1, 2)))));
			} else {
				aux = "00";
				opCode = aux.concat(completaZerosEsquerda(Integer.toBinaryString(Integer
						.parseInt(instrucao))));
				System.out.println("valor entrado: "+ Integer.toBinaryString(Integer
						.parseInt(instrucao)));
			}
		}

		// Pra ver se dentro da string tem um inteiro
		// matches(".*\\d+.*")
		return opCode;
	}

	/**
	 * Retorna em um vetor a instru��o e os operadores separadamentes
	 * resultado[0]: instru��o; resultado[1]: op1; resultado[2]: op2
	 * 
	 * @param comando
	 * @return
	 */
	public String[] getInsOp1Op2(String comando) {
		String[] resultado = new String[3];
		int i = 0;
		while (i < comando.length()) {

			resultado[0] = comando.split(" ")[0];

			// Se tiver uma v�rgula, ou seja, se houverem 2 operadores
			if (comando.indexOf(',') > 0) {
				resultado[1] = comando.split(" ")[1].split(",")[0];
				resultado[2] = comando.split(" ")[1].split(",")[1];
			} else {
				resultado[1] = comando.split(" ")[1];
				resultado[2] = null;
			}
			i++;
		}

		System.out.println("comando: " + resultado[0] + " " + resultado[1]
				+ " " + resultado[2]);

		return resultado;
	}

	/**
	 * Retorna uma String com a concatena��o dos opCodes da intru��o e dos
	 * operadores
	 * 
	 * @param componentesLinha
	 * @return
	 */
	public String concatInstrucao(String[] componentesLinha) {

		return getOpCode(componentesLinha[0]).concat(
				getOpCode(componentesLinha[1]).concat(
						getOpCode(componentesLinha[2])));
	}

	/**
	 * Adiciona uma nova linha na 1� posi��o da tabela
	 * 
	 * @param data
	 */
	public void addRow(Object[] data) {
		model.insertRow(0, data);
		table = new JTable(model);
	}

	/**
	 * Adiciona as linhas de codigo a partir do endere�o da memoria especificado
	 * 
	 * @param linhasComando
	 * @param enderecoInicial
	 */
	public void addLinhasMemoria(String[] linhasComando, int enderecoInicial) {

		String[] componentesLinha;

		// Cada linha � adicionada � mem�ria
		for (int i = 0; i < linhasComando.length; i++) {

			componentesLinha = getInsOp1Op2(linhasComando[i]);

			memoria.put(enderecoInicial + i, concatInstrucao(componentesLinha));
		}
	}

	/**
	 * Inicializa os componentes do Frame
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setAlwaysOnTop(false);
		frame.setBounds(100, 100, 936, 631);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(354, 11, 556, 269);
		frame.getContentPane().add(scrollPane);

		String[] columnNames = { "PC", "MAR", "MBR", "IR", "ax", "bx", "cx",
				"dx", "==0", "overf", "null" };
		Object[][] data = { {} };

		model = new DefaultTableModel(data, columnNames);
		table = new JTable(model);
		table.setEnabled(false);
		scrollPane.setViewportView(table);

		JTextArea textArea = new JTextArea();

		// O que acontece quando "F9" � apertado
		textArea.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_F9) {

					// Cria um vetor onde cada posi��o � uma linha do bloco de
					// codigo
					String[] linhasComando = textArea.getText().split(
							"\r\n|\r|\n");

					// Coloca na memoria as linhas de codigo digitadas
					addLinhasMemoria(linhasComando, 0);
					memoria.printMemoria();
					// processador.startProcess()

				}
			}
		});
		textArea.setBounds(10, 11, 334, 269);
		frame.getContentPane().add(textArea);
	}
}
