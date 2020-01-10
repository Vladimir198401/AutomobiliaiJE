package autoy;

public class Autobusas extends Auto {
	
	public Stotele[] marsrutas;

	
	public Autobusas() {
		
	}

	public Autobusas( Stotele[] marsrutas) {
		
		super();
		this.marsrutas = marsrutas;			
	}
	
	public Autobusas ( String vardas, double greitis, Stotele[] marsrutas  ) {
		
		super( vardas, greitis );
		this.marsrutas = marsrutas;
	}
	
	public double nuvaziotasAtstumas () {
		
		return (this.greitis * laiko_intervalas);
	}	
	
	public String parodyti ( ) {
		
		String stoteles = "";
		
		for (int i = 0; i < marsrutas.length; i++ ) {
			
			stoteles += " " + marsrutas[ i ].parodyti();
		} 
		return  super.parodyti() + "\n\t---- Marsrutas:" + stoteles;
	}
	
	public String kasAs() {
		
		return getClass().getSimpleName();
	}	
}
