package common.domain;


import com.any.common.core.domain.Tuple;
import com.any.common.core.enums.IEnum;

public enum MyEnum implements IEnum<String, String> {

    A("1", "2"),

    B("3", "4"),
    ;

    private final String key;

    private final String value;

    MyEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }


    @Override
    public String toString() {
        return "MyEnum{" +
                "key='" + key + '\'' +
                ", value='" + value + '\'' +
                '}';
    }

    @Override
    public Tuple<String, String> meta() {
        return Tuple.of(key, value);
    }


    @Override
    public String k() {
        return key;
    }

    @Override
    public String v() {
        return value;
    }
}
