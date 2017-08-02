package pe.com.tss.runakuna.util;

public class FrecuencyJobSingleton {
	
	private static FrecuencyJobSingleton instance = null;
	 
	private FrecuencyJobSingleton() {
	}
 
	// Lazy Initialization (If required then only)
	public static FrecuencyJobSingleton getInstance() {
		if (instance == null) {
			// Thread Safe. Might be costly operation in some case
			synchronized (FrecuencyJobSingleton.class) {
				if (instance == null) {
					instance = new FrecuencyJobSingleton();
				}
			}
		}
		return instance;
	}
	
	private String metodo1(String codJob){
		String frecuencyEjecucion = "";
		
		return frecuencyEjecucion;
	}
	
	public static final String getFrecuencyExecution(String codJob){
		
		
		return getInstance().metodo1(codJob);
	}

}
