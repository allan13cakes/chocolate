package com.allan.automation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.allan.automation.dao.AutoActionRepository;
import com.allan.automation.dao.AutoFlowRepository;
import com.allan.automation.dao.AutoParameterRepository;
import com.allan.automation.dao.AutoProjectRepository;
import com.allan.automation.dao.AutoTestCaseRepository;
import com.allan.automation.entites.AutoAction;
import com.allan.automation.entites.AutoActionFlow;
import com.allan.automation.entites.AutoFlow;
import com.allan.automation.entites.AutoParameter;
import com.allan.automation.entites.AutoProject;
import com.allan.automation.entites.AutoTestCase;
import com.allan.automation.entites.AutoTestStep;

@SpringBootApplication
public class AutomationProjectmanagementApplication {
	@Autowired
	private AutoFlowRepository autoFlowRepo;
	@Autowired
	private AutoActionRepository autoActionRepo;

	@Autowired
	private AutoTestCaseRepository autoTestCaseRepo;

	@Autowired
	private AutoProjectRepository autoProjectRepo;
	
	@Autowired
	private AutoParameterRepository autoParameterRepo;

	public static void main(String[] args) {
		SpringApplication.run(AutomationProjectmanagementApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner() {
		return args -> {
			System.out.println("this is commandLineRunner.");
			
			AutoAction autoAction1 = new AutoAction("action1", "description");
			
			AutoParameter p1 = new AutoParameter("aaa","123");
			AutoParameter p2 = new AutoParameter("bbb","456");
			p1.setAutoAction(autoAction1);
			p2.setAutoAction(autoAction1);
			
			
			AutoAction autoAction2 = new AutoAction("action2", "description");
			AutoAction autoAction3 = new AutoAction("action2", "description");

			AutoFlow autoFlow1 = new AutoFlow("flow1");

			AutoActionFlow autoActionFlow1 = new AutoActionFlow(autoFlow1, autoAction1);
			AutoActionFlow autoActionFlow2 = new AutoActionFlow(autoFlow1, autoAction2);
			AutoActionFlow autoActionFlow3 = new AutoActionFlow(autoFlow1, autoAction3);
			autoActionFlow1.setActionOrder(1);
			autoActionFlow2.setActionOrder(2);
			autoFlow1.getAutoActionFlows().add(autoActionFlow1);
			autoFlow1.getAutoActionFlows().add(autoActionFlow2);
			autoFlow1.getAutoActionFlows().add(autoActionFlow3);

			autoActionRepo.save(autoAction1);
			autoActionRepo.save(autoAction2);
			autoActionRepo.save(autoAction3);
			autoFlowRepo.save(autoFlow1);
			autoParameterRepo.save(p1);
			autoParameterRepo.save(p2);

			AutoTestCase autoTestCase = new AutoTestCase();
			autoTestCase.setJiraKey("jira1");
			autoTestCase.setStage("NOTSTARTED");
			autoTestCase.setSummary("test");

			AutoTestStep step1 = new AutoTestStep();
			step1.setAutoTestCase(autoTestCase);
			step1.setStepOrder(1);
			step1.setAutoFlow(autoFlow1);
//			step1.setAutoAction(autoAction1);

			AutoTestStep step2 = new AutoTestStep();
			step2.setAutoTestCase(autoTestCase);
			step2.setStepOrder(2);
			step2.setAutoFlow(autoFlow1);

			autoTestCase.getAutoTestSteps().add(step1);
			autoTestCase.getAutoTestSteps().add(step2);

			autoTestCaseRepo.save(autoTestCase);
			
			AutoParameter p3 = new AutoParameter("aaa","789");
			AutoParameter p4 = new AutoParameter("bbb","987");
			p3.setAutoAction(autoAction1);
			p4.setAutoAction(autoAction1);
			p3.setAutoFlow(autoFlow1);
			p4.setAutoFlow(autoFlow1);
			p3.setAutoTestStep(step1);
			p4.setAutoTestStep(step1);
			autoParameterRepo.save(p3);
			autoParameterRepo.save(p4);

			AutoProject autoProject = new AutoProject();
			autoProject.setDescription("project description");
			autoProject.setName("project1");
			autoProject.setStage("NOTSTART");
			autoProjectRepo.save(autoProject);
		};
	}
}
