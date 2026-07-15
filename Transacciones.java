/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package practica3;

/**
 *
 * @author gae34
 */
public interface Transacciones {

    void retirar(double monto);
    void transferir(double monto, String destino);
    void depositar(double monto);
    void pagar(double monto);
    void consultar();
    void bloquear();
    void desbloquear();
    void extender(double nuevoLimite);
    void reportarRobo();
    void comprar(double monto);
}