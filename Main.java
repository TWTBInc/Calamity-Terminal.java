// Copyright 2022 TWTB Incorporated On behalf of Ryuzaki ikari

// Code comments meant to be read with word wrap
// Code comments meant
// To be read with word wrap

import java.io.*;
import java.util.*;
import java.nio.file.*;
import java.awt.Desktop;
import java.net.URI;
import java.net.URISyntaxException;

public class Main {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		Random random = new Random();
		String userInput = "";
		String[] userInputArray = {};
		String activeDirectory = "";
		String userName = System.getProperty("user.name");
		String baseDirectory = System. getProperty("user.dir") ;
		// Basedir done before anything bad because technically user canchange the active directory
		while (!userInput.equals("exit")) {
			System.out.flush();
			Integer randomInt = random.nextInt(12321);
			userInput = Integer.toString(randomInt);
			if (activeDirectory != "") {System.out.print("[" + userName + "/" + activeDirectory + "]: ");}
			else {System.out.print("[" + userName + "]: ");}
			userInput = input.nextLine();
			userInput = userInput.replaceAll("#myself", userName);

			userInputArray = userInput.split(" ");
			userInputArray[0] = userInputArray[0].toLowerCase();
			// How to make memory scanner developers mad 101

		switch (userInputArray[0]) {
			case "help":
				System.out.println("\nList of available commands: \nClear, Myself, CreateFile,\n DeleteFile, Browser, Search,\n Github, Say, Cd\n");
				break;
			case "clear":
				if (System.getProperty("os.name").contains("Windows")) {
					try {
						new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
					}

					catch (IOException | InterruptedException clearException) {
						System.out.println("Catastrophic failure unable to invoke cmd as a subprocess. Have you tried running Calamity Shell as an administrator?");
					}
				}

				else {
					System.out.println("\033[H\033[2J");

					// If you can have ANSI escape codes in Java can we do 0x80 interrupt?
				}
				break;

			case "createfile":
				if(userInputArray.length<2){System.out.println("No arguments; failure");break;}

				if (Arrays.asList(userInputArray).contains("--help")) {
					System.out.println("Creates a file with a name you choose :)");
					break;
				}

				try {

					File cFileSystem = new File(activeDirectory + "/" + userInputArray[1]);

					if (cFileSystem.createNewFile()) {
						// This means the file was created correctly.
						System.out.println("File " + userInputArray[1] + " was created with no errors!");
					}

					// This code is so old it was before try catch existed.

					else {
						// If we were over dramatic we would say Catastrophic failure, Segmentation fault on line 81 (Core Dumped)
						System.out.println("This file already exists!");
					}


				}

				catch (IOException fileError) {
					// This is an error, I don't know what the error is. But it's an error So we print the stack trace.
					System.out.println("An error occurred please refer to the following error message");
					fileError.printStackTrace();
				}

					// I'm not gonna fix the fact that we're looking for just a random exception instead of the one we expect to happen. Because legacy code.

					break;
					// This is around the normal amount of lines to create a file in Java


			case "credits":
				System.out.println("TWTB Incorporated on behalf of Ikari Ryuzaki");
				break;

			case "browser":
				if(userInputArray.length<2){System.out.println("No arguments; failure");break;}

				if (Arrays.asList(userInputArray).contains("--help")) {
					System.out.println("Opens a specified URL in your default browser :)");
					break;
				}

				userInputArray[1] = userInputArray[1].replaceAll("`", "");
				userInputArray[1] = userInputArray[1].replaceAll("%", "");
				userInputArray[1] = userInputArray[1].replaceAll("<", "");
				userInputArray[1] = userInputArray[1].replaceAll(">", "");
				userInputArray[1] = userInputArray[1].replaceAll("\"", "");
				userInputArray[1] = userInputArray[1].replaceAll("^", "");
				userInputArray[1] = userInputArray[1].replaceAll("\\s", "%20");

				// So we don't get errors while attempting to do this I mean we could try and catch but screw try catch plus it doesn't stop us from not going there.

				if (userInputArray[1].equals("")) {
					userInputArray[1] = "duckduckgo.com";
				}

				if(Desktop.isDesktopSupported()){
            		Desktop desktop = Desktop.getDesktop();

            		try {
                		desktop.browse(new URI("https://" + userInputArray[1]));
                		// Why because screw unsecured connections, thats why.
            		}

            		catch (IOException | URISyntaxException browserError) {

            			System.out.println("An error occurred please refer to the following error message. If you think this was by mistake please report this to our github page.");
                		browserError.printStackTrace();
                		System.out.println("\nDo note that if you included \"or ^ or a back-slash it will cause an error!");

            		}

        		}

        		break;

			case "github":
				if (Arrays.asList(userInputArray).contains("--help")) {
					System.out.println("Goes to our github page :)");
					break;
				}

				if(Desktop.isDesktopSupported()){
            		Desktop desktop = Desktop.getDesktop();

            		try {
                		desktop.browse(new URI("https://github.com/TWTBInc/Calamity-Terminal.java/"));
            		}

            		catch (IOException | URISyntaxException browserError) {
            			System.out.println("An error has occurred please refer to this as a backup: https://github.com/TWTBInc/Calamity-Terminal.java/");
            		}

        		}

        		break;

        	case "deletefile":
        		if(userInputArray.length<2){System.out.println("No arguments; failure");break;}

				if (Arrays.asList(userInputArray).contains("--help")) {
					System.out.println("Deletes a file of your choice :)");
					break;
				}

        		File cFileSystem = new File(activeDirectory + "/" + userInputArray[1]);

        		if (cFileSystem.exists()) {
        			cFileSystem.delete();
        		}

        		else {
        			System.out.println("That file doesn't exist! (Note: this command is case sensitive please keep that in mind.)");
        		}

        		break;

			case "search":
				if(userInputArray.length<2){System.out.println("No arguments; failure");break;}
				if (Arrays.asList(userInputArray).contains("--help")) {
					System.out.println("Searches duck duck go for whatever query you want :)");
					break;
				}

				userInput = userInput.replaceFirst("search", "");
				userInput = userInput.replaceFirst(" ", "");

				userInput = userInput.replaceAll("`", "");
				userInput = userInput.replaceAll("%", "");
				userInput = userInput.replaceAll("<", "");
				userInput = userInput.replaceAll(">", "");
				userInput = userInput.replaceAll("^", "");
				userInput = userInput.replaceAll("\"", "");
				userInput = userInput.replaceAll("\\s", "+");

				// Illegal characters spaces ` % ^ "" \ <>

				if(Desktop.isDesktopSupported()){
            		Desktop desktop = Desktop.getDesktop();

            		try {
                		desktop.browse(new URI("https://www.duckduckgo.com/?q=" + userInput + "&=ia=Calamity-Terminal"));
            		}

            		catch (IOException | URISyntaxException browserError) {
            			System.out.println("An error has occurred! Please refer to the following error message for technical details.");
                		browserError.printStackTrace();
            		}

            		// Every time you see an IOException note that its legacy code.

        		}

        		break;

        	case "say":
        		if(userInputArray.length<2){System.out.println("No arguments; failure");break;}

        		if (Arrays.asList(userInputArray).contains("--help")) {
					System.out.println("Echoes whatever you specify :)");
					break;
				}
				userInput = userInput.replaceFirst("say", "");
				userInput = userInput.replaceFirst(" ", "");

				System.out.println(userInput);
        		break;

			case "":
				break;

				// When I wrote this code only god and I knew how it worked now only god knows under here is an hours spent timer. Add 1 to it for every hour you spend trying to fix the above code

				// Hours spent = 302.9;

			case "exit":
				break;

			case "myself":
				System.out.println(userName);
				break;

			case "cd":
				if(userInputArray.length<2){activeDirectory = "";break;}

				if (Arrays.asList(userInputArray).contains("--help")) {
					System.out.println("Echoes whatever you specify :)");
					break;
				}

				if (userInputArray[1] == "/") {activeDirectory = "";}
				if (activeDirectory == "") {
					activeDirectory = activeDirectory + userInputArray[1];
				}
				else {
					activeDirectory = activeDirectory + "/" + userInputArray[1];
				}
				// Basically active dir goes b4 all that deals wit dirs and files, so everything bc unix.
				break;

			default:
				userInput = userInput.replaceFirst(userInputArray[0], "");
				userInput = userInput.replaceFirst(" ", "");

				try {
					new ProcessBuilder((activeDirectory + userInputArray[0]), userInput).inheritIO().start().waitFor();
				}
				catch (IOException | InterruptedException execException) {
					// NESTED TRY CATCH GO!
					try {
						// AHHAHAHAHAHAHHA WE TRULY ARE EVIL HERE AT TWTB
						new ProcessBuilder(baseDirectory + "Programs/" + userInputArray[0], userInput).inheritIO().start().waitFor();
					}

					catch (IOException | InterruptedException executableException) {
						System.out.println("Unable to find command or executable " + userInputArray[0]);
					}
				}
				break;
			}

		}

		// My girlfriend cheated on me with a squirrel
		// ^^^ That sucks Jimmothy
	}
}