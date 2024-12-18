package org.kainos.ea.dao;
import org.kainos.ea.models.JobRoleRequest;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class JobRoleDao {
    private static final Logger LOGGER = Logger.getLogger(
            JobRoleDao.class.getName());

    public List<JobRoleRequest> getAllJobRoles() {
        List<JobRoleRequest> jobRoles = new ArrayList<>();
        try (Connection connection = DatabaseConnector.getConnection()) {
            Statement statement = connection.createStatement();

            ResultSet resultSet =
                    statement.executeQuery(
                                    "SELECT jobRoleId, roleName, location, "
                                            +
                                            "closingDate, "
                                            +
                                            "capabilityName, bandName "
                                            +
                                            "FROM JobRoles JOIN Capability"
                                            +
                                            " ON JobRoles.capabilityId JOIN "
                                            +
                                            "Band ON JobRoles.bandId;"
                            );


            while (resultSet.next()) {
                JobRoleRequest jobRoleRequest = new JobRoleRequest(
                        resultSet.getInt("jobRoleId"),
                        resultSet.getString("roleName"),
                        resultSet.getString("location"),
                        resultSet.getDate("closingDate"),
                        resultSet.getString("capabilityName"),
                        resultSet.getString("bandName")
                );
                jobRoles.add(jobRoleRequest);
            }
            LOGGER.info("Successfully returned job roles");
            return jobRoles;
        } catch (SQLException e) {
            LOGGER.severe("SEVERE: SQL Exception: " + e.getMessage());
            return null;
        }
    }
}
