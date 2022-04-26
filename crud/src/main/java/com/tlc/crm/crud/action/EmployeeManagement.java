package com.tlc.crm.crud.action;

import com.tlc.commons.json.Json;
import com.tlc.commons.json.JsonArray;
import com.tlc.commons.json.JsonObject;
import com.tlc.crm.common.action.secure.CrmConfigAction;
import com.tlc.crm.common.config.ConfigManager;
import com.tlc.crm.crud.api.EmployeeManager;
import com.tlc.crm.crud.model.Employee;
import com.tlc.crm.crud.validation.HibernateValidation;
import com.tlc.validator.type.Group.Update;
import com.tlc.validator.type.Group.Create;
import com.tlc.web.WebAction;

import java.util.ArrayList;
import java.util.Collection;

/**
 * EmployeeManageMent is manages employees.
 *
 * @author KarthickV
 */
@WebAction(path = "/Employee/mgmt")
public class EmployeeManagement extends CrmConfigAction<Employee> {

    /**
     * Construct the employee from jsonObject.
     *
     * @param jsonObject
     * @return Employee
     */
    @Override
    public Employee construct(final JsonObject jsonObject) {
        return null;
    }

    /**
     * construct the jsonObject
     *
     * @param jsonObject
     * @return Collection<Employee>
     */
    public Collection<Employee> constructArray(final JsonObject jsonObject) {
        final Collection<Employee> employeeList = new ArrayList<>();
        final JsonArray jsonArray = jsonObject.getJsonArray("data");

        for (int i = 0; i < jsonArray.size(); i++) {
            final JsonObject newJsonObject = jsonArray.getJsonObject(i);

            final Long id = newJsonObject.optLong("id", 0);
            final String name = newJsonObject.optString("name", null);
            final String age = newJsonObject.optString("age", null);
            final String type = jsonObject.getString("type");

            final Employee employee = new Employee(id, name, age);

            if (type.equals("create") || type.equals("update")) {

                try {
                    HibernateValidation.validate(employee, getGroups(type));
                    employeeList.add(employee);
                } catch (Exception exception) {
                    System.out.println(exception);
                }
            }
        }
        return employeeList;
    }

    /**
     * Get groups.
     *
     * @param type
     * @return Class
     */
    private Class getGroups(final String type) {
        if (type.equals("create")) {
            return Create.class;
        } else {
            return Update.class;
        }
    }

    /**
     * Construct the employee.
     *
     * @param employee
     * @return JsonObject
     */
    @Override
    public JsonObject construct(final Employee employee) {
        final JsonObject jsonObject = Json.object();

        jsonObject.put("id", employee.id());
        jsonObject.put("name", employee.getName());
        jsonObject.put("age", employee.getAge());

        return jsonObject;
    }

    /**
     * Get the ConfigManager.
     *
     * @return ConfigManager.
     */
    @Override
    public ConfigManager<Employee> getConfigManager() {
        return EmployeeManager.getInstance();
    }
}
