package org.kainos.ea.mappers;

import org.kainos.ea.models.Applicant;
import org.kainos.ea.responses.ApplicantResponse;

import java.util.List;
import java.util.stream.Collectors;

public final class ApplicantMapper {
    private ApplicantMapper() {
    }

    public static List<ApplicantResponse> mapApplicantToApplicantResponse(
            final List<Applicant> applicants) {
        return applicants.stream()
                .map(applicant -> new ApplicantResponse(applicant.getEmail(),
                        applicant.getJobRoleName(), applicant.getEtag(),
                        applicant.getStatus())).collect(Collectors.toList());
    }
}
