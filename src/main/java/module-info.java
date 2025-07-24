module com.example.jobsearchhndnetworkingcd_groupthirtyseven {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires java.security.sasl;
    requires java.naming;
    requires java.management;

    requires org.junit.jupiter.api;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.desktop;
    requires java.sql;
    requires org.javassist;
    requires protobuf.java;
    requires org.slf4j;
    requires org.hamcrest;
    requires c3p0;
    requires java.sql.rowset;
    requires org.junit.platform.engine;
    requires org.junit.platform.launcher;
    requires java.rmi;
    requires oci.java.sdk.common;

    opens com.example.jobsearchhndnetworkingcd_groupthirtyseven to javafx.fxml;
    exports com.example.jobsearchhndnetworkingcd_groupthirtyseven;
}