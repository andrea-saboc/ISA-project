package com.example.isa.dto;

public class AccountDeletionDTO {
    public Long id;
    public String name;
    public String surname;
    public String reason;
    public String advertiserType;
    public Boolean deleted;
    public Boolean notdeleted;

    public AccountDeletionDTO(Long id, String name, String surname, String reason, String advertiserType,Boolean deleted,Boolean notdeleted) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.reason = reason;
        this.advertiserType = advertiserType;
        this.deleted=deleted;
        this.notdeleted=notdeleted;
    }
    public AccountDeletionDTO(Long id)
    {
        this.id=id;
    }

    public AccountDeletionDTO() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getAdvertiserType() {
        return advertiserType;
    }

    public void setAdvertiserType(String advertiserType) {
        this.advertiserType = advertiserType;
    }
}
