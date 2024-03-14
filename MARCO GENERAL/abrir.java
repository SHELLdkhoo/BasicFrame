/** Esta clase invoca a la ventana estándar de abrir archivo de Windows */
import java.awt.*;

public class abrir extends FileDialog{

	public abrir(Frame f){

	/*	super(f,"Cargar un archivo .NC o .GC");
		setFile("*.DXF" );
		setSize(400,250);
		//show();
	}*/


	super(f,"CARGAR UN ARCHIVO DE CUALQUIER EXTENCION");
			setFile("*.*" );
		    setSize(400,250);
		    show();

	}



}