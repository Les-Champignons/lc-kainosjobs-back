package org.kainos.ea.mappers;

import org.kainos.ea.requests.JobRoleRequest;
import org.kainos.ea.responses.JobRoleResponse;

import java.util.List;
import java.util.stream.Collectors;

public final class JobRoleMapper {
    private JobRoleMapper() {

    }

    public static List<JobRoleResponse> mapJobRoleRequestToJobRoleResponse(
            final List<JobRoleRequest> jobRoleRequest) {
            return jobRoleRequest.stream()
                .map(jobRole -> new JobRoleResponse(jobRole.getJobRoleId(),
                        jobRole.getRoleName(), jobRole.getLocation(),
                        jobRole.getClosingDate(), jobRole.getCapabilityName(),
                        jobRole.getBandName())).collect(Collectors.toList());
    }
}

