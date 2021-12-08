package com.rc.ali.Modelo;

public class Pesa {
    private String proveedor;
    private String producto;
    private String cantidad;
    private String fecha;
    private String timestamp;
    private String proveedor_timestamp;
    String key;

    public Pesa() {
    }


    public Pesa(String proveedor, String producto, String cantidad, String fecha, String timestamp, String proveedor_timestamp){
        this.proveedor = proveedor;
        this.producto = producto;
        this.cantidad = cantidad;
        this.fecha= fecha;
        this.timestamp = timestamp;
        this.proveedor_timestamp = proveedor_timestamp;
    }

    public String getProveedor(){
        return  proveedor;
    }
    public void setProveedor(String proveedor){
        this.proveedor = proveedor;
    }
    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }
    //
    public String getProducto() {
        return producto;
    }
    public void setProducto(String producto) {
        this.producto = producto;
    }
    //
    public String getCantidad() {
        return cantidad;
    }
    public void setCantidad(String cantidad) {
        this.cantidad= cantidad;
    }
    //
    public String getFecha() {
        return fecha;
    }
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    //
    public String getTimestamp() { return timestamp; }
    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }
    //
    public String getProveedor_timestamp() { return proveedor_timestamp; }

    public void setProveedor_timestamp(String proveedor_timestamp) { this.proveedor_timestamp = proveedor_timestamp; }
}
