package org.kainos.ea.services;

import org.kainos.ea.dao.ApplicantDao;
import org.kainos.ea.exceptions.FailedtoCreateException;
import org.kainos.ea.requests.ApplicantRequest;

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
}
