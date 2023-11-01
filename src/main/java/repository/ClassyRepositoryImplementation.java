package repository;

import raf.dsw.classycraft.app.core.ClassyRepository;
import repository.implementation.ProjectExplorer;

public class ClassyRepositoryImplementation implements ClassyRepository {
    private ProjectExplorer root;

    @Override
    public ProjectExplorer getRoot() {
        return root;
    }
}
