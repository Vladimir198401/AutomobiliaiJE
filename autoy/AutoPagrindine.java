package autoy;

import java.io.*;

public class AutoPagrindine {
	
	public static void main ( String[] args ) {
		
		
		String s; //eilute
		Auto[] automob = new Auto[10]; //masivas
		double atstumas = 1000;   //kintameji
		double laiko_zingsnis = 10;
		double[] laiko_zingsniai = new double[100];
		double maza_paklaida = 0.001;
		int t = 0;
		int i = 0;
		int k = 0;

		InputStreamReader stream = new InputStreamReader(System.in);
		
		BufferedReader reader=new BufferedReader(stream);	
		
		System.out.println ( " Masinu ir autobusus judejimas:" );
		
		try {
			
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
								"ivesti dydziai\n "
								+ "atstumas: " + atstumas 
								+ "\n laiko_zingsnis: " + laiko_zingsnis 
								+ "\n laiko_paklaida: " + maza_paklaida
			);			
				
				FileReader fr=new FileReader( "list_auto.csv" );
				BufferedReader frx = new BufferedReader ( fr );
	
				while ( ( s = frx.readLine() ) != null ) {
			
					// cikle skaitysim po viena eilutę
					
					String[] ss = s.split ( "," );
					
					// ja skaidom ir jei s[0] automobilis kurima objekta Auto jei autobusas Autobusas
					
					if ( ss[ 0 ].equals ( "automobilis" ) ) {
					
						automob [ k ] = new Auto ( ss [ 1 ], Double.parseDouble ( ss [ 2 ] ) );
						k++;
						
					} else {
						
						if ( ss[ 0 ].equals ( "autobusas" ) )  {
																																		// nuskaitom dar viena eilute
							s = frx.readLine();
							String[] ss2 = s.split ( "," );
																								//System.out.println ( ss2 [ 0 ] + ":" + ss2[ 1 ] + ":" + ss2 [ 2 ] );							
							int nr_stoteliu = ss2.length / 3;
																																	//System.out.println (ss2.length/3);
							Stotele[] marsrutas = new Stotele[ nr_stoteliu + 1 ];
							
							for (int  j = 0; j < nr_stoteliu * 3; j+=3 ) {
								
								marsrutas [ t ] = new Stotele ( ss2 [ j ], Double.parseDouble( ss2 [ j + 1 ] ), Double.parseDouble( ss2 [ j + 2 ] ) ); 
																																			//System.out.println (j + 1 );
								t++;
							}
							marsrutas [ t ] = new Stotele ( "Galute stotele", atstumas, 10 );
							
							automob[ k ] = new Autobusas( ss[ 1 ], Double.parseDouble( ss [ 2 ] ), marsrutas );
							k++;
						}
					}
				}
				
				fr.close();  

				System.out.println ( " Nuskaitytas " + k + " transportu is failo: " );
				
				while ( i < k ) {
					
					System.out.println ( automob [ i ].parodyti()  );
				
					i++;
				}				
			System.out.println ();
			
		} catch ( IOException e ) {
			
			System.out.println ( "Skaitymo klaida" );
			System.out.println ( e.getMessage() );
		}

		
		KonsolesLentele konsoles_lenta = new KonsolesLentele ( k + 2 );
		
		konsoles_lenta.pridetiStulpeli( "Laikas", "%7.0f", 7);
		
		for ( i = 0; i < k; i++ ) {
		
			konsoles_lenta.pridetiStulpeli( automob[ i ].pav, "%10.0f", 10 );
		}
		konsoles_lenta.pridetiStulpeli( "Papildoma informacija apie autobuso busena", "%50s", 100);
		konsoles_lenta.suformuoti();
		
		System.out.println ( konsoles_lenta.eilute );		
		System.out.println ( konsoles_lenta.antraste );
		System.out.println ( konsoles_lenta.eilute );
		
