package org.kainos.ea.daos;

import org.kainos.ea.dao.DatabaseConnector;
import org.kainos.ea.models.Applicant;
import org.kainos.ea.requests.ApplicantRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ApplicantDao {

    private static final Logger LOGGER = Logger.getLogger(
            ApplicantDao.class.getName());
    public int createApplicant(final ApplicantRequest applicantRequest) {
        try (Connection connection = org.kainos.ea.dao.DatabaseConnector.getConnection()) {
            String query = "INSERT INTO Applicants "
                    +
                    "(email, jobRoleName, etag)"
                    +
                    " VALUES (?, ?, ?);";
            PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            final int emailIndex = 1;
            final int jobRoleNameIndex = 2;
            final int etagIndex = 3;

            ps.setString(emailIndex, applicantRequest.getEmail());
            ps.setString(jobRoleNameIndex, applicantRequest.getJobRoleName());
            ps.setString(etagIndex, applicantRequest.getEtag());

            ps.executeUpdate();

            ResultSet res = ps.getGeneratedKeys();
            if (res.next()) {
                return res.getInt(1);
            }
            return -1;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Applicant> selectApplicants() {
        List<Applicant> applicants = new ArrayList<>();

        try (Connection connection = DatabaseConnector.getConnection()) {
            String query =
                    "SELECT email, jobRoleName, etag, status FROM Applicants";
            PreparedStatement ps = connection.prepareStatement(query);

            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                Applicant applicant = new Applicant(
                        resultSet.getString("email"),
                        resultSet.getString("jobRoleName"),
                        resultSet.getString("etag"),
                        resultSet.getString("status")
                );

                applicants.add(applicant);
            }
            LOGGER.info("Successfully returned applicants");
            return applicants;
        } catch (SQLException e) {
            LOGGER.severe("SEVERE: SQL Exception: " + e.getMessage());
            return null;
        }
    }
}
