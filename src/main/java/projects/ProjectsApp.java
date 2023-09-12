package projects;

import java.math.BigDecimal;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import projects.service.ProjectService;
import projects.entity.Project;
import projects.exception.Dbexception;

public class ProjectsApp {

    @SuppressWarnings("unused")
	private List<String> operations = List.of("1) Add a project");
    private Scanner scanner = new Scanner(System.in);
    private ProjectService projectService = new ProjectService();
    private Project curProject;

    private void processUserSelections() {
        boolean done = false;
        while (!done) {
            try {
            	System.out.println("Enter a menu selection");
            
            	System.out.println("1) Add a project");
            	System.out.println("2) List projects");
            	System.out.println("3) Select a project");
                int selection = getUserSelection();
                switch (selection) {
                    case -1:
                        done = true;
                        break;
                    case 1:
                    	createProject();
                        System.out.println("You selected: Add a project");
                       
                      
                        break;
                    case 2:
                    	listProjects();
                    	break;
                    case 3:
                    	selectProject();
                    	break;
                    default:
                        System.out.println("\n" + selection + " is not a valid selection. Try again.");
                        break;
                }
            } catch (Exception e) {
                System.out.println("\nError: " + e.getMessage() + " Try again");
            }
        }
    }
    private void printOperations() {
    	System.out.println("\nThese are the available selections. Press the enter key  to quit: ");
    	operations.forEach(line -> System.out.println("  " + line));
    	
    }

    private void selectProject() {
		listProjects();
		Integer projectId = getIntInput("Enter a project ID to selct a project");
		curProject = null;
		curProject = projectService.fetchProjectById(projectId);
		
	}

	private void listProjects() {
		List<Project> projects = projectService.fetchAllProjects();
		System.out.println("\nProjects:");
		projects.forEach(project -> System.out.println("   " + project.getProjectId() + ": " + project.getProjectName()));
	}

	private int getUserSelection() {
        Integer input = getIntInput("These are the available menu selections. Press enter to exit: ");
        
        return Objects.isNull(input) ? -1 : input;
    }

    private Integer getIntInput(String prompt) {
        String input = getStringInput(prompt);
        if (Objects.isNull(input)) {
            return null;
        }
        try {
            return Integer.valueOf(input);
        } catch (NumberFormatException e) {
            throw new Dbexception(input + " is not a valid number.");
        }
    }

    private String getStringInput(String prompt) {
        System.out.print(prompt + ": ");
        String input = scanner.nextLine();
        return input.isBlank() ? null : input.trim();
    }

    public static void main(String[] args) {
        new ProjectsApp().processUserSelections();
    }
    private void createProject() {
    	String projectName = getStringInput("Enter the project name");
    	BigDecimal estimatedHours = getDecimalInput("Enter the estimates hours");
    	BigDecimal actualHours = getDecimalInput("Enter the actual hours");
    	Integer difficulty = getIntInput("Enter the project difficulty(1-5)");
    	String notes= getStringInput("Enter the project notes");
    	Project project = new Project();
    	project.setProjectName(projectName);
    	project.setEstimatedHours(estimatedHours);
    	project.setActualHours(actualHours);
    	project.setDifficulty(difficulty);
    	project.setNotes(notes);
    	Project dbProject = ProjectService.addProject(project);
    	System.out.println("You have successfully created project: " + dbProject);
    }

	private BigDecimal getDecimalInput(String prompt) {
		
		String input = getStringInput(prompt);
		if(Objects.isNull(input)) {
			return null;
		}
		try {
			return new BigDecimal(input).setScale(2);
		}
		catch(NumberFormatException e) {
			throw new Dbexception(input + " is not a valid decimal number.");
		}
	}
}