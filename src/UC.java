import java.util.ArrayList;

public class UC {
	String operacao;
	String operando1;
	String operando2;
	boolean op1Reg;
	boolean op1Ind;
	boolean op2Reg;
	boolean op2Ind;
	String palavraControle;
	String[] palavra;
	private Assembler assembler;
	
	public UC(Assembler assembler){
		this.assembler = assembler;
	}
	
	// Esse método retorna as palavras de controle envolvidas na linha de código digitada
	// Deve ser executado após a execução completa da linha de código anterior 
	public void lerInstrucao(Memoria memoria, int endereco) {
		//Para log
		ArrayList<Palavra> palavras = new ArrayList<Palavra>(3);
		
		String opcode = memoria.get(endereco); // instrução a ser lida
		//Para log
		
		boolean op1Ind = false, op2Ind = false, op1Reg = false, op2Reg = false;
		
		if (operando1.charAt(0) == '1') { // op1 registrador
			op1Reg = true;
		}
		if (operando2.charAt(0) == '1') { // op2 registrador 
			op2Reg = true;
		}
		if (operando1.charAt(1) == '1') { // op1 indireção
			op1Ind = true;
		}
		if (operando2.charAt(1) == '1') { // op2 indireção 
			op2Ind = true;
		}
		
		String ins = opcode.substring(0,4);
		String operando1 = opcode.substring(4,18);
		String operando2 = opcode.substring(18);
		
		if (ins == "0100") {
		// MOV 
			palavras.addAll(palavraBase(ins,operando1, operando2, op1Ind, op2Ind, op1Reg, op2Reg)); // indireções
			palavras.addAll(palavraMOV(ins,operando1, operando2, op1Ind, op2Ind, op1Reg, op2Reg));
		} else if (ins == "0101" || ins == "0110" || ins == "0111" || ins == "1000"){  
		//ADD - SUB - MUL - DIV
		} else if (ins == "1001"){
		// JUMP 
		} else {
		// OUTROS JUMPS
		}

		
	}	

	public ArrayList<Palavra> palavraBase(String instrucao, String operando1, String operando2, 
			boolean op1Ind, boolean op2Ind, boolean op1Reg, boolean op2Reg) {
		//String linhasControle, endJump, comandoUla, readWrite, indirecao;
		
		ArrayList<Palavra> palavras = new ArrayList<Palavra>();
		
		if (op1Ind){ // indireção registrador ou memória
			palavras.add(indLeitura(operando1, op1Reg));
			palavras.add(indEscrita(operando1));
		} 	
		if(op2Ind){ // indireção registrador ou memória
			palavras.add(indLeitura(operando2, op2Reg));
			palavras.add(indEscrita(operando2));
		}
		
		//Para log
		return palavras;
	}
	
	/** 
	20 bits para linhas de controle
	8 bits pro endereço pra onde pular
	3 bits pro comando pra ula
	1 bit pra dizer para a memoria se é read(1) ou write(0)
	1 bit pra dizer se é indireção
	00000000000000000000 00000000 000 0 0*/
	
	// Indireção na leitura
	public Palavra indLeitura(String operando, boolean reg) {
		ArrayList<Integer> portas = new ArrayList<Integer>();
		
		if (reg) {
			portas.addAll((recuperaSaida(operando, false)));
		} else {
			portas.add(retornaPortaSaida("MEM"));
		}
		portas.add(retornaPortaEntrada("MAR"));
		Palavra palavra = new Palavra(geraSinal(portas),"00000000", "000", "1", "1"); // read
		
		return palavra;
	}

	// Indireção na escrita
	public Palavra indEscrita(String operando) { // determina o operando para saber se é entrada ou saída
		ArrayList<Integer> portas = new ArrayList<Integer>();
		
		portas.add(retornaPortaSaida("MEM"));
		portas.add(retornaPortaEntrada("MBR"));
		Palavra palavra = new Palavra(geraSinal(portas),"00000000", "000", "0", "1"); // write
		
		return palavra;
	}
	// Leitura direta
	public Palavra dirLeitura(String operando, boolean reg) {
		ArrayList<Integer> portas = new ArrayList<Integer>();
		
		if (reg) {
			portas.addAll((recuperaSaida(operando, false)));
		} else {
			portas.add(retornaPortaSaida("IR"));
		}
		portas.add(retornaPortaEntrada("MAR"));
		Palavra palavra = new Palavra(geraSinal(portas),"00000000", "000", "1", "0"); // read
		
		return palavra;
	}

