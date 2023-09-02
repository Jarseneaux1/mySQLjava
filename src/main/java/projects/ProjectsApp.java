package projects;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import projects.exception.Dbexception;
import java.math.BigDecimal;
import projects.service.ProjectService;
import projects.entity.Project;
import projects.entity.project;

public class ProjectsApp {

    @SuppressWarnings("unused")
	private List<String> operations = List.of("1) Add a project");
    private Scanner scanner = new Scanner(System.in);

    private void processUserSelections() {
        boolean done = false;
        while (!done) {
            try {
            	System.out.println("Enter a menu selection");
            	System.out.println("1) Add a project");
                int selection = getUserSelection();
                switch (selection) {
                    case -1:
                        done = true;
                        break;
                    case 1:
                    	createProject();
                        System.out.println("You selected: Add a project");
                      
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
    	Project dbProject = projectService.addProject(project);
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