package org.kainos.ea.dao;
import org.kainos.ea.models.JobRoleDetailedParameters;
import org.kainos.ea.models.JobRoleDetailedRequest;
import org.kainos.ea.models.JobRoleRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class JobRoleDao {
    private static final Logger LOGGER = Logger.getLogger(
            JobRoleDao.class.getName());

    public List<JobRoleRequest> getAllJobRoles() {
        List<JobRoleRequest> jobRoles = new ArrayList<>();
        try (Connection connection = DatabaseConnector.getConnection()) {
            String query = "SELECT jobRoleId, roleName, "
                    +
                    "location, closingDate, "
                    +
                    "capabilityName, bandName FROM JobRoles "
                    +
                    "JOIN Capability ON "
                    +
                    "JobRoles.capabilityId = Capability.capabilityId "
                    +
                    "JOIN Band ON JobRoles.bandId = Band.bandId "
                    +
                    "JOIN Status ON JobRoles.statusId = Status.statusId "
                    +
                    "WHERE statusName = 'open';";

            PreparedStatement preparedStatement = connection.prepareStatement(
                    query
            );
            ResultSet resultSet = preparedStatement.executeQuery();
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

    public JobRoleDetailedRequest getJobRoleInformationById(final int id) {
        try (Connection connection = DatabaseConnector.getConnection()) {
            String query = "SELECT roleName, location, closingDate, "
                    +
                    "`description`, responsibilities, sharepointUrl, "
                    +
                    "numberOfOpenPositions, statusName, "
                    +
                    "bandName, capabilityName"
                    +
                    " FROM JobRoles JOIN Capability ON "
                    +
                    "JobRoles.capabilityId = Capability.capabilityId "
                    +
                    "JOIN Status ON JobRoles.statusId = Status.statusId "
                    +
                    "JOIN Band ON JobRoles.bandId = Band.bandId "
                    +
                    "WHERE jobRoleId = ?;";

            PreparedStatement preparedStatement = connection.prepareStatement(
                    query
            );

            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    JobRoleDetailedRequest jobRoleDetailedRequest =
                            new JobRoleDetailedRequest(
                                    id,
                                    resultSet.getString("statusName"),
                                    resultSet.getString("bandName"),
                                    resultSet.getString("capabilityName"),
                                    new JobRoleDetailedParameters(
                                            resultSet.getString("description"),
                                            resultSet.getString(
                                                    "responsibilities"),
                                            resultSet.getString(
                                                    "sharepointUrl"),
                                            resultSet.getString("roleName"),
                                            resultSet.getString("location"),
                                            resultSet.getDate("closingDate"),
                                            resultSet.getInt(
                                                    "numberOfOpenPositions")
                                    )
                            );
                    LOGGER.info("Successfully returned job roles");
                    return jobRoleDetailedRequest;
                }
            }
            return null;
        } catch (SQLException e) {
            LOGGER.severe("SEVERE: SQL Exception: " + e.getMessage());
            return null;
        }
    }
}
