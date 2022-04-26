package com.tlc.crm.crud.model;

import com.tlc.validator.TlcModel;
import com.tlc.crm.crud.validation.Age;
import com.tlc.validator.type.Group.Update;
import com.tlc.validator.type.Group.Create;
import com.tlc.crm.crud.validation.Name;

/**
 * Employee pojo.
 *
 * @author KarthickV
 */
public class Employee implements TlcModel {

    private Long id;

    @Name (groups = {Create.class, Update.class})
    private String name;

    @Age ( groups = {Create.class, Update.class})
    private String age;

    public Employee(Long id, String name, String age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Employee() {

    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public Long id() {
        return id;
    }

    @Override
    public Long orgId() {
        return null;
    }

    @Override
    public Object identity() {
        return null;
    }

    public String toString() {
        return String.format("id:%s \n name:%s \n age:%s \n\n", id, name,
                age);
    }
}
