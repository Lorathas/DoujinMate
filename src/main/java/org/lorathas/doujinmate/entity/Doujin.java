package org.lorathas.doujinmate.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "Doujins")
public class Doujin {

    @Id
    @Column(columnDefinition = "BINARY(16)", updatable = false, nullable = false)
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    @Column
    @NotNull
    private String name;
    @Column
    @NotNull
    private String path;
    //SHA-256 file hash
    @Column(columnDefinition = "BINARY(32)")
    private Byte[] shaHash;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "DoujinTags", joinColumns = {@JoinColumn(name = "doujinId")}, inverseJoinColumns = {@JoinColumn(name = "tagId")})
    private Set<Tag> tags = new HashSet<>();

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Byte[] getShaHash() {
        return shaHash;
    }

    public void setShaHash(Byte[] shaHash) {
        this.shaHash = shaHash;
    }
}
