package org.example.Model;

public class ParsedQueryFields {
    private String select;
    private String from;
    private String where;
    private String alias;

    public ParsedQueryFields(String select, String from, String where, String alias) {
        this.select = select;
        this.from = from;
        this.where = where;
    }

    public String getSelect() {
        return select;
    }

    public void setSelect(String select) {
        this.select = select;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getWhere() {
        return where;
    }

    public void setWhere(String where) {
        this.where = where;
    }
}