	// Escrita direta
	public Palavra dirEscrita(String operando) { // determina o operando para saber se é entrada ou saída
		ArrayList<Integer> portas = new ArrayList<Integer>();
		
		portas.add(retornaPortaSaida("MEM"));
		portas.add(retornaPortaEntrada("MBR"));
		Palavra palavra = new Palavra(geraSinal(portas),"00000000", "000", "0", "0"); // write
		
		return palavra;
	}
	
	private void palavraJump() {
		// TODO Auto-generated method stub
		
	}

	private ArrayList<Palavra> palavraMOV(String ins, String operando1, String operando2, 
			boolean op1Ind, boolean op2Ind, boolean op1Reg, boolean op2Reg) {
		
		
		
		return null;
	}

	private void palavraULA() {
		// TODO Auto-generated method stub
		
	}

	
	public String geraSinal(ArrayList<Integer>portas) {
		String sinal = "";
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < 20; i++) {
			if (portas.contains(i)) {
				sb.append('1');
			} else {
				sb.append('0');
			}
		}
		sinal = sb.toString();
		System.out.println("sinal " + sinal);
		return sinal;
	}
	
	public String mandaSinal(){
		return palavraControle;
	}
	
	public ArrayList<Integer> recuperaEntrada(String operando, boolean indirecao) {
		ArrayList<Integer> portas = new ArrayList<Integer>();
		String reg = operando.substring(10);
		switch(reg) {
		case "00":
			portas.add(retornaPortaEntrada("AX"));
			break;
		case "01":
			portas.add(retornaPortaEntrada("BX"));
			break;
		case "10":
			portas.add(retornaPortaEntrada("CX"));
			break;
		case "11":
			portas.add(retornaPortaEntrada("DX"));
			break;
		}
		if (indirecao) {
			portas.add(retornaPortaEntrada("MAR"));
		}
		return portas;
	}
	
	public ArrayList<Integer> recuperaSaida(String operando, boolean indirecao) {
		ArrayList<Integer> portas = new ArrayList<Integer>();
		String reg = operando.substring(10);
		switch(reg) {
		case "00":
			portas.add(retornaPortaSaida("AX"));
			break;
		case "01":
			portas.add(retornaPortaSaida("BX"));
			break;
		case "10":
			portas.add(retornaPortaSaida("CX"));
			break;
		case "11":
			portas.add(retornaPortaSaida("DX"));
			break;
		}
		if (indirecao) {
			portas.add(retornaPortaSaida("MAR"));
		}
		return portas;
	}
	
	public int retornaPortaEntrada(String local) {
		switch(local) {
			case "PC":
				return 0;
			case "MAR":
				return 2;
			case "MBR":
				return 3;
			case "AX":
				return 5;
			case "BX":
				return 7;
			case "CX":
				return 9;
			case "DX":
				return 11;
			case "IR":
				return 13;
			case "ULA":
				return 15;
			case "X":
				return 16;
			case "AC":
				break; // TODO
			case "MEM":
				return 18;
			default:
				break; 
			}
		return -1;
	}
	
	public int retornaPortaSaida(String local) {
		switch(local) {
		case "PC":
			return 1;
		case "MAR":
			break; // TODO 
		case "MBR":
			return 4;
		case "AX":
			return 6;
		case "BX":
			return 8;
		case "CX":
			return 10;
		case "DX":
			return 12;
		case "IR":
			return 14;
		case "ULA":
			break; // TODO
		case "X":
			break; // TODO
		case "AC":
			return 17;
		case "MEM":
			return 19;
		default:
			break;
		} 
		return -1; //TODO
	}
}