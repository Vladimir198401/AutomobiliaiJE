package autoy;

import java.io.*;

public class AutoPagrindine {
	
	public static void main ( String[] args ) {
		
		String s;
		Auto[] automob = new Auto[10]; 
		
		InputStreamReader stream = new InputStreamReader(System.in);
		
		BufferedReader reader=new BufferedReader(stream);	
		
		System.out.println ( " Masinu ir autobusus judejimas:" );
		
		try {
				
				FileReader fr=new FileReader( "list_auto.csv" );
				BufferedReader frx = new BufferedReader ( fr );
	
				while ( ( s = frx.readLine() ) != null ) {
			
			// cikle skaitysim po viena eilutę
					
					String[] ss = s.split ( "," );
					
					//System.out.println ( ss [ 0 ] + ":" + ss[ 1 ] + ":" + ss [ 2 ] ); 
					
					list_auto [ k ] = new Auto ( ss [ 0 ], Double.parseDouble ( ss [ 1 ] ), Double.parseDouble ( ss [ 2 ] ) );
	
					k++;
					
				}
				fr.close();  
				
				i = 0;
				System.out.println ( " Nuskaitomas transportas is failo: " );
	
				while ( i < k ) {
					
					System.out.println ( list_auto[ i ].present()  );
				
					i++;
				}
			
			// ja skaidom ir jei s[0] automobilis kurima objekta Auto jei autobusas Autobusas
			
			if ( s[ 0 ] == 'automobilis' ) {
			
				automob[ i ].pavadinti( s[ 1 ] );
				automob[ i ].keistiGreiti( s[ 2 ]);
				
			} else {
	
				if ( s[ 0 ] == 'autobusas' ) {
					
					// nuskaitom dar viena eilute
					String ss2 = s.split ( "," );
					
					nr_stoteliu = ss2.length / 3;
					
					Stoteles[] marsrutas = new Stoteles[ nr_stoteliu ];
					
					for (int  j = 0, int t = 0; j < nr_stoteliu * 3; j+=3, t++ ) {
						
						marsrutas [ t ].Stotele ( ss2 [ j ], Double.parseDouble( ss [ j + 1 ] ), Double.parseDouble(ss [ j + 2 ]) ); 
					}
					
					automob[ i ] = new Autobusas( marsrutas );
					automob[ i ].pavadinti( s[ 1 ] );
					automob[ i ].keistiGreiti( s[ 2 ]);	 
				}
			}
			System.out.println ();
			
			System.out.println ( " ivesti greiti " );
			s= reader.readLine();
			greitis = Double.parseDouble( s );
			
			System.out.println (  "ivesti greiti: \n greitis: " + greitis );
			
		} catch ( IOException e ) {
			
			System.out.println ( "Skaitymo klaida" );
		}
		System.out.println ( "--------------------------------------------------" );
		System.out.println ( "|            |        Nuvaziotas atstumas        |" );
		System.out.println ( "--------------------------------------------------" );
		System.out.println ( "|Transportas |     greitis     | laiko intervalas|" );
		System.out.println ( "--------------------------------------------------" );
		
		for ( i = 0; i < k; i++ ) {
			
			//System.out.println ( list_auto [ i ].present4Table() +  String.format("%10.0f|", list_auto [ i ].spejamasRez ( atstumas)));

			System.out.println ( "--------------------------------------------------" );
		}
	}

}
