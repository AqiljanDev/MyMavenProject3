package org.example;


import org.apache.log4j.Logger;
import org.example.exeption.DaoExeption;
import org.example.util.ConnectionManager;

import java.sql.*;

public class Main {

    private static Logger logger = Logger.getLogger(Main.class.getName());
    public static void main(String[] args) {

        logger.info("Пустая трата времени");

        //Hello
        try(var conn = ConnectionManager.open()) {
            System.out.println("float");
            logger.info("Active try, good job");

            throw new SQLException();
        }catch (SQLException e) {
            logger.error("Cannot create main");
            throw new DaoExeption(e);
        }

    }


}
