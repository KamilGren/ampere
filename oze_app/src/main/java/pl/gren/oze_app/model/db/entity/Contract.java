package pl.gren.oze_app.model.db.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.gren.oze_app.model.db.entity.product.quantity.CentralHeatingBufferTankQuantity;
import pl.gren.oze_app.model.db.entity.product.quantity.DomesticHotWaterTankQuantity;
import pl.gren.oze_app.model.db.entity.product.quantity.HeatPumpQuantity;
import pl.gren.oze_app.model.db.entity.product.quantity.OtherProductQuantity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "contract")
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "building_info_id")
    private BuildingInfo buildingInfo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "salesman_id")
    private Salesman salesman;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToMany(mappedBy = "contract")
    private Set<HeatPumpQuantity> heatPumps = new HashSet<>();

    @OneToMany(mappedBy = "contract")
    private Set<DomesticHotWaterTankQuantity> cwuTanks = new HashSet<>();

    @OneToMany(mappedBy = "contract")
    private Set<CentralHeatingBufferTankQuantity> coBuffers = new HashSet<>();

    @OneToMany(mappedBy = "contract")
    private Set<OtherProductQuantity> otherProducts = new HashSet<>();

    @Column(name = "material_cost")
    private BigDecimal materialCost;

    @Column(name = "labor_cost")
    private BigDecimal laborCost;

    @Column(name = "markup")
    private BigDecimal markup;

    @Column(name = "tax_rate")
    private BigDecimal taxRate;

    @Column(name = "margin")
    private BigDecimal margin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tax_credit_id")
    private TaxCredit taxCredit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "financial_program_id")
    private FinancialProgram financialProgram;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Override
    public int hashCode() {
        return 12;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contract contract = (Contract) o;
        return Objects.equals(getId(), contract.getId()) && Objects.equals(getName(), contract.getName()) && Objects.equals(getBuildingInfo(), contract.getBuildingInfo()) && Objects.equals(getSalesman(), contract.getSalesman()) && Objects.equals(getClient(), contract.getClient()) && Objects.equals(getHeatPumps(), contract.getHeatPumps()) && Objects.equals(getCwuTanks(), contract.getCwuTanks()) && Objects.equals(getCoBuffers(), contract.getCoBuffers()) && Objects.equals(getOtherProducts(), contract.getOtherProducts()) && Objects.equals(getMaterialCost(), contract.getMaterialCost()) && Objects.equals(getLaborCost(), contract.getLaborCost()) && Objects.equals(getMarkup(), contract.getMarkup()) && Objects.equals(getTaxRate(), contract.getTaxRate()) && Objects.equals(getMargin(), contract.getMargin()) && Objects.equals(getTaxCredit(), contract.getTaxCredit()) && Objects.equals(getFinancialProgram(), contract.getFinancialProgram()) && Objects.equals(getCreatedAt(), contract.getCreatedAt());
    }

}
