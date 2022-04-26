package com.tlc.crm.common.action.secure;

import com.tlc.commons.code.ErrorCode;
import com.tlc.commons.code.ErrorCodes;
import com.tlc.commons.json.Json;
import com.tlc.commons.json.JsonArray;
import com.tlc.commons.json.JsonObject;
import com.tlc.crm.common.action.CrmRequest;
import com.tlc.crm.common.config.ConfigManager;
import com.tlc.validator.TlcModel;

import java.util.Collection;

/**
 * @author Abishek
 * @version 1.0
 */
public abstract class CrmConfigAction<T extends TlcModel> extends CrmSecureAction {
    @Override
    public final JsonObject sProcess(CrmRequest request) {
        final JsonObject responseJson = Json.object();
        final JsonObject requestJson = request.getRequestJson();

        final String type = requestJson.getString("type");
        final JsonObject data = requestJson.getJsonObject("data");
        data.put("type", type);

        switch (type) {
            case "create" -> {
                Collection<T> model = constructArray(data);
                create(model);
            }
            case "update" -> {
                final T model = construct(data);
                update(model);
                responseJson.put("id", model.id());
            }
            case "delete" -> {
                final Long id = data.getLong("id");
                final T model = partialGet(id);
                delete(model);
            }
            case "exists" -> {
                final T model = construct(data);
                final boolean exists = exists(model);
                responseJson.put("found", exists);
            }
            default -> {
                final Long id = data.getLong("id");
                final T model = get(id);
                responseJson.put("id", construct(model));
            }
        }
        return responseJson;
    }

    public void create(T model) {
        getConfigManager().create(model);
    }

    public void create(Collection<T> model) {
        getConfigManager().create(model);
    }

    public void update(T model) {
        getConfigManager().update(model);
    }

    public void delete(T model) {
        getConfigManager().delete(model);
    }

    public boolean exists(T model) {
        return getConfigManager().exists(model);
    }

    public T partialGet(Long id) {
        return getConfigManager().partialGet(id);
    }

    public T get(Long id) {
        return getConfigManager().get(id);
    }

    public abstract ConfigManager<T> getConfigManager();

    public abstract JsonObject construct(T model);

    public abstract Collection<T> constructArray(JsonObject jsonObject);

    public abstract T construct(JsonObject jsonObject);
}
