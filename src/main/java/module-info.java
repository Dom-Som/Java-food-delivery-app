module org.example.kurisinis {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;
    requires org.hibernate.orm.core;
    requires java.sql;
    requires java.naming;
    requires mysql.connector.j;
    requires jakarta.persistence;
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires javafx.graphics;

    opens org.kurisinis to javafx.fxml, org.hibernate.orm.core, jakarta.persistence;
    opens org.kurisinis.fxControllers to javafx.fxml, javafx.base; // <<--- fix here
    opens org.kurisinis.model to org.hibernate.orm.core;

    exports org.kurisinis;
    exports org.kurisinis.fxControllers;
    exports org.kurisinis.model;
}
