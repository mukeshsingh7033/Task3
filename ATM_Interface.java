package com.codsoft.com;
import java.util.Scanner;


class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposit of $" + amount + " successful. Current balance: $" + balance);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawal of $" + amount + " successful. Current balance: $" + balance);
        } else {
            System.out.println("Invalid withdrawal amount or insufficient balance.");
        }
    }
}

class ATM {
    private BankAccount bankAccount;

    public ATM(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public void displayMenu() {
        System.out.println("ATM Menu:");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Exit");
    }

    public void performTransaction(int choice, Scanner scanner) {
        switch (choice) {
            case 1:
                System.out.println("Current balance: $" + bankAccount.getBalance());
                break;
            case 2:
                System.out.print("Enter deposit amount: $");
                if (scanner.hasNextDouble()) {
                    double depositAmount = scanner.nextDouble();
                    bankAccount.deposit(depositAmount);
                } else {
                    System.out.println("Invalid input. Please enter a valid number.");
                    scanner.nextLine();
                }
                break;
            case 3:
                System.out.print("Enter withdrawal amount: $");
                if (scanner.hasNextDouble()) {
                    double withdrawalAmount = scanner.nextDouble();
                    bankAccount.withdraw(withdrawalAmount);
                } else {
                    System.out.println("Invalid input. Please enter a valid number.");
                    scanner.nextLine(); 
                }
                break;
            case 4:
                System.out.println("Exiting ATM. Thank you!");
                break;
            default:
                System.out.println("Invalid choice. Please select a valid option.");
        }
    }
}

public class ATM_Interface {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter initial account balance: $");
        double initialBalance = scanner.nextDouble();
        BankAccount bankAccount = new BankAccount(initialBalance);

        ATM atm = new ATM(bankAccount);

        boolean running = true;
        while (running) {
            atm.displayMenu();
            System.out.print("Select an option: ");
            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                if (choice == 4) {
                    atm.performTransaction(choice, scanner);
                    running = false;
                } else {
                    atm.performTransaction(choice, scanner);
                }
            } else {
                System.out.println("Invalid input. Please enter a valid option.");
                scanner.nextLine(); 
            }
        }

        scanner.close(); 
    }
}