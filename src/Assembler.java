public class Assembler {
	
	/**
	 * Retorna em um vetor as instru��o e os operadores separadamentes
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

		return resultado;
	}

}
