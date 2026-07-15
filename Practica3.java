/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package practica3;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author gae34
 */
public class Practica3 {

    static ArrayList<Tarjeta> listaTarjetas = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            try{
            System.out.println("|----------------------------------|");
            System.out.println("|------------BIENVENIDO------------|");
            System.out.println("|(1).-Crear tarjeta----------------|");
            System.out.println("|(2).-Tarjetero--------------------|");
            System.out.println("|(0).-Salir------------------------|");
            System.out.println("|----------------------------------|");
            System.out.println("|-Elija una opcion: ");
            int opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 0:
                    System.out.println("|-Gracias por usar el sistema------|");
                    System.exit(0);
                case 1:
                    crearTarjeta();
                    break;
                case 2:
                    tarjetero();
                    break;
                default:
                    System.out.println("Opcion no valida");
            }
            }catch(Exception e){
                System.out.println("No pongas letras porfa");
                sc.nextLine();
            
            }
        }
    }
    public static void crearTarjeta() {
        String titular;
        int nip;
        String banco;

        System.out.println("|-----------------------------------------|");
        System.out.println("|-Que tipo de tarjeta te gustaria crear?: |");
        System.out.println("|(1).-Debito------------------------------|");
        System.out.println("|(2).-Credito-----------------------------|");
        System.out.println("|-Elija una opcion: ");
        int tipo = sc.nextInt();
        sc.nextLine();

        if (tipo != 1 && tipo != 2) {
            System.out.println("Tipo de tarjeta no valido");
            return;
        }

        while (true) {
            System.out.println("ingresa el nombre del titular de la cuenta: ");
            titular = sc.nextLine().trim();
            if (titular.isEmpty() == false) {
                break;
            }
        }

        while (true) {
            try {
                System.out.println("Ingrese su nuevo nip de seguridad (4 digitos)");
                nip = sc.nextInt();
                sc.nextLine();
                if (nip >= 1000 && nip <= 9999) {
                    break;
                } else {
                    System.out.println("Ingrese un nip valido de 4 digitos");
                }
            } catch (Exception ex) {
                sc.nextLine();
                System.out.println("Por favor ingrese unicamente numeros");
            }
        }

        while (true) {
            System.out.println("A que banco pertenece tu tarjeta: ");
            banco = sc.nextLine().trim();
            if (banco.isEmpty() == false) {
                break;
            }
        }

        if (tipo == 1) {
            double limiteRetiro = 10000;
            double limiteDeposito = 20000;
            Debito debito = new Debito(titular, nip, banco, limiteRetiro, limiteDeposito);
            listaTarjetas.add(debito);
            System.out.println("El resto de sus datos han sido asignados correctamente");
            System.out.println("Su tarjeta de debito ha sido creada con exito");
            debito.verInfoTarjeta();
        } else {
            double limiteCredito;
            int diaPago;

            while (true) {
                try {
                    System.out.println("Ingrese el limite de credito deseado: ");
                    limiteCredito = sc.nextDouble();
                    sc.nextLine();
                    break;
                } catch (Exception ex) {
                    sc.nextLine();
                    System.out.println("Por favor ingrese unicamente numeros.");
                }
            }

            while (true) {
                try {
                    System.out.println("Ingrese el dia de pago (1-31): ");
                    diaPago = sc.nextInt();
                    sc.nextLine();
                    break;
                } catch (Exception ex) {
                    sc.nextLine();
                    System.out.println("Por favor ingrese unicamente numeros");
                }
            }

            Credito credito = new Credito(titular, nip, banco, limiteCredito, diaPago);
            listaTarjetas.add(credito);
            System.out.println("El resto de sus datos han sido asignados correctamente");
            System.out.println("Su tarjeta de credito ha sido creada con exito");
            credito.verInfoTarjeta();
        }
    }

    public static void tarjetero() {
        if (listaTarjetas.isEmpty()) {
            System.out.println("No hay tarjetas registradas todavia");
            return;
        }

        System.out.println("|-----------------------------------------|");
        System.out.println("|--------------MUESTRA TARJETAS-----------|");
        for (int i = 0; i < listaTarjetas.size(); i++) {
            Tarjeta t = listaTarjetas.get(i);
            String tipoTarjeta = (t instanceof Credito) ? "Credito" : "Debito";
            System.out.println((i + 1) + ". " + t.getTitular() + " [" + tipoTarjeta + "] - **** " + t.getUltimosDigitos());
        }

        System.out.println("Ingresa los ultimos 4 digitos de la tarjeta: ");
        String digitos = sc.nextLine().trim();

        Tarjeta encontrada = null;
        for (Tarjeta t : listaTarjetas) {
            if (t.getUltimosDigitos().equals(digitos)) {
                encontrada = t;
                break;
            }
        }

        if (encontrada == null) {
            System.out.println("No se encontro ninguna tarjeta con esos digitos");
            return;
        }

        menuAcciones(encontrada);
    }

    public static void menuAcciones(Tarjeta t) {
        Transacciones tx = (Transacciones) t;

        while (true) {
            System.out.println("|-----------------------------------------|");
            t.verInfoTarjeta();
            System.out.println("|(1).-Consultar-----------------------------|");
            System.out.println("|(2).-Bloquear-------------------------------|");
            System.out.println("|(3).-Desbloquear------------------------------|");
            System.out.println("|(4).-Reportar robo-------------------------------|");
            System.out.println("|(5).-Ver movimientos---------------------------|");

            if (t instanceof Debito) {
                System.out.println("|(6).-Retirar----------------------------------|");
                System.out.println("|(7).-Depositar--------------------------------|");
                System.out.println("|(8).-Transferir--------------------------------|");
                System.out.println("|(9).-Comprar------------------------------------|");
            } else {
                System.out.println("|(6).-Pagar--------------------------------------|");
                System.out.println("|(7).-Comprar------------------------------------|");
                System.out.println("|(8).-Extender limite-----------------------------|");
            }

            System.out.println("|(0).-Regresar------------------------------------|");
            System.out.println("|-Elija una opcion: ");
            int opcion = sc.nextInt();
            sc.nextLine();

            if (opcion == 0) {
                break;
            }

            double monto;

            switch (opcion) {
                case 1:
                    tx.consultar();
                    break;
                case 2:
                    tx.bloquear();
                    break;
                case 3:
                    tx.desbloquear();
                    break;
                case 4:
                    tx.reportarRobo();
                    break;
                case 5:
                    t.verMovimientos();
                    break;
                case 6:
                    System.out.println("Monto: ");
                    monto = sc.nextDouble();
                    sc.nextLine();
                    if (t instanceof Debito) {
                        tx.retirar(monto);
                    } else {
                        tx.pagar(monto);
                    }
                    break;
                case 7:
                    System.out.println("Monto: ");
                    monto = sc.nextDouble();
                    sc.nextLine();
                    if (t instanceof Debito) {
                        tx.depositar(monto);
                    } else {
                        tx.comprar(monto);
                    }
                    break;
                case 8:
                    if (t instanceof Debito) {
                        System.out.println("Monto a transferir: ");
                        monto = sc.nextDouble();
                        sc.nextLine();
                        System.out.println("Cuenta destino: ");
                        String destino = sc.nextLine().trim();
                        tx.transferir(monto, destino);
                    } else {
                        System.out.println("Nuevo limite de credito: ");
                        monto = sc.nextDouble();
                        sc.nextLine();
                        tx.extender(monto);
                    }
                    break;
                case 9:
                    if (t instanceof Debito) {
                        System.out.println("Monto de la compra: ");
                        monto = sc.nextDouble();
                        sc.nextLine();
                        tx.comprar(monto);
                    } else {
                        System.out.println("Opcion no valida");
                    }
                    break;
                default:
                    System.out.println("Opcion no valida");
            }
        }
    }
}