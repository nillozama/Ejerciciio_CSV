
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Principal {
	
	//static List<String> listaGenerica=new ArrayList<String>();
	static List<String> listaUsuarios=new ArrayList<String>();
	static List<String> listaHashtags=new ArrayList<String>();
	static List<String> listaLugares=new ArrayList<String>();

	public static void main(String[] args) {

		boolean salirMenu=false;
		
		try {
			cargarFicheroCSV("palabras.csv");
		} catch (IOException e) {

			e.printStackTrace();
		}
		
		do{
			salirMenu=showMenu(salirMenu);
			
		}while(!salirMenu);
	}
	
	public static boolean showMenu(boolean salirMenu) {

		String usuario;
		String lugar;
		String hashtag;
		int indexSwitch;
		
		System.out.println("Programa pera ordenar listas Instagram.\n \n1.- Mostrar por pantalla tamaño y nombre de las listas.\r\n"
				+ "\r\n"
				+ "2.- Mostrar lista personas.\r\n"
				+ "\r\n"
				+ "3.- Mostrar lista hashtags.\r\n"
				+ "\r\n"
				+ "4.- Mostrar lista lugares.\r\n"
				+ "\r\n"
				+ "5.- Buscar usuario.\r\n"
				+ "\r\n"
				+ "6.- Buscar hashtag.\r\n"
				+ "\r\n"
				+ "7.- Buscar lugar.\r\n"
				+ "\r\n"
				+ "8.- Buscar hashtags más repetidos.\r\n"
				+ "\r\n"
				+ "0.- Salir.");
		
		indexSwitch=requireIntNumber("\r\nQue opción del menú quieres escoger?");
		
		switch(indexSwitch) {
		
		case 0:

			System.out.println("Gracias por utilitzar la aplicación. Adios!!");
			salirMenu=true;

			break;
			
		case 1:
			
			System.out.println("La lista de usuarios tiene un tamaño de "+listaUsuarios.size());
			System.out.println("La lista de hashtag tiene un tamaño de "+listaHashtags.size());
			System.out.println("La lista de lugares tiene un tamaño de "+listaLugares.size());
			
			break;
			
		case 2:

	        listaUsuarios.forEach(System.out::println);

			break;
			
		case 3:
			
	        listaHashtags.forEach(System.out::println);
			
			break;
			
		case 4:

	        listaLugares.forEach(System.out::println);

			break;
			
		case 5:

			usuario=requireString("Que usuario quieres buscar?");
			
			if (listaUsuarios.contains("@"+usuario)) {
				
				System.out.println("El usuario "+usuario +" si se encuentra en la lista.");
			}
			else {
				
				System.out.println("El usuario "+usuario +" no se encuentra en la lista.");
			}
			
			/*listaUsuarios
		    .stream()
		    .filter((cadena)->cadena.equals("@"+usuario))
		    .findFirst()
		    .ifPresentOrElse((texto)->System.out.println("El usuario "+texto+ " se encuentra en la lista."), ()->System.out.println("No se encuentra en la lista"));*/
			
			break;
				
		case 6:
			
			hashtag=requireString("Que hastag quieres buscar?");
			
			if (listaHashtags.contains("#"+hashtag)) {
				
				System.out.println("El hastag "+hashtag +" si se encuentra en la lista.");
			}
			else {
				
				System.out.println("El hastag "+hashtag +" no se encuentra en la lista.");
			}
			
			break;
			
		case 7:
			
			lugar=requireString("Que lugar quieres buscar?");
			
			if (listaLugares.contains(lugar)) {
				
				System.out.println("El lugar "+lugar +" si se encuentra en la lista.");
			}
			else {
				
				System.out.println("El lugar "+lugar +" no se encuentra en la lista.");
			}
			
			break;
			
		case 8:
			
			Set <String> listaOrdenadaHashtags=new TreeSet <String>(); 
			int count;

			Collections.sort(listaHashtags);
			
			for (String i : listaHashtags) {
				count=Collections.frequency(listaHashtags, i);
				listaOrdenadaHashtags.add(count+" "+i);
			}

			System.out.println(listaOrdenadaHashtags);

			break;
			
		default:
			
			System.out.println("Tienes que escoger un número de la lista.");
		}
		
		return salirMenu;
	}
	
	public static void cargarFicheroCSV (String fileName) throws IOException{
		
		FileReader fr = new FileReader(new File(fileName));
        BufferedReader br = new BufferedReader(fr);
        String linea = br.readLine();
        String palabra="";
        String [] parts;

        while (linea != null) {
            
          parts=linea.split(";");

          for (int i=0; i<10; i++) {
        	   
               palabra= parts[i].trim();
               
               if(palabra.charAt(0)=='@') {
            	   
            	   listaUsuarios.add(palabra);
               }
               else if (palabra.charAt(0)=='#') {
            	   
            	   listaHashtags.add(palabra);
               }
               else {
            	   
            	   listaLugares.add(palabra);
               }
               //listaGenerica.add(palabra);
           }

           linea = br.readLine();
        }
        
        br.close();
        fr.close();
        
        /*listaHashtags = listaGenerica.stream()
        		.filter(p -> p.startsWith("#"))
        		.collect(Collectors.toList());
        
        listaUsuarios = listaGenerica.stream()
        		.filter(p -> p.startsWith("@"))
        		.collect(Collectors.toList());
        
        listaLugares = listaGenerica.stream()
        		.filter(p -> !p.startsWith("#")&&!p.startsWith("@"))
        		.collect(Collectors.toList());*/
	}
	
	public static int requireIntNumber(String message) {
		
		Scanner sc=new Scanner(System.in);
		int num;
		
		System.out.println(message);
		num=sc.nextInt();
		
		return num;
	}
	
	public static String requireString(String message) {
		
		Scanner sc=new Scanner(System.in);
		String string;
		
		System.out.println(message);
		string=sc.nextLine();
		
		return string;
	}
}