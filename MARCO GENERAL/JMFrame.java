import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;
import java.awt.*;
import java.math.*;
import java.awt.event.*;
import java.util.*;
import javax.comm.*;
import java.io.*;

/** Clase principal, que manipula las variables de la decodificación del archivo DXF */
public class JMFrame extends JFrame implements ActionListener{

	//Componentes para la interfaz con el usuario
	static Container p;
	static JPanel p0 = new JPanel();
	static JPanel p1 = new JPanel();
	static JPanel p11 = new JPanel();
	static JPanel p2 = new JPanel();
	JLabel ejex = new JLabel(new ImageIcon("./images/ejex.gif"));
	JLabel ejey = new JLabel(new ImageIcon("./images/ejey.gif"));
	JLabel bandaV = new JLabel(new ImageIcon("./images/bandaV.gif"));
	JLabel bandaH = new JLabel(new ImageIcon("./images/bandaH.gif"));
	JLabel title = new JLabel("VENTANA DE MONITOREO DE ACTIVIDAD",JLabel.CENTER);
	static JButton c = new JButton("Cargar");
	static JButton d = new JButton("Dibujar");
	static JButton clean = new JButton("Limpiar");
	static JButton t = new JButton("Calcular");
	static JButton pi = new JButton("cerrar");
	JPanel can;


   boolean absoluto,pausado,ParoDefinitivo;
   int  tiempoDormir,Lineas,i;
   int Sec[];
   Contador timer;


	//herramientas para leer y decodificar un archivo
	StringTokenizer st,st1;
	String file,input,elemento;
	ArrayList<Line> ent = null;

	//ventana de abrir archivo
	abrir a;



