import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import static Util.Validation.*;

public class Main {
    private static Integer pageNumber = 1;
    private static Integer rowSize = 4;
    public static void main(String[] args) {
        // Scanner object
        Scanner scanner = new Scanner(System.in);

        List<Product> productList = new ArrayList<>();
        // Static Data
        productList.add(new Product(1, "Iphone 15 Pro Max", 1000.00, 10, LocalDate.now()));
        productList.add(new Product(2, "Sumsung Galaxy Note 10+", 500.00, 20, LocalDate.now()));
        productList.add(new Product(3, "ASUS ROG R15", 1500.00, 30, LocalDate.now()));
        productList.add(new Product(4, "Lambo", 100000.00, 40, LocalDate.now()));
        productList.add(new Product(5, "Honda Dream", 2500.00, 40, LocalDate.now()));

        System.out.println("""
                 
                 ██████ ███████ ████████  █████  ██████           ██  █████  ██    ██  █████     \s
                ██      ██         ██    ██   ██ ██   ██          ██ ██   ██ ██    ██ ██   ██    \s
                ██      ███████    ██    ███████ ██   ██          ██ ███████ ██    ██ ███████    \s
                ██           ██    ██    ██   ██ ██   ██     ██   ██ ██   ██  ██  ██  ██   ██    \s
                 ██████ ███████    ██    ██   ██ ██████       █████  ██   ██   ████   ██   ██    \s
                                                                                                 \s
                                                                                                 \s
                """);

        System.out.println("STOCK MANAGEMENT SYSTEM");
        outer:
        while (true) {
            int totalPage = (int) Math.ceil((double) productList.size() / rowSize);
            Table table = getTable();
            System.out.println(table.render());
            System.out.print("Command --> ");
            String option = scanner.nextLine().toLowerCase();
            switch (option){

                case "*", "display" -> {
                    displayTable(productList, pageNumber, rowSize);
                }

                //Start Case Write Product Data to Memory
                case "W", "w", "Write", "write" -> {
                    String name, price, qty;
                    System.out.print("Product ID : ");
                    System.out.println(productList.size() + 1);

                    do {
                        System.out.print("Product Name : ");
                        name = scanner.nextLine();
                    }while(name.isBlank());

                    do {
                        System.out.print("Product Price : ");
                        price = scanner.nextLine();
                    }while(!isDouble(price));

                    do{
                        System.out.print("Product Quantity : ");
                        qty = scanner.nextLine();
                    }while(!isInteger(qty));

                    Table productTable = new Table(1, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.SURROUND);
                    productTable.addCell(" ID" + " ".repeat(14) + " : " + (productList.size() + 1));
                    productTable.addCell(" Name" + " ".repeat(12) + " : " + name);
                    productTable.addCell(" Unit Price" + " ".repeat(6) + " : " + Double.parseDouble(price));
                    productTable.addCell(" Qty" + " ".repeat(13) + " : " + qty);
                    productTable.addCell(" Imported Date" + " ".repeat(3) + " : " + LocalDate.now() + " ".repeat(6));

                    System.out.println();
                    System.out.println(productTable.render());
                    System.out.println();
                    while (true){
                        System.out.print("Are you sure want to add this record? [Y/y] or [N/n]: ");
                        String choice = scanner.nextLine().toLowerCase();
                        if (choice.equals("n")){
                            break;
                        }
                        else if (choice.equals("y")) {
                            productList.add(new Product(productList.size() + 1, name, Double.parseDouble(price), Integer.parseInt(qty), LocalDate.now()));
                            System.out.println();
                            Table table2 = new Table(1, BorderStyle.DESIGN_CAFE_WIDE, ShownBorders.SURROUND);
                            table2.addCell("  "+ (productList.size()) + " was added successfully!  ");
                            System.out.println(table2.render());
                            System.out.println();
                            break;
                        }
                        else System.out.println("Invalid Input");
                    }
                }

                //Start Case Read
                case "R", "r", "Read", "read" -> {
                    String readById;
                    do {
                        System.out.print("Read by ID: ");
                        readById = scanner.nextLine();
                    } while (!isInteger(readById));
                    for(Product p: productList) {
                        if(readById.equalsIgnoreCase(p.getProductCode().toString())) {
                            Table table1 = new Table(1, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.SURROUND);
                            table1.addCell(" ID" + " ".repeat(14) + " : " + p.getProductCode());
                            table1.addCell(" Name" + " ".repeat(12) + " : " + p.getName());
                            table1.addCell(" Unit Price" + " ".repeat(6) + " : " + p.getPrice());
                            table1.addCell(" Qty" + " ".repeat(13) + " : " + p.getQty());
                            table1.addCell(" Imported Date" + " ".repeat(3) + " : " + p.getDate() + " ".repeat(6));
                            System.out.println();
                            System.out.println(table1.render());
                            System.out.println();
                            continue outer;
                        }
                    }
                    Table table1 = new Table(1, BorderStyle.DESIGN_CAFE_WIDE, ShownBorders.SURROUND);
                    System.out.println();
                    table1.addCell("     ID: " + readById + " is not found!     ");
                    System.out.println(table1.render());
                }

                //Start Case Update
                case "U", "u", "Update", "update" -> {
                    String inputID;
                    do {
                        System.out.print("Please Input ID of Product : ");
                        inputID = scanner.nextLine();
                    } while(!isInteger(inputID));

                    for(Product p: productList) {
                        if(inputID.equalsIgnoreCase(p.getProductCode().toString())) {
                            Table table1 = new Table(1, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.SURROUND);
                            table1.addCell(" ID" + " ".repeat(14) + " : " + p.getProductCode());
                            table1.addCell(" Name" + " ".repeat(12) + " : " + p.getName());
                            table1.addCell(" Unit Price" + " ".repeat(6) + " : " + p.getPrice());
                            table1.addCell(" Qty" + " ".repeat(13) + " : " + p.getQty());
                            table1.addCell(" Imported Date" + " ".repeat(3) + " : " + p.getDate() + " ".repeat(6));
                            System.out.println();
                            System.out.println(table1.render());
                            System.out.println();
                            while(true) {
                                Table table2 = new Table(1, BorderStyle.UNICODE_BOX, ShownBorders.SURROUND);
                                System.out.println("What do you want to update?");
                                table2.addCell(" ".repeat(4) + "1. All" + " ".repeat(4) + "2. Name" + " ".repeat(4) + "3. Quantity" + " ".repeat(4) + "4. Unit Price" + " ".repeat(4) + "5. Back to Menu" + " ".repeat(4));
                                System.out.println(table2.render());
                                System.out.print("Option (1 - 5) : ");
                                int optionUpdate = Integer.parseInt(scanner.nextLine());

                                switch (optionUpdate) {
                                    case 1 -> {
                                        String newName, newQuantity, newPrice;
                                        do {
                                            System.out.print("Product's name : ");
                                            newName = scanner.nextLine();
                                        } while (newName.isBlank());

                                        do {
                                            System.out.print("Product's Price : ");
                                            newPrice = scanner.nextLine();
                                        } while (!isDouble(newPrice));

                                        do {
                                            System.out.print("Product's Qty : ");
                                            newQuantity = scanner.nextLine();
                                        } while (!isInteger(newQuantity));
                                        Table table3 = new Table(1, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.SURROUND);
                                        table3.addCell(" ID" + " ".repeat(14) + " : " + p.getProductCode());
                                        table3.addCell(" Name" + " ".repeat(12) + " : " + newName);
                                        table3.addCell(" Unit Price" + " ".repeat(6) + " : " + Double.parseDouble(newPrice));
                                        table3.addCell(" Qty" + " ".repeat(13) + " : " + Integer.parseInt(newQuantity));
                                        table3.addCell(" Imported Date" + " ".repeat(3) + " : " + LocalDate.now() + " ".repeat(6));

                                        System.out.println();
                                        System.out.println(table3.render());
                                        System.out.println();

                                        while (true){
                                            System.out.print("Are you sure want to update this record? [Y/y] or [N/n]: ");
                                            String choice = scanner.nextLine().toLowerCase();
                                            if (choice.equals("n")){
                                                break;
                                            }
                                            else if (choice.equals("y")) {
                                                p.setName(newName);
                                                p.setPrice(Double.parseDouble(newPrice));
                                                p.setQty(Integer.parseInt(newQuantity));
                                                System.out.println();
                                                Table table4 = new Table(1, BorderStyle.DESIGN_CAFE_WIDE, ShownBorders.SURROUND);
                                                table4.addCell(" ".repeat(5)+ "Product was updated." + " ".repeat(5));
                                                System.out.println(table4.render());
                                                System.out.println();
                                                break;
                                            }
                                            else System.out.println("Invalid Input");
                                        }
                                    }
  
                                    case 2 -> {
                                        String newName;
                                        do {
                                            System.out.print("Product's name : ");
                                            newName = scanner.nextLine();
                                        } while (newName.isBlank());
                                        Table table3 = new Table(1, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.SURROUND);
                                        table3.addCell(" ID" + " ".repeat(14) + " : " + p.getProductCode());
                                        table3.addCell(" Name" + " ".repeat(12) + " : " + newName);
                                        table3.addCell(" Unit Price" + " ".repeat(6) + " : " + p.getPrice());
                                        table3.addCell(" Qty" + " ".repeat(13) + " : " + p.getQty());
                                        table3.addCell(" Imported Date" + " ".repeat(3) + " : " + LocalDate.now() + " ".repeat(6));

                                        System.out.println();
                                        System.out.println(table3.render());
                                        System.out.println();

                                        while (true){
                                            System.out.print("Are you sure want to update this record? [Y/y] or [N/n]: ");
                                            String choice = scanner.nextLine().toLowerCase();
                                            if (choice.equals("n")){
                                                break;
                                            }
                                            else if (choice.equals("y")) {
                                                p.setName(newName);
                                                System.out.println();
                                                Table table4 = new Table(1, BorderStyle.DESIGN_CAFE_WIDE, ShownBorders.SURROUND);
                                                table4.addCell(" ".repeat(5)+ "Product was updated." + " ".repeat(5));
                                                System.out.println(table4.render());
                                                System.out.println();
                                                break;
                                            }
                                            else System.out.println("Invalid Input");
                                        }
                                    }

                                    case 3 -> {
                                        String newQty;
                                        do {
                                            System.out.print("Product's Qty : ");
                                            newQty = scanner.nextLine();
                                        } while (!isInteger(newQty));
                                        Table table3 = new Table(1, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.SURROUND);
                                        table3.addCell(" ID" + " ".repeat(14) + " : " + p.getProductCode());
                                        table3.addCell(" Name" + " ".repeat(12) + " : " + p.getName());
                                        table3.addCell(" Unit Price" + " ".repeat(6) + " : " + p.getPrice());
                                        table3.addCell(" Qty" + " ".repeat(13) + " : " + newQty);
                                        table3.addCell(" Imported Date" + " ".repeat(3) + " : " + LocalDate.now() + " ".repeat(6));

                                        System.out.println();
                                        System.out.println(table3.render());
                                        System.out.println();

                                        while (true){
                                            System.out.print("Are you sure want to update this record? [Y/y] or [N/n]: ");
                                            String choice = scanner.nextLine().toLowerCase();
                                            if (choice.equals("n")){
                                                break;
                                            }
                                            else if (choice.equals("y")) {
                                                p.setQty(Integer.parseInt(newQty));
                                                System.out.println();
                                                Table table4 = new Table(1, BorderStyle.DESIGN_CAFE_WIDE, ShownBorders.SURROUND);
                                                table4.addCell(" ".repeat(5)+ "Product was updated." + " ".repeat(5));
                                                System.out.println(table4.render());
                                                System.out.println();
                                                break;
                                            }
                                            else System.out.println("Invalid Input");
                                        }
                                    }

                                    case 4 -> {
                                        String newPrice;
                                        do {
                                            System.out.print("Product's Qty : ");
                                            newPrice = scanner.nextLine();
                                        } while (!isDouble(newPrice));
                                        Table table3 = new Table(1, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.SURROUND);
                                        table3.addCell(" ID" + " ".repeat(14) + " : " + p.getProductCode());
                                        table3.addCell(" Name" + " ".repeat(12) + " : " + p.getName());
                                        table3.addCell(" Unit Price" + " ".repeat(6) + " : " + Double.parseDouble(newPrice));
                                        table3.addCell(" Qty" + " ".repeat(13) + " : " + p.getQty());
                                        table3.addCell(" Imported Date" + " ".repeat(3) + " : " + LocalDate.now() + " ".repeat(6));

                                        System.out.println();
                                        System.out.println(table3.render());
                                        System.out.println();

                                        while (true){
                                            System.out.print("Are you sure want to update this record? [Y/y] or [N/n]: ");
                                            String choice = scanner.nextLine().toLowerCase();
                                            if (choice.equals("n")){
                                                break;
                                            }
                                            else if (choice.equals("y")) {
                                                p.setPrice(Double.parseDouble(newPrice));
                                                System.out.println();
                                                Table table4 = new Table(1, BorderStyle.DESIGN_CAFE_WIDE, ShownBorders.SURROUND);
                                                table4.addCell(" ".repeat(5)+ "Product was updated." + " ".repeat(5));
                                                System.out.println(table4.render());
                                                System.out.println();
                                                break;
                                            }
                                            else System.out.println("Invalid Input");
                                        }
                                    }

                                    case 5 -> {
                                        continue outer;
                                    }
                                    default -> System.out.println("Invalid Input!");
                                }
                            }
                        }
                    }
                    Table table1 = new Table(1, BorderStyle.DESIGN_CAFE_WIDE, ShownBorders.SURROUND);
                    System.out.println();
                    table1.addCell("     ID: " + inputID + " is not found!     ");
                    System.out.println(table1.render());
                }

                //Start Case Delete
                case "D", "d", "Delete", "delete" -> {
                    String inputID;
                    do {
                        System.out.print("Please Input ID of Product : ");
                        inputID = scanner.nextLine();
                    } while(!isInteger(inputID));
                    for(Product p: productList) {
                        if (inputID.equals(p.getProductCode().toString())) {
                            Table table1 = new Table(1, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.SURROUND);
                            table1.addCell(" ID" + " ".repeat(14) + " : " + p.getProductCode());
                            table1.addCell(" Name" + " ".repeat(12) + " : " + p.getName());
                            table1.addCell(" Unit Price" + " ".repeat(6) + " : " + p.getPrice());
                            table1.addCell(" Qty" + " ".repeat(13) + " : " + p.getQty());
                            table1.addCell(" Imported Date" + " ".repeat(3) + " : " + p.getDate() + " ".repeat(6));
                            System.out.println();
                            System.out.println(table1.render());
                            System.out.println();

                            System.out.print("Are you sure want to delete this record? [Y/y] or [N/n]: ");
                            String choice = scanner.nextLine().toLowerCase();
                            if (choice.equals("n")){
                                break;
                            }
                            else if (choice.equals("y")) {
                                productList.remove(p);
                                System.out.println();
                                Table table4 = new Table(1, BorderStyle.DESIGN_CAFE_WIDE, ShownBorders.SURROUND);
                                table4.addCell(" ".repeat(5)+ "Product was removed" + " ".repeat(5));
                                System.out.println(table4.render());
                                System.out.println();
                                break;
                            }
                            else System.out.println("Invalid Input");
                        }
                    }
                }

                //First Page Case
                case "F", "f", "First", "first" -> {
                    if (pageNumber >= 1) {
                        pageNumber = 1;
                    }
                    displayTable(productList, pageNumber, rowSize);
                }

                //Previous Page Case
                case "P", "p", "Previous", "previous" -> {
                    if (pageNumber > 1) {
                        pageNumber--;
                    } else pageNumber = totalPage;
                    displayTable(productList, pageNumber, rowSize);
                }

                //Next Page Case
                case "N", "n", "Next", "next" -> {
                    if (pageNumber < totalPage) {
                        pageNumber++;
                    } else pageNumber = 1;
                    displayTable(productList, pageNumber, rowSize);
                }

                //Last Page Case
                case "L", "l", "Last", "last" -> {
                    if (pageNumber < totalPage) {
                        pageNumber = totalPage;
                    }
                    displayTable(productList, pageNumber, rowSize);
                }

                //Search Case
                case "s", "search" -> {
                    String searchName;
                    do {
                        System.out.print("Search by Name : ");
                        searchName = scanner.nextLine();
                    } while (searchName.isBlank());

                    CellStyle cellStyle = new CellStyle(CellStyle.HorizontalAlign.center);
                    Table table1 = new Table(5, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.ALL);
                    table1.setColumnWidth(0,20, 30);
                    table1.setColumnWidth(1,20, 30);
                    table1.setColumnWidth(2,20, 30);
                    table1.setColumnWidth(3,20, 30);
                    table1.setColumnWidth(4,20, 30);

                    table1.addCell("ID", cellStyle);
                    table1.addCell("NAME", cellStyle);
                    table1.addCell("Unit Price", cellStyle);
                    table1.addCell("Qty", cellStyle);
                    table1.addCell("Imported Date", cellStyle);
                    int found = 0;
                    for(Product p: productList) {
                        if(p.getName().equalsIgnoreCase(searchName) || p.getName().contains(searchName)) {
                            table1.addCell(p.getProductCode().toString(), cellStyle);
                            table1.addCell(p.getName());
                            table1.addCell(p.getPrice().toString(), cellStyle);
                            table1.addCell(p.getQty().toString(), cellStyle);
                            table1.addCell(p.getDate().toString(), cellStyle);
                            found++;
                        }
                    }
                    if (found == 0) {
                        Table table2 = new Table(1, BorderStyle.DESIGN_CAFE_WIDE, ShownBorders.SURROUND);
                        System.out.println();
                        table2.addCell(" ".repeat(5) + "Product for [" + searchName + "] is not found!" + " ".repeat(5));
                        System.out.println(table2.render());
                    }
                    if(found == 1) {
                        System.out.println("Product found for [" + searchName + "] : " + found);
                        System.out.println(table1.render());
                        Table table2 = new Table(1, BorderStyle.DESIGN_CURTAIN, ShownBorders.SURROUND);
                        table2.addCell( "Page : " + pageNumber + " of " + totalPage + " ".repeat(74) + "Total record: "+ found);
                        System.out.println(table2.render());
                    }
                    if(found > 1) {
                        System.out.println("Product found for [" + searchName + "] : " + found);
                        System.out.println(table1.render());
                        Table table2 = new Table(1, BorderStyle.DESIGN_CURTAIN, ShownBorders.SURROUND);
                        table2.addCell( "Page : " + pageNumber + " of " + totalPage + " ".repeat(74) + "Total record: "+ found);
                        System.out.println(table2.render());
                    }
                }

                //Goto Case
                case "G", "g", "Goto", "goto" -> {
                    String goTo;
                    do{
                        System.out.print("Go to Page: ");
                        goTo = scanner.nextLine();
                    }while (!isInteger(goTo));
                    if (Integer.parseInt(goTo) > 0 && Integer.parseInt(goTo) <= totalPage){
                        pageNumber = Integer.parseInt(goTo);
                    }else {
                        return;
                    }
                    displayTable(productList, pageNumber, rowSize);
                }

                //Setrow Case
                case "Se", "se", "Setrow", "setrow" -> {
                    Table table1 = new Table(1, BorderStyle.DESIGN_CAFE_WIDE, ShownBorders.SURROUND);
                    String setRow;
                    do {
                        System.out.print("Please enter Row for Display : ");
                        setRow = scanner.nextLine();
                    } while(!isInteger(setRow));
                    rowSize = Integer.parseInt(setRow);
                    System.out.println();
                    table1.addCell("Set row to " + setRow + " Successfully.  ");
                    System.out.println(table1.render());
                    System.out.println();
                }

                //Help Case
                case "h", "help" -> {
                    helpMenu();
                }

                //Exit Case
                case "e", "exit" -> {
                    System.exit(0);
                }

                //When user input the invalid option, the code will be executed the block default
                default -> {
                    String[] shortCutOption = option.split("[#-]");
                    switch (shortCutOption[0]){
                        case "W", "w", "Write", "write" -> {
                            Table productAddByShortCut = new Table(1, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.SURROUND);
                            productAddByShortCut.addCell(" ID" + " ".repeat(14) + " : " + (productList.size() + 1));
                            productAddByShortCut.addCell(" Name" + " ".repeat(12) + " : " + shortCutOption[1]);
                            productAddByShortCut.addCell(" Unit Price" + " ".repeat(6) + " : " + Double.parseDouble(shortCutOption[2]));
                            productAddByShortCut.addCell(" Quantity" + " ".repeat(13) + " : " + shortCutOption[3]);
                            productAddByShortCut.addCell(" Imported Date" + " ".repeat(3) + " : " + LocalDate.now() + " ".repeat(6));

                            System.out.println();
                            System.out.println(productAddByShortCut.render());
                            System.out.println();
                            while (true) {
                                System.out.print("Are you sure want to add this record? [Y/y] or [N/n]: ");
                                String choice = scanner.nextLine().toLowerCase();
                                if (choice.equals("n")) {
                                    break;
                                } else if (choice.equals("y")) {
                                    productList.add(new Product(productList.size() + 1, shortCutOption[1], Double.parseDouble(shortCutOption[2]), Integer.parseInt(shortCutOption[3]), LocalDate.now()));
                                    System.out.println();
                                    Table table2 = new Table(1, BorderStyle.DESIGN_CAFE_WIDE, ShownBorders.SURROUND);
                                    table2.addCell("  " + (productList.size()) + " was added successfully  ");
                                    System.out.println(table2.render());
                                    System.out.println();
                                    break;
                                } else {
                                    System.out.println("Invalid Input");
                                }
                            }

                        }
                        case "R", "r", "Read", "read" -> {
                            for(Product p: productList) {
                                if(shortCutOption[1].equalsIgnoreCase(p.getProductCode().toString())) {
                                    Table tableReadByShortCutKey = new Table(1, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.SURROUND);
                                    tableReadByShortCutKey.addCell(" ID" + " ".repeat(14) + " : " + p.getProductCode());
                                    tableReadByShortCutKey.addCell(" Name" + " ".repeat(12) + " : " + p.getName());
                                    tableReadByShortCutKey.addCell(" Unit Price" + " ".repeat(6) + " : " + p.getPrice());
                                    tableReadByShortCutKey.addCell(" Qty" + " ".repeat(13) + " : " + p.getQty());
                                    tableReadByShortCutKey.addCell(" Imported Date" + " ".repeat(3) + " : " + p.getDate() + " ".repeat(6));
                                    System.out.println();
                                    System.out.println(tableReadByShortCutKey.render());
                                    System.out.println();
                                }
                            }
                        }
                        case "D", "d", "Delete", "delete" -> {
                            for(Product p: productList) {
                                if (shortCutOption[1].equals(p.getProductCode().toString())) {
                                    Table tableDeleteByShortCutKey = new Table(1, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.SURROUND);
                                    tableDeleteByShortCutKey.addCell(" ID" + " ".repeat(14) + " : " + p.getProductCode());
                                    tableDeleteByShortCutKey.addCell(" Name" + " ".repeat(12) + " : " + p.getName());
                                    tableDeleteByShortCutKey.addCell(" Unit Price" + " ".repeat(6) + " : " + p.getPrice());
                                    tableDeleteByShortCutKey.addCell(" Qty" + " ".repeat(13) + " : " + p.getQty());
                                    tableDeleteByShortCutKey.addCell(" Imported Date" + " ".repeat(3) + " : " + p.getDate() + " ".repeat(6));
                                    System.out.println();
                                    System.out.println(tableDeleteByShortCutKey.render());
                                    System.out.println();

                                    System.out.print("Are you sure want to delete this record? [Y/y] or [N/n]: ");
                                    String choice = scanner.nextLine().toLowerCase();
                                    if (choice.equals("n")){
                                        break;
                                    }
                                    else if (choice.equals("y")) {
                                        productList.remove(p);
                                        System.out.println();
                                        Table tableRemove = new Table(1, BorderStyle.DESIGN_CAFE_WIDE,ShownBorders.SURROUND);
                                        tableRemove.addCell(" ".repeat(5)+ "Product was removed" + " ".repeat(5));
                                        System.out.println(tableRemove.render());
                                        System.out.println();
                                        break;
                                    }
                                }
                            }
                        }
                        default -> {
                            System.out.println("~".repeat(20));
                            System.out.println("\tInvalid Input");
                            System.out.println("~".repeat(20));
                        }
                    }
                }

            }
        }
    }

