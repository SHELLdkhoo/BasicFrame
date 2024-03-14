import java.awt.*;
import java.util.*;
import java.lang.*;
import java.io.*;
import java.applet.*;



//clase que funciona como Contador
class Contador  extends Thread {
  public static int tiempoInactivo;
   public static boolean fin;
  //EL Tiempo minimo de inactividad es 10 milisegundos

   public Contador(String name, int tiempo )
   {
      super( name );
      tiempoInactivo = tiempo;
      System.out.println(tiempo);
	  fin = false;
   }


   public Contador() {
       tiempoInactivo = 100;    // tiempo por defecto 100 milisegundos
       fin = false;
     }
	 
	 public static boolean fin()
	 {
	 return fin;
	  }
	  
	  
   public void run()
      {

         try {
            System.err.println( getName() +" de "+ (tiempoInactivo/1000) + " segundos"+ "  activada" );
            Thread.sleep( tiempoInactivo*1000 );
         }
         catch ( InterruptedException exception ) {
            System.err.println( exception.toString() );
         }

         // print thread name
         System.err.println( getName() +" de "+ (tiempoInactivo/1000) + " segundos"+  " durmiendo" );
         fin = true;
		 fin();
   }

}