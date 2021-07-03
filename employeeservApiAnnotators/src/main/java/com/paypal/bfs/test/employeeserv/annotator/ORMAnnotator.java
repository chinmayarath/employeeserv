package com.paypal.bfs.test.employeeserv.annotator;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.jsonschema2pojo.AbstractAnnotator;

import com.fasterxml.jackson.databind.JsonNode;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JFieldVar;

public class ORMAnnotator extends AbstractAnnotator {
	private static final Map<String, Map<String, List<Class<? extends Annotation>>>> propMap;
	static {
		propMap = new HashMap<>();
		/**
		 * Employee annotations
		 */
		Map<String, List<Class<? extends Annotation>>> employeeFieldMap = new HashMap<String, List<Class<? extends Annotation>>>() {
			private static final long serialVersionUID = 1L;
			{
				put("id", Arrays.asList(Id.class, NotNull.class));
				put("firstName", Arrays.asList(NotNull.class, NotBlank.class));
				put("lastName", Arrays.asList(NotNull.class, NotBlank.class));
				put("dob", Arrays.asList(NotNull.class, NotBlank.class));
				put("address", Arrays.asList(NotNull.class, Embedded.class, Valid.class));
			}
		};
		/**
		 * Address annotations
		 */
		Map<String, List<Class<? extends Annotation>>> addressFieldMap = new HashMap<String, List<Class<? extends Annotation>>>() {
			private static final long serialVersionUID = 1L;
			{
				put("line1", Arrays.asList(NotNull.class));
				put("city", Arrays.asList(NotNull.class, NotBlank.class));
				put("country", Arrays.asList(NotNull.class, NotBlank.class));
				put("state", Arrays.asList(NotNull.class, NotBlank.class));
				put("zip", Arrays.asList(NotNull.class, NotBlank.class));
			}
		};
		propMap.put("Employee", employeeFieldMap);
		propMap.put("Address", addressFieldMap);
	}

	@Override
	public void typeInfo(JDefinedClass clazz, JsonNode schema) {
		if ("Employee".equals(clazz.name())) {
			clazz.annotate(Entity.class);
		}
		if ("Address".equals(clazz.name())) {
			clazz.annotate(Embeddable.class);
		}
	}

	@Override
	public void propertyField(JFieldVar field, JDefinedClass clazz, String propertyName, JsonNode propertyNode) {
		if (propMap.get(clazz.name()) != null && propMap.get(clazz.name()).size() > 0
				&& propMap.get(clazz.name()).containsKey(field.name())) {
			propMap.get(clazz.name()).get(field.name()).stream().forEach(field::annotate);
		}
		if("Employee".equals(clazz.name()) && "id".equals(field.name())) {
			field.annotate(GeneratedValue.class).param("strategy", GenerationType.IDENTITY);
		}
	}
}
