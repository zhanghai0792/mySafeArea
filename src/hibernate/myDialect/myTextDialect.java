package hibernate.myDialect;

import java.sql.Types;

import org.hibernate.dialect.MySQL5InnoDBDialect;

public class myTextDialect extends MySQL5InnoDBDialect {
    public myTextDialect() {
        super();
        registerHibernateType(Types.LONGVARCHAR, 65535, "text");
    }
} 