        public static Table getTable() {
            Table table = new Table(9, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.SURROUND);
            table.addCell(" ".repeat(4) + "*)Display");
            table.addCell(" ".repeat(4) + " | W)rite");
            table.addCell(" ".repeat(4) + " | R)ead");
            table.addCell(" ".repeat(4) + " | U)pdate");
            table.addCell(" ".repeat(4) + " | D)elete");
            table.addCell(" ".repeat(4) + " | F)irst");
            table.addCell(" ".repeat(4) + " | P)revious");
            table.addCell(" ".repeat(4) + " | N)ext");
            table.addCell(" ".repeat(4) + " | L)ast" + " ".repeat(4));
            table.addCell(" ".repeat(4) + "S)earch");
            table.addCell(" ".repeat(4) + " | G)oto");
            table.addCell(" ".repeat(4) + " | Se)t row");
            table.addCell(" ".repeat(4) + " | H)elp");
            table.addCell(" ".repeat(4) + " | E)xit");
            return table;
        }

        public static void displayTable(List<Product> productList, int pageNumber, int rowSize) {
            int totalPage = (int) Math.ceil((double) productList.size() / rowSize);
            CellStyle cellStyle = new CellStyle(CellStyle.HorizontalAlign.center);
            Table table1 = new Table(5, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.ALL);
            table1.setColumnWidth(0,20, 30);
            table1.setColumnWidth(1,20, 30);
            table1.setColumnWidth(2,20, 30);
            table1.setColumnWidth(3,20, 30);
            table1.setColumnWidth(4,20, 30);

            table1.addCell("Product ID", cellStyle);
            table1.addCell("Name", cellStyle);
            table1.addCell("Unit Price", cellStyle);
            table1.addCell("Quantity", cellStyle);
            table1.addCell("Imported Date", cellStyle);
            for (int i = (pageNumber - 1) * rowSize; i < (pageNumber - 1) * rowSize + rowSize && i < productList.size(); i++) {
                Product product = productList.get(i);
                table1.addCell(product.getProductCode().toString(), cellStyle);
                table1.addCell(product.getName());
                table1.addCell(product.getPrice().toString(), cellStyle);
                table1.addCell(product.getQty().toString(), cellStyle);
                table1.addCell(product.getDate().toString(), cellStyle);
            }
            System.out.println(table1.render());
            Table table2 = new Table(1, BorderStyle.DESIGN_CURTAIN, ShownBorders.SURROUND);
            table2.addCell( "Page : " + pageNumber + " of "+ totalPage + " ".repeat(74) + "Total record: "+productList.size());
            System.out.println(table2.render());
        }
        public static void helpMenu() {
            Table table;
            table = new Table(1, BorderStyle.CLASSIC_COMPATIBLE_LIGHT_WIDE, ShownBorders.SURROUND);
            table.addCell("1." + " ".repeat(4) + "Press" + " ".repeat(4) + "* : Display all record of products");
            table.addCell("2." + " ".repeat(4) + "Press" + " ".repeat(4) + "w : Add new product");
            table.addCell(" ".repeat(6) + "Press" + " ".repeat(4) + "w#proname-unitprice-qty : shortcut for add new product");
            table.addCell("3." + " ".repeat(4) + "Press" + " ".repeat(4) + "r : read Content any content");
            table.addCell(" ".repeat(6) + "Press" + " ".repeat(4) + "r#proId : shortcut for read product by Id");
            table.addCell("4." + " ".repeat(4) + "Press" + " ".repeat(4) + "u : Update Data");
            table.addCell("5." + " ".repeat(4) + "Press" + " ".repeat(4) + "d : Delete Data");
            table.addCell(" ".repeat(6) + "Press" + " ".repeat(4) + "d#proId : shortcut for delete product by Id");
            table.addCell("6." + " ".repeat(4) + "Press" + " ".repeat(4) + "f : Display First Page");
            table.addCell("7." + " ".repeat(4) + "Press" + " ".repeat(4) + "p : Display Previous Page");
            table.addCell("8." + " ".repeat(4) + "Press" + " ".repeat(4) + "n : Display Next Page");
            table.addCell("9." + " ".repeat(4) + "Press" + " ".repeat(4) + "l : Display Last Page");
            table.addCell("10." + " ".repeat(3) + "Press" + " ".repeat(4) + "s : Search product by name");
            table.addCell("11." + " ".repeat(3) + "Press" + " ".repeat(4) + "h : Help");
            table.addCell("12." + " ".repeat(3) + "Press" + " ".repeat(4) + "e : Exit");
            System.out.println(table.render());
        }
}