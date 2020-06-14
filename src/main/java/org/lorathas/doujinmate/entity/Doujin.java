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

    public Doujin() {
    }

    public Doujin(@NotNull String name, @NotNull String path) {
        this.name = name;
        this.path = path;
    }

    public Doujin(@NotNull String name, @NotNull String path, byte[] shaHash) {
        this.name = name;
        this.path = path;
        this.shaHash = shaHash;
    }

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
    private byte[] shaHash;

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

    public byte[] getShaHash() {
        return shaHash;
    }

    public void setShaHash(byte[] shaHash) {
        this.shaHash = shaHash;
    }
}
