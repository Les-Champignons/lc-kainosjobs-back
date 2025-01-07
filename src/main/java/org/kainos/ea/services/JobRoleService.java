package org.kainos.ea.services;

import org.kainos.ea.dao.JobRoleDao;
import org.kainos.ea.mappers.JobRoleMapper;
import org.kainos.ea.models.JobRoleDetailedRequest;
import org.kainos.ea.responses.JobRoleDetailedResponse;
import org.kainos.ea.responses.JobRoleResponse;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

public class JobRoleService {
    JobRoleDao jobRoleDao;
    private static final Logger LOGGER = Logger.getLogger(
            JobRoleService.class.getName());

    public JobRoleService(final JobRoleDao jobRoleDao) {
        this.jobRoleDao = jobRoleDao;
    }

    public List<JobRoleResponse> getAllJobRoles() throws SQLException {
        LOGGER.info("Successfully returned job roles");
        return JobRoleMapper.mapJobRoleRequestToJobRoleResponse(
                jobRoleDao.getAllJobRoles());
    }

    public JobRoleDetailedResponse getDetailedJobRole(final int id) throws SQLException {
        JobRoleDetailedRequest jobRoleDetailedRequest = jobRoleDao.getJobRoleInformationById(id);
        return new JobRoleDetailedResponse(
                    jobRoleDetailedRequest.getJobRoleId(),
                    jobRoleDetailedRequest.getStatusName(),
                    jobRoleDetailedRequest.getBandName(),
                    jobRoleDetailedRequest.getCapabilityName(),
                    jobRoleDetailedRequest.getJobRoleDetailedParameters()
        );
    }
}
