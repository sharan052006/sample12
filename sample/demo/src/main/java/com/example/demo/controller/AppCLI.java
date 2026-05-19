package com.example.demo.controller;

import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.demo.entity.Payment;
import com.example.demo.entity.Request;
import com.example.demo.entity.Review;
import com.example.demo.entity.Skill;
import com.example.demo.entity.User;
import com.example.demo.service.AppService;

@Component
public class AppCLI implements CommandLineRunner {

    private final AppService appService;

    public AppCLI(AppService appService) {
        this.appService = appService;
    }

    @Override
    public void run(String... args) throws Exception {

        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\n===== SKILL SWAP CLI =====");
            System.out.println("1. Create User");
            System.out.println("2. Create Skill");
            System.out.println("3. Search Skill");
            System.out.println("4. Create Request");
            System.out.println("5. Accept Request");
            System.out.println("6. Reject Request");
            System.out.println("7. Create Payment");
            System.out.println("8. Create Review");
            System.out.println("9. Exit");

            System.out.print("Enter Choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:

                    User user = new User();

                    System.out.print("Enter Name: ");
                    user.setUsername(sc.nextLine());

                    System.out.print("Enter Email: ");
                    user.setEmail(sc.nextLine());

                    User savedUser = appService.createUser(user);

                    System.out.println("User Created: " + savedUser);

                    break;

                case 2:

                    System.out.print("Enter User ID: ");
                    Long userId = sc.nextLong();
                    sc.nextLine();

                    Skill skill = new Skill();

                    System.out.print("Enter Skill Name: ");
                    skill.setSkillName(sc.nextLine());

                    Skill savedSkill = appService.createSkill(userId, skill);

                    System.out.println("Skill Added: " + savedSkill);

                    break;

                case 3:

                    System.out.print("Enter Skill Name: ");
                    String skillName = sc.nextLine();

                    Skill foundSkill = appService.getSkillByName(skillName);

                    System.out.println(foundSkill);

                    break;

                case 4:

                    System.out.print("Sender ID: ");
                    Long senderId = sc.nextLong();

                    System.out.print("Receiver ID: ");
                    Long receiverId = sc.nextLong();

                    System.out.print("Skill ID: ");
                    Long skillId = sc.nextLong();

                    Request request =
                            appService.createRequest(senderId, receiverId, skillId);

                    System.out.println("Request Created: " + request);

                    break;

                case 5:

                    System.out.print("Request ID: ");
                    Long acceptId = sc.nextLong();

                    Request accepted =
                            appService.AcceptRequest(acceptId);

                    System.out.println("Accepted: " + accepted);

                    break;

                case 6:

                    System.out.print("Request ID: ");
                    Long rejectId = sc.nextLong();

                    Request rejected =
                            appService.RejectRequest(rejectId);

                    System.out.println("Rejected: " + rejected);

                    break;

                case 7:

                    System.out.print("Request ID: ");
                    Long reqId = sc.nextLong();

                    System.out.print("Amount: ");
                    Double amount = sc.nextDouble();

                    Payment payment =
                            appService.createPayment(reqId, amount);

                    System.out.println("Payment Created: " + payment);

                    break;

                case 8:

                    System.out.print("Request ID: ");
                    Long reviewReqId = sc.nextLong();
                    sc.nextLine();

                    System.out.print("Comment: ");
                    String comment = sc.nextLine();

                    System.out.print("Rating: ");
                    int rating = sc.nextInt();

                    Review review =
                            appService.createReview(reviewReqId, comment, rating);

                    System.out.println("Review Added: " + review);

                    break;

                case 9:

                    System.out.println("Exiting...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid Choice");
            }
        }
    }
}