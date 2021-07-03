package com.paypal.bfs.test.employeeserv.annotator;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.jsonschema2pojo.AbstractAnnotator;

import com.fasterxml.jackson.databind.JsonNode;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JFieldVar;

public class AnnotatorV2 extends AbstractAnnotator {
	private static final Map<String, 
	List<Map<Class<? extends Annotation>, Map<String, Object>>>> propMap;
	static {
		propMap = new HashMap<>();
		/**
		 * Employee annotations
		 *
		 */
		List<Map<Class<? extends Annotation>, Map<String, Object>>> x = new ArrayList<Map<Class<? extends Annotation>, Map<String, Object>>>() {
			{
				add(getMapforGeenratedValue());
				add(getMapForIdCass());
			}

			private Map<Class<? extends Annotation>, Map<String, Object>> getMapforGeenratedValue() {
				Map<String, Object> generateValueAnnotationMap = new HashMap<>();
				generateValueAnnotationMap.put("strategy", GenerationType.IDENTITY);
				Map<Class<? extends Annotation>, Map<String, Object>> genMap = new HashMap<>();
				genMap.put(GeneratedValue.class, generateValueAnnotationMap);
				return genMap;
			}

			private Map<Class<? extends Annotation>, Map<String, Object>> getMapForIdCass() {
				return new HashMap<Class<? extends Annotation>, Map<String,Object>>(){{
					put(Id.class, null);
				}};
			}
		};
		
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
		/*
		 * if (propMap.get(clazz.name()) != null && propMap.get(clazz.name()).size() > 0
		 * && propMap.get(clazz.name()).containsKey(field.name())) {
		 * propMap.get(clazz.name()).get(field.name()).stream().forEach(field::annotate)
		 * ; }
		 */
	}
}
