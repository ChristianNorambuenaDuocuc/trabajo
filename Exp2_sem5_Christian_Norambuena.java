/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package semana5;
import java.util.Scanner;
/**
 *
 * @author tazja
 */
public class Semana5 {
//variables static
static String teatro="Teatro Moro";
static int NumeroEntradas=4;
static double acumulador;
//variables de instancia
int vipvalor=30000;
int PlateaAlta=18000;
int PlateaBaja=15000;
int Palco=13000;

    //Main
    public static void main(String[] args) {
    //venta de entradas
    Scanner tc=new Scanner(System.in);
   
    //variables locales
    int vip=0;
    int PlateaAlta=0;
    int PlateaBaja=0;
    int Palco=0;
    int num0;
    double descuento;
    acumulador=0;
    int contar=0;
    double entrada1=0;
    double entrada2=0;
    double entrada3=0;
    double entrada4=0;
    int entradaNombre1=0;
    int entradaNombre2=0;
    int entradaNombre3=0;
    int entradaNombre4=0;
    
    //Saludo de bienvenida
    System.out.println("Bienvenidos al "+teatro+"!!!!"+"entradas disponibles "+NumeroEntradas );
    
    //Cantidad de entradas a comprar
    do {
        System.out.println("Cuantas entradas quiere comprar(1-4)");
        num0=tc.nextInt();
    }while(num0<=0 || num0>=5);
            
    //Tipo de entrada a comprar
    int i;
    for (i=1;i<=num0;i++){
        System.out.println("¿que tipo de entrada quiere comprar?");
        System.out.println("1.VIP");
        System.out.println("2.PlateaAlta");
        System.out.println("3.PlateaBaja");
        System.out.println("4.Palco");
        int num1=tc.nextInt();
   
    switch (num1){
        case 1:
        vip=30000;
        break;
        case 2:
        PlateaAlta=18000;
        break;
        case 3:
        PlateaBaja=15000;
        break;
        case 4:
        Palco=13000;
        break;
    }
    System.out.println("¿Que edad tiene?");
    int num2=tc.nextInt();
    
    
    if (num2<=18){
        descuento=0.1;
    }else if (num2>=60){
        descuento=0.15;
    }else{
        descuento=1;
    }
    contar=contar++;
    
    double PrecioFinal=(vip+PlateaAlta+PlateaBaja+Palco)*(1-descuento);
    acumulador=acumulador+PrecioFinal;
    
    if(i==1){
        entradaNombre1=num1;}
    else if(i==2){
        entradaNombre2=num1;}
    else if(i==3){
        entradaNombre3=num1;}
    else if (i==4){
        entradaNombre4=num1;}
    
    if(i==1){
        entrada1=PrecioFinal;}
    else if(i==2){
        entrada2=PrecioFinal;}
    else if(i==3){
        entrada3=PrecioFinal;}
    else if (i==4){
        entrada4=PrecioFinal;}
    }    
    
    //Promociones de entradas
    System.out.println("Hay promociones solo por hoy!!! lleva 4 entradas y te llevas un paquete de papas fritas gratis!!!");
    System.out.println("!!!!!!");
    System.out.println("");
    
     //Eliminaciòn de entradas
    System.out.println("Si quiere eliminar una entrada, Ingrese el numero de la entrada(1-4), en caso de continuar ingrese numero 5");
    int eliminar=tc.nextInt();
    switch (eliminar){
        case 1:
        entrada1=0;
        break;
        case 2:
        entrada2=0;
        break;
        case 3:
        entrada3=0;
        break;
        case 4:
        entrada4=0;
        break;
        default:
        System.out.println("Continuar....");
    }
    
    //Busqueda de entrada
    String n1="";
    String n2="";
    String n3="";
    String n4="";
    
    
    switch (entradaNombre1){
        case 1:
            n1="vip";
            break;
        case 2:
            n1="PlateaAlta";
            break;
        case 3:
            n1="PlateaBaja";
            break;
        case 4:
            n1="Palco";
            break;}
               
    switch (entradaNombre2){
        case 1:
            n2="vip";
            break;
        case 2:
            n2="PlateaAlta";
            break;
        case 3:
            n2="PlateaBaja";
            break;
        case 4:
            n2="Palco";
            break;}
    
    switch (entradaNombre3){
        case 1:
            n3="vip";
            break;
        case 2:
            n3="PlateaAlta";
            break;
        case 3:
            n3="PlateaBaja";
            break;
        case 4:
            n3="Palco";
            break;}
    
    switch (entradaNombre4){
        case 1:
            n4="vip";
            break;
        case 2:
            n4="PlateaAlta";
            break;
        case 3:
            n4="PlateaBaja";
            break;
        case 4:
            n4="Palco";
            break;}
         
    int busqueda;
           
    
    do {
    System.out.println("Busqueda de entrada:(1-4), si quiere salir ingrese numero 5");
    busqueda=tc.nextInt();
    switch (busqueda){
        case 1:
            System.out.println("Numero: "+busqueda );
            System.out.println("Ubicacion: "+n1);
            break;
        case 2:
            System.out.println("Numero: "+busqueda );
            System.out.println("Ubicacion: "+n2);
            break;
        case 3:
            System.out.println("Numero: "+busqueda );
            System.out.println("Ubicacion: "+n3);
            break;
        case 4:
            System.out.println("Numero: "+busqueda );
            System.out.println("Ubicacion: "+n4);
            break;
        case 5:
            System.out.println("Continuar.....");}
    }while (busqueda>=1 && busqueda<=4);
          
    
    
    //Precios finales
    System.out.println("");
    System.out.println("Los valores finales de las entradas son:");
    System.out.println("Precio de la entrada 1:"+entrada1);
    System.out.println("Precio de la entrada 2:"+entrada2);
    System.out.println("Precio de la entrada 3:"+entrada3);
    System.out.println("Precio de la entrada 4:"+entrada4);    
    System.out.println("El precio final: "+acumulador);
    
    
      }
     }           
    
        
  
   

