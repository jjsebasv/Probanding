package banco;
 import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



public class TarjetaDeCoordenadas implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final long numeroTarjeta;
	private final Map<String, Map<Integer, Integer>> datos;
	
	public TarjetaDeCoordenadas(long numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
		datos = new HashMap<String, Map<Integer, Integer>>();
		creadorCoordenadas(datos);
		
	}
	
	public void creadorCoordenadas( Map<String, Map<Integer, Integer>> datos){
		ArrayList<String> filas = new ArrayList<String>();
		filas.add("A");
		filas.add("B");
		filas.add("C");
		filas.add("D");

		for ( int i=0; i<4; i++){
			System.out.println("FILA:"+filas.get(i)); 
			Map<Integer,Integer> aux = new HashMap<Integer,Integer>();
			for ( int j=1; j<5; j++){
				int nrand =  (int)Math.floor( Math.random() *(1-11)+10);
				aux.put(j, nrand);
				System.out.println("J:"+j+" numero:"+nrand);
			}
	
			datos.put(filas.get(i), aux);
		}
	
		
	}

	
	// --------------------------- HASHCODE, EQUALS, TOSTRING -----------------------------------

	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((datos == null) ? 0 : datos.hashCode());
		result = prime * result
				+ (int) (numeroTarjeta ^ (numeroTarjeta >>> 32));
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TarjetaDeCoordenadas other = (TarjetaDeCoordenadas) obj;
		if (datos == null) {
			if (other.datos != null)
				return false;
		} else if (!datos.equals(other.datos))
			return false;
		if (numeroTarjeta != other.numeroTarjeta)
			return false;
		return true;
	}
	
	public String toString(){
		return "XXXX-XXXX-XXXX-"+this.getNumeroTarjeta()%10000;
	}

	
	// --------------------------- GETTERS -----------------------------------

	
	public long getNumeroTarjeta() {
		return numeroTarjeta;
	}

	public Map<String, Map<Integer, Integer>> getDatos() {
		return datos;
	}
	
	
}