	//constructor de la interfaz
	public JMFrame(){

		super("MONITOR PRINCIPAL DE ACTIVIDADES");

/***********************AMBIENTE GRAFICO*************************************/
		try{UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}
        catch(Exception exception){System.err.println("Error loading L&F: " + exception);}
		setVisible(true);
		setLocation(50,50);
/*******************************************************************************************/
        can = new JPanel();
        can.setBackground(Color.black);

        p = getContentPane();
		p.setLayout(new BorderLayout());
		p0.setLayout(new BorderLayout(5,5));


		p0.add("West",ejey);
		p0.add("North",bandaH);
		p0.add("South",ejex);
		p0.add("East",bandaV);
		p0.add("Center",can);

		p1.add("Center",c);
		p1.add("Center",d);
		p1.add("Center",clean);
		p1.add("Center",t);
		p1.add("Center",pi);

		p2.add("Center",title);

        p.add("Center",p0);
		p.add("North",title);
		p.add("South",p1);




		           JMenuBar BarraMenus = new JMenuBar();

				    BarraMenus.setVisible(true);
				    System.out.println("valor es:"+BarraMenus.getAlignmentY());
				   	setJMenuBar(BarraMenus);
			        BarraMenus.setBorderPainted(true);

					ImageIcon imageicon = new ImageIcon("images/iconos/icon1.gif");
			        ImageIcon imageicon1 = new ImageIcon("images/iconos/icon2.gif");
			        ImageIcon imageicon2 = new ImageIcon("images/iconos/icon3.gif");
			        ImageIcon imageicon3 = new ImageIcon("images/iconos/icon4.gif");
			        JMenu jmenu = new JMenu("Archivo");
			        JMenuItem jmenuitem0 = new JMenuItem("Abrir", imageicon);
			        JMenuItem jmenuitem1 = new JMenuItem("Guardar", imageicon1);
			        JMenuItem jmenuitem2 = new JMenuItem("Imprimir", imageicon2);
			        JMenuItem jmenuitem3 = new JMenuItem("Cerrar", imageicon3);
			        jmenu.add(jmenuitem0);
			        jmenu.add(jmenuitem1);
			        jmenu.addSeparator();
			        jmenu.add(jmenuitem2);
			        jmenu.add(jmenuitem3);
					jmenu.setVisible(true);
			        BarraMenus.add(jmenu);

			        ImageIcon imageicon4 = new ImageIcon("images/iconos/icon5.gif");
			        ImageIcon imageicon5 = new ImageIcon("images/iconos/icon6.gif");
			        ImageIcon imageicon6 = new ImageIcon("images/iconos/icon7.gif");
			        ImageIcon imageicon7 = new ImageIcon("images/iconos/icon8.gif");
			        ImageIcon imageicon8 = new ImageIcon("images/iconos/icon9.gif");
			        ImageIcon imageicon9 = new ImageIcon("images/iconos/icon10.gif");
			        ImageIcon imageicon10 = new ImageIcon("images/iconos/icon11.gif");
			        JMenu jmenu1 = new JMenu("Controles");
			        JMenuItem jmenuitem4  = new JMenuItem("Control de Temperatura", imageicon4);
			        JMenuItem jmenuitem5  = new JMenuItem("Control de Actuadores", imageicon5);
			        JMenuItem jmenuitem6  = new JMenuItem("Control de Alertas", imageicon6);
			        JMenuItem jmenuitem7  = new JMenuItem("Control de Velocidad", imageicon7);
			        JMenuItem jmenuitem8  = new JMenuItem("Control de Coordenadas", imageicon8);
			        JMenuItem jmenuitem9  = new JMenuItem("Control de Unidades", imageicon9);
			        JMenuItem jmenuitem10 = new JMenuItem("Control de Luces", imageicon10);
			        jmenu1.add(jmenuitem4);
			        jmenu1.add(jmenuitem5);
			        jmenu1.addSeparator();
			        jmenu1.add(jmenuitem6);
			        jmenu1.add(jmenuitem7);
			        jmenu1.add(jmenuitem9);
			        jmenu1.add(jmenuitem8);
			        jmenu1.add(jmenuitem10);
			        BarraMenus.add(jmenu1);

			        ImageIcon imageicon11 = new ImageIcon("images/iconos/icon12.gif");
			        ImageIcon imageicon12 = new ImageIcon("images/iconos/icon13.gif");
			        ImageIcon imageicon13 = new ImageIcon("images/iconos/icon14.gif");
			        ImageIcon imageicon14 = new ImageIcon("images/iconos/icon15.gif");
			        ImageIcon imageicon15 = new ImageIcon("images/iconos/icon16.gif");
			        ImageIcon imageicon16 = new ImageIcon("images/iconos/icon17.gif");
			        JMenu jmenu2 = new JMenu("Herramientas");
			        JMenuItem jmenuitem11 = new JMenuItem("Control Manual", imageicon11);
			        JMenuItem jmenuitem12 = new JMenuItem("tool2", imageicon12);
			        JMenuItem jmenuitem13 = new JMenuItem("tool3", imageicon13);
			        JMenuItem jmenuitem14 = new JMenuItem("Zoom para alejar", imageicon14);
			        JMenuItem jmenuitem15 = new JMenuItem("Cambiar contrase\361a y(o) usuario", imageicon15);
			        JMenuItem jmenuitem16 = new JMenuItem("Checkeo de sistema", imageicon16);
			        jmenu2.add(jmenuitem11);
			        jmenu2.add(jmenuitem12);
			        jmenu2.addSeparator();
			        jmenu2.add(jmenuitem13);
			        jmenu2.add(jmenuitem14);
			        jmenu2.add(jmenuitem15);
			        jmenu2.add(jmenuitem16);
			        BarraMenus.add(jmenu2);

				    JMenu jmenu3 = new JMenu("Ventana");
			        jmenu3.setMnemonic('r');
			        BarraMenus.add(jmenu3);
					JMenuItem jmenuitem17 = new JMenuItem("Ventana 800 x 600");
					jmenu3.add(jmenuitem17);
					JMenuItem jmenuitem18 = new JMenuItem("Ventana 1024 x 768");
			        jmenu3.add(jmenuitem18);

			        JMenu jmenu4 = new JMenu("INFO");
			        jmenu4.setMnemonic('i');
			        BarraMenus.add(jmenu4);

		                c.addActionListener(this);
		            	d.addActionListener(this);
		            	t.addActionListener(this);
		            	pi.addActionListener(this);
		            	clean.addActionListener(this);

		        	setContentPane(p);
		            p.validate();



	System.out.println("");
	System.out.println("******************* CONSOLA DEL INTERPRETE DE COMANDOS G&M*************** ");
    System.out.println("");
	absoluto  =  true;
	timer = new Contador();
    Sec = new int[1000];//lo maximo que puede tener una secuencia son 1000 lineas



		jmenuitem0.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent actionevent){cargarArchivo();}});


		jmenuitem3.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent actionevent){System.exit(0);}});

		jmenuitem11.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent actionevent){ }});//aqui se convocaba al control manual

        jmenuitem17.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent actionevent){setSize(566,540);
				 }});

		jmenuitem18.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent actionevent){setSize(895,740); }});

	}




	//metodo de ejecución
	public static void main(String[] args){
		//construye una interfaz
		JMFrame o = new JMFrame();
		WindowListener l = new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		};
		//establece las caracteristicas principales
		o.addWindowListener(l);
		o.pack();
		o.setSize(865,660);      //para configuracion de 1024  x 768
		o.setSize(566,540);      // para configuracion de 800 x 600
	    //can.setSize(400,400);
		o.setResizable(false);


	}






        public void cargarArchivo()
        {
		limpiar();//limpia la pantalla
	    load();
        imprime(input);
		System.out.println("********************FIN DEL ARCHIVO DE CODIGO NUMERICO*******************");
		System.out.println("DEPURANDO EL ARCHIVO ....");
		if(file!=null){check();}//si el archivo existe, lo analiza
        }








