package com.sysManagemet.servientrega;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;

@Component
public class ConnectionTest implements CommandLineRunner {

    private final DataSource dataSource;

    public ConnectionTest(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void run(String... args) throws Exception {
        try (Connection conn = dataSource.getConnection()) {
            System.out.println(">>> CONECTADO A MySQL EXITOSAMENTE <<<");
        }
    }
}

