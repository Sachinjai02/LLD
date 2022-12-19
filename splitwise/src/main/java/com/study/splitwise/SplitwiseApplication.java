package com.study.splitwise;

import com.study.splitwise.controllers.ExpenseController;
import com.study.splitwise.controllers.GroupController;
import com.study.splitwise.controllers.SettleUpController;
import com.study.splitwise.controllers.UserController;
import com.study.splitwise.dtos.CreateExpenseDto;
import com.study.splitwise.dtos.CreateGroupRequestDto;
import com.study.splitwise.dtos.RegisterUserRequestDto;
import com.study.splitwise.dtos.RegisterUserResponseDto;
import com.study.splitwise.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@SpringBootApplication
public class SplitwiseApplication implements CommandLineRunner {

    private UserController userController;
    private GroupController groupController;
    private ExpenseController expenseController;
    private SettleUpController settleUpController;

    @Autowired
    public SplitwiseApplication(UserController userController, GroupController groupController, ExpenseController expenseController, SettleUpController settleUpController) {
        this.userController = userController;
        this.groupController = groupController;
        this.expenseController = expenseController;
        this.settleUpController = settleUpController;
    }

    public static void main(String[] args) {
        SpringApplication.run(SplitwiseApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        //register Sachin
        Long sachinId = registerUser("Sachin Jain", "1234567890", "12345");

        Long friend1Id = registerUser("Friend1", "1234567891", "12345");
        Long friend2Id = registerUser("Friend2", "1234567892", "12345");
        Long friend3Id = registerUser("Friend3", "1234567893", "12345");

        //check logic for login
        System.out.println(userController.login("1234567890", "12345"));
        System.out.println(userController.login("1234567890", "123456"));

        //Create a group
        CreateGroupRequestDto groupRequestDto = new CreateGroupRequestDto();
        groupRequestDto.setDesc("fun fun fun");
        groupRequestDto.setName("Goa___Trip");
        groupRequestDto.setCreatedByUserId(sachinId);
        groupRequestDto.setMembers(Arrays.asList(sachinId, friend2Id, friend1Id, friend3Id));
        Long goaTrip = groupController.createGroup(groupRequestDto).getData().getId();

        //Create individual expenses with friend1 & Sachin
        createExpense(500D, "Borrowed by Sachin", "Borrowed by Sachin for lunch",
                Arrays.asList(friend1Id), Arrays.asList(500D),
                Arrays.asList(sachinId), Arrays.asList(500D),
                sachinId, null);

        //Create individual expense with friend1, friend2 & friend3
        createExpense(1000D, "Lunch at Dominos", "Lunch on 25th DEC",
                Arrays.asList(friend1Id, friend2Id), Arrays.asList(300D, 700D),
                Arrays.asList(friend1Id, friend2Id, friend3Id), Arrays.asList(333.33D, 333.33D, 333.33D),
                friend1Id, null);

        //Create group expense with all friends
        createExpense(8000D, "Beer", "Beer all night!!",
                Arrays.asList(friend1Id, friend2Id), Arrays.asList(5000D, 3000D),
                Arrays.asList(friend1Id, friend2Id, friend3Id, sachinId), Arrays.asList(2000D, 2000D, 2000D,2000D),
                friend1Id, goaTrip);

        //create group expense with f1, f2, and f3
        createExpense(3000D, "Fun ride", "rented bikes",
                Arrays.asList(friend3Id), Arrays.asList(3000D),
                Arrays.asList(friend1Id, friend2Id, friend3Id), Arrays.asList(1000D, 1000D, 1000D),
                friend3Id, goaTrip);


    }

    private void createExpense(Double amount, String title, String desc, List<Long> paidByList,
                               List<Double> paidByAmt, List<Long> owedByList, List<Double> owedByAmt,
                               Long createdByUserId, Long groupId) {
        try {
            CreateExpenseDto createExpenseDto = new CreateExpenseDto();
            createExpenseDto.setCreatedByUserId(createdByUserId);
            createExpenseDto.setAmount(amount);
            createExpenseDto.setDesc(desc);
            createExpenseDto.setName(title);
            createExpenseDto.setGroupId(groupId);
            createExpenseDto.setPaidBy(new HashMap<Long, Double>() {{
                for (int i = 0; i < paidByAmt.size(); ++i) {
                    put(paidByList.get(i), paidByAmt.get(i));
                }
            }});

            createExpenseDto.setOwedBy(new HashMap<Long, Double>() {{
                for (int i = 0; i < owedByList.size(); ++i) {
                    put(owedByList.get(i), owedByAmt.get(i));
                }
            }});
            expenseController.createExpense(createExpenseDto);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Long registerUser(String name, String phone, String pwd) {
        RegisterUserRequestDto requestDto = new RegisterUserRequestDto();
        requestDto.setPassword(pwd);
        requestDto.setPhoneNumber(phone);
        requestDto.setUserName(name);
        RegisterUserResponseDto<User> userRegisterUserResponseDto = userController.registerUser(requestDto);
        return userRegisterUserResponseDto.getData().getId();
    }
}
