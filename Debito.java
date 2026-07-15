/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica3;

/**
 *
 * @author gae34
 */
public class Debito extends Tarjeta implements Transacciones {

    private double limiteRetiro;   
    private double limiteDeposito; 

    public Debito(String titular, int nip, String banco, double limiteRetiro, double limiteDeposito) {
        super(titular, nip, banco);
        this.limiteRetiro = limiteRetiro;
        this.limiteDeposito = limiteDeposito;
    }

    
    public void retirar(double monto) {
        if (!isActive()) {
            System.out.println("La tarjeta esta bloqueada.");
            return;
        }
        if (monto <= 0) {
            System.out.println("Ingrese un monto valido.");
            return;
        }
        if (monto > limiteRetiro) {
            System.out.println("Excede el limite de retiro diario ($" + limiteRetiro + ").");
            return;
        }
        if (monto > getSaldo()) {
            System.out.println("Saldo insuficiente.");
            return;
        }
        setSaldo(getSaldo() - monto);
        registrarMovimiento("Retiro de $" + monto);
        System.out.println("Retiro exitoso. Saldo actual: $" + getSaldo());
    }

    @Override
    public void transferir(double monto, String destino) {
        if (!isActive()) {
            System.out.println("La tarjeta esta bloqueada.");
            return;
        }
        if (monto <= 0) {
            System.out.println("Ingrese un monto valido.");
            return;
        }
        if (monto > getSaldo()) {
            System.out.println("Saldo insuficiente.");
            return;
        }
        setSaldo(getSaldo() - monto);
        registrarMovimiento("Transferencia de $" + monto + " a cuenta " + destino);
        System.out.println("Transferencia exitosa. Saldo actual: $" + getSaldo());
    }

    @Override
    public void depositar(double monto) {
        if (!isActive()) {
            System.out.println("La tarjeta esta bloqueada.");
            return;
        }
        if (monto <= 0) {
            System.out.println("Ingrese un monto valido.");
            return;
        }
        if (monto > limiteDeposito) {
            System.out.println("Excede el limite de deposito diario ($" + limiteDeposito + ").");
            return;
        }
        setSaldo(getSaldo() + monto);
        registrarMovimiento("Deposito de $" + monto);
        System.out.println("Deposito exitoso. Saldo actual: $" + getSaldo());
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
        if (monto > getSaldo()) {
            System.out.println("Saldo insuficiente.");
            return;
        }
        setSaldo(getSaldo() - monto);
        registrarMovimiento("Compra de $" + monto);
        System.out.println("Compra realizada. Saldo actual: $" + getSaldo());
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
        System.out.println("Saldo:                     $" + getSaldo());
        System.out.println("Limite de retiro diario:   $" + limiteRetiro);
        System.out.println("Limite de deposito diario: $" + limiteDeposito);
    }

    

    @Override
    public void pagar(double monto) {
        System.out.println("Esta operacion no aplica para tarjetas de debito.");
    }

    @Override
    public void extender(double nuevoLimite) {
        System.out.println("Esta operacion no aplica para tarjetas de debito.");
    }

    // ---------- Getters y setters ----------

    public double getLimiteRetiro() {
        return limiteRetiro;
    }

    public void setLimiteRetiro(double limiteRetiro) {
        this.limiteRetiro = limiteRetiro;
    }

    public double getLimiteDeposito() {
        return limiteDeposito;
    }

    public void setLimiteDeposito(double limiteDeposito) {
        this.limiteDeposito = limiteDeposito;
    }
}