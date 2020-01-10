package autoy;

import java.io.*;

public class AutoPagrindine {
	
	public static void main ( String[] args ) {
		
		int k = 0;
		String s;
		Auto[] automob = new Auto[10]; 
		double atstumas = 1000;
		double laiko_zingsnis = 10;
		double maza_paklaida = 0.001;

		InputStreamReader stream = new InputStreamReader(System.in);
		
		BufferedReader reader=new BufferedReader(stream);	
		
		System.out.println ( " Masinu ir autobusus judejimas:" );
		
		try {
				
				FileReader fr=new FileReader( "list_auto.csv" );
				BufferedReader frx = new BufferedReader ( fr );
	
				while ( ( s = frx.readLine() ) != null ) {
			
					// cikle skaitysim po viena eilutę
					
					String[] ss = s.split ( "," );
					
					System.out.println ( ss [ 0 ] + ":" + ss[ 1 ] + ":" + ss [ 2 ] );
					// ja skaidom ir jei s[0] automobilis kurima objekta Auto jei autobusas Autobusas
					
					
					if ( ss[ 0 ].equals ( "automobilis" ) ) {
					
						automob [ k ] = new Auto ( ss [ 1 ], Double.parseDouble ( ss [ 2 ] ) );
						k++;
						
					} else {
						
						if ( ss[ 0 ].equals ( "autobusas" ) )  {
							
							// nuskaitom dar viena eilute
							s = frx.readLine();
							String[] ss2 = s.split ( "," );
							System.out.println ( ss2 [ 0 ] + ":" + ss2[ 1 ] + ":" + ss2 [ 2 ] );
							
							int nr_stoteliu = ss2.length / 3;
							System.out.println ( "nr_stoteliu: " + nr_stoteliu );
							
							Stotele[] marsrutas = new Stotele[ nr_stoteliu ];
							
							int t = 0;
							
							for (int  j = 0; j < nr_stoteliu * 3; j+=3 ) {
								
								System.out.println ( "t: " + t );								
								
								marsrutas [ t ] = new Stotele ( ss2 [ j ], Double.parseDouble( ss2 [ j + 1 ] ), Double.parseDouble( ss2 [ j + 2 ] ) ); 
								t++;
							}
							
							automob[ k ] = new Autobusas( ss[ 1 ], Double.parseDouble( ss [ 2 ] ), marsrutas );
							k++;
						}
					}
				}
				
				fr.close();  

				System.out.println ( " Nuskaitytas " + k + " transportu is failo: " );
				
				int i = 0;
				
				while ( i < k ) {
					
					System.out.println ( automob [ i ].parodyti()  );
				
					i++;
				}				
			System.out.println ();
			/*
			System.out.println ( " atstumas? " );
			s= reader.readLine();
			atstumas = Double.parseDouble( s );

			System.out.println ( " laiko zingsnis? " );
			s= reader.readLine();
			laiko_zingsnis = Double.parseDouble( s );
			
			System.out.println ( " laiko paklaida? " );
			s= reader.readLine();
			maza_paklaida = Double.parseDouble( s );			
			*/
			System.out.println (  
								"ivesti dydziai\n atstumas: " + atstumas 
								+ "\n laiko_zingsnis: " + laiko_zingsnis 
								+ "\n laiko_paklaida: " + maza_paklaida
			);
			
		} catch ( IOException e ) {
			
			System.out.println ( "Skaitymo klaida" );
			System.out.println ( e.getMessage() );
		}
		
		String linija = "-----------";
		String antrasete = "";
		
		for (int i = 0; i < k; i++ ) {
		
			System.out.print ( String.format( " %10s |", automob[ i ].pav ) );
		}
		System.out.println ( linija );
		System.out.print   ( "| Laikas  |" );		
		
		System.out.print ( " |\n" );
		System.out.println ( "---------------------------------------------------" );
		
		boolean reikia_vazioti = true;
		double praejes_laikas = 0;
		
		while ( reikia_vazioti ) { 
			
			praejes_laikas += laiko_zingsnis;
		
			for (int i = 0; i < k; i++ ) {
				
				reikia_vazioti = false;
				
				if ( automob [ i ].nuvaziotas_atstumas() < atstumas ) {
					
					reikia_vazioti = true;
					double liko_nuvaziuoti = atstumas - automob [ i ].nuvaziotas_atstumas();
					
					double laiko_zingsnis_x = laiko_zingsnis;
					
					if ( automob[ i ].greitis * laiko_zingsnis > liko_nuvaziuoti ) {
						
						laiko_zingsnis_x = ( liko_nuvaziuoti / automob[ i ].greitis ) + maza_paklaida;
					}
					
					automob[ i ].judeti( laiko_zingsnis_x );		
				}				
			}
			
			if ( reikia_vazioti ) {
				
				System.out.print ( String.format( "| %7.0f |", praejes_laikas) );
				
				for (int i = 0; i < k; i++ ) {
					
					System.out.print ( String.format( " %10.0f |", automob [ i ].nuvaziotas_atstumas() ) );	
				}
				System.out.println ( "\n--------------------------------------------------" );
			}
		}
	}

}
