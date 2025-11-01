module org.example.kurisinis{
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

    opens org.kurisinis to javafx.fxml, org.hibernate.orm.core, jakarta.persistence;
    exports org.kurisinis;
    opens org.kurisinis.fxControllers to javafx.fxml;
    exports org.kurisinis.fxControllers to javafx.fxml;
    opens org.kurisinis.model to org.hibernate.orm.core;
    exports org.kurisinis.model;


    //opens org.example.kurisinis to javafx.fxml, org.hibernate.orm.core, jakarta.persistence;
   // exports org.example.kurisinis;
   // opens org.example.kurisinis.fxControllers to javafx.fxml;

  //  exports org.kurisinis;
  //  opens org.kurisinis to jakarta.persistence, javafx.fxml, org.hibernate.orm.core;
}