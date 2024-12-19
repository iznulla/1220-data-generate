package com.uzinfo.datagenerate.web.entity.enums;

public enum Database {
    MYSQL {
        @Override
        public String getDriver() {
            return "com.mysql.cj.jdbc.Driver";
        }

        @Override
        public String getDialect() {
            return "org.hibernate.dialect.MySQLDialect";
        }
    },
    POSTGRESQL {
        @Override
        public String getDriver() {
            return "org.postgresql.Driver";
        }
        @Override
        public String getDialect() {
            return "org.hibernate.dialect.PostgreSQLDialect";
        }
    },
    ORACLE {
        @Override
        public String getDriver() {
            return "oracle.jdbc.OracleDriver";
        }
        @Override
        public String getDialect() {
            return "org.hibernate.dialect.OracleDialect";
        }
    },
    h2 {
        public String getDriver() {
            return "org.h2.Driver";
        }

        public String getDialect() {
            return "org.hibernate.dialect.H2Dialect";
        }
    };

    public abstract String getDriver();

    public abstract String getDialect();
}