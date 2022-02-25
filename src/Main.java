
// Copyright 2022 TWTB Incorporated

// Code comments meant to be read with word wrap
// Code comments meant
// To be read with word wrap

import java.util.Scanner;
import java.util.Random;
import java.io.File;
import java.io.IOException;
import java.awt.Desktop;
import java.net.URI;
import java.net.URISyntaxException;

// Purposes for all the imports Scanner for user input File for manipulating files IOException because some things need it Desktop so we make sure we aren't trying to open firefox on a server URI so we can open firefox as a webpage URISyntaxException so we can make sure that we aren't about to kill ourselves Base64 would allow us to create a random crypt and Random to create random stuff lol

public class Main {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		Random random = new Random();
		String userInput = "";
		String userName = System.getProperty("user.name");

		// We get the users userName which is important because we're going to have it in the userInput step

		// You can also see here that we only have one userInput variable this is to save memory unlike in old versions of CTerm where we decided "Yeah why not create a new variable?"

		while (!userInput.equals("exit")) {
			// We do this at top because after every loop of the while loop it'll check this so since its doing that we should take advantage of that and allow it to exit our loop more efficiently than just having a break with a case which is less efficient
			System.out.flush();
			Integer randomInt = random.nextInt(12321);
			userInput = Integer.toString(randomInt);
			System.out.print("[" + userName + "]: ");
			userInput = input.nextLine();
			userInput = userInput.toLowerCase();

			switch (userInput) {
				case "--help":
					System.out.println("List of available commands: Clear, Myself, CreateFile, DeleteFile, Browser\n");
					break;

				case "clear":
					System.out.println("\n".repeat(100));
					break;

				case "myself":
					System.out.println(userName);
					// Not sure why a user would want this but... Why not.
					break;

				case "createfile":
					System.out.print("What would you like to name this file: ");
					userInput = input.nextLine();

					try {

						File cFileSystem = new File(userInput);

						if (cFileSystem.createNewFile()) {
							// This means the file was created correctly.
							System.out.println("File " + userInput + " was created with no errors!");
						}

						else {
							// If we were overdramatic we would say Catastrophic failure, Segmentation fault on line 63 (Core Dumped)
							System.out.println("This file already exists!");
						}


					}

					catch (IOException fileError) {
						// This is an error, I don't know what the error is. But it's an error So we print the stack trace.
						System.out.println("An error occured please refer to the following error message");
						fileError.printStackTrace();
					}

					break;
					// This is a lot of lines to create a file... jesus.


				case "credits":
					// Not sure why anybody would want to see the credits for this but sure let them see the credits

					System.out.println("Calamity Software a subsidiary of TWTB Incorporated.");
					break;

				case "browser":

					System.out.print("Enter the url you want to go to (leave empty for google): ");
					userInput = input.nextLine();

					userInput = userInput.replaceAll("`", "");
					userInput = userInput.replaceAll("%", "");
					userInput = userInput.replaceAll("<", "");
					userInput = userInput.replaceAll(">", "");
					userInput = userInput.replaceAll("\\s", "%20");

					if (userInput.equals("")) {
						userInput = "google.com";
					}


					if(Desktop.isDesktopSupported()){

            			Desktop desktop = Desktop.getDesktop();

            			try {

                			desktop.browse(new URI("https://" + userInput));

            			}

            			catch (IOException | URISyntaxException browserError) {

            				System.out.println("An error occured please refer to the following error message. If you think this was by mistake please report this to our github page.");
                			browserError.printStackTrace();
                			System.out.println("\nDo note that if you included \"or ^ or a back-slash it will cause an error!");

            			}

        			}

        			break;

				case "github":
					if(Desktop.isDesktopSupported()){

            			Desktop desktop = Desktop.getDesktop();

            			try {

                			desktop.browse(new URI("https://github.com/TWTBInc/Calamity-Terminal.java/"));

            			}

            			catch (IOException | URISyntaxException browserError) {

            				System.out.println("An error has occured please refer to this as a backup: https://github.com/TWTBInc/Calamity-Terminal.java/");
                			

            			}

        			}
        			break;

        		case "deletefile":
        			System.out.print("Enter the name of the file you want to delete: ");
        			userInput = input.nextLine();

        			File cFileSystem = new File(userInput);

        			if (cFileSystem.exists()) {
        				cFileSystem.delete();
        			}

        			else {
        				System.out.println("That file doesn't exist! (Note: this command is case sensetive please keep that in mind.)");
        			}
        			break;

				case "google":
					System.out.print("What would you like to search: ");
					userInput = input.nextLine();

					userInput = userInput.replaceAll("`", "");
					userInput = userInput.replaceAll("%", "");
					userInput = userInput.replaceAll("<", "");
					userInput = userInput.replaceAll(">", "");
					userInput = userInput.replaceAll("\\s", "%20");

					// Illegal characters spaces ` % ^ "" \ <>

					if(Desktop.isDesktopSupported()){

            			Desktop desktop = Desktop.getDesktop();

            			try {

                			desktop.browse(new URI("https://www.google.com/search?q=" + userInput + "&sourceid=Calamity-Terminal"));

            			}

            			catch (IOException | URISyntaxException browserError) {

            				System.out.println("An error has occured! Please refer to the following error message for technical details.");

                			browserError.printStackTrace();

                			System.out.println("\nDo note that if you included \"or ^ or a back-slash it will cause an error!");

            			}

        			}
        			break;

        		case "say":
        			System.out.print("Enter what you want to echo: ");
        			userInput = input.nextLine();
        			System.out.println(userInput);
        			break;

				case "":
					break;

				case "exit":
					break;

				default:
					System.out.println("That isn't a command please enter a command. Type --help to see a list of available commands!");
					break;
			}

		}
	}
}
