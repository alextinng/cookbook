package com.jokes;

import org.apache.hadoop.hive.ql.parse.ASTNode;
import org.apache.hadoop.hive.ql.parse.ParseException;
import org.junit.Test;

public class HqlParseUtilTest {
    @Test
    public void testParse() throws ParseException {
        ASTNode astNode = HqlParseUtil.parse("select name, age from student join b " +
                "on student.id=b.id where b.id in (select id from c)");
        System.out.println(astNode.dump());
    }
}