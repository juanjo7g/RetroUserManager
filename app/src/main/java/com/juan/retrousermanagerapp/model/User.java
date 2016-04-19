package com.juan.retrousermanagerapp.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by juan on 6/03/16.
 */
@DatabaseTable
public class User {

    public static final String ID = "id";
    public static final String _ID = "_id";
    public static final String NAME = "name";
    public static final String AGE = "age";
    public static final String SYNC = "sync";

    @DatabaseField(generatedId = true, columnName = ID)
    private Integer id;
    @DatabaseField(columnName = _ID)
    private String _id;
    @DatabaseField(columnName = NAME)
    private String name;
    @DatabaseField(columnName = AGE)
    private Integer age;
    @DatabaseField(columnName = SYNC)
    private Boolean sync;

    @Override
    public String toString() {
        return "Usuario " + _id + "\n" + name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean getSync() {
        return sync;
    }

    public void setSync(Boolean sync) {
        this.sync = sync;
    }
}
