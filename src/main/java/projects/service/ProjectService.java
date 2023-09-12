package projects.service;


import java.sql.SQLException;
import java.util.List;

import projects.dao.ProjectDao;

import projects.entity.Project;

public class ProjectService {
private static ProjectDao projectDao = new ProjectDao();

public static Project addProject(Project project) {
	
		try {
			return projectDao.insertProject(project);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return project;
	
}
public List<Project> fetchAllProjects(){
	return projectDao.fetchAllProjects();
}
public Project fetchProjectById(Integer projectId) {
	return projectDao.fetchProjectById(projectId).orElseThrow(() -> new NoSuchElementException("Project with project ID=" + projectId + "does not exist."));
}
}
