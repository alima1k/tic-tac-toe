import java.util.Scanner;
public class Game{
	public static void Menu(){//функция меню
		System.out.println("1. Новая игра");
		System.out.println("2. Выйти");
		Scanner scan = new Scanner (System.in);
		int choice = scan.nextInt();
		if (choice == 1){
			//функция настройки выбора размера поля
		}else if (choice == 2){
			Exit();
		}else{
			System.out.println("Некорректное значение, попробуйте снова:");
			Menu();
		}
	}
	public static void Exit(){//функция выхода из игры с помощью меню
		System.out.println("Вы точно хотите выйти из игры?");
			System.out.println("1. Да");
			System.out.println("2. Нет");
			Scanner scan = new Scanner (System.in);
			int choice2 = scan.nextInt();
			if (choice2 == 1){
				System.exit(0);
			}else if (choice2 == 2){
				Menu();
			}else{
				System.out.println("Некорректное значение, попробуйте снова:");
				Exit();
			}
	}
	public static boolean AreThereAnyFreeCells(char[][] Field, int n){ //функция, считающая, есть ли еще свободные ячейки; n - размер поля 
		boolean FreeCells = false;
		for (int i = 0; i < n; i ++){
			for (int j = 0; j < n; j ++){
				if ((Field[i][j] >= '1') && (Field[i][j] <= '9')){
					FreeCells = true;
					break;
				}
			}
		}
		return FreeCells; //если свободные ячейки есть - true
	}
	public static boolean WinningSituations(char[][] Field, int n, char player){//функция, ищущая есть ли выигрышные ситуации для одного из игроков; char playerv- символ, за который играет игрок
		boolean win = true;
		for (int i = 0; i < n; i ++){
			if (Field[i][i] != player){
				win = false;
			}
		}
		if (win) {
			return true;
		}
		win = true;
		for (int i = 0; i < n; i ++){
			if (Field[i][n - 1 - i] != player){
				win = false;
			}
		}
		for (int i = 0; i < n; i ++){
			win = true;
			for (int j = 0; j < n; j ++){
				if (Field[i][j] != player){
					win = false;
					break;
				}
			}
		}
		if (win){
			return true;
		}
		for (int i = 0; i < n; i ++){
			win = true;
			for (int j = 0; j < n; j ++){
				if (Field[j][i] != player){
					win = false;
					break;
				}
			}
		}
		if (win){
			return true;
		}
		return false;
	}
	public static void main(String[] args){
		System.out.println("Добро пожаловать в игру Крeстики - Нолики !");
		Menu();
	}
}