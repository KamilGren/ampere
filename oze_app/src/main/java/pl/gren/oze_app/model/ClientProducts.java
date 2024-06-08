package pl.gren.oze_app.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class ClientProducts {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "heat_pump_id")
    private HeatPump heatPump;

    @ManyToOne
    @JoinColumn(name = "co_buffer_tank_id")
    private COBufferTank coBufferTank;

    @ManyToOne
    @JoinColumn(name = "cwu_buffor_tank_id")
    private CWUBufforTank cwuBufforTank;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "other_product_table", joinColumns = @JoinColumn(name = "client_products_id"), inverseJoinColumns = @JoinColumn(name = "other_products_id"))
    private List<OtherProduct> otherProducts = new ArrayList<>();

    public ClientProducts() {
    }

    public ClientProducts(HeatPump heatPump) {
        this.heatPump = heatPump;
    }

    public ClientProducts(HeatPump heatPump, COBufferTank coBufferTank, CWUBufforTank cwuBufforTank) {
        this.heatPump = heatPump;
        this.coBufferTank = coBufferTank;
        this.cwuBufforTank = cwuBufforTank;
    }

//    public Set<Product> getProducts() {
//        return products;
//    }
//
//    public void setProducts(Set<Product> products) {
//        this.products = products;
//    }

    public void updateClientProducts(ClientProducts clientProducts) {

        if(clientProducts.getHeatPump() != null)
            this.heatPump = clientProducts.getHeatPump();
        if(clientProducts.getCoBufferTank() != null) {
            this.coBufferTank = clientProducts.getCoBufferTank();
        }
        if(clientProducts.getCwuBufforTank()!= null) {
            this.cwuBufforTank = clientProducts.getCwuBufforTank();
        }
    }

    public List<OtherProduct> getOtherProducts() {
        return otherProducts;
    }

    public void setOtherProducts(List<OtherProduct> otherProducts) {
        this.otherProducts = otherProducts;
    }

    public HeatPump getHeatPump() {
        return heatPump;
    }

    public void setHeatPump(HeatPump heatPump) {
        this.heatPump = heatPump;
    }

    public COBufferTank getCoBufferTank() {
        return coBufferTank;
    }

    public void setCoBufferTank(COBufferTank coBufferTank) {
        this.coBufferTank = coBufferTank;
    }

    public CWUBufforTank getCwuBufforTank() {
        return cwuBufforTank;
    }

    public void setCwuBufforTank(CWUBufforTank cwuBufforTank) {
        this.cwuBufforTank = cwuBufforTank;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
