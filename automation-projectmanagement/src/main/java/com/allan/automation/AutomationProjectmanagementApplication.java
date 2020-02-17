package com.allan.automation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.allan.automation.dao.AutoActionRepository;
import com.allan.automation.dao.AutoFlowRepository;
import com.allan.automation.entites.AutoAction;
import com.allan.automation.entites.AutoActionFlow;
import com.allan.automation.entites.AutoFlow;

@SpringBootApplication
public class AutomationProjectmanagementApplication {
	@Autowired
	private AutoFlowRepository autoFlowRepo;
	@Autowired
	private AutoActionRepository autoActionRepo;

	public static void main(String[] args) {
		SpringApplication.run(AutomationProjectmanagementApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner() {
		return args -> {
			System.out.println("this is commandLineRunner.");
			AutoAction autoAction1 = new AutoAction("action1", "description", "[\"aaa\",\"bbb\"]");
			AutoAction autoAction2 = new AutoAction("action2", "description", "[\"aaa\",\"bbb\"]");

			AutoFlow autoFlow1 = new AutoFlow("flow1");

			AutoActionFlow autoActionFlow1 = new AutoActionFlow(autoFlow1, autoAction1);
			AutoActionFlow autoActionFlow2 = new AutoActionFlow(autoFlow1, autoAction2);
			autoActionFlow1.setActionOrder(1);
			autoActionFlow2.setActionOrder(2);
			autoFlow1.getAutoActionFlows().add(autoActionFlow1);
			autoFlow1.getAutoActionFlows().add(autoActionFlow2);

			autoActionRepo.save(autoAction1);
			autoActionRepo.save(autoAction2);
			autoFlowRepo.save(autoFlow1);

		};
	}
}
