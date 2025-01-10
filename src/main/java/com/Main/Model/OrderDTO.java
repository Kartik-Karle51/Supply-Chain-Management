package com.Main.Model;

import java.util.List;

import org.hibernate.annotations.Collate;

import jakarta.persistence.Column;
public class OrderDTO {

    private int id;
    @Column(name = "Customer Id")
    private int CustId;

    @Column(name = "Supplier Name")
    private String supplier_name = "no supplier";

    @Column(name = "Customer Name")
    private String Customer_name;

    private String status;

    @Column(name = "Payment Status")
    private String Paymentstatus;

    // List of ProductDTO
    private List<ProductDTO> products;

    public OrderDTO(int id, int custId, String customer_name, String supplier_name, String status, String paymentstatus, List<ProductDTO> products) {
        this.id = id;
        this.CustId = custId;
        this.supplier_name = supplier_name;
        this.Customer_name = customer_name;
        this.status = status;
        this.Paymentstatus = paymentstatus;
        this.products = products;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustId() {
        return CustId;
    }

    public void setCustId(int custId) {
        CustId = custId;
    }

    public String getSupplier_name() {
        return supplier_name;
    }

    public void setSupplier_name(String supplier_name) {
        this.supplier_name = supplier_name;
    }

    public String getCustomer_name() {
        return Customer_name;
    }

    public void setCustomer_name(String customer_name) {
        Customer_name = customer_name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPaymentstatus() {
        return Paymentstatus;
    }

    public void setPaymentstatus(String paymentstatus) {
        Paymentstatus = paymentstatus;
    }

    public List<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDTO> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "OrderDTO [id=" + id + ", CustId=" + CustId + ", supplier_name=" + supplier_name + ", Customer_name=" + Customer_name + ", status="
                + status + ", Paymentstatus=" + Paymentstatus + ", products=" + products + "]";
    }
}
