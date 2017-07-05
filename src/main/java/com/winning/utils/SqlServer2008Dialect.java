package com.winning.utils;

import java.sql.Types;

import org.hibernate.dialect.SQLServerDialect;

public class SqlServer2008Dialect extends SQLServerDialect
{

    public SqlServer2008Dialect()
    {
        super();
        registerHibernateType(Types.CHAR, "string");
        registerHibernateType(Types.NVARCHAR, "string");
        registerHibernateType(Types.LONGNVARCHAR, "string");
        registerHibernateType(Types.DECIMAL, "double");
    }
}