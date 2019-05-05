//package com.mk.cep.utils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.core.convert.TypeDescriptor;
//import org.springframework.core.convert.converter.ConditionalGenericConverter;
//import org.springframework.format.support.FormattingConversionService;
//import org.springframework.security.access.prepost.PostAuthorize;
//import org.springframework.util.StringUtils;
//
//import javax.persistence.EntityManager;
//import javax.persistence.NoResultException;
//import javax.persistence.PersistenceContext;
//import javax.persistence.metamodel.Attribute;
//import javax.persistence.metamodel.EntityType;
//import javax.persistence.metamodel.SingularAttribute;
//import java.util.Collections;
//import java.util.HashSet;
//import java.util.Set;
//
///**
// * Converts an ID (the field in the entity annotated with
// * {@link javax.persistence.Id}) to the entity itself. This is similar to
// * {@link org.springframework.data.repository.support.DomainClassConverter<T>}
// * but since we don't use Spring Data, we can't use that.
// */
//public class IdToEntityConverter implements ConditionalGenericConverter {
//
//    @PersistenceContext
//    private EntityManager entityManager;
//
//    @Autowired
//    @Qualifier("mvcConversionService")
//    private FormattingConversionService conversionService;
//
//    @Override
//    public Set<ConvertiblePair> getConvertibleTypes() {
//        return Collections.singleton(new ConvertiblePair(Object.class,
//                Object.class));
//    }
//
//    @SuppressWarnings("unchecked")
//    @Override
//    public boolean matches(TypeDescriptor sourceType, TypeDescriptor targetType) {
//        EntityType<?> entityType;
//        try {
//            entityType = entityManager.getMetamodel().entity(
//                    targetType.getType());
//        } catch (IllegalArgumentException e) {
//            return false;
//        }
//        if (entityType == null)
//            return false;
//
//        // In my opinion, this is probably a bug in Hibernate.
//        // If the class has a @Id that is a complex type (such as another
//        // entity), then entityType.getIdType() will throw an
//        // IllegalStateException with the message "No supertype found"
//        // To work around this, get the idClassAttributes, and if there is
//        // exactly 1, use the java type of that.
//        Class<?> idClass;
//        if (entityType.hasSingleIdAttribute()) {
//            idClass = entityType.getIdType().getJavaType();
//        } else {
//            Set<SingularAttribute<?, ?>> idAttributes = new HashSet<>();
//            @SuppressWarnings("rawtypes")
//            Set attributes = entityType.getAttributes();
//            for (Attribute<?, ?> attribute : (Set<Attribute<?, ?>>) attributes) {
//                if (attribute instanceof SingularAttribute<?, ?>) {
//                    SingularAttribute<?, ?> singularAttribute = (SingularAttribute<?, ?>) attribute;
//                    if (singularAttribute.isId()) {
//                        idAttributes.add(singularAttribute);
//                    }
//                }
//
//            }
//            if (idAttributes.size() == 1) {
//                idClass = idAttributes.iterator().next().getJavaType();
//            } else {
//                return false;
//            }
//        }
//
//        return conversionService.canConvert(sourceType,
//                TypeDescriptor.valueOf(idClass));
//    }
//
//    @Override
//    @PostAuthorize("hasPermission(returnObject, 'view')")
//    public Object convert(Object source, TypeDescriptor sourceType,
//                          TypeDescriptor targetType) {
//        if (sourceType.getType() == targetType.getType()) {
//            return source;
//        }
//        EntityType<?> entityType = entityManager.getMetamodel().entity(
//                targetType.getType());
//        Object id = conversionService.convert(source, sourceType,
//                TypeDescriptor.valueOf(entityType.getIdType().getJavaType()));
//        if (id == null
//                || (id instanceof String && StringUtils.isEmpty((String) id))) {
//            return null;
//        }
//        Object ret = entityManager.find(targetType.getType(), id);
//        if (ret == null) {
//            throw new NoResultException(targetType.getType().getName()
//                    + " with id '" + id + "' not found");
//        }
//        return ret;
//    }
//}
