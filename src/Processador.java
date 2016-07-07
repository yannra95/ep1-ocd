import java.util.ArrayList;
import java.util.List;

public class Processador {
	private IR ir;
	private Registrador pc;
	private Registrador mbr;
	private Registrador mar;
	private Registrador ax;
	private Registrador bx;
	private Registrador cx;
	private Registrador dx;
	
	private boolean[] barramentoDados;
	private ArrayList<Integer> sinal;
	
	public Processador() {
		this.pc = new Registrador("0",0 , 1);
		this.mar = new Registrador("", 2, 3);
		this.mbr = new Registrador("", 4, 5);
		this.ir = new IR();
		
		//Vetor de boolan que representa as portas e seus estados (aberta ou fechada)
		this.barramentoDados = new boolean[23];
		//Vetor de int que representa quais portas um dado sinal pede que sejam abertas
		this.sinal = new ArrayList<Integer>();
	}
	
	public void cicloInstrucao(){
		
	}
	
	/**
	 * MAR <== PC
	 * Memoria <== MAR
	 * MBR <== Memoria
	 * IR <== MBR
	 */
}
