/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica3;

/**
 *
 * @author gae34
 */
public class Credito extends Tarjeta implements Transacciones {

    private double limiteCredito;
    private double deudaActual;
    private int fPago; 

    public Credito(String titular, int nip, String banco, double limiteCredito, int fPago) {
        super(titular, nip, banco);
        this.limiteCredito = limiteCredito;
        this.deudaActual = 0;
        this.fPago = fPago;
    }

    @Override
    public void pagar(double monto) {
        if (!isActive()) {
            System.out.println("La tarjeta esta bloqueada.");
            return;
        }
        if (monto <= 0) {
            System.out.println("Ingrese un monto valido.");
            return;
        }
        deudaActual -= monto;
        if (deudaActual < 0) {
            deudaActual = 0;
        }
        registrarMovimiento("Pago de $" + monto);
        System.out.println("Pago realizado. Deuda actual: $" + deudaActual);
    }

    @Override
    public void comprar(double monto) {
        if (!isActive()) {
            System.out.println("La tarjeta esta bloqueada.");
            return;
        }
        if (monto <= 0) {
            System.out.println("Ingrese un monto valido.");
            return;
        }
        if (deudaActual + monto > limiteCredito) {
            System.out.println("Compra rechazada: excede el limite de credito disponible.");
            return;
        }
        deudaActual += monto;
        registrarMovimiento("Compra de $" + monto);
        System.out.println("Compra realizada. Deuda actual: $" + deudaActual);
    }

    @Override
    public void extender(double nuevoLimite) {
        if (nuevoLimite <= limiteCredito) {
            System.out.println("El nuevo limite debe ser mayor al limite actual ($" + limiteCredito + ").");
            return;
        }
        limiteCredito = nuevoLimite;
        System.out.println("Limite de credito extendido a $" + limiteCredito);
    }

    @Override
    public void reportarRobo() {
        setActive(false);
        System.out.println("Tarjeta reportada como robada. La tarjeta ha sido bloqueada.");
    }

    @Override
    public void bloquear() {
        setActive(false);
        System.out.println("Tarjeta bloqueada.");
    }

    @Override
    public void desbloquear() {
        setActive(true);
        System.out.println("Tarjeta desbloqueada.");
    }

    @Override
    public void consultar() {
        System.out.println("Limite de credito: $" + limiteCredito);
        System.out.println("Deuda actual:      $" + deudaActual);
        System.out.println("Credito disponible: $" + (limiteCredito - deudaActual));
        System.out.println("Dia de pago:        " + fPago);
    }
    @Override
    public void retirar(double monto) {
        System.out.println("Esta operacion no aplica para tarjetas de credito.");
    }

    @Override
    public void transferir(double monto, String destino) {
        System.out.println("Esta operacion no aplica para tarjetas de credito.");
    }

    @Override
    public void depositar(double monto) {
        System.out.println("Esta operacion no aplica para tarjetas de credito.");
    }

    // ---------- Getters y setters ----------

    public double getLimiteCredito() {
        return limiteCredito;
    }

    public void setLimiteCredito(double limiteCredito) {
        this.limiteCredito = limiteCredito;
    }

    public double getDeudaActual() {
        return deudaActual;
    }

    public int getfPago() {
        return fPago;
    }

    public void setfPago(int fPago) {
        this.fPago = fPago;
    }
}   