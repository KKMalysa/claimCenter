package com.kkmalysa.claimcenter.model.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "motorcar")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Motorcar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //id autoincrementation
    private Long id;

    @Column(name = "brand", length = 80, unique = false, nullable = false)
    private  String brand;

    @Column(name = "model", length = 80, unique = false, nullable = false)
    private  String model;

    @Column(name = "vin", length = 80, unique = true, nullable = false)
    private  String vin;

    @Column(name = "year", unique = false, nullable = false)
    private String year;


    @OneToMany(mappedBy = "motorcar")
    @ToString.Exclude
    private List<Insurance> insuranceList;

    @ManyToOne
    @JoinColumn(name = "idCompanyClient", nullable = false)
    private CompanyClient companyClient;


    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Motorcar motorcar = (Motorcar) o;
        return getId() != null && Objects.equals(getId(), motorcar.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
