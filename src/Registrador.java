public class Registrador {

	private String conteudo;
	private int portaEntrada;
	private int portaSaida;
	private String endereco;
	
	public Registrador(String conteudo, int portaEntrada, int portaSaida,
			String endereco) {
		super();
		this.conteudo = conteudo;
		this.portaEntrada = portaEntrada;
		this.portaSaida = portaSaida;
		this.endereco = endereco;
	}
	public Registrador(String conteudo, int portaEntrada, int portaSaida) {
		super();
		this.conteudo = conteudo;
		this.portaEntrada = portaEntrada;
		this.portaSaida = portaSaida;
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
	

}
