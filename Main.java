package com.swiggy.assignment;

import java.util.Random;

class Player {
	int Health;
	int Strength;
	int Attack;
	Random randomNumber;     

	public Player(int health, int strength, int attack) {
		this.Health = health;
		this.Strength = strength;
		this.Attack = attack;
		this.randomNumber = new Random();
	}

	int getHealth() {
		return Health;
	}

	int attack() {
		
		
		int res=randomNumber.nextInt(6)+1;
		return res; // 6-sided die Roll    
	}

	int defend() {
		
		int res=randomNumber.nextInt(6)+1;
		return res; // 6-sided die Roll
	}

	void reduceHealth(int damage) {
		Health -= damage;		
	}

	boolean isAlive() {
		return Health > 0;
	}
}

class MagicalArena {
	Player playerA;
	Player playerB;

	public MagicalArena(Player playerA, Player playerB) {
		this.playerA = playerA;
		this.playerB = playerB;
	}

	void fight() {
		Player attacker = playerA.Health < playerB.Health ? playerA : playerB;
		Player defender = attacker == playerA ? playerB : playerA;

		while (playerA.isAlive() && playerB.isAlive()) {
			int attackRoll = attacker.attack();
			int defenseRoll = defender.defend();

			int damage = attackRoll * attacker.Attack - defenseRoll * defender.Strength;
			if (damage > 0) {
				defender.reduceHealth(damage);
			}

			// Swap roles for the next round
			Player temp = attacker;
			attacker = defender;
			defender = temp;
		}

		if (playerA.isAlive()) {
			System.out.println("Player A wins!");
		} else {
			System.out.println("Player B wins!");
		}
	}
}

public class Main {
	public static void main(String[] args) {
		Player playerA = new Player(100, 10, 5);
		Player playerB = new Player(50, 5, 10);

		MagicalArena arena = new MagicalArena(playerA, playerB);
		arena.fight();
	}
}
