/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package trabajosemana4;
import java.util.Scanner;
import java.util.InputMismatchException;

/**
 *
 * @author tazja
 */
public class Trabajosemana4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Despliegue mensaje de bienvenida
        System.out.println("Bienvenidos al teatro Moro!!");
        Scanner tc=new Scanner(System.in);
        int num1;
        int respuesta;
        double acumulador=0;
        double descuento;
        double preciovip=0;
        double preciopbaja=0;
        double preciopalta=0;
        double preciopalco=0;
        
        //Menu principal
        System.out.println("Elegir entre las siguientes opciones del menú:");
        System.out.println("1.Comprar entrada");
        System.out.println("2.Salir");
         num1=tc.nextInt();
        switch (num1){
                case 1:
                    System.out.println("Has elegido comprar entradas");
                    System.out.println();
                    break;
                case 2:
                    System.out.println("Has elegido salir del programa");
                    System.exit(0);
                    break;}
                         
        //Selección de tipo de entrada
        do {
        System.out.println("Seleccionar el tipo de entrada");
        System.out.println("1.VIP");
        System.out.println("2.Platea Baja");
        System.out.println("3.Platea Alta");
        System.out.println("4.Palco");
        int num2=tc.nextInt();
        int num3=0;
        
        //Ingreso de edad
        System.out.println("Ingrese su edad:");
        try{
        num3=tc.nextInt();
        } catch (InputMismatchException e){
        System.out.println("Solo puedes ingresar números enteros");
        }
        
        //Asignación de descuento
        if (num3>0 && num3<=18){
        descuento=0.10;
        }else if (num3>60){
        descuento=0.15;}
        else {
        descuento=0;
        }
        
         //Precios de entradas
        switch (num2){
            case 1:
                preciovip=30000-descuento*30000;
                break;
            case 2:
                preciopbaja=15000-descuento*15000;
                break;
            case 3:
                preciopalta=18000-descuento*18000;
                break;
            case 4:
                preciopalco=13000-descuento*13000;
                break;}
   
        //Suma de los totales acumulados
        acumulador=acumulador+preciovip+preciopbaja+preciopalta+preciopalco;
      
        //resumen individual
        if (num2==1){
        System.out.println("asiento: "+"vip");
        System.out.println("precio de la entrada: 30.000");
        System.out.println("descuento aplicado:"+descuento);}
        else if (num2==2){
        System.out.println("asiento: "+"Platea baja");
        System.out.println("precio de la entrada: 15.000");
        System.out.println("descuento aplicado:"+descuento);}
        else if (num2==3){
        System.out.println("asiento: "+"Platea alta");
        System.out.println("precio de la entrada: 18.000");
        System.out.println("descuento aplicado:"+descuento);}
        else if (num2==4){
        System.out.println("asiento: "+"Palco");
        System.out.println("precio de la entrada: 13.000");
        System.out.println("descuento aplicado:"+descuento);}
        
        System.out.println("Quiere  realizar otra compra?");
        System.out.println("1.Si");
        System.out.println("2.No");
        respuesta=tc.nextInt();
        
        }while (respuesta==1);
        
        //resumen de la compra
        System.out.println("Precio final a pagar: "+acumulador);
        
        }
        }
