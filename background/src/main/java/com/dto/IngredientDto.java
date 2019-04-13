package com.dto;

import com.pojo.Factory;

import java.util.List;

public class IngredientDto {
    private int id;
    private String type;
    private String source;
    private String inportDate;
    private Factory factory;
    private String status;
    private float amount;
    private List<ImportDto> _imports;

    public List<ImportDto> get_imports() {
        return _imports;
    }

    public void set_imports(List<ImportDto> imports) {
        this._imports = imports;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getInportDate() {
        return inportDate;
    }

    public void setInportDate(String inportDate) {
        this.inportDate = inportDate;
    }

    public Factory getFactory() {
        return factory;
    }

    public void setFactory(Factory factory) {
        this.factory = factory;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
}
