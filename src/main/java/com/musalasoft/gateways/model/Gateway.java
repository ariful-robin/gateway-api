package com.musalasoft.gateways.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.musalasoft.gateways.util.ValidIp;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@ToString(of = {"serialNo", "uid", "name", "ip"})
public class Gateway {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NonNull
    @Column(unique = true)
    private String serialNo;

    @NonNull
    private String name;

    @NonNull
    @ValidIp
    private String ip;

    @JsonIgnore
    @OneToMany(mappedBy = "gateway", cascade = CascadeType.MERGE)
    @Fetch(FetchMode.JOIN)
    private Set<Device> devices = new HashSet();
}
