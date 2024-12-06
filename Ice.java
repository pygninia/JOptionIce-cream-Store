/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ice;

import javax.swing.JOptionPane;

/**
 *
 * @author Billey
 */
public class Ice {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JOptionPane.showMessageDialog(null, "Welcome to JsR Ice Cream Store");

        double totalAmount = 0.0;
        int totalItems = 0;
        boolean hasTransaction = false;

        // Display menu
        String menu = "•──────♔•♛•♖─────•\n"
                + "♝1 Strawberry ---------------- P15.10♘\n"
                + "♝2 Chocolate ------------------ P13.30♘\n"
                + "♝3 Cookies and Cream ----- P12.25♘\n"
                + "♝4 Ube --------------------------- P14.12♘\n"
                + "♝5 Mango ----------------------- P14.22♘\n"
                + "♝6 Vanilla ----------------------- P12.25♘\n"
                + "♝7 Charcoal -------------------- P10.15♘\n"
                + "♝8 Melon ------------------------ P11.13♘\n"
                + "•──────♔•♛•♖─────•\n"
                + "\nIf you buy 5 ice creams you'll have a 15% discount!!!";

        while (true) {
            int choice = JOptionPane.showConfirmDialog(null, menu + "\n\nDo you want to continue shopping?", "Shopping", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.NO_OPTION) {
                if (!hasTransaction) { // Check if no items were purchased
                    double donation = -1;
                    while (donation < 1) {
                        String donationInput = JOptionPane.showInputDialog("Instead of not buying my product, donate any amount for charity:");
                        try {
                            donation = Double.parseDouble(donationInput);
                            if (donation > 99) {
                                JOptionPane.showMessageDialog(null, String.format("The amount we received: P%.2f!\nThank you so much, you have such a good heart!", donation));
                                return;
                            } else if (donation > 0.99) {
                                JOptionPane.showMessageDialog(null, String.format("Thank you for your donation of P%.2f!", donation));
                                return;
                            } else {
                                JOptionPane.showMessageDialog(null, "Please have a heart, please donate.");
                            }
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "Please enter a valid amount.");
                        }
                    }
                }
                break; // Exit the shopping loop
            }

            String itemChoiceInput = JOptionPane.showInputDialog("Enter the number of the item you want to buy (0 for exit):");
            int choiceNumber;
            try {
                choiceNumber = Integer.parseInt(itemChoiceInput);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a number.");
                continue;
            }

            if (choiceNumber == 0) {
                if (hasTransaction) {
                    JOptionPane.showMessageDialog(null, "You can't leave now, you already have transactions.");
                    continue; // User cannot exit, continue prompting
                } else {
                    JOptionPane.showMessageDialog(null, "Thank you for visiting and please come again.");
                    break;
                }
            } else if (choiceNumber < 1 || choiceNumber > 8) {
                JOptionPane.showMessageDialog(null, "Please choose only 1-8.");
                continue; // Go back to the start of the loop
            }

            String quantityInput = JOptionPane.showInputDialog("How many would you like to buy?");
            int quantity;
            try {
                quantity = Integer.parseInt(quantityInput);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a number.");
                continue;
            }

            switch (choiceNumber) {
                case 1:
                    totalAmount += 15.10 * quantity;
                    break;
                case 2:
                    totalAmount += 13.30 * quantity;
                    break;
                case 3:
                    totalAmount += 12.25 * quantity;
                    break;
                case 4:
                    totalAmount += 14.12 * quantity;
                    break;
                case 5:
                    totalAmount += 14.22 * quantity;
                    break;
                case 6:
                    totalAmount += 12.25 * quantity;
                    break;
                case 7:
                    totalAmount += 10.15 * quantity;
                    break;
                case 8:
                    totalAmount += 11.13 * quantity;
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid number.");
                    continue; // Go back to the start of the loop
            }

            totalItems += quantity;
            hasTransaction = true; // Set transaction flag to true

            String message = String.format("You selected %d for item %d.\nCurrent Total Amount: P%.2f", quantity, choiceNumber, totalAmount);
            JOptionPane.showMessageDialog(null, message);

            // Apply 15% discount if total items reach 5 or more
            if (totalItems >= 5) {
                totalAmount *= 0.85;
                JOptionPane.showMessageDialog(null, String.format("A 15%% discount has been applied! New Total Amount: P%.2f", totalAmount));
            }
        }

        // Payment process
        if (totalAmount > 0) { // Only proceed if there's an amount to pay
            double payment = -1;
            while (payment < totalAmount) {
                String paymentInput = JOptionPane.showInputDialog("Enter the amount of money you are paying (with cents if needed):");
                try {
                    payment = Double.parseDouble(paymentInput);
                    if (payment == totalAmount) {
                        JOptionPane.showMessageDialog(null, "You don't have any change.\nThank you and come again!");
                        break; // Exit the payment loop
                    } else if (payment > totalAmount) {
                        double change = payment - totalAmount;
                        JOptionPane.showMessageDialog(null, String.format("Your change: P%.2f\nThank you and come again!", change));
                        break; // Exit the payment loop
                    } else {
                        JOptionPane.showMessageDialog(null, String.format("Insufficient amount, please try again. Missing P%.2f", totalAmount - payment));
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid amount.");
                }
            }
        }
    }
}

