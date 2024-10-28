package ec.gob.ambiente.enlisy.exportauthorization.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="unit_measurement", schema="biodiversity")
@Where(clause = "unme_status='t'")
public class UnitMeasurement implements Serializable {
    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "UNIT_MEASUREMENT_GENERATOR")
    @SequenceGenerator(name = "UNIT_MEASUREMENT_GENERATOR", initialValue = 1, sequenceName = "seq_unme_id", schema = "biodiversity", allocationSize = 1)
    @Column(name="unme_id")
    private Integer unmeId;

    @Getter
    @Setter
    @Column(name="unme_name")
    private String unmeName;

    @Getter
    @Setter
    @Column(name="unme_code")
    private String unmeCode;

    @Getter
    @Setter
    @Column(name="unme_decimal")
    private Integer unmeDecimal;

    @Getter
    @Setter
    @Column(name="unme_user_create")
    private String unmeUserCreate;

    @Getter
    @Setter
    @Column(name="unme_date_create")
    private Date unmeDateCreate;

    @Getter
    @Setter
    @Column(name="unme_user_update")
    private String unmeUserUpdate;

    @Getter
    @Setter
    @Column(name="unme_date_update")
    private Date unmeDateUpdate;

    @Getter
    @Setter
    @Column(name="unme_status")
    private Boolean unmeStatus;

    @Getter
    @Setter
    @Transient
    private String text;
}