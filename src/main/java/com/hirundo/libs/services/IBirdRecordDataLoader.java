package com.hirundo.libs.services;

import java.util.List;

public interface IBirdRecordDataLoader<DataRecordType> {
    List<DataRecordType> loadData(String tableName) throws Exception;
}

