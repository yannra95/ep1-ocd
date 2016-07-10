
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Memoria extends HashMap<Integer, String> {
	
	private Assembler assembler;
	
	public Memoria(Assembler assembler){
		this.assembler = assembler;
	}
	
	public void printMemoria() {
		Iterator it = this.entrySet().iterator();
		
		while(it.hasNext()){
			Map.Entry entrada = (Map.Entry) it.next();
			System.out.println("endere�o: " + entrada.getKey()
					+ "\t conteudo: " + entrada.getValue() + "\t tamanho: " + entrada.getValue().toString().length());
		}
	}
}
