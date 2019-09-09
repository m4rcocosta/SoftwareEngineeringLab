package com.softeng.professorws;

import javax.jws.WebService;

@WebService
public interface ProfessorWSInterface {
    Professor getDetails(String id);
}
