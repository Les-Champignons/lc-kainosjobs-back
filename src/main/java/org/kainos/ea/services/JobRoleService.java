package org.kainos.ea.services;

import org.kainos.ea.dao.JobRoleDao;
import org.kainos.ea.mappers.JobRoleMapper;
import org.kainos.ea.models.JobRoleRequest;
import org.kainos.ea.responses.JobRoleResponse;

import java.sql.SQLException;
import java.util.List;

public class JobRoleService {
    JobRoleDao jobRoleDao;

    public JobRoleService(JobRoleDao jobRoleDao){
        this.jobRoleDao = jobRoleDao;
    }

    public List<JobRoleResponse> getAllJobRoles() throws SQLException {
        return JobRoleMapper.mapJobRoleRequestToJobRoleResponse(
                jobRoleDao.getAllJobRoles());
    }
}
