package com.jokes;

import org.apache.hadoop.hive.ql.parse.ASTNode;
import org.apache.hadoop.hive.ql.parse.ParseDriver;
import org.apache.hadoop.hive.ql.parse.ParseException;

public abstract class HqlParseUtil {
    public static ASTNode parse(String sql) throws ParseException {
        ParseDriver parseDriver = new ParseDriver();

        return parseDriver.parse(sql);
    }
}
