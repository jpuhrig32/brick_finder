package com.juhrig.bricktool.datasource.dao;

import com.juhrig.bricktool.datasource.dto.DatabaseVersion;

public interface DatabaseVersionDao {

    DatabaseVersion getCurrentVersion();

    void writeDatabaseVersion(DatabaseVersion currentVersion);
}
