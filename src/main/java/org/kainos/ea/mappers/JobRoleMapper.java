package org.kainos.ea.mappers;

import org.kainos.ea.models.JobRoleRequest;
import org.kainos.ea.responses.JobRoleResponse;

import java.util.List;
import java.util.stream.Collectors;

public class JobRoleMapper {
    public static List<JobRoleResponse> mapJobRoleRequestToJobRoleResponse(
            List<JobRoleRequest> jobRoleRequest) {
            return jobRoleRequest.stream()
                .map(jobRole -> new JobRoleResponse(jobRole.getJobRoleId(),
                        jobRole.getRoleName(), jobRole.getLocation(), jobRole.getClosingDate(), jobRole.getCapabilityName(),
                        jobRole.getBandName())).collect(Collectors.toList());
    }
}

