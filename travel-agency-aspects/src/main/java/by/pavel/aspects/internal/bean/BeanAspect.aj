package by.pavel.aspects.internal.bean;

import by.pavel.aspects.bean.Bean;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public aspect BeanAspect {

    public String Bean.toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public int Bean.hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    public boolean Bean.equals(Object obj) {
        return this.getClass() == obj.getClass() && EqualsBuilder.reflectionEquals(this, obj);
    }
}
