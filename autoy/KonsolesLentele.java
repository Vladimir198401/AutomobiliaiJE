package autoy;

public class KonsolesLentele {
	
	String[] antrastes_pav;
	String[] formatai;
	String eilute;
	int nr_esamu = 0;
	

	public KonsolesLentele( int kiekis ) {
		
		antrastes_pav = new String [ kiekis ];
		formatai = new String [ kiekis ];
	}
	
	public void pridetiStulpeli( String pav, String formatas ) {
		
		antrastes_pav [ nr_esamu ] = pav;
		formatai [ nr_esamu ] = formatas;
		nr_esamu++;
	}
}
