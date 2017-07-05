package com.winning.utils;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.property.ChainedPropertyAccessor;
import org.hibernate.property.PropertyAccessor;
import org.hibernate.property.PropertyAccessorFactory;
import org.hibernate.property.Setter;
import org.hibernate.transform.ResultTransformer;

/**
 * 
 * @ClassName: CommonColumnToBean
 * @Description: TODO (自定义的数据库字库转换成POJO)
 * @author Ligh
 * @date 2017年7月5日下午4:49:55
 */
public class CommonColumnToBean implements ResultTransformer {

	private static final long serialVersionUID = -5509446847216650356L;
	private final Class resultClass;
	private final PropertyAccessor propertyAccessor;
	private Setter[] setters;

	public CommonColumnToBean(Class resultClass) {
		if (resultClass == null) {
			throw new IllegalArgumentException("resultClass cannot be null");
		}
		this.resultClass = resultClass;
		this.propertyAccessor = new ChainedPropertyAccessor(
				new PropertyAccessor[] { PropertyAccessorFactory.getPropertyAccessor(resultClass, null),
						PropertyAccessorFactory.getPropertyAccessor("field") });
	}

	public Object transformTuple(Object[] tuple, String[] aliases) {
		Object result;
		try {
			if (this.setters == null) {
				this.setters = new Setter[aliases.length];
				for (int i = 0; i < aliases.length; i++) {
					String alias = aliases[i].toLowerCase();
					if (alias != null) {
						this.setters[i] = this.propertyAccessor.getSetter(this.resultClass, alias);
					}
				}
			}
			result = this.resultClass.newInstance();

			for (int i = 0; i < aliases.length; i++) {
				if (this.setters[i] != null) {
					this.setters[i].set(result, tuple[i], null);
				}
			}
		} catch (InstantiationException e) {
			throw new HibernateException("Could not instantiate resultclass: " + this.resultClass.getName());
		} catch (IllegalAccessException e) {
			throw new HibernateException("Could not instantiate resultclass: " + this.resultClass.getName());
		}

		return result;
	}

	public List transformList(List collection) {
		return collection;
	}

	public int hashCode() {
		int result = this.resultClass.hashCode();
		result = 31 * result + this.propertyAccessor.hashCode();
		return result;
	}
}
