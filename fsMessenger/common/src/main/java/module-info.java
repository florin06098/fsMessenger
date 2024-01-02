module common {
    requires org.hibernate.orm.core;
    requires jakarta.persistence;
    requires java.sql;

    exports fSMessenger_common.module;
    opens fSMessenger_common.module;
}