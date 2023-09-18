package projects.service;


import java.sql.SQLException;
import java.util.List;

import projects.dao.ProjectDao;

import projects.entity.Project;
import projects.exception.Dbexception;

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
	return projectDao.fetchProjectById(projectId).orElseThrow();
}
public void modifyProjectDetails(Project project) {
	if(!projectDao.modifyProjectDetails(project)) {
		throw new Dbexception("Project withID=" + project.getProjectId() + " does not exist.");
		
	}
}

public void deleteProject(Integer projectId) {
	if(!projectDao.deleteProject(projectId)) {
		throw new Dbexception("Project with ID=" + projectId + " does not exist.");
		
	}
}

}
