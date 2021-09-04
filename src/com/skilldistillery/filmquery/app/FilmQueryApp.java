package com.skilldistillery.filmquery.app;

import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {

	DatabaseAccessor db = new DatabaseAccessorObject();

	public static void main(String[] args) {
		FilmQueryApp app = new FilmQueryApp();
		app.printMenu();
		
//    app.launch();
	}

	private void test() {
//    System.out.println(film);
	}

	private void launch() {
		System.out.println("Make your selection: ");
		Scanner input = new Scanner(System.in);
		int answer = input.nextInt();
		switch(answer) {
		case 1:
			startUserInterface(input);

		
		
		
		}

		input.close();
	}

	private void startUserInterface(Scanner input) {
		System.out.println("Enter Film ID: "); 
		int filmId = input.nextInt();
		Film film = db.findFilmById(filmId);
		System.out.println(film);

	}

	private void printMenu() {
		System.out.println("*******************************\n");

		System.out.println("\tWelcome to Find-A-Film!\t\n");
		System.out.println("Through this Application you'll be able to: \n");
		System.out.println("1) Look up film by film ID");
		System.out.println("2) Look up a film by keyword search\n");
		System.out.println(" Enter 9 to exit the app\n\n");
		launch();
		
		
		
		
		
	}

}
