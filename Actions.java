import BankSystem.BankSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import BankSystem.BankSystem.*;

public class Actions {
    private static List<Bank> banks = new ArrayList<Bank>();
    private static List<Customer> customers = new ArrayList<Customer>();
    private static List<Account> accounts = new ArrayList<Account>();
    private static int accountID = 1;
    private static int customerID = 1;
    public void genData(){
        Bank bidv = new Bank(1, "BIDV", "CauGiay");
        Bank techcom = new Bank(2, "TechcomBank", "MeTri");
        banks.add(bidv);  banks.add(techcom);
        Customer customer1 = new Customer(1, "nguyennam", "nguyennam123", "HaNoi");
        customer1.openAccount(accountID,1);
        Customer customer2 = new Customer(2, "nguyennam2", "nguyennam123", "ThaiBinh");
        customer2.openAccount(accountID+1,1);
        accountID=3; customerID=3;
        customers.add(customer1);  customers.add(customer2);

    }
    public boolean isAccountExist(String username){
        boolean check = false;
        for(Customer c:customers) {
            if (c.getName().equals(username)) {
                check = true;
            }
        }
        return check;
    }
    public boolean checkPassword(String username, String password){
        boolean check = false;
        for(Customer c:customers){
            if(c.getName().equals(username)){
                if(c.getPassword().equals(password)){
                    check=true;
                    break;
                }
            }
        }
        return check;
    }

    public void createAccount(){
        Scanner input = new Scanner(System.in);
        System.out.println("Select a bank from below list:");
        for(Bank bank : banks){
            System.out.println(bank.getBankID()+"."+bank.getName()+" at "+bank.getLocation());
        }
        System.out.print("Bank option: ");
        int bank = input.nextInt();
        System.out.print("Enter your username:");
        String username = input.next();
        while(true){
            if(isAccountExist(username)){
                System.out.println("Username "+username+" exists.Please enter a different username");
            }
            else{
                System.out.print("Enter your password:");
                String password = input.next();
                System.out.print("Enter your address (city only):");
                String address = input.next();
                Customer newCustomer = new Customer(customerID,username,password,address);
                newCustomer.openAccount(accountID,bank);
                customers.add(newCustomer); accounts.add(newAccount);
                accountID ++; customerID++;
                break;
            }
        }
    }
    public void display() {
        while (true){
            Scanner input = new Scanner(System.in);
            System.out.println("----BANKONG SYSTEM WELCOME CUSTOMER----");
            System.out.println("Select your options:"); //option1
            System.out.println("1. Login");
            System.out.println("2. Create account");
            System.out.println("0. Exit");
            System.out.print("Your option: ");
            int option1 = input.nextInt();
            if(option1==2){
                createAccount();
            }
            else if(option1==0) break;
            else{
                System.out.print("Enter username:");
                String username = input.next();
                boolean accountExist=false;
                Customer loggedCustomer = null;
                for(Customer c:customers){
                    if(c.getName().equals(username)){
                        ///////////////////CHECK PASSWORD//////////////////////
                        while(true){
                            System.out.print("Enter password:");
                            String password = input.next();
                            if(checkPassword(username, password)){
                                System.out.println("Welcome back, "+username);
                                loggedCustomer=c;
                                accountExist=true;
                                break;
                            }
                            else{
                                System.out.println("Wrong password");
                            }
                        }
                        System.out.println("LOGGED IN");
                        //////////////////////////////////////////////////////
                        break;
                    }
                }
                if(!accountExist){
                    System.out.println("Account not exist. Please select option");
                    System.out.println("1. Create new Account");
                    System.out.println("0. Exit");
                    System.out.print("Your option:");
                    int option2 = input.nextInt();
                    if(option2==0) break;
                    else{
                        createAccount();
                    }
                }
                else{
                    while(true){
                        System.out.println("Please select option");
                        System.out.println("1. Deposit money");
                        System.out.println("2. Withdraw money");
                        System.out.println("3. View account information");
                        System.out.println("0. Exit");
                        System.out.print("Your option:");
                        int option3 = input.nextInt();
                        if(option3==0) break;
                        else if(option3==3) loggedCustomer.print();
                        else if(option3==1) {
                            System.out.print("Please enter your deposit amount: ");
                            int deposit = input.nextInt();
                            loggedCustomer.depositMoney(deposit);
                        }
                        else if(option3==2) {
                            while(true){
                                System.out.print("Please enter your withdrawal amount: ");
                                int withdrawal = input.nextInt();
                                if(loggedCustomer.withdrawMoney(withdrawal)){
                                    break;
                                }
                            }
                        }
                    }
                    break;
                }
            }
        }
    }
}
