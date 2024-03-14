import java.awt.*;

class Line implements Runnable{

int x1, y1,x2,y2;
Thread hilo;
String dato;

/*
public Line(){
	start();
	run();
		}

*/

public Line(String dato){
	this.dato = dato;

		}

public void run(){
try{hilo.sleep(1);}
catch (InterruptedException e) { hilo = null; return; }
		}

public void start(){
	hilo = new Thread();
	hilo.start();
		}

public synchronized void stop() {
    hilo = null;
          }

public void set_nodes_values(int x1, int y1){this.x1=x1;	this.y1=y1;}
public void set_values(int x2, int y2){this.x2=x2;	this.y2=y2;}
public void next(){this.x1=x2; this.y1=y2;}
public void reset(){this.x2=0;this.y2=0;this.x1=0;	this.y1=0;}
public int get_x1(){return this.x1;}
public int get_y1(){return this.y1;}
public int get_x2(){return this.x2;}
public int get_y2(){return this.y2;}
public String get_dato(){return this.dato;}



//@Override
public void paint_line(Graphics g){

	System.out.println("ingreso al nodo Line");

	g.drawLine(x1,y1,x2,y2);




   }



}