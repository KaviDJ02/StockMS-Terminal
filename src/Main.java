import java.util.Scanner;

public class Main{
    public static Scanner scanner = new Scanner(System.in);
    private static String[] usernames = {"admin", "kavindu"};
    private static String[] passwords = {"admin", "abc123"};
    private static String[][] suppliers = {{"S001","S002"},{"Kavindu", "Ravindu"}};
    private static String[] itemCategories = {"FOOD"};
    private static String[][] items = {{"S001"},{"I001"},{"TOFEE"},{"50"},{"120"},{"FOOD"}};

    public static void credentialPage(){
        clearConsole();

        System.out.println("+----------------------------------------------------------------------------------------------------------+\n" +
                "|                                             CREDENTIAL MANAGE                                            |\n" +
                "+----------------------------------------------------------------------------------------------------------+\n");

        boolean isValid = false;
        String username = "";

        while (!isValid){
            System.out.print("Please enter the user name to verify it's you : ");
            String temp = scanner.nextLine();
            username = temp;
            isValid = loginAuth(temp);

            if(!isValid){
                System.out.println("invalid user name. try again!\n");
            }
        }

        isValid = false;
        String tempPW = "";

        while (!isValid){
            System.out.print("Enter your current password : ");
            String temp = scanner.nextLine();
            tempPW = temp;
            isValid = loginAuthPW(temp,username);

            if(!isValid){
                System.out.println("invalid password. try again!\n");
            }
        }

        int index = 0;

        for (int i = 0; i < passwords.length; i++) {
            if (tempPW.equals(passwords[i])) {
                index = i;
            }
        }

        System.out.print("Enter your new password : ");
        String newPW = scanner.nextLine();

        passwords[index] = newPW;

        System.out.print("Password changed successfully! Do you want to go home page (Y/N) : ");
        char ans = scanner.next().charAt(0);
        scanner.nextLine();

        while (ans != 'Y' && ans != 'y' && ans != 'N' && ans != 'n') {
            System.out.println("Invalid input. Please enter 'Y' or 'N'.\n");
            System.out.print("Do you want to go home page (Y/N) ? ");
            ans = scanner.next().charAt(0);
            scanner.nextLine();
        }

        if (ans == 'Y' || ans == 'y') {
            homePage();
        } else {
            credentialPage();
        }
    }
    private final static void clearConsole() {
        final String os = System.getProperty("os.name");
        try {
            if (os.contains("Linux")) {
                System.out.print("\033\143");
            } else if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (final Exception e) {
            System.err.println(e.getMessage());
        }
    }
    public static void homePage(){
        clearConsole();
        System.out.println("+----------------------------------------------------------------------------------------------------------+\n" +
                "|                                    WELCOME TO IJSE STOCK MANAGEMENT SYSTEM                               |\n" +
                "+----------------------------------------------------------------------------------------------------------+\n");
        System.out.println("[1] Change the Credentials\t\t [2] Supplier Manage\n" +
                "[3] Stock Manage          \t\t [4] Log out\n" +
                "[5] Exit the system\n");

        int option = 0;
        boolean validInput = false;

        while (!validInput) {
            System.out.print("Enter an option to continue > ");
            String input = scanner.nextLine();

            try {
                option = Integer.parseInt(input);
                if (option >= 1 && option <= 5) {
                    validInput = true;
                } else {
                    System.out.println("Invalid option. Please enter a number between 1 and 5.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 5.");
            }
        }

        switch (option){
            case 1 -> credentialPage();
            case 2 -> supplierManagePage();
            case 3 -> stockManagePage();
            case 4 -> loginPage();
            case 5 -> System.out.println("Good Bye!");
        }
    }
    public static void loginPage(){
        clearConsole();
        boolean isValid = false;

        System.out.println("+----------------------------------------------------------------------------------------------------------+\n" +
                "|                                                 LOGIN PAGE                                               |\n" +
                "+----------------------------------------------------------------------------------------------------------+\n");

        String username = "";

        while (!isValid){
            System.out.print("User Name : ");
            String temp = scanner.nextLine();
            username = temp;
            isValid = loginAuth(temp);

            if(!isValid){
                System.out.println("User name is invalid. please try again!\n");
            }
        }

        isValid = false;

        while (!isValid){
            System.out.print("Password : ");
            String temp = scanner.nextLine();
            isValid = loginAuthPW(temp,username);

            if(!isValid){
                System.out.println("Password is invalid. please try again!\n");
            }
        }

        homePage();
    }

    public static boolean loginAuthPW(String pw,String user) {

        int indexUser = -1;

        for (int j = 0; j < usernames.length; j++) {
            if(usernames[j].equals(user)){
                indexUser = j;
            }
        }

        if(passwords[indexUser].equals(pw)){
            return true;
        }
        return false;
    }
    public static boolean loginAuth(String input) {

        for (String s : usernames) {
            if (input.equals(s)) {
                return true;
            }
        }
        return false;
    }
    public static void supplierManagePage(){
        clearConsole();

        System.out.println("+----------------------------------------------------------------------------------------------------------+\n" +
                "|                                              SUPPLIER MANAGE                                             |\n" +
                "+----------------------------------------------------------------------------------------------------------+\n");
        System.out.println("[1] Add Supplier           \t\t [2] Update Supplier\n" +
                "[3] Delete Supplier        \t\t [4] View Supplier\n" +
                "[5] Search Supplier        \t\t [6] Home Page\n");

        int option = 0;
        boolean validInput = false;

        while (!validInput) {
            System.out.print("Enter an option to continue > ");
            String input = scanner.nextLine();

            try {
                option = Integer.parseInt(input);
                if (option >= 1 && option <= 6) {
                    validInput = true;
                } else {
                    System.out.println("Invalid option. Please enter a number between 1 and 6.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 6.");
            }
        }

        switch (option){
            case 1 -> addSupplierPage();
            case 2 -> updateSupplierPage();
            case 3 -> deleteSupplierPage();
            case 4 -> viewSupplierPage();
            case 5 -> searchSupplierPage();
            case 6 -> homePage();
        }
    }
    public static void addSupplierPage(){
        clearConsole();

        System.out.println("+----------------------------------------------------------------------------------------------------------+\n" +
                "|                                                ADD SUPPLIER                                              |\n" +
                "+----------------------------------------------------------------------------------------------------------+\n");

        boolean isValid = true;
        String newID = "";

        while(isValid){
            System.out.print("Supplier ID    : ");
            newID = scanner.nextLine();

            if (isSupplierIDExists(newID)) {
                System.out.println("already exists. try another supplier id!\n");
            }
            isValid = isSupplierIDExists(newID);
        }

        System.out.print("Supplier Name  : ");
        String newName = scanner.nextLine();

        int currentSize = suppliers[0].length;

        String[] newIDs = new String[currentSize + 1];
        String[] newNames = new String[currentSize + 1];

        for (int i = 0; i < currentSize; i++) {
            newIDs[i] = suppliers[0][i];
            newNames[i] = suppliers[1][i];
        }

        newIDs[currentSize] = newID;
        newNames[currentSize] = newName;

        suppliers[0] = newIDs;
        suppliers[1] = newNames;

        System.out.print("added successfully! Do you want to add another supplier (Y/N) ? ");
        char ans = scanner.next().charAt(0);
        scanner.nextLine();

        while (ans != 'Y' && ans != 'y' && ans != 'N' && ans != 'n') {
            System.out.println("Invalid input. Please enter 'Y' or 'N'.\n");
            System.out.print("Do you want to add another supplier (Y/N) ? ");
            ans = scanner.next().charAt(0);
            scanner.nextLine();
        }

        if (ans == 'Y' || ans == 'y') {
            addSupplierPage();
        } else {
            supplierManagePage();
        }
    }
    private static boolean isSupplierIDExists(String id) {
        for (String supplierID : suppliers[0]) {
            if (supplierID.equals(id)) {
                return true;
            }
        }
        return false;
    }
    public static void updateSupplierPage() {
        clearConsole();

        System.out.println("+----------------------------------------------------------------------------------------------------------+\n" +
                "|                                             UPDATE SUPPLIER                                              |\n" +
                "+----------------------------------------------------------------------------------------------------------+\n");

        boolean isValid = true;
        int index = -1;

        while(isValid){
            System.out.print("Supplier ID      : ");
            String supId = scanner.nextLine();

            for (int i = 0; i < suppliers[0].length; i++) {
                if (suppliers[0][i].equals(supId)) {
                    index = i;
                    isValid = false;
                }
            }
            if (index == -1) {
                System.out.println("can't find supplier id. try again! \n");
                isValid = true;
            }
        }
        System.out.println("Supplier Name    : "+ suppliers[1][index]);

        System.out.print("\nEnter the new supplier name : ");
        String newName = scanner.nextLine();

        suppliers[1][index] = newName;

        System.out.print("Updated successfully! Do you want to update another supplier (Y/N) ? ");
        char ans = scanner.next().charAt(0);
        scanner.nextLine();

        while (ans != 'Y' && ans != 'y' && ans != 'N' && ans != 'n') {
            System.out.println("Invalid input. Please enter 'Y' or 'N'.\n");
            System.out.print("Do you want to update another supplier (Y/N) ? ");
            ans = scanner.next().charAt(0);
            scanner.nextLine();
        }

        if (ans == 'Y' || ans == 'y') {
            updateSupplierPage();
        } else {
            supplierManagePage();
        }
    }
    public static void deleteSupplierPage() {
        clearConsole();

        System.out.println("+----------------------------------------------------------------------------------------------------------+\n" +
                "|                                             DELETE SUPPLIER                                              |\n" +
                "+----------------------------------------------------------------------------------------------------------+\n");

        boolean isValid = true;
        int index = -1;

        while(isValid){
            System.out.print("Supplier ID      : ");
            String supId = scanner.nextLine();

            for (int i = 0; i < suppliers[0].length; i++) {
                if (suppliers[0][i].equals(supId)) {
                    index = i;
                    isValid = false;
                }
            }
            if (index == -1) {
                System.out.println("can't find supplier id. try again! \n");
                isValid = true;
            }
        }
        String[] newIDs = new String[suppliers[0].length - 1];
        String[] newNames = new String[suppliers[1].length - 1];

        for (int i = 0; i < index; i++) {
            newIDs[i] = suppliers[0][i];
            newNames[i] = suppliers[1][i];
        }

        for (int i = index + 1; i < suppliers[0].length; i++) {
            newIDs[i - 1] = suppliers[0][i];
            newNames[i - 1] = suppliers[1][i];
        }

        suppliers[0] = newIDs;
        suppliers[1] = newNames;

        System.out.print("deleted successfully! Do you want to delete another (Y/N) ? ");
        char ans = scanner.next().charAt(0);
        scanner.nextLine();

        while (ans != 'Y' && ans != 'y' && ans != 'N' && ans != 'n') {
            System.out.println("Invalid input. Please enter 'Y' or 'N'.\n");
            System.out.print("Do you want to delete another (Y/N) ? ");
            ans = scanner.next().charAt(0);
            scanner.nextLine();
        }

        if (ans == 'Y' || ans == 'y') {
            deleteSupplierPage();
        } else {
            supplierManagePage();
        }
    }
    public static void viewSupplierPage() {
        clearConsole();

        System.out.println("+----------------------------------------------------------------------------------------------------------+\n" +
                "|                                               VIEW SUPPLIER                                              |\n" +
                "+----------------------------------------------------------------------------------------------------------+\n");
        System.out.println("+-------------------+------------------------+");
        System.out.println("|    Supplier ID    |      Supplier Name     |");
        System.out.println("+-------------------+------------------------+");
        for (int i = 0; i < suppliers[0].length; i++) {
            String name = suppliers[1][i];
            int nameLen = name.length();
            System.out.print("|       "+suppliers[0][i]+"        |        "+name);
            for (int j = 0; j < 16-nameLen; j++) {
                System.out.print(" ");
            }
            System.out.println("|");

        }
        System.out.println("+-------------------+------------------------+");

        System.out.print("\nDo you want to go back to the supplier management menu (Y/N)? ");
        char ans = scanner.next().charAt(0);
        scanner.nextLine();

        while (ans != 'Y' && ans != 'y' && ans != 'N' && ans != 'n') {
            System.out.println("Invalid input. Please enter 'Y' or 'N'.\n");
            System.out.print("Do you want to go back to the supplier management menu (Y/N) ? ");
            ans = scanner.next().charAt(0);
            scanner.nextLine();
        }

        if (ans == 'Y' || ans == 'y') {
            supplierManagePage();
        } else {
            viewSupplierPage();
        }
    }
    public static String tableCenter(int tableWidth, String str) {
        int strLen = str.length();
        int totalPadding = tableWidth - strLen;
        int leftPadding = totalPadding / 2;
        int rightPadding = totalPadding - leftPadding;

        String leftPad = "";
        for (int i = 0; i < leftPadding; i++) {
            leftPad += " ";
        }

        String rightPad = "";
        for (int i = 0; i < rightPadding; i++) {
            rightPad += " ";
        }

        return leftPad + str + rightPad;

    }
    public static void searchSupplierPage(){
        clearConsole();

        System.out.println("+----------------------------------------------------------------------------------------------------------+\n" +
                "|                                             SEARCH SUPPLIER                                              |\n" +
                "+----------------------------------------------------------------------------------------------------------+\n");

        boolean isValid = true;
        int index = -1;

        while(isValid){
            System.out.print("Supplier ID      : ");
            String supId = scanner.nextLine();

            for (int i = 0; i < suppliers[0].length; i++) {
                if (suppliers[0][i].equals(supId)) {
                    index = i;
                    isValid = false;
                }
            }
            if (index == -1) {
                System.out.println("can't find supplier id. try again! \n");
                isValid = true;
            }
        }
        System.out.println("Supplier Name    : "+ suppliers[1][index]);

        System.out.println("added successfully! Do you want to add another find (Y/N) ? ");
        char ans = scanner.next().charAt(0);
        scanner.nextLine();

        while (ans != 'Y' && ans != 'y' && ans != 'N' && ans != 'n') {
            System.out.println("Invalid input. Please enter 'Y' or 'N'.\n");
            System.out.print("Do you want to add another find (Y/N) ? ");
            ans = scanner.next().charAt(0);
            scanner.nextLine();
        }

        if (ans == 'Y' || ans == 'y') {
            searchSupplierPage();
        } else {
            supplierManagePage();
        }
    }
    public static void stockManagePage(){
        clearConsole();

        System.out.println("+----------------------------------------------------------------------------------------------------------+\n" +
                "|                                              STOCK MANAGEMENT                                            |\n" +
                "+----------------------------------------------------------------------------------------------------------+\n");
        System.out.println("[1] Manage Item Categories        \t\t [2] Add Item\n" +
                "[3] Get Item Supplier Wise        \t\t [4] View Item\n" +
                "[5] Rank Item Per Unit Price      \t\t [6] Home Page\n");

        int option = 0;
        boolean validInput = false;

        while (!validInput) {
            System.out.print("Enter an option to continue > ");
            String input = scanner.nextLine();

            try {
                option = Integer.parseInt(input);
                if (option >= 1 && option <= 6) {
                    validInput = true;
                } else {
                    System.out.println("Invalid option. Please enter a number between 1 and 6.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 6.");
            }
        }

        switch (option){
            case 1 -> manageItemCategory();
            case 2 -> addItem();
            case 3 -> itemSupplierWise();
            case 4 -> viewItem();
            case 5 -> rankItem();
            case 6 -> homePage();
        }
    }
    public static void manageItemCategory(){
        clearConsole();
        System.out.println("+----------------------------------------------------------------------------------------------------------+\n" +
                "|                                        MANAGE ITEM CATEGORY                                              |\n" +
                "+----------------------------------------------------------------------------------------------------------+\n");
        System.out.println("[1] Add New Item Category  \t\t [2] Delete Item Category\n" +
                "[3] Update Item Category   \t\t [4] Stock Management\n");

        int option = 0;
        boolean validInput = false;

        while (!validInput) {
            System.out.print("Enter an option to continue > ");
            String input = scanner.nextLine();

            try {
                option = Integer.parseInt(input);
                if (option >= 1 && option <=4 ) {
                    validInput = true;
                } else {
                    System.out.println("Invalid option. Please enter a number between 1 and 4.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 4.");
            }
        }

        switch (option){
            case 1 -> addCategory();
            case 2 -> deleteCategory();
            case 3 -> updateCategory();
            case 4 -> stockManagePage();
        }
    }
    public static void addCategory(){
        clearConsole();

        System.out.println("+----------------------------------------------------------------------------------------------------------+\n" +
                "|                                           ADD ITEM CATEGORY                                              |\n" +
                "+----------------------------------------------------------------------------------------------------------+\n");

        boolean isValid;
        String categoryName;

        do {
            isValid = true;
            System.out.print("Enter the new item category: ");
            categoryName = scanner.nextLine();

            for (String category : itemCategories) {
                if (category.equalsIgnoreCase(categoryName)) {
                    System.out.println("Category already exists.");
                    isValid = false;
                    break;
                }
            }
        } while (!isValid);

        int currentSize = itemCategories.length;
        String[] newCategory = new String[currentSize + 1];
        for (int i = 0; i < currentSize; i++) {
            newCategory[i] = itemCategories[i];
        }

        newCategory[currentSize] = categoryName;
        itemCategories = newCategory;

        System.out.print("\nadded successfully! Do you want to add another category (Y/N)? ");
        char ans = scanner.next().charAt(0);
        scanner.nextLine();

        while (ans != 'Y' && ans != 'y' && ans != 'N' && ans != 'n') {
            System.out.println("Invalid input. Please enter 'Y' or 'N'.\n");
            System.out.print("Do you want to add another category (Y/N) ? ");
            ans = scanner.next().charAt(0);
            scanner.nextLine();
        }

        if (ans == 'Y' || ans == 'y') {
            addCategory();
        } else {
            manageItemCategory();
        }
    }
    public static void updateCategory(){
        clearConsole();

        System.out.println("+----------------------------------------------------------------------------------------------------------+\n" +
                "|                                           UPDATE ITEM CATEGORY                                           |\n" +
                "+----------------------------------------------------------------------------------------------------------+\n");

        boolean isValid = true;
        int index = -1;

        while(isValid){
            System.out.print("Enter the current item category name: ");
            String category = scanner.nextLine();

            for (int i = 0; i < itemCategories.length; i++) {
                if (itemCategories[i].equals(category)) {
                    index = i;
                    isValid = false;
                }
            }
            if (index == -1) {
                System.out.println("Category not found. try again! \n");
                isValid = true;
            }
        }

        isValid = true;

        while (isValid) {
            System.out.print("\nEnter the new item category name: ");
            String updatedCategory = scanner.nextLine();

            boolean newNameExists = false;
            for (String category : itemCategories) {
                if (category.equalsIgnoreCase(updatedCategory)) {
                    System.out.println("New category name already exists. Try again!");
                    newNameExists = true;
                    break;
                }
            }

            if (!newNameExists) {
                itemCategories[index] = updatedCategory;
                System.out.println("Updated successfully!");

                System.out.print("Do you want to update another category (Y/N)? ");
                char ans = scanner.next().charAt(0);
                scanner.nextLine();

                while (ans != 'Y' && ans != 'y' && ans != 'N' && ans != 'n') {
                    System.out.println("Invalid input. Please enter 'Y' or 'N'.\n");
                    System.out.print("Do you want to update another category (Y/N) ? ");
                    ans = scanner.next().charAt(0);
                    scanner.nextLine();
                }

                if (ans == 'Y' || ans == 'y') {
                    updateCategory();
                } else {
                    manageItemCategory();
                }

                isValid = false;
            }
        }
    }
    public static void deleteCategory(){
        clearConsole();

        System.out.println("+----------------------------------------------------------------------------------------------------------+\n" +
                "|                                           DELETE ITEM CATEGORY                                           |\n" +
                "+----------------------------------------------------------------------------------------------------------+\n");

        boolean isValid = false;
        int indexToDelete = -1;

        while (!isValid) {
            System.out.print("Enter the item category to delete: ");
            String categoryName = scanner.nextLine();

            for (int i = 0; i < itemCategories.length; i++) {
                if (itemCategories[i].equalsIgnoreCase(categoryName)) {
                    indexToDelete = i;
                    isValid = true;
                    break;
                }
            }

            if (!isValid) {
                System.out.println("Category not found. Try again!\n");
            }
        }

        String[] newCategories = new String[itemCategories.length - 1];
        for (int i = 0, j = 0; i < itemCategories.length; i++) {
            if (i != indexToDelete) {
                newCategories[j++] = itemCategories[i];
            }
        }
        itemCategories = newCategories;

        System.out.print("\nCategory deleted successfully! Do you want to delete another category (Y/N) ? ");
        char ans = scanner.next().charAt(0);
        scanner.nextLine();

        while (ans != 'Y' && ans != 'y' && ans != 'N' && ans != 'n') {
            System.out.println("Invalid input. Please enter 'Y' or 'N'.\n");
            System.out.print("Do you want to delete another category (Y/N) ? ");
            ans = scanner.next().charAt(0);
            scanner.nextLine();
        }

        if (ans == 'Y' || ans == 'y') {
            deleteCategory();
        } else {
            manageItemCategory();
        }
    }
    public static void addItem(){
        clearConsole();
        System.out.println("+----------------------------------------------------------------------------------------------------------+\n" +
                "|                                                  ADD ITEM                                                |\n" +
                "+----------------------------------------------------------------------------------------------------------+\n");

        if(itemCategories.length==0){
            System.out.println("OPPS! It seems that you don't have any item categories in the system.\n");
            System.out.print("Do you want to add a new item category ? (Y/N) ");
            char ans = scanner.next().charAt(0);
            scanner.nextLine();

            while (ans != 'Y' && ans != 'y' && ans != 'N' && ans != 'n') {
                System.out.println("Invalid input. Please enter 'Y' or 'N'.\n");
                System.out.print("Do you want to add a new item category (Y/N) ? ");
                ans = scanner.next().charAt(0);
                scanner.nextLine();
            }

            if (ans == 'Y' || ans == 'y') {
                addCategory();
                return;
            } else {
                stockManagePage();
                return;
            }
        }
        if(suppliers[0].length==0){
            System.out.println("OPPS! It seems that you don't have any suppliers in the system.\n");
            System.out.print("Do you want to add a new supplier ? (Y/N) ");
            char ans = scanner.next().charAt(0);
            scanner.nextLine();

            while (ans != 'Y' && ans != 'y' && ans != 'N' && ans != 'n') {
                System.out.println("Invalid input. Please enter 'Y' or 'N'.\n");
                System.out.print("Do you want to add a new supplier (Y/N) ? ");
                ans = scanner.next().charAt(0);
                scanner.nextLine();
            }

            if (ans == 'Y' || ans == 'y') {
                addSupplierPage();
                return;
            } else {
                stockManagePage();
                return;
            }
        }
        System.out.print("Item Code   : ");
        String itemCode = scanner.nextLine();
        System.out.println("");

        System.out.println("Suppliers list: ");
        System.out.println("+--------------+-----------------------+----------------------------+");
        System.out.println("|" + tableCenter(14, "#") + "|"+tableCenter(23,"SUPPLIER ID")+"|"+tableCenter(28,"SUPPLIER NAME")+"|");
        System.out.println("+--------------+-----------------------+----------------------------+");
        for (int i = 0; i < suppliers[0].length; i++) {
            String name = suppliers[1][i];
            String code = (i+1)+"";
            String id = suppliers[0][i];
            System.out.println("|" + tableCenter(14, code) + "|"+tableCenter(23,id)+"|"+tableCenter(28,name)+"|");
        }
        System.out.println("+--------------+-----------------------+----------------------------+\n");

        System.out.print("Enter the supplier number > ");
        int supplierNum = Integer.parseInt(scanner.nextLine());
        System.out.println("");

        System.out.println("Item Categories:");
        System.out.println("+--------------+----------------------------+");
        System.out.println("|" + tableCenter(14, "#") +"|"+tableCenter(28,"CATEGORY NAME")+"|");
        System.out.println("+--------------+----------------------------+");
        for (int i = 0; i < itemCategories.length; i++) {
            String name = itemCategories[i];
            String code = (i+1)+"";
            System.out.println("|" + tableCenter(14, code) +"|"+tableCenter(28,name)+"|");
        }
        System.out.println("+--------------+----------------------------+\n");

        System.out.print("Enter the category number > ");
        int categoryNum = Integer.parseInt(scanner.nextLine());
        System.out.println("");

        System.out.print("Description : ");
        String description = scanner.nextLine();

        System.out.print("Unit Price : ");
        String unitPrice = scanner.nextLine();

        System.out.print("Qty on hand : ");
        String qtyOnHand = scanner.nextLine();

        int currentSize = items[0].length;

        String[] newSupplierID = new String[currentSize + 1];
        String[] newItemID = new String[currentSize + 1];
        String[] newDescription = new String[currentSize + 1];
        String[] newUnitPrice = new String[currentSize + 1];
        String[] newQtyOnHand = new String[currentSize + 1];
        String[] newCategory = new String[currentSize + 1];

        for (int i = 0; i < currentSize; i++) {
            newSupplierID[i] = items[0][i];
            newItemID[i] = items[1][i];
            newDescription[i] = items[2][i];
            newUnitPrice[i] = items[3][i];
            newQtyOnHand[i] = items[4][i];
            newCategory[i] = items[5][i];
        }

        newSupplierID[currentSize] = suppliers[0][supplierNum-1];
        newItemID[currentSize] = itemCode;
        newDescription[currentSize] = description;
        newUnitPrice[currentSize] = unitPrice;
        newQtyOnHand[currentSize] = qtyOnHand;
        newCategory[currentSize] = itemCategories[categoryNum-1];

        items[0] = newSupplierID;
        items[1] = newItemID;
        items[2] = newDescription;
        items[3] = newUnitPrice;
        items[4] = newQtyOnHand;
        items[5] = newCategory;

        System.out.print("added successfully! Do you want to add another Item (Y/N) ? ");
        char ans = scanner.next().charAt(0);
        scanner.nextLine();

        while (ans != 'Y' && ans != 'y' && ans != 'N' && ans != 'n') {
            System.out.println("Invalid input. Please enter 'Y' or 'N'.\n");
            System.out.print("Do you want to add another Item (Y/N) ? ");
            ans = scanner.next().charAt(0);
            scanner.nextLine();
        }

        if (ans == 'Y' || ans == 'y') {
            addItem();
        } else {
            stockManagePage();
        }
    }
    public static void itemSupplierWise(){
        clearConsole();
        System.out.println("+----------------------------------------------------------------------------------------------------------+\n" +
                "|                                               SEARCH SUPPLIER                                            |\n" +
                "+----------------------------------------------------------------------------------------------------------+\n");

        boolean isValid = true;
        int index = -1;

        while(isValid){
            System.out.print("Enter Supplier ID      : ");
            String supId = scanner.nextLine();

            for (int i = 0; i < suppliers[0].length; i++) {
                if (suppliers[0][i].equals(supId)) {
                    index = i;
                    isValid = false;
                    break;
                }
            }
            if (index == -1) {
                System.out.println("can't find supplier id. try again! \n");
                isValid = true;
            }
        }
        System.out.println("Supplier Name    : "+ suppliers[1][index]);
        System.out.println("\n");

        System.out.println("+---------------+--------------------+----------------+-----------------+----------------------+");
        System.out.println("|" + tableCenter(15, "ITEM CODE") + "|"+tableCenter(20,"DESCRIPTION")+"|"+tableCenter(16,"UNIT PRICE")+"|"+tableCenter(17,"QTY ON HAND")+"|"+tableCenter(22,"CATEGORY")+"|");
        System.out.println("+---------------+--------------------+----------------+-----------------+----------------------+");

        for (int i = 0; i < items[0].length; i++) {
            if(items[0][i].equals(suppliers[0][index])){
                System.out.println("|" + tableCenter(15, items[1][i]) + "|"+tableCenter(20,items[2][i])+"|"+tableCenter(16,items[3][i])+"|"+tableCenter(17,items[4][i])+"|"+tableCenter(22,items[5][i])+"|");
            }
        }
        System.out.println("+---------------+--------------------+----------------+-----------------+----------------------+\n");

        System.out.print("search successfully! Do you want to another search (Y/N) ? ");
        char ans = scanner.next().charAt(0);
        scanner.nextLine();

        while (ans != 'Y' && ans != 'y' && ans != 'N' && ans != 'n') {
            System.out.println("Invalid input. Please enter 'Y' or 'N'.\n");
            System.out.print("Do you want to another search (Y/N) ? ");
            ans = scanner.next().charAt(0);
            scanner.nextLine();
        }

        if (ans == 'Y' || ans == 'y') {
            itemSupplierWise();
        } else {
            stockManagePage();
        }
    }
    public static void viewItem(){
        clearConsole();
        System.out.println("+----------------------------------------------------------------------------------------------------------+\n" +
                "|                                                  VIEW ITEMS                                              |\n" +
                "+----------------------------------------------------------------------------------------------------------+\n");

        for (int i = 0; i < itemCategories.length; i++) {
            System.out.println(itemCategories[i]+":");
            System.out.println("+---------------+--------------------+---------------------+-----------------+-----------------+");
            System.out.println("|" + tableCenter(15, "SID") + "|"+tableCenter(20,"CODE")+"|"+tableCenter(21,"DESC")+"|"+tableCenter(17,"PRICE")+"|"+tableCenter(17,"QTY")+"|");
            System.out.println("+---------------+--------------------+---------------------+-----------------+-----------------+");

            for (int j = 0; j < items[0].length; j++) {
                if(items[5][j].equals(itemCategories[i])){
                    System.out.println("|" + tableCenter(15, items[0][j]) + "|"+tableCenter(20, items[1][j])+"|"+tableCenter(21,items[2][j])+"|"+tableCenter(17,items[3][j])+"|"+tableCenter(17,items[4][j])+"|");
                }
            }
            System.out.println("+---------------+--------------------+---------------------+-----------------+-----------------+\n");
        }
        System.out.print("Do you want to go to Stock Management Page (Y/N) ? ");
        char ans = scanner.next().charAt(0);
        scanner.nextLine();

        while (ans != 'Y' && ans != 'y' && ans != 'N' && ans != 'n') {
            System.out.println("Invalid input. Please enter 'Y' or 'N'.\n");
            System.out.print("Do you want to go to Stock Management Page (Y/N) ? ");
            ans = scanner.next().charAt(0);
            scanner.nextLine();
        }

        if (ans == 'Y' || ans == 'y') {
            stockManagePage();
        } else {
            viewItem();
        }
    }
    public static void rankItem() {
        clearConsole();
        System.out.println("+----------------------------------------------------------------------------------------------------------+\n" +
                "|                                            RANKED UNIT PRICE                                             |\n" +
                "+----------------------------------------------------------------------------------------------------------+\n");


        String[][] sortedItems = new String[items.length][items[0].length];
        for (int i = 0; i < items.length; i++) {
            for (int j = 0; j < items[i].length; j++) {
                sortedItems[i][j] = items[i][j];
            }
        }

        int numRows = sortedItems[0].length;

        for (int i = 0; i < numRows - 1; i++) {
            for (int j = 0; j < numRows - 1 - i; j++) {
                int unitPrice1 = Integer.parseInt(sortedItems[3][j]);
                int unitPrice2 = Integer.parseInt(sortedItems[3][j + 1]);

                if (unitPrice1 > unitPrice2) {
                    for (int k = 0; k < sortedItems.length; k++) {
                        String temp = sortedItems[k][j];
                        sortedItems[k][j] = sortedItems[k][j + 1];
                        sortedItems[k][j + 1] = temp;
                    }
                }
            }
        }

        System.out.println("+---------------+---------------+------------------+-----------------+-----------------+-------------------+");
        System.out.println("|" + tableCenter(15, "SID") + "|"+tableCenter(15,"CODE")+"|"+tableCenter(18,"DESC")+"|"+tableCenter(17,"PRICE")+"|"+tableCenter(17,"QTY")+"|"+tableCenter(19,"CAT")+"|");
        System.out.println("+---------------+---------------+------------------+-----------------+-----------------+-------------------+");

        for (int j = 0; j < sortedItems[0].length; j++) {
            System.out.println("|" + tableCenter(15, sortedItems[0][j]) + "|"+tableCenter(15, sortedItems[1][j])+"|"+tableCenter(18,sortedItems[2][j])+"|"+tableCenter(17,sortedItems[3][j])+"|"+tableCenter(17,sortedItems[4][j])+"|"+tableCenter(19,sortedItems[5][j])+"|");
        }
        System.out.println("+---------------+---------------+------------------+-----------------+-----------------+-------------------+\n");

        System.out.print("Do you want to go back (Y/N) ? ");
        char ans = scanner.next().charAt(0);
        scanner.nextLine();

        while (ans != 'Y' && ans != 'y' && ans != 'N' && ans != 'n') {
            System.out.println("Invalid input. Please enter 'Y' or 'N'.\n");
            System.out.print("Do you want to go back (Y/N) ? ");
            ans = scanner.next().charAt(0);
            scanner.nextLine();
        }

        if (ans == 'Y' || ans == 'y') {
            stockManagePage();
        } else {
            rankItem();
        }
    }

    public static void main(String[] args){
        loginPage();

    }
}
