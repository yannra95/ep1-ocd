public class Registrador {

	private String conteudo;
	private String[] conteudoIR;
	private int portaEntrada;
	private int portaSaida;
	private String endereco;
	
	public Registrador(String conteudo, int portaEntrada, int portaSaida) {
		super();
		this.conteudo = conteudo;
		this.portaEntrada = portaEntrada;
		this.portaSaida = portaSaida;
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
		this.portaSaida = portaSaida;
	}
	
	//Se tiver apenas uma porta, por conven�ao, usaremos a de entrada
	public Registrador(String conteudo, int portaEntrada) {
		super();
		this.conteudo = conteudo;
		this.portaEntrada = portaEntrada;
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
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String[] getConteudoIR() {
		return conteudoIR;
	}

	public void setConteudoIR(String[] conteudoIR) {
		this.conteudoIR = conteudoIR;
	}
	

}
