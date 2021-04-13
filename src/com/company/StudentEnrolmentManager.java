package com.company;

import java.io.FileNotFoundException;
import java.util.ArrayList;

interface StudentEnrolmentManager {


    void addEnrolment() throws FileNotFoundException;

    void updateEnrolment() throws FileNotFoundException;

    void deleteEnrolment() throws FileNotFoundException;

    void getOne(String sid, String cid, String semester) throws FileNotFoundException;

    void getAll() throws FileNotFoundException;

}
