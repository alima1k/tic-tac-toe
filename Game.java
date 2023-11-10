import java.util.Scanner;
public class Game{
	public static void Menu(){
		System.out.println("1. Новая игра");
		System.out.println("2. Выйти");
		Scanner scan = new Scanner (System.in);
		int choice = scan.nextInt();
		if (choice == 1){
			int d=0;// можно убрать
		}
		else if (choice == 2){
			Exit();
		}
		else {
			System.out.println("Некорректное значение, попробуйте снова:");
			Menu();
		}
	}
	public static int Size( ){//размеры поля
		System.out.println("Введите ширину поля");
		Scanner scan = new Scanner (System.in);
		int size = scan.nextInt();
    	return size;
    }

	public static String [][] Field(int size){//создание поля
    	String [][] field1= new String[size][size];
    	for (int i = 0; i < size; i++) {
    		for (int j = 1; j <= size; j++) {
    			field1[i][j-1] = Integer.toString(i*size+j);
    		}
    	}
    	return field1;
    	
    }
    public static void Regime(String [][]field, int size){//выюор режима
    	Scanner scan = new Scanner (System.in);
		System.out.println("Выберите режим игры (введите номер режима)");
    	System.out.println("1)Игра с другом          2) Игра с компьютером");
    	int regime = scan.nextInt();
		if (regime == 1){
			Regime1(field,1,size);
		}
		else if (regime == 2){
			//Regime2(); режим игры с компьютером
			int a=1;// можно убрать
			
		}
		else { 
			System.out.println("Некорректное значение, попробуйте снова:");
			Regime(field,size);
		}
	}
	public static void Regime1(String [][]field, int sign, int size){//поменять void 
		//если нет выигрышных / нечейных ситуаций
		//прописать действия при выигрышных / нечейных ситуациях
		String str;
		for (int i = 0; i < size; i++) { 
			str="";
			for (int j = 0; j < size; j++) {
				System.out.print(field[i][j]);
				System.out.print(" | ");
				str+="____";
				
			}	
			System.out.println("");
			System.out.println(str);	
		}
		Scanner scan = new Scanner (System.in);
		if (sign==1){
			System.out.println("Ваш ход - x .Выберите номер незанятой ячейки");
			int number=scan.nextInt();
			int t1=(number-1)/size;
			int t2=number-((number-1)/size)*size;
			if (field[t1][t2-1]!="X" && field[t1][t2-1]!="0"){
				field[t1][t2-1]="X";
				Regime1(field,2,size);
			}
			else {
			System.out.println("Выберите другую ячейку");
			Regime1(field,1,size);
			}

		}
		else {
			System.out.println("Ваш ход - 0 .Выберите номер незанятой ячейки");
			int number=scan.nextInt();
			int t1=(number-1)/size;
			int t2=number-((number-1)/size)*size;
			if (field[t1][t2-1]!="X" && field[t1][t2-1]!="0"){
				field[t1][t2-1]="0";
				Regime1(field,1,size);
			}
			else{
				System.out.println("Выберите другую ячейку");
				Regime1(field,2,size);
			}
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
		int size=Size();
		String [][] field=Field(size);
		Regime(field,size);

	}
}