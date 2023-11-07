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
	public static void main(String[] args){
		System.out.println("Добро пожаловать в игру Крeстики - Нолики !");
		Menu();
	}
}