		boolean reikia_vazioti = true; 								//boolean logine reiksme tiesa arba mielas.  true arba false / true iskart nustatoma kad veiktu
																																			// poto nustatom ribas
		double praejes_laikas = 0;
		
		/**
		 * Generuojam laiko zingsniu masyva
		 */
		i = 0;
		praejes_laikas = 0;

		while ( i < laiko_zingsniai.length ) {
			
			boolean neprideta_reiksme = true;
			
			for ( int r = 0; r < k; r++ ) {
				
				if ( automob[ r ].kasAs().equals ( "Autobusas" ) ) {
					
					for ( int o = 0; o < ( (Autobusas) automob[ r ] ).marsrutas.length; o++ ) {
						
						if ( ( (Autobusas) automob[ r ] ).marsrutas[ o ].atvykimo_laikas < ( praejes_laikas + laiko_zingsnis ) ) {
							
							laiko_zingsniai [ i ] = ( (Autobusas) automob[ r ] ).marsrutas[ o ].atvykimo_laikas;
							neprideta_reiksme = false;
						}
						if ( ( ( (Autobusas) automob[ r ] ).marsrutas[ o ].isvykimoLaikas() < ( praejes_laikas + laiko_zingsnis ) ) && neprideta_reiksme ) {
							
							laiko_zingsniai [ i ] = ( (Autobusas) automob[ r ] ).marsrutas[ o ].atvykimo_laikas;
							neprideta_reiksme = false;							
						}
					}
				}
			}
			if ( neprideta_reiksme ) {
				
				laiko_zingsniai [ i ] = laiko_zingsnis;
			}
			
			praejes_laikas += laiko_zingsniai [ i ];
			i++;
		}

		/**
		 * Vygdomas vaziavimo simuliavimas
		 */
		praejes_laikas = 0;
		int pajudejimai = 0;																					// apsauga nuo amzino ciklo ir laiko zingsniu skaitiklis
																							
		while ( reikia_vazioti && ( pajudejimai < 100 ) ) {  													// kol reikia nuvazioti tiesa kol nepasekia false
			
			praejes_laikas += laiko_zingsniai [ pajudejimai ];
			
			for ( i = 0; i < k; i++ ) {
				
				reikia_vazioti = false; 							//boolean logine reiksme tiesa arba mielas.  true arba false / sio atveju falsse kad sustotu.
				
				if ( automob [ i ].nuvaziotasAtstumas() < atstumas ) {						// tikrinam ar automobilis neprivaziuos pabaigos ( galutines vietos )
																							// sitame laiko intervale
					reikia_vazioti = true; 																							//  true nustatoma kad veiktu
					double liko_nuvaziuoti = atstumas - automob [ i ].nuvaziotasAtstumas();
					
					double laiko_zingsnis_x = laiko_zingsniai [ pajudejimai ];
					
					if ( automob[ i ].greitis * laiko_zingsnis > liko_nuvaziuoti ) {
						
						laiko_zingsnis_x = ( liko_nuvaziuoti / automob[ i ].greitis ) + maza_paklaida;
					}
					
					automob[ i ].judeti( laiko_zingsnis_x );		
				}
				pajudejimai++;													
			}
			
			if ( reikia_vazioti ) {  // jai reikia nuvazioti = tiesa arba mielas
				
				System.out.print ( String.format( "| %7.0f |", praejes_laikas) );
				
				for (i = 0; i < k; i++ ) {
					
					System.out.print ( String.format( " %" + konsoles_lenta.ilgiai [ i + 1 ] + ".0f |", automob [ i ].nuvaziotasAtstumas() ) );	
					
					if ( i == ( k - 1 ) ) {
						
						System.out.print ( String.format ( " %" + konsoles_lenta.ilgiai [ i + 1 ] + "s |", automob [ i ].parodytiPapildomaInfo() ) );
					}
			
				}
				System.out.println ( "\n" + konsoles_lenta.eilute );
			}
		}
	}

}
