// Save file name as AtmApp.java

import java.util.InputMismatchException;
import java.util.Scanner;

class Customer {
    private String name;
    private int cid;
    private int accNo;
    private String branch;
    private String bank;
    private int balance = 0;
    private int pin = 5566;

    public Customer(String name, int cid, int accNo, String branch, String bank) {
        this.name = name;
        this.cid = cid;
        this.accNo = accNo;
        this.branch = branch;
        this.bank = bank;
    }

    public Customer() {
    }

    public void displayDetails() {
        System.out.println("\n========== Customer Details ==========");
        System.out.printf("Name   : %s%n", name);
        System.out.printf("CID    : %d%n", cid);
        System.out.printf("Acc No : %d%n", accNo);
        System.out.printf("Branch : %s%n", branch);
        System.out.printf("Bank   : %s%n", bank);
        System.out.println("======================================");
    }

    public void deposit(int amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("✅ Amount deposited successfully.");
        } else {
            System.out.println("⚠️ Invalid deposit amount.");
        }
    }

    public void withdraw(int enteredPin, int amount) {
        if (enteredPin == pin) {
            if (amount <= balance) {
                balance -= amount;
                System.out.println("✅ Withdrawal successful.");
            } else {
                System.out.println("⚠️ Insufficient balance.");
            }
        } else {
            System.out.println("❌ Incorrect PIN.");
        }
    }

    public void checkBalance(int enteredPin) {
        if (enteredPin == pin) {
            System.out.println("💰 Your current balance is: ₹" + balance);
        } else {
            System.out.println("❌ Incorrect PIN.");
        }
    }

    public void changePin(int enteredPin, Scanner sc) {
        if (enteredPin == pin) {
            System.out.print("Enter new PIN: ");
            int newPin = sc.nextInt();
            pin = newPin;
            System.out.println("🔒 PIN changed successfully.");
        } else {
            System.out.println("❌ Incorrect current PIN.");
        }
    }
}

public class AtmApp {

    public static void displayMenu() {
        System.out.println("\n========= ATM MENU =========");
        System.out.println("1. Deposit");
        System.out.println("2. Withdraw");
        System.out.println("3. Check Balance");
        System.out.println("4. Change PIN");
        System.out.println("5. Exit");
        System.out.println("============================");
        System.out.print("Enter your choice: ");
    }

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("========== Welcome to ATM ==========\n");

            System.out.print("Enter your Name        : ");
            String name = sc.nextLine();

            System.out.print("Enter Branch           : ");
            String branch = sc.nextLine();

            System.out.print("Enter Bank Name        : ");
            String bank = sc.nextLine();

            System.out.print("Enter Customer ID      : ");
            int cid = sc.nextInt();

            System.out.print("Enter Account Number   : ");
            int accNo = sc.nextInt();

            Customer customer = new Customer(name, cid, accNo, branch, bank);
            customer.displayDetails();

            int choice;
            do {
                displayMenu();

                while (!sc.hasNextInt()) {
                    System.out.print("❌ Invalid input. Please enter a number (1-5): ");
                    sc.next(); // Clear invalid input
                }

                choice = sc.nextInt();

                switch (choice) {
                    case 1 -> {
                        System.out.print("Enter amount to deposit: ₹");
                        int depositAmount = sc.nextInt();
                        customer.deposit(depositAmount);
                    }
                    case 2 -> {
                        System.out.print("Enter PIN: ");
                        int withdrawPin = sc.nextInt();
                        System.out.print("Enter amount to withdraw: ₹");
                        int withdrawAmount = sc.nextInt();
                        customer.withdraw(withdrawPin, withdrawAmount);
                    }
                    case 3 -> {
                        System.out.print("Enter PIN: ");
                        int balancePin = sc.nextInt();
                        customer.checkBalance(balancePin);
                    }
                    case 4 -> {
                        System.out.print("Enter current PIN: ");
                        int currentPin = sc.nextInt();
                        customer.changePin(currentPin, sc);
                    }
                    case 5 -> System.out.println("\n👋 Thank you for using our ATM. Goodbye!\n");
                    default -> System.out.println("⚠️ Invalid choice. Please select between 1 and 5.");
                }

            } while (choice != 5);
        } catch (InputMismatchException e) {
            System.out.println("⚠️ Invalid input. Program terminated.");
        }
    }
}
