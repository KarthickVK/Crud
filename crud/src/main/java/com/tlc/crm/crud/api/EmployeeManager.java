package com.tlc.crm.crud.api;

import com.tlc.commons.code.ErrorCode;
import com.tlc.crm.common.config.AuditEntry;
import com.tlc.crm.common.config.ConfigManager;
import com.tlc.crm.crud.model.Employee;
import com.tlc.crm.crud.status.EmployeeErrorCodes;
import com.tlc.sql.SQLAccess;
import com.tlc.sql.api.DataContainer;
import com.tlc.sql.api.Row;
import com.tlc.sql.api.dml.*;
import com.tlc.sql.api.ds.OrgDataStore;

import java.util.*;

/**
 * EmployeeManager.
 *
 * @author KarthickV
 */
public class EmployeeManager implements ConfigManager<Employee> {

    private static final Table TABLE = Table.get("EmployeeDetails");
    private static final OrgDataStore ORG_DATA_STORE = getOrgDataStore();

    private EmployeeManager() {
    }

    private static class Instance {
        private static final EmployeeManager INSTANCE = new EmployeeManager();
    }

    public static EmployeeManager getInstance() {
        return Instance.INSTANCE;
    }

    /**
     * Get OrgDataStore object
     *
     * @return OrgDataStore
     */
    private static OrgDataStore getOrgDataStore() {
        return SQLAccess.get().getOrgDataStore(1L);
    }

    /**
     * Create the employee.
     *
     * @param employee
     */
    @Override
    public void create(final Employee employee) {
        create(List.of(employee));
    }

    /**
     * Create the collection of employees.
     *
     * @param employee
     */
    public void create(final Collection<Employee> employee) {
        final DataContainer dataContainer = DataContainer.create();
        employee.forEach(employees -> {
            final Row row = new Row(TABLE);

            row.set("NAME", employees.getName());
            row.set("AGE", employees.getAge());

            dataContainer.addNewRow(row);
            ORG_DATA_STORE.commitChanges(dataContainer);
        });
    }

    /**
     * Update the employee
     *
     * @param employee
     */
    @Override
    public void update(final Employee employee) {
        final Row row = new Row(TABLE, employee.id());
        final DataContainer dataContainer = DataContainer.create();

        if (exists(employee)) {
            row.set("NAME", employee.getName());
            row.set("AGE", employee.getAge());

            dataContainer.updateRow(row);

            ORG_DATA_STORE.commitChanges(dataContainer);
        } else {
            throw ErrorCode.get(EmployeeErrorCodes.INVALID_ID);
        }
    }

    /**
     * Delete the employee
     *
     * @param employee
     */
    @Override
    public void delete(final Employee employee) {
        if (exists(employee)) {
            delete(List.of(employee));
        } else {
            throw ErrorCode.get(EmployeeErrorCodes.INVALID_ID);
        }
    }

    /**
     * Delete the employees
     *
     * @param employees
     */
    @Override
    public void delete(final Collection<Employee> employees) {
        final Collection<Long> list = new ArrayList<>();

        employees.forEach(employee -> list.add(employee.id()));

        ORG_DATA_STORE.delete(TABLE, list);
    }

    /**
     * PartialGet employee
     *
     * @param id
     * @return Employee
     */
    @Override
    public Employee partialGet(final Long id) {
        Employee employee = new Employee();

        employee.setId(id);

        return employee;
    }

    /**
     * Get the employee
     *
     * @param id
     * @return Employee
     */
    @Override
    public Employee get(final Long id) {
        final Employee employee = new Employee();
        final Row row = ORG_DATA_STORE.get(TABLE, id);

        employee.setId(row.get("ID"));
        employee.setName(row.get("NAME"));
        employee.setAge(row.get("AGE").toString());

        return employee;
    }

    @Override
    public Collection<Employee> get(final Collection<Long> id) {
        final Collection<Row> list = new ArrayList<>();
        final Collection<Employee> employees = new ArrayList<>();

        id.forEach(ids -> list.add(ORG_DATA_STORE.get(TABLE, ids)));

        for (final Row row : list) {
            final Employee employee = new Employee();

            employee.setId(row.get("ID"));
            employee.setName(row.get("NAME"));
            employee.setAge(row.get("AGE").toString());

            employees.add(employee);
        }
        return employees;
    }

    @Override
    public boolean exists(final Employee employee) {
        if (ORG_DATA_STORE.get(TABLE, employee.id()) != null) {
            return true;
        }
        return false;
    }

    @Override
    public AuditEntry auditEntry(Employee model) {
        return null;
    }
}


























