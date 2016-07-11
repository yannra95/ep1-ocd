public class Registrador {

	private String conteudo;
	private String[] conteudoIR = new String[3];
	private int portaEntrada;
	private boolean isEntradaAberta;
	private int portaSaida;
	private boolean isSaidaAberta;
	
	public Registrador(String conteudo, int portaEntrada, int portaSaida) {
		super();
		this.conteudo = conteudo;
		this.portaEntrada = portaEntrada;
		this.isEntradaAberta = false;
		this.portaSaida = portaSaida;
		this.isSaidaAberta = false;
	}
	/**
	 * Se o registrador for o IR. 0 � a opera��o, 1 � o operando1, 2 � o operando 2
	 * @param conteudo
	 * @param portaEntrada
	 * @param portaSaida
	 */
	public Registrador(String operacao, String operando1, String operando2, int portaEntrada, int portaSaida) {
		super();
		this.conteudoIR[0] = operacao;
		this.conteudoIR[1] = operando1;
		this.conteudoIR[2] = operando2;
		this.portaEntrada = portaEntrada;
		this.isEntradaAberta = false;
		this.portaSaida = portaSaida;
		this.isSaidaAberta = false;
	}
	
	//Se tiver apenas uma porta, por conven�ao, usaremos a de entrada
	public Registrador(String conteudo, int portaEntrada) {
		super();
		this.conteudo = conteudo;
		this.portaEntrada = portaEntrada;
		this.isEntradaAberta = false;
		this.portaSaida = -1;
	}
	
	
	public String getConteudo() {
		return conteudo;
	}
	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}
	//N�o possui set pois s� pode ser definido na inst�ncia do objeto
	public int getPortaEntrada() {
		return portaEntrada;
	}
	//N�o possui set pois s� pode ser definido na inst�ncia do objeto
	public int getPortaSaida() {
		return portaSaida;
	}

	public String[] getConteudoIR() {
		return conteudoIR;
	}

	public void setConteudoIR(String[] conteudoIR) {
		this.conteudoIR = conteudoIR;
	}
	public boolean isEntradaAberta() {
		return isEntradaAberta;
	}
	public void setEntradaAberta(boolean isEntradaAberta) {
		this.isEntradaAberta = isEntradaAberta;
	}
	public boolean isSaidaAberta() {
		return isSaidaAberta;
	}
	public void setSaidaAberta(boolean isSaidaAberta) {
		this.isSaidaAberta = isSaidaAberta;
	}
	

}
