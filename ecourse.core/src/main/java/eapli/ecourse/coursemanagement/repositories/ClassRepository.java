package eapli.ecourse.coursemanagement.repositories;
import eapli.ecourse.classmanagement.domain.SchClass;

import java.util.ArrayList;
import java.util.List;

public class ClassRepository {

    private final List<SchClass> classes = new ArrayList<>();

    public SchClass add(SchClass classs) {
        this.classes.add(classs);
        return classs;
    }

    public List<SchClass> getSchClassList() {
        return this.classes;
    }

}