public void buscar()
{


}




	//controlador de acciones para los botones de la ventana

	public void actionPerformed(ActionEvent e){
		//botón de cargar
		if (e.getSource()==c){
        cargarArchivo();
		}

		//botón de dibujar
		if (e.getSource()==d){
       System.out.println("Llama al metodo dibujar");

		}

		//botón de trazar
		if (e.getSource()==t){
        System.out.println("Llama al metodo trazar");
		}

		//botón de limpiar
		if (e.getSource()==clean){
			System.out.println("Llama al metodo limpiar");
			limpiar();//limpia la pantalla
		}

		//botón de posición inicial
		if(e.getSource()==pi){
			System.out.println("inicia el puerto RS232");
			/*cutter.SerialPort.getPort();
			cutter.SerialPort.openPort();
			Inicial();
			AquiX=0;
			AquiY=0;
			cutter.SerialPort.closePort();*/
		}



}

//**********FIN DE METODOS DE INICIO DE PROGRAMA *******************





//METODO PARA CARGAR UN ARCHIVO NC

public void load(){
 System.out.println("metodo load activo");
  a = new abrir(this);
  file = a.getFile();
  if(file!=null)lee();
}
//********************************






//SOBRECARGA DE METODOS DE IMPRESION PARA MONIOTOREO DE VARIABLES*********

public void imprime(String dato){
System.out.println("la cadena es: "+dato);
}
public void imprime(int dato){
System.out.println("el entero es: "+dato);
}
public void imprime(double dato){
System.out.println("el racional es: "+dato);
}
public void imprime(Object dato){
System.out.println("el Objeto es: "+dato);
}

//************************************************************************



//*************ENCARGADO DE ALMACENAR STRING LA SECUENCIA DE COMANDOS*****
//guarda en el string "input" el contenido del archivo elegido
  public void lee(){
	  System.out.println("Ingreso al metodo lee");
   try{
	File di=new File(a.getDirectory(),(a.getFile()));
	System.out.println("1");
	char entrada[]=new char[new Long(di.length()).intValue()];
	System.out.println("2");
	FileReader archivo = new FileReader(di);
	System.out.println("3");
	archivo.read(entrada,0,new Long(di.length()).intValue()-1);
	System.out.println("4");
	input = new String(entrada);
	System.out.println("5");
	archivo.close();
	System.out.println("6");


}
catch(IOException e){System.out.println("Error "+e.toString());
System.out.println("7");
}
}
//************************************************************************




//********METODO QUE REALIZA UNA LISTA DE LOS COMANDOS EN UNA ENTIDAD*******************
//separa el código del archivo de texto en palabras individuales
			public void check(){
                System.out.println("metodo Check activo");
				//elementos que no se consideran palabras
				st=new StringTokenizer(input," \t\n\r\f!¡¿?{}[],;:@#$%^&*+/=()%",false);
              	st1=new StringTokenizer(input," \n\t\r\f!¡¿?{}[],:@#$%^&*+/=()%",false);
                int i = 0;

			   	//inicializa la lista de Comandos
				ent = new ArrayList<Line>();
                i = st.countTokens();
				String []lista = new String[i];
				imprime(i);
                System.out.println("variables del metodo Check inicializadas correctamente");
				//mientras no se gasten las palabras
				while (st.hasMoreTokens()){
                  //  Line temporal;
 				    elemento = st.nextToken();
				    ent.add(new Line(elemento));
				    i--;
					lista[i]= elemento;
					imprime(lista[i]);
                         }

RESETEAR();
			}

/****************************************************************************/





//***********FUNCIONES AUXILIARES MONITOREABLES****************

    //metodo para limpiar la pantalla
	public void limpiar(){
		System.out.println("metodo Limpiar activo");
		//can.clean();
		//limpiar board.
	}

	//metodo para llevar la cabeza de corte a la posición (0,0)
	public void Inicial(){
		System.out.println("metodo inicial activo");
	}

	//metodo para enviar el código de inicio de transmisión de archivo
	public void Empezar(){
		System.out.println("metodo empezar activo");
	}

	//metodo para enviar el código de final de transmisión de archivo
	public void Terminar(){
		System.out.println("metodo terminar activo");
	}


   public void RESETEAR()
   {
    System.out.println("metodo resetear activo");
   }


public void dibujar(){
System.out.println("metodo dibujar activo");
	}


}