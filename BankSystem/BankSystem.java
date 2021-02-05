package BankSystem;


import org.jetbrains.annotations.NotNull;

public class BankSystem {
    public static class Bank{
        private int BankID;
        private String name;
        private String location;

        public Bank(int bankID, String name, String location) {
            BankID = bankID;
            this.name = name;
            this.location = location;
        }
        public int getBankID() {
            return BankID;
        }
        public String getName() {
            return name;
        }
        public String getLocation() {
            return location;
        }
    }
    public static class Account{
        private int AccountNo;
        private int CustomerID;
        private int Balance;
        private int BankID;
        public Account(int accountNo, int customerID, int bankID) {
            AccountNo = accountNo;
            CustomerID = customerID;
            BankID = bankID;
        }
        public void upBalance(int amount){
            this.Balance += amount;
        }
        public int getBalance(){
            return this.Balance;
        }
        public void print(){
            System.out.println("Account ID "+AccountNo+" of customer with ID "+CustomerID+" with balance of "+Balance);
        }
    }
    public static class Customer{
        private int ID;
        private String name;
        private String password;
        private String address;
        private int AccountNo;
        private Account account;
        public Customer(int ID, String name,  String password, String address) {
            this.ID = ID;
            this.name = name;
            this.password = password;
            this.address = address;
        }

        public int getID() {
            return ID;
        }

        public void setID(int ID) {
            this.ID = ID;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public void openAccount(int accountNo, int bankID){
            this.AccountNo = accountNo;
            this.account = new Account(AccountNo, ID, bankID);
            this.account.print();
        }
        public void closeAccount(){
            this.account = null;
        }
        public void depositMoney(int amount){
            this.account.upBalance(amount);
            this.account.print();
        }
        public boolean withdrawMoney(int amount){
            if(this.account.getBalance() >= amount){
                this.account.upBalance(-amount);
                this.account.print();
                return true;
            }
            else{
                System.out.println("Account "+this.AccountNo+" has insufficient money to withdraw");
                return false;
            }
        }
        public void print(){
            System.out.println("Account ID "+AccountNo+" of customer with username "+name+" with balance of "+account.getBalance());
        }
    }
}
