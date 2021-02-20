package org.fp024.jpaquick.shopping.common.hibernate;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

/**
 * 도메인 모델의 이름들이 Camel Case 로 되어있다면 테이블/컬럼 생성시 자동으로 Snake Case로 변경
 * 그런데 도메인 모델에서 unique를 지정할 때는 원래 도메인 필드이름대로 써줘야 인식했다.
 * <p>
 * <p>
 * https://thorben-janssen.com/naming-strategies-in-hibernate-5/
 * <p>
 * https://www.baeldung.com/hibernate-naming-strategy
 */
public class CustomPhysicalNamingStrategy extends PhysicalNamingStrategyStandardImpl {
    private static final long serialVersionUID = 1L;

    @Override
    public Identifier toPhysicalCatalogName(Identifier name, JdbcEnvironment context) {
        return super.toPhysicalCatalogName(toSnakeCase(name), context);
    }

    @Override
    public Identifier toPhysicalColumnName(Identifier name, JdbcEnvironment context) {
        return super.toPhysicalColumnName(toSnakeCase(name), context);
    }

    @Override
    public Identifier toPhysicalSchemaName(Identifier name, JdbcEnvironment context) {
        return super.toPhysicalSchemaName(toSnakeCase(name), context);
    }

    @Override
    public Identifier toPhysicalSequenceName(Identifier name, JdbcEnvironment context) {
        return super.toPhysicalSequenceName(toSnakeCase(name), context);
    }

    @Override
    public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment context) {
        return super.toPhysicalTableName(toSnakeCase(name), context);
    }

    private Identifier toSnakeCase(Identifier id) {
        if (id == null) {
            return id;
        }

        String name = id.getText();
        String snakeName = name.replaceAll("([a-z]+)([A-Z]+)", "$1\\_$2").toLowerCase();
        if (!snakeName.equals(name)) {
            return new Identifier(snakeName, id.isQuoted());
        } else {
            return id;
        }
    }
}
