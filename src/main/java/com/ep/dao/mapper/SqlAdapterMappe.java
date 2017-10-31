package com.ep.dao.mapper;

/**
 * Created by 吴晓海 on 2017/10/25.
 */
import java.util.List;

public interface SqlAdapterMappe {

    List selectSQL(String sql);

    int selectIntSQL(String sql);

    int updateSQL(String sql);

    int deleteSQL(String sql);

    int insertSQL(String sql);
}
