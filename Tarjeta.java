/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica3;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author gae34
 */
public abstract class Tarjeta {

    private String titular;
    private String numero;      
    private String clabe;
    private int nip;
    private String banco;
    private String expiracion;  
    private int cvv;
    private double saldo;
    private boolean activo;
    private ArrayList<String> movimientos = new ArrayList<>();

    public Tarjeta(String titular, int nip, String banco) {
        this.titular = titular;
        this.nip = nip;
        this.banco = banco;
        this.numero = generarNumero(16);
        this.clabe = generarNumero(18);
        this.expiracion = generarExpiracion();
        this.cvv = generarCvv();
        this.saldo = 0;
        this.activo = true;
    }

    private String generarNumero(int digitos) {
        Random r = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < digitos; i++) {
            sb.append(r.nextInt(10));
        }
        return sb.toString();
    }

    private String generarExpiracion() {
        Random r = new Random();
        int mes = 1 + r.nextInt(12);
        int anio = 26 + r.nextInt(6); 
        return String.format("%02d/%02d", mes, anio);
    }

    private int generarCvv() {
        Random r = new Random();
        return 100 + r.nextInt(900);
    }

    public void verInfoTarjeta() {
        System.out.println("|-----------------------------------------|");
        System.out.println("Titular:      " + titular);
        System.out.println("Numero:       **** **** **** " + getUltimosDigitos());
        System.out.println("Banco:        " + banco);
        System.out.println("Expiracion:   " + expiracion);
        System.out.println("Saldo:        $" + saldo);
        System.out.println("Estado:       " + (activo ? "Activa" : "Bloqueada"));
        System.out.println("|-----------------------------------------|");
    }

    public void verMovimientos() {
        if (movimientos.isEmpty()) {
            System.out.println("no hay movimientos registrados todavia");
            return;
        }
        System.out.println("|------------- Movimientos ----------------|");
        for (String m : movimientos) {
            System.out.println("- " + m);
        }
    }

    protected void registrarMovimiento(String descripcion) {
        movimientos.add(descripcion);
    }

    public String getUltimosDigitos() {
        return numero.substring(numero.length() - 4);
    }

    // ---------- Getters y setters ----------

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public String getNumero() {
        return numero;
    }

    public String getClabe() {
        return clabe;
    }

    public int getNip() {
        return nip;
    }

    public void setNip(int nip) {
        this.nip = nip;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getExpiracion() {
        return expiracion;
    }

    public int getCvv() {
        return cvv;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public boolean isActive() {
        return activo;
    }

    public void setActive(boolean activo) {
        this.activo = activo;
    }
}