package pl.gren.oze_app.model.db.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;
import java.util.*;
import java.math.BigDecimal;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "inverter")
public class Inverter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "efficiency_percent")
    private BigDecimal efficiencyPercent;

    @Column(name = "phases")
    private Integer phases;

    @Column(name = "mppt")
    private Integer mppt;

    @Column(name = "catalogue")
    private BigDecimal catalogue;

    @Column(name = "net")
    private BigDecimal net;

    @Column(name = "gross")
    private BigDecimal gross;

    @Column(name = "nominal_watts")
    private BigDecimal nominalWatts;

    @Column(name = "min_watts")
    private BigDecimal minWatts;

    @Column(name = "max_watts")
    private BigDecimal maxWatts;

    @Column(name = "percent")
    private BigDecimal percent;

    @Column(name = "max_voltage")
    private String maxVoltage;

    @Column(name = "ac_protections")
    private String acProtections;

    @Column(name = "warranty")
    private String warranty;

    @Column(name = "image_id")
    private Integer image;

    @Column(name = "max_adc")
    private BigDecimal maxAdc;

    @Column(name = "range_dc")
    private BigDecimal rangeDc;

    @Column(name = "min_dc")
    private BigDecimal minDc;

    @Column(name = "thdi_hz")
    private BigDecimal thdiHz;

    @Override
    public String toString() {
        return "Inverter{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", efficiencyPercent=" + efficiencyPercent +
                ", phases=" + phases +
                ", mppt=" + mppt +
                ", catalogue=" + catalogue +
                ", net=" + net +
                ", gross=" + gross +
                ", nominalWatts=" + nominalWatts +
                ", minWatts=" + minWatts +
                ", maxWatts=" + maxWatts +
                ", percent=" + percent +
                ", maxVoltage='" + maxVoltage + '\'' +
                ", acProtections='" + acProtections + '\'' +
                ", warranty='" + warranty + '\'' +
                ", image=" + image +
                ", maxAdc=" + maxAdc +
                ", rangeDc=" + rangeDc +
                ", minDc=" + minDc +
                ", thdiHz=" + thdiHz +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Inverter that = (Inverter) o;
        return Objects.equals(this.id, that.id);
    }

    @Override
    public int hashCode() {
        return 14;
    }
}
