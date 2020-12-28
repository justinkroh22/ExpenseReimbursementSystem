package models;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

import org.hibernate.*;

public class PostgreSQLEnumType extends org.hibernate.type.EnumType {
	 
    public void nullSafeSet(
            PreparedStatement st,
            Object value,
            int index,
            SharedSessionContract session)
            throws HibernateException, SQLException {
        st.setObject(
            index,
            value != null ?
                ((Enum) value).name() :
                null,
            Types.OTHER
        );
    }
}