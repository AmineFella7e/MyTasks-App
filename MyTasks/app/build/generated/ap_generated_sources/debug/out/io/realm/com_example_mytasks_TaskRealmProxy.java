package io.realm;


import android.annotation.TargetApi;
import android.os.Build;
import android.util.JsonReader;
import android.util.JsonToken;
import io.realm.ImportFlag;
import io.realm.ProxyUtils;
import io.realm.exceptions.RealmMigrationNeededException;
import io.realm.internal.ColumnInfo;
import io.realm.internal.NativeContext;
import io.realm.internal.OsList;
import io.realm.internal.OsMap;
import io.realm.internal.OsObject;
import io.realm.internal.OsObjectSchemaInfo;
import io.realm.internal.OsSchemaInfo;
import io.realm.internal.OsSet;
import io.realm.internal.Property;
import io.realm.internal.RealmObjectProxy;
import io.realm.internal.Row;
import io.realm.internal.Table;
import io.realm.internal.android.JsonUtils;
import io.realm.internal.core.NativeRealmAny;
import io.realm.internal.objectstore.OsObjectBuilder;
import io.realm.log.RealmLog;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@SuppressWarnings("all")
public class com_example_mytasks_TaskRealmProxy extends com.example.mytasks.Task
    implements RealmObjectProxy, com_example_mytasks_TaskRealmProxyInterface {

    static final class TaskColumnInfo extends ColumnInfo {
        long idColKey;
        long titleColKey;
        long descriptionColKey;
        long categoryColKey;
        long priorityColKey;
        long dueDateColKey;
        long completedColKey;

        TaskColumnInfo(OsSchemaInfo schemaInfo) {
            super(7);
            OsObjectSchemaInfo objectSchemaInfo = schemaInfo.getObjectSchemaInfo("Task");
            this.idColKey = addColumnDetails("id", "id", objectSchemaInfo);
            this.titleColKey = addColumnDetails("title", "title", objectSchemaInfo);
            this.descriptionColKey = addColumnDetails("description", "description", objectSchemaInfo);
            this.categoryColKey = addColumnDetails("category", "category", objectSchemaInfo);
            this.priorityColKey = addColumnDetails("priority", "priority", objectSchemaInfo);
            this.dueDateColKey = addColumnDetails("dueDate", "dueDate", objectSchemaInfo);
            this.completedColKey = addColumnDetails("completed", "completed", objectSchemaInfo);
        }

        TaskColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        @Override
        protected final ColumnInfo copy(boolean mutable) {
            return new TaskColumnInfo(this, mutable);
        }

        @Override
        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            final TaskColumnInfo src = (TaskColumnInfo) rawSrc;
            final TaskColumnInfo dst = (TaskColumnInfo) rawDst;
            dst.idColKey = src.idColKey;
            dst.titleColKey = src.titleColKey;
            dst.descriptionColKey = src.descriptionColKey;
            dst.categoryColKey = src.categoryColKey;
            dst.priorityColKey = src.priorityColKey;
            dst.dueDateColKey = src.dueDateColKey;
            dst.completedColKey = src.completedColKey;
        }
    }

    private static final String NO_ALIAS = "";
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();

    private TaskColumnInfo columnInfo;
    private ProxyState<com.example.mytasks.Task> proxyState;

    com_example_mytasks_TaskRealmProxy() {
        proxyState.setConstructionFinished();
    }

    @Override
    public void realm$injectObjectContext() {
        if (this.proxyState != null) {
            return;
        }
        final BaseRealm.RealmObjectContext context = BaseRealm.objectContext.get();
        this.columnInfo = (TaskColumnInfo) context.getColumnInfo();
        this.proxyState = new ProxyState<com.example.mytasks.Task>(this);
        proxyState.setRealm$realm(context.getRealm());
        proxyState.setRow$realm(context.getRow());
        proxyState.setAcceptDefaultValue$realm(context.getAcceptDefaultValue());
        proxyState.setExcludeFields$realm(context.getExcludeFields());
    }

    @Override
    @SuppressWarnings("cast")
    public long realmGet$id() {
        proxyState.getRealm$realm().checkIfValid();
        return (long) proxyState.getRow$realm().getLong(columnInfo.idColKey);
    }

    @Override
    public void realmSet$id(long value) {
        if (proxyState.isUnderConstruction()) {
            // default value of the primary key is always ignored.
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        throw new io.realm.exceptions.RealmException("Primary key field 'id' cannot be changed after object was created.");
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$title() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.titleColKey);
    }

    @Override
    public void realmSet$title(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.titleColKey, row.getObjectKey(), true);
                return;
            }
            row.getTable().setString(columnInfo.titleColKey, row.getObjectKey(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.titleColKey);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.titleColKey, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$description() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.descriptionColKey);
    }

    @Override
    public void realmSet$description(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.descriptionColKey, row.getObjectKey(), true);
                return;
            }
            row.getTable().setString(columnInfo.descriptionColKey, row.getObjectKey(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.descriptionColKey);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.descriptionColKey, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$category() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.categoryColKey);
    }

    @Override
    public void realmSet$category(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.categoryColKey, row.getObjectKey(), true);
                return;
            }
            row.getTable().setString(columnInfo.categoryColKey, row.getObjectKey(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.categoryColKey);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.categoryColKey, value);
    }

    @Override
    @SuppressWarnings("cast")
    public int realmGet$priority() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.priorityColKey);
    }

    @Override
    public void realmSet$priority(int value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setLong(columnInfo.priorityColKey, row.getObjectKey(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.priorityColKey, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$dueDate() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.dueDateColKey);
    }

    @Override
    public void realmSet$dueDate(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.dueDateColKey, row.getObjectKey(), true);
                return;
            }
            row.getTable().setString(columnInfo.dueDateColKey, row.getObjectKey(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.dueDateColKey);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.dueDateColKey, value);
    }

    @Override
    @SuppressWarnings("cast")
    public boolean realmGet$completed() {
        proxyState.getRealm$realm().checkIfValid();
        return (boolean) proxyState.getRow$realm().getBoolean(columnInfo.completedColKey);
    }

    @Override
    public void realmSet$completed(boolean value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setBoolean(columnInfo.completedColKey, row.getObjectKey(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setBoolean(columnInfo.completedColKey, value);
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder(NO_ALIAS, "Task", false, 7, 0);
        builder.addPersistedProperty(NO_ALIAS, "id", RealmFieldType.INTEGER, Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty(NO_ALIAS, "title", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty(NO_ALIAS, "description", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty(NO_ALIAS, "category", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty(NO_ALIAS, "priority", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty(NO_ALIAS, "dueDate", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty(NO_ALIAS, "completed", RealmFieldType.BOOLEAN, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static TaskColumnInfo createColumnInfo(OsSchemaInfo schemaInfo) {
        return new TaskColumnInfo(schemaInfo);
    }

    public static String getSimpleClassName() {
        return "Task";
    }

    public static final class ClassNameHelper {
        public static final String INTERNAL_CLASS_NAME = "Task";
    }

    @SuppressWarnings("cast")
    public static com.example.mytasks.Task createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        final List<String> excludeFields = Collections.<String> emptyList();
        com.example.mytasks.Task obj = null;
        if (update) {
            Table table = realm.getTable(com.example.mytasks.Task.class);
            TaskColumnInfo columnInfo = (TaskColumnInfo) realm.getSchema().getColumnInfo(com.example.mytasks.Task.class);
            long pkColumnKey = columnInfo.idColKey;
            long objKey = Table.NO_MATCH;
            if (!json.isNull("id")) {
                objKey = table.findFirstLong(pkColumnKey, json.getLong("id"));
            }
            if (objKey != Table.NO_MATCH) {
                final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
                try {
                    objectContext.set(realm, table.getUncheckedRow(objKey), realm.getSchema().getColumnInfo(com.example.mytasks.Task.class), false, Collections.<String> emptyList());
                    obj = new io.realm.com_example_mytasks_TaskRealmProxy();
                } finally {
                    objectContext.clear();
                }
            }
        }
        if (obj == null) {
            if (json.has("id")) {
                if (json.isNull("id")) {
                    obj = (io.realm.com_example_mytasks_TaskRealmProxy) realm.createObjectInternal(com.example.mytasks.Task.class, null, true, excludeFields);
                } else {
                    obj = (io.realm.com_example_mytasks_TaskRealmProxy) realm.createObjectInternal(com.example.mytasks.Task.class, json.getLong("id"), true, excludeFields);
                }
            } else {
                throw new IllegalArgumentException("JSON object doesn't have the primary key field 'id'.");
            }
        }

        final com_example_mytasks_TaskRealmProxyInterface objProxy = (com_example_mytasks_TaskRealmProxyInterface) obj;
        if (json.has("title")) {
            if (json.isNull("title")) {
                objProxy.realmSet$title(null);
            } else {
                objProxy.realmSet$title((String) json.getString("title"));
            }
        }
        if (json.has("description")) {
            if (json.isNull("description")) {
                objProxy.realmSet$description(null);
            } else {
                objProxy.realmSet$description((String) json.getString("description"));
            }
        }
        if (json.has("category")) {
            if (json.isNull("category")) {
                objProxy.realmSet$category(null);
            } else {
                objProxy.realmSet$category((String) json.getString("category"));
            }
        }
        if (json.has("priority")) {
            if (json.isNull("priority")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'priority' to null.");
            } else {
                objProxy.realmSet$priority((int) json.getInt("priority"));
            }
        }
        if (json.has("dueDate")) {
            if (json.isNull("dueDate")) {
                objProxy.realmSet$dueDate(null);
            } else {
                objProxy.realmSet$dueDate((String) json.getString("dueDate"));
            }
        }
        if (json.has("completed")) {
            if (json.isNull("completed")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'completed' to null.");
            } else {
                objProxy.realmSet$completed((boolean) json.getBoolean("completed"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static com.example.mytasks.Task createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        boolean jsonHasPrimaryKey = false;
        final com.example.mytasks.Task obj = new com.example.mytasks.Task();
        final com_example_mytasks_TaskRealmProxyInterface objProxy = (com_example_mytasks_TaskRealmProxyInterface) obj;
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (false) {
            } else if (name.equals("id")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$id((long) reader.nextLong());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'id' to null.");
                }
                jsonHasPrimaryKey = true;
            } else if (name.equals("title")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$title((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$title(null);
                }
            } else if (name.equals("description")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$description((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$description(null);
                }
            } else if (name.equals("category")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$category((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$category(null);
                }
            } else if (name.equals("priority")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$priority((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'priority' to null.");
                }
            } else if (name.equals("dueDate")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$dueDate((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$dueDate(null);
                }
            } else if (name.equals("completed")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$completed((boolean) reader.nextBoolean());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'completed' to null.");
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        if (!jsonHasPrimaryKey) {
            throw new IllegalArgumentException("JSON object doesn't have the primary key field 'id'.");
        }
        return realm.copyToRealmOrUpdate(obj);
    }

    static com_example_mytasks_TaskRealmProxy newProxyInstance(BaseRealm realm, Row row) {
        // Ignore default values to avoid creating unexpected objects from RealmModel/RealmList fields
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        objectContext.set(realm, row, realm.getSchema().getColumnInfo(com.example.mytasks.Task.class), false, Collections.<String>emptyList());
        io.realm.com_example_mytasks_TaskRealmProxy obj = new io.realm.com_example_mytasks_TaskRealmProxy();
        objectContext.clear();
        return obj;
    }

    public static com.example.mytasks.Task copyOrUpdate(Realm realm, TaskColumnInfo columnInfo, com.example.mytasks.Task object, boolean update, Map<RealmModel,RealmObjectProxy> cache, Set<ImportFlag> flags) {
        if (object instanceof RealmObjectProxy && !RealmObject.isFrozen(object) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null) {
            final BaseRealm otherRealm = ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm();
            if (otherRealm.threadId != realm.threadId) {
                throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
            }
            if (otherRealm.getPath().equals(realm.getPath())) {
                return object;
            }
        }
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        RealmObjectProxy cachedRealmObject = cache.get(object);
        if (cachedRealmObject != null) {
            return (com.example.mytasks.Task) cachedRealmObject;
        }

        com.example.mytasks.Task realmObject = null;
        boolean canUpdate = update;
        if (canUpdate) {
            Table table = realm.getTable(com.example.mytasks.Task.class);
            long pkColumnKey = columnInfo.idColKey;
            long objKey = table.findFirstLong(pkColumnKey, ((com_example_mytasks_TaskRealmProxyInterface) object).realmGet$id());
            if (objKey == Table.NO_MATCH) {
                canUpdate = false;
            } else {
                try {
                    objectContext.set(realm, table.getUncheckedRow(objKey), columnInfo, false, Collections.<String> emptyList());
                    realmObject = new io.realm.com_example_mytasks_TaskRealmProxy();
                    cache.put(object, (RealmObjectProxy) realmObject);
                } finally {
                    objectContext.clear();
                }
            }
        }

        return (canUpdate) ? update(realm, columnInfo, realmObject, object, cache, flags) : copy(realm, columnInfo, object, update, cache, flags);
    }

    public static com.example.mytasks.Task copy(Realm realm, TaskColumnInfo columnInfo, com.example.mytasks.Task newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache, Set<ImportFlag> flags) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (com.example.mytasks.Task) cachedRealmObject;
        }

        com_example_mytasks_TaskRealmProxyInterface unmanagedSource = (com_example_mytasks_TaskRealmProxyInterface) newObject;

        Table table = realm.getTable(com.example.mytasks.Task.class);
        OsObjectBuilder builder = new OsObjectBuilder(table, flags);

        // Add all non-"object reference" fields
        builder.addInteger(columnInfo.idColKey, unmanagedSource.realmGet$id());
        builder.addString(columnInfo.titleColKey, unmanagedSource.realmGet$title());
        builder.addString(columnInfo.descriptionColKey, unmanagedSource.realmGet$description());
        builder.addString(columnInfo.categoryColKey, unmanagedSource.realmGet$category());
        builder.addInteger(columnInfo.priorityColKey, unmanagedSource.realmGet$priority());
        builder.addString(columnInfo.dueDateColKey, unmanagedSource.realmGet$dueDate());
        builder.addBoolean(columnInfo.completedColKey, unmanagedSource.realmGet$completed());

        // Create the underlying object and cache it before setting any object/objectlist references
        // This will allow us to break any circular dependencies by using the object cache.
        Row row = builder.createNewObject();
        io.realm.com_example_mytasks_TaskRealmProxy managedCopy = newProxyInstance(realm, row);
        cache.put(newObject, managedCopy);

        return managedCopy;
    }

    public static long insert(Realm realm, com.example.mytasks.Task object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && !RealmObject.isFrozen(object) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getObjectKey();
        }
        Table table = realm.getTable(com.example.mytasks.Task.class);
        long tableNativePtr = table.getNativePtr();
        TaskColumnInfo columnInfo = (TaskColumnInfo) realm.getSchema().getColumnInfo(com.example.mytasks.Task.class);
        long pkColumnKey = columnInfo.idColKey;
        long objKey = Table.NO_MATCH;
        Object primaryKeyValue = ((com_example_mytasks_TaskRealmProxyInterface) object).realmGet$id();
        if (primaryKeyValue != null) {
            objKey = Table.nativeFindFirstInt(tableNativePtr, pkColumnKey, ((com_example_mytasks_TaskRealmProxyInterface) object).realmGet$id());
        }
        if (objKey == Table.NO_MATCH) {
            objKey = OsObject.createRowWithPrimaryKey(table, pkColumnKey, ((com_example_mytasks_TaskRealmProxyInterface) object).realmGet$id());
        } else {
            Table.throwDuplicatePrimaryKeyException(primaryKeyValue);
        }
        cache.put(object, objKey);
        String realmGet$title = ((com_example_mytasks_TaskRealmProxyInterface) object).realmGet$title();
        if (realmGet$title != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.titleColKey, objKey, realmGet$title, false);
        }
        String realmGet$description = ((com_example_mytasks_TaskRealmProxyInterface) object).realmGet$description();
        if (realmGet$description != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.descriptionColKey, objKey, realmGet$description, false);
        }
        String realmGet$category = ((com_example_mytasks_TaskRealmProxyInterface) object).realmGet$category();
        if (realmGet$category != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.categoryColKey, objKey, realmGet$category, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.priorityColKey, objKey, ((com_example_mytasks_TaskRealmProxyInterface) object).realmGet$priority(), false);
        String realmGet$dueDate = ((com_example_mytasks_TaskRealmProxyInterface) object).realmGet$dueDate();
        if (realmGet$dueDate != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.dueDateColKey, objKey, realmGet$dueDate, false);
        }
        Table.nativeSetBoolean(tableNativePtr, columnInfo.completedColKey, objKey, ((com_example_mytasks_TaskRealmProxyInterface) object).realmGet$completed(), false);
        return objKey;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.example.mytasks.Task.class);
        long tableNativePtr = table.getNativePtr();
        TaskColumnInfo columnInfo = (TaskColumnInfo) realm.getSchema().getColumnInfo(com.example.mytasks.Task.class);
        long pkColumnKey = columnInfo.idColKey;
        com.example.mytasks.Task object = null;
        while (objects.hasNext()) {
            object = (com.example.mytasks.Task) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && !RealmObject.isFrozen(object) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getObjectKey());
                continue;
            }
            long objKey = Table.NO_MATCH;
            Object primaryKeyValue = ((com_example_mytasks_TaskRealmProxyInterface) object).realmGet$id();
            if (primaryKeyValue != null) {
                objKey = Table.nativeFindFirstInt(tableNativePtr, pkColumnKey, ((com_example_mytasks_TaskRealmProxyInterface) object).realmGet$id());
            }
            if (objKey == Table.NO_MATCH) {
                objKey = OsObject.createRowWithPrimaryKey(table, pkColumnKey, ((com_example_mytasks_TaskRealmProxyInterface) object).realmGet$id());
            } else {
                Table.throwDuplicatePrimaryKeyException(primaryKeyValue);
            }
            cache.put(object, objKey);
            String realmGet$title = ((com_example_mytasks_TaskRealmProxyInterface) object).realmGet$title();
            if (realmGet$title != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.titleColKey, objKey, realmGet$title, false);
            }
            String realmGet$description = ((com_example_mytasks_TaskRealmProxyInterface) object).realmGet$description();
            if (realmGet$description != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.descriptionColKey, objKey, realmGet$description, false);
            }
            String realmGet$category = ((com_example_mytasks_TaskRealmProxyInterface) object).realmGet$category();
            if (realmGet$category != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.categoryColKey, objKey, realmGet$category, false);
            }
            Table.nativeSetLong(tableNativePtr, columnInfo.priorityColKey, objKey, ((com_example_mytasks_TaskRealmProxyInterface) object).realmGet$priority(), false);
            String realmGet$dueDate = ((com_example_mytasks_TaskRealmProxyInterface) object).realmGet$dueDate();
            if (realmGet$dueDate != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.dueDateColKey, objKey, realmGet$dueDate, false);
            }
            Table.nativeSetBoolean(tableNativePtr, columnInfo.completedColKey, objKey, ((com_example_mytasks_TaskRealmProxyInterface) object).realmGet$completed(), false);
        }
    }

    public static long insertOrUpdate(Realm realm, com.example.mytasks.Task object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && !RealmObject.isFrozen(object) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getObjectKey();
        }
        Table table = realm.getTable(com.example.mytasks.Task.class);
        long tableNativePtr = table.getNativePtr();
        TaskColumnInfo columnInfo = (TaskColumnInfo) realm.getSchema().getColumnInfo(com.example.mytasks.Task.class);
        long pkColumnKey = columnInfo.idColKey;
        long objKey = Table.NO_MATCH;
        Object primaryKeyValue = ((com_example_mytasks_TaskRealmProxyInterface) object).realmGet$id();
        if (primaryKeyValue != null) {
            objKey = Table.nativeFindFirstInt(tableNativePtr, pkColumnKey, ((com_example_mytasks_TaskRealmProxyInterface) object).realmGet$id());
        }
        if (objKey == Table.NO_MATCH) {
            objKey = OsObject.createRowWithPrimaryKey(table, pkColumnKey, ((com_example_mytasks_TaskRealmProxyInterface) object).realmGet$id());
        }
        cache.put(object, objKey);
        String realmGet$title = ((com_example_mytasks_TaskRealmProxyInterface) object).realmGet$title();
        if (realmGet$title != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.titleColKey, objKey, realmGet$title, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.titleColKey, objKey, false);
        }
        String realmGet$description = ((com_example_mytasks_TaskRealmProxyInterface) object).realmGet$description();
        if (realmGet$description != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.descriptionColKey, objKey, realmGet$description, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.descriptionColKey, objKey, false);
        }
        String realmGet$category = ((com_example_mytasks_TaskRealmProxyInterface) object).realmGet$category();
        if (realmGet$category != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.categoryColKey, objKey, realmGet$category, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.categoryColKey, objKey, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.priorityColKey, objKey, ((com_example_mytasks_TaskRealmProxyInterface) object).realmGet$priority(), false);
        String realmGet$dueDate = ((com_example_mytasks_TaskRealmProxyInterface) object).realmGet$dueDate();
        if (realmGet$dueDate != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.dueDateColKey, objKey, realmGet$dueDate, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.dueDateColKey, objKey, false);
        }
        Table.nativeSetBoolean(tableNativePtr, columnInfo.completedColKey, objKey, ((com_example_mytasks_TaskRealmProxyInterface) object).realmGet$completed(), false);
        return objKey;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.example.mytasks.Task.class);
        long tableNativePtr = table.getNativePtr();
        TaskColumnInfo columnInfo = (TaskColumnInfo) realm.getSchema().getColumnInfo(com.example.mytasks.Task.class);
        long pkColumnKey = columnInfo.idColKey;
        com.example.mytasks.Task object = null;
        while (objects.hasNext()) {
            object = (com.example.mytasks.Task) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && !RealmObject.isFrozen(object) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getObjectKey());
                continue;
            }
            long objKey = Table.NO_MATCH;
            Object primaryKeyValue = ((com_example_mytasks_TaskRealmProxyInterface) object).realmGet$id();
            if (primaryKeyValue != null) {
                objKey = Table.nativeFindFirstInt(tableNativePtr, pkColumnKey, ((com_example_mytasks_TaskRealmProxyInterface) object).realmGet$id());
            }
            if (objKey == Table.NO_MATCH) {
                objKey = OsObject.createRowWithPrimaryKey(table, pkColumnKey, ((com_example_mytasks_TaskRealmProxyInterface) object).realmGet$id());
            }
            cache.put(object, objKey);
            String realmGet$title = ((com_example_mytasks_TaskRealmProxyInterface) object).realmGet$title();
            if (realmGet$title != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.titleColKey, objKey, realmGet$title, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.titleColKey, objKey, false);
            }
            String realmGet$description = ((com_example_mytasks_TaskRealmProxyInterface) object).realmGet$description();
            if (realmGet$description != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.descriptionColKey, objKey, realmGet$description, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.descriptionColKey, objKey, false);
            }
            String realmGet$category = ((com_example_mytasks_TaskRealmProxyInterface) object).realmGet$category();
            if (realmGet$category != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.categoryColKey, objKey, realmGet$category, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.categoryColKey, objKey, false);
            }
            Table.nativeSetLong(tableNativePtr, columnInfo.priorityColKey, objKey, ((com_example_mytasks_TaskRealmProxyInterface) object).realmGet$priority(), false);
            String realmGet$dueDate = ((com_example_mytasks_TaskRealmProxyInterface) object).realmGet$dueDate();
            if (realmGet$dueDate != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.dueDateColKey, objKey, realmGet$dueDate, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.dueDateColKey, objKey, false);
            }
            Table.nativeSetBoolean(tableNativePtr, columnInfo.completedColKey, objKey, ((com_example_mytasks_TaskRealmProxyInterface) object).realmGet$completed(), false);
        }
    }

    public static com.example.mytasks.Task createDetachedCopy(com.example.mytasks.Task realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        com.example.mytasks.Task unmanagedObject;
        if (cachedObject == null) {
            unmanagedObject = new com.example.mytasks.Task();
            cache.put(realmObject, new RealmObjectProxy.CacheData<RealmModel>(currentDepth, unmanagedObject));
        } else {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (com.example.mytasks.Task) cachedObject.object;
            }
            unmanagedObject = (com.example.mytasks.Task) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        com_example_mytasks_TaskRealmProxyInterface unmanagedCopy = (com_example_mytasks_TaskRealmProxyInterface) unmanagedObject;
        com_example_mytasks_TaskRealmProxyInterface realmSource = (com_example_mytasks_TaskRealmProxyInterface) realmObject;
        Realm objectRealm = (Realm) ((RealmObjectProxy) realmObject).realmGet$proxyState().getRealm$realm();
        unmanagedCopy.realmSet$id(realmSource.realmGet$id());
        unmanagedCopy.realmSet$title(realmSource.realmGet$title());
        unmanagedCopy.realmSet$description(realmSource.realmGet$description());
        unmanagedCopy.realmSet$category(realmSource.realmGet$category());
        unmanagedCopy.realmSet$priority(realmSource.realmGet$priority());
        unmanagedCopy.realmSet$dueDate(realmSource.realmGet$dueDate());
        unmanagedCopy.realmSet$completed(realmSource.realmGet$completed());

        return unmanagedObject;
    }

    static com.example.mytasks.Task update(Realm realm, TaskColumnInfo columnInfo, com.example.mytasks.Task realmObject, com.example.mytasks.Task newObject, Map<RealmModel, RealmObjectProxy> cache, Set<ImportFlag> flags) {
        com_example_mytasks_TaskRealmProxyInterface realmObjectTarget = (com_example_mytasks_TaskRealmProxyInterface) realmObject;
        com_example_mytasks_TaskRealmProxyInterface realmObjectSource = (com_example_mytasks_TaskRealmProxyInterface) newObject;
        Table table = realm.getTable(com.example.mytasks.Task.class);
        OsObjectBuilder builder = new OsObjectBuilder(table, flags);
        builder.addInteger(columnInfo.idColKey, realmObjectSource.realmGet$id());
        builder.addString(columnInfo.titleColKey, realmObjectSource.realmGet$title());
        builder.addString(columnInfo.descriptionColKey, realmObjectSource.realmGet$description());
        builder.addString(columnInfo.categoryColKey, realmObjectSource.realmGet$category());
        builder.addInteger(columnInfo.priorityColKey, realmObjectSource.realmGet$priority());
        builder.addString(columnInfo.dueDateColKey, realmObjectSource.realmGet$dueDate());
        builder.addBoolean(columnInfo.completedColKey, realmObjectSource.realmGet$completed());

        builder.updateExistingTopLevelObject();
        return realmObject;
    }

    @Override
    @SuppressWarnings("ArrayToString")
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("Task = proxy[");
        stringBuilder.append("{id:");
        stringBuilder.append(realmGet$id());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{title:");
        stringBuilder.append(realmGet$title() != null ? realmGet$title() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{description:");
        stringBuilder.append(realmGet$description() != null ? realmGet$description() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{category:");
        stringBuilder.append(realmGet$category() != null ? realmGet$category() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{priority:");
        stringBuilder.append(realmGet$priority());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{dueDate:");
        stringBuilder.append(realmGet$dueDate() != null ? realmGet$dueDate() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{completed:");
        stringBuilder.append(realmGet$completed());
        stringBuilder.append("}");
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    @Override
    public ProxyState<?> realmGet$proxyState() {
        return proxyState;
    }

    @Override
    public int hashCode() {
        String realmName = proxyState.getRealm$realm().getPath();
        String tableName = proxyState.getRow$realm().getTable().getName();
        long objKey = proxyState.getRow$realm().getObjectKey();

        int result = 17;
        result = 31 * result + ((realmName != null) ? realmName.hashCode() : 0);
        result = 31 * result + ((tableName != null) ? tableName.hashCode() : 0);
        result = 31 * result + (int) (objKey ^ (objKey >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        com_example_mytasks_TaskRealmProxy aTask = (com_example_mytasks_TaskRealmProxy)o;

        BaseRealm realm = proxyState.getRealm$realm();
        BaseRealm otherRealm = aTask.proxyState.getRealm$realm();
        String path = realm.getPath();
        String otherPath = otherRealm.getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;
        if (realm.isFrozen() != otherRealm.isFrozen()) return false;
        if (!realm.sharedRealm.getVersionID().equals(otherRealm.sharedRealm.getVersionID())) {
            return false;
        }

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aTask.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getObjectKey() != aTask.proxyState.getRow$realm().getObjectKey()) return false;

        return true;
    }
}
