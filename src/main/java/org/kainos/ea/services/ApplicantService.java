package org.kainos.ea.services;

import org.kainos.ea.daos.ApplicantDao;
import org.kainos.ea.exceptions.FailedtoCreateException;
import org.kainos.ea.mappers.ApplicantMapper;
import org.kainos.ea.requests.ApplicantRequest;
import org.kainos.ea.responses.ApplicantResponse;

import java.sql.SQLException;
import java.util.List;

public class ApplicantService {
    ApplicantDao applicantDao;

    public ApplicantService(final ApplicantDao applicantDao) {
        this.applicantDao = applicantDao;
    }

    public int createApplicant(final ApplicantRequest applicantRequest)
            throws FailedtoCreateException {
        int id = applicantDao.createApplicant(applicantRequest);

        if (id == -1) {
            throw new FailedtoCreateException();
        }

        return id;
    }

    public List<ApplicantResponse> selectApplicants()
        throws SQLException {
        return ApplicantMapper.mapApplicantToApplicantResponse(
                applicantDao.selectApplicants());
    }
}
