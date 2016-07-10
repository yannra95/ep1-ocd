import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class Processador {
	private Registrador pc;
	private Registrador mbr;
	private Registrador mar;
	private Registrador ax;
	private Registrador bx;
	private Registrador cx;
	private Registrador dx;
	private Registrador ir;
	private Registrador ula;
	private Registrador x;
	private Registrador ac;
	private Registrador mem;
	private Registrador[] registradores;
	public Memoria memoria;
	public String palavraControle;
	private UC uc;

	private boolean[] barramentoDados;

	public Processador() {

//		entrada	saida
//		PC	0		1
//		MAR	2		null
//		MBR	3		4
//		AX	5		6
//		BX	7		8
//		CX	9		10
//		DX	11		12
//		IR	13		14
//		ULA	15		null
//		X	16		null
//		AC	null	17
//		MEM	18		19
		
		this.pc = new Registrador("0", 0, 1);
		this.mar = new Registrador("", 2);
		this.mbr = new Registrador("", 3, 4);
		this.ax = new Registrador("", 5, 6);
		this.bx = new Registrador("", 7, 8);
		this.cx = new Registrador("", 9, 10);
		this.dx = new Registrador("", 11, 12);
		this.ir = new Registrador("", "", "", 13, 14);
		this.ula = new Registrador("", 15);
		this.x = new Registrador("", 16);
		this.ac = new Registrador("", 17);
		this.mem = new Registrador("", 18, 19);
		this.registradores = new Registrador[]{pc, mar, mbr, ir, ax, bx, cx, dx};
		this.memoria = new Memoria();
		this.palavraControle = "";

		//Vetor de boolean que representa as portas e seus estados (aberta ou fechada)
		this.barramentoDados = new boolean[21];
	}

	public void cicloInstrucao() {
		
		cicloBusca();
		
	}
	
	public void interpretaPalavra(){
		abrePortas();
		String aux = "";
		
		//Se as portas da ULA n�o tiverem abertas, opera��o envolve apenas transporte de dados
		if(!barramentoDados[15]){
			for (int i = 0; i < barramentoDados.length; i++) {
				for (int j = 0; j < registradores.length; j++) {
					if(registradores[j].getPortaSaida() == i)
						aux = registradores[j].getConteudo();
				}
			}
		}else{
			
		}
		
	}	
	
	
	public void abrePortas(){
		String sinal = palavraControle.substring(0, 20);
		for (int i = 0; i < barramentoDados.length; i++) {
			if(sinal.charAt(i) == '1')
				barramentoDados[i] = true;
		}
	}
	
	public void fechaPortas(){
		for (int i = 0; i < barramentoDados.length; i++) {
			barramentoDados[i] = false;
		}
	}
	
	/**
	 * Contru��o manual da palavra de controle representa a interpreta��o da UC
	 */
	public void cicloBusca() {
		
		//MAR <- PC 1,2
		palavraControle = "01100000000000000000 0 00000000 00 0 0";
		interpretaPalavra();
		//Memoria <- MAR 18
		palavraControle = "00000000000000000010 0 00000000 00 1 1";
		
		//MBR <- Memoria 4, 19
		palavraControle = "00010000000000000001 0 00000000 00 1 1";
		
		//IR <- MBR 4,13
		palavraControle = "00001000000001000000 0 00000000 00 1 1";
	}
	
	public void cicloIndirecao(){
		
	}

	public void cicloExecucao() {

	}

	/**
	 * MAR <== PC Memoria <== MAR MBR <== Memoria IR <== MBR
	 */

	public Registrador getPc() {
		return pc;
	}

	public void setPc(Registrador pc) {
		this.pc = pc;
	}

	public Registrador getMbr() {
		return mbr;
	}

	public void setMbr(Registrador mbr) {
		this.mbr = mbr;
	}

	public Registrador getMar() {
		return mar;
	}

	public void setMar(Registrador mar) {
		this.mar = mar;
	}

	public Registrador getAx() {
		return ax;
	}

	public void setAx(Registrador ax) {
		this.ax = ax;
	}

	public Registrador getBx() {
		return bx;
	}

	public void setBx(Registrador bx) {
		this.bx = bx;
	}

	public Registrador getCx() {
		return cx;
	}

	public void setCx(Registrador cx) {
		this.cx = cx;
	}

	public Registrador getDx() {
		return dx;
	}

	public void setDx(Registrador dx) {
		this.dx = dx;
	}

	public boolean[] getBarramentoDados() {
		return barramentoDados;
	}

	public void setBarramentoDados(boolean[] barramentoDados) {
		this.barramentoDados = barramentoDados;
	}

	public Registrador getIr() {
		return ir;
	}

	public void setIr(Registrador ir) {
		this.ir = ir;
	}
}
