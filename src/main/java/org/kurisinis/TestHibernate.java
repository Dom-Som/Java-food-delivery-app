package org.kurisinis;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class TestHibernate {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("kurisinis");
    }
}
