package org.hibernate.lab5.dao;

import org.hibernate.lab5.model.Product;
import org.hibernate.Session;
import org.hibernate.lab5.util.HibernateUtil;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductDao extends AbstractDao<Product, Integer> {

    public ProductDao() {
        super(Product.class);
    }

    public List<Object[]> findProductsWithDeliveryInfo(Optional<Integer> productIdOpt, Optional<String> deliveryAddressOpt) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Object[]> cq = cb.createQuery(Object[].class);
            Root<Product> productRoot = cq.from(Product.class);
            List<Predicate> predicates = new ArrayList<>();

            productIdOpt.ifPresent(productId -> predicates.add(cb.equal(productRoot.get("productId"), productId)));
            deliveryAddressOpt.ifPresent(address -> predicates.add(cb.like(productRoot.get("delivery").get("address"), "%" + address + "%")));

            cq.where(predicates.toArray(new Predicate[0]));
            cq.multiselect(productRoot, productRoot.get("delivery").get("address"));

            return session.createQuery(cq).getResultList();
        }
    }
}
